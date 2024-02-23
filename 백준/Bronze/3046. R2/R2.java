import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 두 정수 R1과 S를 입력받음
        int R1 = scanner.nextInt();
        int S = scanner.nextInt();

        // R2를 구하기 위해 평균값인 S에서 R1을 뺀 후 두 배를 함
        // 즉, R2 = 2 * S - R1
        int R2 = 2 * S - R1;

        // R2 출력
        System.out.println(R2);

        scanner.close();
    }
}