package chapter_5_stringproblem;


/**
 * 字典数(前缀树)的实现：字典树又称为前缀数或Trie数，是处理字符串常见的数据结构。假设组成所有单词的字符仅是'a'~'z'，
 * 请实现字典树结构，并包含以下四个主要功能：
 * void insert(String word): 添加word,可重复添加
 * void delete(String word): 删除word,如果word添加过多次，仅删除一个
 * boolean search(String word):查询word是否在字典树中
 * int prefixNumber(String pre):返回以字符串pre为前缀的单词数量
 * @author zhangy
 *
 */
public class TrieTree {
	


	public static class TrieNode {//字典树结构类
		public int path;
		public int end;
		public TrieNode[] map;

		public TrieNode() {
			path = 0;
			end = 0;
			map = new TrieNode[26];//key代表该节点的一条字符路径，value代表字符路径指向的节点
		}
	}

	public static class Trie {
		private TrieNode root;

		public Trie() {
			root = new TrieNode();
		}

		public void insert(String word) {
			if (word == null) {
				return;
			}
			char[] chs = word.toCharArray();
			TrieNode node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {//遍历单词word,将每一个字符检查其是否在map集合中
				index = chs[i] - 'a';
				if (node.map[index] == null) {//如果不在，那么就新建一个节点
					node.map[index] = new TrieNode();
				}
				node = node.map[index];//如果存在
				node.path++;//那么就将path+1,path表示有多少个单词共用这个字符
			}
			node.end++;//end表多有多个单词以这个节点结尾
		}

		public void delete(String word) {//删除字典树中的一个节点
			if (search(word)) {
				char[] chs = word.toCharArray();
				TrieNode node = root;
				int index = 0;
				for (int i = 0; i < chs.length; i++) {
					index = chs[i] - 'a';
					if (node.map[index].path-- == 1) {
						node.map[index] = null;
						return;
					}
					node = node.map[index];
				}
				node.end--;
			}
		}

		public boolean search(String word) {
			if (word == null) {
				return false;
			}
			char[] chs = word.toCharArray();
			TrieNode node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.map[index] == null) {//说明字符在字典树中不存在
					return false;
				}
				node = node.map[index];//字符在字典树中存在
			}
			return node.end != 0;//end这个节点不为空字符
		}

		public int prefixNumber(String pre) {
			if (pre == null) {
				return 0;
			}
			char[] chs = pre.toCharArray();
			TrieNode node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.map[index] == null) {
					return 0;
				}
				node = node.map[index];
			}
			return node.path;
		}
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		System.out.println(trie.search("zuo"));
		trie.insert("zuo");
		System.out.println(trie.search("zuo"));
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.insert("zuo");
		trie.insert("zuo");
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.insert("zuoa");
		trie.insert("zuoac");
		trie.insert("zuoab");
		trie.insert("zuoad");
		trie.delete("zuoa");
		System.out.println(trie.search("zuoa"));
		System.out.println(trie.prefixNumber("zuo"));

	}
}
