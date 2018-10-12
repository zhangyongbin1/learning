package chapter_4_recursionanddp;

/**
 * 字符串转换为字母组合的种数：给定一个字符串str,str全部由数字字符组成，如果str中某一个或者某相邻两个字符组成的子串在1～26之间，
 * 则这个子串可以转换为一个字母。规定"1"转换为"A","2"转换为"B"..."26"转换为"Z"。写一个函数，求str有多少中不同的转换结果，并返回种数
 * 
 * @author zhangy
 *
 */
public class NumberToLetter {
	// 使用暴力递归的方法，时间复杂度为O(2^N)
	public int num1(String str) {
		if (str == null || str.equals("")) {
			return 0;
		}
		char[] ch = str.toCharArray();
		return process(ch, 0);
	}

	public int process(char[] ch, int i) {
		if (i == ch.length) {// 局势整个字符串可以转换的种数
			return 1;
		}
		if (ch[i] == '0') {// 如果出现字符'0'，那么当前转换的种数为0
			return 0;
		} 
		int res = process(ch, i + 1);// 递归开始以一个字符为标准开始转换
		if (i + 1 < ch.length && (ch[i] - '0') * 10 + (ch[i + 1] - '0') < 27) {// 判断是否符合两个字符的情况
			res += process(ch, i + 2);// 递归开始以两个字符为标准开始转换
		}

		return res;
	}
	// 观察以上的递归过程可以发现，p(i)最多依赖p(i+1)和p(i+2)的值，这是可以从后往前进行顺序计算的，也就是先计算p(N)和p(N-1)。然后
	// 根据这两个值计算p(N-2).再根据p(N-1)和p(N-2)计算p(N-3)，依次类推，根据p(1)和p(2)计算出p(0)即可。类似斐波纳切数列。只不过这厮从后往前计算的
	//时间复杂度为O(N),空间复杂度为O(1)
	public int num2(String str){
		if(str.equals("") || str == null){
			return 0;
		}
		char[] ch = str.toCharArray();
		int cur = ch[ch.length - 1] == '0' ? 0 : 1;//从当前字符串的最后一个字符开始往前进行匹配，cur记录到达当前字符数时可以转换的字符串总数
		int next = 1;//用于记录更新当前字符的后一个字符为起始位置时可以转换的字符串总数
		int temp = 0;//临时变量用于临时保持值
		for(int i = ch.length - 2; i >= 0; i--){//从倒数第二个位置开始循环
			if(ch[i] == '0'){
				next = cur;//记录当前位置的后一个位置的转换总数
				cur = 0;//当前字符的转换成字符串的总数为0
			}else{//说明可以转换
				temp = cur;
				//如果出现10,说明cur之前为0，而现在10转换不成字符串，所以当前cur也为0，如果出现11，那么之前的cur = 1,那么现在11也能转换成字符串，所以当前cur也等于1
				if((ch[i] - '0') * 10 + ch[i+1] - '0' < 27){//满足两个字符组合在一起也能转换的情况
					cur += next;//然后加上两个字符能转换的情况，所以next初始值为1
				}
				next = temp;
			}
		}
		
		return cur;
	}
	public static void main(String[] main){
		NumberToLetter ntl = new NumberToLetter();
		String str = "1111";
		System.out.println(ntl.num2(str));
	}
}
