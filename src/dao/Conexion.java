package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion 
{
    private static Conexion instancia;
    private Connection connection;
    
    private Conexion()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_utnhomebanking?useSSL=false","root","root");
            this.connection.setAutoCommit(false);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static synchronized Conexion getConexion()   
    {                                
        if(instancia == null)
        {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection getSQLConexion() 
    {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_utnhomebanking?useSSL=false","root","root");
                this.connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.connection;
    }
    
    public void cerrarConexion()
    {
        try 
        {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        instancia = null;
    }
}


