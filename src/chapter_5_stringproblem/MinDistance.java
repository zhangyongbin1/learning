package chapter_5_stringproblem;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * 数组中两个字符串的最小距离：给定一个字符串数组strs,再给定连个字符串str1和str2，返回在strs中str1与str2的最小距离，
 * 如果str1或str2为null,或不在strs中，返回-1。
 * 举例：strs=["1", "3", "3", "3", "2", "3", "1"], str1="1", str2="2",返回2。
 * 		strs=["CD"], str1="CD", str2="AB",返回-1
 * @author zhangy
 *
 */
public class MinDistance {
	/**
	 * 从左到右遍历strs,用变量last1记录最近一次出现的str1的位置，用变量last2记录最近一次出现的str2的位置。如果遍历到str1,
	 * 那么i-last2的值就是当前的str1和左边离它最近的str2之间的距离。如果遍历到str2,那么i-last1的值就是当前的str2和左边
	 * 离它最近的str1之间的距离。用变量min记录这些距离的最小值即可。
	 */
	public int minDistance(String[] strs, String str1, String str2){
		if(str1 == null || str2 == null){
			return -1;
		}
		if(str1.equals(str2)){
			return 0;
		}
		int min = Integer.MAX_VALUE;//将min赋值便于最后return时候的判断
		int last1 = -1;
		int last2 = -1;
		for(int i = 0; i < strs.length; i++){//从左到右遍历strs数组
			if(strs[i].equals(str1)){//这种写代码的技巧挺重要的
				min = Math.min(min, last2 == -1 ? min : i - last2);//在没有遇到str2之前，这里的min都是Integer.MAX_VALUE
				last1 = i;//更新最近一次str1字符串在字符串数组strs中的角标位置
			}
			if(strs[i].equals(str2)){
				min = Math.min(min, last1 == -1 ? min : i - last1);//在没有遇到str1之前，这里的min都是Integer.MAX_VALUE 
				last2 = i;//更新最近一次str2字符串在字符串数组strs中的角标位置
			}
		}
		//这个时候已经遍历完了strs数组，这时的min就应该是最小的
		return min == Integer.MAX_VALUE ? -1 : min;
	}
	/**
	 * 进阶题目：如果查询发生的次数有很多，如果把每次查询的时间复杂度降为O(1)?
	 * @param args
	 */
	public static class Record{
		//建立一个全局变量用于统计每个字符str与其他字符之间的最小距离这样可以方便一次查询
		private HashMap<String,HashMap<String,Integer>> record;
		
		public Record(String[] strs){
			record = new HashMap<String,HashMap<String,Integer>>();
			//indexMap 集合用于记录当前字符串数组strs中所有字符及其角标键值对
			HashMap<String,Integer> indexMap = new HashMap<String,Integer>();
			for(int i = 0; i < strs.length; i++){//遍历字符串数组
				String curStr = strs[i];
				update(indexMap, curStr, i);//先跟下record记录表
				indexMap.put(curStr, i);//然后更新indexMap集合
			}
		}
		//更Record集合
		public void update(HashMap<String, Integer> indexMap, String curStr, int i) {
			if(!record.containsKey(curStr)){//如果record中不包含当前str对应的键值对
				record.put(curStr, new HashMap<String,Integer>());//那么就直接当前str放入到record中
			}
			//如果存在，那么就把str对应的value取出来
			HashMap<String,Integer> strMap = record.get(curStr);
			for(Entry<String,Integer> lastEntry : indexMap.entrySet()){//遍历indexMap集合，寻找之前是否出现过curStr
				//获取键key
				String key = lastEntry.getKey();
				//获取值value
				int index = lastEntry.getValue();
				
				if(!key.equals(curStr)){//一下用于更新curStr与其它字符之间的最小距离
					HashMap<String,Integer> lastMap = record.get(key);//外哈希表与内哈希表之间的key是互相关联的
					int curMin = i - index;
					if(strMap.containsKey(key)){//如果strMap中本来就含有key,
						int preMin = strMap.get(key);
						if(curMin < preMin){//那么就更新最小值
							strMap.put(key, curMin);//strapMap中是更新key对应的最小值
							lastMap.put(curStr, curMin);//lastMap中是更新curStr对应的最小值
						}
					}else{//否则就直接添加进strMap集合和lastMap集合中
						strMap.put(key, curMin);
						lastMap.put(curStr, curMin);
					}
				}
			}
			
		}
		public int minDistance2(String str1, String str2){
			if(str1 == null || str2 == null){
				return -1;
			}
			if(str1.equals(str2)){
				return 0;
			}
			//这样就能保证只查询一次就把结果算出来了
			if(record.containsKey(str1) && record.get(str1).containsKey(str2)){
				return record.get(str1).get(str2);
			}
			
			return -1;
		}			
	}
	
	public static void main(String[] args){
		MinDistance md = new MinDistance();
		String[] strs = {"1","1","3","3","2","1","1","3","3","2","1","1","3","3","2","1","1","3","3","2","1","1","3","3","2"
				,"1","1","3","3","2","1","1","3","3","2","1","1","3","3","2","1","1","3","3","2","1","1","3","3","2"};
		String str1 = "1";
		String str2 = "2";
		long start1 = System.currentTimeMillis();
		System.out.println(md.minDistance(strs, str1, str2));
		long end1 = System.currentTimeMillis();
		System.out.println("minDistance1's cost time: "+ (end1-start1));
		
		long start2 = System.currentTimeMillis();
		Record record = new Record(strs);
		int min = record.minDistance2(str1, str2);
		System.out.println(min);
		long end2 = System.currentTimeMillis();
		System.out.println("minDistance2's cost time: "+ (end2-start2));
	}

}
