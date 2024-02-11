

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

	public static int solution(int[][] maps) {
		int n = maps.length;
		int m = maps[0].length;
		int[][] deltas = {{1,0}, {0,1}, {-1, 0}, {0,-1}}; // 하, 좌, 상, 우 순으로 탐색
		
		Queue<int[]> q = new ArrayDeque<>(){};
		
		// 
		q.add(new int[] {0,0,1});
		
		while(!q.isEmpty()) {
			int[] loc = q.poll();
			int cx = loc[0];
			int cy = loc[1];
			int move = loc[2];
			
			if(cx == n-1 && cy == m-1) return move;
			
			for(int[] delta : deltas) {
				int nx = cx + delta[0];
				int ny = cy + delta[1];
				if(nx < 0 || nx >= n || ny <0 || ny >= m || maps[nx][ny] == 0) continue;
				if(maps[nx][ny] == 1) {
					q.add(new int[] {nx, ny, move+1});
					maps[nx][ny] = 0;
				}
				
			}
		}
		return -1;
	}
}
