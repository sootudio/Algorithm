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
		
		Deque<Integer> deq = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0 ; i<cmd ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd1 = st.nextToken();
			
			switch (cmd1) {
			case "push_front": {
				int value = Integer.parseInt(st.nextToken());
				deq.addFirst(value);;
				break;
			}
			case "push_back": {
				int value = Integer.parseInt(st.nextToken());
				deq.addLast(value);;
				break;
			}
			case "pop_front": {
				if(deq.isEmpty())
					sb.append(-1).append("\n");
				else
					sb.append(deq.pollFirst()).append("\n");
				break;
			}
			case "pop_back": {
				if(deq.isEmpty())
					sb.append(-1).append("\n");
				else
					sb.append(deq.pollLast()).append("\n");
				break;
			}
			case "size": {
				sb.append(deq.size()).append("\n");
				break;
			}
			case "empty": {
				if(deq.isEmpty())
					sb.append(1).append("\n");
				else
					sb.append(0).append("\n");
				break;
			}
			case "front": {
				if(deq.isEmpty())
					sb.append(-1).append("\n");
				else
					sb.append(deq.peek()).append("\n");
				break;
			}
			case "back" : {
				if(deq.isEmpty())
					sb.append(-1).append("\n");
				else
					sb.append(deq.getLast()).append("\n");
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