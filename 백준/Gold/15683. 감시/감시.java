import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //상 우 하 좌
	
	// cctv의 번호에 맞게 관측 가능한 deltas값을 넣는다.
	// 때문에, 0번은 null
	static int[][][] dir = {
			null, // 0번
			{{0},{1},{2},{3}}, // 1번
			{{0,2},{1,3}}, // 2번
			{{0,1},{1,2},{2,3},{3,0}},// 3번
			{{0,1,2},{1,2,3},{2,3,0},{3,0,1}}, // 4번
			{{0,1,2,3}},// 5번
	};
	
	static int N, M; // 행크기, 열크기
	static int[][] map; // 초기 배열 정보 저장
	static int[][] copyMap; // 실제 감시영역, 사각지대 체크
	static List<CCTV> cctvs = new ArrayList<>();
	static int minBlindCnt; // 사각지대 최솟값 저장
	
	static class CCTV{
		int r, c; //행좌표, 열좌표
		int number; // cctv 번호
		
		int dirCnt = 4; // 변경해야할 방향의 개수(2,5번은 생성자에서 다시 설정)
		int dir; // 결정된(관측할 방향)
		
		public CCTV(int r, int c, int number) {
			this.r = r;
			this.c = c;
			this.number = number;
			if(number == 2) dirCnt = 2; // 상하, 좌우 두 방향밖에 없음
			else if(number == 5) dirCnt = 1; // 상하좌우 한 방향밖에 없음
		}
	}
			
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		copyMap = new int[N][M];
		
		// 값 입력받기
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j++) {
				int input = Integer.parseInt(st.nextToken());
				
				if(input != 0 && input != 6) { // cctv라면
					cctvs.add(new CCTV(i, j, input));
				}
				
				map[i][j] = input;
			}
		}
		
		minBlindCnt = Integer.MAX_VALUE;
		
		selectDir(0); // cctvs의 첫 번째 순서부터 selectDir에 넣는다.
		
		System.out.println(minBlindCnt);
		
	}

	private static void selectDir(int idx) {
		
		// 기저조건: 모든 cctv의 방향설정이 완료됐다면
		if(idx == cctvs.size()) {
			
			// copyMap 배열 초기화
			arrayCopy();
			
			// cctvs에서 cctv 객체를 하나씩 빼서 감시영역 표시
			for(CCTV cctv: cctvs) {
				watch(cctv);
			}
			
			int curBlindCnt = getCurBlindCnt(); // 사각지대 영역 계산

			minBlindCnt = Math.min(minBlindCnt, curBlindCnt); // 최솟값 갱신
			
			return;
		}
		// 유도파트
		CCTV cctv = cctvs.get(idx); // idx번째의 cctv 가져옴
		
		// cctv의 방향의 개수 만큼 반복
		// 즉, 2번은 2방향, 5번은 1방향, 나머지는 4방향으로 설정할 수 있음.
		for(int d = 0 ; d < cctv.dirCnt ; d++) {
			// 해당 cctv의 방향을 d로 설정하고, 재귀 호출
			cctv.dir = d; 
			selectDir(idx + 1);
		}
		
		
	}


	/**
	 * 감시 영역 체크하는 함수
	 * @param cctv 감시영역을 계산할 cctv
	 */
	private static void watch(CCTV cctv) {
		// cctv가 볼 수 있는 방향(delta 값) 들을 dir 변수에 뽑아옴
		for(int dir : dir[cctv.number][cctv.dir]) {
			// 해당 cctv의 좌표 가져옴
			int r = cctv.r;
			int c = cctv.c;
			
			// 영역 전개
			while(true) {
				// 선택된 방향으로 한칸씩 이동
				r += deltas[dir][0];
				c += deltas[dir][1];
				
				if(!isValid(r, c)) break; // 경계 체크
				if(copyMap[r][c] == 6) break; // 벽 체크
				
				// 해당 조건들이 아니라면, 감시될 수 있는 영역이므로
				// -1로 감시된 영역을 표시해준다.
				copyMap[r][c] = -1;
			}
		}
		
	}
	
	
	/**
	 * 사각지대 개수 세는 함수
	 * @return 사각지대 영역의 크기
	 */
	private static int getCurBlindCnt() {
		int cnt = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				// 감시영역 표시가 끝났는데, 0이라면 사각지대라는 뜻이다.
				if(copyMap[i][j] == 0) cnt++;
			}
		}
		return cnt;
	}


	/**
	 * copyMap을 초기화
	 */
	private static void arrayCopy() {
		
		for(int i = 0 ; i < N ; i++) {
			System.arraycopy(map[i], 0, copyMap[i], 0, M);
		}
		
	}
	
	/**
	 * 경계체크 함수
	 * @param r 행좌표
	 * @param c 열좌표
	 * @return 경계를 벗어났는지 여부
	 */
	private static boolean isValid(int r, int c) {
		if(r >= 0 && r < N && c >=0 && c < M) return true;
		return false;
	}

}