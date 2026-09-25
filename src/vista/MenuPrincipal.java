package vista;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("Sistema de Gestión Escolar");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // HIDE, DISPOSE, EXIT
        setLocationRelativeTo(null); // Centra la ventana

        // Panel para botones
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton btnAlumno = new JButton("Alumno");
        JButton btnProfesor = new JButton("Profesor");
        JButton btnMateria = new JButton("Materia");
        JButton btnGrupo = new JButton("Grupo");
        JButton btnSalir = new JButton("Salir");

        // Acción solo para Alumno, FALTA LOS DEMAS PROFESO, MATERIA, GRUPO, ETC.
        btnAlumno.addActionListener(e -> new AlumnoVista());
        // Acción solo para Profesor, FALTA LOS DEMAS PROFESO, MATERIA, GRUPO, ETC.
        btnProfesor.addActionListener(e -> new ProfesorVista());
        
        /*
        btnAlumno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                new AlumnoVista(); // abre la vista del alumno
            }
        })  ;  */

        // Los demás aún no disponibles
      
        btnMateria.addActionListener(e
                -> JOptionPane.showMessageDialog(null, "Esta opción aún no está disponible."));
        btnGrupo.addActionListener(e
                -> JOptionPane.showMessageDialog(null, "Esta opción aún no está disponible."));

        btnSalir.addActionListener(e -> System.exit(0));

        // Agrega los botones al panel
        panel.add(btnAlumno);
        panel.add(btnProfesor);
        panel.add(btnMateria);
        panel.add(btnGrupo);
        panel.add(btnSalir);

        // Agrega el panel a la ventana
        add(panel);
        setVisible(true);
    }
}
