import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, M, C; // 테스트 케이스, 벌통 크기, 벌통 개수, 최대 꿀 채취량
	static int[][] honeyComb; // 벌집
	static int[][] honeyCollect; // 해당 좌표에서 M만큼의 벌통에서 구할 수 있는 최대 꿀의 값을 담는 배열
	static int[] honeyBox; // 꿀을 채취할 수 있는 배열. 이걸 부분집합 함수에 보내서 여기에서 최대값을 구한다.
	static boolean[] isSelected; // 선택할 꿀
	static int curMaxHoneyInBox; // 현재 고른 벌통에서 최대 벌꿀 값
	static int maxHoneyInBox; // 현재 고른 벌통에서 최대 벌꿀 값
	static int maxHoneyInComb; // 전체 벌집에서 최대 벌꿀 값
	static int[] maxHoneyLoc = new int[2]; // 전체 벌집에서 최대 벌꿀 값의 좌표

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		// 테스트 케이스의 수만큼 반복
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			honeyComb = new int[N][N];
			honeyCollect = new int[N][N];
			honeyBox = new int[M];
			
			curMaxHoneyInBox = 0;; // 현재 고른 벌통에서 최대 벌꿀 값
			maxHoneyInBox = 0;; // 현재 고른 벌통에서 최대 벌꿀 값
			maxHoneyInComb = 0;
			

			// 벌집의 값 입력받기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					honeyComb[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 벌집 배열 순회하며, 각 좌표에서 구할 수 있는 최대 꿀 값 찾기.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 해당 좌표에서 M만큼의 벌집을 만드는 것이 불가능하다면, 해당 좌표에서 구할 수 있는 최대 꿀의 양은 0이다.
					if ((j + M - 1) >= N)
						continue;
					// 부분집합을 구할 꿀 배열 생성
					for (int k = 0; k < M; k++) {
						honeyBox[k] = honeyComb[i][j + k];
					}
					// 값들 초기화 하고, 부분집합 함수 호출
					isSelected = new boolean[M];
					maxHoneyInBox = 0;
					
					powerSet(0, 0);
					
					// 값 저장하는 배열에 최대 꿀 값 넣기
					honeyCollect[i][j] = maxHoneyInBox;
					
					if(maxHoneyInBox > maxHoneyInComb) {
						maxHoneyInComb = maxHoneyInBox;
						maxHoneyLoc[0] = i;
						maxHoneyLoc[1] = j;
					}
				}
			} // 배열 순회 끝
			
			
			/*
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					System.out.print(honeyCollect[i][j] + " ");
				}
				System.out.println();
			}
			*/
			
			//System.out.println("최대 벌꿀 값: "+maxHoneyInComb);
			
			int result = maxHoneyInComb; // 최대 벌꿀 값 저장
			result += selectSecondBox(); // 두번째 큰 벌꿀 값 구해서 더하는 함수
			
			System.out.println("#"+tc+" "+result);
			
		}
	}

	private static int selectSecondBox() {
        int secondMax = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == maxHoneyLoc[0]) {
                    //if (j >= maxHoneyLoc[1] && j <= maxHoneyLoc[1] + M - 1) continue;
                	continue;
                }
                secondMax = Math.max(secondMax, honeyCollect[i][j]);
            }
        }
        return secondMax;
    }

	
	
	
	private static void powerSet(int idx, int sum) {
		// 기저조건: idx가 M이라면
		if (idx == M) {
			if(sum > C) return;
			curMaxHoneyInBox = 0;
			for(int i = 0 ; i < M ; i++) {
				if(isSelected[i]) curMaxHoneyInBox += Math.pow(honeyBox[i], 2) ;
			}
			maxHoneyInBox = Math.max(maxHoneyInBox, curMaxHoneyInBox);
//			System.out.println(Arrays.toString(isSelected));
			return;
		}
		// 유도파트
		for(int i = idx ; i < M ; i++) {
			isSelected[i] = true;
			powerSet(idx+1, sum+honeyBox[i]);
			isSelected[i] = false;
			powerSet(idx+1, sum);
		}

	}

}