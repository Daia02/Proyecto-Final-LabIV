package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.AdminitradorNegocio;
import entidades.Usuario;
import entidades.Administrador;

@WebServlet("/ServletCambiarContraAdmin")
public class ServletCambiarContraAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletCambiarContraAdmin() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("InicioSesion.jsp");
            return;
        }

        String contraActual = request.getParameter("contraActual");
        String nuevaContra = request.getParameter("nuevaContra");
        String confirmNuevaContra = request.getParameter("confirmNuevaContra");

        if (!nuevaContra.equals(confirmNuevaContra)) {
            request.setAttribute("mensajeError", "La nueva contraseña y su confirmación no coinciden.");
            request.getRequestDispatcher("CambiarContraAdmin.jsp").forward(request, response);
            return;
        }

        AdminitradorNegocio negAdministrador = new AdminitradorNegocio();
        Administrador administrador = negAdministrador.DevolverInformacion(usuario.getNombre());

        if (administrador == null) {
            System.out.println("Administrador no encontrado.");
            session.setAttribute("mensajeError", "Administrador no encontrado.");
            response.sendRedirect(request.getContextPath() + "/CambiarContraAdmin.jsp");
            return;
        }

        int adminID = administrador.getIdUsuario();
        System.out.println("ID del administrador obtenido: " + adminID);

        boolean cambiado = false;
        try {
            cambiado = negAdministrador.cambiarContraseniaAdmin(adminID, contraActual, nuevaContra);
            System.out.println("Resultado del cambio de contraseña: " + cambiado);

            if (cambiado) {
                session.setAttribute("mensaje", "Contraseña actualizada con éxito.");
                response.sendRedirect(request.getContextPath() + "/ServletInfoAdministrador");
            } else {
                request.setAttribute("mensajeError", "La contraseña actual es incorrecta.");
                request.getRequestDispatcher("CambiarContraAdmin.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al cambiar la contraseña del administrador.");
            request.getRequestDispatcher("CambiarContraAdmin.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}