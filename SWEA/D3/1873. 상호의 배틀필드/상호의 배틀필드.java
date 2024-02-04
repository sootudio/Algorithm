import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int T; // 테스트 케이스
    static int H; // 맵 높이
    static int W; // 맵 넓이
    static int N; // 사용자 입력 문자열 길이
    static char[][] map; // 배틀필드 맵
    static int[] tankLoc = new int[2]; // 탱크 위치
    static int tankDir = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
        
        for(int tc = 1 ; tc <= T ; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][W];

            for(int i = 0 ; i < H ; i++) {
                String str = br.readLine();
                for(int j = 0 ; j < W ; j++) {
                    map[i][j] = str.charAt(j);
                    if(map[i][j] == '^') {
                        tankLoc[0] = i;
                        tankLoc[1] = j;
                        tankDir = 0;
                    }
                    else if( map[i][j] == 'v') {
                    	tankLoc[0] = i;
                        tankLoc[1] = j;
                        tankDir = 1;
                    } else if ( map[i][j] == '<') {
                    	tankLoc[0] = i;
                        tankLoc[1] = j;
                        tankDir = 2;
                    } else if( map[i][j] == '>') {
                    	tankLoc[0] = i;
                        tankLoc[1] = j;
                        tankDir = 3;
                    }

                }
            }

            N = Integer.parseInt(br.readLine());
            String command = br.readLine();
            for(int i = 0 ; i < N ; i++) {
                switch (command.charAt(i)) {
                case 'U':
                    tankDir = 0;
                    if(tankLoc[0] > 0 && map[tankLoc[0]-1][tankLoc[1]] == '.') {
                    	map[tankLoc[0]][tankLoc[1]] = '.';
                    	tankLoc[0]--;
                    }
                    map[tankLoc[0]][tankLoc[1]] = '^';
                    break;
                case 'D':
                	tankDir = 1;
                    if(tankLoc[0] < (H-1) && map[tankLoc[0]+1][tankLoc[1]] == '.') {
                    	map[tankLoc[0]][tankLoc[1]] = '.';
                    	tankLoc[0]++;
                    }
                    map[tankLoc[0]][tankLoc[1]] = 'v';
                    break;
                case 'L':
                	tankDir = 2;
                    if(tankLoc[1] > 0 && map[tankLoc[0]][tankLoc[1]-1] == '.') {
                    	map[tankLoc[0]][tankLoc[1]] = '.';
                    	tankLoc[1]--;
                    }
                    map[tankLoc[0]][tankLoc[1]] = '<';
                    break;
                case 'R':
                	tankDir = 3;
                    if(tankLoc[1] < (W-1) && map[tankLoc[0]][tankLoc[1]+1] == '.') {
                    	map[tankLoc[0]][tankLoc[1]] = '.';
                    	tankLoc[1]++;
                    }
                    map[tankLoc[0]][tankLoc[1]] = '>';
                    break;
                case 'S':
                	int nx = tankLoc[0]; 
            		int ny = tankLoc[1];
                	while(true) {
                		nx += deltas[tankDir][0];
                		ny += deltas[tankDir][1];
                		if(nx < H && nx >= 0 && ny < W && ny >= 0) {
                    		if(map[nx][ny] == '*') {
                    			map[nx][ny] = '.';
                    			break;
                    		}
                    		else if(map[nx][ny] == '#') {
                    			break;
                    		}
                    	}
                		else break;
                	}
                    break;
                default:
                    break;
                }
            }
            System.out.print("#"+tc+" ");
            for(int i = 0 ; i < H ; i++) {
            	for(int j = 0 ; j < W ; j++) {
            		System.out.print(map[i][j]);
            	}
            	System.out.println();
            }   
        }
    }
}