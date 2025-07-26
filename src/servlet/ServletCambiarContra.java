package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Usuario;
import negocio.UsuarioNegocio;

@WebServlet("/ServletCambiarContra")
public class ServletCambiarContra extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletCambiarContra() {
        super();
    }

    @Override
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

        UsuarioNegocio usuarioNegocio = new UsuarioNegocio();

        try {
            // Validaciones de servidor
            if (!nuevaContra.equals(confirmNuevaContra)) {
                request.setAttribute("mensajeError", "Las nuevas contrase�as no coinciden.");
                request.getRequestDispatcher("CambiarContra.jsp").forward(request, response);
                return;
            }

            // Actualizar contrase�a
            boolean exito = usuarioNegocio.cambiarContrasenia(usuario, contraActual, nuevaContra);

            if (exito) {
                session.setAttribute("mensaje", "Contrase�a actualizada con �xito.");
                response.sendRedirect("ServletInfoUsuario");
            } else {
                request.setAttribute("mensajeError", "La contrase�a actual es incorrecta.");
                request.getRequestDispatcher("CambiarContra.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al actualizar la contrase�a.");
            request.getRequestDispatcher("CambiarContra.jsp").forward(request, response);
        }
    }
}