package BANK;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.SQLException;
import java.util.Scanner;

public class BankMain {

    public static void main(String[] args) throws SQLException {

        // 고객(BankClient) 객체 : client
        BankClient client = new BankClient();

        // SQL(BankSQL) 객체 : sql
        BankSQL sql = new BankSQL();

        // 입력(Scanner)을 위한 객체 : sc
        Scanner sc = new Scanner(System.in);

        // 프로그램 실행을 위한 변수 : run
        boolean run = true;

        // 메뉴를 입력받기 위한 변수 : menu
        int menu = 0;

        System.out.println("ICIA은행에 오신 것을 환영합니다");
        System.out.println("무엇을 도와드릴까요?");

        // while문 돌리기 전에 DB접속 실행
        sql.connect();

        // 프로그램이 계속 반복해서 실행되는 이유는? run = true 이기 때문에
        // 프로그램을 멈추려면 run을 false로 만들면 된다
        while(run){
            System.out.println("===============================");
            System.out.println("[1]생성\t\t[2]입급\t\t[3]출금");
            System.out.println("[4]송금\t\t[5]조회\t\t[6]종료");
            System.out.println("===============================");

            System.out.print("선택 >> ");
            menu = sc.nextInt();

            switch (menu){
                case 1:
                    // 고객번호(자동)
                    int cNum = sql.clientNumber()+1;

                    // 고객이름(작성)
                    System.out.print("고객이름 >> ");
                    String cName = sc.next();

                    // 계좌번호(자동)
                    String cAccount = sql.accountNumber();

                    // 잔액
                    int balance = 0;

                    client.setcNum(cNum);
                    client.setcName(cName);
                    client.setcAccount(cAccount);
                    client.setBalance(balance);

                    sql.joinClient(client);
                    break;
                case 2:
                    // (1) 입금할 계좌번호 입력
                    System.out.print("계좌번호 입력 >> ");
                    cAccount = sc.next();

                    // (2) 계좌번호 유무 확인
                    boolean checked = sql.checkAccount(cAccount);

                    // (3) 입금 진행
                    if(checked){    // 계좌가 존재하면 checked가 true
                        System.out.print("입금할 금액 >> ");
                        int amount = sc.nextInt();

                        sql.deposit(cAccount, amount);
                    } else {
                        System.out.println("계좌가 존재하지 않습니다.");
                    }

                    break;
                case 3:
                    // (1) 출금할 계좌번호 입력
                    System.out.print("계좌번호 입력 >> ");
                    cAccount = sc.next();

                    // (2) 계좌번호 유무 확인
                    checked = sql.checkAccount(cAccount);

                    // (3) 출금 진행
                    if(checked){    // 계좌가 존재하면 checked가 true
                        // (3-1) 출금액 입력
                        System.out.print("출금할 금액 >> ");
                        int amount = sc.nextInt();

                        // (3-2) 잔액확인 : BANKCLIENT 테이블에서 계좌번호를 통해 잔액을 조회
                        // SELECT BALANCE FROM BANKCLIENT WHERE CACCOUNT = ?
                        balance = sql.checkBalance(cAccount);

                        // if(잔액 >= 출금액)
                        if(balance >= amount){
                            // (3-3) 출금진행 : withdraw()
                            sql.withdraw(cAccount, amount);
                        } else {
                            //System.out.println("잔액이 부족합니다.");
                            System.out.println("잔액이 " + (amount-balance) + "원 부족합니다");
                        }
                    } else {
                        System.out.println("계좌가 존재하지 않습니다.");
                    }

                    // (3)출금진행

                    // (3-2) 잔액확인 : BANKCLIENT 테이블에서 계좌번호를 통해 잔액을 조회
                    // SELECT BALANCE FROM BANKCLIENT WHERE CACCOUNT = ?


                    break;
                case 4:
                    // 입력할 정보 보내는 사람 계좌, 받는 사람 계좌 , 송금액
                    System.out.print("보내는 사람 계좌번호 입력 >> ");
                    cAccount = sc.next();

                    System.out.print("받는사람 계좌번호 입력 >> ");
                    String cAccount1 = sc.next();
                  //  System.out.println("송금액 입력 ");
                    
                    // (2) 계좌번호 유무 확인
                    checked = sql.checkAccount(cAccount);
                    Boolean checked1 = sql.checkAccount(cAccount);

                    // (3) 출금 진행
                    if(checked) {
                        System.out.println("보내는사람 계좌 확인");
                    if (checked1) {
                        System.out.println("받는사람 계좌 확인");

                        balance = sql.checkBalance(cAccount);
                        // 송금액 입력
                        System.out.print("송금할 금액 >> ");
                        int amount = sc.nextInt();

                        if (balance >= amount) {
                            //  계좌에서 돈뺴기  : withdraw()
                            sql.withdraw(cAccount, amount);
                            // 계좌에 돈넣기 
                            sql.deposit(cAccount1, amount);

                            System.out.println("송금성공");
                        } else {
                            //System.out.println("잔액이 부족합니다.");
                            System.out.println("송금액이 " + (amount - balance) + "원 부족합니다");
                        }
                    }
                }else {
                        System.out.println("잘못입력");
                    }
                    // 보내는 사람 계좌 유뮤 확인
                    // 받는 사람계좌 유무 확인
                    // 보내는 사람의 잔액 확인 후 송금액 보다 많은지 확인
                    // 보내는 사람은 송금액 만큼 출금
                    // 받는 사라믕ㄴ 송금액 만큼 입금
                    break;
                case 5:
                    // (3-2) 잔액확인 : BANKCLIENT 테이블에서 계좌번호를 통해 잔액을 조회
                    // SELECT BALANCE FROM BANKCLIENT WHERE CACCOUNT = ?
                    System.out.print("계좌번호 입력 >> ");
                    cAccount = sc.next();
                    checked = sql.checkAccount(cAccount);
                    if (checked){
                        balance = sql.checkBalance(cAccount);
                        System.out.println("잔액 :" + balance + "원");
                    }else {
                        System.out.println("계좌가 존재하지 않습니다.");}
                    break;
                case 6:
                    run = false;
                    sql.conClose();
                    System.out.println("이용해 주셔서 감사합니다.");
                    break;
                default:
                    System.out.println("다시 입력해주세요");
                    break;
            }}}}