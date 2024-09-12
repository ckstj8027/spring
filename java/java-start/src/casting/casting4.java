package casting;

public class casting4 {
    public static void main(String[] args) {
        int div1=3/2;

        double div2=3/2; // 근데 왜 1.0 이냐 하면 인트인트여서 1 인데
                        // 인트를 더블에 넣을라면 자동형 변환이 일어남 그래서 1.0으로 바뀜

        double div3=3.0/2;
        double div4=(double) 3/2;;


        System.out.println(div1);
        System.out.println(div2);
        System.out.println(div3);
        System.out.println(div4);
    }
}
