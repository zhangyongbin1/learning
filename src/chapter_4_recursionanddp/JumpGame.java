package chapter_4_recursionanddp;
/**
 * 跳跃游戏：给定数组arr,arr[i] == k 代表可以从位置i向右跳1~k个距离。比如，arr[2] = 3,代表从位置2可以跳到位置3、位置4、或位置5.
 * 如果从位置0出发，返回最少跳几次能跳到arr的最后的位置上。例如：arr=[3,2,3,1,1,4]，arr[0] = 3,选择跳到位置2；arr[2] ==3,可以跳到最后的位置，所以返回2次
 * @author zhangy
 *
 */
public class JumpGame {
	/**
	 * 1.整型变量jump,代表目前跳了多少步。整型变量cur，代表如果只能跳jump步，最远能够达到的位置。整型变量next,代表如果再多跳一步，
	 * 最远能够达到的位置。初始时，jump = 0, cur = 0,next = 0;
	 * 2.从左到右遍历arr,假设遍历到位置i:
	 * 	(1).如果cur >= i,说明跳jump步可以到达位置i，此时什么也不做。
	 * 	(2).如果cur < i,说明只跳jump步不能到达位置i，需要多跳一步才行。此时令jump++,cur = next.表示多跳楼一步，cur更新成跳jump + 1步能够达到的位置，即next
	 * 	(3).将next更新成math.max(next,i+arr[i])，表示下一次多跳一步到达的最远位置
	 * 3.最终返回jump即可
	 */

	public int jump(int[] arr){
		if(arr == null || arr.length == 0){
			return 0;
		}
		int jump = 0;
		int cur = 0;
		int next = 0;
		for(int i = 0; i < arr.length; i++){
			if(cur < i){//如果当前cur位置小于i,说明只跳jump无法到达位置i,
				jump++;//所以需要再跳一次
				cur = next;//更新当前所处的位置
			}
			next = Math.max(next, i + arr[i]);//下一次能跳到最远的位置
		}
		return jump;
	}
	public static void main(String[] args){
		JumpGame jg = new JumpGame();
		int[] arr = {3,2,3,1,1,4};
		System.out.println(jg.jump(arr));
	}
}
