import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		
		String str = sc.next();
		
		for(int i = 0 ; i < alphabet.length ; i++) {
			sb.append(str.indexOf(alphabet[i])).append(" ");
		}
		
		sb.setLength(sb.length() - 1);
		
		System.out.println(sb);

	}

}