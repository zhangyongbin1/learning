package chapter_5_stringproblem;

import java.util.LinkedList;

/**
 * 公式字符串求值：给定一个字符串str,str表示一个公式，公式里可能有整数、加减乘除符号和左右括号，返回公司的计算结果
 * 举例：str="48*((70-65)-43)+8*1",返回-1816
 * 		str="3+1*4",返回7
 * 		str = "3+(1*4)",返回7
 * 说明：1.可以认为给定的字符串一定是正确的公式，即不需要对str做公式有效性检查。
 * 		2.如果是负数就需要用括号括起来，比如"4*(-3)"。但如果负数作为公式的开头或括号部分的开头，则可以没有括号，比如"-3*4"和"(-3*4)都是合法的"
 * 		3.不用考虑计算过程中会发生溢出的情况
 * 分析：从左到右遍历str,开始遍历或者遇到字符'('时，就进行递归过程。当发现str遍历完，或者遇到字符')'时，递归过程就结束。比如"3*(4+5)+7",一开始遍历
 * 就进入递归过程value(str,0),在递归过程value(str,0)中继续遍历str,当遇到字符'('时，递归过程value(str,0)又重复调用递归value(str,3).然后在
 * 递归过程value(str,3)中继续遍历str,当遇到字符')'时，递归过程value(str,3)结束，并向递归过程value(str,0)返回两个结果，第一结果是value(str,3)
 * 遍历过的公式字符子串的结果，即"4+5"==9, 第二个结果是value(str,3)遍历到的位置，即字符')'的位置==6。递归过程value(str,0)收到这两个结果后，既可以
 * 交给value(str,3)过程处理的字符串结果是多少("(4+5)"的结果是9)，又可知道自己下一该从什么位置继续遍历(该从位置6的下一个位置(即位置7))继续遍历。总之，
 * value方法的第二个参数代表递归过程是从什么位置开始的，返回的结果是一个长度为2的数组，记为res.res[0]表示递归过程计算的结果，res[1]表示这个递归过程
 * 遍历到str的什么位置。
 * 		既然在递归过程中遇到'('就交给下一层的递归过程处理，自己只用接受'('和')'之间的公式字符子串的结果，所以所有的递归过程来说，可以看作计算的公式
 * 都是不含有'('和')'字符的，比如，对递归过程value(str,0)来说，实际上计算的公式是"3*9+7","(4+5)"的部分交给递归过程value(str,3)处理，拿到结果9
 * 之后，再从字符'+'继续。所以，只要想弄清楚如果计算一个不含有'('和')'的公式字符串，整个实现就完成了
 * @author zhangy
 *
 */
public class ExpressionCompute {
	
	public int getValue(String str){
		return value(str.toCharArray(), 0)[0];
	}
	
	public int[] value(char[] chas, int i){
		LinkedList<String> que = new LinkedList<String>();
		int pre = 0;
		int[] bra = null;
		while(i < chas.length && chas[i] != ')'){//当发现str遍历完，或者遇到字符')'时，递归过程就结束。
			if(chas[i] >= '0' && chas[i] <= '9'){//递归过程遇到的第一个字符是数字，那么就直接记录下来(48)
				pre = pre * 10 + chas[i++] - '0';
			}else if(chas[i] != '('){
				addNum(que, pre);//只是把pre的值放置到队列中，而没有把当前字符chas[i]放入到队列的末尾
				que.addLast(String.valueOf(chas[i++]));//所以这里需要将当前字符chas[i]放入到队列的末尾
				pre = 0;//重置pre的值
			}else{//chas[i] == '('进入到重复递归过程
				bra = value(chas, i+1);//每一层递归返回的结果都会到这里来
				pre = bra[0];
				i = bra[1] + 1;
			}
		}
		addNum(que, pre);//48*((70-65)-43)+8*1中遇到(70-65)中的')'字符，就会退出while循环来到这里
		return new int[] {getNum(que), i};//此时会把que中的结果计算出来，getNum(que)是结果，i是下次递归开始的位置
	}

	public int getNum(LinkedList<String> que) {//该方法将que中的所有类似'3+4-5+6'的字符串计算出正确结果出来
		int res = 0;
		boolean add = true;
		String cur = null;
		int num = 0;
		while(!que.isEmpty()){
			cur = que.pollFirst();//获取队列que中第一个位置的值
			if(cur.equals("+")){
				add = true;
			}else if(cur.equals("-")){
				add = false;
			}else{
				num = Integer.valueOf(cur);
				res += add ? num : (-num);
			}
		}
		return res;
	}

	public void addNum(LinkedList<String> que, int num) {//就是为了将数字字符和加减字符保存在队列中，最后使用这个队列进行最后的结果计算
		if(!que.isEmpty()){
			int cur = 0;
			String last = que.pollLast();
			if(last.equals("+") || last.equals("-")){
				que.addLast(last);
			}else{//说明que中最后一个字符是'*'或者'/',所以先给它计算出来从而保证que中全是加法和减法
				cur = Integer.valueOf(que.pollLast());
				num = last.equals("*") ? cur * num : cur / num;
			}
		}
		que.addLast(String.valueOf(num));
	}

	public static void main(String[] args){
		ExpressionCompute ec = new ExpressionCompute();
		String str = "48*((70-65)-43)+8*1";
		System.out.println(ec.getValue(str));
	}
}
