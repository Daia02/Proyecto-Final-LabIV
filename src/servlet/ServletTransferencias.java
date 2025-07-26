package servlet;

import negocio.NegocioCuenta;
import negocio.TransferenciaNegocio;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Cuenta;
import entidades.Usuario;
import negocio.TransferenciaNegocio;;
/**
 * Servlet implementation class ServletTransferencias
 */
@WebServlet("/ServletTransferencias")
public class ServletTransferencias extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTransferencias() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 HttpSession session = request.getSession();
    	    Usuario usuario = (Usuario) session.getAttribute("usuario");

    	    TransferenciaNegocio transferenciaNegocio = new TransferenciaNegocio();
    	    
    	    try {
    	        if (usuario == null) {
    	            throw new Exception("El usuario no está en sesión.");
    	        }
    	        
    	        int idUsuario = usuario.getIdUsuario();
    	        System.out.println("Obteniendo cuentas para el usuario con ID: " + idUsuario);

    	        List<Cuenta> cuentas = transferenciaNegocio.obtenerCuentasPorUsuario(idUsuario);
    	        
    	        if (cuentas.isEmpty()) {
    	            System.out.println("No se encontraron cuentas para el usuario ID: " + idUsuario);
    	        } else {
    	            System.out.println("Cuentas obtenidas: " + cuentas.size());
    	            for (Cuenta cuenta : cuentas) {
    	                System.out.println("Cuenta: " + cuenta.getNroCuenta() + " - Saldo: " + cuenta.getSaldo());
    	            }
    	        }
    	        
    	        // Guardar las cuentas en sesión para usarlas en el JSP
    	        session.setAttribute("cuentasDisponibles", cuentas);
    	        System.out.println("Cuentas guardadas en sesión: " + session.getAttribute("cuentasDisponibles"));

    	    } catch (Exception e) {
    	        System.err.println("Error al obtener las cuentas: " + e.getMessage());
    	        e.printStackTrace();
    	        
    	        request.setAttribute("mensaje", "Error al obtener las cuentas: " + e.getMessage());
    	        request.setAttribute("tipoMensaje", "danger");
    	    }


    	    // Redirigir al JSP
    	    request.getRequestDispatcher("Transferencias.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //doGet(request, response);
    	 int nroCuentaOrigen = Integer.parseInt(request.getParameter("nroCuentaOrigen"));
    	 // Obtener los parámetros del formulario
       
        String cbuDestino = request.getParameter("cbuDestino");
        double monto = Double.parseDouble(request.getParameter("monto"));

        
        
        TransferenciaNegocio transferenciaNegocio = new TransferenciaNegocio();
        
        NegocioCuenta negCuenta= new NegocioCuenta();

        // Llamamos al método para obtener el CBU de la cuenta origen
        String cbuOrigen = negCuenta.obtenerCbuPorNumeroCuenta(nroCuentaOrigen);
        
        try {
            // Llamar a la lógica de negocio para realizar la transferencia
            transferenciaNegocio.realizarTransferencia(cbuOrigen, cbuDestino, monto);
            
            // Si la transferencia fue exitosa, establecer mensaje de éxito
            request.setAttribute("mensaje", "La transferencia se ha realizado con éxito.");
            request.setAttribute("tipoMensaje", "success");

        } catch (Exception e) {
            // Manejar otros tipos de errores
            request.setAttribute("mensaje", "Error al realizar la transferencia: " + e.getMessage());
            request.setAttribute("tipoMensaje", "danger");
        }

        // Redirigir de nuevo al JSP de transferencias
       // request.getRequestDispatcher("Transferencias.jsp").forward(request, response);
        //response.sendRedirect("ServletTransferencias");
        doGet(request, response);
        
    }
    }

