import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[][] paper = new boolean[100][100]; // 도화지 크기 배열 생성
		int N = Integer.parseInt(br.readLine()); 
		int colored = 0; // 검정색으로 덮인 부분의 갯수
		
		for(int k = 0 ; k < N ; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int bottom = Integer.parseInt(st.nextToken()); // 색종이의 시작행
			int left = Integer.parseInt(st.nextToken()); // 색종이의 시작열
			
			// 색종이의 크기만큼 탐색
			for(int i = 0 ; i < 10 ; i++) {
				for(int j = 0 ; j < 10 ; j++) {
					paper[bottom][left] = true; // 색종이로 덮인 부분 true 처리
					left++; 
				}
				bottom++;
				left -= 10; // 다음 행 탐색해야 하므로
			}
		}
		
		// 도화지의 크기만큼 탐색
		for(int i = 0 ; i < 100 ; i++) {
			for(int j = 0 ; j < 100 ; j++) {
				if(paper[i][j]) colored += 1; // 검정색으로 덮인 부분 찾으면 1씩 더함
			}
		}
		
		// 결과 출력
		System.out.println(colored);
	}

}