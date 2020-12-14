import java.util.*;
class Solution{
	public static void main(String[] args) {
		int[] v = new int[]{0,1,2,3,4};
		int[] w = new int[]{0,2,4,4,5};
		int N = 4;
		int V = 5;
		System.out.println("**************** 1");
		System.out.println(maxValue1(V,N,v,w));
		System.out.println("**************** 2");
		System.out.println(maxValue2(V,N,v,w));
		System.out.println("**************** 3");
		System.out.println(maxValue3(V,N,v,w));
		System.out.println("**************** 4");
		System.out.println(maxValue4(V,N,v,w));
		System.out.println("**************** 5");
		System.out.println(maxValue5(V,N,v,w));
		System.out.println("**************** 6");
		int[] s = new int[]{0,3,1,3,2};
		System.out.println(maxValue6(V,N,v,w,s));
		System.out.println("**************** 7");
		System.out.println(maxValue7(V,N,v,w,s));
		System.out.println("**************** 8");
		System.out.println(maxValue8(V,N,v,w,s));
		System.out.println("**************** 9");
		int[] s2 = new int[]{0,-1,1,0,2};
		System.out.println(maxValue9(V,N,v,w,s2));
		System.out.println("**************** 10");
		System.out.println(maxValue10(V,N,v,w,s2));
		System.out.println("**************** 11");
		int M = 6;
		int[] m = new int[]{0,2,4,4,5};
		int[] w2 = new int[]{0,3,4,5,6};
		System.out.println(maxValue11(V,N,M,v,w2,m));
		System.out.println("**************** 12");
		maxValue12();
		System.out.println("**************** 13");
		System.out.println(maxValue13(V,N,v,w));
		System.out.println("**************** 14");
		System.out.println(maxValue14(V,N,v,w));
		System.out.println("**************** 15");
	}
	public static void print(int[][] arrs){
		for(int[] arr:arrs){
			for(int i:arr) System.out.print(i+" ");
			System.out.println();
		}
	}
	/**
	背包问题：1 : 0-1背包问题
		问题描述：有一个容量为V的背包，然后有N个物品，N个物品的价值与体积分别为w_i,v_i,且每一个物品最多只能使用一次，求用该背包能装取的最大价值是多少？
		状态定义：dp[i][j]表示前i个物品放入容量是j的背包里所能获得的最大的价值
		状态转移方程：
			dp[i][j] = dp[i-1][j] , if not choose item i , 表示前i-1个物品占用j空间的最大价值
			dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-v[i]] + w[i]) ,if choose item i，表示选择第i个物品，那么就相当于是从体积j-v[i]转移过来的状态
		初始状态：dp[0][0] = 0

		时间复杂度一般而言是不能够进行优化的，但是可以对空间复杂度进行优化，
		状态定义：dp[j] 表示体积为j的时候，所能装的物品的最大价值
		状态转移方程:dp[j] = max(dp[j],dp[j-v[i]] + w[i]) 但是要注意的是第二层循环中，要从大到小进行遍历，不然的话，因为若是继续从小到大循环，后面算的时候，用的是这一层已经算过的数据，就变成dp[i][j]=max(dp[i][j],dp[i][j-v[i]]+w[i]) ，（这正好是完全背包一维的解法，每个物品可以选无限次）而从大到小算的话一定用的是上一层的状态
		初始状态：dp[...] = 0
	*/

