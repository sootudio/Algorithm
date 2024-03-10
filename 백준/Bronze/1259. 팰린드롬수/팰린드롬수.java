import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N; //숫자의 길이
		int[] nums;
		
		
		while(true) {
			boolean isNotPalindrome = false;
			String string = br.readLine();
			
			if(Integer.parseInt(string) == 0) break;
			
			N = string.length();
			nums = new int[N];
			
			
			
			for(int i = 0 ; i < N ; i++) {
				nums[i] = string.charAt(i);
			}
			
			for(int i = 0 ; i < N ; i++) {
				if(nums[i] != nums[N - i - 1]) {
					isNotPalindrome = true;
				}
			}
			
			if(isNotPalindrome) System.out.println("no");
			else System.out.println("yes");
		}
	}

}