import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0 ; i < N ; i++) {
			int bonus = 0;
			int score = 0;
			String str = br.readLine();
			for(int j = 0 ; j < str.length() ; j++) {
				if(str.charAt(j) == 'O'){	
					score = score + 1 + bonus;
					bonus++;
				}
				else bonus = 0;
			}
			
			System.out.println(score);
				
		}

	}

}