import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, R; // 행크기, 열크기, 돌리는 횟수
	static int[][] map, boundary; // 값 넣는 배열, 경계 배열
	static int[][] deltas = {{0,1},{1,0},{0,-1},{-1,0}}; // 우 하 좌 상 순으로 당김

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		// 값 입력받기
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 잘 입력받았는지 확인
		//for(int[] a:map) System.out.println(Arrays.toString(a)); System.out.println();
		
		int depth = Math.min(N, M)/2; // 배열의 껍질? 개수
		boundary = new int[depth][4]; // 껍질별로 행최소, 행최대, 열최소, 열최대 값 넣음
		for(int dep = 0 ; dep < depth ; dep++) {
			boundary[dep][0] = 0 + dep; //행최소
			boundary[dep][1] = N - dep; //행최대
			boundary[dep][2] = 0 + dep; // 열최소
			boundary[dep][3] = M - dep; // 열최대
		}
		// 잘 계산했는지 확인
		//for(int[] a:boundary) System.out.println(Arrays.toString(a)); System.out.println();
		
		for(int r = 0 ; r < R ; r++) {
			for(int dep = 0 ; dep < depth; dep++) {
				rotate(dep);
			}
			
		}
		
		// 결과 출력하기
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		
		
	}

	private static void rotate(int dep) {
		int r = boundary[dep][0]; //시작행
		int c = boundary[dep][2]+1; // 시작열, 좌상단보다 하나 오른쪽에서 시작함.
		int temp = map[r][c]; // 처음 값 저장해놓기
		int d = 0;
		
		//다음 위치에 있는 값 당겨서 넣기
		while(d < 4) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];
			if(!isValid(nr,nc,dep)) {
				d++;
				continue; // 경계를 벗어난다면, 다음 방향으로
			}
			map[r][c] = map[nr][nc]; // 값 당겨오기
			r = nr; // 다음 위치 담기
			c = nc;
		}
		map[r][c] = temp; // 처음 값 담기
	}

	private static boolean isValid(int nr, int nc, int dep) {
		return nr>=boundary[dep][0] && nr<boundary[dep][1] && nc >= boundary[dep][2] && nc < boundary[dep][3];
	}

}