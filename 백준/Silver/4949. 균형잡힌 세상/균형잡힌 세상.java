import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			Deque<Character> st = new ArrayDeque<>();
			String str = br.readLine();
			if(str.charAt(0) == '.') break; // 마지막이므로 끝냄
			
			for(int i = 0 ; i < str.length() ; i++) {
				if(str.charAt(i) == '(' || str.charAt(i) == '[') {
					st.push(str.charAt(i));
				}
				else if(str.charAt(i) == ')' || str.charAt(i) == ']') {
					if(st.isEmpty()) { // 스택이 비어있을 경우 고려
						sb.append("no").append("\n");
						break;
					}
					else {
						if(str.charAt(i) == ')') {
							if(st.peek() == '(') {
								st.pop();
								continue;
							}
							else {
								sb.append("no").append("\n");
								break;
							}
						}
						else if(str.charAt(i) == ']') {
							if(st.peek() == '[') {
								st.pop();
								continue;
							}
							else {
								sb.append("no").append("\n");
								break;
							}
						}
					}
				}
				else if(str.charAt(i) == '.') {
					if(!st.isEmpty())sb.append("no").append("\n");
					else sb.append("yes").append("\n");
					continue;
				}
			}
			
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

}
