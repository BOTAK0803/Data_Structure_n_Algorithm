# 字典序排序

## leetcode 386 字典序排序

> 给定一个正整数n，返回从1到n的字典序排序
>
> 例如：给定n=13
>
> 返回[1,10,11,12,13,2,3,4,5,6,7,8,9]

> Idea ： 字典序排序可以抽象成为一棵树，由小到大的输出其实就是这棵树的前序遍历。

```java
// 递归版本
class Solution{
  public List<Integer> lexicalOrder(int n){
    List<Integer> list = new ArrayList<>();
    for(int i = 1;i<10;i++) dfs(n,i,list);
    return list;
    
  }
  /**
  params : n 目标字典序中最大的元素
  params : i 现在遍历的字典序元素
  list : 存储当前已经遍历过的字典序元素
  */
  public void dfs(int n,int i,List<Integer> list){
    if(i>n) return;
    list.add(i);
    for(int j = 0;j<=9;j++) dfs(n,i*10+j,list);
  }
}
```

```java
// 非递归版本，也就是去模拟递归
class Solution{
  public List<Integer> lexicalOrder(int n){
    List<Integer> list = new ArrayList<>();
    int curr = 1;
    // 10叉树的前序遍历
    for(int i = 0;i<n;i++){
      list.add(curr);
      if(curr * 10 <= n) {
        curr*=10;
      }else{
        if(curr>=n) curr/=10;
        curr+=1;
        while(curr%10==0) curr/=10;
      }
    }
    return list;
  }
}
```

## leetcode 440

> 给定整数 `n` 和 `k`，找到 `1` 到 `n` 中字典序第 `k` 小的数字。

```java
class Solution {
    public int findKthNumber(int n, int k) {
        int step = 1;
        int prefix = 1;
        //当步数还没达到k,就一直进行遍历
        while (step < k){
            //tmp为在以prefix下有多少个数
            int tmp = count(prefix, n);
            //如果此前缀下数字加上之前的步数，已经超过k，则该数字一定在此前缀下
            if(tmp + step > k){
                //确认在此前缀，则开始深度搜索，即step++，prefix*=10（往下搜）
                step++;
                prefix *= 10;
            }else {
                //不在此前缀下，则步数需要加上此前缀下全部个数，前缀加一（往右搜）
                step += tmp;
                prefix++;
            }
        }
        return prefix;
    }

    //计算当前prefix下的数字个数
    int count(int prefix,int n){
        int count = 0;
        //当前前缀
        long cur = prefix;
        //下一个前缀
        long next = cur+1;
        //当前缀还没有超过n,则一直往下搜索
        while (cur <= n){
            //很厉害的操作
            //当到达最后一行，可能是满十叉树，也可能是完全十叉树，取最小的。每往下搜索一层则加上对应层的count
            count += Math.min(n+1,next)-cur;
            cur *= 10;
            next*= 10;
        }
        return count;
    }
}

```

