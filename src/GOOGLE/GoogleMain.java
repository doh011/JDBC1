package GOOGLE;

import GOOGLE.GoogleMember;
import GOOGLE.GoogleSQL;

import java.sql.Date;
import java.util.Scanner;




public class GoogleMain {

    public static void main(String[] args) {


        GoogleMember member = new GoogleMember();


        GoogleSQL sql = new GoogleSQL();


        Scanner sc = new Scanner(System.in);
        boolean run = true;
        int menu = 0;

        boolean run1 = true;
        int menu1 = 0;


        sql.connect();

        while(run){
            System.out.println("===========================");
            System.out.println("[1]회원가입\t\t[2]회원목록");
            System.out.println("[3]로그인\t\t[4]종료");
            System.out.println("===========================");
            System.out.print("선택 >> ");
            menu = sc.nextInt();

            switch (menu){
                case 1:
                    System.out.println("회원정보를 입력해주세요");

                    System.out.print("아이디 >> ");
                    String gId = sc.next();

                    System.out.print("비밀번호 >> ");
                    String gPw = sc.next();

                    System.out.print("이메일 >> ");
                    String gEmail = sc.next();

                    System.out.print("이름 >> ");
                    String gName = sc.next();

                    System.out.print("생년월일 >> ");
                    String gBirth = sc.next();

                    System.out.print("성별 >> ");
                    String gGender = sc.next();

                    System.out.print("연락처 >> ");
                    String gPhone = sc.next();


                    member.setgId(gId);
                    member.setgPw(gPw);
                    member.setgEmail(gEmail);
                    member.setgName(gName);
                    member.setgBirth(gBirth);
                    member.setgGender(gGender);
                    member.setgPhone(gPhone);


                    sql.memberJoin(member);
                    break;

                case 2:
                    sql.memberList();
                    break;

                case 3:
                    System.out.print("아이디 >> ");
                    gId = sc.next();
                    System.out.print("비밀번호 >> ");
                    gPw = sc.next();

                    boolean login = sql.memberLogin(gId, gPw);

                    if(login){
                        System.out.println("로그인 성공!");
                        run1 = true;

                        while(run1) {
                            System.out.println("===========================");
                            System.out.println("[1]내정보\t\t[2]회원수정");
                            System.out.println("[3]회원삭제\t\t[4]로그아웃");
                            System.out.println("===========================");
                            System.out.print("선택 >> ");
                            menu1 = sc.nextInt();

                            switch (menu1){
                                case 1:

                                    sql.myInfo(gId);
                                    break;
                                case 2:
                                    System.out.println("수정할 정보를 입력해주세요!");

                                    System.out.print("비밀번호 >> ");
                                    gPw = sc.next();

                                    System.out.print("이메일 >> ");
                                    gEmail = sc.next();

                                    System.out.print("이름 >> ");
                                    gName = sc.next();

                                    System.out.print("생년월일 >> ");
                                    gBirth = sc.next();

                                    System.out.print("성별 >> ");
                                    gGender = sc.next();

                                    System.out.print("연락처 >> ");
                                    gPhone = sc.next();


                                    member.setgId(gId);
                                    member.setgPw(gPw);
                                    member.setgEmail(gEmail);
                                    member.setgName(gName);
                                    member.setgBirth(gBirth);
                                    member.setgGender(gGender);
                                    member.setgPhone(gPhone);

                                    sql.memberModify(member);

                                    break;
                                case 3:
                                    System.out.println("정말 삭제하시겠습니까? (y/n)");
                                    String checkDelete = sc.next();

                                    switch (checkDelete){
                                        case "y":
                                            sql.memberDelete(gId);
                                            run1 = false;
                                            break;
                                        case "n":
                                            System.out.println("삭제를 취소합니다.");
                                            break;
                                        default:
                                            System.out.println("y와 n중에 입력해주세요.");
                                            break;
                                    }
                                    break;
                                case 4:
                                    System.out.println("로그아웃 합니다.");
                                    run1 = false;
                                    break;
                                default:
                                    System.out.println("다시 입력해주세요");
                                    break;
                            }
                        }

                    } else {
                        System.out.println("로그인 실패!");
                    }

                    break;

                case 4:
                    System.out.println("프로그램을 종료합니다.");
                    run = false;
                    sql.conClose();
                    break;

                default:
                    System.out.println("다시 입력해주세요.");
                    break;
            }
        }





    }




}
