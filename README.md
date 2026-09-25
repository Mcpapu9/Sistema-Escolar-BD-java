# 🏫 Sistema de Gestión Escolar (Java + MySQL + MVC)

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Architecture](https://img.shields.io/badge/Architecture-MVC%20%2B%20DAO-blue?style=for-the-badge)](#arquitectura-del-sistema)

Sistema de gestión para el control de alumnos y profesores desarrollado en Java. Implementa una arquitectura por capas basada en el patrón de diseño **MVC (Modelo-Vista-Controlador)** junto con el patrón **DAO (Data Access Object)** para garantizar la separación de responsabilidades y la persistencia de datos orientada a bases de datos relacionales.

---

## 🚀 Características Principales

- **Gestión de Alumnos y Profesores (CRUD):** Registro, consulta individual por clave/matrícula, listado general e inhabilitación lógica (soft delete).
- **Control de Duplicados:** Verificación previa de matrículas y números de empleado únicos mediante consultas SQL parametrizadas (`PreparedStatement`).
- **Inhabilitación Lógica:** Preservación de la integridad referencial manteniendo un registro del estado activo/inactivo (`activo = 1/0`).
- **Manejo Seguro de Conexiones:** Gestión eficiente de recursos JDBC mediante el patrón *Try-with-Resources* para prevenir fugas de memoria (*connection leaks*).
- **Soporte de Entorno:** Configuración flexible de credenciales de base de datos mediante variables de entorno o parámetros por defecto para entorno local.

---

## 🏗️ Arquitectura del Sistema

El proyecto está organizado bajo la estructura **MVC + DAO** dentro del directorio `src/`:

```text
src/
├── Controlador/       # Lógica de intermediación entre Vistas y DAOs
│   ├── AlumnoControlador.java
│   └── ProfesorControlador.java
├── DAO/               # Capa de acceso e interacción con la BD (SQL)
│   ├── AlumnoDAO.java
│   └── ProfesorDAO.java
├── Modelo/            # Clases POJO / DTO de la entidad del dominio
│   ├── AlumnoModelo.java
│   ├── ProfesorModelo.java
│   ├── PersonaModelo.java
│   └── PersonaModeloPRD.java
├── util/              # Utilidades transversales (Conexión JDBC)
│   └── ConexionBD.java
└── vista/             # Interfaz de usuario (Consola/GUI)
    ├── AlumnoVista.java
    ├── ProfesorVista.java
    └── MenuPrincipal.java
```

---

## 🛠️ Requisitos e Instalación

### Prerrequisitos

- **JDK 17** o superior.
- **MySQL Server 8.0** o superior.
- **MySQL Connector/J** (`mysql-connector-j-x.x.x.jar`) añadido a las bibliotecas del proyecto.

### Pasos de Configuración

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/Mcpapu9/nombre-de-tu-repo.git
   cd nombre-de-tu-repo
   ```

2. **Cargar la Base de Datos:**
   Importa el archivo de script ubicado en `/database/schema.sql` en tu servidor MySQL (puedes usar MySQL Workbench, phpMyAdmin o la consola de MySQL):
   ```bash
   mysql -u root -p < database/schema.sql
   ```

3. **Configurar las Credenciales:**
   Por defecto, el archivo `ConexionBD.java` intenta conectarse a `localhost:3306/registro_escuela` con el usuario `root` y clave `root`. Si utilizas credenciales distintas, puedes definir variables de entorno en tu sistema:
   ```bash
   export DB_URL="jdbc:mysql://localhost:3306/registro_escuela"
   export DB_USER="tu_usuario"
   export DB_PASSWORD="tu_password"
   ```

4. **Ejecutar la Aplicación:**
   Abre el proyecto en tu IDE preferido (NetBeans, Eclipse o IntelliJ), compila y ejecuta la clase principal `MenuPrincipal.java`.

---

## 🗄️ Esquema de la Base de Datos

El script SQL suministrado incluye la creación de las tablas y registros iniciales de prueba:

| Tabla | Clave Primaria | Clave Única | Descripción |
| :--- | :--- | :--- | :--- |
| `alumnos` | `id` | `matricula` | Almacena datos personales, correo y estado del estudiante. |
| `profesor` | `id` | `num_emp` | Almacena datos del docente, sueldo, correo y número de empleado. |
| `materia` | `idMateria` | - | Registro de catálogo de materias disponibles. |

---

## ✒️ Autor

* **Luis Ariel Granados Campos** - [@Mcpapu9](https://github.com/Mcpapu9)


