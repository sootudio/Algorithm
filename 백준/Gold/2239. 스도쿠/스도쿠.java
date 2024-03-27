import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	
	static int[][] sudoku; // 스도쿠 판
	
	static ArrayList<Blank> blankList = new ArrayList<>(); // 빈 칸의 좌표를 넣을 리스트

	static class Blank{
		int r, c; //빈 칸 좌표
		public Blank(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		sudoku = new int[9][9];
		//스도쿠 값 넣기
		for(int i = 0 ; i < 9 ; i++) {
			String str = br.readLine();
			for(int j = 0 ; j < 9 ; j++) {
				sudoku[i][j] = str.charAt(j) - '0';
				if(sudoku[i][j] == 0) blankList.add(new Blank(i, j)); // 빈칸일때 좌표 넣어두기.
			}
		}
		
		fill(0); // 첫 번째 빈칸부터 채워넣기
	}

	
	private static void fill(int idx) {
		// 기저조건: 스도쿠의 모든 빈칸이 채워짐
		// idx가 리스트의 끝까지 갔다는 것은, 그 전 빈칸들을 모두 채웠다는 뜻이다.
		if(idx == blankList.size()) {
			// 출력하고 종료
			StringBuilder sb = new StringBuilder();
			for(int i = 0 ; i < 9 ; i++) {
				for(int j = 0 ; j <9 ; j++) {
					sb.append(sudoku[i][j]);
				}
				sb.append('\n');
			}
			System.out.println(sb);
			System.exit(0);
		}
		
		//step 01. 빈칸 정보 가져오기
		Blank b = blankList.get(idx);
		int r = b.r;
		int c = b.c;
		
		//step 02. 해당 빈칸에 1~9 숫자 넣어보기
		for(int num = 1 ; num <= 9 ; num++) {
			if(!isValid(r, c, num)) continue; //유효한 숫자인지 체크
			
			sudoku[r][c] = num; // 빈칸에 값 넣기
			fill(idx+1); // 다음 빈칸에 대해 탐색
			sudoku[r][c] = 0; // 백트레킹 시, 이미 채워넣은 값들을 다시 초기화 해줘야 함.
		}
 		
		
	}


	private static boolean isValid(int r, int c, int num) {
		// 행 체크
		for(int i = 0 ; i < 9 ; i++) {
			if(sudoku[r][i] == num) return false;
		}
		
		// 열 체크
		for(int i = 0 ; i < 9 ; i++) {
			if(sudoku[i][c] == num) return false;
		}
		
		//박스 체크
		int sr = (r/3)*3; //0,1,2이면 0, 3,4,5이면 3, 6,7,8이면 6이 나온다. 
		int sc = (c/3)*3; //0,1,2이면 0, 3,4,5이면 3, 6,7,8이면 6이 나온다. 
		// 이렇게 하면 해당 박스의 시작행과 시작열을 알 수 있다.
		for(int i = sr; i < sr+3; i++) { // 3x3 박스 체킹
			for(int j = sc; j < sc+3 ; j++) {
				if(sudoku[i][j] == num) return false;
			}
		}
		return true;
	}

}