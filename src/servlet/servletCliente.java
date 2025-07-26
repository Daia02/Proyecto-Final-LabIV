package servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import entidades.Cuenta;
import negocio.NegocioCliente;
import negocio.NegocioCuenta;
import negocio.TipoCuentaNegocio;

/**
 * Servlet implementation class servletCliente
 */
@WebServlet("/servletCliente")
public class servletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnBuscar")!=null) {
			request.getSession().removeAttribute("sessionCliente");
			request.getSession().removeAttribute("ultimoIdCuenta");
			request.getSession().removeAttribute("listaTipoCuenta");
			String CUIT = request.getParameter("txtCuit");
			NegocioCliente negCliente = new NegocioCliente();
			NegocioCuenta negCuenta = new NegocioCuenta();
			TipoCuentaNegocio negTipoCuenta = new TipoCuentaNegocio();
			Cliente clienteSolicitado = negCliente.buscarClienteUnico(CUIT);
			if(clienteSolicitado!=null) {
				request.setAttribute("cliente", clienteSolicitado);
				request.getSession().setAttribute("ultimoIdCuenta", negCuenta.cuentaUltimoId());
				request.getSession().setAttribute("listaTipoCuenta", negTipoCuenta.listarTodoTipoCuenta());
			}
			RequestDispatcher rd = request.getRequestDispatcher("Alta-CuentaCliente.jsp");
			rd.forward(request, response);
		}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
