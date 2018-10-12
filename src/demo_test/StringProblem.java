package demo_test;

import org.junit.Test;

public class StringProblem {

	/**
	 * 反转字符串
	 */

	public char[] rotateStr(String str) {
		if (str == null) {
			return null;
		}
		char[] chas = str.toCharArray();
		reverse(chas, 0, chas.length - 1);
		int l = -1;
		int r = -1;
		for (int i = 0; i < chas.length; i++) {
			if (chas[i] != ' ') {
				l = i == 0 || chas[i - 1] == ' ' ? i : l;
				r = i == chas.length - 1 || chas[i + 1] == ' ' ? i : r;
			}
			if (l != -1 && r != -1) {
				reverse(chas, l, r);
				l = -1;
				r = -1;
			}
		}
		return chas;
	}

	public void reverse(char[] chas, int start, int end) {
		if (start > end || start < 0 || end > chas.length) {
			return;
		}
		while (start <= end) {
			swap(chas, start, end);
			start++;
			end--;
		}
	}

	/**
	 * KMP算法判断小串是否是大串的子串
	 * 
	 * @param chas
	 * @param start
	 * @param end
	 */
	public boolean isSubStr(String str1, String str2) {
		if (str1.length() < str2.length() || str1 == null) {
			return false;
		}
		char[] ss = str1.toCharArray();
		char[] ms = str2.toCharArray();
		int[] next = getNext(ms);
		int si = 0;
		int mi = 0;
		while (si < ss.length && mi < ms.length) {
			if (ss[si] == ms[mi]) {
				si++;
				mi++;
			} else if (next[mi] == -1) {
				si++;
			} else {
				mi = next[mi];
			}
		}
		return mi == ms.length - 1 ? true : false;
	}

	public int[] getNext(char[] ms) {
		if (ms == null) {
			return new int[] { -1 };
		}
		// next数组表示当前字符之前的字符串的前后缀子串的最大公共子串长度
		int[] next = new int[ms.length];
		next[0] = -1;
		next[1] = 0;
		int pos = 2;
		int cn = 0;
		while (pos < ms.length) {
			if (ms[pos - 1] == ms[cn]) {
				next[pos++] = ++cn;
			} else if (cn >= 0) {
				cn = next[cn];
			} else {
				next[pos++] = 0;
			}
		}
		return next;
	}

	/**
	 * 将字符串转换成数字
	 * 
	 * @param chas
	 * @param start
	 * @param end
	 */
	public int convertStringToNumber(String str) {
		if (str == null) {
			return 0;
		}
		char[] chas = str.toCharArray();
		if (!isValid(chas)) {
			return 0;
		}
		boolean positive = chas[0] == '-' ? false : true;
		int miner = Integer.MIN_VALUE / 10;
		int mine = Integer.MIN_VALUE % 10;
		int cur = 0;
		int res = 0;
		for (int i = positive ? 0  : 1; i < chas.length; i++) {
			cur = '0' - chas[i];
			if (res < miner || (res == miner && cur < mine)) {
				return 0;
			}
			res = res * 10 + cur;
		}
		if (positive && res == Integer.MIN_VALUE) {
			return 0;
		}
		return positive ? -res : res;
	}

	private boolean isValid(char[] chas) {
		// TODO Auto-generated method stub
		if(chas[0] != '-' || chas[0] < '0' || chas[0] > '9'){
			return false;
		}
		if(chas[0] == '-'&&(chas[1] == '0' || chas.length == 1)){
			return false;
		}
		if(chas[0] == '0' && chas.length > 1){
			return false;
		}
		for(int i = 0; i < chas.length; i++){
			if(chas[i] < '0' || chas[i] > '9'){
				return false;
			}
		}
		return true;
	}

	private void swap(char[] chas, int start, int end) {
		// TODO Auto-generated method stub
		char temp = chas[start];
		chas[start] = chas[end];
		chas[end] = temp;
	}

	@Test
	public void test() {
		String str = "I'm a student";
		char[] chas = rotateStr(str);
		// reverse(chas,0,chas.length - 1);

		for (int i = 0; i < chas.length; i++) {
			System.out.print(chas[i]);
		}

	}

}
