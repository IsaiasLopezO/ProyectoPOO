/**
 * Esta clase almacena informacion necesaria de la actividad
 * @author Arcos Raul, Ferreira Cariel, Lopez Isaias, Soto Ivan
 * @version 1.0
 */
package mx.unam.fi.PersonasBeans;

public class Actividad {
    private int id_actividad;
    private String nombre1, status1;
    private String nombre2, status2;
    private String nombre3, status3;
    private String nombre4, status4;

    public int getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(int id_actividad) {
        this.id_actividad = id_actividad;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getStatus2() {
        return status2;
    }

    public void setStatus2(String status2) {
        this.status2 = status2;
    }

    public String getNombre3() {
        return nombre3;
    }

    public void setNombre3(String nombre3) {
        this.nombre3 = nombre3;
    }

    public String getStatus3() {
        return status3;
    }

    public void setStatus3(String status3) {
        this.status3 = status3;
    }

    public String getNombre4() {
        return nombre4;
    }

    public void setNombre4(String nombre4) {
        this.nombre4 = nombre4;
    }

    public String getStatus4() {
        return status4;
    }

    public void setStatus4(String status4) {
        this.status4 = status4;
    }
    /**
     * Este metodo imprime toda la informacion de la actividad
     * @return String resultado de los datos
     */
    @Override
    public String toString() {
        return "Actividad{" + "id_actividad=" + id_actividad + ", nombre1=" + nombre1 + ", status1=" + status1 + ", nombre2=" + nombre2 + ", status2=" + status2 + ", nombre3=" + nombre3 + ", status3=" + status3 + ", nombre4=" + nombre4 + ", status4=" + status4 + '}' + "\n";
    }
}
