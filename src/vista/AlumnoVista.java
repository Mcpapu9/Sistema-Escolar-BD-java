package vista;

import Controlador.AlumnoControlador;
import Modelo.AlumnoModelo;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

// Vista gráfica para administrar alumnos
public class AlumnoVista extends JFrame {

    private JTextField txtNombre, txtEdad, txtCorreo, txtMatricula;
    private JTextArea areaResultado;
    private JButton btnAgregar, btnBuscar, btnConsultar, btnInhabilitar;
    private AlumnoControlador controller;

    public AlumnoVista() {
        
        controller = new AlumnoControlador();

        setTitle("Gestión de Alumnos");
        setSize(700, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel formulario con etiquetas y campos
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2));

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Edad:"));
        txtEdad = new JTextField("0");
        panelFormulario.add(txtEdad);

        panelFormulario.add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        panelFormulario.add(txtCorreo);

        panelFormulario.add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        panelFormulario.add(txtMatricula);

        add(panelFormulario, BorderLayout.NORTH);

        // Panel botones
        JPanel panelBotones = new JPanel();
        btnAgregar = new JButton("Agregar");
        btnBuscar = new JButton("Buscar");
        btnConsultar = new JButton("Consultar Todos");
        btnInhabilitar = new JButton("Inhabilitar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnInhabilitar);

        add(panelBotones, BorderLayout.CENTER);

        // Área de resultados
        areaResultado = new JTextArea(10, 40);
        add(new JScrollPane(areaResultado), BorderLayout.SOUTH);

        // Eventos botones
        btnAgregar.addActionListener(e -> agregarAlumno());
        btnBuscar.addActionListener(e -> buscarAlumno());
        btnConsultar.addActionListener(e -> consultarTodos());
        btnInhabilitar.addActionListener(e -> inhabilitarAlumno());

        setVisible(true);
    }

    private void agregarAlumno() {
        controller.agregarAlumno(txtNombre.getText(), 
                Integer.parseInt(txtEdad.getText()), txtCorreo.getText(),
                txtMatricula.getText());
       
    }

    private void buscarAlumno() {
        String matricula = txtMatricula.getText();
        if (matricula.trim().isEmpty()) {
            areaResultado.setText("Ingrese matrícula para buscar.\n");
            return;
        }
        AlumnoModelo alumno = controller.buscarAlumno(matricula);
        if (alumno != null) {
            String activo = alumno.isActivo() ? "Activo" : "Inhabilitado";
            areaResultado.setText("Alumno encontrado:\n" +
                "Nombre: " + alumno.getNombre() + "\n" +
                "Edad: " + alumno.getEdad() + "\n" +
                "Correo: " + alumno.getCorreo() + "\n" +
                "Matrícula: " + alumno.getMatricula() + "\n" +
                "Estado: " + activo + "\n");
        } else {
            areaResultado.setText("Alumno no encontrado.\n");
        }  
    }

    private void consultarTodos() {
        List<AlumnoModelo> lista = controller.consultarTodos();
        if (lista == null || lista.isEmpty()) {
            areaResultado.setText("No hay alumnos registrados.\n");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de alumnos:\n");
        for (AlumnoModelo a : lista) {
            sb.append(String.format("Nombre: %s, Matrícula: %s, Estado: %s\n",
                    a.getNombre(),
                    a.getMatricula(),
                    a.isActivo() ? "Activo" : "Inhabilitado"));
        }
        areaResultado.setText(sb.toString());
    }

    private void inhabilitarAlumno() {
        String matricula = txtMatricula.getText();
        if (matricula.trim().isEmpty()) {
            areaResultado.setText("Ingrese matrícula para inhabilitar.\n");
            return;
        }
        boolean exito = controller.inhabilitarAlumno(matricula);
        if (exito) {
            areaResultado.setText("Alumno inhabilitado correctamente.\n");
        } else {
            areaResultado.setText("No se inhabilitó alumno (¿Error matrícula).\n");
        }
    }
}