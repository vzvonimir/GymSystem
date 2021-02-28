package app.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection extends Configs{

    public static Connection con;

    public Connection getConnection() throws ClassNotFoundException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String str = "jdbc:mysql://"+Configs.dbhost+"/"+Configs.dbname+"?" + "user="+Configs.dbuser+"&password="+Configs.dbpassword;

        try {
            con = DriverManager.getConnection(str);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return con;
    }

}
