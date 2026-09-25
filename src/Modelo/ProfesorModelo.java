package Modelo;

// ProfesorModelo hereda de PersonaModelo y añade numero de empleado y estado activo
public class ProfesorModelo extends PersonaModeloPRD {
    private String numeroEmpleado;
    private boolean activo;

    public ProfesorModelo(String nombre, int edad, String correo, String numeroEmpleado) {
        super(nombre, edad, correo);
        this.numeroEmpleado = numeroEmpleado;
        this.activo = true; // Por defecto activo
    }

    public String getnumeroEmpleado() {
        return numeroEmpleado;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}