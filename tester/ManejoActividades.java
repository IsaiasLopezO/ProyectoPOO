package tester;

import personas.actividades.*;
import personas.domain.Actividad;
import java.sql.*;


public class ManejoActividades {
    public static void main(String[] args) {
        Connection conexion = null;//Se declara fuera del bloque trycatch para poder utilizar la var en try catch
        try {
            //La conexión se maneja desde fuera de la clase ActividadJDBC
            conexion = Conexion.getConnection();//Regresa una conexión
            //Se revisa si la conexión está en autocommit
            //
            if(conexion.getAutoCommit()){
                //Necesitamos que la conexión no haga autocommit
                //Ya que lo haremos manuealmente
                conexion.setAutoCommit(false);
            }
            //Aquí se aplica la transacción (VARIAS SENTENCIAS SQL)
            ActividadJDBC actividadJDBC = new ActividadJDBC(conexion);

            //Dentro de la misma transacción se haga el UPDATE
            Actividad cambioActividad = new Actividad();

            cambioActividad.setId_actividad(2);
            cambioActividad.setGrade(10);

            actividadJDBC.update(cambioActividad);

            //Dentro de la misma transacción se haga el INSERT
            Actividad nuevaActividad = new Actividad();
            nuevaActividad.setGrade(0);
            nuevaActividad.setStatus("Pendiente");
            actividadJDBC.insert(nuevaActividad);

            //Hasta este momento se guardan los cambios
            conexion.commit();
            System.out.println("Se ha hecho COMMIT de la transacción");

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            //Hacer rollback sucede cuando, en caso de que falle,
            //no se guardan las modificaciones. Las operaciones no se ejecutan
            System.out.println("Entramos al ROLLBACK");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }
}
