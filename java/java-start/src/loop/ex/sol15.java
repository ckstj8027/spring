package loop.ex;

public class sol15 {
    public static void main(String[] args) {
        int n=5;
        int m=2*n-1; //9    / 2   4
        int rl=m/2;// 4




        for (int i=1;i<=n  ; i ++){
            for(int j=rl+1-i;  j>=0; j--){
                System.out.print(" ");


            }
            for(int k=2*i-1; k>0;k-- ){
                System.out.print("*");

            }
            System.out.println( );

        }


    }
}
