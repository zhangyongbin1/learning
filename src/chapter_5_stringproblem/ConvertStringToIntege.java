package chapter_5_stringproblem;
/**
 * 将整数字符转换成整数数值：给定一个字符串str,如果str符合日常书写的整体形式，并且属于32位整数的范围，返回str所代表的整数值，否则返回0
 * 例如：str="123" 返回123；
 * 		str = "023" 因为"023"不符合日常的书写习惯，所以返回0.
 * 		str = "A13"，返回0
 * 		str = "2147483647" ,返回2147483647
 * 		str = "2147483648", 因为溢出来，所以返回0
 * 		str = "-123",返回-123
 * @author zhangy
 *
 */
public class ConvertStringToIntege {
	
	/**
	 * 遍历的过程如何判断res已经已经溢出了？假设当前字符为a，那么'0'-a就是当前字符所代表的数字的负数形式，记为cur。如果在res加上cur之前，
	 * 发现res已经小于minq,那么当res加上cur之后一定会溢出，比如str="3333333333",遍历完倒数第二个字符后，res == -333333333 < minq == -214748364,
	 * 所以当遍历到最后一个字符是，res * 10肯定会产生溢出。如果在res加上cur之前，发现res等于minq,但又发现cur小于minr.那么当res加上cur之后一定会溢出。
	 * 例如str=2147483649,遍历完倒数第二个字符后，res=-214748364 == minq,当遍历到最后一个字符时，发现res == minq,同时也发现cur == -1 < minr = -8,
	 * 那么当res加上cur之后一定会溢出。出现任何一种溢出情况时，直接返回0.
	 */

	public int convertToString(String str){
		if(str.equals("") || str == null){
			return 0;//不能转 
		}
		char[] ch = str.toCharArray();
		if(!isValid(ch)){
			return 0;//说明字符串不符合日常书写规范
		}
		boolean posi = ch[0] == '-' ? false : true;//posi = false为负数，posi = true为正数
		int minq = Integer.MIN_VALUE / 10;//用于判断当转换完倒数第二个字符时，此时的值是否超过minq
		int minr = Integer.MIN_VALUE % 10;//用于判断当转换完倒数第二个字符时，如果值等于minq时，用于判断最后一个字符的值与minr的大小情况
		int res = 0;
		int cur = 0;
		for(int i = posi ? 0 : 1; i < ch.length; i++ ){
			cur = '0' - ch[i];//先将字符位置的值转换为负的
			if((res < minq) || (res == minq && cur < minr)){//说明溢出来，不能转换，直接返回0
				return 0;
			}
			res = res * 10 + cur;
		}
		if(posi && res == Integer.MIN_VALUE){
			return 0;//不能转
		}
		return posi ? -res : res;
	}
	
	public boolean isValid(char[] ch){
		if(ch[0] != '-' && ((ch[0] < '0') || ch[0] > '9')){//整数字符不以'-'开头，不以数字字符开头
			return false;
		}
		if(ch[0] == '-' && (ch.length == 1 || ch[1] == '0')){//以'-'开头，并且长度为1或者后面紧接着是字符-0 or -
			return false;
		}
		if(ch[0] == '0' && ch.length >1){//如果整数字符串以0字符开头并且长度大于1, 0111111
			return false;
		}
		for(int i = 0; i < ch.length; i++){//遍历整数字符串看其是否用非整数字符
			if(ch[i] < '0' || ch[i] > '9'){
				return false;
			}
		}
		return true;
	}
}
