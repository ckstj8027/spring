package loop.ex;

public class sol6 {
    public static void main(String[] args) {
        int i=1;
        int count=0;
        while(true){
            i++;
            if(i%2==0){
                System.out.println(i);
                count++;
                continue;

            }
            else{
                if(count>=10){
                    break;
                }

            }
        }
    }
}
