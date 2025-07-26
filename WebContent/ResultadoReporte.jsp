<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="adminNavbar.jsp" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Resultado Reporte de Promedio de Cuentas Nuevas</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" crossorigin="anonymous">
    <style>
        body {
            background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url(Imagenes/fondo_utn.jpg);
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            background-attachment: fixed;
            color: white;
        }
        .table-custom {
            background-color: #343a40;
            border-radius: 10px;
            color: white;
        }
    </style>
</head>
<body>

<div class="container-fluid p-3 mb-2 text-dark">
    <div class="row justify-content-center">
        <div class="col-10 p-4 bg-dark text-white rounded">
            <h3 class="text-center">Reporte de Promedio de Cuentas Nuevas</h3>
            
            <!-- Filtros seleccionados -->
            <p><strong>Fecha de inicio:</strong> <%= request.getParameter("startDate") %></p>
            <p><strong>Fecha de fin:</strong> <%= request.getParameter("endDate") %></p>
            
            <!-- Promedio de cuentas nuevas -->
            <h4 class="text-center">Promedio de Cuentas Nuevas en el Período Seleccionado</h4>
            <p class="text-center">
                <strong>Promedio de Cuentas Creadas por Día:</strong> 
                <%= request.getAttribute("promedioCuentas") != null ? request.getAttribute("promedioCuentas") : "0.00" %> 
            </p>
            <p class="text-center">
                <strong>Total de Cuentas Creadas:</strong> 
                <%= request.getAttribute("totalCuentas") != null ? request.getAttribute("totalCuentas") : "0" %> 
            </p>
            
            <!-- Contenedor del gráfico -->
            <canvas id="chart" width="400" height="200"></canvas>
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script>
    // Obtener los valores del promedio y total desde el JSP
    var promedioCuentas = <%= request.getAttribute("promedioCuentas") != null ? request.getAttribute("promedioCuentas") : 0 %>;
    var totalCuentas = <%= request.getAttribute("totalCuentas") != null ? request.getAttribute("totalCuentas") : 0 %>;

    // Crear el gráfico
    var ctx = document.getElementById('chart').getContext('2d');
    var chart = new Chart(ctx, {
        type: 'bar', // Tipo de gráfico (barra)
        data: {
            labels: ['Promedio de Cuentas', 'Total de Cuentas'], // Etiquetas en el eje X
            datasets: [{
                label: 'Cuentas Nuevas',
                data: [promedioCuentas, totalCuentas], // Valores para las barras
                backgroundColor: ['rgba(54, 162, 235, 0.2)', 'rgba(255, 99, 132, 0.2)'], // Colores de las barras
                borderColor: ['rgba(54, 162, 235, 1)', 'rgba(255, 99, 132, 1)'], // Colores de los bordes
                borderWidth: 1 // Ancho del borde
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true // Asegurarse de que el gráfico comience en cero
                }
            }
        }
    });
</script>

</body>
</html>
