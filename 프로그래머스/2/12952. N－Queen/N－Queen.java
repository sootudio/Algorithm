import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	private static int[] board;
	private static int answer;

	public static int solution(int n) throws NumberFormatException, IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//int n = Integer.parseInt(br.readLine());
		
		// 배열의 값은 해당 행의 queen이 있는 '열(column)'을 의미함
		board = new int[n];
		
		backTracking(0, n);
		
		return answer;
		

	}

	// depth는 행을 의미함
	private static void backTracking(int depth, int n) {
		if(depth == n) {
			answer++;
			return;
		}
		for(int i = 0; i < n ; i++) {
			board[depth] = i; // 해당 depth(행)과 i(열)에 퀸을 놓을 수 있는지 확인
			if(valid(depth)) {
				backTracking(depth + 1, n);
			}
		}
	}

	private static boolean valid(int i) {
		for(int j = 0 ; j < i ; j++) { // 마지막으로 놓여진 것과 이전의 것들을 비교
			if(board[i] == board[j]) return false; // 같은 열에 놓였을 때, false
			if(Math.abs(i - j) == Math.abs(board[i] - board[j])) return false; // 같은 대각선에 놓였을때, false
		}
		return true;
	}

}
