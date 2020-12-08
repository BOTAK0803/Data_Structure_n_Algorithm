class Test{
	public static void main(String[] args) {
		String S = "aabaabaabaac";
		String T = "aabaac";
		// int[] next = new int[]{-1,0,0,0,1};
		int[] next = getNext(T);
		for(int i = next.length -1;i>0;i--) next[i] = next[i-1]+1;
		next[0] = 0;
		// print(getNext(T));
		System.out.println(kmp(S,T,next));
		System.out.println(vilot(S,T));

		
	}
	// 暴力求解
	public static int vilot(String target,String pattern){
		for(int i = 0;i<target.length() - pattern.length()+1;i++) if(pattern.equals(target.substring(i,i+ pattern.length()))) return i;
		return 0;
	}
	// Rabin-Karp算法
	public static int RK(){
		return 0;
	}
	// 有限自动状态机
	// kmp算法
	public static int kmp(String target,String pattern,int[] next){
		int i = 0;
		int j = 0;
		int index = 0;
		while(i < target.length()){
			if(target.charAt(i) == pattern.charAt(j)){
				// System.out.println("000000");
				i++;
				j++;
				if(j == pattern.length()) return i-j;
			}else{
				int move = 1;
				// if(j > 0) move = j-0-next[j]; 
				if(j > 0) move = next[j]; 
				index += move;
				// System.out.println("move" + move);
				// System.out.println("index" + index);
				i=index;
				j=0;

			}
		}
		return 0;
	}
	public static int[] getNext(String pattern){
		// i表示子串的长度
		int i = 0;
		int[] res = new int[pattern.length()];
		for(i = 1;i<pattern.length();i++){
			if(i==1) {
				res[i-1] = 0;
				continue;
			}else{
				String str = pattern.substring(0,i);
				// System.out.println(str);
				int left = 0;
				int right = i-1;
				int nex = 0;
				while(left < i-1 && right > 0){
					if(str.charAt(left) == str.charAt(right)){
						nex++;
						left++;
						right--;
					}else{
						break;
					}
				}
				res[i-1] = nex;
			}


		}
		
		return res;
	}
	public static void print(int[] arr){
		for(int i:arr) System.out.print(i + " ");
		System.out.println();
	}
}