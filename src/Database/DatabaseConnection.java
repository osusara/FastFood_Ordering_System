package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {
    public static Statement getConnection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/restaurentsystem","root","1234");
        
        Statement statement = c.createStatement();
        return statement;
    }
}
