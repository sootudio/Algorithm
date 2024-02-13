import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = 19;
		int[][] board = new int[19][19];
		// 아래, 우하향, 오른쪽, 우상향, 위쪽, 좌상향, 왼쪽, 좌하향
		// 앞 4개는 승리했는지 탐색하는데, 뒤 4개는 시작점인지 탐색하는데 사용
		int[][] deltas = {{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1}};
		
		// 바둑판에 현재 상태 입력
		for(int i= 0 ; i < N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0 ; i < N ; i++) {
			for(int j = 0; j < N ; j++) {
				if(board[i][j] == 0) continue;
				int value = board[i][j];
				for(int k = 0; k < 4 ; k++) {
					int cnt = 1;
					int nx = i + deltas[k+4][0];
					int ny = j + deltas[k+4][1];
					if(nx >= 0 && nx < N && ny >=0 && ny < N && value == board[nx][ny]) 
						continue;
					nx = i + deltas[k][0];
					ny = j + deltas[k][1];
					while(nx >= 0 && nx < N && ny >=0 && ny < N &&  value == board[nx][ny]) {
						cnt++;
						nx += deltas[k][0];
						ny += deltas[k][1];
					}
					if(cnt == 5) {
						System.out.println(value);
						System.out.println((i+1) + " "+ (j+1));
						System.exit(0);
					}
				}
			}
		}
		System.out.println(0);
	}

}