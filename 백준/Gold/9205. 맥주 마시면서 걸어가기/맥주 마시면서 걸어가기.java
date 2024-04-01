import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static class Node{
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
		public int getDistance(Node n) {
			return Math.abs(this.r - n.r) + Math.abs(this.c - n.c);
		}
		
		public boolean isHappy(Node n) {
			if(getDistance(n) <= 1000) return true;
			else return false;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 0 ; tc < T ; tc++) {
			List<Node> list = new ArrayList<>();
			int conv = Integer.parseInt(br.readLine()); // 편의점 개수
			
			// 입력 받아서 노드 만들어서 넣음
			for(int i = 0 ; i < conv+2 ; i++) { // 편의점 수 + 출발점 + 도착점
				StringTokenizer st= new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				Node n = new Node(r, c);
				list.add(n);
			}
			
			// 두 점 사이 이동가능한지 담아두는 배열
			boolean[][] dis = new boolean[conv+2][conv+2];
			for(int i = 0 ; i < conv+2 ; i++) {
				for(int j = 0 ; j < conv+2 ; j++) {
					if(list.get(i).isHappy(list.get(j))) dis[i][j] = true;
				}
			}
			
			// 플로이드 - 워샬 사용해서 거쳐 갈 수 있는 경로 포함 가능한 모든 경로를 true로 만든다.
			for(int k = 0 ; k < conv+2 ; k++) {
				for(int i = 0 ; i < conv+2 ; i++) {
					for(int j = i ; j < conv+2 ; j++) {
						// 무방향 그래프이기 때문에 j = i부터 시작해도 된다.
						if(dis[i][k] && dis[k][j]) {
							dis[i][j] = true;
							dis[j][i] = true;
						}
					}
				}
			}
			
			// 0번째 노드(출발점) 에서 n+1번쨰 노드(도착점)까지 이동이  가능하다면 happy, 불가능하다면 sad
			sb.append(dis[0][conv+1]? "happy" : "sad").append("\n");
		}
		
		System.out.println(sb);
	}

}