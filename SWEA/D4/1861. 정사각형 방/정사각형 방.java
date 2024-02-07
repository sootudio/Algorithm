import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int T; // 테스트케이스 수
	static int N; // 방의 개수의 제곱근
	static int[][] rooms; // 방들의 숫자가 들어있는 배열
	static int[][] deltas = {{-1, 0},{0, -1},{1,0},{0,1}}; // 상, 좌, 하, 우 방향으로 한칸씩 이동
	static int[] curRoom = new int[2]; // 현재 방의 번호와 이동거리
	static int[] longest = new int[2]; // 이동거리가 가장 긴 방의 시작번호와 이동거리
	static boolean moveable ; // 현재 위치에서 더 이동할 수 있는지 확인

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int tc= 1 ; tc <= T ; tc++) {
			N = Integer.parseInt(br.readLine());
			
			// 방 배열에 각각의 숫자를 넣어줌
			rooms = new int[N][N];
			for(int i = 0 ; i < N ; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j ++) {
					rooms[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// longest를 구하는 함수 호출
			move();
			
			// longest의 시작 방 번호와 이동 거리 출력
			System.out.println("#"+tc+" "+longest[0]+" "+longest[1]);
			
			// 다음 tc에서 사용해야 하므로 longest 초기화
			longest[0] = 0;
			longest[1] = 0;
		}
	}
	
	private static void move() {
		// 배열에 하나씩 접근해서 이동여부 파악
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				
				// 현재 방의 번호 저장
				curRoom[0] = rooms[i][j];
				
				// 이동거리 1로 초기화 (방 n개를 지나면 n이다)
				curRoom[1] = 1;
				
				// 현재 좌표 저장
				int idxI = i;
				int idxJ = j;
				
				// 새로 이동할 좌표를 담을 변수 선언
				int nx = 0;
				int ny = 0;
				
				
				while(true) {
					// 상하좌우를 탐색
					for(int k = 0 ; k < 4 ; k++) {
						// 현재 위치에 상, 하, 좌, 우 중 하나를 더한 값
						nx = idxI +  deltas[k][0];
						ny = idxJ + deltas[k][1];
						
						// 만약 경계를 벗어나지 않고, 다음 방의 숫자가 현재 방의 숫자보다 1 크면 
						if(nx >= 0 && nx < N && ny >=0 && ny <N && rooms[idxI][idxJ] - rooms[nx][ny] == -1) {
							// 이동거리 + 1
							curRoom[1] += 1;
							// 반복문 계속하기 위해
							moveable = true;
							// 좌표를 이동할 좌표로 변경
							idxI = nx;
							idxJ = ny;
							// k=0부터 시작해야 하므로
							k = -1;
						}
					}
					// 상하좌우 모두 조건에 맞지 않으면 더이상 이동할 곳이 없는 것이므로 반복문 탈출
					if(!moveable) break;
					// moveable 초기화
					moveable = false;
				}
				// 현재 방의 이동 거리가 제일 길다면, longest 값 변경
				if(curRoom[1] > longest[1]) {
					longest[0] = curRoom[0];
					longest[1] = curRoom[1];
				}
				// 이동거리가 같다면, 시작지점이 더 작은 것으로 변경
				else if(curRoom[1] == longest[1])
					longest[0] = Math.min(curRoom[0], longest[0]);
			
			}
		}
		
	}

}