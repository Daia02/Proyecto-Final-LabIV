package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;


//Importa la clase CuentaDao
import dao.CuentaDao;

/**
 * Servlet implementation class servletReporte
 */
@WebServlet("/servletReporte")
public class servletReporte extends HttpServlet {
    private static final long serialVersionUID = 1L;
    

    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletReporte() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	  // Obtener los parámetros de fecha
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        // Obtener el promedio de cuentas nuevas desde el DAO
        double promedioCuentas = CuentaDao.obtenerPromedioCuentasNuevas(startDate, endDate);

        // Obtener también el total de cuentas para mostrar en el reporte
        int totalCuentas = CuentaDao.obtenerTotalCuentasNuevas(startDate, endDate);

        // Pasar los datos al JSP
        request.setAttribute("promedioCuentas", promedioCuentas);
        request.setAttribute("totalCuentas", totalCuentas);

        // Redirigir al JSP de resultados
        request.getRequestDispatcher("ResultadoReporte.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}