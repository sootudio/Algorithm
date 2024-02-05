import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	static int N;
	static int K;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LinkedList<Integer> jp = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		
		N = sc.nextInt(); // 사람 수
		K = sc.nextInt(); // K번째 사람을 제거함.
		
		// 리스트에 요소 추가. 사람 번호가 1번부터이므로 1~N까지 넣어준다.
		for(int i = 1 ; i <=N ; i++) {
			jp.add(i);
		}
		
		sb.append('<');
		
		int idx = K-1; // 0부터 시작하므로 index K-1이 K번째임.
		
		while(true) {
			
			sb.append(jp.remove(idx)).append(", ");
			if(jp.size() == 0) break; // 뺀 후 리스트의 길이가 0이 되면 반복문 탈출
			
			idx += (K-1); // 삭제된 요소 때문에 K칸이 아닌 K-1칸 움직여야 함.
			idx %= jp.size(); // K-1 칸 움직였을 때 index를 벗어날 수도 있기 때문에, size로 나눠준 나머지만큼 간다.
			
		}
		sb.setLength(sb.length()-2); // 마지막에 들어간 ', ' 제거
		sb.append('>');
		System.out.println(sb);
	}

}