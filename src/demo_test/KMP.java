package demo_test;
//使用KMP算法判断一个字符串是否完全包含另外一个字符串
public class KMP {
	public int[] getNext(char[] ms){
		if(ms.length == 1){
			return new int[] {-1};
		}
		//当前字符之前字符串的前后缀字符中公共子串的最大长度
		int[] next = new int[ms.length];
		next[0] = -1;
		next[1] = 0;
		int pos = 2;
		int cn = 0;
		while(pos < next.length){
			if(next[pos - 1] == next[cn]){
				next[pos++] = ++cn;
			}else if(cn >= 0){
				cn = next[cn];
			}else{
				next[pos++] = 0; 
			}
		}
		return next;
	}
	public boolean isContains(String str1, String str2){
		if(str1.length() < str2.length() || str1 == null){
			return false;
		}
		char[] ss = str1.toCharArray();
		char[] ms = str2.toCharArray();
		int si = 0;
		int mi = 0;
		int[] next = getNext(ms);
		while(si < ss.length && mi < ms.length){
			if(ss[si] == ms[mi]){
				si++;
				mi++;
			}else if(next[mi] < 0){
				si++;
			}else{
				mi = next[mi];
			}
		}
		return mi == ms.length ? true : false;
	}
}
