package negocio;

import dao.Conexion;
import dao.UsuarioDao;
import entidades.Cliente;
import entidades.Usuario;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Inegocio.INegUsuario;

public class UsuarioNegocio implements INegUsuario {

    private UsuarioDao daoUsuarios;

    public UsuarioNegocio() {
        this.daoUsuarios = new UsuarioDao();
    }

    @Override
    public Usuario obtenerUsuarioPorNombre(String nombreUsuario) {
        try {
            Usuario usuario = daoUsuarios.buscarUsuarioPorNombre(nombreUsuario);
            if (usuario != null) {
                System.out.println("Se encontró al usuario = " + usuario.toString());
                return usuario;
            } else {
                System.out.println("Usuario no encontrado: " + nombreUsuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String obtenerTipoDeUsuario(String nombreUsuario) {
        try {
            Usuario usuario = daoUsuarios.buscarUsuarioPorNombre(nombreUsuario);
            if (usuario != null) {
                System.out.println("Usuario encontrado: " + usuario.getNombre() + ", Tipo: " + usuario.getTipoUsuario());
                return usuario.getTipoUsuario();
            } else {
                System.out.println("Usuario no encontrado: " + nombreUsuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean devolverusuario(String nombre, String contra) {
        try {
            return daoUsuarios.VerificarExiste(nombre, contra);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean esAdministrador(String nombreUsuario, String contrasenia) {
        try {
            return daoUsuarios.buscarAdministrador(nombreUsuario, contrasenia);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public Cliente obtenerInformacionCliente(String nombreUsuario) {
        try {
            Cliente cliente = daoUsuarios.devolverInformacionUsuario(nombreUsuario);
            if (cliente != null) {
                System.out.println("Se encontró al cliente = " + cliente.toString());
                return cliente;
            } else {
                System.out.println("Cliente no encontrado: " + nombreUsuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public Map<String, Object> obtenerInformacionUsuario(long cuil) {
        try {
            return daoUsuarios.obtenerInformacionUsuario(cuil);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public boolean actualizarInformacionContacto(Cliente cliente) throws SQLException {
        boolean resultado = false;
        try {
            resultado = daoUsuarios.actualizarInformacionContacto(cliente);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error en UsuarioNegocio al actualizar la información de contacto", e);
        }
        return resultado;
    }
    
    @Override
    public boolean cambiarContrasenia(Usuario usuario, String contraActual, String nuevaContra) throws SQLException {
        if (usuario == null || contraActual == null || nuevaContra == null) {
            throw new IllegalArgumentException("El usuario y las contraseñas no pueden ser nulos");
        }

        boolean exito = false;
        try {
            exito = daoUsuarios.cambiarContrasenia(usuario.getIdUsuario(), contraActual, nuevaContra);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error en UsuarioNegocio al cambiar la contraseña", e);
        }
        return exito;
    }

	@Override
	 // Método para obtener los números de cuenta del cliente asociado a un usuario
    public List<Integer> obtenerNumerosDeCuentaPorUsuario(int idUsuario) {
        List<Integer> numerosDeCuenta = new ArrayList<>();
       
        numerosDeCuenta=daoUsuarios.obtenerNumerosDeCuentaPorUsuario(idUsuario);
        return numerosDeCuenta;
    
	}
	
	@Override
	public Integer obtenerIdUsuarioPorNombre(String nombreUsuario) {
	    try {
	        // Llamamos al método del DAO para obtener el ID de usuario por nombre
	        Integer idUsuario = daoUsuarios.obtenerIdUsuarioPorNombre(nombreUsuario);
	        
	        if (idUsuario != null) {
	            System.out.println("ID de usuario encontrado: " + idUsuario);
	            return idUsuario;
	        } else {
	            System.out.println("No se encontró un usuario con el nombre: " + nombreUsuario);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;  // Si no se encuentra el ID, devolver null
	}
	
	// Nuevo método para obtener números de cuenta del usuario
    public List<Integer> obtenerNumerosDeCuenta(int idUsuario) {
        try {
            return daoUsuarios.obtenerNumerosDeCuenta(idUsuario);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
 



}