package chapter3_TreeProblem;
/**
 * 给定彼此独立的两颗树头节点分别为t1和t2，判断t1中是否有与t2树拓扑结构完全相同的子树（复杂度为O(N+M)）,使用KMP算法
 * 1.现将二叉树序列化成字符串
 * 2.使用KMP算法去判断字符串t1Str是否完整包含字符串t2Str
 * @author zhangy
 *
 */
public class T1SubtreeEqualsT2 {
	public class Node{
		public int value;
		public Node left;
		public Node right;
		public Node(int data){
			this.value = data;
		}
	}
	public boolean isSubTree(Node t1, Node t2){
		String t1Str = serialBinaryTree(t1);
		String t2Str = serialBinaryTree(t2);
		return getIndexOf(t1Str, t2Str) != -1;
	}
	//使用先序遍历进行二叉树的序列化
	public String serialBinaryTree(Node head){
		if(head == null){
			return "#!";
		}
		String res = head.value + "!";
		res += serialBinaryTree(head.left);
		res += serialBinaryTree(head.right);
		return res;
	}
	//KMP
	public int getIndexOf(String s, String m){
		if(s == null || m == null || m.length() < 1 || s.length() < m.length()){
			return -1;//字符串s中不包含字符串m,所以返回-1
		}
		//先将两个字符串转换成字符串数组
		char[] ss = s.toCharArray();
		char[] ms = m.toCharArray();
		int si = 0;
		int mi = 0;
		int[] next = getNextArray(ms);//获取需要匹配字符串对应的next数组
		while(si < ss.length && mi < ms.length){//只有两个变量变量都小于两个字符串的长度，就一直循环比较
			if(ss[si] == ms[mi]){//如果当前两个字符相等，那么两个字字符串同时往下移动
				si++;
				mi++;
			}else if(next[mi] == -1){//如果match字符串当前的位置是第一个字符，即mi = 0,那么说明两个字符串的第一个位置就没有匹配上
				si++;//所以大字符串往下移动以字符，然后继续比较
			}else{//如果当前match字符串的位置不是第一个字符
				mi = next[mi];//那么match字符串向右移动next[mi]个位置，然后继续比较
			}
		}
		return mi == ms.length ? si - mi : -1;//返回遍历到match字符串的最后一个位置，那么起始位置为si-mi,否则就返回-1
	}

	//获取KMP算法的next的数组
	public int[] getNextArray(char[] ms){
		if(ms.length == 1){
			return new int[] {-1};
		}
		int[] next = new int[ms.length];
		next[0] = -1;//开始位置设置为-1
		next[1] = 0;//第一个字符的最长前缀和后缀为0
		int pos = 2;//循环遍历match字符串数组的当前位置
		int cn = 0;//
		while(pos < next.length){//pos变量为当前的mathc字符串中的位置
			if(ms[pos - 1] == ms[cn]){//当期字符串的前一个位置字符与其对应最长前缀的后一个字符相等，那么就当前字符的最长前后缀的长度就等于
				next[pos++] = ++cn;//前一个字符最长前后缀的值cn + 1
			}else if(cn > 0){
				cn = next[cn];//表示前一个字符串的前缀部分当作字符串数组继续与前一个字符串进行比较
			}else{//说明当前位置的字符串不存在最长前后缀，所以设置为0，当前cn <= 0了，所以不存在最大前后缀
				next[pos++] = 0;
			}
		}
		return next;
	}
	public static void main(String[] args){
		T1SubtreeEqualsT2 t1et2 = new T1SubtreeEqualsT2();
		Node head = t1et2.new Node(1);
		head.left = t1et2.new Node(2);
		head.right = t1et2.new Node(3);
		head.left.left = t1et2.new Node(4);
		head.left.right = t1et2.new Node(5);
		head.left.left.right = t1et2.new Node(8);
		head.left.right.left = t1et2.new Node(9);
		head.right.left = t1et2.new Node(6);
		head.right.right = t1et2.new Node(7);
		
		Node head2 = t1et2.new Node(2);
		head2.left = t1et2.new Node(4);
		head2.left.right = t1et2.new Node(8);
		head2.right = t1et2.new Node(5);
		//head2.right.left = t1et2.new Node(9);
		System.out.println("t1 is contains t2: "+t1et2.isSubTree(head, head2));
	}
}
