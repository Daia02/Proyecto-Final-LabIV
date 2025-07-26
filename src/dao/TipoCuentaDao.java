package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entidades.TipoCuenta;

public class TipoCuentaDao {
	
	public TipoCuentaDao() {
		
	}
	
	public ArrayList<TipoCuenta> obtenerTodosTipoCuenta(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<TipoCuenta> lista = new ArrayList<>();
		PreparedStatement statement;
		ResultSet rs;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement("SELECT * FROM Tipos_de_cuenta");
			rs = statement.executeQuery();
			while(rs.next()) {
				TipoCuenta aux = new TipoCuenta();
				aux.setNroTipoCuenta(rs.getInt("Nro_Tipo_cuenta"));
				aux.setTipoCuenta(rs.getString("Tipo_de_cuenta"));
				lista.add(aux);
			}
		}catch (Exception e) {
		
		}
		
		return lista;
	}
}