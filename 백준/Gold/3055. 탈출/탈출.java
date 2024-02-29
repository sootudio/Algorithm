import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
- x좌표, y좌표, 고슴이 여부를 저장하는 클래스 생성

Main 함수
- Map 입력받고, 고슴이와 물의 위치 저장
- bfs탐색()

bfs함수
- Queue에 물(들)의 위치랑 고슴이 위치 넣음
    - Queue가 빌때까지 반복
        - q.poll
            - 비버 집이면 현재 이동 횟수 반환
            - 물이면 사방탐색
                - 바위가 아니면 Queue에 넣음
            - 고슴이면 사방탐색
                - 물, 바위, visited가 아니면 Queue에 넣음
            - 이동거리 + 1
 * @author SSAFY
 *
 */

public class Main {
	
	static public class Coord{
		int x; 
		int y;
		boolean isWater; // 물인지 고슴이인지
		
		public Coord(int x, int y, boolean isWater) {
			this.x = x;
			this.y = y;
			this.isWater = isWater;
		}
		
	}
	
	static int R; // Map의 높이
	static int C; // Map의 넓이
	static char[][] forest; // 숲 정보 넣는 배열
	static boolean[][] visited; // 고슴이가 이동했는지 체크하는 배열
	static List<int[]> waters = new ArrayList<>();
	static int[] hog = new int[2]; // 고슴도치 첫 위치
	static Queue<Coord> q = new ArrayDeque<>(); // 고슴이와 물 넣는 Queue
	static int[][] deltas = {{-1, 0},{0, 1},{1, 0},{0, -1}}; // 상 우 하 좌
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		forest = new char[R][C];
		visited = new boolean[R][C];
		
		for(int i = 0; i < R ; i ++) {
			String str = br.readLine(); 
			for(int j = 0; j < C ; j++) {
				switch(str.charAt(j)) {
				case '*': // 물인 경우
					int[] water = {i, j}; // 왜 바로  Queue에 안넣냐면 물의 입력이 고슴이보다 늦게 나올수도 있기 떄문이다.
					waters.add(water);
					break;
				case 'S': // 고슴이인 경우
					hog[0] = i;
					hog[1] = j;
					break;
				}
				forest[i][j] = str.charAt(j);
			}
		}
		
		int answer = bfs();
		if(answer == -1) System.out.println("KAKTUS");
		else System.out.println(answer);
	}

	private static int bfs() {
		int moveCnt = 0 ; //이동횟수
		for(int[] water : waters) { // 물 좌표들 넣음
			q.offer(new Coord(water[0], water[1], true));
		}
		q.offer(new Coord(hog[0], hog[1], false)); // 고슴이 좌표 넣음
		
		while(!q.isEmpty()) {
			int size = q.size(); //한 번에 탐색할 사이즈 설정
			
			while(size-- > 0) { // 이 안에서 일어나는 것들은 한번 탐색할때 벌어짐.
				Coord crd = q.poll();
				int x = crd.x;
				int y = crd.y;
				boolean isWater = crd.isWater;
				
				if(forest[x][y] == 'D') return moveCnt; // 비버 집에 도착했으면 이동횟수 반환
				
				if(crd.isWater) { // 물이면
					for(int d = 0; d< 4; d++) { // 사방탐색
						int nx = x + deltas[d][0];
						int ny = y + deltas[d][1];
						if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue; // 경계체크
						// 물, 돌, 비버집인 경우에는 이동하지 않음.
						if(forest[nx][ny] == '*' || forest[nx][ny] == 'X' || forest[nx][ny] == 'D') continue;
						forest[nx][ny] = '*'; // 물로 바꿈
						q.offer(new Coord(nx, ny, true)); // 물 위치 queue에 넣어줌
					}
					
				}
				else {
					for(int d = 0; d< 4; d++) { // 사방탐색
						int nx = x + deltas[d][0];
						int ny = y + deltas[d][1];
						if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue; // 경계체크
						// 물, 돌, 인 경우에는 이동하지 않음.
						if(forest[nx][ny] == '*' || forest[nx][ny] == 'X') continue;
						if(visited[nx][ny]) continue; //경계체크
						q.offer(new Coord(nx, ny, false)); // 물 위치 queue에 넣어줌
						visited[nx][ny] =true;
					}
					
				}
			}
			moveCnt++; // 한 level의 bfs가 끝나면 moveCnt++
		}
		return -1;
	}

}