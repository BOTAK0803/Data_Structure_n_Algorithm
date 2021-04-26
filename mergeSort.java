/**
利用归并排序求逆序对的数目1
*/
class Solution{
	static int[] tmp;
	static int[] nums;
	public static void main(String[] args) {
		nums = new int[]{7,3,2,6,0,1,5,4};
		tmp = new int[nums.length];
		int ans = mergeSort(0,nums.length - 1);
		System.out.println(ans);
		for(int i = 0;i<nums.length;i++) System.out.print(nums[i] + " ");
		System.out.println();

	}

	public static int mergeSort(int l,int r){
		if(l>=r) return 0;
		int m = l + (r-l)/2;
		int res = mergeSort(l,m) + mergeSort(m+1,r);
		int i = l;
		int j = m+1;
		for(int k = l;k<=r;k++){
			tmp[k] = nums[k];
		}
		for(int k = l;k<=r;k++){
			if(i == m + 1){
				nums[k] = tmp[j];
				j++;
			}
			else if(j == r + 1 || tmp[i] <= tmp[j]){
				nums[k] = tmp[i];
				i++;
			}
			else{
				res+=m-i+1;
				nums[k] = tmp[j];
				j++;
			}
		}
		for(int k = l;k<=r;k++) System.out.print(tmp[k] + " ");
		System.out.println();
		return res;

	}
}