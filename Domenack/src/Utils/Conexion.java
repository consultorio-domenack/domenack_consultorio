package Utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
    protected Connection cn = null;
    protected PreparedStatement ps = null;
    protected CallableStatement cs = null;
    protected ResultSet rs = null;
    
    private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //private static final String user = "sa";
    //private static final String password = "12345678";
    private static final String user = "domenack";
    private static final String password = "upn@2019";
            
    
    public static Connection conectar(){
        try {
            Class.forName(driver);
            return DriverManager.getConnection("jdbc:sqlserver://domenack-db.database.windows.net;database=Domenack_DB;user="+user+";Password="+password);
            //return DriverManager.getConnection("jdbc:sqlserver://localhost;database=Domenack_DB;user="+user+";Password="+password);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
}