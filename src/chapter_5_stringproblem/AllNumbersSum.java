package chapter_5_stringproblem;
/**
 * 字符串中数字子串的求和：给定一个字符串str,求其中全部数字串所代表的数字之和。例如：str="A1CD2E33" 返回36；str="A-1BC--12",返回11
 * 要求：1.忽略小数点字符，例如："A1.3",其中包含两个数字1和3
 * 		2.如果紧贴数字子串的左侧出现字符'-'，当连续出现的数量为奇数时，则数字视为负，连续出现的数量为偶数时，则数字视为正
 * 				例如："A-1BC--12",其中包含数字为-1，和12
 * @author zhangy
 *
 */
public class AllNumbersSum { 
	/**
	 * 关键就是：如果在从左到右遍历字符串str的过程中，准确地收集每个数字并累加起来。具体过程如下：
	 * 	1.生成三个变量。整型变量res,表示目前的累加和；整型变量num,表示当前收集到的数字；布尔型变量posi，表示如果把num累加到res里，num是正还是负
	 * 	2.从左到右遍历str，假设遍历到字符cha,根据具体的cha有不同的处理
	 * 	3.如果cha是'0'~'9',cha-'0'记为cur,假设之前收集的数字为num,举例说明：比如str="123",初始时num=0,posi = true。当cha = '1'时，num变成1，
	 * 	cha='2'时，num变成12；cha == '3',num变成123.再如：str="-123"，初始时，num=0,posi = true.当cha=='-'时，posi = false,cha == '1',
	 * 	则num变成-1，cha == '2'，num变成-12；cha == '3'；num变成-123.总之num = num * 10 + (posi ? cur : -cur);
	 * 	4.如果cha不是'0'~'9'，此时不管cha具体是什么，都是是累加是。令res += num;然后令num = 0,累加完num当然要清0.累加完成后，再看cha具体的情况，
	 * 	如果cha不是字符'-',令posi = true,即如果cha既不是数字字符，也不是'-'字符，posi都变为true.如果cha是'-'字符，此时看cha的前一个字符，如果
	 * 	前一个字符也是'-'字符，则posi改变符号，即posi = !posi;否则令posi = false
	 * 	5.既然我们把累加时机放在了cha不是数字字符的时候，那么如果str是以数字字符结尾的，会出现最后一个数字没有累加的情况。所以遍历完成后，令 res += num
	 * 	防止最后的数字累加不上的情况发生
	 * 	6.最后返回res
	 */
	
	public int numSumInString(String str){
		if(str == null){
			return 0;
		}
		int num = 0;
		int res = 0;
		boolean posi = true;
		int cur = 0;
		char[] ch = str.toCharArray();
		for(int i = 0; i < ch.length; i++){
			cur = ch[i] - '0';//记录当前遍历到的字符
			if(cur < 0 || cur > 9){//如果当前字符不是数字字符,num统计的是当前连续数字字符组成的数字
				res += num;//如果当前字符不是数字字符是开始计算累加和
				num = 0;//计算完累加和之后，当然要把num清零
				if(ch[i] == '-'){
					if(i - 1 > -1 && ch[i - 1] == '-'){//如果连续出现两次，或者是偶数次字符'-'，那么posi需要取反
						posi = !posi;
					}else{
						posi = false;//如果出现奇数次，那么就是负数，置为false
					}
				}else{
					posi = true;//如果当前的字符不是数字字符也不是'-'字符，则posi都置为true
				}
			}else{
				num = num * 10 + (posi ? cur : -cur);
			}
		}
		res += num;//如果字符串str是以数字结尾，那么需要再加上最后的这个数字字符组成的数字值
		return res;
	}

	public static void main(String[] args){
		AllNumbersSum ans = new AllNumbersSum();
		String str1 = "A-1BC--12";
		System.out.println(ans.numSumInString(str1));
	}
}
