package chapter1_stackAndqueue;

import java.util.Stack;

/**
 * 1:不能违反小压大的原则； 2：相邻不可逆原则：就是说第一步走了LToM,下一步就不能走MToL了，没有意义。
 * 由以上两个原则可以推导出以下两个重要的约束条件： a:游戏的第一个动作一定是LToM,这是显而易见的
 * b:在走出最少步数的过程中的任何时刻，四个动作中只有一个动作是符合以上两个原则的
 * 
 * @author zhangy
 *
 */
public class HanoiStack {
	public enum Action {
		No, LToM, MToL, MToR, RToM
	};

	//使用递归的方法解汉诺塔的问题:左边————右边
	public static int hanoiProblem2(int num,String left,String mid,String right){
		if(num < 1){
			return 0;
		}
		return process(num,left,mid,right,left,right);
		
	}
	public static int process(int num,String left,String mid,String right,String from,String to){
		//所有的都分两种情况，一种是只需要跨越一个格子到达目的地，另一中是需要跨越两个格子到达目的地
		//递归就是关注终止条件：分析最上一层汉诺塔需要移动的所有可能情况
		if(num == 1){//终止条件就是移动最上层汉诺塔(即汉诺塔1)一个塔时所有可能的情况
			if(from.equals(mid) || to.equals(mid)){//只需要跨越一个格子就能到达目的地
				System.out.println("Move 1 from "+from+" to "+to);
				return 1;
			}else{//左——>右 或者 右——>左，因为题目规定每一次只能跨越一个格子，所以需要两步
				System.out.println("Move 1 from "+from+" to "+mid);
				System.out.println("Move 1 from "+mid+" to "+ to);
				return 2;
			}
			//允许一步跨越两个格子
			/*System.out.println("Move 1 from "+from+" to "+to);
			return 1;*/
		}
		if(from.equals(mid) || to.equals(mid)){//需要跨越一个格子就能到达目的地，例如：全部在左，需要全部搬移到中； (1~N-1)左——>右；(N)左——中；(1~N-1)右——中
			String another = from.equals(left) || to.equals(left) ? right:left;//知道from和to的具体位置之后，需要确定另外一格子是left还是right
			int part1 = process(num-1,left,mid,right,from,another);//将1～N-1个格子从左移动到右，关键是看最后两个参数就表明当前是从那个位置(from)到那个位置(to)
			int part2 = 1;//中间这一步肯定是将最后一个格子从左--->中，只需要一个步
			System.out.println("Move "+num+" from "+from+" to "+to);//打印格子移动的轨迹
			int part3 = process(num-1,left,mid,right,another,from);//然后是将此时位于右边的1～N-1个格子全部移动到中
			return part1+part2+part3;//三步加起来的总和就是最少的移动步数
		}else{//这是对需要跨越两个格子的处理，例如：全部在左，需要全部搬移到右，总共分为5个部分：num-1——>右；第N个——>中；num-1个从右——>左；第N个从中——>右；num-1个从左——>右
			int part1 = process(num-1,left,mid,right,from,to);//将1～N-1个格子从左移动到右边，使用递归
			int part2 = 1;//将第N个格子从左移动到中，需要一步
			System.out.println("Move "+num+" from "+from+" to "+ mid);//打印格子的移动轨迹
			int part3 = process(num-1,left,mid,right,to,from);//将1～N-1个格子从此时的右移动到左，这也是同样的递归处理过程
			int part4 = 1;//将位置中的第N个格子移动到右，只需要一步
			System.out.println("Move "+num+" from "+mid +" to "+to);//打印移动轨迹
			int part5 = process(num-1,left,mid,right,from,to);//此时需要将位置左的1～N-1个格子移动到右边完成所有格子从左到右的跨越
			return part1+part2+part3+part4+part5;//5个步骤移动的总数为最小移动步数
		}
	}
	public static int hanoiProblem(int num,String left,String mid,String right){
		Stack<Integer> lS = new Stack<Integer>();
		Stack<Integer> mS = new Stack<Integer>();
		Stack<Integer> rS = new Stack<Integer>();
		lS.push(Integer.MAX_VALUE);
		mS.push(Integer.MAX_VALUE);
		rS.push(Integer.MAX_VALUE);
		for(int i = num; i>0; i--){
			lS.push(i);
		}
		Action[] record = {Action.No};
		int step = 0;
		while(rS.size() < num+1){
			step += fStackToStack(record,Action.MToL,Action.LToM,lS,mS,left,mid);
			step += fStackToStack(record,Action.LToM,Action.MToL,mS,lS,mid,left);
			step += fStackToStack(record,Action.RToM,Action.MToR,mS,rS,mid,right);
			step += fStackToStack(record,Action.MToR,Action.RToM,rS,mS,right,mid);
		}
		return step;
	}
	//每走一步的函数
	public static int fStackToStack(Action[] record, Action preNoAct, Action nowAct, Stack<Integer> fStack,
			Stack<Integer> tStack, String from, String to) {
		if(record[0] != preNoAct && fStack.peek() < tStack.peek()) {//只要保证小压大原则和不可逆原则即可：前一步是LToM,后一步不能是MToL
			tStack.push(fStack.pop());
			System.out.println("Move " + fStack.peek() + " from " + from + " to " + to);
			record[0] = nowAct;
			return 1;
		}
		return 0;
	}
	
	public static void main(String[] args){
		int num = 4;
		int step = hanoiProblem(num,"left","mid","right");
		System.out.println("It will move "+step+" steps");
		System.out.println("--------------------------------------");
		int step2 = hanoiProblem2(num,"left","mid","right");
		System.out.println("It will move "+step2+" steps");
	}

}
