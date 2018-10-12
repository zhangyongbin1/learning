package chapter_5_stringproblem;
/**
 * 字符串匹配问题：给定字符串str,其中绝对不含有字符'.'和'*'。再给定字符串exp,其中可以含有'.'或'*','*'字符不能是
 * exp的首字符，并且任意两个'*'字符不相邻。exp中的'.'代表任意一个字符，exp中的'*'表示'*'的钱一个字符可以有0个或者
 * 多个。请写一个函数，判断str是否能被exp匹配。
 * 举例： str = 'abc', exp = 'abc',返回true
 * 		str = 'abc', exp='a.c',exp中单个'.'可以代表任意字符，所有返回true
 * 		str = 'abcd', exp = '.*',exp中的'*'的前一个字符是'.'，所以可表示任意数量的'.'字符，当exp是'....'时与
 * 		'abcd'匹配，返回true
 * 		str = '', exp = '..*'.exp中'*'的前一个字符是'.',可表示任意数量的'.'字符，但是'.*'之前还有一个'.'字符
 * 		该字符不受'*'的影响，所以str起码有一个字符才能被exp匹配。所以返回false
 * @author zhangy
 *
 */
public class RegularExpressionMatch {
	/**
	 * 递归过程process(char[] s,char[] e, si, ei)表示从str的si位置开始，一直到str结束位置的子串，即str[si...slen]
	 * ,是否能被exp的ei位置开始一直到exp结束位置的子串(即exp[ei...elen])匹配，所以返回process(s,e,0,0);就是最终结果
	 * 那么在递归过程中如何判断str[si...slen]是否能被exp[ei...elen]匹配呢？
	 * 假设当前判断到str的思维值和exp的ei位置，即process(s,e,si,ei):
	 * 1.如果ei为exp的结束位置(ei==elen),si也是str的结束位置，返回true，因为""可以匹配""。如果si不是str的结束位置，返回
	 * false，这是显而易见的
	 * 2.如果ei位置的下一个字符(e[ei+1])不为'*'.那么就必须关注str[si]字符能否和exp[ei]字符匹配。如果str[si]与exp[ei]
	 * 能够匹配(e[ei] == s[si] || e[ei] == '.'),还要关注str后续的部分能否被exp后续的部分匹配，即process(s,e,si+1,ei+1)
	 * 的返回值。如果str[si]与exp[ei]不能匹配，当前字符都匹配，当然不用计算后续的，直接返回false
	 * 3.如果当前ei位置的下一个字符(e[ei+1])为'*'字符
	 * 	(1)如果str[si]与exp[ei]不能匹配，那么只能让exp[ei...ei+1]这个部分为"",也就是exp[ei+1] == '*'字符的前一个字符
	 * exp[ei]的数量为0才行，然后考察process(s,e,si,ei+2)的返回值。举个例子，str[si...slen]为"bXXX","XXX"代表字符'b'
	 * 之后的字符串。exp[ei...elen]为"a*YYY","YYY"代指字符"*"之后的字符串。当前无法匹配('a' != 'b'),所以让"a*"为"",
	 * 然后考查str[si...slen](即"bXXX")能否被exp[ei+2...elen](即"YYY")匹配
	 * 	(2)如果str[si]与exp[ei]能匹配，这种情况下举例说明：
	 * str[si...slen]为"aaaaaXXX","XXX"指不再连续出现'a'字符的后续字符串，exp[ei...elen]为"a*YYY","YYY"指字符"*"
	 * 之后的后续字符串。
	 * 
	 */
	
	public boolean isValid(char[] s, char[] e){
		for(int i = 0; i < s.length; i++){
			if(s[i] == '.' || s[i] == '*'){
				return false;
			}
		}
		for(int i = 0; i < e.length; i++){
			if(e[i] == '*' && (i == 0 || e[i-1] == '*')){
				return false;
			}
		}
		return true;
	}
	public boolean isMatch(String str, String exp){
		if(str == null || exp == null){
			return false;
		}
		char[] s = str.toCharArray();
		char[] e = exp.toCharArray();
		return isValid(s,e) ? process(s,e,0,0) : false;
	}
	
	public boolean process(char[] s, char[] e, int si, int ei){//递归函数
		if(ei == e.length){//如果ei和si都是两个字符串的末尾位置，则返回true，因为末尾位置都是""
			return si == s.length;
		}
		if(ei + 1 == e.length || e[ei+1] != '*'){//如果ei的下一个位置e[ei+1] != '*'
			return si != s.length && (s[si] == e[ei] || e[ei] == '.') && process(s,e,si+1,ei+1);//那么就关心str[si]位置和exp[ei]的值是否相等，并同时递归到下一个位置
		}
		while(si != s.length && (s[si] == e[ei] || e[ei] == '.')){//如果ei的下一个位置e[ei+1] == '*'，str[si]位置和exp[ei]的值相等
			if(process(s,e,si,ei+2)){
				return true;
			}
			si++;
		}
		return process(s,e,si,ei+2);//如果ei的下一个位置e[ei+1] == '*'，str[si]位置和exp[ei]的值不相等，那么就是exp[ei...ei+1]为""
	}
	
	public static void main(String[] args){
		RegularExpressionMatch rem = new RegularExpressionMatch();
		String str = "abcd";
		String exp = ".*";
		System.out.println(rem.isMatch(str, exp));
	}

}
