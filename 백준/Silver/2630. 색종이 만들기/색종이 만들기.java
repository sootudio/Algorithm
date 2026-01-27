import java.util.*;
import java.io.*;

public class Main {

    static int[][] board;
    static int b;
    static int w;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        board = new int[N][N];

        for(int i = 0;  i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        split(N, 0, 0);

        System.out.println(w);
        System.out.println(b);
    }

    private static void split(int N, int x, int y){
        int c = board[x][y];
        boolean isSameC = true;
        for(int i = x ; i < x+N ; i++){
            for(int j = y ; j < y+N ; j++){
                if(board[i][j] != c) {
                    isSameC = false;
                    break;
                }
            }
            if(!isSameC) break;
        }
        if(isSameC){
            if(c == 1) {
                b++;
            }
            else if(c == 0){
                w++;
            }
            return;
        } else{
            split(N/2, x, y);
            split(N/2, x, y + N/2);
            split(N/2, x + N/2, y);
            split(N/2, x + N/2, y + N/2);
        }
    }
}