package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SolicitudPrestamosDao;
import entidades.Administrador;
import entidades.Cliente;
import entidades.SolicitudPrestamo;
import entidades.Usuario;
import negocio.AdminPrestamosNegocio;
import negocio.AdminitradorNegocio;
import negocio.NegocioCliente;

/**
 * Servlet implementation class ServletAdminHistorialPrestamo
 */
@WebServlet("/ServletAdminHistorialPrestamo")
public class ServletAdminHistorialPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAdminHistorialPrestamo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		cargarDatos(request);
		
		request.getRequestDispatcher("/AdminHistorialPrestamos.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("VerDetalles")!=null) {
			
			HttpSession session = request.getSession(false);
	        String Admin = obtenerUsuarioDeSesion(session);
	        String cuil=request.getParameter("Cuil");
	        negocio.AdminPrestamosNegocio negAdminitrador=new AdminPrestamosNegocio();
	        
			SolicitudPrestamo solicitudPrestamo=negAdminitrador.infoVerDetalle(cuil, Admin);
			System.out.println(solicitudPrestamo.getAdministrador().getNombre());
			
			BigDecimal Importe= new BigDecimal(request.getParameter("Importe"));
			BigDecimal Total= new BigDecimal(request.getParameter("Total"));
			
			
			request.setAttribute("VerDetalles","1");
			
		}
		
		
		doGet(request, response);
	}
private void cargarDatos(HttpServletRequest request) {
	dao.SolicitudPrestamosDao dao=new SolicitudPrestamosDao();
	List<SolicitudPrestamo> lista=dao.obtenerHistorialPrestamos();
	
	request.setAttribute("lista", lista);
	
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
}
