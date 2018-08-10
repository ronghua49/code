import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Jdbc{
    @Test
    public void jdbc() throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql:///mydb","root","root");
            con.setAutoCommit(false);
            String sql = "update t_book set name=? where id=?";
            PreparedStatement pstat = con.prepareStatement(sql);

            pstat.setString(1,"番茄做法");
            pstat.setInt(2,24);
            pstat.execute();

           pstat.setString(1,"把时间当做朋友2");
           pstat.setInt(2,23);
           pstat.execute();
           con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            System.out.println(e.getMessage());


        }
    }

}
