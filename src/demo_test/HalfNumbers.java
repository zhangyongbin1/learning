package demo_test;

import org.junit.Test;
//找出一个数组中出现次数超过一半的数
public class HalfNumbers {
	public int majorityElement(int[] nums){
        int Value = nums[0] ;  
        int count = 1 ;  
           for (int i = 1; i < nums.length; ++i)  
           {  
               if (nums[i] == Value)  
                   count++ ;  
               else  
               {  
                   count-- ;  
                   if (count < 0)  
                   {  
                       Value = nums[i] ;  
                       count = 1 ;  
                   }  
               }  
           }  
     
       return Value ; 
    }
	public int halfNumbers2(int[] arr){
		if(arr.length < 1 || arr == null){
			return 0;
		}
		int num = arr[0];
		int count = 1;
		for(int i = 1; i < arr.length; i++){
			if(count == 0){
				num = arr[i];
				count = 1;
			}else{
				if(arr[i] == num){
					count++;
				}else{
					count--;
				}
			}
		}
		//如果数组中没有超过一半的数的话，num可能是最后一个数，所以如果需要，则还需要去判断num是否真的是超过一个半的那个数
		return num;
	}
	@Test
	public void test(){
		int[] arr = {1,2,3,2,4,2,2,3,2};
		System.out.println(halfNumbers2(arr));
	}
}
