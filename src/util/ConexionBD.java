// ConexionBD
package util;

import java.sql.Connection;  // para poder crear la conexion
import java.sql.DriverManager;  // Para realizar la conexion
import java.sql.SQLException;   // Para obtener los errores de la conexion BD

public class ConexionBD {

    // Datos de conexión a la base de datos (ajustar según el entorno)
    // con final se crean constantes, y se escriben en mayusculas
    private static final String URL = "jdbc:mysql://localhost:3306/registro_escuela";
    private static final String USUARIO = "root";
    private static final String CLAVE = "root";

    // Método para obtener la conexión
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }

    // Método para cerrar una conexión
    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }
}