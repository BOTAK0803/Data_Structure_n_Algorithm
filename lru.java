class LRU{
	static class Node{
		public int key;
		public int val;
		public Node next;
		public Node prev;
		public Node(int k,int v){
			this.key = k;
			this.v = v;
		}
	}
	static class DoubleList{
		private Node head;
		private Node tail;
		private int size;
		public DoubleList(){
			head = new Node(0,0);
			tail = new Node(0,0);
			head.next = tail;
			tail.prev = head;
			size = 0;
		}
		public void addLast(Node x){
			x.prev = tail.prev;
			x.next = tail;
			tail.prev.next = x;
			tail.prev = x;
			size++;
		}
		public void remove(Node x){
			x.prev.next = x.next;
			x.next.prev = x.prev;
			size--;
		}
		public Node removeFirst(){
			if(head.next == tail) return null;
			Node first = head.next;
			remove(first);
			return first;
		}
		public int size(){
			return size;
		}
	}
	static class LRUCache{
		private HashMap<Integer,Node> map;
		private DoubleList cache;
		private int cap;
		public LRUCache(int capacity){
			this.cap = capacity;
			map = new HashMap<>();
			cache = new DoubleList();
		}
		// 将某一个key提升为最近最常使用的key
		private void makeRecently(int key){
			Node x = map.get(key);
			cache.remove(x);
			cache.addLast(x);
		}
		// 添加最近使用的元素
		private void addRecently(int key,int val){
			Node x = new Node(key,val);
			cache.addLast(x);
			map.put(key,x);
		}	
		// 删除某一个key
		private void seleteKey(int key){
			Node x = map.get(key);
			cache.remove(x);
			map.remove(key);
		}
		// 删除最久未使用的元素
		private void removeLeastRecently(){
			Node deletedNode = cache.removeFirst();
			int deletedKey = deletedNode.key;
			map.remove(deletedKey);
		}
		public int get(int key){
			if(!map.containsKey(key)) return -1;
			makeRecently(key);
			return map.get(key).val;
		}
		public void put(int key, int val){
			if(map.containsKey(key)){
				deletedKey(key);
				addRecently(key,val);
				return;
			}
			if(cap == cache.size()) removeLeastRecently();
			addRecently(key,val);
		}
	}
}