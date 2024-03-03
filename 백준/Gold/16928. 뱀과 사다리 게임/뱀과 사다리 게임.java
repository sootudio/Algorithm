import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	// 게임 진행할 보드판, 100번까지 쓸 것이므로 크기를 101로 만들어 준다.
	// 행은 현재 숫자, 열은 현재 숫자에서 이동이 가능한 숫자인지 나타낸다.
	// 예를 들어, map[5][10] = true 이면 5번에서 10번이 이동 가능하다는 뜻이다.
	// 또한 map[7][3] = false 이면 7번에서 3번으로는 이동이 불가능하다는 뜻이다.
	static boolean[][] map = new boolean[101][101];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 각 칸에서 주사위를 굴려 갈 수 있는 칸에 이동 가능 표시를 한다.
		for(int i = 1 ; i <= 100 ; i++) {
			for(int j = 1 ; j <= 6 ; j ++) {
				if(i + j > 100) continue; // 100보다 커지면 갈 수 있는지가 의미없음.
				map[i][i+j] = true; // 이동 가능 처리
			}
		}
		
		// 사다리와 뱀의 수 입력받음
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 사다리와 뱀 연결 -> 둘다 똑같은 로직 사용한다.
		for(int i = 0 ; i < N+M ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 출발 위치
			int b = Integer.parseInt(st.nextToken()); // 도착 위치
			for(int j = 1 ; j <= 6 ; j++) {
				if(a-j <= 0) continue; // 출발 위치에서 해당 주사위를 뺀 자리가 0보다 작으면 의미 없음
				// 주사위를 굴려 출발 지점까지 갈 수 있는 위치와 도착 지점을 이동 가능 처리해준다.
				map[a-j][b] = true;
				// 주사위를 굴려 출발 지점까지 가면 무조건 도착 지점으로 이동한다.
				// 따라서 주사위를 굴려 도착할 수 있는 출발 지점을 이동 불가 처리 해준다.
				map[a-j][a] = false;
			}
		}
		
		// bfs 탐색을 실시하고, 결과를 출력한다.
		System.out.println(bfs());
		
	}

	private static int bfs() {
		// int[0]은 해당 위치, int[1]은 주사위를 굴린 횟수
		Deque<int[]> q = new ArrayDeque<>();
		boolean[] visited = new boolean[101]; // 해당 번호 방문체크
		q.offer(new int[] {1, 0}); // 첫 번째 칸 넣기
		visited[1] = true; // 방문 처리
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0]; // 현재 위치 꺼내기
			for(int i = 1 ; i <= 100 ; i++) { //현재 위치에서 갈 수 있는 도착점들 체크해보기
				if(!map[x][i]) continue; // i번 위치로 이동 불가능한 경우
				if(i == 100) { // 100번까지 이동 가능하면
					return cur[1]+1; // 현재 칸에서 한번 더 이동 가능한 것이므로 1 더해서 반환
				}
				if(visited[i]) continue; //순환 방지 위해
				visited[i] = true;
				q.offer(new int[] {i, cur[1]+1}); // 주사위 한번 더 굴려서 값  넣어줌
			}
		}
		return 0;
	}

}