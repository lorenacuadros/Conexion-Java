/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import crud.model.Notificacion;
import crud.servlets.ClassConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotifDAO {
    private ClassConnection con;
    private Connection connection;
    
    public NotifDAO() {
        con = new ClassConnection();
    }
    
    public boolean insert(Notificacion notifi) throws SQLException {
        String sql = "insert into notificacion (texto, autor, fecha_inicio, fecha_fin) values (?, 1, ?, ?)";
        
        con.connect();
        connection = con.getJdbcConnection();
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, notifi.getMensaje());
        statement.setString(2, notifi.getInicio().toString());
        statement.setString(3, notifi.getFin().toString());
        
        return statement.executeUpdate() > 0;
    }
    
    public List<Notificacion> listNotifi() throws SQLException {
        List<Notificacion> listNotifi = new ArrayList<Notificacion>();
        String sql = "select * from notificacion";
        con.connect();
        connection = con.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
            int id = resultSet.getInt("id");
            String mensaje = resultSet.getString("texto");
            int autor = resultSet.getInt("autor");
            String inicio = resultSet.getString("fecha_inicio");
            String fin = resultSet.getString("fecha_fin");

            Notificacion notificacion = new Notificacion(id, mensaje, LocalDateTime.parse(inicio, formato), LocalDateTime.parse(fin, formato),  autor);

            listNotifi.add(notificacion);
        }
        
        return listNotifi;
    }
    
    public boolean delete(Notificacion notificacion) throws SQLException {
        boolean rowDelete = false;
        String sql = "delete from notificacion where id = ?";
        con.connect();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, notificacion.getId());
        
        rowDelete = statement.executeUpdate() > 0;
        statement.close();
        con.disconnect();
        
        return rowDelete;
    }
    
    public Notificacion getById(int id) throws SQLException {
        Notificacion notificacion = null;
        String sql = "select * from notificacion where id = " + id;
        con.connect();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        ResultSet res = statement.executeQuery();

        while (res.next()) {
            notificacion = new Notificacion(res.getInt("id"), res.getString("texto"), LocalDateTime.parse(res.getString("fecha_inicio"), formato), LocalDateTime.parse(res.getString("fecha_fin"), formato), res.getInt("autor"));
        }

        res.close();
        con.disconnect();
        
        return notificacion;
    }
    
    public boolean update (Notificacion notificacion) throws SQLException {
        boolean rowUpdate = false;
        String sql = "update notificacion set texto = ?,  fecha_inicio = ?, fecha_fin = ? where id = ?";
        con.connect();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, notificacion.getMensaje());
        statement.setString(2, notificacion.getInicio().toString());
        statement.setString(3, notificacion.getFin().toString());
        statement.setInt(4, notificacion.getId());

        rowUpdate = statement.executeUpdate() > 0;
        statement.close();
        con.disconnect();
        return rowUpdate;
    }
}
