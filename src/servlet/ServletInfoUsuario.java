package servlet;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import negocio.UsuarioNegocio;
import entidades.Cliente;
import entidades.Usuario;

@WebServlet("/ServletInfoUsuario")
public class ServletInfoUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletInfoUsuario() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

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

            request.getRequestDispatcher("InfoUsuario.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al cargar la información del usuario.");
            request.getRequestDispatcher("InfoUsuario.jsp").forward(request, response);
        }
    }
}