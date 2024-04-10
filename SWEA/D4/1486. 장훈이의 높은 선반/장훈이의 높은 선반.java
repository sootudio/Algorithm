import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, B, S; // 테스트케이스, 점원수, 선반높이, 점원키의합
	static int[] heights; // 점원들의 키
	static boolean[] isSelected; // 점원 고르는 배열
	static int minH;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T ; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 점원 수
			B = Integer.parseInt(st.nextToken()); // 선반높이
			heights = new int[N];
			isSelected = new boolean[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				heights[i] = Integer.parseInt(st.nextToken());
			}
			
			minH = Integer.MAX_VALUE; // 초기화
			select(0, 0);
			
			int result = minH - B;
			
			System.out.println("#"+tc+" "+result);
			
		}
		
	}

	private static void select(int idx, int sum) {
		// 가지치기: 이미 최소를 넘었다면 return 해버리기
		if(sum > minH) return;
		// 기저조건: 모든 점원들 선택이 끝났다면
		if(idx == N) {
			if(sum >= B)
				minH = sum;
			return;
		}
		// 유도파트: 직원들을 부분집합으로 고른다.
		isSelected[idx] = true;
		sum += heights[idx];
		select(idx+1, sum);
		isSelected[idx] = false;
		sum -= heights[idx];
		select(idx+1, sum);
	}

}