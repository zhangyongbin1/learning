package chapter_8_arrayandmatrix;
/**
 * 在数组中找到出现次数大于N/K的数：给定一个整型数组arr,打印其中出现次数大于一半的数，如果没有这样的数，打印提示信息
 * @author zhangy
 *
 */
public class FindKMajority {
	
	/**
	 * 无论是原问题还是进阶问题，都可以用哈希表记录每个数及其出现的次数，但是额外空间复杂度为O(N),不符合题目要求。
	 * 新方法的核心思路是：一次在数组中删掉K个不同的数，不停地删除，知道剩下的种类不足K就停止删除，那么，如果一个
	 * 数在数组中出现的次数大于N/K，则这个数最后一定会被剩下来。
	 * 对于原问题：出现次数大于一半的数最多只会有一个，还可能不存在这样的数，具体的过程为。一次在数组中删除掉两个
	 * 不同的数，不停第删除，直到剩下的数只有一种，如果一个数出现次数大于一半，这个数最后一定会剩下来。
	 */
	
	public static void printHalfMajor(int[] arr){
		
		int cand = 0;
		int times = 0;
		for(int i =0; i != arr.length; i++){//循环遍历数组，一次在数组中删除两个不同数的代码实现
			if(times == 0){
				cand = arr[i];//最后cand所有代表的数就是数组中出现相同个数的候选数
				times = 1;
			}else if(arr[i] == cand){//相等则加+1记录
				times++;
			}else{//两个数不相等则删除一个记录，这样不停第删除，增加，直到剩下的只有一种，如果一个数出现的次数大于一半，则这个数最后一定会被留下来
				times--;
			}
		}
		/**
		 * 注意上一个for循环留下的的最后的一个数cand不一定满足要求。例如：1，2，3这种数组，最后剩下来的是3，但3不满足要求，所以
		 * 需要下一个for循环进行判断
		 */
		times = 0;
		for(int i = 0; i != arr.length; i++){
			if(arr[i] == cand){
				times++;
			}
		}
		if(times > arr.length / 2){
			System.out.println(cand);
		}else{
			System.out.println("such number do not eixt!");
		}
	}
	public static void main(String[] args){
		int[] arr = {1,2,3,3,2,2,2};
		printHalfMajor(arr);
	}
}
