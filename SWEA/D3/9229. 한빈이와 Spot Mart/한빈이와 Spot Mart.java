import java.util.Scanner;

public class Solution{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int TC = scanner.nextInt(); // 테스트 케이스의 수

        for (int testCase = 1; testCase <= TC; testCase++) {
            int N = scanner.nextInt(); // 과자 봉지의 개수
            int M = scanner.nextInt(); // 무게 합 제한
            int[] snacks = new int[N];

            for (int i = 0; i < N; i++) {
                snacks[i] = scanner.nextInt(); // 각 과자 봉지의 무게
            }

            System.out.println("#" + testCase + " " + getMaxWeight(snacks, N, M));
        }

        scanner.close();
    }

    private static int getMaxWeight(int[] snacks, int N, int M) {
        int maxWeight = -1;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                int currentWeight = snacks[i] + snacks[j];
                if (currentWeight <= M && currentWeight > maxWeight) {
                    maxWeight = currentWeight;
                }
            }
        }

        return maxWeight;
    }
}
