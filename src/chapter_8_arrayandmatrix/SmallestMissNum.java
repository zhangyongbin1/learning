package chapter_8_arrayandmatrix;
/**
 * 数组中未出现的最小正整数：给定一个无序整数数组arr,找到数组中未出现的最小正整数
 * 举例：arr=[-1,2,3,4].返回1
 * 		arr = [1,2,3,4]返回5
 * @author zhangy
 *
 */
public class SmallestMissNum {
	
	public static int missNum(int[] arr) {
		int l = 0;//表示遍历到目前为止，整数arr已经包含的正整数范围是[1,l]
		int r = arr.length;//表示遍历到目前为止，在后续出现最优状况的情况下，arr可能包含的任何正整数范围是[1,r]
		while (l < r) {
			if (arr[l] == l + 1) {//说明当前arr已经包含的整数范围可以到底l + 1
				l++;
			} else if (arr[l] <= l || arr[l] > r || arr[arr[l] - 1] == arr[l]) {
				arr[l] = arr[--r];//把arr最后位置上的(arr[r-1])放在位置l上，下一步检查这个数，然后令r--
			} else {
				swap(arr, l, arr[l] - 1);//说明发现了[l+1,r]范围上的数，并且此时并未发现重复，那么arr[l]应该放在arr[l]-1的位置上
			}
		}
		return l + 1;//最终l位置和r位置会碰到一起(l == r),arr已经包含的正整数范围是[1,l],所以返回l+1即可
	}

	public static void swap(int[] arr, int index1, int index2) {
		int tmp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = tmp;
	}

	public static void main(String[] args) {
		int[] arr = { -1, 0, 3, 1, 7, 5 };
		System.out.println(missNum(arr));

	}

}
