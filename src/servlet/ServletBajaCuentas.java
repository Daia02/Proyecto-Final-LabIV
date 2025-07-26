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
        // Depuraci�n: Verifica si llega al doGet
        System.out.println("Entrando al m�todo doGet");

        String action = request.getParameter("action");
        String nroCuentaParam = request.getParameter("NroCuenta");

        // Depuraci�n: Verifica el valor de los par�metros
        System.out.println("Action recibido: " + action);
        System.out.println("N�mero de cuenta recibido: " + nroCuentaParam);

        if (nroCuentaParam != null) {
            try {
                int nroCuenta = Integer.parseInt(nroCuentaParam);

                // Depuraci�n: Verifica que el n�mero de cuenta sea v�lido
                System.out.println("N�mero de cuenta convertido a int: " + nroCuenta);

                if (action != null && action.equals("deleteCuenta")) {
                    NegocioCuenta negocioCuenta = new NegocioCuenta();
                    boolean eliminado = negocioCuenta.eliminarCuenta(nroCuenta);

                    // Depuraci�n: Verifica si la cuenta fue eliminada
                    System.out.println("Cuenta eliminada: " + eliminado);

                    if (eliminado) {
                        // Redirige a la p�gina de listado de cuentas con un mensaje de �xito
                        response.sendRedirect("servletCuentaBancaria?Param=true&mensaje=Cuenta eliminada correctamente");
                    } else {
                        // Redirige a la p�gina de listado con un mensaje de error
                        response.sendRedirect("servletCuentaBancaria?Param=true&mensaje=Error al eliminar la cuenta");
                    }

                }
            } catch (NumberFormatException e) {
                // Depuraci�n: Si ocurre un error en la conversi�n del n�mero
                System.out.println("Error de conversi�n de n�mero de cuenta: " + e.getMessage());
                response.sendRedirect("CuentasBancarias.jsp?mensaje=N�mero de cuenta inv�lido");
            }
        } else {
            // Depuraci�n: Si no se recibe el par�metro NroCuenta
            System.out.println("Error: No se ha proporcionado un n�mero de cuenta");
            response.sendRedirect("CuentasBancarias.jsp?mensaje=Error: No se ha proporcionado un n�mero de cuenta");
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
