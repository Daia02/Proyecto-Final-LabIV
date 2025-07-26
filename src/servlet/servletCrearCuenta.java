package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import entidades.Cuenta;
import negocio.NegocioCuenta;

/**
 * Servlet implementation class servletCrearCuenta
 */
@WebServlet("/servletCrearCuenta")
public class servletCrearCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletCrearCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher rd = request.getRequestDispatcher("Alta-CuentaCliente.jsp");
			rd.forward(request, response);
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnCrear")!=null) {
			NegocioCuenta negCuenta = new NegocioCuenta();
			Cuenta crearCuenta = new Cuenta();
			 
	        
			crearCuenta.setNroCuenta(Integer.parseInt(request.getParameter("txtIDCuenta")));
			String cuilString = ((Cliente) request.getSession().getAttribute("sessionCliente")).getCuilCl();
			long cuilLong = Long.parseLong(cuilString);
			crearCuenta.setCuilCu(cuilLong);

			crearCuenta.setNroTipoCuenta(Integer.parseInt(request.getParameter("tipoUsuario")));
			crearCuenta.setFechaCreacion(java.sql.Date.valueOf(request.getParameter("txtFecha").toString()));
			crearCuenta.setCbu((String)request.getParameter("txtCBU"));
			crearCuenta.setSaldo(10000.00);
			
			// Imprimir el estado del objeto crearCuenta para verificar que se cargaron correctamente
			System.out.println("Objeto Cuenta:");
			System.out.println("Nro Cuenta: " + crearCuenta.getNroCuenta());
			System.out.println("Cuil Cliente: " + crearCuenta.getCuilCu());
			System.out.println("Tipo Cuenta: " + crearCuenta.getNroTipoCuenta());
			System.out.println("Fecha Creación: " + crearCuenta.getFechaCreacion());
			System.out.println("CBU: " + crearCuenta.getCbu());
			System.out.println("Saldo: " + crearCuenta.getSaldo());
			
			String contrasenia = request.getParameter("txtContra");
			String repetida = request.getParameter("txtContraRepetir");
			if(!(contrasenia.equals(repetida))) {
				response.sendRedirect("ErrorContrasenia.jsp");
				return;
			}
			
			if(negCuenta.cuentasTotales(crearCuenta.getCuilCu()) > 3) {
			    request.setAttribute("resultadoCuenta", "El cliente ya posee el máximo de cuentas.");
			    request.getRequestDispatcher("Alta-CuentaCliente.jsp").forward(request, response);
			    return;
			}
			
			if(negCuenta.asignarCuenta(crearCuenta)) {
				request.setAttribute("resultadoCuenta", "Se agrego la nueva cuenta.");
	            request.getRequestDispatcher("Alta-CuentaCliente.jsp").forward(request, response);
			}else {
				request.setAttribute("resultadoCuenta", "No se agrego la nueva cuenta.");
	            request.getRequestDispatcher("Alta-CuentaCliente.jsp").forward(request, response);
	           

			}
			
		}
		
	}

}

