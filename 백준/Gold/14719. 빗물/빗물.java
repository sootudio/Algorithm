import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int H, W;
	static int[][] walls;
	static int rain = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		walls = new int[H][W];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < W ; i++) {
			int h = Integer.parseInt(st.nextToken());
			for(int j = 0 ; j < h ; j++) {
				walls[j][i] = 1;
			}
			
		}
		
		checkrain();
		
		
		System.out.println(rain);
		
		
	}

	private static void checkrain() {
		boolean flag;
		int temp = 0;
		for(int i = 0 ; i < H ; i++) {
			flag = false;
			temp = 0;
			for(int j = 0 ; j < W ; j++) {
				if(walls[i][j] == 0) {
					if(flag) {
						temp ++;
					}
				}
				else {
					flag = true;
					rain += temp;
					temp = 0;
				}
			}
		}
		
	}

}