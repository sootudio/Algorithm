import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		int num3 = sc.nextInt();
		
		int[] nums = new int[3];
		nums[2] = Math.max(Math.max(num2, num1), num3);
		nums[0] = Math.min(Math.min(num2, num1), num3);
		
		for(int i = 0; i < 3; i++) {
			if(num1 != nums[2] && num1 != nums[0]) {
				nums[1] = num1;
			}
			else if(num2 != nums[2] && num2 != nums[0]) {
				nums[1] = num2;
			}
			else if(num3 != nums[2] && num3 != nums[0]) {
				nums[1] = num3;
			}
		}
		
		for(int i = 0; i < 3; i++) {
			System.out.print(nums[i]+" ");
		}
	}

}