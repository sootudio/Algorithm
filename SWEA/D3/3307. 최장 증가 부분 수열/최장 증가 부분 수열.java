import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int T; // 테스트 케이스
	static int N; //수열의 원소의 개수
	static List<Integer> lis;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int tc = 1 ; tc <= T ; tc++) {
			N = Integer.parseInt(br.readLine());
			lis = new ArrayList<>();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			lis.add(Integer.parseInt(st.nextToken())); // 첫 번째 원소 리스트에 넣어주기
			for(int i = 0 ; i < N-1 ; i++) {
				findLis(Integer.parseInt(st.nextToken()));
			}
			
			System.out.println("#"+tc+" "+lis.size());
		}

	}

	private static void findLis(int num) {
		if(num > lis.get(lis.size()-1)) {
			lis.add(num);
			return;
		}
		else {
			int idx = binSearch(num);
			lis.set(idx, Math.min(num, lis.get(idx)));
		}
	}

	private static int binSearch(int num) {
		int start = 0;
		int end = lis.size() - 1;
		while(start <= end) {
			int mid = start + (end - start)/2;
			if(lis.get(mid) > num)
				end = mid -1;
			else
				start = mid + 1;
		}
		return start;
	}

}