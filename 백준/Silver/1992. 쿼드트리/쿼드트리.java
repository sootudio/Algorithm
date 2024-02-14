import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N; // 쿼드트리 맵의 행/열 길이
	static int[][] map; // 쿼드트리의 값을 넣는 배열
	static StringBuilder sb = new StringBuilder(); // 답을 저장할 스트링빌더
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		String input;
		map = new int[N][N];
		
		// map에 초기 쿼드트리의 값 입력
		for(int i = 0 ; i < N ; i++) {
			input = br.readLine();
			for(int j = 0 ; j < N ; j++) {
				// 띄어쓰기 없이 들어왔으므로, stringTokenizer 대신 이렇게 받아야 한다.
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		resize(0,0,N); // x좌표, y좌표, 길이
		
		System.out.println(sb);
	}

	// 행렬을 4등분으로 분할해주고, 값 넣어주는 재귀함수
	private static void resize(int x, int y, int size) {
		//기저조건 -> 해당 배열 내의 모든 요소가 같으면
		if(check(x, y, size)) {
			// sb에 map의 현재 좌표의 값을 넣음
			sb.append(map[x][y]);
			return;
		}
		// 배열을 구분해줄 수 있도록 (로 경계 표시
		sb.append('(');
		// 나눠야 하는 경우이므로 4등분함
		resize(x, y, size/2); // 좌상
		resize(x, y+size/2, size/2); // 우상
		resize(x+size/2, y, size/2); // 좌하
		resize(x+size/2, y+size/2, size/2); // 우하
		// 배열의 경계 닫아줌
		sb.append(')');
	}

	// 현재 크기의 배열 내의 값들이 모두 같은 값인지 확인하는 boolean 함수
	private static boolean check(int x, int y, int size) {
		// 현재 배열의 첫번째 위치의 값 넣어줌
		int value = map[x][y];
		for(int i = x ; i < x+ size ; i++) {
			for(int j = y ; j < y+size ; j++) {
				// 다른 값이 있으면 false 리턴
				if(value != map[i][j]) return false;
			}
		}
		// 반복문을 나왔다면 다른 값이 없다는 것이므로 true 리턴
		return true;
	}

}