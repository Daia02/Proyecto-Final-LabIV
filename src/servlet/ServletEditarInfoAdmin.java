package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Administrador;
import entidades.Usuario;
import negocio.AdminitradorNegocio;

@WebServlet("/ServletEditarInfoAdmin")
public class ServletEditarInfoAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletEditarInfoAdmin() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            System.out.println("Usuario no autenticado, redirigiendo a InicioSesion.jsp");
            response.sendRedirect("InicioSesion.jsp");
            return;
        }

        String nombreAdmin = usuario.getNombre();
        System.out.println(nombreAdmin);
        AdminitradorNegocio negAdministrador = new AdminitradorNegocio();
        Administrador administrador = negAdministrador.DevolverInformacion(nombreAdmin);

        if (administrador == null) {
            System.out.println("Administrador no encontrado.");
            request.setAttribute("mensajeError", "Administrador no encontrado.");
            request.getRequestDispatcher("EditarInfoAdmin.jsp").forward(request, response);
            return;
        }

        System.out.println("Administrador encontrado: " + administrador.getNombre());
        request.setAttribute("administrador", administrador);
        request.getRequestDispatcher("EditarInfoAdmin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            System.out.println("Usuario no autenticado, redirigiendo a InicioSesion.jsp");
            response.sendRedirect("InicioSesion.jsp");
            return;
        }

        String nombreAdmin = usuario.getNombre();
        System.out.println("Nombre de usuario obtenido de la sesión: " + nombreAdmin);

        AdminitradorNegocio negAdministrador = new AdminitradorNegocio();
        Administrador administradorExistente = negAdministrador.DevolverInformacion(nombreAdmin);

        if (administradorExistente == null) {
            System.out.println("Administrador no encontrado.");
            session.setAttribute("mensajeError", "Administrador no encontrado.");
            response.sendRedirect(request.getContextPath() + "/ServletEditarInfoAdmin");
            return;
        }

        int idAdmin = administradorExistente.getIdUsuario();
        System.out.println("ID del administrador obtenido: " + idAdmin);

        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");

        System.out.println("Datos obtenidos del formulario - DNI: " + dni + ", Nombre: " + nombre + ", Apellido: " + apellido);

        Administrador administrador = new Administrador();
        administrador.setIdUsuario(idAdmin);
        administrador.setDni(dni);
        administrador.setNombre(nombre);
        administrador.setApellido(apellido);

        boolean actualizado = false;
        try {
            actualizado = negAdministrador.actualizarDatosPersonales(administrador);
            System.out.println("Resultado de la actualización: " + actualizado);

            if (actualizado) {
                System.out.println("Información actualizada con éxito.");
                session.setAttribute("mensaje", "Información actualizada con éxito.");
                response.sendRedirect(request.getContextPath() + "/ServletInfoAdministrador");
            } else {
                System.out.println("No se pudo actualizar la información.");
                session.setAttribute("mensajeError", "No se pudo actualizar la información.");
                response.sendRedirect(request.getContextPath() + "/ServletEditarInfoAdmin");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al actualizar la información del administrador.");
            session.setAttribute("mensajeError", "Error al actualizar la información del administrador.");
            response.sendRedirect(request.getContextPath() + "/ServletEditarInfoAdmin");
        }
    }
}