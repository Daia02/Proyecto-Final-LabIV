package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Movimiento;
import entidades.Usuario;
import negocio.NegocioMovimiento;

/**
 * Servlet implementation class servletMovimiento
 */
@WebServlet("/servletListarMovimiento")
public class servletListarMovimiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletListarMovimiento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("BtnCargarMovimiento")!=null) {
			NegocioMovimiento negMovimiento = new NegocioMovimiento();
			if(!(request.getParameter("txtImporte").isEmpty())) {
				String importe = request.getParameter("txtImporte").toString();
				String criterio = request.getParameter("filtroImporte").toString();
				int idUsuario = ((Usuario)request.getSession().getAttribute("usuario")).getIdUsuario();
				ArrayList<Movimiento> movimientosTotales = negMovimiento.listarConFiltroImporte(importe , criterio, idUsuario);
				request.setAttribute("listaMov", movimientosTotales); 
				RequestDispatcher rd = request.getRequestDispatcher("Movimientos.jsp");
				rd.forward(request, response);
			}else {
				int idUsuario = ((Usuario)request.getSession().getAttribute("usuario")).getIdUsuario();
				ArrayList<Movimiento> movimientosTotales = negMovimiento.listarMovimientoUnaCuenta(idUsuario);
				request.setAttribute("listaMov", movimientosTotales);
				RequestDispatcher rd = request.getRequestDispatcher("Movimientos.jsp");
				rd.forward(request, response);
				response.getWriter().write(request.getParameter("txtImporte"));
			}
		}
	}*/

}

