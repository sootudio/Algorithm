import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class Solution {
    static int T, N; // 테케, 맵 넓이
    static int[][] map ;// 핀볼 맵
    static List<int[]> blanks ;// 빈칸 좌표
    static List<Integer>[] worms; //웜홀 좌표
    static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0,-1}}; // 상 우 하 좌
    final static int TOP = 0, RIGHT = 1, BOT = 2, LEFT=3; 
    static int startR, startC; // 현재 시작좌표, 끝좌표
    static int maxScore, curScore;
    static boolean isArrived;
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine().trim());
         
        for(int tc =1 ; tc <= T ; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            blanks = new ArrayList<>();
            worms = new List[11];
             
            for(int i = 6 ; i <= 10; i++) {
                worms[i] = new ArrayList<Integer>();
            }
             
             
            // 입력값 받기
            for(int i = 0 ; i < N ; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
                for(int j = 0 ; j < N ; j++) {
                    int input = Integer.parseInt(st.nextToken());
                    if(input == 0) 
                        blanks.add(new int[] {i, j}); // 빈 칸 넣기
                    else if(input >= 6) {
                        worms[input].add(i); // 웜홀 좌표 넣기
                        worms[input].add(j);
                    }
                    map[i][j] = input;
                }
            }
             
//          for(int i = 5 ; i <= 10 ; i++) {
//              if(worms[i].size() < 1) continue;
//              System.out.println(i);
//              for(int j = 0 ; j < 4 ; j++) {
//                  System.out.print(worms[i].get(j)+" ");
//              }
//              System.out.println();
//          }
             
            maxScore = 0;
             
            // 빈 칸 배열에서 하나씩 꺼내면서 탐색
            for(int i = 0 ; i < blanks.size() ; i++) {
                int[] cur = blanks.get(i);
                startR = cur[0];
                startC = cur[1];
                 
                for(int d = 0; d < 4 ; d ++){
                    curScore = 0;
                    isArrived = false;
                    move(cur[0], cur[1], d);
                    maxScore = Math.max(maxScore, curScore);
                }
            }
            System.out.println("#"+tc+" "+maxScore);
        }
    }
 
    private static void move(int r, int c, int d) {
        int nr = r + deltas[d][0];
        int nc = c + deltas[d][1];
        int nd = d;
         
        // 경계 체크
        if(!isValid(nr, nc)) {
            curScore ++;
            nd = (d+2)%4;
//            move(nr, nc, nd);
//            return ;
        }
         
        // 블랙홀 만난다면 게임 끝
        // 시작 위치로 간다면 게임 끝
        else if(map[nr][nc] == -1 || (nr == startR && nc == startC)) {
            isArrived = true;
            return;
        }
         
        
         
        // 도형 만난다면
        else if(map[nr][nc] >= 1 && map[nr][nc] <= 5) {
            curScore ++;
            nd = changeDir(map[nr][nc], d);
        }
         
        // 웜홀 만난다면
        else if(map[nr][nc] >= 6  ) {
            List<Integer> worm = worms[map[nr][nc]];
            
            if(nr == worm.get(0) && nc == worm.get(1)) {
//              System.out.println("현재 웜홀 숫자: "+ map[nrIdx][ncIdx]);
//              for(int j = 0 ; j < 4 ; j++) {
//                  System.out.print(worms[map[nrIdx][ncIdx]].get(j)+" ");
//              }
//              System.out.println();
            	
                nr = worm.get(2);
                nc = worm.get(3);
            }
            else {
                nr = worm.get(0);
                nc = worm.get(1);
            }
        }
        move(nr, nc, nd);
        if(isArrived) return;
    }
 
    private static int changeDir(int shape, int d) {
        switch (shape) {
        case 1: {
            switch(d) {
            case BOT:
                d = RIGHT;
                break;
            case LEFT:
                d = TOP;
                break;
            default:
                d = (d+2) % 4;
                break;
            }
            break;
        }
        case 2: {
            switch(d) {
            case TOP:
                d = RIGHT;
                break;
            case LEFT:
                d = BOT;
                break;
            default:
                d = (d+2) % 4;
                break;
            }
            break;
        }
        case 3: {
            switch(d) {
            case TOP:
                d = LEFT;
                break;
            case RIGHT:
                d = BOT;
                break;
            default:
                d = (d+2) % 4;
                break;
            }
            break;
        }
        case 4: {
            switch(d) {
            case RIGHT:
                d = TOP;
                break;
            case BOT:
                d = LEFT;
                break;
            default:
                d = (d+2) % 4;
                break;
            }
            break;
        }
        case 5: {
            d = (d+2) % 4;
            break;
        }
        }
        return d;
    }
 
    private static boolean isValid(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < N;
    }
     
     
}