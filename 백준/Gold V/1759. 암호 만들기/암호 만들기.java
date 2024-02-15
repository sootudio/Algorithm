import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int L; // 암호의 길이
	static int C; // 사용하려는 문자의 개수
	static String gather = "aeiou"; // 암호의 유효성을 체크하기 위해 모음 문자열 선언
	static char[] letters; // 제공되는 문자를 담을 문자열
	static char[] pwd; // 암호를 만들 문자열
	static boolean[] isGather; // 만들어진 암호가 조건을 갖추었는지 확인하기 위한 문자열
	static boolean[] isSelected; // 이미 사용한 문자인지 확인
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		pwd = new char[L];
		letters = new char[C];
		isGather = new boolean[L];
		isSelected = new boolean[C];
		// 주어진 문자들을 letters에 넣음
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < C ; i++) {
			letters[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(letters);
		
		// 암호를 만드는 함수 호출
		pwdCombi(0,0);
		
	}

	private static void pwdCombi(int idx, int cnt) {
		// 기저조건: 함수의 길이 L이 되면
		if(idx == L) {
			int vNum = 0; // 자음의 개수
			int gNum = 0; // 모음의 개수
			for(int i = 0 ; i < L ; i++) {
				if(isGather[i]) gNum++ ; // 모음인 경우
				else vNum++; // 자음인 경우
			}
			// 자음이 2개보다 적고, 모음이 1개보다 적으면 다시 만들기
			if(vNum < 2 || gNum < 1) return;
			for(int i = 0; i < L ; i++) {
				System.out.print(pwd[i]);
			}
			System.out.println();
			return;
		}
		
		// 유도파트: 조건에 맞는 암호를 만든다.
		for(int i = cnt ; i < C ; i++) {
			if(!isSelected[i]) {
				if(idx > 0) {
					if(pwd[idx-1] < letters[i]) {
						if(gather.indexOf(letters[i]) != -1) isGather[idx] = true;
						else isGather[idx] = false;	
						pwd[idx] = letters[i];
						isSelected[i] = true;
						pwdCombi(idx+1, i+1);
						isSelected[i] = false;
					}
				}
				else {
					if(gather.indexOf(letters[i]) != -1) isGather[idx] = true;
					else isGather[idx] = false;	
					pwd[idx] = letters[i];
					isSelected[i] = true;
					pwdCombi(idx+1, i+1);
					isSelected[i] = false;
				}
			}
			
			
			
		}
	}
}