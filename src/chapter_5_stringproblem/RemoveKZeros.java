package chapter_5_stringproblem;
/**
 * 去掉字符串中连续出现k个0的子串：给定一个字符串str和一个整数k，如果str中正好有两个的k个'0'字符出现时，把k个连续的'0'字符去除，返回处理后的字符串
 * 例如：str="A00B",k=2,返回"AB";str="A0000B000",k = 3,返回A0000B
 * @author zhangy
 *
 */
public class RemoveKZeros {
	/**
	 * 本题的关键是如何从左到右遍历str时，将正好有连续的k个'0'的字符串都找到，然后把字符'0'去掉。具体过程如下：
	 * 	1.生成两个变量。整型变量count，表示目前连续个'0'的数量；整型变量start，表示连续个'0'出现的开始位置。初始时，count=1，start = -1.
	 * 	2.从左到右遍历str,假设遍历到i位置的字符为cha，根据具体的cha有不同的处理。
	 * 	3.如果cha是字符'0',令start = start == -1 ? i : start,表示如果start等于-1，说明之前没处在发现连续的'0'的阶段，那么令start = i,表示
	 * 	连续的'0'从i位置开始，如果start不等于-1，说明之前就已经处在发现连续的'0'的阶段，所以start不变。令count++。
	 * 	4.如果cha不是字符'0'，是去掉连续'0'的时刻。首先看此时count是否等于k，如果等于说明之前发现的连续k个'0'可以从start位置开始去掉。如果不等于，
	 * 	说明之前发现的'0'数量不是k个，则不能去掉。最后令count = 0，start = -1。
	 * 	5.既然把去掉连续'0'的时机放在了cha不是字符'0'的时候，那么如果str是以字符'0'结尾的，可能会出现最后一组正好有连续的k个'0'字符出现而没有去掉
	 * 	的情况。所以遍历完成后，再检查一下count是否等于k，如果等于，就去掉最后一组连续的k个'0'。
	 */

	public String removeKZeros(String str, int k){
		if(str == null || k < 1){
			return str;
		}
		char[] ch = str.toCharArray();
		int count = 0;
		int start = -1;
		for(int i = 0; i < ch.length; i++){
			if(ch[i] == '0'){
				count++;
				start = start == -1 ? i : start;
			}else{//字符不为零的时候开始判断是否要删除一组0字符
				if(count == k){
					while(count-- != 0){
						ch[start++] = 0;//相当于把对应位置上的字符给删除了
					}
				}
				//删除了k个0字符后，需要重新设置初始值以便于统计下一组字符0的情况
				count = 0;
				start = -1;
			}
		}
		//如果字符串最后k个位置满足为'0'的情况，那么也需要另外再删除最后一组0字符
		if(count == k){
			while(count-- != 0){
				ch[start++] = 0;//相当于在对应位置设置了一个空字符
			}
		}
		return String.valueOf(ch);
	}
	public static void main(String[] args){
		RemoveKZeros rkz = new RemoveKZeros();
		String str = "A00B";
		//String str = "A0000B000";
		int k = 2;
		//ch[0] = 0;
//		while(count-- != 0){
//			ch[start++] = 0;
//		}
		System.out.println(rkz.removeKZeros(str, k));
	}
}
