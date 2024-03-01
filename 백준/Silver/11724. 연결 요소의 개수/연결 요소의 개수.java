import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static List<Integer>[] arrList; // 인접 리스트
	static boolean[] isVisited; //방문체크 하는 배열
	static int cnt = 0; // 연결 요소의 개수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arrList = new List[N+1];
		isVisited = new boolean[N+1];
		
		for(int i= 1 ; i <= N ; i++) { // 인접 리스트 생성
			arrList[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0 ; i < M ; i++) { // 인접 리스트에 값 넣어줌
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arrList[a].add(b);
			arrList[b].add(a); // 무방향 그래프이므로 반대로도 해줘야함.
		}
		
		for(int i = 1; i<=N ; i++) {
			if(isVisited[i]) continue;
			bfs(i); // 모든 노드 탐색
			cnt++;
		}
		
		System.out.println(cnt);
	}

	private static void bfs(int i) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(i); // 현재 노드 큐에 넣기
		isVisited[i] = true;
		while(!q.isEmpty()) {
			int curNode = q.poll();
			
			for (int neighbor : arrList[curNode]) { // 현재 노드에 인접한 노드들 탐색
				if(isVisited[neighbor]) continue; // 방문 체크
				q.offer(neighbor); // 인접 노드 큐에 넣기
				isVisited[neighbor] = true;
			}
		}
	}
	
	

}