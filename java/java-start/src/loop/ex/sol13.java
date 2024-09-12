package loop.ex;

public class sol13 {
    public static void main(String[] args) {
        int N = 5; // N이 5인 경우

        for (int i = 1; i <= N; i++) {
            // 왼쪽 공백 출력
            for (int j = N - i; j > 0; j--) {
                System.out.print(" ");
            }

            // 별 출력
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }

            // 줄 바꿈
            System.out.println();
        }
    }

    }

