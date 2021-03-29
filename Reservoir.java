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