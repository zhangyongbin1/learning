package chapter_8_arrayandmatrix;
/**
 * 需要排序的最短子数组长度：给定一个无序数组arr,求出需要排序的最短子数组长度。
 * 例如： arr = [1,5,3,4,2,6,7]返回4，因为只有[5,3,4,2]需要排序
 * @author zhangy
 *
 */
public class MinLengthForSort {
	/**
	 * 1.初始化变量noMinIndex = -1, 从右向左遍历，遍历的过程中记录右侧出现过的数的最小值记为min.假设当前数为arr[i],
	 * 如果arr[i] > min,说明如果要整体有序，min值必然会挪到arr[i]的左边。用noMinIndex记录最左边出现这种情况的位置。
	 * 如果遍历完成后，noMinIndex依然等于-1，说明从右到左始终不升序，原数组本来就有序，直接返回0，即完全不需排序
	 * 2.接下来从左向右遍历，遍历的过程中记录左侧出现过的数的最大值，记为max.假设当前数为arr[i],如果arr[i] < max,说明
	 * 如果排序，max值必然会挪到arr[i]的右边，用变量noMaxIndex记录最右边出现这样情况的位置。
	 * 3.遍历完成后，arr[noMinIndex...noMaxIndex]是真正需要排序的部分，返回它的长度即可
	 */
	
	public static  int getMinLengthForSort(int[] arr){
		if(arr == null || arr.length < 2){
			return 0;
		}
		int noMinIndex = -1;
		int min = arr[arr.length - 1];
		for(int i = arr.length - 2; i > -1; i--){//从右往左遍历，寻找最小值
			if(arr[i] > min){//在寻找最小值的过程中
				noMinIndex = i;//记录出现比min大的值arr[i]，如果跑好序的话，arr[i]必定出现在min的右边，而现在在min的左边
			}else{
				min = Math.min(min, arr[i]);//记录最下值
			}
		}
		if(noMinIndex == -1){//说明从右到左都没有出现arr[i] > min的情况，说明原数组已经是有序的
			return 0;//直接返回0即可
		}
		int noMaxIndex = -1;
		int max = arr[0];
		for(int i = 1; i < arr.length; i++){//从左到右遍历数组
			if(arr[i] < max){//寻找在max右边比max小的数
				noMaxIndex = i;//记录其角标位置
			}else{
				max = Math.max(max, arr[i]);//选二者的较大值
			}
		}
		return noMaxIndex - noMinIndex + 1;//返回两个角标之间的差值即是最短需要排序的长度
	}
	public static void main(String[] args){
		int[] arr = {1,5,3,4,2,6,7};
		int nums = getMinLengthForSort(arr);
		System.out.println(nums);
	}
}
