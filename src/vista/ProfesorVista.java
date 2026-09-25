package vista;

import Controlador.ProfesorControlador;
import Modelo.ProfesorModelo;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

// Vista gráfica para administrar alumnos
public class ProfesorVista extends JFrame {

    private JTextField txtNombre, txtEdad, txtCorreo, txtnumeroEmpleado;
    private JTextArea areaResultado;
    private JButton btnAgregar, btnBuscar, btnConsultar, btnInhabilitar;
    private ProfesorControlador controller;

    public ProfesorVista() {
        
        controller = new ProfesorControlador();

        setTitle("Gestión de profesores");
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

        panelFormulario.add(new JLabel("Numero de empleado:"));
        txtnumeroEmpleado = new JTextField();
        panelFormulario.add(txtnumeroEmpleado);

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
        btnAgregar.addActionListener(e -> agregarProfesor());
        btnBuscar.addActionListener(e -> buscarProfesor());
        btnConsultar.addActionListener(e -> consultarTodos());
        btnInhabilitar.addActionListener(e -> inhabilitarProfesor());

        setVisible(true);
    }

    private void agregarProfesor() {
        controller.agregarProfesor(txtNombre.getText(), 
                Integer.parseInt(txtEdad.getText()), txtCorreo.getText(),
                txtnumeroEmpleado.getText());
       
    }

    private void buscarProfesor() {
        String numeroEmpleado = txtnumeroEmpleado.getText();
        if (numeroEmpleado.trim().isEmpty()) {
            areaResultado.setText("Ingrese numero de empleado para buscar.\n");
            return;
        }
        ProfesorModelo profesor = controller.buscarProfesor(numeroEmpleado);
        if (profesor != null) {
            String activo = profesor.isActivo() ? "Activo" : "Inhabilitado";
            areaResultado.setText("Profesor encontrado:\n" +
                "Nombre: " + profesor.getNombre() + "\n" +
                "Edad: " + profesor.getEdad() + "\n" +
                "Correo: " + profesor.getCorreo() + "\n" +
                "Numero de empleado: " + profesor.getnumeroEmpleado() + "\n" +
                "Estado: " + activo + "\n");
        } else {
            areaResultado.setText("Profesor no encontrado.\n");
        }  
    }

    private void consultarTodos() {
        List<ProfesorModelo> lista = controller.consultarTodos();
        if (lista == null || lista.isEmpty()) {
            areaResultado.setText("No hay profesores registrados.\n");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de profesores:\n");
        for (ProfesorModelo a : lista) {
            sb.append(String.format("Nombre: %s, Numero de empleado: %s, Estado: %s\n",
                    a.getNombre(),
                    a.getnumeroEmpleado(),
                    a.isActivo() ? "Activo" : "Inhabilitado"));
        }
        areaResultado.setText(sb.toString());
    }

    private void inhabilitarProfesor() {
        String numeroEmpleado = txtnumeroEmpleado.getText();
        if (numeroEmpleado.trim().isEmpty()) {
            areaResultado.setText("Ingrese numero de empleado para inhabilitar.\n");
            return;
        }
        boolean exito = controller.inhabilitarProfesor(numeroEmpleado);
        if (exito) {
            areaResultado.setText("Profesor inhabilitado correctamente.\n");
        } else {
            areaResultado.setText("No se inhabilitó profesor (¿Error numeroEmpleado).\n");
        }
    }
}