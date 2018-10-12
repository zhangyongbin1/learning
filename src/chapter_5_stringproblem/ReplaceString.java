package chapter_5_stringproblem;
/**
 * 替换字符串中连续出现的指定字符串
 * @author zhangy
 *
 */
public class ReplaceString {
	
	public String replaceString(String str, String from, String to){
		if(str == null || from == null || str.equals("") || from.equals("")){//from为空，说明str中是不可能有空字符的
			return str;
		}
		char[] ch1 = str.toCharArray();
		char[] ch2 = from.toCharArray();
		int match = 0;
		for(int i = 0; i < ch1.length; i++){
			if(ch1[i] == ch2[match++]){
				if(match == ch2.length){//说明匹配上了from字符串
					clear(ch1, i, ch2.length);//先清空str中的匹配字符
					match = 0;//match重置为0
				}
			}else{//如果ch1[i] != ch2[match++],那么就说明不匹配，将match重置为0，重新从from的开始位置进行匹配
				match = 0;
			}
		}
		//将匹配上的字符清空后，需要将to字符插入进去
		String res =  "";
		String cur = "";
		for(int i = 0; i < ch1.length; i++){
			if(ch1[i] != 0){
				cur = cur + String.valueOf(ch1[i]);
			}
			if(ch1[i] == 0 && (i == 0 || ch1[i - 1] != 0)){//如果在修改后的str中遇到空字符，并且空字符之前的一个字符不为空
				res = res + cur + to;//那么就把替换字符加在空字符的位置
				cur = "";
			}
		}
		//如果最后一段没有空字符串的话，需要把最后一段统计的字符串加在res上
		if(!cur.equals("")){
			res += cur;
		}
		return res;
	}
	public void clear(char[] ch, int end, int len){//将某个字符位置设置为空字符
		while(len-- != 0){
			ch[end--] = 0;
		}
	}

}
