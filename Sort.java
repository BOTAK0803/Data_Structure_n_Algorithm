// 各种排序算法
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.List;
class Test{
	public static void main(String[] args) {
		int[] arr = new int[]{6,2,4,1,7,3,5};
		print(arr);
		// insertSort(arr);
		// insertSort2(arr);
		// inHalfInsertSort(arr);
		// shellSort(arr);
		// bubbleSort(arr);
		// quickSort(arr,0,arr.length-1);
		// print(arr);
		// selectSort(arr);
		// heapSort(arr);
		// mergeSort(arr,0,arr.length-1);
		int[] arr2 = {21,56,88,195,354,1,35,12,6,7};
		baseSort(arr2,3);

	}
	// 打印数组
	public static void print(int[] arr){
		for(int i:arr) System.out.print(i+" ");
		System.out.println();
	}
	// 直接插入排序，没有哨兵
	public static void insertSort(int arr[]){
		// 插入排序的思想是，假定前面i个元素已经有序，然后针对于第i+1个元素，寻找第i+1个元素应该在的位置，插入，移动即可
		int len = arr.length;
		int i;
		int j;
		// 遍历第1个到最后一个元素，假定第0个元素已经有序
		for(i = 1;i<len;i++){
			// 当前元素小于其前驱
			if(arr[i] < arr[i-1]){
				// 将当前元素的值取出，不然后续移动元素的时候会覆盖该元素
				int tmp = arr[i];
				// 找到第i个元素应该在的位置,从后往前找到第i个元素应该在的位置
				for(j = i-1;j>=0&&arr[j]>tmp;j--){
					// 将比tmp大的元素向后移动
					arr[j+1] = arr[j];
				}
				// j+1指向的位置就是tmp应该在的位置
				arr[j+1] = tmp;
			}
		}
		print(arr);
	}
	// 直接插入排序，有哨兵的插入排序
	/**
	哨兵
	*/
	public static void insertSort2(int[] arr){
		int len = arr.length;
		int i;
		int j;
		for(i = 2;i<len;i++){
			if(arr[i] < arr[i-1]){
				// arr[0]是哨兵元素
				arr[0] = arr[i];
				// 引用了哨兵元素之后，这个地方就不用去判断越界的问题了
				for(j = i-1;arr[j]>arr[0];j--) arr[j+1] = arr[j];
				arr[j+1] = arr[0];
			}
		}
		print(arr);
	}
	// 折半插入排序
	/**
	直接插入排序中（不管有没有哨兵的存在，其实是进行了两项工作）
		- 1 从前面的子数组中查找出待插入元素应该被插入的位置
		- 2 给插入元素腾出空间
	在该算法中，总是一边比较，一边插入元素，下面将比较操作与移动操作进行分离，即现折半查找出元素的待插入位置，然后统一的移动待插入位置之后的所有元素，
	- **折半插入排序仅仅减少了元素比较的次数**约为$O(n * \log_2n)$
	- 时间复杂度仍为$O(n^2)$
	- 是一种稳定性的排序方法

	*/
	public static void inHalfInsertSort(int[] arr){
		int i;
		int j;
		int low;
		int high;
		int mid;
		int len = arr.length;
		// i从1开始，同直接排序的想法一样，假设第0个元素是有序的
		for(i = 1;i<len;i++){
			//
			int tmp = arr[i];
			// 设置折半查找的范围
			low = 0;
			high = i-1;
			// 折半查找,查找出第i个元素应该在的位置
			while(low<=high){
				// 取中间点
				mid = (low+high)/2;
				// 查找左半子数组
				if(arr[mid] > tmp) high = mid-1;
				// 查找右半子数组
				else low = mid+1;
			}
			for(j = i-1;j>=high+1;j--) arr[j+1] = arr[j];
			arr[high+1] = tmp;
		}
		print(arr);

	}
	// 希尔排序
	/**
	直接插入排序适用于数组基本有序的数组的以及数据量不大的排序
	希尔排序又称之为缩小增量排序
	希尔排序的思想：
		- 先将要排序的列表分割成为arr[i,i+d,i+2d,i+kd]的特殊子表，分别进行插入排序
		- 当整个数组表呈现基本有序的时候，再对所有元素进行一次直接插入排序
	算法过程
		- 先选取一个小于数组长度的步长d1,将数组分成d1组，所有距离为d1的倍数的记录放在同一组中，在各个组进行直接插入排序
		- 然后取第二个步长d2 < d1 重复上述过程，直到dn = 1
		- 对所有的元素进行直接插入排序

	至今为止，没有一个最好的增量序列，一般情况下d1 = n/2,d2 = d1/2,并且最后一个增量为1
	*/
	public static void shellSort(int[] arr){
		int len = arr.length;
		int i;
		int j;
		// 缩小增量的选取，也就是步数的选择
		for(int step = len/2;step>=1;step=step/2){
			// 对每一组的元素进行直接插入排序
			for(i=step;i<len;i++){
				if(arr[i] < arr[i-step]){
					int tmp = arr[i];
					for(j=i-step;j>=0&&tmp < arr[j];j-=step) arr[j+step] = arr[j];
					arr[j+step] = tmp;
				}
			}
			print(arr);
		}
		print(arr);
	}
	// 交换排序
	// 冒泡排序
	public static void bubbleSort(int[] arr){
		// 冒泡排序类似于金鱼吐泡泡，比较两个相邻的元素，将大的元素放在后面
		int len = arr.length;
		// 进行len-1 次就结束了循环
		for(int i = len;i>=0;i--){
			// 设计一个标记位，如果没有元素进行移动就说明到达了最终的排序好的位置，就直接break
			boolean flag = false;
			for(int j = 0;j<i-1;j++){
				if(arr[j+1] < arr[j]){
					// 交换
					int tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
					flag = true;
				}

			}
			// 本趟遍历没有发生交换，说明表已经有序
			if(!flag){
				print(arr);
				return;
			}
		}	
		print(arr);
	}
	// 快速排序
	public static void quickSort(int[] arr,int low,int high){
		int left = low;
		int right = high;
		int pivot = arr[left];
		if(low<high){
			while(left<right){
				while(left<right && arr[right] >= pivot) right--;
				if(left<right) arr[left] = arr[right];
				while(left<right && arr[left] <= pivot) left++;
				if(left<right) arr[right] = arr[left];
			}
			arr[left] = pivot;
			quickSort(arr,low,left-1);
			quickSort(arr,left+1,high);

		}
		// print(arr);
	}
	// 选择排序
	// 简单选择排序
	public static void selectSort(int[] arr){
		// 选择排序
		int len = arr.length;
		for(int i = len-1;i>=0;i--){
			int index = i;
			for(int j = 0;j<=i;j++){
				if(arr[j] > arr[index]) index = j; 
			}
			int tmp = arr[i];
			arr[i] = arr[index];
			arr[index] = tmp;
		}
		print(arr);
	}
	// 堆排序
	public static void heapSort(int[] arr){
		// heap，使用java中的PriorityQueue默认是小顶堆，若要使用大顶堆，需要PriorityQueue<>(o1,o2->(o2-o1))
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		for(Integer i:arr) queue.offer(i);
		while(!queue.isEmpty()) System.out.print(queue.poll() + " ");

	}
	// 归并排序
	public static void mergeSort(int[] arr,int low,int high){
		int mid = (low+high)/2;
		if(low<high){
			mergeSort(arr,low,mid);
			mergeSort(arr,mid+1,high);
			merge(arr,low,mid,high);

		}
		print(arr);

	}
	public static void merge(int[] arr,int low,int mid,int high){
		int[] temp = new int[high-low+1];
		int i = low;
		int j = mid+1;
		int k = 0;
		// 先把较小的数先移动到新的数组中
		while(i<=mid&&j<=high){
			if(arr[i] < arr[j]) temp[k++] = arr[i++];
			else temp[k++] = arr[j++];
		}
		// 把左边剩余的数移入数组
		while(i<=mid) temp[k++] = arr[i++];
		// 把右边剩余的数移入数组
		while(j<=high) temp[k++] = arr[j++];
		// 覆盖原来的数组
		for(int index = 0;index<temp.length;index++) arr[low + index] = temp[index];

	}

