import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N; // 도시 가로/세로 길이
	static int M; // 남길 치킨집 개수
	static int[][] city; // 도시 행렬
	static int homeNum = 0; // 집의 개수
	static int[][] homes; //집들의 위치
	static int chickNum = 0; // 치킨집 개수
	static int[][] chicks; // 치킨집들의 위치
	static int[] chickRemain; // 남은 치킨집들 번호
	static int minChickLength = 100000; // 최소 치킨 거리
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		city = new int[N][N];
		homes = new int[2*N][2];
		chicks = new int[13][2];
		chickRemain = new int[M];
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				int place = Integer.parseInt(st.nextToken());
				if(place == 1) {
					homes[homeNum][0] = i;
					homes[homeNum][1] = j;
					homeNum ++;
				}
				else if(place == 2) {
					chicks[chickNum][0] = i;
					chicks[chickNum][1] = j;
					chickNum ++;
				}
				city[i][j] = place;
			}
		}
		
		minChickLength = 2*N*homeNum;
		combi_chicken(0, 0);
		System.out.println(minChickLength);
	}

	private static void combi_chicken(int idx, int cnt) {
		if(cnt == M) {
			int curCityChickLength = 0;
			int curHomeChickLength = 0;
			int minHomeChickLength = 10000;
			for(int i =0 ; i < homeNum ; i++) {
				for(int j = 0 ; j < M ; j++) {
					curHomeChickLength = Math.abs(homes[i][0]-chicks[chickRemain[j]][0]) + Math.abs(homes[i][1]-chicks[chickRemain[j]][1]);
					minHomeChickLength = Math.min(minHomeChickLength, curHomeChickLength);
				}
				curCityChickLength += minHomeChickLength;
				minHomeChickLength = 10000;
			}
			minChickLength = Math.min(curCityChickLength, minChickLength);
			curCityChickLength = 0;
			return;
		}
		for(int i=idx ; i < chickNum ; i++) {
			chickRemain[cnt] = i;
			combi_chicken(i+1, cnt+1);
		}
	}

}