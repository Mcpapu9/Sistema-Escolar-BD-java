-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS registro_escuela;
USE registro_escuela;
-- Crear tabla de alumnos
CREATE TABLE IF NOT EXISTS alumnos (
   id INT AUTO_INCREMENT PRIMARY KEY,         -- Identificador único (clave primaria)
   nombre VARCHAR(100) NOT NULL,              -- Nombre del alumno
   edad INT NOT NULL CHECK (edad > 0),        -- Edad (mayor que cero)
   correo VARCHAR(100) NOT NULL,              -- Correo electrónico
   matricula VARCHAR(20) NOT NULL UNIQUE,     -- Matrícula única
   activo BOOLEAN DEFAULT 1                   -- Activo = 1 (activo), 0 = inhabilitado
);
-- Insertar alumnos de ejemplo en la tabla 'alumnos'
INSERT INTO alumnos (nombre, edad, correo, matricula, activo) VALUES
('María Fernanda López', 18, 'maria.fernanda@gmail.com', 'A2023001', 1),
('José Luis Martínez', 18, 'jose.martinez@hotmail.com', 'A2023002', 1),
('Ana Sofía Ramírez', 17, 'ana.ramirez@yahoo.com', 'A2023003', 1),
('Carlos Alberto Núñez', 19, 'carlos.nunez@outlook.com', 'A2023004', 1),
('Valeria González Pérez', 18, 'valeria.gonzalez@gmail.com', 'A2023005', 1);
 
show databases;
 
-- Crear tabla de profesores
CREATE TABLE IF NOT EXISTS profesor (
   id INT AUTO_INCREMENT PRIMARY KEY,         -- Identificador único (clave primaria)
   nombre VARCHAR(100) NOT NULL,              -- Nombre del profesor
   edad INT NOT NULL CHECK (edad > 0),        -- Edad (mayor que cero)
   correo VARCHAR(100) NOT NULL,              -- Correo electrónico
   num_emp VARCHAR(20) NOT NULL UNIQUE,       -- numero de empleado única
   sueldo decimal(8,2) not null,
   activo BOOLEAN DEFAULT 1                   -- Activo = 1 (activo), 0 = inhabilitado
);
-- Insertar alumnos de ejemplo en la tabla 'profesores'
INSERT INTO profesor (nombre, edad, correo, num_emp, sueldo, activo)
VALUES
('Ana María Rodríguez', 45, 'ana.rodriguez@universidad.edu.mx', 'EMP1001', 32500.50, 1),
('Carlos Ernesto Mejía', 39, 'carlos.mejia@universidad.edu.mx', 'EMP1002', 28750.75, 1),
('Leticia Gómez Herrera', 52, 'leticia.gomez@universidad.edu.mx', 'EMP1003', 36200.00, 1),
('José Luis Padilla', 60, 'jose.padilla@universidad.edu.mx', 'EMP1004', 29800.00, 0),
('Martha Elena Solís', 34, 'martha.solis@universidad.edu.mx', 'EMP1005', 31250.30, 1);
 
 CREATE TABLE IF NOT EXISTS materia (
    idMateria INT AUTO_INCREMENT PRIMARY KEY,         -- Identificador único
    nombre VARCHAR(100) NOT NULL,                     -- Nombre de la materia
    descripcion TEXT,                                 -- Descripción (más larga que VARCHAR)
    activo BOOLEAN DEFAULT 1                          -- 1 = activa, 0 = inactiva
);

INSERT INTO materia (nombre, descripcion, activo) VALUES
('Programación Orientada a Objetos', 'Estudio de los principios fundamentales de la programación orientada a objetos como herencia, encapsulamiento y polimorfismo.', 1),
('Bases de Datos I', 'Introducción a los sistemas de bases de datos, su diseño lógico, modelo relacional y lenguaje SQL.', 1),
('Redes de Computadoras', 'Fundamentos de redes de datos, protocolos, modelos OSI y TCP/IP, así como configuración básica de redes.', 1),
('Sistemas Operativos', 'Análisis de los componentes y funcionamiento de los sistemas operativos modernos, incluyendo procesos e hilos.', 0),
('Ingeniería de Software', 'Metodologías de desarrollo de software, incluyendo modelos en cascada, ágil, documentación y pruebas.', 1);
show databases;