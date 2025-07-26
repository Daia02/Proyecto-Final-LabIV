CREATE DATABASE DB_UtnHomeBanking;
USE DB_UtnHomeBanking;

-- Tabla Nacionalidad
CREATE TABLE Nacionalidad (
    Nro_Nacionalidad INT PRIMARY KEY AUTO_INCREMENT,
    Nacionalidad VARCHAR(50) NOT NULL
);

-- Tabla Provincia
CREATE TABLE Provincia (
    Nro_Provincia INT PRIMARY KEY AUTO_INCREMENT,
    Nro_Nacionalidad INT NOT NULL,
    Nombre_Provincia VARCHAR(50) NOT NULL,
    FOREIGN KEY (Nro_Nacionalidad) REFERENCES Nacionalidad(Nro_Nacionalidad)
);

-- Tabla Localidad
CREATE TABLE Localidad (
    Nro_Localidad INT PRIMARY KEY AUTO_INCREMENT,
    Nro_Provincia INT,
    Nombre_Localidad VARCHAR(50) NOT NULL,
    FOREIGN KEY (Nro_Provincia) REFERENCES Provincia(Nro_Provincia)
);

-- Tabla Usuarios
CREATE TABLE Usuarios (
    ID_Usuario INT PRIMARY KEY AUTO_INCREMENT,
    Nombre_usuario VARCHAR(50) UNIQUE NOT NULL,
    Contrasenia VARCHAR(50) NOT NULL,
    Estado BOOLEAN NOT NULL,
    UNIQUE (Nombre_usuario)
);

-- Tabla Administradores
CREATE TABLE Administradores (
    ID_Admin INT PRIMARY KEY AUTO_INCREMENT,
    DNI VARCHAR(15) NOT NULL,
    Nombre VARCHAR(50) NOT NULL,
    Apellido VARCHAR(50) NOT NULL,
    Contrasenia VARCHAR(50) NOT NULL,
    Estado BOOLEAN NOT NULL
);

-- Tabla Clientes
CREATE TABLE Clientes (
    CUIL_cl BIGINT PRIMARY KEY,
    ID_Usuario INT UNIQUE,
    Nro_Localidad INT,
    Nro_Provincia INT,
    Nro_Nacionalidad INT,
    DNI VARCHAR(15) UNIQUE NOT NULL,
    Nombre VARCHAR(50) NOT NULL,
    Apellido VARCHAR(50) NOT NULL,
    Sexo CHAR(1) NOT NULL,
    Fecha_de_nacimiento DATE NOT NULL,
    Dirección VARCHAR(100),
    Correo_electrónico VARCHAR(100),
    Teléfono VARCHAR(20),
    Estado BOOLEAN NOT NULL,
    FOREIGN KEY (ID_Usuario) REFERENCES Usuarios(ID_Usuario),
    FOREIGN KEY (Nro_Localidad) REFERENCES Localidad(Nro_Localidad),
    FOREIGN KEY (Nro_Provincia) REFERENCES Provincia(Nro_Provincia),
    FOREIGN KEY (Nro_Nacionalidad) REFERENCES Nacionalidad(Nro_Nacionalidad)
);

-- Tabla Tipos_de_cuenta
CREATE TABLE Tipos_de_cuenta (
    Nro_Tipo_cuenta INT PRIMARY KEY,
    Tipo_de_cuenta VARCHAR(50) NOT NULL
);

-- Tabla Cuentas
CREATE TABLE Cuentas (
    Nro_cuenta INT PRIMARY KEY,
    CUIL_cu BIGINT,
    Nro_Tipo_cuenta INT,
    Fecha_de_creación DATE NOT NULL,
    CBU VARCHAR(22) UNIQUE NOT NULL,
    Saldo DECIMAL(10, 2) NOT NULL,
    Estado BOOLEAN NOT NULL,
    FOREIGN KEY (CUIL_cu) REFERENCES Clientes(CUIL_cl),
    FOREIGN KEY (Nro_Tipo_cuenta) REFERENCES Tipos_de_cuenta(Nro_Tipo_cuenta)
);


