<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="usuarioNavbar.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="entidades.Movimiento" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>
    <title>Mis Movimientos</title>
    <style>
        /* Estilos personalizados */
        .card-custom {
            border-radius: 12px;
            padding: 20px;
            color: #f8f9fa;
            box-shadow: 0 4px 10px rgba(255, 255, 255, 0.1);
            background-color: #343a40;
        }

        .table-custom {
            background-color: #1e1e1e;
            border-radius: 8px;
            overflow: hidden;
            color: #f8f9fa;
        }

        th {
            text-align: center;
        }

        td {
            text-align: center;
            color: #f8f9fa;
        }

        .badge {
            font-size: 1rem;
            padding: 8px 12px;
            border-radius: 6px;
            font-weight: bold;
        }

        .badge-ingreso {
            background-color: #28a745;
            color: #ffffff;
        }

        .badge-egreso {
            background-color: #dc3545;
            color: #ffffff;
        }

        .dataTables_wrapper .dataTables_paginate .paginate_button {
            color: #f8f9fa !important;
        }

        .dataTables_wrapper .dataTables_filter input {
            border: 1px solid #444;
            color: #f8f9fa;
        }
    </style>
    
    <style>
    /* Estilos personalizados */
    .card-custom {
        border-radius: 12px;
        padding: 20px;
        color: #f8f9fa;
        box-shadow: 0 4px 10px rgba(255, 255, 255, 0.1);
        background-color: #343a40;
    }

    .table-custom {
        background-color: #1e1e1e;
        border-radius: 8px;
        overflow: hidden;
        color: #f8f9fa;
    }

    table.dataTable thead {
        background-color: #212529;
        color: #f8f9fa;
    }

    table.dataTable tbody tr:hover {
        background-color: rgba(255, 255, 255, 0.1);
    }

    th, td {
        text-align: center;
        padding: 12px;
        border-bottom: 1px solid #444;
    }

    th {
        font-weight: bold;
    }

    .badge {
        font-size: 1rem;
        padding: 8px 12px;
        border-radius: 6px;
        font-weight: bold;
    }

    .badge-ingreso {
        background-color: #28a745;
        color: #ffffff;
    }

    .badge-egreso {
        background-color: #dc3545;
        color: #ffffff;
    }

    .dataTables_wrapper .dataTables_paginate .paginate_button {
        color: #f8f9fa !important;
    }

    .dataTables_wrapper .dataTables_filter input {
        background-color: #222;
        border: 1px solid #444;
        color: #f8f9fa;
        padding: 6px;
        border-radius: 5px;
    }
</style>
    
</head>

<body class="bg-light">

    <div class="container-fluid p-4 mb-2 text-dark">
        <div class="row justify-content-center">
            <div class="col-8 p-4 mb-3 bg-dark text-white rounded">
                <h3 class="text-center">Mis Movimientos</h3>

                <!-- Formulario para seleccionar cuenta -->
                <form action="ServletMovimiento" method="get">
                    <div class="form-group mb-3">
                        <label for="cuentaSelect">Seleccione una cuenta:</label>
                        <select id="cuentaSelect" name="cuentaSelect" class="form-select">
                            <option value="">Seleccione una cuenta</option>
                            <% 
                                List<Integer> numerosDeCuenta = (List<Integer>) request.getAttribute("numerosDeCuenta"); 
                                if (numerosDeCuenta != null && !numerosDeCuenta.isEmpty()) {
                                    for (Integer cuenta : numerosDeCuenta) {
                            %>
                                        <option value="<%= cuenta %>"><%= cuenta %></option>
                            <%  
                                    }
                                } else { 
                            %>
                                    <option>No hay cuentas disponibles</option>
                            <% 
                                }
                            %>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Seleccionar Cuenta</button>
                </form>

                <!-- Card de movimientos -->
                <div class="card card-custom mt-4">
                    <div class="d-flex justify-content-end mb-2">
                        <button id="descargarPDF" class="btn btn-primary"><i class="fas fa-download"></i> Descargar PDF</button>
                    </div>
                    <div class="table-responsive">
                        <table id="movimientosTable" class="table table-striped table-bordered table-custom">
                            <thead>
                                <tr>
                                    <th>Fecha</th>
                                    <th>Descripción</th>
                                    <th>Importe</th>
                                    <th>Tipo</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% 
                                    List<Movimiento> movimientos = (List<Movimiento>) request.getAttribute("movimientos");
                                    if (movimientos != null && !movimientos.isEmpty()) {
                                        for (Movimiento movimiento : movimientos) {
                                %>
                                            <tr>
                                                <td><%= movimiento.getFecha() %></td>
                                                <td><%= movimiento.getDetalle() %></td>
                                                <td><%= movimiento.getImporte() %></td>
                                                <td><%= movimiento.getTipoMovimiento() != null ? movimiento.getTipoMovimiento().getTipoMovimiento() : "N/A" %></td>
                                            </tr>
                                <% 
                                        }
                                    } else {
                                %>
                                        <tr>
                                            <td colspan="4" class="text-center">No hay movimientos para esta cuenta.</td>
                                        </tr>
                                <% 
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Scripts -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>
    <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.5.28/jspdf.plugin.autotable.min.js"></script>

    <script>
        $(document).ready(function () {
            $('#movimientosTable').DataTable({
                "language": {
                    "url": "https://cdn.datatables.net/plug-ins/1.11.5/i18n/Spanish.json"
                }
            });
        });

        document.addEventListener('DOMContentLoaded', function () {
            document.getElementById('descargarPDF').addEventListener('click', function () {
                const { jsPDF } = window.jspdf;
                const doc = new jsPDF();

                // Título del PDF
                doc.text("Mis Movimientos", 20, 10);

                let rows = [];
                document.querySelectorAll("#movimientosTable tbody tr").forEach(tr => {
                    let row = [];
                    tr.querySelectorAll("td").forEach(td => row.push(td.innerText));
                    rows.push(row);
                });

                if (typeof doc.autoTable !== 'function') {
                    console.error("Error: autoTable no está definida.");
                    return;
                }

                doc.autoTable({
                    head: [['Fecha', 'Descripción', 'Monto', 'Tipo']],
                    body: rows,
                    startY: 20,
                });

                let pageHeight = doc.internal.pageSize.height;
                let fecha = new Date();
                let fechaFormateada = fecha.toLocaleDateString("es-ES"); // Formato: dd/mm/yyyy
                let currentPage = doc.internal.getNumberOfPages();

                doc.setFontSize(10);
                doc.text(`Descargado desde proyecto final Laboratorio IV`, 20, pageHeight - 15);
                doc.text(`Fecha de generación: ${fechaFormateada}`, 20, pageHeight - 10);
                doc.text(`Página ${currentPage}`, 180, pageHeight - 10);

                doc.save("movimientos.pdf");
            });
        });
    </script>

</body>

</html>
