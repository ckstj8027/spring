package loop;

public class continue1 {
    public static void main(String[] args) {
        int i=1;
        int sum=0;


        while (sum<=60){
            sum+=i;



            if (sum==55) {
                continue;
            }



            System.out.println("  i "+i+"sum "+sum);
            i++;

            }
        }
    }

