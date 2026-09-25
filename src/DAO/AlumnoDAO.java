/**
    * Ejemplo de implementacion de DAO + VO(DTO)
    * 1.- el patrón DAO propone separar por completo la lógica de negocio 
    * de la lógica para acceder a los datos, de esta forma, 
    * el DAO proporcionará los métodos necesarios para insertar, 
    * actualizar, borrar y consultar la información; 
    * 
    * por otra parte, 
    * la capa de negocio solo se preocupa por lógica de negocio y 
    * utiliza el DAO para interactuar con la fuente de datos.
    * 
    *Objetivo DAO: Proveer acceso a un modelo sin revelar datos
    *              de su estructura interna.
   */ 

package DAO;

import Modelo.AlumnoModelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DAO para manejar operaciones BD de Alumnos
public class AlumnoDAO {

    private Connection conexion;

    public AlumnoDAO() {
        conectarBD();
    }

    // Método para conectar a la base de datos
    private void conectarBD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/registro_escuela", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Agrega alumno a la BD
    public boolean agregarAlumno(AlumnoModelo alumno) throws SQLException {
        // Verificar si ya existe matricula
        String queryBuscar = "SELECT * FROM alumnos WHERE matricula = ?";
        PreparedStatement stmtBuscar = conexion.prepareStatement(queryBuscar);
        stmtBuscar.setString(1, alumno.getMatricula());
        ResultSet rs = stmtBuscar.executeQuery();
        if (rs.next()) {
            return false; // Ya existe
        }

        // Insertar nuevo alumno
        String queryInsert = "INSERT INTO alumnos (nombre, edad, correo, matricula, activo) VALUES (?, ?, ?, ?, 1)";
        PreparedStatement stmt = conexion.prepareStatement(queryInsert);
        stmt.setString(1, alumno.getNombre());
        stmt.setInt(2, alumno.getEdad());
        stmt.setString(3, alumno.getCorreo());
        stmt.setString(4, alumno.getMatricula());

        int res = stmt.executeUpdate();
        return res > 0;
    }

    // Buscar alumno por matrícula
    public AlumnoModelo buscarAlumno(String matricula) throws SQLException {
        String query = "SELECT * FROM alumnos WHERE matricula = ?";
        PreparedStatement stmt = conexion.prepareStatement(query);
        stmt.setString(1, matricula);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            AlumnoModelo a = new AlumnoModelo(
                rs.getString("nombre"),
                rs.getInt("edad"),
                rs.getString("correo"),
                rs.getString("matricula")
            );
            a.setActivo(rs.getBoolean("activo"));
            return a;
        }
        return null;
    }

    // Consultar todos los alumnos
    public List<AlumnoModelo> consultarTodos() throws SQLException {
        List<AlumnoModelo> lista = new ArrayList<>();
        String query = "SELECT * FROM alumnos";
        PreparedStatement stmt = conexion.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            AlumnoModelo a = new AlumnoModelo(
                rs.getString("nombre"),
                rs.getInt("edad"),
                rs.getString("correo"),
                rs.getString("matricula")
            );
            a.setActivo(rs.getBoolean("activo"));
            lista.add(a);
        }
        return lista;
    }

    // Inhabilitar alumno (cambiar estado activo)
    public boolean inhabilitarAlumno(String matricula) throws SQLException {
        String query = "UPDATE alumnos SET activo = 0 WHERE matricula = ?";
        PreparedStatement stmt = conexion.prepareStatement(query);
        stmt.setString(1, matricula);
        int res = stmt.executeUpdate();
        return res > 0;
    }
}
