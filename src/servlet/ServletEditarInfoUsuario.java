package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Cliente;
import entidades.Localidad;
import entidades.Provincia;
import entidades.Usuario;
import negocio.AltaClienteNegocio;
import negocio.UsuarioNegocio;

/**
 * Servlet implementation class ServletEditarInfoUsuario
 */
@WebServlet("/ServletEditarInfoUsuario")
public class ServletEditarInfoUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletEditarInfoUsuario() {
        super();
    }

    private void cargarDatos(HttpServletRequest request) {
        AltaClienteNegocio neg = new AltaClienteNegocio();
        List<Provincia> provincias = neg.ObtenerProvincias();
        List<Localidad> localidades = neg.ObtenerLocalidades();

        // Pasar las listas al JSP
        request.setAttribute("provincias", provincias);
        request.setAttribute("localidades", localidades);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        System.out.println("Entro al servlet: " + usuario.getNombre());

        if (usuario == null) {
            response.sendRedirect("InicioSesion.jsp");
            return;
        }

        String nombreUsuario = usuario.getNombre();

        UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
        Cliente cliente = usuarioNegocio.obtenerInformacionCliente(nombreUsuario);

        if (cliente == null) {
            request.setAttribute("mensajeError", "Cliente no encontrado.");
            request.getRequestDispatcher("InfoUsuario.jsp").forward(request, response);
            return;
        }

        String cuilCliente = cliente.getCuilCl();

        try {
            Map<String, Object> informacionUsuario = usuarioNegocio.obtenerInformacionUsuario(Long.parseLong(cuilCliente));
            request.setAttribute("informacionUsuario", informacionUsuario);

            // Cargar datos adicionales (provincias y localidades)
            cargarDatos(request);

            request.getRequestDispatcher("EditarInfoContacto.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al cargar la información del usuario.");
            request.getRequestDispatcher("EditarInfoContacto.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        System.out.println("Entro al do post del servlet ServletEditarInfoUsuario");

        if (usuario == null) {
            response.sendRedirect("InicioSesion.jsp");
            return;
        }

        String nombreUsuario = usuario.getNombre();
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
        Cliente cliente = usuarioNegocio.obtenerInformacionCliente(nombreUsuario);

        if (cliente == null) {
            request.setAttribute("mensajeError", "Cliente no encontrado.");
            request.getRequestDispatcher("EditarInfoContacto.jsp").forward(request, response);
            return;
        }

        try {
            // Obtener datos del formulario
            String direccion = request.getParameter("direccion");
            String correoElectronico = request.getParameter("correoElectronico");
            String telefono = request.getParameter("telefono");
            String nroLocalidad = request.getParameter("selectLocalidad");
            String nroProvincia = request.getParameter("selectProvincia");

            // Crear una instancia de Cliente con los datos actualizados
            cliente.setDireccion(direccion);
            cliente.setCorreoElectronico(correoElectronico);
            cliente.setTelefono(telefono);
            cliente.setNroLocalidad(nroLocalidad);
            cliente.setNroProvincia(nroProvincia);

            System.out.println("Datos obtenidos del formulario: ");
            System.out.println("Dirección: " + direccion);
            System.out.println("Correo Electrónico: " + correoElectronico);
            System.out.println("Teléfono: " + telefono);
            System.out.println("Nro Localidad: " + nroLocalidad);
            System.out.println("Nro Provincia: " + nroProvincia);

            // Actualizar la información del cliente
            boolean exito = usuarioNegocio.actualizarInformacionContacto(cliente);

            if (exito) {
                request.getSession().setAttribute("mensaje", "Información actualizada con éxito.");
            } else {
                request.getSession().setAttribute("mensajeError", "No se pudo actualizar la información.");
            }

            response.sendRedirect(request.getContextPath() + "/ServletInfoUsuario");
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("mensajeError", "Error al actualizar la información de contacto.");
            response.sendRedirect(request.getContextPath() + "/EditarInfoContacto.jsp");
        }
    }



}