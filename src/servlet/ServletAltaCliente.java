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

@WebServlet("/ServletAltaCliente")
public class ServletAltaCliente extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletAltaCliente() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Cargar datos y redirigir al JSP
        cargarDatos(request);
        request.getRequestDispatcher("Alta-Clientes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Cargar listas dinámicas
        cargarDatos(request);

        // Obtener valores del formulario
        String cuil = request.getParameter("txtcuil");
        String idUsuario = request.getParameter("txtidusuario");
        String dni = request.getParameter("txtdni");
        String nombre = request.getParameter("txtnombre");
        String apellido = request.getParameter("txtapellido");
        String sexo = request.getParameter("rd-sexo");
        String nacionalidad = request.getParameter("selectNacionalidad");
        String fechaNacimientoString = request.getParameter("txtFechaNac");
        String direccion = request.getParameter("txtDirecccion"); // Verificar ortografía
        String localidad = request.getParameter("selectLocalidad");
        String provincia = request.getParameter("selectProvincia");
        String email = request.getParameter("txtEmail");
        String telefono = request.getParameter("txtTelefono");

        // Imprimir los valores recibidos para depuración
        System.out.println("CUIL: " + cuil);
        System.out.println("ID Usuario: " + idUsuario);
        System.out.println("DNI: " + dni);
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Sexo: " + sexo);
        System.out.println("Nacionalidad: " + nacionalidad);
        System.out.println("Fecha Nac: " + fechaNacimientoString);
        System.out.println("Dirección: " + direccion);
        System.out.println("Localidad: " + localidad);
        System.out.println("Provincia: " + provincia);
        System.out.println("Email: " + email);
        System.out.println("Teléfono: " + telefono);

        // Verificar que los campos requeridos no sean nulos ni vacíos
        if (cuil == null || idUsuario == null || dni == null || nombre == null || apellido == null ||
            sexo == null || nacionalidad == null || fechaNacimientoString == null || direccion == null ||
            localidad == null || provincia == null || email == null || telefono == null ||
            cuil.isEmpty() || idUsuario.isEmpty() || dni.isEmpty() || nombre.isEmpty() || apellido.isEmpty() ||
            sexo.isEmpty() || nacionalidad.isEmpty() || fechaNacimientoString.isEmpty() || direccion.isEmpty() ||
            localidad.isEmpty() || provincia.isEmpty() || email.isEmpty() || telefono.isEmpty()) {

            String mensaje = "Todos los campos son obligatorios.";
            response.sendRedirect("Alta-Clientes.jsp?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
            return;
        }

        // Convertir la fecha de nacimiento a java.sql.Date
        java.sql.Date sqlFechaNacimiento = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date utilFechaNacimiento = sdf.parse(fechaNacimientoString);
            sqlFechaNacimiento = new java.sql.Date(utilFechaNacimiento.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error al analizar la fecha de nacimiento: " + fechaNacimientoString);
        }

        // Crear el objeto Cliente
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

        // Llamar al negocio
        AltaClienteNegocio neg = new AltaClienteNegocio();
        boolean seSubio = neg.CargarCliente(cliente);

        // Configurar mensaje y redirigir
        String mensaje;
        if (seSubio) {
            mensaje = "El cliente fue agregado exitosamente.";
        } else {
            mensaje = "Hubo un error al agregar el cliente. Por favor, inténtelo de nuevo.";
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