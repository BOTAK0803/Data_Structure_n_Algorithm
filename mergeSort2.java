/**
利用归并排序求逆序对的数目1
*/
class Solution{
	static int count = 0;
	public static void main(String[] args) {
		int[] nums = new int[]{7,3,2,6,0,1,5,4};
		merge(nums,0,nums.length - 1);
		System.out.println(count);
	}
	public static void merge(int[] nums,int left,int right){
		int mid = left + (right - left)/2;
		if(left < right){
			merge(nums,left,mid);
			merge(nums,mid+1,right);
			mergeSort(nums,left,mid,right);
		}
	}
	public static void mergeSort(int[] nums,int left,int mid,int right){
		int[] tmp = new int[right - left + 1];
		int index = 0;
		int t1 = left;
		int t2 = mid+1;
		while(t1<=mid && t2<=right){
			if(nums[t1] <= nums[t2]){
				tmp[index] = nums[t1];
				index++;
				t1++;
			}else{
				tmp[index] = nums[t2];
				index++;
				t2++;
				count += mid-t1+1;
			}
		}
		while(t1<=mid){
			tmp[index] = nums[t1];
			t1++;
			index++;
		}
		while(t2<=right){
			tmp[index] = nums[t2];
			t2++;
			index++;
		}
		for(int i=0;i<tmp.length;i++){
			nums[left+i] = tmp[i];
		}
	}
}