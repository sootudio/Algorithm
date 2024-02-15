import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N; // N자리 수
	static int[] startPrimes = {2, 3, 5, 7}; // 맨 첫 자리에 들어갈 수 있는 수들
	static int[] nextPrimes = {1, 3, 7, 9};
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for(int idx=0; idx < startPrimes.length; idx++) {
			// startPrime의 숫자들은 첫 번째를 뽑을 때 밖에 쓰지 않으므로 호출할때만 보낸다.
			mkPrime(1, startPrimes[idx]);
		}
		
		System.out.println(sb);

	}
	
	private static void mkPrime(int cnt, int primeNum) {
		// 지금까지 만든 숫자가 소수가 아니라면 가지치기
		if(!isPrime(primeNum)) return;
		
		// 기저조건: N자의 숫자를 완성한 경우
		if(cnt == N) {
			sb.append(primeNum).append('\n');
			return;
		}
		
		// 유도파트: cnt 자리에 1~9까지의 숫자를 하나씩 넣어봄
		for(int idx = 0 ; idx < nextPrimes.length ; idx++) {
			mkPrime(cnt+1, primeNum*10 + nextPrimes[idx]);
		}
	}

	private static boolean isPrime(int primeNum) {
		// 1이면 소수가 아니므로 return false. 근데 이게 필요한가?
		// if(primeNum == 1) return false;
		// 만약 2이상부터 pirme/2 까지 나눠떨어지는 수가 있다면 소수가 아님.
		for(int i=2; i<=primeNum/2 ; i++) {
			if(primeNum%i == 0) return false;
		}
		// 여기까지 return이 되지 않았으면 소수임.
		return true;
	}

}