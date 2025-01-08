import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] deltas = {{-1,0}, {0,1}, {1,0}, {0, -1}}; // 상 우 하 좌
	static int[][][] dir = {
			null,
			{{0},{1},{2},{3}},
			{{0,2},{1,3}},
			{{0,1},{1,2},{2,3},{3,0}},
			{{0,1,2},{1,2,3},{2,3,0},{3,0,1}},
			{{0,1,2,3}}
	};
	
	static int N, M;
	static int[][] map;
	static int[][] copyMap;
	static List<CCTV> cctvs = new ArrayList<>();
	static int minBlindCnt;
	
	static class CCTV{
		int r, c;
		int number;
		int dirCnt = 4;
		int dir;
		public CCTV(int r, int c, int number) {
			this.r = r;
			this.c = c;
			this.number = number;
			if(number == 2) this.dirCnt = 2;
			else if(number == 5) this.dirCnt = 1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		copyMap = new int[N][M];
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j++) {
				int input = Integer.parseInt(st.nextToken());
				if(input != 0 && input != 6) {
					cctvs.add(new CCTV(i, j, input));
				}
				map[i][j] = input;
			}
		}
		
		minBlindCnt = Integer.MAX_VALUE;
		selectDir(0);
		
		System.out.println(minBlindCnt);

	}

	private static void selectDir(int idx) {
		if(idx == cctvs.size()) {
			arrayCopy();
			for(CCTV cctv : cctvs) {
				watch(cctv);
			}
			int curBlindCnt = getCurBlindCnt();
			minBlindCnt = Math.min(minBlindCnt, curBlindCnt);
			return;
		}
		CCTV cctv = cctvs.get(idx);
		
		for(int d=0 ; d < cctv.dirCnt ; d++) {
			cctv.dir = d;
			selectDir(idx+1);
		}
		
	}

	private static void watch(CCTV cctv) {
		for(int dir : dir[cctv.number][cctv.dir]) {
			int r = cctv.r;
			int c = cctv.c;
			while(true) {
				r += deltas[dir][0];
				c += deltas[dir][1];
				if(!isValid(r,c)) break;
				if(copyMap[r][c] == 6) break;
				copyMap[r][c] = -1;
			}
		}
	}

	private static int getCurBlindCnt() {
		int cnt = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(copyMap[i][j] == 0) cnt += 1;
			}
		}
		return cnt;
	}

	private static void arrayCopy() {
		for(int i = 0 ; i < N ; i++) {
			System.arraycopy(map[i], 0, copyMap[i], 0, M);
		}
	}
	
	private static boolean isValid(int r, int c) {
		if(r >= 0 && r < N && c >= 0 && c < M) return true;
		return false;
	}
	

}
