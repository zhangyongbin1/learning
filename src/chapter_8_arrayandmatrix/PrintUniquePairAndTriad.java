package chapter_8_arrayandmatrix;
/**
 * 不重复打印排序数组中相加和为给定值的所有二元组和三元组：给定排序数组arr和整数k,不重复打印arr中所有相加和为k的不降序二元组。
 * 例如：arr = [-8,-4,-3,0,1,2,4,5,8,9], k = 10,打印结果为：
 * 1，9
 * 2，8
 * @author zhangy
 *
 */
public class PrintUniquePairAndTriad {
	/**
	 * 利用排序后的数组的特点，打印二元组的过程可以用一个左指针和一个右指针不断向中间压缩的方式实现，具体过程为：
	 * 1.设置变量left = 0, right = arr.length -1;
	 * 2.比较arr[left] + arr[right]的值sum与k的大小：
	 * 		如果sum == k, 打印"arr[left],arr[right]",则left++,right--;
	 * 		如果sum > k, right--;
	 * 		如果sum < k, left++;
	 * 3.如果left < right, 那么一直重复步骤2，否则过程结束
	 * 那么如果保证不重复打印相同的二元组呢？只需要在打印时增加一个检查即可，检查arr[left]是否与它前一个值arr[left-1]相等，
	 * 如果相等就不打印。 
	 */
	
	public static void printUniquePair(int[] arr, int k){
		if(arr == null || arr.length < 2){
			return ;
		}
		int left = 0;
		int right = arr.length -1;
		while(left < right){
			if(arr[left] + arr[right] < k){//如果sum < k, left++;
				left++;
			}else if(arr[left] + arr[right] > k){//如果sum > k, right--;
				right--;
			}else{//如果sum == k, 打印"arr[left],arr[right]",则left++,right--;
				if(left == 0 || arr[left - 1] != arr[left]){//检查arr[left]是否与它前一个值arr[left-1]相等
					System.out.println(arr[left]+","+arr[right]);
				}
				left++;
				right--;
			}
		}
	}
	/**
	 * 三元组的问题类似与二元组的求解过程：
	 * 例如arr = {-8,-4,-3,0,1,2,4,5,8,9};k =10,
	 * 当三元组的第一个值为-8时，寻找-8后面的子数组中所有相加为18的不重复二元组
	 * 当三元组的第一个为-4时，寻找-4后面的子数组中所有相加为14的不重复二元组
	 * 依此类推
	 * 如果保证不重复打印相同的三元组呢？首先要保证每次寻找过程开始前，选定的三元组中第一个值不重复，其次就是和原问题一样的
	 * 打印检查一样，要保证不重复打印二元组
	 * @param args
	 */
	public static void printUniqueTriad(int[] arr, int k){
		if(arr == null || arr.length < 3){
			return ;
		}
		for(int i = 0; i < arr.length -2; i++){
			if(i == 0 || arr[i] != arr[i-1]){//保证每次寻找过程开始前，选定的三元组中第一个值不重复
				printRest(arr, i, i+1, arr.length -1, k - arr[i]);
			}
		}
		
	}
	
	public static void printRest(int[] arr, int f, int l, int r, int k){//l表示二元组开始的角标位置
		while(l < r){
			if(arr[l] + arr[r] < k){
				l++;
			}else if(arr[l] + arr[r] > k){
				r--;
			}else{
				if(l == f+1 || arr[l -1] != arr[l]){//f表示三元组的开始角标位置
					System.out.println(arr[f] + ","+arr[l]+","+arr[r]);
				}
				l++;
				r--;
			}
		}
	}
	
	public static void main(String[] args){
		int[] arr = {-8,-4,-3,0,1,2,4,5,8,9};
		int k = 10;
		printUniquePair(arr,k);
		printUniqueTriad(arr, 14);
	}

}
