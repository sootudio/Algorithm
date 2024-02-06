

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N; // 숫자의 개수
	static int M; // 최대로 나올 수 있는 수
	static int maxNum = 0; // 최대 합을 담아 출력할 변수
	static int[] nums; //숫자가 들어가는 배열
	static int num; // 숫자를 뽑을 때, 현재 숫자.

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 숫자의 갯수와 최대값을 입력받음
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N];
		
		// 배열에 숫자들을 넣음.
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		// 숫자들을 조합해 최댓값을 구하는 함수에 넣는다.
		SumOfNums(0, 0 ,0);
		
		// 최댓값을 반환
		System.out.println(maxNum);

	}
	
	// cnt: 뽑은 횟수, idx: nums의 인덱스, sum: 숫자의 총합
	public static void SumOfNums(int cnt, int idx, int sum) {
		// 이미 넘어야 하는 숫자를 넘었으면 더 할 필요도 없다.
		if(sum > M) return;
		// 숫자 3개 뽑았으면
		if(cnt == 3) {
			// 최댓값 구해서 저장
			maxNum = Math.max(sum, maxNum);
			return;
		}
		for(int i = idx ; i < N ; i++) {
			// 숫자 하나 선택
			num = nums[i];
			// 다음 숫자 선택
			SumOfNums(cnt+1, i+1, sum+nums[i]);
		}
		
	}

}