	public static int maxValue1(int V,int N,int[] v,int[] w){
		int[][] dp = new int[N+1][V+1];
		for(int i = 1;i<=N;i++){
			for(int j = 1;j<=V;j++){
				if(j>=v[i]) dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-v[i]]+w[i]);
				else dp[i][j] = dp[i-1][j];
			}
		}
		print(dp);
		return dp[N][V];
	}
	public static int maxValue2(int V,int N,int[] v,int[] w){
		int[] dp = new int[V+1];
		for(int i = 1;i<N;i++){
			for(int j = V;j>=v[i];j--){
				dp[j] = Math.max(dp[j],dp[j-v[i]]+w[i]);
			}
		}
		for(int i:dp) System.out.print(i+" ");
		System.out.println();
		return dp[V];
	}
	/**
	背包问题：2 : 完全背包问题
		问题描述：有一个容量为V的背包，然后有N个物品，N个物品的价值与体积分别为w_i,v_i,且每一个物品可以用多次，求用该背包能装取的最大价值是多少？
		状态定义：dp[i][j]表示前i个物品放入容量是j的背包里所能获得的最大的价值
		状态转移方程：
			dp[i][j] = dp[i-1][j] , if not choose item i , 表示前i-1个物品占用j空间的最大价值
			dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-v[i]] + w[i]) ,if choose item i，表示选择第i个物品，那么就相当于是从体积j-v[i]转移过来的状态
		初始状态：dp[0][0] = 0


		时间复杂度一般而言是不能够进行优化的，但是可以对空间复杂度进行优化，
		状态定义：dp[j] 表示体积为j的时候，所能装的物品的最大价值
		状态转移方程:dp[j] = Math.max(dp[j],dp[j-v[i]]+w[i])
		初始状态：dp[0][0] = 0

	*/
	public static int maxValue3(int V,int N,int[] v,int[] w){
		int[][] dp = new int[N+1][V+1];
		for(int i=1;i<=N;i++){
			for(int j = 1;j<=V;j++){
				for(int k=0;k*v[i]<=j;k++) {
                    dp[i][j]=Math.max(dp[i][j], dp[i-1][j-k*v[i]]+w[i]*k);
                }
			}
		}
		print(dp);
		return dp[N][V];
	}
	public static int maxValue4(int V,int N,int[] v,int[] w){
		int[][] dp = new int[N+1][V+1];
		for(int i=1;i<=N;i++){
			for(int j = 1;j<=V;j++){
				if(j>=v[i]) dp[i][j] = Math.max(dp[i-1][j],dp[i][j-v[i]]+w[i]);
				else dp[i][j] = dp[i-1][j];
			}
		}
		print(dp);
		return dp[N][V];
	}
	public static int maxValue5(int V,int N,int[] v,int[] w){
		int[] dp = new int[V+1];
		for(int i = 1;i<N;i++){
			for(int j = v[i];j<=V;j++){
				dp[j] = Math.max(dp[j],dp[j-v[i]]+w[i]);
			}
		}
		for(int i:dp) System.out.print(i+" ");
		System.out.println();
		return dp[V];
	}
	/**
	背包问题：3 : 多重背包问题
		问题描述：有 N种物品和一个容量是V的背包，第 i种物品最多有 s_i件，每件体积是 v_i，价值是 w_i，求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。输出最大价值。
		状态定义：dp[i][j]表示前i个物品放入容量是j的背包里所能获得的最大的价值
		状态转移方程：
			dp[i][j]=Math.max(dp[i][j], dp[i-1][j-k*v[i]]+w[i]*k)
		初始状态：dp[0][0] = 0

		空间优化：
		状态定义：dp[j] 表示体积为j的时候，所能装的物品的最大价值
		状态转移方程：dp[j]=Math.max(dp[j], dp[j-k*v[i]]+w[i]*k);
		初始状态：dp[...] = 0


		二进制优化,时间上和空间上的优化
		二进制优化思想：这道题的数据范围如果用三层循环的话是达到了1e9，所以必须优化它。其实可以把它转化为一个01背包的问题。每个物品有s件，我们可以把它差分成s份，每份物品当做不同的个体，即只能选一次，这就转化为了01背包物品，但是这样的话，物品个数变成了1000*2000=2e6，再循环一层空间的话，还是1e9的复杂度。
		那么继续优化，一个物品的数量是s的话，只要把s拆分成一些数字，使它们能够表示出1-s中任意一个数字，就可以，没必要把它拆成s个1。那么这样的数字最少需要多少个呢？最少需要log(s)个，向上取整。
		比如7，它最少需要3个数字来表示：
		即 1（2^0=1 ）， 2（2^1=2）， 4（2^2=4）。
		原因：每个数字有2种可能选或不选，那么可以表示的不同数字个数就是 2 * 2 * 2 = 8。但是还需要注意一个问题，就是有些数字可能能够表示出来一些大于s的数字，但是这件物品最多只有s件，那么就需要特殊处理一下最后一个数。
		状态定义：dp[j] 表示体积为j的时候，所能装的物品的最大价值
		状态转移方程：dp[j] = Math.max(dp[j],dp[j-v2[i]]+w2[i]);
		初始状态：dp[...] = 0

	*/
	public static int maxValue6(int V,int N,int[] v,int[] w,int[] s){
		int[][] dp = new int[N+1][V+1];
		for(int i=1;i<=N;i++){
			for(int j = 1;j<=V;j++){
				for(int k=0;k<=s[i] && k*v[i]<=j;k++) {
                    dp[i][j]=Math.max(dp[i][j], dp[i-1][j-k*v[i]]+w[i]*k);
                }
			}
		}
		print(dp);
		return dp[N][V];
	}
	public static int maxValue7(int V,int N,int[] v,int[] w,int[] s){
		int[] dp = new int[V+1];
		for(int i=1;i<=N;i++){
			for(int j = V;j>=v[i];j--){
				for(int k=0;k<=s[i] && k*v[i]<=j;k++) {
                    dp[j]=Math.max(dp[j], dp[j-k*v[i]]+w[i]*k);
                }
			}
		}
		for(int i:dp) System.out.print(i+" ");
		System.out.println();
		return dp[V];
	}
	public static int maxValue8(int V,int N,int[] v,int[] w,int[] s){
		int[] dp = new int[V+1];
		//01背包相当于物品件数为1的多重背包
		int[] v2 = new int[N*V];
		int[] w2 = new int[N*V];
		// 遍历所有的物品的数量
		int k = 1;
		for(int i = 1;i<s.length;i++){
			// 记录2的多少次方
			int j = 1;
			while(j<=s[i]){
				v2[k] = v[i]*j;
				w2[k++] = w[i]*j;
				s[i]-=j;
				j*=2;
			}
			if(s[i] >0){
				v2[k] = v[i] * s[i];
				w2[k++] = w[i] * s[i];
			}
		}
		for(int i = 1;i<k;i++){
			for(int j = V;j>=v2[i];j--){
				dp[j] = Math.max(dp[j],dp[j-v2[i]]+w2[i]);
			}
		}
		return dp[V];
	}
	/**
	背包问题：4 : 混合背包问题
		问题描述：有N种物品和一个容量是V的背包。物品一共有三类：第一类物品只能用1次（01背包）；第二类物品可以用无限次（完全背包）；第三类物品最多只能用 s_i 次（多重背包）；每种体积是 v_i，价值是 w_i。求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。输出最大价值。
		si=−1 表示第 i 种物品只能用1次；
		si=0 表示第 i 种物品可以用无限次；
		si>0 表示第 i 种物品可以使用 si 次
		思路：是一个前三种背包问题的综合，如果明白了前面的，就很简单了，只需要判断一下类型，如果是多重背包，将它转换为01背包插入数组当中，然后按着不同类型的处理方式去遍历空间大小即可
	*/
	public static int maxValue9(int V,int N,int[] v,int[] w,int[] s){
		int[] dp = new int[V+1];
		//01背包相当于物品件数为1的多重背包
		for(int i =0;i<s.length;i++) if(s[i] == -1) s[i] = 1;
		for(int i = 1;i<=N;i++){
			// 完全背包问题
			if(s[i] == 0){
				for(int j = v[i];j<=V;j++) dp[j] = Math.max(dp[j],dp[j-v[i]]+w[i]);
			}else{
				// 多重背包问题
				for(int j = V;j>=v[i];j--){
					for(int k=0;k<=s[i] && k*v[i]<=j;k++) {
                    	dp[j]=Math.max(dp[j], dp[j-k*v[i]]+w[i]*k);
                	}
				}
			}
		}
		return dp[V];
	}
	/**
	利用二进制优化
	*/
	public static int maxValue10(int V,int N,int[] v,int[] w,int[] s){
		int[] dp = new int[V+1];
		//01背包相当于物品件数为1的多重背包
		for(int i = 0; i < N; i++){
            if(s[i] == 0){
                // 完全背包问题
                for(int j = v[i]; j <= V; j++){
                    dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
                }
            }else{
                // 多重背包问题，01背包是多重背包的特例，可以一并处理
                s[i] = Math.abs(s[i]);
                for(int j = 1; s[i] >= j; s[i] -= j, j *= 2){
                    for(int k = V; k >= j * v[i]; k--){
                        dp[k] = Math.max(dp[k], dp[k - j * v[i]] + j * w[i]);
                    }
                }
                if(s[i] > 0){
                    for(int j = V; j >= s[i] * v[i]; j--){
                        dp[j] = Math.max(dp[j], dp[j - s[i] * v[i]] + s[i] * w[i]);
                    }
                }
            }
        }
		return dp[V];
	}
	
	/**
	背包问题：5 : 二维费用的背包问题
		问题描述：有 N 件物品和一个容量是 V 的背包，背包能承受的最大重量是 M。每件物品只能用一次。体积是 vi，重量是 mi，价值是 wi。
		求解将哪些物品装入背包，可使物品总体积不超过背包容量，总重量不超过背包可承受的最大重量，且价值总和最大。输出最大价值
	*/
	public static int maxValue11(int V,int N,int M,int[] v,int[] w,int[] m){
		int[][] dp = new int[V+1][M+1];
		for(int i=1;i<=N;i++){
			for(int j  =V;j>=v[i];j--){
				for(int k = M;k>=m[i];k--){
					dp[j][k] = Math.max(dp[j][k],dp[j-v[i]][k-m[i]]+w[i]);
				}
			}
		}
		return dp[V][M];
	}
	/**
	背包问题：6 : 分组背包问题
		问题描述：有 N 组物品和一个容量是 V 的背包。每组物品有若干个，同一组内的物品最多只能选一个。
		每件物品的体积是 vij，价值是 wij，其中 i 是组号，j 是组内编号。求解将哪些物品装入背包，可使物品总体积不超过背包容量，且总价值最大。输出最大价值。
	*/
	public static int maxValue12(){
		int v = 10;
		int n = 3;
		int[] dp = new int[v+1];
		int[][] w = new int[][]{{2, 3, 5}, {1, 3, 2}, {7, 4, 2, 6}};
		int[][] p = new int[][]{{4, 6, 7}, {1, 4, 8}, {3, 6, 2, 4}};
		// 遍历背包容量大小
		for(int j = v; j >= 0; j--){ 
			// 当前组有w.length个物品
            for(int i = 0; i < w.length; i++){ 
                // 遍历每一个背包中的物品重量
                for(int k = 0;k<w[i].length;k++){
                	if(j >= w[i][k]){
                		dp[j] = Math.max(dp[j],dp[j-w[i][k]] + p[i][k]);
                	}
                }
            }
        }
        System.out.println(dp[v]);
		return 0;
	}
	/**
	背包问题：7 : 有依赖的背包问题(树形DP+背包问题)
	问题描述：
	*/
	/**
	背包问题：8 : 背包问题求方案数
		问题描述：有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次。第 i 件物品的体积是 vi，价值是 wi。求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。输出 最优选法的方案数。注意答案可能很大，请输出答案模 10^9+7 的结果。
		思路：在原来01背包的基础上加一个表示方案数的数组即可。注意初始化，如果只把num[0]赋值成1，那么需要把对应的01背包转化为体积恰好是j的情况下的最大价值。最后找出最大价值，将此价值对应的所有体积的方案数加上即可。
	*/
	public static int maxValue13(int V,int N,int[] v,int[] w){
		// dp1[j] 表示当前容量为j时的最大价值
		int[] dp1 = new int[V+1];
		// dp2[j]表示当前容量为j时的最大价值的方案数目
		int[] dp2 = new int[V+1];
		Arrays.fill(dp1,Integer.MIN_VALUE);
		// 就算一个也不拿，也是一种方案
		Arrays.fill(dp2,1);
		for(int i=1;i<=N;i++){
			for(int j  = V;j>=v[i];j--){
				int temp = dp1[j-v[i]] + w[i];
				if(temp == dp1[j]) {
					dp2[j]+=dp2[j-v[i]];
				}
				else if(dp1[j] < temp){
					dp1[j] = temp;
					dp2[j] = dp2[j-v[i]];
				}
				dp2[j]%=1000000007;
			}
		}
		int maxw = Integer.MIN_VALUE;
		for(int i=1;i<=V;i++)
        	maxw=Math.max(maxw,dp1[i]);
    	int ans=0;
    	for(int i=1;i<=V;i++)
        	if(dp1[i]==maxw)
            	ans=(ans+dp2[i])%1000000007;
		return ans;
	}
	/**
	背包问题：9 : 背包问题求具体方案
	问题描述：有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次。第 i 件物品的体积是 vi，价值是 wi。求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。输出 字典序最小的方案。这里的字典序是指：所选物品的编号所构成的序列。物品的编号范围是 1…N。
	*/
	public static int maxValue14(int V,int N,int[] v,int[] w){
		// 就是记录转移方案
		int[][] dp = new int[N+2][V+2];
		for(int i = N;i>=1;i--){
			for(int j = 0;j<=V;j++){
				dp[i][j] = dp[i+1][j];
				if(j>=v[i]) dp[i][j] = Math.max(dp[i][j],dp[i+1][j-v[i]]+w[i]);
			}
		}
		int x = V;
		for(int i = 1;i<=N;i++){
			if(x-v[i]>=0 && dp[i][x] == dp[i+1][x-v[i]]+w[i]){
				System.out.print(i + " ");
				x-=v[i];
			}
		}
		System.out.println();
		for(int[] info:dp){
			for(int i:info) System.out.print(i+" ");
			System.out.println();
		}
		return 0;
	}

}