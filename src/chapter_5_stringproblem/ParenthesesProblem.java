package chapter_5_stringproblem;
/**
 * 括号字符串的有效性和最长有效长度：给定一个字符str,判断是不是整体有效的括号字符串
 * 举例：str="()",返回true  str="(())",返回true  str="(()())"返回true
 *      str="())",返回false； str="()(" 返回false str = "()a()",返回false
 * @author zhangy
 *
 */
public class ParenthesesProblem {
	/**
	 * 1.从左到右遍历字符串str,判断每一个字符是不是"(" and ")",如果不是，就直接返回false
	 * 2.遍历到每一个字符时，都检查到目前为止"(" and ")"的数量，如果")"更多，则直接返回false
	 * 3.遍历后检查"(" and ")"的数量，如果一样多，则返回true，否则返回false
	 */
	public boolean ValidParentheses(String str){
		if(str == null || str.equals("")){
			return false;
		}
		char[] chas = str.toCharArray();
		int status = 0;
		for(int i = 0; i < chas.length; i++){//遍历字符串
			if(chas[i] != ')' && chas[i] != '('){//判断每个字符是不是'(' and ')'
				return false;//不是的话直接返回false
			}
			if(chas[i] == ')' && --status < 0){//如果之前没出现过'('，那么status就一直为0,
				return false;
			}
			if(chas[i] == '('){//统计遇到'('个数
				status++;
			}
		}
		return status == 0;
	}
	/**
	 * 补充题目： 给定一个括号字符串str,返回最长的有效括号子串
	 * 举例：str = "(()())",返回6； str = "())",返回2; str = "()(()()(",返回4
	 * 用动态规划法求解，可以做到时间复杂度为O(N)，额外空间复杂度为O(N)。首先生成长度和str字符串一样的数组dp[],dp[i]值的含义为
	 * str[0...i]中必须以字符str[i]结尾的最长的有效括号子串长度。那么dp[i]值可以按如下方式求解：
	 * 1.dp[0]=0;只含有一个字符肯定不是有效括号字符串，长度自然为0
	 * 2.从左到右依次遍历str[1...n-1]的每一个字符，假设遍历到str[i]
	 * 3.如果str[i] == '(',有效括号字符串必然是以')'结尾，而不是以'('结尾，所以dp[i]=0
	 * 4.如果str[i]==')',那么以str[i]结尾的最长有效括号子串可能存在。dp[i-1]的值代表必须以str[i-1]结尾的最长有效括号子串的长度
	 * ，所以如果i-dp[i-1]-1位置上的字符是'(',就能与当前位置的str[i]字符再配出一对有效括号。比如"(()())",假设遍历到最后一个字符')',
	 * 必须以倒数第二个字符结尾的最长有效括号子串是"()()",找到这个字符之前的字符，即i-dp[i-1]-1位置的字符，发现是'(',所以它可以和最后
	 * 一个字符再配出一对有效括号。如果该情况发生，dp[i]的值起码是dp[i-1]+2,但还有一部分长度容易被人忽略。比如"()(())",假设遍历到
	 * 最后一个字符')'，通过上面的过程找到必须以最后字符结尾的最长有效括号子串起码是"(())",但是前面还有一段"()",可以和"(())"结合在一起
	 * 构成更大的有效括号子串。也就是说str[i-dp[i-1]-1]和str[i]配成了一对，这是还应该把dp[i-dp[i-1]-2]的值加到dp[i]中，这么做表示把
	 * str[i-dp[i-1]-2]结尾的最长有效括号子串接到前面，才能得到以当前字符结尾的最长有效括号子串
	 * 5.dp[0...N-1]中的最大值就是最终结果
	 */
	
	public int getMaxLength(String str){
		if(str == null || str.equals("")){
			return 0;
		}
		char[] chas = str.toCharArray();
		int[] dp = new int[chas.length];
		int pre = 0;
		int res = 0;
		for(int i = 0; i < chas.length; i++){
			if(chas[i] == ')'){//必须是以字符')'结尾才有可能构成有效括号字符
				pre = i - dp[i-1] -1;//往前面找匹配当前')'的有效字符'('的角标位置
				if(pre >=0 && chas[pre] == '('){//如果pre角标位置的字符是'(',说明匹配上了i位置的')'字符构成了有效的括号字符
					dp[i] = dp[i-1] + 2 + (pre > 0 ? dp[pre -1] : 0);//括号里面表示pre位置之前可能还有构成有效括号字符的个数，所以都需要加起来
				}
			}
			res = Math.max(res, dp[i]);
		}
		return res;
	}
	public static void main(String[] args){
		ParenthesesProblem ptp = new ParenthesesProblem();
		String str = "()(()()())";
		System.out.println(ptp.ValidParentheses(str));
		System.out.println(ptp.getMaxLength(str));
	}
}
