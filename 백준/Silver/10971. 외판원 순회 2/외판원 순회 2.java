import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; // 도시 개수
	static int[][] costs; // 여행 비용을 넣는 배열
	static int[] travel; //고른 도시들의 순서를 넣는 배열
	static boolean[] isSelected; // 방문체크하는 배열
	static int minCost = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		costs = new int[N][N];
		travel = new int[N];
		isSelected = new boolean[N];
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				costs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		permu(0);
		
		System.out.println(minCost);
		
	}

	private static void permu(int cnt) {
		if(cnt == N) {
			int curCost = 0;
			for(int i = 0 ; i < N ; i++) {
				if(i == (N -1)) {
					if(costs[travel[N-1]][travel[0]] == 0) {
						curCost = Integer.MAX_VALUE;
						break;
					}
					curCost += costs[travel[N-1]][travel[0]];
				}
				else {
					if(costs[travel[i]][travel[i+1]] == 0) {
						curCost = Integer.MAX_VALUE;
						break;
					}
					curCost += costs[travel[i]][travel[i+1]];
				}
				
			}
			minCost = Math.min(curCost, minCost);
			return;
		}
		for(int i = 0 ; i < N ; i++) {
			if(isSelected[i]) continue;
			travel[cnt] = i;
			isSelected[i] = true;
			permu(cnt + 1);
			isSelected[i] = false;
		}
	}

}