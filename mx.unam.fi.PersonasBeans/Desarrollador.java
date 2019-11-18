/**
 * Esta clase almacena informacion necesaria de la persona Desarrollador
 * @author Arcos Raul, Ferreira Cariel, Lopez Isaias, Soto Ivan
 * @version 1.0
 */
package mx.unam.fi.PersonasBeans;

import java.util.ArrayList;
import java.util.List;

public class Desarrollador {
    private String id_desarrollador;
    private String nombre;
    private String apellido;
    private String password;
    private String email;
    private List<Actividad> actividades = new ArrayList<Actividad>();

    public String getId_desarrollador() {
        return id_desarrollador;
    }

    public void setId_desarrollador(String id_desarrollador) {
        this.id_desarrollador = id_desarrollador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }
    /**
     * Este metodo imprime toda la informacion de la persona Desarrollador
     * @return String resultado de los datos
     */
    @Override
    public String toString() {
        return "Desarrollador{" + "id_desarrollador=" + id_desarrollador + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", actividades=" + actividades + '}' + "\n";
    }
}
