import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	
	static int T, N, M; // 테케수, 학생수, 비교횟수
	static ArrayList<Integer>[] asc; // 키 오름차순
	static ArrayList<Integer>[] desc; // 키 내림차순
	static int[] tallOrShort; // 나보다 크거나 작은 사람들 수 저장

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int tc= 1 ; tc <= T ; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			asc = new ArrayList[N+1];
			desc = new ArrayList[N+1];
			tallOrShort = new int[N+1];
			
			for(int i = 0 ; i <= N ; i++) {
				asc[i] = new ArrayList<>();
				desc[i] = new ArrayList<>();
			}

			for(int i = 0 ; i < M ; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				asc[a].add(b);
				desc[b].add(a);
			}
			
			for(int i = 1 ; i <= N ; i++) {
				bfs(asc, i);
				bfs(desc, i);
			}
			
			int cnt = 0;
			int person = N -1;
			for(int i = 1 ; i <= N ; i++) {
				if(tallOrShort[i] == person) cnt ++;
			}
			
			System.out.println("#"+tc+" "+cnt);
		}
	}

	private static void bfs(ArrayList<Integer>[] list, int start) {
		boolean[] isVisited = new boolean[N+1];
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		q.offer(start);
		isVisited[start] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			tallOrShort[cur]++;
			
			for(int i : list[cur]) {
				if(isVisited[i]) continue;
				q.offer(i);
				isVisited[i] = true;
			}
		}
		
		tallOrShort[start]--;
	}

}