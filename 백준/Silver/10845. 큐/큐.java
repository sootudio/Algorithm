import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cmd = Integer.parseInt(br.readLine());
		
		Deque<Integer> q = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0 ; i<cmd ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd1 = st.nextToken();
			
			switch (cmd1) {
			case "push": {
				int value = Integer.parseInt(st.nextToken());
				q.offer(value);
				break;
			}
			case "pop": {
				if(q.isEmpty())
					sb.append(-1).append("\n");
				else
					sb.append(q.pop()).append("\n");
				break;
			}
			case "size": {
				sb.append(q.size()).append("\n");
				break;
			}
			case "empty": {
				if(q.isEmpty())
					sb.append(1).append("\n");
				else
					sb.append(0).append("\n");
				break;
			}
			case "front": {
				if(q.isEmpty())
					sb.append(-1).append("\n");
				else
					sb.append(q.peek()).append("\n");
				break;
			}
			case "back" : {
				if(q.isEmpty())
					sb.append(-1).append("\n");
				else
					sb.append(q.getLast()).append("\n");
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + cmd1);
			}
		}
		if(sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb);
	}

}