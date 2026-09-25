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

import Modelo.ProfesorModelo;
import util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejemplo de implementación de DAO + VO(DTO)
 * DAO para manejar operaciones BD de Profesores
 */
public class ProfesorDAO {

    // Ya no se requiere constructor con conectarBD() ni atributo conexion privado.

    // Agrega profesor a la BD
    public boolean agregarProfesor(ProfesorModelo profesor) throws SQLException {
        String queryBuscar = "SELECT 1 FROM profesor WHERE num_emp = ?";
        String queryInsert = "INSERT INTO profesor (nombre, edad, correo, num_emp, activo) VALUES (?, ?, ?, ?, 1)";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement stmtBuscar = conexion.prepareStatement(queryBuscar)) {

            stmtBuscar.setString(1, profesor.getnumeroEmpleado());
            try (ResultSet rs = stmtBuscar.executeQuery()) {
                if (rs.next()) {
                    return false; // Ya existe el número de empleado
                }
            }

            try (PreparedStatement stmtInsert = conexion.prepareStatement(queryInsert)) {
                stmtInsert.setString(1, profesor.getNombre());
                stmtInsert.setInt(2, profesor.getEdad());
                stmtInsert.setString(3, profesor.getCorreo());
                stmtInsert.setString(4, profesor.getnumeroEmpleado());

                int res = stmtInsert.executeUpdate();
                return res > 0;
            }
        }
    }

    // Buscar profesor por número de empleado
    public ProfesorModelo buscarProfesor(String numeroEmpleado) throws SQLException {
        String query = "SELECT * FROM profesor WHERE num_emp = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(query)) {

            stmt.setString(1, numeroEmpleado);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ProfesorModelo p = new ProfesorModelo(
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("correo"),
                        rs.getString("num_emp")
                    );
                    p.setActivo(rs.getBoolean("activo"));
                    return p;
                }
            }
        }
        return null;
    }

    // Consultar todos los profesores
    public List<ProfesorModelo> consultarTodos() throws SQLException {
        List<ProfesorModelo> lista = new ArrayList<>();
        String query = "SELECT * FROM profesor";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProfesorModelo p = new ProfesorModelo(
                    rs.getString("nombre"),
                    rs.getInt("edad"),
                    rs.getString("correo"),
                    rs.getString("num_emp")
                );
                p.setActivo(rs.getBoolean("activo"));
                lista.add(p);
            }
        }
        return lista;
    }

    // Inhabilitar profesor (cambiar estado activo)
    public boolean inhabilitarProfesor(String numeroEmpleado) throws SQLException {
        String query = "UPDATE profesor SET activo = 0 WHERE num_emp = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(query)) {

            stmt.setString(1, numeroEmpleado);
            int res = stmt.executeUpdate();
            return res > 0;
        }
    }
}