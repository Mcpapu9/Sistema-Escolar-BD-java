package Controlador;

import DAO.AlumnoDAO;
import Modelo.AlumnoModelo;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

// Controlador para manejar lógica entre la vista y modelo/DAO
public class AlumnoControlador {

    private AlumnoDAO alumnoDAO;

    public AlumnoControlador() {
        alumnoDAO = new AlumnoDAO();
    }

    public boolean agregarAlumno(String nombre, int edad, String correo, String matricula) {
        if(nombre == null || nombre.trim().isEmpty() || edad <= 0 ||
           correo == null || correo.trim().isEmpty() ||
           matricula == null || matricula.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error en datos");  // AGREGADO
            return false; // Datos incompletos
        }

        AlumnoModelo alumno = new AlumnoModelo(nombre, edad, correo, matricula);
        try {
            JOptionPane.showMessageDialog(null, "Alumno gregado");
            return alumnoDAO.agregarAlumno(alumno);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public AlumnoModelo buscarAlumno(String matricula) {
        try {
            return alumnoDAO.buscarAlumno(matricula);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<AlumnoModelo> consultarTodos() {
        try {
            return alumnoDAO.consultarTodos();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean inhabilitarAlumno(String matricula) {
        try {
            return alumnoDAO.inhabilitarAlumno(matricula);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}