package chapter_5_stringproblem;
/**
 * 最小包含子串的长度：给定字符串str1和str2，求str1的子串中含有str2所有字符的最小子串长度。
 * 举例：str1 = "abcde" , str2 = "ac".因为"abc"包含str2的所有字符，并且在满足这一条件的str1的所有子串中，"abc"是最短的，返回3
 * str1 = "12345",str2 = "344".最小包含子串不存在。返回0
 * 应该是不关注顺序的，只要包含字符就可以
 * @author zhangy
 *
 */
public class MinWindowLength {
	
	public int minLength(String str1, String str2){
		if(str1 == null || str2 == null || str2.length() > str1.length()){
			return 0;
		}
		char[] chas1 = str1.toCharArray();
		char[] chas2 = str2.toCharArray();
		int match = chas2.length;
		int minLen = Integer.MAX_VALUE;
		int[] map = new int[256];
		for(int i = 0; i < chas2.length; i++){
			map[chas2[i]]++;
		}
		int left = 0;
		int right = 0;
		while(right != chas1.length){//先right指针向右移动
			map[chas1[right]]--;
			if(map[chas1[right]] >= 0){//说明当前字符chas1[right]匹配上str2中的字符，所以match -1,如果当前字符在str2中不存在，那么map[chas1[right]]必定小于0
				match--;
			}
			//这时已经确定left到right位置str1包含str2的right位置，这时需要移动left位置来确定最小str2字符的开始位置left
			if(match == 0){//再左指针向右移动
				while(map[chas1[left]] < 0){//说明map[chas1[left]]在str2字符串中没有
					map[chas1[left++]]++;//说明即便拿回chas1[left]也不会欠str2,所有拿回来。left指向下一个位置
				}
				minLen = Math.min(minLen, right - left + 1);//这时str1[left...rigth]也包含str2
				//继续寻找更靠左的位置
				match++;
				map[chas1[left++]]++;
			}
			right++;
		}
		return minLen == Integer.MAX_VALUE ? 0 : minLen;
	}
	public static void main(String[] args) {
		MinWindowLength mwl = new MinWindowLength();
		String str1 = "adabbca";
		String str2 = "acb";
		System.out.println(mwl.minLength(str1, str2));

	}
}
