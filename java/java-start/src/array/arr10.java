package array;

import java.util.Scanner;

public class arr10 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[][] arr =new int[n][3];
        String[] s= new String[] {"국어","영어","수학"};

        for(int row =0;row< arr.length ;row++){
            System.out.println((row+1)+"번 학생의 성적을 입력하세요");
            for(int col =0;col<arr[row].length ;col++){
                System.out.print(s[col]+"점수 :");




                arr[row][col]=scanner.nextInt();

            }
        }


        for(int row =0;row< arr.length ;row++){
            int total=0;

            for(int col =0;col<arr[row].length ;col++){
                total+=arr[row][col];


                }
            System.out.println(row+1+"번 학생의총점"+total+"평균"+(double)total/3);


                }


            }


    }


