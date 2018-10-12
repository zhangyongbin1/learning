package chapter_5_stringproblem;
/**
 * 字符串的调整与替换：给定一个字符串类型的数组chas[],chas右半区全是空字符，左半区不含有空字符。现在想将左半区中所有的空格
 * 替换成"%20",假设chas右半区足够大，可以满足替换所需要的空间，请完成替换函数。
 * 例如：如果把chas的左半去看作字符串， 为"a b  c"，假设chas的右半去足够大。替换后，chas的左半区为"a%20b%20%20c"。
 * @author zhangy
 *
 */
public class ModifyAndReplace {
	
	/**
	 * 遍历一遍可以得到两个信息，chas的左半区有多大，记为len,左半区的空格数有多少，记为num,那么可知空格字符被"%20"替换后，
	 * 长度将为Len+ num * 2, 其中0字符就是代表的是空字符 
	 */
	
	public void replace(char[] chas){
		if(chas == null || chas.length == 0){
			return;
		}
		int len = 0;
		int num = 0;
		for(len = 0; len < chas.length && chas[len] != 0; len++){
			if(chas[len] == ' ' ){
				num++;
			}
		}
		int j = len + num * 2 -1;//被替换后的新字符数组长度,为什么是乘以2，因为空格本身就算是一个字符，所有只需要乘以2即可
		//继续遍历原来的数组，将最后一个字符放置在j位置处
		for(int i = len -1; i >=0; i-- ){
			if(chas[i] != ' '){
				chas[j--] = chas[i];
			}else{
				chas[j--] = '0';
				chas[j--] = '2';
				chas[j--] = '%';
			}
		}
	}
	/**
	 * 补充题目：给定一个字符类型的数组chas[].其中只含有数字字符和'*'字符。现在想把所有的'*'字符挪到chas的左边，数字字符挪到chas的右边
	 * 要求：不得改变数字字符从左到右出现的顺序。例如：原字符为："12**345".调整后的chas为"**12345"
	 * @param args
	 */
	//简单的方法：从右向左倒着复制，遇到数字字符那么就直接复制，遇到'*'字符不复制。当把数字字符复制完毕后，把左半区全部设置成'*'即可
	public void modify(char[] chas){
		if(chas == null || chas.length == 0){
			return ;
		}
		int j = chas.length -1;
		for(int i = j; i >= 0; i--){
			if(chas[i] != '*'){
				chas[j--] = chas[i];
			}
		}
		for( ; j >=0; j--){
			chas[j] = '*';
		}
	}
	public static void main(String[] args){
		ModifyAndReplace mar = new ModifyAndReplace();
		char[] chas = { 'a', ' ', 'b', ' ', ' ', 'c', 0, 0, 0, 0, 0, 0, 0, 0, };
		mar.replace(chas);
		System.out.println(String.valueOf(chas));
		char[] chas2 = { '1', '2', '*', '*', '3', '4', '5' };
		mar.modify(chas2);
		System.out.println(String.valueOf(chas2));
	}

}
