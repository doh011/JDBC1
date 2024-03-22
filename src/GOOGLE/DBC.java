package GOOGLE;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBC {
    public static Connection DBConnect(){
        // DB에 접속정보 저장을 위한 Connection객체 con선언
        Connection con = null;

        // DB에 접속할 계정정보
        String user = "DO";
        String password = "1111";

        // DB에 접속할 주소정보
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        // String url = "jdbc:oracle:thin:@192.168.0.34:1521:XE";


        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");


            con = DriverManager.getConnection(url, user, password);

          //  System.out.println("DB접속 성공");

        } catch (ClassNotFoundException e) {
            System.out.println("DB접속 실패 : 드라이버 로딩 실패");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("DB접속 실패 : 접속 정보 오류");
            throw new RuntimeException(e);
        }
        return con;
    }

}
