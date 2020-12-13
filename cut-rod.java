import java.util.*;
class Solution{
	static int[] p = new int[]{0,1,5,8,9,10,17,17,20,24,30};
	static int[] r = new int[p.length];
	public static void main(String[] args) {
		Arrays.fill(r,Integer.MIN_VALUE);
		System.out.println("--------------------");
		System.out.println(cutRod(p,10));
		System.out.println("--------------------");
		System.out.println(memoizedCutRod(p,10,r));
		System.out.println("--------------------");
		System.out.println("--------------------");
		System.out.println(bottomUpCutRod(p,10));
		System.out.println("--------------------");
		System.out.println(bottomUpCutRod2(p,7));
	}
	/**
	int[] p : 价格数组
	int n : 长度
	function：返回长度为n的钢条的最大收益
	*/
	public static int cutRod(int[] p , int n){
		if(n == 0) return 0;
		int q = Integer.MIN_VALUE;
		for(int i = 1;i<=n;i++){
			q = Math.max(q,p[i] + cutRod(p,n-i));
		}
		return q;
	}
	/**
	带备忘录的自顶向下方法
	*/
	
	public static int memoizedCutRod(int[] p,int n,int[] r){
		if(r[n] >= 0) return r[n];
		if(n==0) return 0;
		int q = Integer.MIN_VALUE;
		for(int i = 1;i<=n;i++){
			q = Math.max(q,p[i] + memoizedCutRod(p,n-i,r));
		}	
		r[n] = q;
		return q;

	}
	/**
	*/
	public static int bottomUpCutRod(int[] p,int n){
		int[] dp = new int[p.length];
		dp[0] = 0;
		for(int j = 1;j<=n;j++){
			int q = Integer.MIN_VALUE;
			for(int i =1;i<=j;i++){
				q=Math.max(q,p[i] + dp[j-i]);
			}
			dp[j] = q;
		}
		return dp[n];

	}
	/**
	*/
	public static int bottomUpCutRod2(int[] p,int n){
		int[] dp = new int[p.length];
		int[] s = new int[p.length];
		dp[0] = 0;
		for(int j = 1;j<=n;j++){
			int q = Integer.MIN_VALUE;
			for(int i =1;i<=j;i++){
				if(q < p[i] + dp[j-i]){
					q = p[i]+dp[j-i];
					s[j] = i;
				}
			}
			dp[j] = q;
		}
		int t = n;
		while(t>0){
			System.out.print(s[t]+" ");
			t-=s[t];
		}
		System.out.println();
		return dp[n];
	}
}