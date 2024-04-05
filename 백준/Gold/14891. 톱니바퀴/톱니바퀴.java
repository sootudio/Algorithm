import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] gears =  new int [5][8]; //톱니바퀴 담는 배열
	static int[] start = new int[5]; //각 톱니바퀴의 12시 방향 값이 들어있음.
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 1; i < 5 ; i++) {
			String str = br.readLine();
			for(int j = 0 ; j < 8 ; j++) {
				gears[i][j] = str.charAt(j)-'0';
			}
		}
		
		int K  = Integer.parseInt(br.readLine());
		// K번 반복하기
		for(int i = 0 ; i < K ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int gearNum = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			// 몇 개의 자석을, 어떤 방향으로 돌려야 하는지
			int[] rotateOperations = new int[5];
			rotateOperations[gearNum] = dir;
			
			checkLeft(rotateOperations, gearNum - 1, dir);
			checkRight(rotateOperations, gearNum + 1, dir);
			
			rotate(rotateOperations);
		}
		int score = count(); // 점수계산
		System.out.println(score);

	}

	private static void checkLeft(int[] rotateOperations, int gearNum, int dir) {
		if(gearNum < 1) {
			return;
		}
		if(gears[gearNum][(start[gearNum]+2)%8] != gears[gearNum+1][(start[gearNum+1]+6)%8]) {
			rotateOperations[gearNum] = -dir;
			checkLeft(rotateOperations, gearNum - 1, -dir);
		}
	}
	
	private static void checkRight(int[] rotateOperations, int gearNum, int dir) {
		if(gearNum > 4) {
			return;
		}
		// 자성이 다를때만 전파
				
				
		// 자성이 다를때만 전파
		if(gears[gearNum][(start[gearNum]+6)%8] != gears[gearNum-1][(start[gearNum-1]+2)%8]) {
			rotateOperations[gearNum] = -dir;
			checkRight(rotateOperations, gearNum + 1, -dir);
		}
		
		
	}

	private static void rotate(int[] rotateOperations) {
		for(int i = 1; i < 5; i++) {
			int delta = rotateOperations[i];
			
			if(delta == 0) {
				continue;
			}
			if(delta == 1) {
				//시계 방향
				start[i] = (start[i]+7)%8;
			}
			else {
				start[i] = (start[i]+1)%8;
			}
		}

	}

	private static int count() {
		int curScore = 0;
		for(int i= 1 ; i < 5 ; i++) {
			if(gears[i][start[i]] == 1)
				curScore += Math.pow(2, i-1); // 1, 2, 4, 8 점
		}
		return curScore;
	}

}