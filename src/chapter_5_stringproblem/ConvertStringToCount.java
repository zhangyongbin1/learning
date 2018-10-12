package chapter_5_stringproblem;
/**
 * 字符串的统计字符串：给定一个字符串str,返回str的统计字符串。例如："aaabbadddffc"的统计字符串为："a_3_b_2_a_1_d_3_f_2_c_1"
 * @author zhangy
 *
 */
public class ConvertStringToCount {
	
	public String getCountString(String str){
		if(str == null){
			return null;
		}
		char[] ch = str.toCharArray();
		String res = String.valueOf(ch[0]);
		int num = 1;
		//从左到右开始遍历字符数组ch
		for(int i = 1; i < ch.length; i++){
			if(ch[i] == ch[i - 1]){
				num++;
			}else{
				res = res + "_" + num + "_" + ch[i];
				num = 1;
			}
		}
		//因为res的累加是放在ch[i] != ch[i - 1]时，所以如果最后几个字符是相同的，那么res就没有加上"_"+num,所以这里需要处理一下
		res = res + "_" + num;
		return res;
	} 
	
	/**
	 * 补充问题：给定一个字符串的统计字符串cstr，再个给定一个整数index,返回cstr所代表的原始字符串上的第index个字符。
	 * 例如："a_1_b_100"所代表的原始字符串上第0个字符是'a'，第50个字符是'b'
	 * @param args
	 */
	public char getCharAt(String str, int index){
		if(str == null){
			return 0;
		}
		char[] ch = str.toCharArray();
		char cur = 0;//表示上一遇到字符阶段是。遇到的是cur
		int num = 0;//上一个遇到连续字符统计的阶段是，字符出现的数量
		int sum = 0;//遇到字符的总个数
		boolean stage = true;//表示目前处在遇到字符串的阶段，false表示目前处在遇到连续字符统计的阶段
		for(int i = 0; i < ch.length; i++){
			if(ch[i] == '_'){//如果遇到'_'表示现在该进入如连续字符的统计阶段
				stage = !stage;
			}else if(stage){
				sum += num;
				if(sum > index){
					return cur;
				}
				num = 0;
				cur = ch[i];
			}else{//连续字符的统计阶段
				num = num * 10 + ch[i] - '0';//_之后的连续字符表示字符的数量，所以使用这种方式统计
			}
		}
		return sum + num > index ? cur : 0;
	}
	
	public static void main(String[] args){
		ConvertStringToCount cstc = new ConvertStringToCount();
		String str = "aaabbadddffc";
		System.out.println(cstc.getCountString(str));
	}
}
