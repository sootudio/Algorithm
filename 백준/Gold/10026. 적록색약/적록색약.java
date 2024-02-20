import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; // 그림의 넓이, 높이
	static char[][] painting; // 그림 배열
	static int[][] deltas = {{-1, 0},{0, 1},{1, 0},{0, -1}};
	static int colorBlindSectionCount = 0;
	static int nonColorBlindSectionCount = 0;
	static boolean[][] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		painting = new char[N][N];
		isVisited = new boolean[N][N];

		for(int i = 0 ; i < N ; i++) {
			char[] charArr = br.readLine().toCharArray();
			for(int j = 0; j< N ; j++) {
				painting[i][j] = charArr[j];
			}
		}

		// 적록색약인 경우
		for(int i = 0 ; i< N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(!isVisited[i][j]) {
					isVisited[i][j] = true;
					countSection(i, j, true);
					colorBlindSectionCount++;
//					for(int k = 0 ; k < N ; k ++) {
//						for(int l =0 ; l < N ; l++) {
//							if(isVisited[k][l]) System.out.print(colorBlindSectionCount+" ");
//							else System.out.print("0 ");
//						}
//						System.out.println();
//					}
//					System.out.println("==============");
				}
			}
		}

		isVisited = new boolean[N][N];

		// 적록색약이 아닌 경우
		for(int i = 0 ; i< N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(!isVisited[i][j]) {
					isVisited[i][j] = true;
					countSection(i, j, false);
					nonColorBlindSectionCount++;
//					for(int k = 0 ; k < N ; k ++) {
//						for(int l =0 ; l < N ; l++) {
//							if(isVisited[k][l]) System.out.print(nonColorBlindSectionCount+" ");
//							else System.out.print("0 ");
//						}
//						System.out.println();
//					}
				}
			}
		}

		System.out.println(nonColorBlindSectionCount + " "+ colorBlindSectionCount);
	}


	private static void countSection(int i, int j, boolean isColorBlind) {
		if(isColorBlind) {
			int r = i;
			int c = j;

			for(int k = 0 ; k < 4 ; k++) {
				int nr = r + deltas[k][0];
				int nc = c + deltas[k][1];
				if(nr < 0 || nr >=N || nc < 0 || nc >=N) continue;
				if(isVisited[nr][nc]) continue;
				if(painting[r][c] == painting[nr][nc]) {
					isVisited[nr][nc] = true;
					countSection(nr, nc, true);
				}
				else if (painting[r][c] == 'R' && painting[nr][nc] == 'G') {
					isVisited[nr][nc] = true;
					countSection(nr, nc, true);
				}
				else if (painting[r][c] == 'G' && painting[nr][nc] == 'R') {
					isVisited[nr][nc] = true;
					countSection(nr, nc, true);
				}
			}
			
		}
		
		else {
			int r = i;
			int c = j;
			
			for(int k = 0 ; k < 4 ; k++) {
				int nr = r + deltas[k][0];
				int nc = c + deltas[k][1];
				if(nr < 0 || nr >=N || nc < 0 || nc >=N) continue;
				if(isVisited[nr][nc]) continue;
				if(painting[r][c] == painting[nr][nc]) {
					isVisited[nr][nc] = true;
					countSection(nr, nc, false);
				}
			}
			
		}

	}


}