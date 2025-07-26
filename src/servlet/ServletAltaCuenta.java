package servlet;
import java.util.List;
import java.io.IOException;

import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.NegocioCliente;
import negocio.NegocioCuenta;
import negocio.TipoCuentaNegocio;
import entidades.TipoCuenta;
import entidades.Cuenta;

/**
 * Servlet implementation class ServletAltaCuenta
 */
@WebServlet("/ServletAltaCuenta")
public class ServletAltaCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAltaCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		
		 NegocioCliente neg = new NegocioCliente();
	        TipoCuentaNegocio tipoCuentaNegocio = new TipoCuentaNegocio();
	        NegocioCuenta negocioCuenta = new NegocioCuenta();  // Crear instancia de NegocioCuenta

	        List<TipoCuenta> tiposCuenta = tipoCuentaNegocio.listarTodoTipoCuenta();
	        List<String> cuiles = neg.obtenerTodosLosCuils();
	        int ultimoIDCuenta = negocioCuenta.cuentaUltimoId();  // Obtener el último ID de cuenta

	        // Depuración en consola
	        System.out.println("Entrando en el método doGet alta cuenta del servlet.");
	        System.out.println("Último ID de cuenta generado: " + ultimoIDCuenta);

	        request.setAttribute("tiposCuenta", tiposCuenta);
	        request.setAttribute("cuiles", cuiles);
	        request.setAttribute("ultimoIDCuenta", ultimoIDCuenta);

	        request.getRequestDispatcher("/Alta_Cuentas_Clientes.jsp").forward(request, response);
	        
	        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
		        // Capturar datos del formulario
		        int nroCuenta = Integer.parseInt(request.getParameter("nroCuenta"));
		        long cuilCu = Long.parseLong(request.getParameter("selectCuil"));
		        int nroTipoCuenta = Integer.parseInt(request.getParameter("selectTipoCuenta"));
		        String cbu = request.getParameter("txtCBU");
		        double saldo = Double.parseDouble(request.getParameter("txtSaldo"));
		        Date fechaCreacion = Date.valueOf(request.getParameter("txtFechaCreacion")); 

		        // Verificar si el CBU ya está registrado en la base de datos
		        NegocioCuenta cuentaNegocio = new NegocioCuenta();
		        boolean cbuRepetido = cuentaNegocio.verificarCbuRepetido(cbu); // Llama a la función verificarCbuRepetido

		        if (cbuRepetido) {
		            // Si el CBU ya está registrado, mostrar mensaje de error
		            request.setAttribute("mensaje", "El CBU ya está registrado en la base de datos.");
		            doGet(request, response); // Redirigir al formulario con el mensaje de error
		            return; // Salir del método si el CBU está repetido
		        }

		        // Verificar cuántas cuentas tiene el CUIL
		        long totalCuentas = cuentaNegocio.cuentasTotales(cuilCu); // Llama a la función totalCuentas

		        if (totalCuentas >= 3) {
		            // Si tiene más de 3 cuentas, mostrar mensaje de error
		            request.setAttribute("mensaje", "No se puede acceder a más de tres cuentas.");
		            doGet(request, response); // Redirigir al formulario con el mensaje de error
		            return; // Salir del método si no se puede agregar la cuenta
		        }

		        // Crear objeto Cuenta
		        Cuenta cuenta = new Cuenta();
		        cuenta.setNroCuenta(nroCuenta);
		        cuenta.setCuilCu(cuilCu);
		        cuenta.setNroTipoCuenta(nroTipoCuenta);
		        cuenta.setFechaCreacion(fechaCreacion);
		        cuenta.setCbu(cbu);
		        cuenta.setSaldo(saldo);
		        cuenta.setActivo(true); 

		        // Insertar en la base de datos
		        boolean resultado = cuentaNegocio.asignarCuenta(cuenta);

		        // Mensaje de éxito o error
		        if (resultado) {
		            request.setAttribute("mensaje", "Cuenta agregada correctamente.");
		        } else {
		            request.setAttribute("mensaje", "Error al agregar la cuenta.");
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		        request.setAttribute("mensaje", "Error en los datos ingresados.");
		    }

		    // Redirigir al formulario con el mensaje
		    doGet(request, response);
	}
}