	// 基数排序(桶排序)
	// max表示数组中最大的位数有几位
	public static void baseSort(int[] arr,int max){
		// count 用来计数
		int[] count = new int[arr.length];
		// bucket用来当作桶,桶用来放数据，取数据
		int[] bucket = new int[arr.length];
		// k表示第几位，1代表个位，2代表十位，3代表百位
		for(int k = 1;k<=max;k++){
			// 将count置空，防止上次循环的数据影响
			for(int i = 0;i<arr.length;i++) count[i] = 0;
			// 分别统计第k位是0，1，2，3，4，5，6，7，8，9的数量
			for(int i = 0;i<arr.length;i++) count[getFigure(arr[i],k)]++;
			// 利用count[i]来确定放置数据的位置
			for(int i = 1;i<arr.length;i++) count[i] = count[i]+count[i-1];
			for(int i = arr.length-1;i>=0;i--){
				int j = getFigure(arr[i],k);
				bucket[count[j] - 1] = arr[i];
				count[j]--;
			}
			for(int i = 0,j = 0;i<arr.length;i++,j++) arr[i] = bucket[j];
		}
		print(arr);

	}
	// 次函数返回整型数i的第k位是什么
	public static int getFigure(int i,int k){
		int[] a = {1,10,100};
		return (i/a[k-1])%10;
	}
}