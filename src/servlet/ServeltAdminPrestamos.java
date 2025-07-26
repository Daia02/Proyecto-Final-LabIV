package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Cuenta;
import entidades.SolicitudPrestamo;
import entidades.Usuario;
import negocio.AdminPrestamosNegocio;
import negocio.PrestamosNegocio;

/**
 * Servlet implementation class ServeltAdminPrestamos
 */
@WebServlet("/ServeltAdminPrestamos")
public class ServeltAdminPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeltAdminPrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("toy en get");
    	HttpSession session = request.getSession(false);
        String usua = obtenerUsuarioDeSesion(session);

    
      if (usua.isEmpty()) {
            //response.sendRedirect("login.jsp"); // Redirigir al login si no hay sesión válida
            //return;
        }

        
       // System.out.println(usua + " enviar");
        cargardatos(request);
        request.getRequestDispatcher("/préstamos.jsp").forward(request, response);
    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession(false);
        String usua = obtenerUsuarioDeSesion(session);

        if (usua.isEmpty()) {
            response.sendRedirect("login.jsp"); 
        }
		
        manejarAcciones(request,response,usua);
	}
	
	private void manejarAcciones(HttpServletRequest request ,HttpServletResponse response,String admin)
	throws ServletException,IOException
	{
		negocio.AdminPrestamosNegocio adminPrestamosNegocio=new AdminPrestamosNegocio();
		
		if(request.getParameter("BtnAutorizar")!=null) {	
			
					cargarResquet(request);
			//boolean f =adminPrestamosNegocio.Actualizar_solicitud(numeroSolicitud,admin);
			//System.out.println(f);
			cargardatos(request);
			
			 
			request.setAttribute("mostrarModal", true);
			request.setAttribute("modal", "confirmModal1");
			 request.getRequestDispatcher("préstamos.jsp").forward(request, response);
			
		}
		if(request.getParameter("BtnRechazar")!=null) {
	
			cargarResquet(request);
			request.setAttribute("MostrarModalRechazar", true);
	
			cargardatos(request);
			request.getRequestDispatcher("préstamos.jsp").forward(request, response);
			return;
			
		}
		
		if(request.getParameter("btnConfirmarPrestamo")!=null) {
			System.out.println("holaa");
			int numeroSolicitud=Integer.parseInt(request.getParameter("NumeroSolicitud"));
			System.out.println(numeroSolicitud);
			boolean f=adminPrestamosNegocio.Actualizar_solicitud(numeroSolicitud, admin);
			cargardatos(request);
			if(f) {
				request.setAttribute("mensajeExito", "Prestamos Autorizado Correctamente");
				
			}
			else {
				request.setAttribute("mensajeError", "UPS hubo un error");
			}
			
			request.getRequestDispatcher("préstamos.jsp").forward(request, response);
			return;
		}
		if(request.getParameter("btnConfirmarRechazar")!=null) {
			int numeroSolicitud=Integer.parseInt(request.getParameter("NumeroSolicitud"));
			boolean f =adminPrestamosNegocio.Rechazar_Solicitud(numeroSolicitud, admin);
			System.out.println(f);
			if(f) {
				request.setAttribute("mensajeExito", "Prestamos Rechazado Correctamente");
				
			}
			else {
				request.setAttribute("mensajeError", "UPS hubo un error");
			}
			cargardatos(request);
			request.getRequestDispatcher("préstamos.jsp").forward(request, response);
		}
		
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
	private void cargardatos(HttpServletRequest request) {
        negocio.AdminPrestamosNegocio pres = new AdminPrestamosNegocio();
        List<SolicitudPrestamo> tipoCuentas = pres.lista();
       
        request.setAttribute("SolicitudPrestamo", tipoCuentas);
    }
private void  cargarResquet(HttpServletRequest request) {
	
	int numeroSolicitud=Integer.parseInt(request.getParameter("NumeroSolicitud"));
	
	String fecha=request.getParameter("fecha");
	String cuil=request.getParameter("Cuil");
	String Cuenta=request.getParameter("cuenta");
	String importe=request.getParameter("importe");
	String interes=request.getParameter("interes");
	String coutas =request.getParameter("coutas");
	String total=request.getParameter("Total");
	
	request.setAttribute("NumeroSolicitud", numeroSolicitud);
	
	request.setAttribute("fecha", fecha);
	request.setAttribute("Cuil", cuil);
	request.setAttribute("cuenta", Cuenta);
	request.setAttribute("importe",importe );
	request.setAttribute("interes", interes);
	request.setAttribute("coutas", coutas);
	request.setAttribute("Total", total);
	
}
	
}
