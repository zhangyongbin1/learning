package chapter3_TreeProblem;
/**
 * KMP解决的是一个长字符串是否完全包含一个短字符串的问题
 * @author zhangy
 *
 */
public class KMPProblem {
	
	public int getStartIndexOfCommon(String s, String m){
		if(s == null || m == null || m.length() < 1 || s.length() < m.length()){
			return -1;
		}
		char[] ss = s.toCharArray();
		char[] ms = m.toCharArray();
		int[] next = getNextArray(ms);
		int mi = 0;
		int si = 0;
		while(si < ss.length && mi < ms.length){
			if(ss[si] == ms[mi]){
				si++;
				mi++;
			}else if(next[mi] == -1){
				si++;
			}else{
				mi = next[mi];
			}
		}
		return mi == ms.length ? si - mi : -1;
	}
	
	//获取KMP算最重要的next数组
	public int[] getNextArray(char[] ms){
		if(ms.length == 1){
			return new int[] {-1};
		}
		int[] next = new int[ms.length];
		next[0] = -1;
		next[1] = 0;//因为第二个字符的最长前后缀是不包括其之前一个字符的，所以设置为0
		int pos = 2;
		int cn = 0;
		while(pos < ms.length){
			if(ms[pos-1] == ms[cn]){
				next[pos++] = ++cn;
			}else if(cn > 0){
				cn = next[cn];
			}else{
				next[pos++] = 0;
			}
		}
		return next;
	}

}
