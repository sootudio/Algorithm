import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int T, R, C, K; // 테케, 행, 열, 테스트 통과 기준
    static int[][] map; // 필름 값들
    static int[] picked;
    static boolean isCombTrue, isPwstTrue;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T ; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            
            map = new int[R][C];
            int result = 0;
            
            // 값 입력받기
            for(int i = 0 ; i < R ; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < C ; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            // 처음에 한번 돌려보기
            if(test(map)) {
            	//System.out.println("시작 지점에서 성공하였습니다.");
                result = 0;
            }
            // 아니라면, 1개부터 골라야 함.
            else {
                for(int i = 1 ; i <= R; i++) {
                    picked = new int[i];
                    isCombTrue = false;
                    isPwstTrue = false;
                    comb(0, 0, i);
                    if(isCombTrue && isPwstTrue) {
                    	result = i;
                    	break;
                    }
                }
            }
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        sb.setLength(sb.length()-1);
        System.out.println(sb);

    }

    /**
     * 약품을 투여할 행을 고르는 함수
     * @param idx 
     * @param cnt
     * @param slt : 약품을 투여할 행 개수
     */
    private static void comb(int idx, int cnt, int slt) {
        // 기저조건: cnt == slt라면
        if(cnt == slt) {
            //부분집합 함수에 넣는다.
        	// 맵을 하나 복사해서 만들기
			int[][] copyMap = new int[R][C];
			for(int i = 0 ; i < R ; i++) {
				for(int j = 0 ; j < C ; j++) {
					copyMap[i][j] = map[i][j];
				}
			}
        	powerSet(0, slt, copyMap);
        	if(isPwstTrue) isCombTrue = true;
        	return;
        }
        for(int i = idx ; i < R; i++) {
            picked[cnt] = i;
            comb(i+1, cnt+1, slt);
            if(isCombTrue) return;
        }
        
    }

    /**
     * 고른 행에 A를 투여할지, B를 투여할지 고르는 함수
     * @param cnt
     * @param slt
     * @param isChanged
     * @param copyMap 
     */
    private static void powerSet(int cnt, int slt, int[][] copyMap) {
    	// 기저조건: slt까지 가면
		if(cnt == slt) {
			if(test(copyMap)) isPwstTrue = true;
			return;
		}
		Arrays.fill(copyMap[picked[cnt]], 0);
		powerSet(cnt+1, slt, copyMap);
		if(isPwstTrue) return;
		Arrays.fill(copyMap[picked[cnt]], 1);
		powerSet(cnt+1, slt, copyMap);
		if(isPwstTrue) return;
	}

    
	private static boolean test(int[][] testMap) {
        boolean[] passed = new boolean[C];
        
        for(int i = 0 ; i < C ; i++) {
            int pass = 0;
            int past = -1;
            for(int j = 0 ; j < R ; j++) {
            	//System.out.println("현재 위치: "+testMap[j][i] +"past 값: "+past);
                if(testMap[j][i] == past) {
                    pass ++;
                    //System.out.println(pass);
                }
                else
                	pass = 1;
                if(pass >= K) {
                    passed[i] = true;
                    break;
                }
                past = testMap[j][i];
            }
            if(!passed[i]) return false;
        }
        
        //System.out.println(Arrays.toString(passed));
        return true;
    }


}