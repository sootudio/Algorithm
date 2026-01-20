import java.util.*;
import java.io.*;

public class Main {

    static boolean[][] board;
    static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int M, N;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int tc = 0;

        while(tc < T){
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            board = new boolean[M][N];

            for(int i = 0 ; i < K ; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                board[x][y] = true;
            }

            int cnt = 0;

            for(int i = 0 ; i < M ; i++){
                for(int j = 0 ; j < N ; j++){
                    if(board[i][j]) {
                        bfs(i, j);
                        cnt++;
                    }
                }
            }

            sb.append(cnt).append("\n");

            tc++;
        }

        if(sb.length() > 0) sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    private static void bfs(int i, int j){
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i, j});
        board[i][j] = false;

        while(!q.isEmpty()){
            int[] vage = q.poll();
            int x = vage[0];
            int y = vage[1];
            for(int d = 0 ; d < 4 ; d++){
                int dx = x + deltas[d][0];
                int dy = y + deltas[d][1];
                if(!isValid(dx, dy)) continue;
                if(board[dx][dy]) q.offer(new int[]{dx, dy});
                board[dx][dy] = false;
            }
        }
    }

    private static boolean isValid(int dx, int dy){
        if(dx < 0 || dx >= M || dy < 0 || dy >= N) return false;
        return true;
    }
}
