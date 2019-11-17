/**
 * Esta clase es la principal de todo el proyecto.
 * @author Arcos Raul, Ferreira Cariel, Lopez Isaias, Soto Ivan
 * @version 1.0
 */
package mx.unam.fi.Proyecto;

import mx.unam.fi.PersonasBeans.*;
import mx.unam.fi.Interfaces.*;
import java.sql.*;

public class MainClass {
    public static void main(String[] args) {
        Connection conexion = null;
        try {
            //La conexión se maneja desde fuera de la clase JDBCLiderProyectoDAO
            conexion = Conexion.getConnection();
            //Se revisa si la conexión está en autocommit
            if(conexion.getAutoCommit()){
                //Necesitamos que la conexión no haga autocommit ya que lo haremos manualmente
                conexion.setAutoCommit(false);
            }

            JDBCLiderProyectoDAO liderJDBC = new JDBCLiderProyectoDAO(conexion);
            Desarrollador desarrolladores = new Desarrollador();
            Actividad actividad = new Actividad();

            /*ESTO SE PUEDE ELIMINAR, SON EJEMPLOS PARA QUE VEAN COMO FUNCIONA TODO
            //PERO NECESITAN TENER LAS MISMAS CONFIGURACIONES QUE YO TENGO, REVISEN EL DOC

            **VALIDACION DE USUARIO
            liderJDBC.validar(1, "whyadie");


            **VER DESARROLLADORES
            System.out.println(liderJDBC.verDesarrolladores());


            **MODIFICAR ACTIVIDAD (PENDIENTE, PROGRESO, TERMINADA, ENTREGADA)
            -Primero se modifican todos los atributos que se desean sobre las ACTIVIDADES

            actividad.setId_actividad(1);
            actividad.setNombre1("Proyecto de muestra");
            actividad.setStatus1("PENDIENTE");

            actividad.setNombre2("Proyecto de muestra");
            actividad.setStatus2("PENDIENTE");

            actividad.setNombre3("Proyecto de muestra");
            actividad.setStatus3("PENDIENTE");

            actividad.setNombre4("Proyecto de muestra");
            actividad.setStatus4("PENDIENTE");

            liderJDBC.modificarAct(actividad);

            **ELIMINAR CONTENIDO DE ACTIVIDAD (TODOS LOS ATRIBUTOS A NULL)
            actividad.setId_actividad(1);

            liderJDBC.modificarAct(actividad);

            **CREAR ACTIVIDAD
            actividad.setId_actividad(6);
            actividad.setNombre3("Proyecto de muestra");
            actividad.setStatus3("PENDIENTE");
            liderJDBC.crearAct(actividad);

            */
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
