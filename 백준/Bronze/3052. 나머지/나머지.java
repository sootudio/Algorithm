import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Integer> list = new ArrayList<>();
		
		for(int i = 0 ; i < 10 ; i ++) {
			int input = sc.nextInt();
			
			int rem = input % 42;
			
			if(list.contains(rem)) continue;
			list.add(rem);
		}
		
		System.out.println(list.size());
		
		

	}

}