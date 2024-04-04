import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nums = new int[5];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < 5 ; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int squareSum = 0;
		for(int i = 0 ; i < 5 ; i++) {
			squareSum += Math.pow(nums[i], 2); 
		}
		
		System.out.println(squareSum % 10);

	}

}