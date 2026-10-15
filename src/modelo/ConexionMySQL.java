package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    private String bd="helpdesk2";
    private String usuario="root";
    private String password="";
    private String url="jdbc:mysql://localhost:3306/" + bd;
    
    Connection con = null;
    
    public Connection getConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexion exitosa");
        }
        catch(ClassNotFoundException e){
            System.err.println("No se encontro el Driver MySQL. " + e.getMessage());
        }catch(SQLException e){
            System.err.println("Error al conectar con la base de datos " + e.getMessage());
            System.err.println(e.getMessage());
        }
        return con;
    }
}
