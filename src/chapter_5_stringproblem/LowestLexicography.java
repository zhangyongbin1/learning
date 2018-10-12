package chapter_5_stringproblem;

import java.util.Arrays;
import java.util.Comparator;

import chapter_5_stringproblem.LowestLexicography.MyComparator;

/**
 * 拼接所有字符串产生字典顺序最小的大写字符串：给定一个字符串类型的数字strs,请找到一种拼接顺序，使得将所有的字符串拼接起来
 * 组成的大写字符串是所有可能性中字典顺序最小的，并返回这个大写字符串
 * 举例：strs=["abc","de"],可以拼成"abcde",也可以拼成"deabc"，但前者的字典顺序更小，所以返回"abcde"
 * strs=["b","ba"]，可以拼成"bba"，也可以拼成"bab"，但后者的字典顺序更小，所有返回"bab"
 * @author zhangy
 *
 */
public class LowestLexicography {
	public static class MyComparator implements Comparator<String> {
		@Override
		public int compare(String a, String b) {
			return (a + b).compareTo(b + a);
		}
	}

	public static String lowestString(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		// 根据新的比较方式排序
		Arrays.sort(strs, new MyComparator());
		String res = "";
		for (int i = 0; i < strs.length; i++) {
			res += strs[i];
		}
		return res;
	}

	public static void main(String[] args) {
		String[] strs1 = { "jibw", "ji", "jp", "bw", "jibw" };
		System.out.println(lowestString(strs1));

		String[] strs2 = { "ba", "b" };
		System.out.println(lowestString(strs2));

	}
}
