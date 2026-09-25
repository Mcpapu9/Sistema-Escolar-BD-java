package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    // Se leen primero las variables de entorno; si no existen, se usan los valores por defecto locales
    private static final String URL = System.getenv("DB_URL") != null 
            ? System.getenv("DB_URL") 
            : "jdbc:mysql://localhost:3306/registro_escuela";

    private static final String USUARIO = System.getenv("DB_USER") != null 
            ? System.getenv("DB_USER") 
            : "root";

    private static final String CLAVE = System.getenv("DB_PASSWORD") != null 
            ? System.getenv("DB_PASSWORD") 
            : "root";

    // Método para obtener la conexión
    public static Connection obtenerConexion() throws SQLException {
        try {
            // Asegura la carga del driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL no encontrado en el classpath.", e);
        }
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }

    // Método para cerrar una conexión (útil si no usas try-with-resources)
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