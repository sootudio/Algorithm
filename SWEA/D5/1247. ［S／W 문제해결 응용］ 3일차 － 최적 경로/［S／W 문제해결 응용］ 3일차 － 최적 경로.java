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
	static boolean isSelected[] ;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int tc = 1 ; tc <= T ; tc++) {
			N = Integer.parseInt(br.readLine());
			customers = new int[N][2];
			isSelected = new boolean[N];
			visitedCt = new int[N][2];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			office[0] = Integer.parseInt(st.nextToken());
			office[1] = Integer.parseInt(st.nextToken());
			home[0] = Integer.parseInt(st.nextToken());
			home[1] = Integer.parseInt(st.nextToken());
			
			for(int i = 0 ; i < N ; i++) {
				customers[i][0] = Integer.parseInt(st.nextToken());
				customers[i][1] = Integer.parseInt(st.nextToken());
			}
			
			permu(0);
			
			System.out.println("#"+tc+" "+shortcut);
			
			shortcut = 1_000_000;
			
		}
		
	}
	
	public static void permu(int idx) {
		if(idx == N) {
			int officeD = Math.abs(office[0]- visitedCt[0][0]) + Math.abs(office[1]- visitedCt[0][1]);
			int homeD = Math.abs(home[0]- visitedCt[N-1][0]) + Math.abs(home[1]- visitedCt[N-1][1]);
			int curD = officeD + homeD;
			for(int i = 0 ; i < N-1 ; i++) {
				curD += Math.abs(visitedCt[i][0]- visitedCt[i+1][0]) + Math.abs(visitedCt[i][1]- visitedCt[i+1][1]);
			}
			shortcut = Math.min(shortcut, curD);
			return;
		}
		for(int i = 0 ; i < N ; i++) {
			if(isSelected[i]) continue;
			visitedCt[idx][0] = customers[i][0];
			visitedCt[idx][1] = customers[i][1];
			isSelected[i] = true;
			
			permu(idx+1);
			
			isSelected[i] = false;
		}
	}
}