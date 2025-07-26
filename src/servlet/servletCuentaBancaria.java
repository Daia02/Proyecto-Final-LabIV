package servlet;

import negocio.NegocioCuenta; 
import entidades.Cuenta;  
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletCuentaBancaria
 */
@WebServlet("/servletCuentaBancaria")
public class servletCuentaBancaria extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletCuentaBancaria() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("Param") != null) {
            // Entra por haber hecho clic en el hyperlink "Mostrar cuentas"
            NegocioCuenta negCuenta = new NegocioCuenta(); 
            ArrayList<Cuenta> listaCuentas = negCuenta.obtenerCuentas(); // Método para obtener las cuentas desde la base de datos

            request.setAttribute("listaCuentas", listaCuentas);  // Establecer la lista de cuentas como atributo de la solicitud

            // Redirigir a la página JSP donde mostrarás las cuentas
            RequestDispatcher rd = request.getRequestDispatcher("/CuentasBancarias.jsp");   
            rd.forward(request, response);
        }
        else{
        	 RequestDispatcher rd = request.getRequestDispatcher("/CuentasBancarias.jsp");  
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // Puedes manejar lógica similar para el POST si es necesario
    }
}
