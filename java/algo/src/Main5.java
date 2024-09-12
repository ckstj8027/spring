import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import java.io.*;
import java.util.*;
import java.lang.*;

public class Main5 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb=new StringBuilder();
        StringBuilder sb1=new StringBuilder();
        StringBuilder sb3=new StringBuilder();
        StringBuilder sb4=new StringBuilder();

      //  int n = Integer.parseInt(st.nextToken());

        ///123 +321   = 444
        // 2 9 10 21 24    10212249 +  92422110
        List<Integer> a=new ArrayList<>();
        List<Integer> aa=new ArrayList<>();

        int n=st.countTokens();
        for(int i=0;i<n;i++){
            int b=Integer.parseInt(st.nextToken());

            a.add(b);

        }
        Collections.sort(a);


        int k=0;
        for(int i=0;i<n;i++){
            if(a.get(i)>10){
                k=1;
            }
            if(a.get(i)>100){
                k=2;
            }
        }


        if(k==0){

            Collections.sort(a);

            for(int i=0;i<n;i++){

                sb3.append(String.valueOf(a.get(i)));

            }
            for(int i=n-1;i>=0;i--){

                sb4.append(String.valueOf(a.get(i)));

            }
            System.out.println(Integer.parseInt(String.valueOf(sb3))+Integer.parseInt(String.valueOf(sb4)));


        }



        else {
            double d=Math.pow(10,k);

            for(int i=0;i<n;i++){
                if (a.get(i)<10){
                    if(k==1){
                        aa.add((int) d*a.get(i)+ a.get(i));

                    }
                    if(k==2){
                        aa.add((int) d*a.get(i)+ (int )(d-1)*a.get(i)+a.get(i));
                    }


                }
                else if(a.get(i)<100){

                    if(k==2){
                        aa.add(a.get(i)*10);
                    }
                    else{
                        aa.add(a.get(i));
                    }

                }
                else {
                    aa.add(a.get(i));
                }
            }
            Map<Integer,Integer> memo=new HashMap<>();
            for(int i=0;i<n;i++){
                memo.put(aa.get(i),i);

            }


            Collections.sort(aa);




            for(int i=n-1;i>=0;i--){
                sb.append(String.valueOf(a.get(memo.get(aa.get(i)))));

            }

            ////////////////////////////



            //Collections.sort(aa,Collections.reverseOrder());





            for(int i=0;i<n;i++){

                sb1.append(String.valueOf(a.get(memo.get(aa.get(i)))));

            }


            // System.out.println(sb);
            // System.out.println(sb1);
            System.out.println(Integer.parseInt(String.valueOf(sb))+Integer.parseInt(String.valueOf(sb1)));
            //  System.out.println(a);

            //10 21 2 24 9
        }














        }










    }







