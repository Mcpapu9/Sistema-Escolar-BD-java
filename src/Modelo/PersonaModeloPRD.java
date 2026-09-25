package Modelo;

// Clase base para persona con atributos comunes
public class PersonaModeloPRD {
    private String nombre;
    private int edad;
    private String correo;
    private String sueldo;

    public PersonaModeloPRD(String nombre, int edad, String correo) {
        this.nombre = nombre;
        this.edad = edad;
        this.correo = correo;
        this.sueldo = sueldo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getCorreo() {
        return correo;
    }
    
    public String getSueldo() {
        return sueldo;
    }
}