import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    static int N, M;
    static int[][] Map;
    static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}}; // 상 하 좌 우
    
    /**
     *  움직인 좌표와 벽을 부순 횟수를 저장할 수 있는 노드 클래스
     * @author SSAFY
     */
    static class Node{
        int r;
        int c;
        int cnt; //벽을 부순 횟수
        public Node(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        Map = new int[N+1][M+1];
        
        // Map 값 입력 받기
        for(int i = 1 ; i <= N ; i++) {
            String str = br.readLine();
            for(int j = 0 ; j < M ; j++) {
            	// 스트링은 0번부터 받아와야 하지만,
            	// Map은 1번부터 사용하므로...
                Map[i][j+1] = str.charAt(j) - '0';
            }
        }
        
        bfs();
    }

    private static void bfs() {
        // 방문을 저장하는 visited 배열 - boolean
        // [행 좌표][열 좌표][1: 벽 부숨 / 0: 벽 부수지 않음]
        // visited[3][2][1] = true 라면 -> (3,2)까지 오면서 벽을 부순적이 있다.
        // visited[2][3][0] = true 라면 -> (2,3)까지 오면서 벽을 부순적이 없다.
        boolean[][][] visited = new boolean[N+1][M+1][2];
        
        // 방문하는 노드들을 담기 위한 큐 생성
        Queue<Node> queue = new ArrayDeque<>();
        
        // 시작위치 노드 큐에 넣고 방문 처리
        queue.offer(new Node(1, 1, 0));
        visited[1][1][0] = true;
        
        // 이동거리를 저장할 변수
        int dist = 1;
        while(!queue.isEmpty()) {
            int size = queue.size(); // 현재 탐색해야 하는 정점의 개수 담기
            while(size-- > 0) { // 대소비교 연산 하고 size 값 줄이는 거임
                // 현재 탐색할 정점 꺼내기
                Node cur = queue.poll();
                int r = cur.r;
                int c = cur.c;
                int cnt = cur.cnt;
                
                // 정점이 현재 N,M에 와있다면 거리 출력
                if(r == N && c == M) {
                    System.out.println(dist);
                    return;
                }
                
                // 사방으로 탐색
                for(int i =0 ; i< 4 ; i++) {
                	int nr = r + deltas[i][0];
                	int nc = c + deltas[i][1];
                	
                	if(nr<1 || nr > N || nc < 1 || nc>M) continue; //경계 체크
                	
                	//벽을 만난 경우 
                	if(Map[nr][nc] == 1) {
                		
                		//이미 벽 부수기를 사용한 경우 넘긴다.
                		// 벽 부수기는 cnt에 저장되어 있음
                		if(cnt == 1) continue;
                		
                		// 현재 좌표에 방문한 적이 있는 경우 넘긴다.
                		// ? 위에서 cnt로 체크해주고 왜 한번 더 체크해야 하는 거지?
                		if(visited[nr][nc][1]) continue;
                		
                		// 벽 부수기를 사용한 적이 없는 경우
                		// 벽 부수기를 사용하여 탐색 이어감
                		queue.offer(new Node(nr, nc, 1)); // 벽 부수기를 사용했으므로 cnt에 1 넣는다.
                		visited[nr][nc][1] = true; // 현재 위치 방문처리(벽 부수기를 사용해서 왔음.
                		
                	}
                	
                	// 빈칸을 만난 경우
                	else {
                		// 위에서는 왜 1로하고, 여기서는 0대신 cnt로 하는 거지?
                		if(visited[nr][nc][cnt]) continue; // 현재 위치 방문 체크
                		
                		// 다음 탐색을 위해 queue에 넣음
                		// 벽 부수기를 사용하지 않았으므로 cnt 그대로 넣음
                		queue.offer(new Node(nr, nc, cnt));
                		visited[nr][nc][cnt] = true; // 현재 위치 방문처리(벽을 부수지 않았으므로 0)
                	}
                }
                
            }   
            // 현재 정점에서 탐색할 수 있는 모든 정점의 탐색을 마쳤으므로 길이 + 1
            dist ++;
        }
        
        //탐색이 무사히 끝나는 경우, 탐색이 끝나도 (N,M)을 만나지 못했다는 뜻이므로 -1 출력
        System.out.println(-1);
        
    }
}