import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entidades.Usuario;
import negocio.UsuarioNegocio;

/**
 * Servlet implementation class ServletMovimientos
 */
@WebServlet("/ServletMovimientos")
public class ServletMovimientos extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletMovimientos() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        // Obtener el usuario de la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuario");
     // Obtener el ID del usuario desde el objeto Usuario
        int idUsuario = usuario.getIdUsuario();

        // Llamar al negocio para obtener los números de cuenta
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
        List<Integer> numerosDeCuenta = usuarioNegocio.obtenerNumerosDeCuentaPorUsuario(idUsuario);

        // Establecer los números de cuenta en el request para que estén disponibles en la vista
        request.setAttribute("numerosDeCuenta", numerosDeCuenta);

        // Redirigir a la página de movimientos (movimientos.jsp)
        request.getRequestDispatcher("Movimientos_Clientes.jsp").forward(request, response);

    }

    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
