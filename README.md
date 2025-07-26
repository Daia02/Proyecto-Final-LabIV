# Proyecto Final - Laboratorio IV

Este es un proyecto desarrollado en **Java** como trabajo integrador final para la materia **Laboratorio de Computación IV**. La aplicación fue construida utilizando tecnologías clave del desarrollo web con Java y emplea una arquitectura en capas bien definida.

---

## 🛠️ Tecnologías utilizadas

- **Java (Servlets)**: para el manejo de peticiones HTTP.
- **JSP (JavaServer Pages)**: para la generación dinámica de contenido en la interfaz.
- **MySQL**: como sistema de gestión de base de datos relacional.
- **JDBC**: para la conexión entre Java y MySQL.
- **Excepciones personalizadas**: para el manejo de errores de forma controlada.
- **Interfaces Java**: para implementar principios de abstracción.
- **Arquitectura en capas**:
  - `dao`: clases que manejan el acceso a datos (consultas SQL, conexión).
  - `entidades`: contiene las clases modelo (POJOs) que representan los datos del sistema.
  - `excepciones`: clases para la gestión de errores específicos de la aplicación.
  - `negocio`: lógica de negocio, validaciones y procesos intermedios.
  - `servlet`: controladores que reciben y responden a las solicitudes del usuario.
  - `WebContent/`: carpeta que contiene todos los archivos JSP para la capa de presentación.

---

## 🔌 Conexión a la base de datos

La aplicación se conecta a una base de datos **MySQL**.  
La configuración de acceso (usuario, contraseña, URL) está definida en una clase dedicada, como `Conexion.java`.

> Se recomienda ejecutar el script `database.sql` (si está disponible) antes de lanzar la aplicación para crear las tablas necesarias.

---

## 🚀 Cómo ejecutar el proyecto

1. Clonar o descargar este repositorio.
2. Abrir **Eclipse** e importar como **Dynamic Web Project**.
3. Configurar el servidor Apache Tomcat.
4. Crear la base de datos en MySQL.
5. Modificar la clase de conexión si es necesario (`Conexion.java`).
6. Ejecutar el proyecto en el servidor.
7. Acceder desde el navegador:


