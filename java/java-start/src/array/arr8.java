package array;

import java.util.Scanner;

public class arr8 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int[][] arr =new int[4][3];
        for(int row =0;row< arr.length ;row++){
            System.out.println((row+1)+"번 학생의 성적을 입력하세요");
            for(int col =0;col<arr[row].length ;col++){
                if (col==0){
                    System.out.print("국어점수: ");
                }
                if (col==1){
                    System.out.print("영어점수: ");
                }
                if (col==2){
                    System.out.print("수학점수: ");
                }




                arr[row][col]=scanner.nextInt();

            }
        }
        int total1=0;
        int total2=0;
        int total3=0;
        int total4=0;

        int avg1=0;
        int avg2=0;
        int avg3=0;
        int avg4=0;

        for(int row =0;row< arr.length ;row++){

            for(int col =0;col<arr[row].length ;col++){
                if(row==0){
                    total1+=arr[row][col];
                }
                if(row==1){
                    total2+=arr[row][col];
                }
                if(row==2){
                    total3+=arr[row][col];

                }
                if(row==3){
                    total4+=arr[row][col];

                }

                }


                }
        System.out.println("총점"+total1+"평균"+(double)total1/3);
        System.out.println("총점"+total2+"평균"+(double)total2/3);
        System.out.println("총점"+total3+"평균"+(double)total3/3);
        System.out.println("총점"+total4+"평균"+(double)total4/3);


            }


    }


