package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.UsuarioNegocio;
import entidades.Usuario;

@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletUsuario() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/InicioSesion.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreUsuario = request.getParameter("name");
        String password = request.getParameter("password");

        UsuarioNegocio usuarioNegocio = new UsuarioNegocio();

        System.out.println("Botón ingresar presionado");

        if (request.getParameter("btnIngresar") != null) {
            System.out.println("Botón ingresar presionadoss");

            // Verificar si es un administrador
            if (usuarioNegocio.esAdministrador(nombreUsuario, password)) {
                System.out.println("Usuario es administrador");
                Usuario admin = new Usuario();
                admin.setNombre(nombreUsuario);
                HttpSession session = request.getSession();
                session.setAttribute("usuario", admin);
                response.sendRedirect("adminHome.jsp");
                return;
            }

            // Verificar si es un usuario regular
            if (usuarioNegocio.devolverusuario(nombreUsuario, password)) {
                Usuario usuario = usuarioNegocio.obtenerUsuarioPorNombre(nombreUsuario);
                if (usuario != null) {
                	 System.out.println("Botón");
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", usuario);
                    System.out.println("Usuario es regular");
                    response.sendRedirect("usuarioHome.jsp");
                    return;
                }
            }

            // Si las credenciales no son válidas
            System.out.println("Usuario o contraseña incorrectos");
            request.setAttribute("errorMsg", "Usuario o contraseña incorrectos. Por favor, inténtelo de nuevo.");
            request.getRequestDispatcher("/InicioSesion.jsp").forward(request, response);
        }
    }
}
