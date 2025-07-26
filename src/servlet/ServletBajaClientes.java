package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.NegocioCliente;
import entidades.Cliente;

/**
 * Servlet implementation class ServletBajaClientes
 */
@WebServlet("/ServletBajaClientes")
public class ServletBajaClientes extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String cuil = request.getParameter("cuil");

        if (action != null && action.equals("deleteCliente")) {
            NegocioCliente negocioCliente = new NegocioCliente();
            Cliente cliente = negocioCliente.buscarClienteUnico(cuil);

            if (cliente == null) {
                request.setAttribute("error", "Cliente no encontrado.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            request.setAttribute("cliente", cliente);
            RequestDispatcher dispatcher = request.getRequestDispatcher("BajaClientesjsp.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("Clientes.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cuil = request.getParameter("cuil");

        if (cuil == null || cuil.isEmpty()) {
            response.sendRedirect("ServletListarClientes?error=CUIL no especificado.");
            return;
        }

        NegocioCliente negocioCliente = new NegocioCliente();
        boolean resultado = negocioCliente.bajaLogicaCliente(Long.parseLong(cuil));

        if (resultado) {
            response.sendRedirect("ServletListarClientes?mensaje=Cliente dado de baja correctamente.");
        } else {
            response.sendRedirect("ServletListarClientes?error=Error al dar de baja al cliente.");
        }
    }

}