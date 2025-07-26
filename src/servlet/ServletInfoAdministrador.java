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

/**
 * Servlet implementation class ServletInfoAdministrador
 */
@WebServlet("/ServletInfoAdministrador")
public class ServletInfoAdministrador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInfoAdministrador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
	        Usuario usuario = (Usuario) session.getAttribute("usuario");

	        if (usuario == null) {
	            response.sendRedirect("InicioSesion.jsp");
	            return;
	        }

	        String nombreAdmin = usuario.getNombre();
	        System.out.println(nombreAdmin);
	        negocio.AdminitradorNegocio negAdministrador=new AdminitradorNegocio();
	        Administrador administrador=negAdministrador.DevolverInformacion(nombreAdmin);
	        
	        if(administrador==null) {
	        	request.setAttribute("mensajeError", "Cliente no encontrado.");
	            request.getRequestDispatcher("InfoAdministador.jsp").forward(request, response);
	            return;
	        }
	        
	        request.setAttribute("Administrador", administrador);
	        request.getRequestDispatcher("InfoAdministador.jsp").forward(request, response);
	        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
}