<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <title>Iconos de Rechazo y Aprobación</title>
    <style>
        .icono-rechazo, .icono-aprobado {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            position: relative;
            margin: 10px;
        }
        .icono-rechazo {
            background-color: red;
        }
        .icono-rechazo::before,
        .icono-rechazo::after {
            content: '';
            position: absolute;
            top: 50%;
            left: 50%;
            width: 30px;
            height: 5px;
            background-color: white;
        }
        .icono-rechazo::before {
            transform: translate(-50%, -50%) rotate(45deg);
        }
        .icono-rechazo::after {
            transform: translate(-50%, -50%) rotate(-45deg);
        }
        .icono-aprobado {
            background-color: green;
        }
        .icono-aprobado::before {
            content: '';
            position: absolute;
            top: 50%;
            left: 50%;
            width: 10px;
            height: 20px;
            border: solid white;
            border-width: 0 5px 5px 0;
            transform: translate(-50%, -50%) rotate(45deg);
        }
    </style>
</head>
<body>
    <div class="d-flex justify-content-center align-items-center" style="height: 100vh;">
        <div class="icono-rechazo"></div>
        <div class="icono-aprobado"></div>
    </div>

    <!-- JavaScript de Bootstrap 5 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
