import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static int R, C, T; // 행, 열, 테케수
    static int[][] map; // 방 정보
    static int[] cleaner = new int[2]; // 공청기 행좌표

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        map = new int[R][C];
        
        int idx = 0;
        
        // 방 정보 입력받기
        for(int i=0 ; i < R ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < C ; j++) {
                int input = Integer.parseInt(st.nextToken());
                if(input == -1) { // 공청기 찾으면
                    cleaner[idx] = i;
                    idx++;
                }
                map[i][j] = input;
            }
        }
        
        // 공청기에 필요한 배열들 만들기
        // deltas : 위치 조정 
        int[][] upperDeltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상 우 하 좌
        int[][] lowerDeltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 하 우 상 좌
        
        // bound : 경계 체크
        int[] upperBound = {0, cleaner[0]+1, 0, C};
        int[] lowerBound = {cleaner[1], R, 0, C};
        
        // start : 공청기 동작 시작점
        int[] upperStart = {cleaner[0]-1, 0};
        int[] lowerStart = {cleaner[1]+1, 0};
        
        // T초만큼 반복
        for(int t = 0 ; t < T ; t++) {
            // 미세먼지 확산시키기
            dustSpread();
            
//            for(int i = 0 ; i < R ; i++) {
//                for(int j = 0 ; j < C ; j++) {
//                    System.out.print(map[i][j]+" ");
//                }
//                System.out.println();
//            }
            
            // 위쪽 공청기 실행
            airClean(upperDeltas, upperBound, upperStart);
            
            // 아래쪽 공청기 실행
            airClean(lowerDeltas, lowerBound, lowerStart);
            
        }
        
        // 미세먼지 세기
        int result = 0; 
        for(int i = 0 ; i < R ; i++) {
            for(int j = 0 ; j < C ; j++) {
                int val = map[i][j];
                if(val == -1) continue;
                result += val;
            }
        }
        
        System.out.println(result);

    }

    /**
     * 미세먼지 확산 함수
     */
    private static void dustSpread() {
        int[][] spread = new int[R][C];
        int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상 우 하 좌
        
        // map 순회하며 미세먼지 찾기
        for(int i = 0 ; i < R ; i++) {
            for(int j = 0 ; j < C ; j++) {
                int dust = map[i][j];
                if(dust == 0 || dust == -1) continue; // 0 체크
                int spreadDir = 0;
                for(int d = 0 ; d < 4 ; d++) {
                    int nr = i + deltas[d][0];
                    int nc = j + deltas[d][1];
                    if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue; // 경계체크
                    if(map[nr][nc] == -1) continue;
                    spread[nr][nc] += dust/5;
                    spreadDir ++;
                }
                spread[i][j] -= (dust/5 * spreadDir);
            }
        }
        
        // map과 합치기
        for(int i = 0 ; i < R ; i++) {
            for(int j = 0 ; j < C ; j++) {
                map[i][j] += spread[i][j];
            }
        }
        
    }
    
    private static void airClean(int[][] deltas, int[] bound, int[] start) {
        int nextVal = 0;
        int r = start[0];
        int c = start[1];
        int d = 0;
        
        // 공청기 작동시키기
        while(d < 4) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if(nr < bound[0] || nr >= bound[1] || nc < bound[2] || nc >= bound[3]) {
                d++; // 다음 방향으로 이동
                continue;
            }
            nextVal = map[nr][nc];
            if(nextVal == -1) {
                map[r][c] = 0;
                break;
            }
            
            map[r][c] = nextVal;
            r = nr;
            c = nc;
        }
        
    }

}