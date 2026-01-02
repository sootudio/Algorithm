import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] board = new boolean[N][M];

        for(int i = 0 ; i < N ; i++){
            String input = br.readLine();
            for(int j = 0 ; j < M ; j++){
                if(input.charAt(j) == 'W') board[i][j] = true;
                else board[i][j] = false;
            }
        }

        int color = 64;

        // 백: 1, 흑: 0
        for(int i = 0 ; i <= N-8 ; i++){
            for(int j = 0 ; j <= M-8 ; j++){
                color = Math.min(color, Math.min(checkW(i, j, board), checkB(i, j, board)));
            }
        }

        System.out.println(color);


    }

    private static int checkW(int n, int m, boolean[][] board){
        int cnt = 0;
        for(int i = 0 ; i < 8 ; i++ ){
            for(int j = 0 ; j < 8 ; j++){
                if(i%2 == 0){
                    if(j%2 == 0){ // 백이여야 함
                        if(!board[i+n][j+m]) cnt++;
                    }
                    else{ // 흑이여야 함
                        if(board[i+n][j+m]) cnt++;
                    }
                }
                else{
                    if(j%2 != 0){ // 백이여야 함
                        if(!board[i+n][j+m]) cnt++;
                    }
                    else{ // 흑이여야 함
                        if(board[i+n][j+m]) cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    private static int checkB(int n, int m, boolean[][] board){
        int cnt = 0;
        for(int i = 0 ; i < 8 ; i++ ){
            for(int j = 0 ; j < 8 ; j++){
                if(i%2 == 0){
                    if(j%2 == 0){ // 흑이여야 함
                        if(board[i+n][j+m]) cnt++;
                    }
                    else{ // 백이여야 함
                        if(!board[i+n][j+m]) cnt++;
                    }
                }
                else{
                    if(j%2 != 0){ // 흑이여야 함
                        if(board[i+n][j+m]) cnt++;
                    }
                    else{ // 백이여야 함
                        if(!board[i+n][j+m]) cnt++;
                    }
                }
            }
        }
        return cnt;
    }
}