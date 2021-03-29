# Reservoir Algorithm

> 蓄水池抽样算法

给定一个数据流，长度为n(从1开始)，在只遍历一遍的情况下，能够随机取出m个不同数据的方法



> Algorithm

1 若接受的数据量<=m，则直接放入到蓄水池中

2 当接受到第i(i>m)个数据的时候,在[1,i]中随机取得一个随机数d，如果d落到了[1,m]区间内，则用第i个数据替换掉蓄水池中第d个数据

3 重复步骤2，直到n个数据都被遍历过

> The correct of the algorithm

**第i个接收到的数据最后能够留在蓄水池中的概率=第i个数据进入过蓄水池的概率*之后第i个数据不被替换的概率（第i+1到第N次处理数据都不会被替换）。**

1 若i<=m,直接放入蓄水池中，则第i个数据进入蓄水池的概率为1

2 若i>m,在[1,i]中取随机数d，如果d<=m，则用第i个数据替换蓄水池中的第d个数据，则第i个数据进入蓄水池的概率为$\frac{m}{i}$

3 若i<=m，程序会从接收到第m+1个数据开始执行替换操作，那么第m+1次处理数据的时候会替换掉蓄水池中的数据的概率为$\frac{m}{m+1}$,会替换掉第i个数据的概率为$\frac{1}{m}$，则第m+1次处理替换掉第i个数据的概率为$\frac{m}{m+1} * \frac{1}{m} = \frac{1}{m+1}$,不被替换的概率为$1 - \frac{1}{m+1} = \frac{m}{m+1}$,依次类推，第n次处理不替换掉第i个数据的概率为$\frac{m}{m+1}*\frac{m+1}{m+2}*...*\frac{n-1}{n} = \frac{m}{n}$.

4 若i>m,程序从接收到第i+1个数据开始有可能替换第i个数据，参考3，之后第i个数据不被替换的概率为$\frac{i}{n}$.

综上所述：

当i<=m时，第i个数据留到蓄水池中的概率为$1 * \frac{m}{n} = \frac{m}{n}$.

当i>m时，第i个数据留到蓄水池中的概率为$\frac{m}{i} * \frac{i}{n} = \frac{m}{n}$.

> The Algorithm realize

```java
import java.util.ArrayList;
import java.util.List;
class Solution{
	public static void main(String[] args) {
		int m = 5;
		int[] nums = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		ArrayList<Integer> res = new ArrayList<>();
		for(int i = 0;i<nums.length;i++){
			if(i < m){
				res.add(nums[i]);
			}
			else{
				int d = (int)(Math.random()*(i+1));  
				if(d >= 0 && d<=m-1){
					res.remove(d);
					res.add(i);
				}
			}
		}
		System.out.println(res);
	}
}
```

