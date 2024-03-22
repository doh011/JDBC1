package GOOGLE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GoogleSQL {

    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;


    public void connect() {
        con = DBC.DBConnect();
    }


    public void conClose(){
        try {
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void memberJoin(GoogleMember member) {

        try {

            String sql = "INSERT INTO GOOGLE VALUES(?, ? ,? ,? ,? ,? ,?)";


            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, member.getgId());
            pstmt.setString(2, member.getgPw());
            pstmt.setString(3, member.getgEmail());
            pstmt.setString(4, member.getgName());
            pstmt.setString(5, member.getgBirth());
            pstmt.setString(6, member.getgGender());
            pstmt.setString(7, member.getgPhone());
            int result = pstmt.executeUpdate();


            if(result > 0){
                System.out.println("가입 성공!");
            } else {
                System.out.println("가입 실패!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void memberList() {

        try {

            String sql = "SELECT * FROM GOOGLE";


            pstmt = con.prepareStatement(sql);


            rs = pstmt.executeQuery();


            while(rs.next()){
                System.out.print("| 아이디 : " + rs.getString(1));
                System.out.print("\t| 이름 : " + rs.getString(4));
                System.out.println("\t| 연락처 : " + rs.getString(7) + "\t|");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }

    public boolean memberLogin(String gId, String gPw) {
        boolean result = false;


        try {

            String sql = "SELECT * FROM GOOGLE WHERE GID=? AND GPW=?";


            pstmt = con.prepareStatement(sql);


            pstmt.setString(1, gId);
            pstmt.setString(2, gPw);


            rs = pstmt.executeQuery();


            result = rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public void myInfo(String gId) {

        try {

            String sql = "SELECT GID, GPW, GEMAIL, GNAME, TO_CHAR(GBIRTH, 'YYYY-MM-DD'), GGENDER, GPHONE FROM GOOGLE WHERE GID=?";


            pstmt = con.prepareStatement(sql);


            pstmt.setString(1, gId);


            rs = pstmt.executeQuery();


            if(rs.next()){
                System.out.println("아이디\t:\t" + rs.getString(1));
                System.out.println("비밀번호\t:\t" + rs.getString(2));
                System.out.println("이메일\t:\t" + rs.getString(3));
                System.out.println("이  름\t:\t" + rs.getString(4));
                System.out.println("생년월일\t:\t" + rs.getString(5));
                System.out.println("성  별\t:\t" + rs.getString(6));
                System.out.println("연락처\t:\t" + rs.getString(7));
                System.out.println();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public void memberModify(GoogleMember member) {
        try {

            String sql = "UPDATE GOOGLE SET GPW=?, GEMAIL=?, GNAME=?, GBIRTH=?, GGENDER=?, GPHONE=? WHERE GID=?";


            pstmt = con.prepareStatement(sql);




            pstmt.setString(1, member.getgPw());
            pstmt.setString(2, member.getgEmail());
            pstmt.setString(3, member.getgName());
            pstmt.setString(4, member.getgBirth());
            pstmt.setString(5, member.getgGender());
            pstmt.setString(6, member.getgPhone());


            pstmt.setString(7, member.getgId());


            int result = pstmt.executeUpdate();


            if(result > 0){
                System.out.println("수정 성공!");
            } else {
                System.out.println("수정 실패!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void memberDelete(String gId) {

        try {

            String sql = "DELETE FROM GOOGLE WHERE GID=?";


            pstmt = con.prepareStatement(sql);


            pstmt.setString(1, gId);


            int result = pstmt.executeUpdate();

            if(result>0){
                System.out.println("삭제 성공!");
            } else {
                System.out.println("삭제 실패!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
