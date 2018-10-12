package chapter_4_recursionanddp;

import java.util.HashMap;

/**
 * 数组中的最长连续序列：给定无序数组arr,返回其中最长的连续序列的长度。例如：arr=[100,4,200,1,3,2],最长的连续序列为[1,2,3,4],所以返回4
 * @author zhangy
 *
 */
public class LongestConsecutive {
	/**
	 * 1.生成哈希表HasMap<Integer,Integer> map,key代表遍历过的某个数，value代表key这个数所在的连续序列的长度。同时map还可以表示arr中的一个数之前是否出现过
	 * 2.从左到右遍历arr,假设遍历到arr[i]。如果arr[i]之前出现过，直接遍历下一个数。只处理之前没出现过的arr[i]。首先在map中加入记录(arr[i],1),代表目前arr[i]单独
	 * 作为一个连续序列。然后看map中是否含有arr[i]-1，如果有，则说明arr[i] - 1所在的连续序列可以和arr[i]合并，合并后记为A序列。利用map可以得到A序列的长度，记为lenA,
	 * 最小值记为leftA,最大值记为rightA，只在map中更新与leftA和rightA有关的记录，更新成(leftA,lenA)和(rightA,lenA)。接下来看map中是否含有arr[i]+1,如果有，则
	 * 说明arr[i]+1所在的连续序列可以和A合并，合并后记为B序列。利用map可以得到B序列的长度为lenB,最小值记为leftB，最大值记为rightB，只在map中更新与leftB和rightB
	 * 有关的记录，更新成(leftB,lenB)和(rightB,lenB)。
	 * 3.遍历的过程中用全局变量max记录每次合并出的序列的长度最大值，最后返回max
	 */

	public int longestConsecutive(int[] arr){
		if(arr == null || arr.length == 0){
			return 0;
		}
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		int max = 0;//定义一个全局变量max用于记录每一次合并后的最大长度
		for(int i = 0; i < arr.length; i++){
			if(!map.containsKey(arr[i])){//只添加不存在的数
				map.put(arr[i], 1);
				if(map.containsKey(arr[i] - 1)){
					max = Math.max(max, updateMap(map, arr[i] - 1, arr[i]));
				}
				if(map.containsKey(arr[i] + 1)){
					max = Math.max(max, updateMap(map, arr[i],arr[i] + 1));
				}
			}
		}
		return max;//最后返回这个最大长度即可
	}
	public int updateMap(HashMap<Integer,Integer> map, int less, int more){
		int left = less - map.get(less) + 1;//合并后的序列的左边最小值
		int right = more + map.get(more) - 1;//合并后的序列的右边最大值
		int len = right - left + 1;
		map.put(left, len);
		map.put(right, len);
		return len;
	}
	
	public static void main(String[] args){
		LongestConsecutive lc = new LongestConsecutive();
		int[] arr = {100,4,200,1,3,2};
		System.out.println(lc.longestConsecutive(arr));
	}
}
