import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M; //연구소의 가로, 세로
	static int[][] lab; // 연구소 행렬
	static boolean[][] isWall; //벽을 true로 표시한 행렬
	
	static int numOfBlanks; // 빈칸의 개수
	static List<int[]> locOfBlanks = new ArrayList<>(); // 빈칸의 위치 좌표
	static boolean[] isSelected; // 빈칸 선택여부 체크하는 함수
	static List<int[]> selectedBlocks = new ArrayList<>(); // 선택된 빈칸들 담는 리스트
	static List<int[]> virus = new ArrayList<>(); // 바이러스의 위치 담는 큐

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		lab = new int[N][M];
		isWall = new boolean[N][M];
		numOfBlanks = 0;
		int maxBlanks = 0;
		
		// 연구소의 값들 입력받기
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ;j++) {
				int input = Integer.parseInt(st.nextToken());
				if(input == 0) {
					numOfBlanks++;
					locOfBlanks.add(new int[] {i, j});
				}
				else if(input == 1) {
					isWall[i][j] = true;
				}
				else{
					virus.add(new int[] {i, j});
					isWall[i][j] = true;
				}
				lab[i][j] = input;
			}
		}
		
		//System.out.println("빈칸 개수: "+numOfBlanks);
		isSelected = new boolean[numOfBlanks];
		
		// 조합으로 빈 칸 고르기
		comb(0,0);
		
		// 뽑은 빈 칸 조합을 하나씩 실험해 보기
		for(int i = 0 ; i < selectedBlocks.size() ; i++) {
		
			// 뽑은 빈 칸을 벽으로 바꾸기
			boolean[][] newLab = blankToWalls(selectedBlocks.get(i));
			
			// 만들어진 배열을 바탕으로 바이러스를 퍼뜨린다.
			int curMaxBlanks = bfs(newLab);
			
			//System.out.println("현재 빈 칸 개수: "+ curMaxBlanks);
			
			// 빈칸의 최대 개수 초기화
			maxBlanks = Math.max(maxBlanks, curMaxBlanks);
		}
		
		System.out.println(maxBlanks);
		
	}
	
	/**
	 * 벽 세운 연구소에서 바이러스 퍼뜨리는 함수.
	 * @param newLab 벽을 세운 연구소
	 * @return 빈칸의 개수
	 */
	private static int bfs(boolean[][] newLab) {
		int[][] deltas = {{-1, 0},{0, 1}, {1, 0}, {0,-1}};// 상 우 하 좌
		int leftBlanks = numOfBlanks - 3 + virus.size(); // 3개는 벽으로 만들었으므로
		Deque<int[]> q = new ArrayDeque<>();
		
		// 바이러스의 값 큐에 넣기
		for(int i = 0 ; i < virus.size() ; i++) {
			q.offer(virus.get(i));
		}
		
		while(!q.isEmpty()) {
			int[] virus = q.poll();
			int r = virus[0];
			int c = virus[1];
			
			leftBlanks--;
			
			// 사방으로 빈칸 탐색해서 q에 담기
			for(int d = 0 ; d < 4 ; d++) {
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];
				if(!isvalid(nr,nc)) continue; // 경계체크
				//if(lab[nr][nc] != 0) continue; // 값 체크
				if(newLab[nr][nc]) continue; // 새로 만든 벽이거나 이미 바이러스가 되었는지 체크
				q.offer(new int[]{nr,nc});
				newLab[nr][nc] = true; // 현재 위치 방문 표시
			}
		}
		
		return leftBlanks;
	}

	/**
	 * 선택된 빈 칸들을 벽으로 만드는 함수
	 * @return 벽을 세운 연구소
	 */
	private static boolean[][] blankToWalls(int[] selectedblanks) {
		
		boolean[][] newLab = new boolean[N][M];
		
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < M ; j++) {
				if(isWall[i][j]) newLab[i][j] = true;
			}
		}
		
		// 뽑힌 3개의 빈칸을 벽으로 바꾼다.
		for(int i = 0 ; i < 3 ; i++) {
			int r = locOfBlanks.get(selectedblanks[i])[0];
			int c = locOfBlanks.get(selectedblanks[i])[1];
			newLab[r][c] = true;
		}
		return newLab;
	}

	/**
	 * 빈칸 고르는 조합 실행하는 함수
	 * @param cnt 고른 개수
	 * @param idx 현재 고르는 위치
	 */
	private static void comb(int cnt, int idx) {
		// 기저조건: 3개 골랐을때
		if(cnt == 3) {
			int[] selectedBlanks = new int[3];
			int arrIdx = 0;
			for(int i = 0 ; i < numOfBlanks ; i++) {
				if(isSelected[i]) {
					selectedBlanks[arrIdx] = i;
					arrIdx++;
				}
			}
			// System.out.println(Arrays.toString(selectedBlanks));
			selectedBlocks.add(selectedBlanks);
			return;
		}
		// 유도파트: 3개 고르기
		for(int i = idx ; i < numOfBlanks ; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			comb(cnt+1, i);
			isSelected[i] = false;
		}
	}
	
	
	private static boolean isvalid(int nr, int nc) {
		if(nr < 0 || nr >= N || nc < 0 || nc >= M) return false;
		return true;
	}
	

}