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
import util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejemplo de implementación de DAO + VO(DTO)
 * DAO para manejar operaciones BD de Alumnos
 */
public class AlumnoDAO {

    // Ya no se requiere constructor con conectarBD() ni atributo conexion privado.

    // Agrega alumno a la BD
    public boolean agregarAlumno(AlumnoModelo alumno) throws SQLException {
        String queryBuscar = "SELECT 1 FROM alumnos WHERE matricula = ?";
        String queryInsert = "INSERT INTO alumnos (nombre, edad, correo, matricula, activo) VALUES (?, ?, ?, ?, 1)";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement stmtBuscar = conexion.prepareStatement(queryBuscar)) {

            stmtBuscar.setString(1, alumno.getMatricula());
            try (ResultSet rs = stmtBuscar.executeQuery()) {
                if (rs.next()) {
                    return false; // Ya existe la matrícula
                }
            }

            try (PreparedStatement stmtInsert = conexion.prepareStatement(queryInsert)) {
                stmtInsert.setString(1, alumno.getNombre());
                stmtInsert.setInt(2, alumno.getEdad());
                stmtInsert.setString(3, alumno.getCorreo());
                stmtInsert.setString(4, alumno.getMatricula());

                int res = stmtInsert.executeUpdate();
                return res > 0;
            }
        }
    }

    // Buscar alumno por matrícula
    public AlumnoModelo buscarAlumno(String matricula) throws SQLException {
        String query = "SELECT * FROM alumnos WHERE matricula = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(query)) {

            stmt.setString(1, matricula);

            try (ResultSet rs = stmt.executeQuery()) {
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
            }
        }
        return null;
    }

    // Consultar todos los alumnos
    public List<AlumnoModelo> consultarTodos() throws SQLException {
        List<AlumnoModelo> lista = new ArrayList<>();
        String query = "SELECT * FROM alumnos";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

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
        }
        return lista;
    }

    // Inhabilitar alumno (cambiar estado activo)
    public boolean inhabilitarAlumno(String matricula) throws SQLException {
        String query = "UPDATE alumnos SET activo = 0 WHERE matricula = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(query)) {

            stmt.setString(1, matricula);
            int res = stmt.executeUpdate();
            return res > 0;
        }
    }
}