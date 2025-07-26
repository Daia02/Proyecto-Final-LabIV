package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Interfaces.IUbicacion;
import entidades.Nacionalidad;
import entidades.Provincia;
import entidades.Localidad;

public class UbicacionDao implements IUbicacion {

    private static final String queryNacionalidades = "SELECT Nro_Nacionalidad, Nacionalidad FROM Nacionalidad";
    private static final String queryProvincias = "SELECT Nro_Provincia, Nombre_Provincia, Nro_Nacionalidad FROM Provincia";
    private static final String queryLocalidades = "SELECT Nro_Localidad, Nro_Provincia, Nombre_Localidad FROM Localidad";

   
    //  obtener las nacionalidades
    @Override
    public List<Nacionalidad> obtenerNacionalidades() {
        List<Nacionalidad> nacionalidades = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            Conexion conexion = Conexion.getConexion(); // Obtener conexión
            statement = conexion.getSQLConexion().prepareStatement(queryNacionalidades);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int nroNacionalidad = resultSet.getInt("Nro_Nacionalidad");
                String nacionalidad = resultSet.getString("Nacionalidad");
                Nacionalidad nat = new Nacionalidad(nroNacionalidad, nacionalidad);
                nacionalidades.add(nat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(statement, resultSet);
        }

        return nacionalidades;
    }

    // obtener las provincias
    @Override
    public List<Provincia> obtenerProvincias() {
        List<Provincia> provincias = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            Conexion conexion = Conexion.getConexion();
            statement = conexion.getSQLConexion().prepareStatement(queryProvincias);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int nroProvincia = resultSet.getInt("Nro_Provincia");
                String nombreProvincia = resultSet.getString("Nombre_Provincia");
                int nroNacionalidad = resultSet.getInt("Nro_Nacionalidad");
                Provincia provincia = new Provincia(nroProvincia, nombreProvincia, nroNacionalidad);
                provincias.add(provincia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(statement, resultSet);
        }

        return provincias;
    }

    // obtener las localidades
    @Override
    public List<Localidad> obtenerLocalidades() {
        List<Localidad> localidades = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            Conexion conexion = Conexion.getConexion();
            statement = conexion.getSQLConexion().prepareStatement(queryLocalidades);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int nroLocalidad = resultSet.getInt("Nro_Localidad");
                int nroProvincia = resultSet.getInt("Nro_Provincia");
                String nombreLocalidad = resultSet.getString("Nombre_Localidad");
                Localidad localidad = new Localidad(nroLocalidad, nroProvincia, nombreLocalidad);
                localidades.add(localidad);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(statement, resultSet);
        }

        return localidades;
    }

    // Método para cerrar recursos
    private void closeResources(PreparedStatement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

