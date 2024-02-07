import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> Math.abs(a) - Math.abs(b)==0 ? a-b : Math.abs(a) - Math.abs(b));
		
		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			int x = Integer.parseInt(br.readLine());
			if( x == 0) {
				// 
				if(pq.isEmpty()) System.out.println("0");
				else System.out.println(pq.poll());
			}
			else pq.add(x);
		}
	}

}