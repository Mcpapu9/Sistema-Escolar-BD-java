
package Modelo;

// Clase base para persona con atributos comunes
public class PersonaModelo {
    private String nombre;
    private int edad;
    private String correo;

    public PersonaModelo(String nombre, int edad, String correo) {
        this.nombre = nombre;
        this.edad = edad;
        this.correo = correo;
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
}