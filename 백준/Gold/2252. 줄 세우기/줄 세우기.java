import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static List<Integer>[] list; // 자신보다 키가 큰 사람들의 번호를 담는 인접 리스트
	static int[] shortThanMe; // 자신(인덱스)보다 작은 사람들의 수를 담는 배열
	static int N, M; // 학생 수 , 비교횟수
	static StringBuilder sb = new StringBuilder(); // 출력문 담은 스트링빌더
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new List[N+1];
		shortThanMe = new int[N+1];
		
		// 인접리스트 초기화
		for(int i = 1 ; i <= N ; i++) {
			list[i] = new ArrayList<>();
		}
		
		// 입력받을 때 키 작은 친구와 키 큰 친구 입력받음
		int small = 0;
		int tall = 0;
		
		// 키 비교 입력받음
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			small = Integer.parseInt(st.nextToken());
			tall = Integer.parseInt(st.nextToken());
			
			// small 보다 큰 사람에 tall을 추가
			list[small].add(tall);
			
			// tall 보다 작은 사람에 1명 추가
			shortThanMe[tall] ++;
		}
		
		bfs();
		
		System.out.println(sb);
		
	}

	private static void bfs() {
		Deque<Integer> q = new ArrayDeque<>();
		
		for(int i = 1; i <= N ; i++) {
			// shortThanMe[i] == 0 이라면
			// q에 넣는다. 나보다 작은 놈이 0이라는 뜻이니까.
			if(shortThanMe[i] == 0) q.offer(i);
		}
		
		//q가 비어있지 않는 동안 반복
		while(!q.isEmpty()) {
			int num = q.poll();
			
			// q에서 꺼냈다는건, 자기보다 작은 놈의 수가 0이라는 뜻이므로,
			// 그냥 현재 있는 애들중에 제일 작은 놈(중 하나) 라고 생각하면 된다. 바로 출력문에 넣어도 됨.
			sb.append(num).append(" ");
			
			// 지금 꺼낸 수보다 확실하게 큰 놈들중에서 찾음
			for(int tall : list[num]) {
				// num이 이미 가장 작은 놈으로 들어갔으므로,
				// num보다 큰 놈들은 이제 자기보다 작은 놈이 1명 사라진거임
				// 그때, 남은 자기보다 작은 놈의 숫자가 0이면
				// 그놈이 다음으로 작은 놈임
				// 선행 연산자로 shortThanMe[tall] 에서 - - 를 해주고, 
				// 이게 0이면 q에 담는다.
				if(--shortThanMe[tall] == 0) q.offer(tall);
			}
		}
		
	}

}