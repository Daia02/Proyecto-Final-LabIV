package servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entidades.Usuario;
import negocio.PagoPrestamosNegocio;

@WebServlet("/ServletConfirmarPago")
public class ServletConfirmarPago extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            // Redirigir al inicio de sesión si el usuario no está en la sesión
            response.sendRedirect("InicioSesion.jsp");
            return;
        }

        try {
            // Obtener los parámetros del formulario
            String nroPrestamoStr = request.getParameter("nroPrestamo");
            String nroCuentaStr = request.getParameter("nroCuenta");

            if (nroPrestamoStr == null || nroPrestamoStr.isEmpty() || nroCuentaStr == null || nroCuentaStr.isEmpty()) {
                response.sendRedirect("ServletPagoPrestamos?error=" + URLEncoder.encode("Debe seleccionar una cuota y una cuenta.", "UTF-8"));
                return;
            }

            int nroPrestamo = Integer.parseInt(nroPrestamoStr);
            int nroCuenta = Integer.parseInt(nroCuentaStr);

            Date fechaPago = new Date(System.currentTimeMillis());

            // Crear una instancia de negocio
            PagoPrestamosNegocio negocio = new PagoPrestamosNegocio();

            // Llamar al método de negocio para pagar la cuota y actualizar el estado
            boolean resultado = negocio.pagarCuotaPrestamo(nroPrestamo, nroCuenta, fechaPago);
            if (resultado) {
                // Redirigir con mensaje de éxito
                response.sendRedirect("ServletPagoPrestamos?mensaje=" + URLEncoder.encode("Pago realizado con éxito.", "UTF-8"));
            } else {
                // Redirigir con mensaje de error
                response.sendRedirect("ServletPagoPrestamos?error=" + URLEncoder.encode("Error al realizar el pago. Por favor, inténtelo de nuevo.", "UTF-8"));
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("ServletPagoPrestamos?error=" + URLEncoder.encode("Error en el formato de los parámetros: " + e.getMessage(), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ServletPagoPrestamos?error=" + URLEncoder.encode("Error al realizar el pago. Por favor, inténtelo de nuevo.", "UTF-8"));
        }
    }
}