package util;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection implements Closeable {

    private static DataBaseConnection instance;
    private Connection connection;
    String url= "jdbc:mysql://localhost:3306/shop?serverTimezone=CET";
    String user = "root";
    String password = "test";

    public DataBaseConnection(){
        try {
            this.connection= DriverManager.getConnection(url, user, password);
            System.out.println("connected");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public static DataBaseConnection getInstance() throws SQLException{
        if(instance== null){
            instance = new DataBaseConnection();
        }
        else if (instance.getConnection().isClosed()){
            instance = new DataBaseConnection();
        }
        return instance;
    }

    @Override
    public void close() throws IOException {
        try {
            this.connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
