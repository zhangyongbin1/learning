package chapter_5_stringproblem;
/**
 * 找到被指的新类型字符：新类型字符的定义如下：
 * 		1.新类型字符是长度为1或者2的字符串
 * 		2.表现形式可以仅是小写字母，例如，"e"；也可以是大写字母+小写字母，例如，"Ab"；还可以是大写字母+大写字母，例如，"DC"
 * 现在给定一个字符串str,str一定是若干新类型字符正确组合的结果。比如"eaCCBi",由新类型字符"e"、"a"、"CC"和"Bi"拼成。再
 * 给定一个整数k，代表str中的位置。请返回被k位置指中的新类型字符
 * 举例： str = "aaABCDEcBCg".当k = 7时，返回"Ec";当k = 4时，返回"CD";当k = 10时，返回"g"
 * @author zhangy
 *
 */
public class FindNewTypeChar {
	/**
	 * 更快的方法：从k-1位置开始，向左统计连续出现的大写字母的数量为uNum,遇到小写字母就停止。如果uNum为奇数，str[k-1...k]
	 * 是被指中的新类型字符。如果uNum为偶数且str[k]是大写字母，str[k...k+1]是被指中的新类型字符。
	 * 如果uNum为偶数且str[k]是小写字母，str[k]是被之中的新类型字符
	 */

	
	public String getNewTypeChar(String str, int k){
		if(str == null || str.length() == 0 || k < 0 || k >= str.length()){
			return "";
		}
		char[] chas = str.toCharArray();
		int uNum = 0;
		//从k-1位置开始向左遍历
		for(int i = k-1; i >=0; i--){
			if(!isUpper(chas[i])){
				break;
			}
			uNum++;
		}
		if((uNum & 1 ) == 1){
			return str.substring(k-1, k+1);//如果uNum是奇数，那么被指中的新型字符就是str[k-1...k]
		}
		//uNum是偶数
		if(isUpper(chas[k])){
			return str.substring(k, k+2);//str[k...k+1]就是被指中的新型字符
		}
		return str.substring(k,k+1);//str[k]就是被中的新型字符
	}

	public boolean isUpper(char c) {
		// TODO Auto-generated method stub
		return (c >= 'A' && c <= 'Z');
	}
	
	public static void main(String[] args) {
		FindNewTypeChar fntc = new FindNewTypeChar();
		String str = "aaABCDEcBCg";
		System.out.println(fntc.getNewTypeChar(str, 7));
		System.out.println(fntc.getNewTypeChar(str, 4));
		System.out.println(fntc.getNewTypeChar(str, 10));

	}
}
