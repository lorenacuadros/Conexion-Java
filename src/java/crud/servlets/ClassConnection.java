package crud.servlets;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Familia
 */
public class ClassConnection {
    
    private Connection Conexion;
    
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/aurelia?user=root&password=1234&serverTimezone=UTC");
            System.out.println("Se ha iniciado la conexion con el servidor de forma exitosa");
        }
        catch (SQLException | ClassNotFoundException ex) {
            System.out.println("No se inicilizo la conexion");
        }
    }
    public void disconnect() {
        try {
            Conexion.close();
            System.out.println("Se ha finalizado la conexion con el servidor");
        }
        catch (SQLException ex){
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Connection getJdbcConnection () {
        return Conexion;
    }
}
