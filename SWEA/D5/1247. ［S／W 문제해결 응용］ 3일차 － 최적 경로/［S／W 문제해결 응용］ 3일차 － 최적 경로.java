
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int T; // 테스트케이스 수
	static int N; // 고객의 수
	static int[] office = new int[2]; // 회사 좌표
	static int[] home = new int[2]; // 집 좌표
	static int[][] customers ; // 고객 좌표들
	static int[][] visitedCt; // 고른 고객 좌표들
	static int shortcut = 1_000_000; // 최단경로
	static boolean isSelected[] ; // 고객이 선택되었는지
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		// 테스트 케이스의 수 만큼 반복
		for(int tc = 1 ; tc <= T ; tc++) {
			N = Integer.parseInt(br.readLine());
			customers = new int[N][2];
			isSelected = new boolean[N];
			visitedCt = new int[N][2];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 회사 좌표 입력받기
			office[0] = Integer.parseInt(st.nextToken());
			office[1] = Integer.parseInt(st.nextToken());
			
			// 집 좌표 입력받기
			home[0] = Integer.parseInt(st.nextToken());
			home[1] = Integer.parseInt(st.nextToken());
			
			// 고객의 수 만큼 고객 좌표 입력받기
			for(int i = 0 ; i < N ; i++) {
				customers[i][0] = Integer.parseInt(st.nextToken());
				customers[i][1] = Integer.parseInt(st.nextToken());
			}
			
			// 고객 선택하는 순열 실행
			permu(0);
			
			// 결과 출력
			System.out.println("#"+tc+" "+shortcut);
			
			// 최단경로 초기화
			shortcut = 1_000_000;
			
		}
		
	}
	
	public static void permu(int idx) {
		// 모든 고객을 다 방문해야지 끝남
		if(idx == N) {
			// 회사에서 첫번째 고객까지의 거리 계산
			int officeD = Math.abs(office[0]- visitedCt[0][0]) + Math.abs(office[1]- visitedCt[0][1]);
			
			// 마지막 고객에서 집까지의 거리 계산
			int homeD = Math.abs(home[0]- visitedCt[N-1][0]) + Math.abs(home[1]- visitedCt[N-1][1]);
			
			// 현재 고객 순열의 거리를 계산할 변수 선언
			int curD = officeD + homeD;
			
			// 고객의 순서대로 다음 고객과의 거리 계산
			for(int i = 0 ; i < N-1 ; i++) {
				curD += Math.abs(visitedCt[i][0]- visitedCt[i+1][0]) + Math.abs(visitedCt[i][1]- visitedCt[i+1][1]);
			}
			
			// 최단경로 저장
			shortcut = Math.min(shortcut, curD);
			return;
		}
		for(int i = 0 ; i < N ; i++) {
			if(isSelected[i]) continue;
			// 방문 고객의 x좌표 저장
			visitedCt[idx][0] = customers[i][0];
			// 방문 고객의 y좌표 저장
			visitedCt[idx][1] = customers[i][1];
			// 해당 고객 방문처리
			isSelected[i] = true;
			
			// 다음 방문 고객 정하러 재귀 호출
			permu(idx+1);
			
			// 해당 고객 방문하지 않음 처리
			isSelected[i] = false;
		}
	}
}