package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import Expeciones.MaximoPrestamos;
import entidades.Cuenta;
import entidades.TipoCuenta;
import entidades.Usuario;
import negocio.PrestamosNegocio;

/**
 * Servlet implementation class ServletClientePrestamos
 */
@WebServlet("/ServletClientePrestamos")
public class ServletClientePrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletClientePrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       System.out.println("toy en get");
    	HttpSession session = request.getSession(false);
        String usua = obtenerUsuarioDeSesion(session);

      

        
        System.out.println(usua + " enviar");
        cargardatos(request, usua);
        request.getRequestDispatcher("/ClientesPrestamos.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 
    	
    	HttpSession session = request.getSession(false);
        String usua = obtenerUsuarioDeSesion(session);

        if (usua.isEmpty()) {
            response.sendRedirect("login.jsp"); 
        }
        
        
        manejarAccionesPrestamo(request, response, usua);
        
    }

    private String obtenerUsuarioDeSesion(HttpSession session) {
        if (session != null) {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            if (usuario != null) {
                System.out.println("Usuario encontrado: " + usuario.getNombre());
                return usuario.getNombre();
            }
        }
        System.out.println("Usuario no encontrado en la sesión.");
        return "";
    }
    private void manejarAccionesPrestamo(HttpServletRequest request, HttpServletResponse response, String usua) throws ServletException, IOException {
        int monto;
        int coutas;
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal interes;
        BigDecimal montoBD;
        String tipoCuentaSeleccionada;
        boolean f;
        cargardatos(request, usua);
        String cartel;
        
 
        f=true;
        
        if (request.getParameter("btnConfirmar") != null) {
        	
        	try {
				
        		
            montoBD = new BigDecimal(request.getParameter("total"));
            coutas = Integer.parseInt(request.getParameter("Coutas"));
            interes = new BigDecimal(coutas * 5);
            monto = Integer.parseInt(request.getParameter("Monto"));
            BigDecimal importe_pedidos = new BigDecimal(monto);
            
            int Numtipo=Integer.parseInt(request.getParameter("tipoCuentaSeleccionada"));
            negocio.PrestamosNegocio daoNegocio=new PrestamosNegocio();
            
           
          
            
            
             f = daoNegocio.agregarPrestamo(importe_pedidos, coutas, usua, Numtipo, montoBD, interes);
             System.out.println(f);
             if(f) {
            	 cartel="SE AGREGO CON EXITOS";
            
            	 String cartelAprobado="block";
            	  request.removeAttribute("Monto");
                  request.removeAttribute("Coutas");
                  request.removeAttribute("total");
                  request.removeAttribute("tipoCuentaSeleccionada");
            	 request.setAttribute("mensajeExito", cartel);
            	 request.setAttribute("cartelAprobado",cartelAprobado );
             }else {
            	 
                 cartel = "UPS HUBO UN ERROR";
                 request.setAttribute("mensajeError", cartel);
                 request.getRequestDispatcher("/ClientesPrestamos.jsp").forward(request, response);
                return;
             }
         } catch (MaximoPrestamos e) {
        	
             cartel = "UPS HUBO UN ERROR: " + e.getMessage();
             request.setAttribute("mensajeError", cartel);
             
         } 
            cargardatos(request, usua);
            
            request.getRequestDispatcher("/ClientesPrestamos.jsp").forward(request, response);
            return;
        }

        if (request.getParameter("BtnAceptar") != null) {
        	
            monto = Integer.parseInt(request.getParameter("Monto"));
            coutas = Integer.parseInt(request.getParameter("Coutas"));
            montoBD = new BigDecimal(monto);

             tipoCuentaSeleccionada = request.getParameter("selectTipoCuenta");
            
            
            buscarNombre(request,tipoCuentaSeleccionada,usua);

            interes = new BigDecimal(coutas * 5);
            BigDecimal cien = new BigDecimal(100);

            total = montoBD.multiply(BigDecimal.ONE.add(interes.divide(cien, 2, RoundingMode.HALF_UP)));

            request.setAttribute("Monto", monto);
            request.setAttribute("Coutas", coutas);
            request.setAttribute("total", total);
            request.setAttribute("tipoCuentaSeleccionada", tipoCuentaSeleccionada);
            request.setAttribute("mostrarModal", true);
            
            request.getRequestDispatcher("/ClientesPrestamos.jsp").forward(request, response);
            

            return; 
        }
        if(request.getParameter("BtnIrEstadoP")!=null) {
        	response.sendRedirect("ServletEstadoPrestamos");
        	return;
        	
        }
        if(request.getParameter("")!=null) {
        	response.sendRedirect("ServletClientePrestamos");
        	return;
        }
        
        if(request.getParameter("BtnVolverMenu")!=null) {
        	
        request.getRequestDispatcher("/usuarioHome.jsp").forward(request, response);
        return;
        }
        request.removeAttribute("Monto");
        request.removeAttribute("Coutas");
        request.removeAttribute("total");
        request.removeAttribute("tipoCuentaSeleccionada");
        request.getRequestDispatcher("/ClientesPrestamos.jsp").forward(request, response);
    }

    private void cargardatos(HttpServletRequest request, String usuario) {
        negocio.PrestamosNegocio pres = new PrestamosNegocio();
        List<Cuenta> tipoCuentas = pres.ObtenerTipoCuentaUsuario(usuario);
       /// for(Cuenta t:tipoCuentas) {
        //	System.out.println(t.getTipoCuenta().getNroTipoCuenta()+t.getTipoCuenta().getTipoCuenta()+t.getNroCuenta());
    	///	}
        request.setAttribute("tipoCuenta", tipoCuentas);
    }
    private void buscarNombre(HttpServletRequest request ,String tipocunetaSeleccionada,String usua) {
    	negocio.PrestamosNegocio prestamosNegocio=new PrestamosNegocio();
    	
    	List<Cuenta>tipoCuentas=prestamosNegocio.ObtenerTipoCuentaUsuario(usua);
    	
    	for(Cuenta t:tipoCuentas) {
    		if(t.getTipoCuenta().getNroTipoCuenta()==Integer.parseInt(tipocunetaSeleccionada)) {
    			request.setAttribute("nombreTipo",t.getTipoCuenta().getTipoCuenta() );
    			request.setAttribute("numeroCuenta", t.getNroCuenta());
    		
    	        	System.out.println(t.getNroCuenta());
    	    		
    			break;
    			
    		}
    	}
    	
    }

}
