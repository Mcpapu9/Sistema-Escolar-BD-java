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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DAO para manejar operaciones BD de Profesores
public class ProfesorDAO {

    private Connection conexion;

    public ProfesorDAO() {
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

    // Agrega profesor a la BD
    public boolean agregarProfesor(ProfesorModelo profesor) throws SQLException {
        // Verificar si ya existe numeroEmpleado
        String queryBuscar = "SELECT * FROM profesor WHERE num_emp = ?";
        PreparedStatement stmtBuscar = conexion.prepareStatement(queryBuscar);
        stmtBuscar.setString(1, profesor.getnumeroEmpleado());
        ResultSet rs = stmtBuscar.executeQuery();
        if (rs.next()) {
            return false; // Ya existe
        }

        // Insertar nuevo profesor
        String queryInsert = "INSERT INTO profesor (nombre, edad, correo, num_emp, activo) VALUES (?, ?, ?, ?, 1)";
        PreparedStatement stmt = conexion.prepareStatement(queryInsert);
        stmt.setString(1, profesor.getNombre());
        stmt.setInt(2, profesor.getEdad());
        stmt.setString(3, profesor.getCorreo());
        stmt.setString(4, profesor.getnumeroEmpleado());

        int res = stmt.executeUpdate();
        return res > 0;
    }

    // Buscar profesor por número de empleado
    public ProfesorModelo buscarProfesor(String numeroEmpleado) throws SQLException {
        String query = "SELECT * FROM profesor WHERE num_emp = ?";
        PreparedStatement stmt = conexion.prepareStatement(query);
        stmt.setString(1, numeroEmpleado);
        ResultSet rs = stmt.executeQuery();

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
        return null;
    }

    // Consultar todos los profesores
    public List<ProfesorModelo> consultarTodos() throws SQLException {
        List<ProfesorModelo> lista = new ArrayList<>();
        String query = "SELECT * FROM profesor";
        PreparedStatement stmt = conexion.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

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
        return lista;
    }

    // Inhabilitar profesor (cambiar estado activo)
    public boolean inhabilitarProfesor(String numeroEmpleado) throws SQLException {
        String query = "UPDATE profesor SET activo = 0 WHERE num_emp = ?";
        PreparedStatement stmt = conexion.prepareStatement(query);
        stmt.setString(1, numeroEmpleado);
        int res = stmt.executeUpdate();
        return res > 0;
    }
}
