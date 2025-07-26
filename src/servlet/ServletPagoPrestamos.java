package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entidades.PagoPrestamo;
import entidades.Cliente;
import entidades.Usuario;
import negocio.PagoPrestamosNegocio;
import negocio.UsuarioNegocio;

@WebServlet("/ServletPagoPrestamos")
public class ServletPagoPrestamos extends HttpServlet {
    private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuario");

            if (usuario == null) {
                response.sendRedirect("InicioSesion.jsp");
                return;
            }

            String nombreUsuario = usuario.getNombre();

            UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
            Cliente cliente = usuarioNegocio.obtenerInformacionCliente(nombreUsuario);

            if (cliente == null) {
                request.setAttribute("mensajeError", "Cliente no encontrado.");
                request.getRequestDispatcher("PagoPrestamos.jsp").forward(request, response);
                return;
            }

            String cuilCliente = cliente.getCuilCl();

            PagoPrestamosNegocio negocio = new PagoPrestamosNegocio();

            try {
                List<PagoPrestamo> prestamos = negocio.obtenerPrestamosYPagos(Long.parseLong(cuilCliente));
                request.setAttribute("prestamos", prestamos);

                boolean hayPrestamos = prestamos != null && prestamos.stream().anyMatch(p -> p.getCuotasRestantes() > 0);
                request.setAttribute("hayPrestamos", hayPrestamos);

                List<Map<String, String>> cuentas = negocio.obtenerCuentasPorCliente(Long.parseLong(cuilCliente));
                request.setAttribute("cuentas", cuentas);

                // Procesar los saldos de las cuentas
                String saldoCuentaCorriente = "";
                String saldoCajaAhorro = "";

                for (Map<String, String> cuenta : cuentas) {
                    String tipoCuenta = cuenta.get("Tipo_de_cuenta");
                    String saldo = cuenta.get("Saldo");
                    
                    if ("Cuenta corriente".equals(tipoCuenta)) {
                        saldoCuentaCorriente = saldo;
                    } else if ("Caja de ahorro".equals(tipoCuenta)) {
                        saldoCajaAhorro = saldo;
                    }
                }

                System.out.println(saldoCuentaCorriente);
                System.out.println(saldoCajaAhorro);
                request.setAttribute("saldoCuentaCorriente", saldoCuentaCorriente);
                request.setAttribute("saldoCajaAhorro", saldoCajaAhorro);

                // Recoger los parámetros de la URL
                String mensaje = request.getParameter("mensaje");
                String error = request.getParameter("error");

                // Establecer los atributos si existen
                if (mensaje != null) {
                    request.setAttribute("mensaje", mensaje);
                }
                if (error != null) {
                    request.setAttribute("mensajeError", error);
                }

                request.getRequestDispatcher("PagoPrestamos.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("mensajeError", "Error al cargar la lista de préstamos.");
                request.getRequestDispatcher("PagoPrestamos.jsp").forward(request, response);
            }
        }
    


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("InicioSesion.jsp");
            return;
        }

        String nombreUsuario = usuario.getNombre();
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
        Cliente cliente = usuarioNegocio.obtenerInformacionCliente(nombreUsuario);

        if (cliente == null) {
            request.setAttribute("mensajeError", "Cliente no encontrado.");
            request.getRequestDispatcher("PagoPrestamos.jsp").forward(request, response);
            return;
        }

        try {
            String nroPrestamoStr = request.getParameter("nroPrestamo");
            if (nroPrestamoStr == null || nroPrestamoStr.isEmpty()) {
                request.setAttribute("mensajeError", "Debe seleccionar una cuota.");
                doGet(request, response);
                return;
            }

            String montoCuota = request.getParameter("montoCuota_" + nroPrestamoStr);

            System.out.println("NroPrestamo: " + nroPrestamoStr);
            System.out.println("MontoCuota: " + montoCuota);

            PagoPrestamosNegocio negocio = new PagoPrestamosNegocio();
            List<Map<String, String>> cuentas = negocio.obtenerCuentasPorCliente(Long.parseLong(cliente.getCuilCl()));
            request.setAttribute("cuentas", cuentas);

            request.setAttribute("nroPrestamo", nroPrestamoStr);
            request.setAttribute("montoCuota", montoCuota);
            request.getRequestDispatcher("ConfirmarPago.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error en el formato de los parámetros: " + e.getMessage());
            doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al realizar el pago. Por favor, inténtelo de nuevo.");
            doGet(request, response);
        }
    }
}