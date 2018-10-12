package chapter_5_stringproblem;
/**
 * 找到字符串的最长无重复字符子串：给定一个字符串str，返回str的最长无重复字符子串的长度
 * 举例：str="abcd",返回4
 * 		str="aabcb", 最长无重复字符子串为"abc",返回3
 * @author zhangy
 *
 */
public class LongestNoRepeatSubstring {
	/**
	 * 如果str长度为N,字符编码范围是M,本题可以做到的时间复杂度为O(N),额外空间复杂度为O(M).下面介绍这种方法的具体实现：
	 * 1.在遍历str之前，先申请几个变量。哈希表map,key表示某个字符，value为这个字符最近一次出现的位置。整型变量pre.如果
	 * 当前遍历到字符str[i],pre表示在必须以str[i-1]字符结尾的情况下，最长无重复字符子串开始位置的前一个位置，初始时，
	 * pre = -1.整型变量len,记录以每一个字符结尾的情况下，最长无重复字符子串的长度的最大值，初始时，len = 0.从左到右依次
	 * 遍历str,假设现在遍历到str[i]，接下来求在必须以str[i]结尾的情况下，最长无重复字符子串的长度。
	 * 2.map(str[i])的值表示之前的遍历中最近一次出现的str[i]字符的位置，假设在a位置。想要求以str[i]结尾的最长无重复子串，
	 * a位置必然不能包含进来，因为str[a]等于str[i]。
	 * 3.根据pre的定义，pre + 1表示在必须以str[i-1]字符结尾的情况下，最长无重复字符子串的开始位置，也就是说，以str[i-1]结尾
	 * 的最长无重复子串是向左扩到pre位置停止的。
	 * 4.如果pre位置在a位置的左边，因为str[a]不能包含进来，而str[a+1...i-1]上都是不重复的，所有以str[i]结尾的最长无重复
	 * 字符子串就是str[a+1...i].如果pre位置在a位置的右边，以str[i-1]结尾的最长无重复字符子串是向左扩到pre位置停止的。
	 * 所以以str[i]结尾的最长无重复子串向左扩到pre位置也必然会停止，而且str[pre + 1...i-1]这一段上肯定不包含有str[i],
	 * 所以以str[i]结尾的最长无重复字符子串就是str[pre+1...i].
	 * 5.计算完长度之后，pre位置和a位置哪一个在右边，就作为新的pre的值。然后去计算下一个位置的字符，整个过程中求得左右长度
	 * 的最大值用len记录下来返回即可
	 */
	
	public int maxUnique(String str){
		if(str == null || str.length() == 0 ){
			return 0;
		}
		char[] chas = str.toCharArray();
		
		int pre = -1;
		int len = 0;
		int cur = 0;
		int[] map = new int[256];
		for(int i = 0; i < map.length; i++){
			map[i] = -1;
		}
		for(int i = 0; i != chas.length; i++){
			pre = Math.max(pre, map[chas[i]]);//如果pre在map[chas[i]]的左边，那么pre赋值为map[chas[i]],如果pre在map[chas[i]]右边，那么直接使用pre
			cur = i - pre;//当前最长无重复字符子串的长度
			len = Math.max(len, cur);//记录最长的无重复字符子串的长度
			map[chas[i]] = i;//更新map集合中key为chas[i]对应的value值 = i
		}
		return len;
	}
	
	public static int maxUnique2(String str) {
		if (str == null || str.equals("")) {
			return 0;
		}
		char[] chas = str.toCharArray();
		int[] map = new int[256];
		for (int i = 0; i < 256; i++) {
			map[i] = -1;
		}
		int len = 0;
		int pre = -1;
		int cur = 0;
		for (int i = 0; i != chas.length; i++) {
			pre = Math.max(pre, map[chas[i]]);
			cur = i - pre;
			len = Math.max(len, cur);
			map[chas[i]] = i;
		}
		return len;
	}
	
	public static void main(String[] args){
		LongestNoRepeatSubstring lnrss = new LongestNoRepeatSubstring();
		String str = "aabcbd";
		System.out.println(lnrss.maxUnique(str));
		System.out.println(maxUnique2(str));
	}
}
