package Controlador;

import DAO.ProfesorDAO;
import Modelo.ProfesorModelo;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

// Controlador para manejar lógica entre la vista y modelo/DAO
public class ProfesorControlador {

    private ProfesorDAO profesorDAO;

    public ProfesorControlador() {
        profesorDAO = new ProfesorDAO();
    }

    public boolean agregarProfesor(String nombre, int edad, String correo, String numeroEmpleado) {
        if(nombre == null || nombre.trim().isEmpty() || edad <= 0 ||
           correo == null || correo.trim().isEmpty() ||
           numeroEmpleado == null || numeroEmpleado.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error en datos");
            return false; // Datos incompletos
        }

        ProfesorModelo profesor = new ProfesorModelo(nombre, edad, correo, numeroEmpleado);
        try {
            JOptionPane.showMessageDialog(null, "Profesor agregado");
            return profesorDAO.agregarProfesor(profesor);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ProfesorModelo buscarProfesor(String numeroEmpleado) {
        try {
            return profesorDAO.buscarProfesor(numeroEmpleado);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ProfesorModelo> consultarTodos() {
        try {
            return profesorDAO.consultarTodos();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean inhabilitarProfesor(String numeroEmpleado) {
        try {
            return profesorDAO.inhabilitarProfesor(numeroEmpleado);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
