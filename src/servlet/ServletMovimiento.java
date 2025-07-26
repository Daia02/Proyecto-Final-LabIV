package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.UsuarioNegocio;
import negocio.NegocioMovimiento;
import entidades.Usuario;
import entidades.Movimiento;

/**
 * Servlet implementation class ServletMovimientos
 */
@WebServlet("/ServletMovimiento")
public class ServletMovimiento extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioNegocio usuarioNegocio;
    // Instancia de la clase NegocioMovimiento
    private NegocioMovimiento negocioMovimiento;

    public ServletMovimiento() {
        super();
        usuarioNegocio = new UsuarioNegocio(); // Instancia del negocio
        negocioMovimiento = new NegocioMovimiento();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario != null) {
            int idUsuario = usuario.getIdUsuario();
            System.out.println("ID del usuario obtenido en Servlet: " + idUsuario);

            List<Integer> numerosDeCuenta = usuarioNegocio.obtenerNumerosDeCuenta(idUsuario);
         // Depuración: Imprimir los números de cuenta en la consola
            System.out.println("Numeros de cuenta: " + numerosDeCuenta);
            request.setAttribute("numerosDeCuenta", numerosDeCuenta);

            String cuentaSeleccionada = request.getParameter("cuentaSelect");
            System.out.println("Numeros de cuentaaass "+cuentaSeleccionada );
            if (cuentaSeleccionada != null && !cuentaSeleccionada.isEmpty()) {
                // Convertir el parámetro de cuenta seleccionada a int
                int cuentaId = Integer.parseInt(cuentaSeleccionada);
                System.out.println("Numeros de cuentaaa "+cuentaSeleccionada );
                // Obtener todos los movimientos de la cuenta seleccionada
                List<Movimiento> movimientos = negocioMovimiento.obtenerMovimientoPorCuenta(cuentaId);
             // Verificar si se obtuvieron movimientos
                if (movimientos != null && !movimientos.isEmpty()) {
                    System.out.println("Se han obtenido " + movimientos.size() + " movimientos.");
                    System.out.println("Movimientos obtenidos: ");
                    for (Movimiento movimiento : movimientos) {
                        System.out.println("Fecha: " + movimiento.getFecha());
                        System.out.println("Detalle: " + movimiento.getDetalle());
                        System.out.println("Importe: " + movimiento.getImporte());
                        System.out.println("Tipo de movimiento: " + movimiento.getTipoMovimiento());
                    }
                
                } else {
                    System.out.println("No se encontraron movimientos para la cuenta: " + cuentaId);
                }
                // Establecer la lista de movimientos en el atributo de la solicitud
                request.setAttribute("movimientos", movimientos);
                
            }
        }

        // Redirigir al JSP para mostrar los movimientos
        request.getRequestDispatcher("Movimientos_Clientes.jsp").forward(request, response);
        
    } 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
