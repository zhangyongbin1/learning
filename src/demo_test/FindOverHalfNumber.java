package demo_test;

import org.junit.Test;

/**
 * 查找一个数组中出现次数超过一半的的数
 * @author zhangy
 *
 */
public class FindOverHalfNumber {
	
	public int function(int[] arr, int lenght){
		if(arr == null || arr.length < 1){
			return 0;
		}
		int result = arr[0];
		int times = 1;
		for(int i = 1; i < arr.length; i++){
			if(times == 0){
				result = arr[i];
				times = 1;
			}else{
				if(result == arr[i]){
					times++;
				}else{
					times--;
				}
			}
		}
		//如果是刚好一半的话，则经过上面处理后result有可能就是该值，或者result的下一个元素有可能是该值
		//如果数组没有超过一半的数，那么就会输出最后一个元素，所以还需要对result进行进一步的判断
		times = 0;
		for(int i = 0; i < arr.length; i++){
			if(result == arr[i]){
				times++;
			}
		}

		return times > arr.length/2 ? result : 0;
	}
	
	public void quanpailieFunction(int[] arr, int begin, int end){
		if(begin == end){//全排列递归的出口
			for(int i = 0; i < arr.length; i++){
				System.out.print(arr[i]);
			}
			System.out.println();
		}else{
			for(int j = begin; j <= end; j++){
				swap(arr,begin,j);
				quanpailieFunction(arr,begin+1,end);
				swap(arr,begin,j);
			}
		}
	}
	public void quanpailieFunction2(int[] arr, int start){
		if(start == arr.length){
			for(int i = 0; i < arr.length; i++){
				System.out.print(arr[i]);
			}
			System.out.println();
		}else{
			for(int i = start; i < arr.length; i++){
				swap(arr,start,i);
				quanpailieFunction2(arr,start+1);
				swap(arr,start, i);
			}
		}
	}
	/**
	 * 三角形的最小路径和问题
	 * @param arr
	 * @param i
	 * @param j
	 */
	public int sumOftriangle(int[][] triangle){
		for(int i = triangle.length - 2; i>=0; i--){
			for(int j = 0; j < triangle[i].length; j++){
				triangle[i][j] += Math.min(triangle[i+1][j], triangle[i+1][j+1]);
			}
		}
		return triangle[0][0];
	}
	public void swap(int[] arr, int i, int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	@Test
	public void test(){
		int[] arr = {1,4,2,2,2,2,8,3};
		int[] arr1 = {1,2,3};
		int[][] triangle={{2},{3,4},{5,6,7},{4,1,8,3}};
//		System.out.println(function(arr,arr.length));
//		quanpailieFunction(arr1,0,arr1.length-1);
		quanpailieFunction2(arr1,0);
		int sum = sumOftriangle(triangle);
		System.out.println(sum);
	}

}
