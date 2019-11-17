package personas.domain;
//EDICION PARA COMPROBAR CONEXION
public class Desarrollador {
    private int id_desarrollador;
    private String nombre;
    private String password;

    public int getId_Desarrollador() {
        return id_desarrollador;
    }

    public void setId_Desarrollador(int id_desarrollador) {
        this.id_desarrollador = id_desarrollador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Desarrollador{" + "id_desarrollador=" + id_desarrollador + ", nombre=" + nombre + ", password=" + password + '}';
    }
}
