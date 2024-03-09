import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		int[] nums = new int[3];
		int maxNum = 0;
		int sumOfOtherNum = 0;
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			num1 = Integer.parseInt(st.nextToken());
			num2 = Integer.parseInt(st.nextToken());
			num3 = Integer.parseInt(st.nextToken());
			
			if(num1 == 0 && num2 == 0 && num3 == 0) break;
			
			maxNum = Math.max(num1, Math.max(num2, num3));
			
			nums[0] = num1;
			nums[1] = num2;
			nums[2] = num3;
			
			for(int i = 0 ; i < 3 ; i++) {
				if(nums[i] != maxNum)
					sumOfOtherNum += nums[i]*nums[i];
			}
			
			maxNum = maxNum * maxNum;
			if(sumOfOtherNum == maxNum)
				System.out.println("right");
			else
				System.out.println("wrong");
			
			sumOfOtherNum = 0;
		}
	}

}