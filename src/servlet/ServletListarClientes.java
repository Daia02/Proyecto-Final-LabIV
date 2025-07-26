package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.Provincia;
import negocio.AltaClienteNegocio;
import negocio.NegocioCliente;

/**
 * Servlet implementation class ServletListarClientes
 */
@WebServlet("/ServletListarClientes")
public class ServletListarClientes extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletListarClientes() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Cargar clientes y datos adicionales
        NegocioCliente neg = new NegocioCliente();
        List<Cliente> clientes = neg.ObtenerTodosClientes();
        request.setAttribute("clientes", clientes);
        
        
   
        
        /*
        
        if(request.getParameter("Param") != null) {
            // Lógica para cargar los CUILs
          
            List<String> cuiles = neg.obtenerTodosLosCuils();
            request.setAttribute("cuiles", cuiles);
            // Depuración: Imprimir los CUILs en la consola
            if (cuiles != null && !cuiles.isEmpty()) {
                System.out.println("CUILs obtenidos: ");
                for (String cuil : cuiles) {
                    System.out.println(cuil);
                }
            } else {
                System.out.println("No se han encontrado CUILs.");
            }
            request.getRequestDispatcher("/Alta_Cuentas_Clientes.jsp").forward(request, response);
        } else {
            // Lógica alternativa si es necesario
        }
        
       */

 


        // Cargar los selects
        cargarDatos(request);

        // Recoger los parámetros de la URL
        String mensaje = request.getParameter("mensaje");
        String error = request.getParameter("error");

        // Establecer los atributos si existen
        if (mensaje != null) {
            request.setAttribute("mensaje", mensaje);
        }
        if (error != null) {
            request.setAttribute("error", error);
        }

        String action = request.getParameter("action");

        if (action != null) {
            if (action.equals("getCliente")) {
                String cuil = request.getParameter("cuil");
                Cliente cliente = neg.obtenerClientePorCuil(cuil);
                response.setContentType("application/json");
                response.getWriter().write(clienteToJson(cliente));
            } else if (action.equals("editCliente")) {
                String cuil = request.getParameter("cuil");
                if (cuil == null || cuil.isEmpty()) {
                    request.setAttribute("error", "CUIL no especificado.");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    return;
                }
                Cliente cliente = neg.obtenerClientePorCuil(cuil);
                if (cliente == null) {
                    request.setAttribute("error", "Cliente no encontrado.");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    return;
                }
                request.setAttribute("cliente", cliente);
                request.getRequestDispatcher("ModificarCliente.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("Clientes.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NegocioCliente neg = new NegocioCliente();

        // Recoger los parámetros de filtro
        String cuil = request.getParameter("txtcuil");
        String idUsuario = request.getParameter("txtidusuario");
        String dni = request.getParameter("txtdni");
        String nombre = request.getParameter("txtnombre");
        String apellido = request.getParameter("txtapellido");
        String sexo = request.getParameter("rd-sexo");
        String nacionalidad = request.getParameter("selectNacionalidad");
        String provincia = request.getParameter("selectProvincia");
        String localidad = request.getParameter("selectLocalidad");

        

        // Filtrar clientes utilizando los parámetros
        List<Cliente> clientes = neg.obtenerClientesFiltrados(cuil, idUsuario, dni, nombre, apellido, sexo, nacionalidad, provincia, localidad);
        request.setAttribute("clientes", clientes);

        // Cargar los selects
        cargarDatos(request);

        // Redirigir a la página JSP que mostrará los datos filtrados
        request.getRequestDispatcher("Clientes.jsp").forward(request, response);
    }



    private void cargarDatos(HttpServletRequest request) {
        AltaClienteNegocio neg = new AltaClienteNegocio();
        List<Nacionalidad> nacionalidades = neg.ObtenerNacionalidades();
        List<Provincia> provincias = neg.ObtenerProvincias();
        List<Localidad> localidades = neg.ObtenerLocalidades();

        request.setAttribute("nacionalidades", nacionalidades);
        request.setAttribute("provincias", provincias);
        request.setAttribute("localidades", localidades);
    }

    private String clienteToJson(Cliente cliente) {
        StringBuilder json = new StringBuilder();
        json.append("{");

        if (cliente != null) {
            json.append("\"cuil\":\"").append(cliente.getCuilCl()).append("\",");
            json.append("\"nombre\":\"").append(cliente.getNombre()).append("\",");
            json.append("\"apellido\":\"").append(cliente.getApellido()).append("\",");
            json.append("\"dni\":\"").append(cliente.getDni()).append("\",");
            json.append("\"sexo\":\"").append(cliente.getSexo()).append("\",");

            AltaClienteNegocio neg = new AltaClienteNegocio();
            json.append("\"nacionalidades\":").append(toOptionListJson(neg.ObtenerNacionalidades(), Integer.parseInt(cliente.getNroNacionalidad()))).append(",");
            json.append("\"provincias\":").append(toOptionListJson(neg.ObtenerProvincias(), Integer.parseInt(cliente.getNroProvincia()))).append(",");
            json.append("\"localidades\":").append(toOptionListJson(neg.ObtenerLocalidades(), Integer.parseInt(cliente.getNroLocalidad()))).append(",");

            json.append("\"nacionalidad\":\"").append(cliente.getNroNacionalidad()).append("\",");
            json.append("\"provincia\":\"").append(cliente.getNroProvincia()).append("\",");
            json.append("\"localidad\":\"").append(cliente.getNroLocalidad()).append("\"");
        } else {
            json.append("\"error\":\"Cliente no encontrado\"");
        }

        json.append("}");
        return json.toString();
    }

    private <T> String toOptionListJson(List<T> list, int selectedId) {
        StringBuilder json = new StringBuilder();
        json.append("[");

        for (T item : list) {
            if (item instanceof Nacionalidad) {
                Nacionalidad n = (Nacionalidad) item;
                json.append("{")
                    .append("\"value\":").append(n.getNroNacionalidad()).append(",")
                    .append("\"label\":\"").append(n.getNacionalidad()).append("\",")
                    .append("\"selected\":").append(n.getNroNacionalidad() == selectedId)
                    .append("},");
            } else if (item instanceof Provincia) {
                Provincia p = (Provincia) item;
                json.append("{")
                    .append("\"value\":").append(p.getNroProvincia()).append(",")
                    .append("\"label\":\"").append(p.getProvincia()).append("\",")
                    .append("\"selected\":").append(p.getNroProvincia() == selectedId)
                    .append("},");
            } else if (item instanceof Localidad) {
                Localidad l = (Localidad) item;
                json.append("{")
                    .append("\"value\":").append(l.getNroLocalidad()).append(",")
                    .append("\"label\":\"").append(l.getLocalidad()).append("\",")
                    .append("\"selected\":").append(l.getNroLocalidad() == selectedId)
                    .append("},");
            }
        }

        if (json.charAt(json.length() - 1) == ',') {
            json.deleteCharAt(json.length() - 1);
        }

        json.append("]");
        return json.toString();
    }
}