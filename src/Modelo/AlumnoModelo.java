package Modelo;

// AlumnoModelo hereda de PersonaModelo y añade matrícula y estado activo
public class AlumnoModelo extends PersonaModelo {
    private String matricula;
    private boolean activo;

    public AlumnoModelo(String nombre, int edad, String correo, String matricula) {
        super(nombre, edad, correo);
        this.matricula = matricula;
        this.activo = true; // Por defecto activo
    }

    public String getMatricula() {
        return matricula;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}