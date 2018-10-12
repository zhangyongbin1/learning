package chapter_5_stringproblem;
/**
 * 判断字符数组中是否所有的字符都只出现过一次：给定一个字符类型数组chas[]，判断chas[]中是否所有的字符都只出现过一次。
 * 举例：chas = ['a','b','c']返回true，chas=['1','2','1']返回false
 * @author zhangy
 *
 */
public class IsAllUnique {
	//复杂度为O(N)的方法
	public boolean isUnique1(char[] chas){
		if(chas == null){
			return true;
		}
		boolean[] map = new boolean[256];//数组的初始值为false
		for(int i = 0; i < chas.length; i++){
			if(map[chas[i]]){//如果chas[i]位置上的值为true,说明map中已经有该字符，所以再出现就不是第一次了
				return false;//直接返回false
			}
			map[chas[i]] = true;//第一次出现就将chas[i]字符值在map中的位置的值设置为true
		}
		return true;//遍历数组完了之后。都没有返回false，说明就都只出现一次，返回true
	}
	//复杂度为O(1)
	public boolean isUnique2(char[] chas){
		if(chas == null){
			return true;
		}
		heapSort(chas);
		for(int i = 1; i < chas.length; i++){
			if(chas[i] == chas[i-1]){
				return false;
			}
		}
		return true;
	}
	public void heapSort(char[] ch){
		for(int i = 0; i < ch.length; i++){
			buildMaxHeap(ch, i);
		}
		for(int i = ch.length - 1; i >= 0; i--){
			swap(ch, 0, i);
			heapify(ch, 0, i);
		}
	}
	public void buildMaxHeap(char[] ch,int i){//将一个数组建立成最大堆
		int parent = 0;
		while(i != 0){
			parent = (i - 1) / 2;
			if(ch[parent] < ch[i]){
				swap(ch, parent, i);
				i = parent;
			}else{
				break;
			}
		}
	}
	
	public void heapify(char[] ch, int i, int size){
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		int largest = i;
		while(left < size){
			if(ch[largest] < ch[left]){
				largest = left;
			}
			if(right < size && ch[largest] < ch[right]){
				largest = right;
			}
			if(largest != i){
				swap(ch, largest, i);
				i = largest;
			}else{
				break;
			}
		}
	}
	public void swap(char[] ch, int index, int end){//交换两个字符的值
		char temp = ch[index];
		ch[index] = ch[end];
		ch[end] = temp;
	}
}
