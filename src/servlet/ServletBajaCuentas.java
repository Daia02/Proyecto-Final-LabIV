package servlet;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidades.Cliente;
import negocio.NegocioCuenta;

/**
 * Servlet implementation class ServletBajaCuentas
 */
@WebServlet("/ServletBajaCuentas")
public class ServletBajaCuentas extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBajaCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Depuración: Verifica si llega al doGet
        System.out.println("Entrando al método doGet");

        String action = request.getParameter("action");
        String nroCuentaParam = request.getParameter("NroCuenta");

        // Depuración: Verifica el valor de los parámetros
        System.out.println("Action recibido: " + action);
        System.out.println("Número de cuenta recibido: " + nroCuentaParam);

        if (nroCuentaParam != null) {
            try {
                int nroCuenta = Integer.parseInt(nroCuentaParam);

                // Depuración: Verifica que el número de cuenta sea válido
                System.out.println("Número de cuenta convertido a int: " + nroCuenta);

                if (action != null && action.equals("deleteCuenta")) {
                    NegocioCuenta negocioCuenta = new NegocioCuenta();
                    boolean eliminado = negocioCuenta.eliminarCuenta(nroCuenta);

                    // Depuración: Verifica si la cuenta fue eliminada
                    System.out.println("Cuenta eliminada: " + eliminado);

                    if (eliminado) {
                        // Redirige a la página de listado de cuentas con un mensaje de éxito
                        response.sendRedirect("servletCuentaBancaria?Param=true&mensaje=Cuenta eliminada correctamente");
                    } else {
                        // Redirige a la página de listado con un mensaje de error
                        response.sendRedirect("servletCuentaBancaria?Param=true&mensaje=Error al eliminar la cuenta");
                    }

                }
            } catch (NumberFormatException e) {
                // Depuración: Si ocurre un error en la conversión del número
                System.out.println("Error de conversión de número de cuenta: " + e.getMessage());
                response.sendRedirect("CuentasBancarias.jsp?mensaje=Número de cuenta inválido");
            }
        } else {
            // Depuración: Si no se recibe el parámetro NroCuenta
            System.out.println("Error: No se ha proporcionado un número de cuenta");
            response.sendRedirect("CuentasBancarias.jsp?mensaje=Error: No se ha proporcionado un número de cuenta");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
