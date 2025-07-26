package servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.NegocioCuenta;
import negocio.TipoCuentaNegocio;
import entidades.Cuenta;
import entidades.TipoCuenta;

/**
 * Servlet implementation class ServletModificarCuentas
 */
@WebServlet("/ServletModificarCuentas")
public class ServletModificarCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModificarCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String nroCuentaParam = request.getParameter("NroCuenta");
        
        if (nroCuentaParam != null) {
        	int nroCuenta = Integer.parseInt(nroCuentaParam);
        	NegocioCuenta negCuenta= new NegocioCuenta();
        	Cuenta cuenta= negCuenta.obtenerCuentaPorNumero(nroCuenta);
        	
        	TipoCuentaNegocio tipoCuentaNegocio = new TipoCuentaNegocio();
            List<TipoCuenta> tiposCuenta = tipoCuentaNegocio.listarTodoTipoCuenta(); // Obtener lista de tipos de cuenta

            request.setAttribute("cuenta", cuenta);
            request.setAttribute("tiposCuenta", tiposCuenta); // Pasar la lista a la vista
            request.getRequestDispatcher("ModificarCuenta.jsp").forward(request, response);
        }
               
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nroCuentaStr = request.getParameter("nroCuenta");
        String cuilCuStr = request.getParameter("cuilCu");
        String nroTipoCuentaStr = request.getParameter("nroTipoCuenta");
        String fechaCreacionStr = request.getParameter("fechaCreacion"); // readonly, pero aún puede enviarse
        String cbu = request.getParameter("cbu");
        String saldoStr = request.getParameter("saldo");

	        // Variables para almacenar valores convertidos
	        int nroCuenta = 0;
	        int nroTipoCuenta = 0;
	        long cuilCu = 0L;
	        double saldo = 0.0;
	        Date fechaCreacion = null;
	     

	        try {
	            if (nroCuentaStr != null && !nroCuentaStr.isEmpty()) {
	                nroCuenta = Integer.parseInt(nroCuentaStr);
	            }
	            if (cuilCuStr != null && !cuilCuStr.isEmpty()) {
	                cuilCu = Long.parseLong(cuilCuStr);
	            }
	            if (nroTipoCuentaStr != null && !nroTipoCuentaStr.isEmpty()) {
	                nroTipoCuenta = Integer.parseInt(nroTipoCuentaStr);
	            }
	            if (saldoStr != null && !saldoStr.isEmpty()) {
	                saldo = Double.parseDouble(saldoStr);
	            }
	            if (fechaCreacionStr != null && !fechaCreacionStr.isEmpty()) {
	                fechaCreacion = Date.valueOf(fechaCreacionStr); // Convierte String a java.sql.Date
	            }
	        } catch (NumberFormatException e) {
	            e.printStackTrace(); // Manejo de error en la conversión de datos numéricos
	            response.sendRedirect("error.jsp"); // Redirigir a una página de error si hay un fallo
	            return; // Salir del método para evitar ejecución innecesaria
	        }
	        
	        System.out.println("Modificando cuenta con Nro: " + nroCuenta);
            System.out.println("CUIL: " + cuilCu);
            System.out.println("Tipo de Cuenta: " + nroTipoCuenta);
            System.out.println("Fecha de Creación: " + fechaCreacion);
            System.out.println("CBU: " + cbu);
            System.out.println("Saldo: " + saldo);

	        
	        Cuenta cuenta = new Cuenta(
	        		nroCuenta,
	        		cuilCu,
	        		nroTipoCuenta,
	        		fechaCreacion,
	        		cbu,
	        		saldo
	        		
	        		);
	        NegocioCuenta negCuenta = new NegocioCuenta();
	        boolean actualizado = negCuenta.modificarCuenta(cuenta);
	        String mensaje;
	        if (actualizado) {
	            mensaje = "La cuenta fue actualizado exitosamente.";
	        } else {
	            mensaje = "Hubo un error al actualizar la cuenta. Por favor, inténtelo de nuevo.";
	        }
	        
	        response.sendRedirect("servletCuentaBancaria?Param=1&mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));

	}

}
