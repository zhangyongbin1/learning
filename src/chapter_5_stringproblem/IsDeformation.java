package chapter_5_stringproblem;
/**
 * 判断两个字符串会否互为变形词：给定两个字符串str1和字符串str2,如果str1和str2中出现的字符种类一样且每种字符出现次数也一样，
 * 那么str1和str2互为变形词。请实现函数判断两个字符串互为变形词。例如：str1="123",str2="231"，返回true;str1="123",str2="2331",返回false
 * @author zhangy
 *
 */
public class IsDeformation {
	/**
	 * 如果字符串str1和str2长度不同，直接返回false。如果长度相同，假设出现字符的编码值在0～255之间，那么先申请一个长度为
	 * 255的整型数组map,map[a]=b代表字符编码为a的字符出现了b次，初始时map[0...255]的中都是0.然后遍历字符串str1,统计
	 * 每种资产出现的数量，比如：遍历到字符‘a’,其初始码值为97，则令map[97]++,这样map就成了str1中每种字符的词频统计表，
	 * 然后遍历字符串str2,每遍历到一个字符串都在map中把词频减下来，比如遍历到字符'a',其编码值为97，则map[97]--,如果减少
	 * 之后的值小于0，直接返回false，如果遍历完str2,map中也没有出现负值(应该是遍历完之后map数组又全为0吧？？，应该是这么理解的)，
	 * 则返回true
	 */
	public boolean isDeformation(String str1, String str2){
		if(str1 == null || str2 == null || str1.length() != str2.length()){
			return false;
		}
		int[] map = new int[255];
		char[] ch1 = str1.toCharArray();
		char[] ch2 = str2.toCharArray();
		for(int i = 0; i < ch1.length; i++){
 			map[ch1[i]]++;
		}
		for(int j = 0; j < ch2.length; j++){
			if((map[ch2[j]]--) == 0){//先使用map[ch2[j]]与0进行比较，然后再执行--操作
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args){
		IsDeformation id = new IsDeformation();
		String str1 = "1A23";
		String str2 = "2B31";
		System.out.println(id.isDeformation(str1, str2));
	}
}
