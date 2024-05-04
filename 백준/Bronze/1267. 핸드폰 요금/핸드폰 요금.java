import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] calls = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			calls[i] = Integer.parseInt(st.nextToken());
		}
		
		int ys = 0;
		int ms = 0;
		
		for(int i = 0 ; i < N ; i++) {
			int yscall = calls[i];
			while(true) {
				if(yscall < 30) {
					ys +=10;
					break;
				}
				else {
					ys += 10;
					yscall -= 30;
				}
//				else if(yscall >= 30 && yscall <60){
//					ys += 20;
//					yscall -= 30;
//				}
//				else if(yscall >= 60) {
//					ys += 10;
//					yscall -= 30;
//				}
			}
		}
		
		for(int i = 0 ; i < N ; i++) {
			int mscall = calls[i];
			while(true) {
				if(mscall < 59) {
					ms +=15;
					break;
				}
//				else if(mscall >= 60 && mscall < 120){
//					ms += 30;
//					mscall -= 60;
//				}
//				else if(mscall >= 120) {
//					ms += 15;
//					mscall -= 60;
//				}
				else {
					ms += 15;
					mscall -= 60;
					
				}
			}
		}
		
		if(ys < ms) {
			System.out.println("Y "+ys);
		}
		else if(ys > ms) {
			System.out.println("M "+ms);
		}
		else if(ys == ms) {
			System.out.println("Y M "+ ys);
		}
		
		
	}

}