-- Tabla Solicitudes_prestamos (Pendientes)
CREATE TABLE Solicitudes_prestamos (
    Nro_solicitud INT PRIMARY KEY AUTO_INCREMENT,
    CUIL_pr BIGINT NOT NULL,
    Importe_pedido DECIMAL(10, 2) NOT NULL,
    Interes DECIMAL(5, 2) NOT NULL,
    Plazo_de_pago INT NOT NULL,
    Total_a_pagar DECIMAL(10, 2) NOT NULL,
    Estado ENUM('Pendiente', 'Aprobada', 'Rechazada') DEFAULT 'Pendiente',
    Fecha_solicitud DATE NOT NULL,
    Nro_cuenta INT NOT NULL,
    FOREIGN KEY (CUIL_pr) REFERENCES Clientes(CUIL_cl),
    FOREIGN KEY (Nro_cuenta) REFERENCES Cuentas(Nro_cuenta)
);

-- Tabla Prestamos_aprobados
CREATE TABLE Prestamos_aprobados (
    Nro_prestamo INT PRIMARY KEY AUTO_INCREMENT,
    Nro_solicitud INT UNIQUE NOT NULL,
    CUIL_pr BIGINT NOT NULL,
    Nro_cuenta INT NOT NULL,
    ID_Admin INT NOT NULL,
    Fecha_aprobacion DATE NOT NULL,
    Importe_aprobado DECIMAL(10, 2) NOT NULL,
    Total_a_pagar DECIMAL(10, 2) NOT NULL,
    Plazo_de_pago INT NOT NULL,
    Interes_aplicado DECIMAL(5, 2) NOT NULL,
    Cantidad_cuotas INT NOT NULL,
    Estado ENUM('En curso', 'Pagado') NOT NULL,
    FOREIGN KEY (Nro_solicitud) REFERENCES Solicitudes_prestamos(Nro_solicitud),
    FOREIGN KEY (CUIL_pr) REFERENCES Clientes(CUIL_cl),
    FOREIGN KEY (ID_Admin) REFERENCES Administradores(ID_Admin),
    FOREIGN KEY (Nro_cuenta) REFERENCES Cuentas(Nro_cuenta)
);

-- Tabla Pagos_prestamos
CREATE TABLE Pagos_prestamos (
    Nro_pago INT PRIMARY KEY AUTO_INCREMENT,
    Nro_prestamo INT NOT NULL,
    Nro_cuenta INT NOT NULL,
    Fecha_pago DATE NOT NULL,
    Importe_pagado DECIMAL(10, 2) NOT NULL,
    Cuotas_restantes INT NOT NULL,
    FOREIGN KEY (Nro_prestamo) REFERENCES Prestamos_aprobados(Nro_prestamo),
    FOREIGN KEY (Nro_cuenta) REFERENCES Cuentas(Nro_cuenta)
);

-- Tabla Historial_prestamos
CREATE TABLE Historial_prestamos (
    Nro_historial INT PRIMARY KEY AUTO_INCREMENT,
    Nro_solicitud INT NOT NULL,
    CUIL_pr BIGINT NOT NULL,
    Nro_cuenta INT NOT NULL,
    Fecha_solicitud DATE NOT NULL,
    ID_Admin INT NOT NULL,
    Estado_final ENUM('Aprobado', 'Rechazado') NOT NULL,
    Observaciones VARCHAR(255),
    FOREIGN KEY (Nro_solicitud) REFERENCES Solicitudes_prestamos(Nro_solicitud),
    FOREIGN KEY (CUIL_pr) REFERENCES Clientes(CUIL_cl),
    FOREIGN KEY (ID_Admin) REFERENCES Administradores(ID_Admin),
    FOREIGN KEY (Nro_cuenta) REFERENCES Cuentas(Nro_cuenta)
);


-- Tabla Tipos_de_movimiento
CREATE TABLE Tipos_de_movimiento (
    Nro_Tipo_movimiento INT PRIMARY KEY,
    Tipo_de_movimiento VARCHAR(50) NOT NULL
);

-- Tabla Movimientos
CREATE TABLE Movimientos (
    Nro_movimiento INT PRIMARY KEY,
    Nro_Tipo_movimiento INT,
    CUIL_emisor_mo BIGINT,
    CUIL_receptor_mo BIGINT,
    Fecha DATE NOT NULL,
    Detalle VARCHAR(100),
    Importe DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (Nro_Tipo_movimiento) REFERENCES Tipos_de_movimiento(Nro_Tipo_movimiento),
    FOREIGN KEY (CUIL_emisor_mo) REFERENCES Clientes(CUIL_cl),
    FOREIGN KEY (CUIL_receptor_mo) REFERENCES Clientes(CUIL_cl)
);