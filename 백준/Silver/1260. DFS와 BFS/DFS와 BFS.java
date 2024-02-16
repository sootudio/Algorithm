import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static List<List<Integer>> graph = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	static int V; // 탐색 시작 정점
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 정점 개수
		int M = Integer.parseInt(st.nextToken()); // 간선 개수
		V = Integer.parseInt(st.nextToken());
		
		// 그래프 초기화
		for(int i = 0 ; i <= N ; i++) {
			graph.add(new ArrayList<>());
		}
		
		// 간선 입력
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to= Integer.parseInt(st.nextToken());
			
			graph.get(from).add(to);
			graph.get(to).add(from);
		}
		
		// 이웃 정점 정렬
		for(int i = 1 ; i <= N ; i++) {
			Collections.sort(graph.get(i));
		}
		
		visited = new boolean[N + 1];
		visited[V] = true;
		dfs(V);
		sb.append("\n");
		
		visited = new boolean[N + 1];
		visited[V] = true;
		bfs();
		
		System.out.println(sb);
	}
	
	static void dfs(int current) {
		sb.append(current).append(" ");
		
		for(int neighbor : graph.get(current)) {
			if (!visited[neighbor]) {
				visited[neighbor] = true;
				dfs(neighbor);
			}
		}
	}
	
	static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(V);
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			sb.append(current).append(" ");
			
			for(int neighbor : graph.get(current)) {
				if (!visited[neighbor]) {
					visited[neighbor] = true;
					queue.offer(neighbor);
				}
			}
		}
	}
}