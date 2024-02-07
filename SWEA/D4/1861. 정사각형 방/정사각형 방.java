

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int T;
	static int N;
	static int[][] rooms;
	static int[][] deltas = {{-1, 0},{0, -1},{1,0},{0,1}};
	static int[] curRoom = new int[2];
	static int[] longest = new int[2];
	static boolean moveable ;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int tc= 1 ; tc <= T ; tc++) {
			N = Integer.parseInt(br.readLine());
			rooms = new int[N][N];
			for(int i = 0 ; i < N ; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j ++) {
					rooms[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			move();
			
			System.out.println("#"+tc+" "+longest[0]+" "+longest[1]);
			longest[0] = 0;
			longest[1] = 0;
		}
	}
	
	private static void move() {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				curRoom[0] = rooms[i][j];
				curRoom[1] = 1;
				int idxI = i;
				int idxJ = j;
				int nx = 0;
				int ny = 0;
				while(true) {
					for(int k = 0 ; k < 4 ; k++) {
						nx = idxI +  deltas[k][0];
						ny = idxJ + deltas[k][1];
						if(nx >= 0 && nx < N && ny >=0 && ny <N && rooms[idxI][idxJ] - rooms[nx][ny] == -1) {
							curRoom[1] += 1;
							//System.out.println("이동거리: "+curRoom[1]);
							moveable = true;
							idxI = nx;
							idxJ = ny;
							k = -1;
							//System.out.println("이동중");
						}
					}
					if(!moveable) break;
					moveable = false;
				}
				if(curRoom[1] > longest[1]) {
					longest[0] = curRoom[0];
					longest[1] = curRoom[1];
				}
				else if(curRoom[1] == longest[1])
					longest[0] = Math.min(curRoom[0], longest[0]);
			
			}
			moveable = false;
		}
		
	}

}
