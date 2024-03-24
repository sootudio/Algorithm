import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < T ; i++) {
			int input = Integer.parseInt(br.readLine());
			
			change(input);
		}
		
	}

	private static void change(int input) {
		int quarter = 25;
		int dime = 10;
		int nickel = 5;
		int penny = 1;
		
		int q = input%quarter;
		int d = q%dime;
		int n = d%nickel;
		
		System.out.print(input / quarter + " ");
		System.out.print(q / dime +" ");
		System.out.print(d / nickel +" ");
		System.out.print(n / penny);
		System.out.println();
		
		return ;
		
	}

}