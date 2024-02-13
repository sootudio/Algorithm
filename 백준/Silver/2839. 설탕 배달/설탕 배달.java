import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int bag = 0;

		switch (N%5) {
		case 0:
			bag = N/5;
			break;
		case 1:
			bag = (N/5 -1) + 2;
			break;
		case 2:
			if(N/5 < 2) bag = -1;
			else bag = (N/5 -2) + 4;
			break;
		case 3:
			bag = N/5 + 1;
			break;
		case 4:
			if(N/5 < 1) bag = -1;
			else bag = (N/5 -1) + 3;
			break;
		}
		
		System.out.println(bag);
	}
	

}