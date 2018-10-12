package chapter_5_stringproblem;
/**
 * 给定一个字符串数组strs[],在strs中有些位置为null,但在不为null的位置上，其字符串是按照字典顺序由小到大依次出现的。再给定
 * 一个字符串str，请返回str在strs中出现的最左的位置.例如：strs=[null,"a",null,"a",null,"b",null,"c"],str="a",返回1.
 * strs=[null,"a",null,"a",null,"b",null,"c"],str=null,返回-1.
 * strs=[null,"a",null,"a",null,"b",null,"c"],str="d",返回-1.
 * @author zhangy
 *
 */
public class FindStringInContainsNullArray {
	//尽可能多地使用二分查找的方法,二分查找的要求是数组必须是有序排列的
	
	public int getIndex(String[] strs, String str){
		if(str == null || strs == null){
			return -1;
		}
		int left = 0;
		int right = strs.length - 1;
		int res = -1;
		int mid = 0;
		int i = 0;
		while(left <= right){
			mid = (left + right)/2;
			if(strs[mid] != null && strs[mid].equals(str)){
				//说明找到了str,但是要找的是最左的位置，所以还需要在左半区继续寻找
				res = mid;
				right = mid - 1;//重复while循环
			}else if(strs[mid] != null){//说明strs[mid] != str，且strs[mid]不为null
				//那么就需要判断strs[mid]与str字典顺序大小，从而判断下一次是遍历那一个半区
				if(strs[mid].compareTo(str) < 0){//说明strs[mid]的字典顺序比sts小，需要遍历右半区
					left = mid + 1;
				}else{//说明需要遍历左半区
					right = mid - 1;
				}
			}else{//说明strs[mid] != str 并且 str[mid] = null
				//从mid开始，从右到左遍历左半区，如果整个左半区都为null，那么继续用二分的方式在右半区上查找，如果整个左半区
				//不都为null,假设从右到左遍历strs[left...mid]时，发现第一个不为null的位置是i,那么把str和strs[i]比较
				//如果strs[i]字典顺序小于str,同样说明整个左半区没有str,令left = mid + 1,
				//如果strs[i]字典顺序等于str,说明找到了str,令res = mid,但要找的是最左的位置，所以还要在strs[left...i-1]上寻找
				//如果str[i]的字典顺序大于str,说明strs[i...right]上都没有str,需要在strs[left...i-1]上继续寻找，所以令right= i -1
				i = mid;
				while(strs[i] == null && --i >= left)//从右到左遍历左半区,即strs[left, mid],直至strs[i] != null 为止
					;
				
				//i < left 说明整个左半区全部为null, 或者strs[i]的字典顺序小于str，说明此时str可能出现在右半区或者是strs[left...i]的右半区
					if(i < left || strs[i].compareTo(str) < 0){
						left = mid + 1;//所有令left = mid + 1
					}else{//否则说明整个左半区不全为null,且str在strs[left...i]的右半区
						res = strs[i].equals(str) ? i : res;//统计str的位置
						right = i -1;//设置right值使区间落在strs[left...i]的右半区
					}
			}
		}
		return res;
	}
	
	public static void main(String[] args){
		String[] strs = new String[] { null, "a", null, "a", null, "b", null,
				null, null, "b", null, "c", null, "c", null, null, "d", null,
				null, null, null, null, "d", null, "e", null, null, "e", null,
				null, null, "f", null, "f", null };
		FindStringInContainsNullArray fsicna = new FindStringInContainsNullArray();
		String str1 = "a";
		System.out.println(fsicna.getIndex(strs, str1));
		String str2 = "b";
		System.out.println(fsicna.getIndex(strs, str2));
		String str3 = "c";
		System.out.println(fsicna.getIndex(strs, str3));
		String str4 = "d";
		System.out.println(fsicna.getIndex(strs, str4));
		String str5 = "e";
		System.out.println(fsicna.getIndex(strs, str5));
		String str6 = "f";
		System.out.println(fsicna.getIndex(strs, str6));
	}
}
