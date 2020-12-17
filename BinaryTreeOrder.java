import java.util.*;
class Solution{
	static class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int val){
			this.val = val;
			this.left = null;
			this.right = null;
		};
	} 
	public static void main(String[] args) {
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(5);
		TreeNode t6 = new TreeNode(6);
		TreeNode t7 = new TreeNode(7);
		t1.left = t2;
		t1.right = t3;
		t2.left = t4;
		t3.left = t6;
		t6.right = t7;
		System.out.println("采用递归前序遍历结果:-----");
		preOrder1(t1);
		System.out.println();
		System.out.println("采用迭代前序遍历结果:-----");
		preOrder2(t1);
		System.out.println();
		System.out.println("采用递归中序遍历结果:-----");
		inOrder1(t1);
		System.out.println();
		System.out.println("采用迭代中序遍历结果:-----");
		inOrder2(t1);
		System.out.println();
		System.out.println("采用递归后序遍历结果:-----");
		postOrder1(t1);
		System.out.println();
		System.out.println("采用迭代后序遍历结果:-----");
		postOrder2(t1);
		System.out.println();
		System.out.println("采用迭代后序遍历结果:-----");
		postOrder3(t1);


	}
	/**
	采用递归的方法进行前序遍历
	*/
	public static void preOrder1(TreeNode root){
		if(root == null) return;
		System.out.print(root.val);
		preOrder1(root.left);
		preOrder1(root.right);

	}
	/**
	采用递归的方法进行前序遍历
	*/
	public static void preOrder2(TreeNode root){
		if(root == null) return;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		List<Integer> res = new ArrayList<Integer>();
		while(root !=null || (!stack.isEmpty())){
			if(root != null){
				res.add(root.val);//步骤一，取根节点的值
                stack.push(root);//把根节点放入栈中
                root=root.left;//步骤二，遍历左子树
            }else{
                TreeNode tem=stack.pop();
                root=tem.right;//步骤三，遍历右子树
            }
		}	
		for(int i:res) System.out.print(i);
	}
	/**
	采用递归的方法进行中序遍历
	*/
	public static void inOrder1(TreeNode root){
		if(root == null) return;
		inOrder1(root.left);
		System.out.print(root.val);
		inOrder1(root.right);

	}
	/**
	采用迭代的方法进行中序遍历
	*/
	public static void inOrder2(TreeNode root){
		if(root == null) return;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		List<Integer> res = new ArrayList<Integer>();
		while(root!=null||(!stack.isEmpty())){
            if(root!=null){
                stack.push(root);//把根节点放入栈中
                root=root.left;//步骤一，遍历左子树
            }
            else{
                TreeNode tem=stack.pop();
                res.add(tem.val);//步骤二，取根结点的值
                root=tem.right;//步骤三，遍历右子树
            }
        }
        for(int i:res) System.out.print(i);
	}
	/**
	采用递归的方法进行后序遍历
	*/
	public static void postOrder1(TreeNode root){
		if(root == null) return;
		postOrder1(root.left);
		postOrder1(root.right);
		System.out.print(root.val);

	}
	/**
	采用迭代的方法进行后序遍历
	*/
	public static void postOrder2(TreeNode root){
		List<Integer> res=new ArrayList<>();
        Stack<TreeNode> stack=new Stack<>();
        while(root!=null||(!stack.empty())){
            if(root!=null){
             stack.push(root);//把根节点放入栈中
             res.add(0,root.val);//步骤一，在index=0处插入根结点的值
             root=root.right;//步骤二，遍历右子树
            }
            else{
                TreeNode tem=stack.pop();
                root=tem.left;//步骤三，遍历左子树
            }
        }
        for(int i:res) System.out.print(i);
	}
	public static void postOrder3(TreeNode root){
		List<Integer> res = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode prev = null;
		while(root!=null||(!stack.isEmpty())){
			while(root!=null){
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
		}
		for(int i:res) System.out.print(i);
	}

}