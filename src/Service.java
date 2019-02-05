import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Service {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "localhost";
    private static final String PORT = "3306";
    private static final String SID = "supermarket";
    private static final String USER = "root";
    private static final String PASSWORD = "jordan";
    private Connection connection = null;

    public Service() {
    }
    protected void connect() throws Exception {
        
        try {
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection("jdbc:mysql://"+URL+":"+PORT+"/"+SID, USER, PASSWORD);
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            throw new Exception("Driver Incorrecto");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("No se puede conectar a la base de datos.");
        }
    }
    
    protected void disconect() throws Exception {
        
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException ex) {
                throw new Exception("No se puede desconectar a la base de datos.");
            }
        }
    }
    
    protected  Connection getConnection() {
        return this.connection;
    }
}
