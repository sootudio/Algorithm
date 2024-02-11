import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int total = 0;
		int score = 0;
		
		for(int i = 0 ; i<5 ; i++) {
			score = sc.nextInt();
			if(score >= 40) total+= score;
			else total += 40; 
		}
		
		System.out.println(total/5);

	}

}