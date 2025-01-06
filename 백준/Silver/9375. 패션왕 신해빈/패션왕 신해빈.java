import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while (T -- > 0) {
			
			HashMap<String, Integer> hm = new HashMap<>(); // 옷 종류과 종류별 개수를 담은 해시맵
			
			int N = Integer.parseInt(br.readLine());
			
			while(N -- > 0) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				
				st.nextToken(); // 옷 이름은 사용하지 않으므로 그냥 받아서 버림
				
				String kind = st.nextToken(); // 옷 종류
				
				if(hm.containsKey(kind)) {
					hm.put(kind, hm.get(kind) + 1); // 이미 있는 종류면 종류 개수 +1
				}
				else {
					hm.put(kind, 1); // 새로운 옷이므로 한 개 있음.
				}
			}
			
			int result = 1;
			
			for(int val : hm.values()) {
				result *= (val + 1); // 1을 더해주는 이유는 조합을 구할 때 해당 옷을 안 입는 경우도 포함시켜야 하기 때문.;
			}
			sb.append(result - 1).append("\n"); // 아무것도 안 입는 경우는 빼어야 함.
		}
		System.out.println(sb);
	}
}
