package personas.domain;

public class Actividad {
    public int id_actividad;
    public float grade;
    public String status;

    public int getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(int id_actividad) {
        this.id_actividad = id_actividad;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Actividad{" + "id_actividad=" + id_actividad + ", grade=" + grade + ", status=" + status + '}';
    }
}
