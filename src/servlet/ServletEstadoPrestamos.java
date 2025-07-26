package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.SolicitudPrestamo;
import entidades.Usuario;
import negocio.PrestamosNegocio;

/**
 * Servlet implementation class ServletEstadoPrestamos
 */
@WebServlet("/ServletEstadoPrestamos")
public class ServletEstadoPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEstadoPrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		HttpSession session = request.getSession(false);
        String usuario = obtenerUsuarioDeSesion(session);

        if (usuario.isEmpty()) {
            response.sendRedirect("login.jsp"); 
        }
		
		cargardato(request, usuario);
		
		
		
		request.getRequestDispatcher("/ClienteEstadoPrestamos.jsp").forward(request, response);
	}
	
	private String obtenerUsuarioDeSesion(HttpSession session) {
        if (session != null) {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            if (usuario != null) {
                System.out.println("Usuario encontrado: " + usuario.getNombre());
                return usuario.getNombre();
            }
        }
        System.out.println("Usuario no encontrado en la sesión.");
        return "";
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
private void cargardato(HttpServletRequest request ,String usuario ){
	negocio.PrestamosNegocio neg=new PrestamosNegocio();
	List<SolicitudPrestamo> list=neg.ObtenerClienteEstadoPrestamo(usuario);
	
	request.setAttribute("lista", list);
	
	
}
}
