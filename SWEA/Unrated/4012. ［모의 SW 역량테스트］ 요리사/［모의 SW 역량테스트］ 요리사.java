import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static int T;
	static int N;
	static int ingred;
	static int[][] synergy;
	static int[] twoFood = new int[2];
	static int foodTaste1 = 0;
	static int foodTaste2 = 0;
	static int minTaste = 20_000;
	static int[] selected;
	static int[] unSelected;
	static boolean[] isSelected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		
		for(int tc = 1 ; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			synergy = new int[N][N];
			ingred = N/2;
			isSelected = new boolean[N];
			
			// 시너지 점수 넣기
			for(int i = 0 ; i < N ; i ++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) {
					synergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			combi_ingred(0, 0);
			System.out.println("#"+tc+" "+minTaste);
			minTaste = 20_000;
		}
	}

	private static void combi_ingred(int idx, int cnt) {
		if(cnt == ingred) {
			int s_idx = 0;
			int uns_idx = 0;
			selected = new int[ingred];
			unSelected = new int[ingred];
			
			for(int i = 0 ; i< N ; i++) {
				if(isSelected[i]) {
					selected[s_idx] = i;
					s_idx++;
				}
				else {
					unSelected[uns_idx] = i;
					uns_idx++;
				}
			}
			//System.out.println();
			//System.out.println(Arrays.toString(selected));
			//System.out.println(Arrays.toString(unSelected));
			
			combi_synergy(0,0,true);
			combi_synergy(0,0,false);
			int curTaste = Math.abs(foodTaste1 - foodTaste2);
			minTaste = Math.min(curTaste, minTaste);
			foodTaste1 = 0;
			foodTaste2 = 0;
			
			return;
		}
		for(int i = idx ; i < N ; i++) {
			isSelected[i] = true;
			combi_ingred(i+1, cnt+1);
			isSelected[i] = false;
		}
		
	}

	private static void combi_synergy(int idx, int cnt, boolean sltd) {
		
		if(sltd == true) {
			if(cnt == 2) {
				// System.out.println("고른 음식: "+ Arrays.toString(twoFood));
				foodTaste1 += (synergy[twoFood[0]][twoFood[1]] + synergy[twoFood[1]][twoFood[0]]);
				// System.out.println("고른 음식 시너지: "+foodTaste1);
				return;
			}
			for(int i = idx ; i < ingred ; i++) {
				twoFood[cnt] = selected[i];
				combi_synergy(i+1, cnt+1, true);
			}
			
		}
		else{
			if(cnt == 2) {
				// System.out.println("안고른 음식: "+ Arrays.toString(twoFood));
				// System.out.println(synergy[twoFood[0]][twoFood[1]]);
				// System.out.println(synergy[twoFood[1]][twoFood[0]]);
				foodTaste2 += (synergy[twoFood[0]][twoFood[1]] + synergy[twoFood[1]][twoFood[0]]);
				// System.out.println("안고른 음식 시너지: "+foodTaste2);
				return;
			}
			for(int i = idx ; i < ingred ; i++) {
				twoFood[cnt] = unSelected[i];
				// System.out.println("twoFood의 "+cnt + " = "+ unSelected[i]);
				combi_synergy(i+1, cnt+1, false);
			}
			
		}
	}
}
