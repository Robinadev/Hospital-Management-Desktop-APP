import java.sql.Connection;
import java.sql.DriverManager;



public class DB {
    String url = "jdbc:mysql://localhost:3306/hospitalms";
    String dbUsername = "root";
    String dbPassword = "root";
    private Connection connection;
    DB()
    {
        try
        {
            this.connection=DriverManager.getConnection(url, dbUsername, dbPassword);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    
    public Connection getConnection()
    {
        return connection;
    }
}
