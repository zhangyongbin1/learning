package chapter_4_recursionanddp;
/**
 * 汉诺塔问题：给定一个整数n，代表汉诺塔游戏中从小到大放置的n个圆盘，假设开始时所有的圆盘都放在左边的柱子上，
 * 想按照汉诺塔游戏的要求把所有的圆盘都移动到右边的柱子上。实现函数打印最优移动轨迹。
 * 例如：
 * 		n = 1时，打印： move from left to right
 * 		n = 2时，打印：move from left to mid;move from left to right; move from mid to right  一次行可以直接从left to right
 * @author zhangy
 *
 */
public class HanoiProblem {
	public void hanoi(int n){
		if( n > 0){
			func(n, "left", "mid", "right");
		}
	}
	public void func(int n, String from, String mid, String to){//递归函数
		if(n == 1){//如果当前n为1,那么就打印从from 到 to
			System.out.println("move from "+from + " to "+to);
		}else{
			func(n-1, from, to, mid);//把1...n-1个盘子移动到中间(其实这是最后一步执行的代码，即：倒数第二个盘子从left 到 mid)
			func(1,from, mid, to);//把最后一个盘子n移动到right(最后一个盘子n从left to right)
			func(n-1, mid, from, to);//把中间的n-1个盘子移动到right(处于中间的n-1个盘子的最后一步是：最后一个盘子n-1从mid 到 right)
		}
	}
	
	public static void main(String[] args){
		int n = 3;
		HanoiProblem hp = new HanoiProblem();
		hp.hanoi(n);
	}

}
