package servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

@WebServlet("/ServletModificarClientes")
public class ServletModificarClientes extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletModificarClientes() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el cliente a editar (puedes recibir un parámetro para identificar al cliente)
        String cuil = request.getParameter("cuil");
        NegocioCliente neg = new NegocioCliente();
        Cliente cliente = neg.obtenerClientePorCuil(cuil);

        if (cliente == null) {
            // Manejo del caso cuando el cliente no se encuentra
            request.setAttribute("error", "Cliente no encontrado.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        // Cargar datos adicionales para los selects
        cargarDatos(request);

        // Pasar el cliente al JSP
        request.setAttribute("cliente", cliente);
        request.getRequestDispatcher("ModificarCliente.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener valores del formulario
        String cuil = request.getParameter("txtcuil");
        String idUsuario = request.getParameter("txtidusuario");
        String dni = request.getParameter("txtdni");
        String nombre = request.getParameter("txtnombre");
        String apellido = request.getParameter("txtapellido");
        String sexo = request.getParameter("rd-sexo");
        String nacionalidad = request.getParameter("selectNacionalidad");
        String fechaNacimientoString = request.getParameter("txtFechaNac");
        String direccion = request.getParameter("txtDirecccion");
        String localidad = request.getParameter("selectLocalidad");
        String provincia = request.getParameter("selectProvincia");
        String email = request.getParameter("txtEmail");
        String telefono = request.getParameter("txtTelefono");

        // Convertir la fecha de nacimiento a java.sql.Date
        java.sql.Date sqlFechaNacimiento = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date utilFechaNacimiento = sdf.parse(fechaNacimientoString);
            sqlFechaNacimiento = new java.sql.Date(utilFechaNacimiento.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Crear el objeto Cliente con los nuevos valores
        Cliente cliente = new Cliente(
            cuil, 
            idUsuario, 
            localidad, 
            provincia, 
            nacionalidad, 
            dni, 
            nombre, 
            apellido,
            sexo, 
            sqlFechaNacimiento, 
            direccion, 
            email, 
            telefono
        );

        // Llamar al negocio para actualizar el cliente
        NegocioCliente neg = new NegocioCliente();
        boolean seActualizo = neg.ActualizarCliente(cliente);

        // Configurar mensaje y redirigir
        String mensaje;
        if (seActualizo) {
            mensaje = "El cliente fue actualizado exitosamente.";
        } else {
            mensaje = "Hubo un error al actualizar el cliente. Por favor, inténtelo de nuevo.";
        }

        response.sendRedirect("ServletListarClientes?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
    }


    private void cargarDatos(HttpServletRequest request) {
        AltaClienteNegocio neg = new AltaClienteNegocio();
        List<Nacionalidad> nacionalidades = neg.ObtenerNacionalidades();
        List<Provincia> provincias = neg.ObtenerProvincias();
        List<Localidad> localidades = neg.ObtenerLocalidades();

        // Pasar las listas al JSP
        request.setAttribute("nacionalidades", nacionalidades);
        request.setAttribute("provincias", provincias);
        request.setAttribute("localidades", localidades);
    }
}