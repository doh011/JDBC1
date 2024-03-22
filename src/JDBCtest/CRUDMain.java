package JDBCtest;

import java.util.Scanner;

public class CRUDMain {

    public static void main(String[] args) {

        // CRUD 객체 생성
        // 필드 생성자 메소드
        CRUD crud = new CRUD();




        // crud.connect() ,DB 접속을 한 후에

        //crud.insert();
         //
   //    crud.delete();


        // crud.insert()  ;
       // crud.select();

    //    crud.selectEMP();



      //  crud.conClose();

// crud.selctEMP();
        //EMP테이블 조회


        Scanner sc =new Scanner(System.in);
        boolean run = true;
        int menu =0;


        while (run){
            System.out.println("===============");
            System.out.println("[1]connect\t\t[2]insert\t\t[3]select");
            System.out.println("[4]update\t\t[5]delete\t\t[6]close");
            System.out.println("===============");
            System.out.println("선택해라 ");

            menu =sc.nextInt();

            switch (menu){
                case 1:
                    crud.connect();
                    break;
                case 2:
                    crud.insert()  ;
                    break;
                case 3:
                    crud.select();
                    break;
                case 4:
                    crud.update();
                    break;
                case 5:
                    crud.delete();
                    break;
                case 6:
                    crud.conClose();
                    break;
                default:
                    System.out.println("잘못입력");
                    break;
            }

        }





    }
}
