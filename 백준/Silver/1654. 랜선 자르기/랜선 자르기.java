import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static int K; // 가지고 있는 랜선의 개수
	static int N; // 만들어야 하는 랜선의 개수
	static int[] lines; // 처음 가지고 있는 랜선들의 길이
	static long mid = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		K = sc.nextInt();
		N = sc.nextInt();
		lines = new int[K];
		
		int totalLen = 0;
		
		for(int i = 0 ; i < K ; i++) {
			lines[i] = sc.nextInt();
			totalLen += lines[i];
		}
		
		mid = totalLen / N;
		
		Arrays.sort(lines);
		
		long length = binsearch();
		
		System.out.println(length);
		
	}

	private static long binsearch() {
		long start = 1;
		long end = lines[K-1];
		long maxLen = 0; 
		
		while(start <= end) {
			mid = (start + end) / 2;
			long cnt = 0;
			for(int i = 0 ; i < K ; i++) {
				cnt += lines[i] / mid;
			}
			
			if(cnt >= N) {
				maxLen = mid;
				start = mid + 1;
			}
			
			else{
				end = mid-1;
			}
		}
		return maxLen;
	}
	
	

}