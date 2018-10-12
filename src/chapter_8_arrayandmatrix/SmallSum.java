package chapter_8_arrayandmatrix;
/**
 * 计算数组的小和：数组小和的定义如下：例如，数组s = [1,3,5,2,4,6],在s[0]的左边小于或等于s[0]的数的和为0，
 * 在s[1]的左边小于或等于s[1]的数的和为1，在s[2]的左边小于或等于s[2]的数的和为1+3=4，在s[3]的左边小于或
 * 等于s[3]的数的和为1，在s[4]的左边小于或等于s[4]的数的和为1+3+2=6，在s[5]的左边小于或等于s[5]的数的和为
 * 1+3+5+2+4 = 15，所以s的小和为0+1+4+1+6+15=27.给定一个数组s,实现函数返回s的小和
 * @author zhangy
 *
 */
public class SmallSum {
	
	/**
	 * 使用归并排序过程，在合并左、右两个子数组过程中如果出现l[i] <= r[j],那么就产生小和，假设从r[j]往右一直到
	 * r[]结束，元素的个数为m,那么产生的小和为l[i]*m
	 */
	public static int getSmallSum(int[] arr){
		if(arr == null || arr.length == 0){
			return 0;
		}
		return func(arr,0,arr.length -1);
	}
	
	public static int func(int[] arr, int l, int r){//归并排序
		if(l == r){
			return 0;
		}
		int mid = (l + r)/2;
		return func(arr,l,mid)+func(arr,mid+1,r)+merge(arr,l,mid,r);
	}
	
	public static int merge(int[] s, int left, int mid, int right){//归并排序的合并过程
		int[] helpArr = new int[right - left + 1];
		int i = left;
		int j = mid + 1;
		int smallSum = 0;
		int h = 0;
		while(i <= mid && j <= right){
			if(s[i] <= s[j]){
				smallSum += s[i] * (right - j + 1);
				helpArr[h++] = s[i++];
			}else{
				helpArr[h++] = s[j++];
			}
		}
		for( ;(j < right +1 || i < mid + 1); i++,j++){
			helpArr[h++] = i > mid ? s[j] : s[i];
		}
		for(int k = 0; k != helpArr.length; k++){//将有序的helpArr中的值复制到原数组s中
			s[left++] = helpArr[k];
		}
		return smallSum;
	}
	
	public static void main(String[] args){
		int[] arr = {1,3,5,2,4,6};
		System.out.println(getSmallSum(arr));
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i]);
		}
	}

}
