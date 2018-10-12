package chapter_5_stringproblem;
/**
 * 判断两个字符串是否互为旋转词：如果一个字符串str,把字符串str前面任意的部分挪到后面形成的字符串叫作str的旋转词。
 * 例如：str="12345"，str的旋转词有"12345"、"23451"、"34512"、"45123"和"51234"。给定两个字符串a和b。请判断a和b是否互为旋转词。
 * @author zhangy
 *
 */
public class IsRotation {
	
	/**
	 * 如果字符串a和b的长度不一样，那么两者肯定不是互为旋转次，可以直接返回false。如果两者长度相同，那么使用两个字符b拼接成一个大字符串b2，
	 * 然后看b2字符串是否包含字符串a,如果包含，那么a和b就互为旋转字符串，如果不包含，那么a和b就不互为旋转字符串。
	 * ps:大字符串b2包含b字符串所有的旋转字符串,也就是将判断是否互为旋转字符串的问题转化为大字符串是否包含小字符串的问题，使用KMP算法解决
	 */

	public boolean isRotation(String str1, String str2){
		if(str1 == null || str2 == null || str1.length() != str2.length()){
			return false;
		}
		String str = str2 + str2;
		return getIndexOf(str,str1) == -1 ? false : true;
	}
	//KMP 算法
	public int getIndexOf(String str, String str1){
		if(str == null || str1 == null || str1.length() < 1 || str1.length() > str.length() ){
			return -1;
		}
		char[] ch1 = str.toCharArray();
		char[] ch2 = str1.toCharArray();
		int[] next = getNext(ch2);
		int si = 0;
		int mi = 0;
		while(si < ch1.length && mi < ch2.length){
			if(ch1[si] == ch2[mi]){//如果两个字符相等，那么继续往下执行
				 si++;
				 mi++;
			}else if(next[mi] == -1){//说明大字符串当前位置与match字符串的起始位置都不相同
				si++;//所以大字符串的位置向下移动一位继续比较
			}else{
				mi = next[mi];//match字符串向右滑动next[mi]个位置
			}
		}
		return mi == ch2.length ? si - mi : -1;//计算包含字符串的骑士位置
	}
	//获取match字符串的next数组
	public int[] getNext(char[] ch){
		if(ch.length == 1){//如果没有最长前缀和后缀
			return new int[]{-1};//直接复制为1
		}
		int[] next = new int[ch.length];
		next[0] = -1;
		next[1] = 0;//因为最长前缀为不包含尾，最长后缀不包含头，所以赋值0
		int pos = 2;
		int cn = 0;
		while(pos < ch.length){
			if(ch[pos - 1] == ch[cn]){//当前位置的前一个字符与前一个字符的最长前后缀位置处的字符相等
				next[pos++] = ++cn; //说明当前位置的next数组值是前一个字符next数组子加1
			}else if(cn > 0){//否则就继续往前寻找
				cn = next[cn];
			}else{
				next[pos++] = 0;
			}
		}
		return next;
	}
	public static void main(String[] args){
		IsRotation ir = new IsRotation();
		String str1 = "12345";
		String str2 = "23451";
		System.out.println(ir.isRotation(str1, str2));
	}
}
