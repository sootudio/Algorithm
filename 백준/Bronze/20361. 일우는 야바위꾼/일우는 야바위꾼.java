import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
			int numOfCups = Integer.parseInt(st.nextToken()); // 종이컵 수
			int place = Integer.parseInt(st.nextToken()); // 종이컵 위치
			int replace = Integer.parseInt(st.nextToken()); // 맞바꾸는 횟수
			boolean[] cups = new boolean[numOfCups+1];
			cups[place] = true;
			
			for(int rp = 0 ; rp < replace ; rp++) {
				st = new StringTokenizer(br.readLine());
				int cupA = Integer.parseInt(st.nextToken());
				int cupB = Integer.parseInt(st.nextToken());
				
				// 1. 간식이 없는 컵끼리 바꾸는 경우
				if(!cups[cupA] && !cups[cupB]) {
					continue;
				}
				// 2. cupA에 간식이 있는 경우
				else if(cups[cupA] && !cups[cupB]) {
					cups[cupB] = true;
					cups[cupA] = false;
				}
				// 3. cupB에 간식이 있는 경우
				else if(!cups[cupA] && cups[cupB]) {
					cups[cupA] = true;
					cups[cupB] = false;
				}
			}
			
			int answer = 0; 
			for(int i = 0 ; i<= numOfCups ; i++) {
				if(cups[i]) {
					answer = i;
					break;
				}
			}
			System.out.println(answer);
		}
	

}