package chapter_5_stringproblem;
/**
 * 翻转数字字符串：给定一个字符类型的数组chas,请在单词间左逆序调整。只要做到单词逆序即可，对空格的位置没有特别的要求。
 * 举例：如果把chas看作字符串为"dog loves pig", 调整成"pig Loves dog"
 * 		如果把chas看作字符串为"I'm a student."，调整成"student. a I'm"
 * @author zhangy
 *
 */
public class RotateString {
	
	//先将chas整体逆序，然后将每个单词再逆序就可以实现了
	public void rotateWord(char[] chas){
		if(chas.length == 0 || chas == null){
			return ;
		}
		//先将chas数组整体逆序
		reverse(chas, 0, chas.length -1);
		//然后开始将每个单词进行逆序
		int l = -1;
		int r = -1;
		for(int i = 0; i < chas.length; i++){
			if(chas[i] !=' '){
				l = i == 0 || chas[i-1] == ' ' ? i : l;//如果当前位置为初始位置或者当前位置的前一个位置是空格字符，那么就将i赋值给l
				r = i == chas.length-1 || chas[i + 1] == ' ' ? i : r;// 如果当前位置为最后一个位置或者当前位置的后一个位置是空格字符，那么就将i赋值给r
			}
			if(l != -1 && r != -1){//如果角标变量l和r的值都不是初始值，那么说明当前一个单词遍历结束，需要逆序
				reverse(chas, l, r);
				l = -1;
				r = -1;
			}
		}
	}
	
	
	/**
	 * 补充题目：给定一个字符类型的数组chas和一个整数size,请把大小为size的左半区移到右半区，右半区整体移到左边
	 * 举例： 如果把chas看作字符串为"ABCDE"，size = 3, 调整成"DEABC".
	 * @param args
	 */
	public void rotate2(char[] chas, int size){
		//首先先把chas[0...size-1]部分进行逆序
		reverse(chas, 0, size-1);
		//然后再将chas[size...length -1]的部分进行逆序
		reverse(chas, size, chas.length -1);
		//最后将整体逆序
		reverse(chas, 0, chas.length-1);
	}
	
	public void reverse(char[] chas, int start, int end){//将一个字符数组逆序的方法
		char temp = 0;//temp为空字符
		while(start < end){//正中间位置不用交换，保持原有位置即可
			temp = chas[end];
			chas[end] = chas[start];
			chas[start] = temp;
			start++;//start角标加1
			end--;//同时end角标减1
		}
	}
	
	public static void main(String[] args){
		RotateString rs = new RotateString();
		String str = "I'm a student.";
		char[] chas = str.toCharArray();
		rs.rotateWord(chas);
		System.out.println(String.valueOf(chas));
		
		char[] chas2 = {'A', 'B', 'C', 'D', 'E'};
		rs.rotate2(chas2, 3);
		System.out.println(String.valueOf(chas2));
	}

}
