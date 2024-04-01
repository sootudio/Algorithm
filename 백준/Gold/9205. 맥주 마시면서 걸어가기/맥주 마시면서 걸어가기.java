import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/**
 * 메모리:19156KB, 시간:228ms
 */
public class Main {
	static List<Node> list = new ArrayList<>();	//좌표값 저장
	
	static class Node{
		int r,c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
		//노드 간의 거리값 반환
		public int getDis(Node n) {
			return Math.abs(this.r - n.r) + Math.abs(this.c - n.c); 
		}
		
		//행복하게 갈 수 있는지 여부 반환
		public boolean isPossible(Node n) {
			return getDis(n) <= 1000;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			list.clear();
			int n = Integer.parseInt(br.readLine()); //편의점 개수
			
			//step 1. 좌표 정보 저장
			for(int i=0; i<n+2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				list.add(new Node(r,c));
			}
			
			//step 2. 좌표 정보 활용해 각 지점간에 행복하게 이동가능한지 여부 체크
			boolean[][] d = new boolean[n+2][n+2];	//동적 배열 - i-> j까지 행복하게 갈수있는지 여부(모든 경유지고려)
			for(int i=0; i<n+2; i++) {
				for(int j=0; j<n+2; j++) {
					d[i][j] =  list.get(i).isPossible(list.get(j));
				}
			}
			
			//step 3. 플로이드 워샬 사용해 모든 경유지를 고려한 행복하게 이동가능한지 여부 갱신하기
			// 경출도 = 경유지 -> 출발지 -> 도착지
			for(int k=0; k<n+2; k++) {
				for(int i=0; i<n+2; i++) {
					for(int j=i; j<n+2; j++) {
						//d[i][j]는 i-> j를 0~ k-1의 경유지를 고려해서 행복하게 이동가능한지 여부
						//d[i][k]는 i-> k를 0~ k-1의 경유지를 고려해서 행복하게 이동가능한지 여부
						if(d[i][k] && d[k][j]) {
							d[i][j] = true;
							d[j][i] = true;
						}
					}
				}
			}
			
			sb.append( d[0][n+1]? "happy" : "sad").append("\n");
		}
		System.out.println(sb);
	}

}