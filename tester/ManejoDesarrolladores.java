package tester;

import personas.actividades.*;
import java.sql.*;
import personas.domain.Desarrollador;

public class ManejoDesarrolladores {
    public static void main(String[] args) {
        Connection conexion = null;//Se declara fuera del bloque trycatch para poder utilizar la var en try catch
        try {
            //La conexión se maneja desde fuera de la clase DesarrolladorJDBC
            conexion = Conexion.getConnection();//Regresa una conexión
            //Se revisa si la conexión está en autocommit
            //
            if(conexion.getAutoCommit()){
                //Necesitamos que la conexión no haga autocommit
                //Ya que lo haremos manuealmente
                conexion.setAutoCommit(false);
            }
            //Aquí se aplica la transacción (VARIAS SENTENCIAS SQL)
            DesarrolladorJDBC desarrolladorJDBC = new DesarrolladorJDBC(conexion);

            //Dentro de la misma transacción se haga el UPDATE
            Desarrollador cambioDesarrollador = new Desarrollador();

            cambioDesarrollador.setId_Desarrollador(2);
            cambioDesarrollador.setNombre("Jacqueline");

            desarrolladorJDBC.update(cambioDesarrollador);

            //Dentro de la misma transacción se haga el INSERT
            Desarrollador nuevaDesarrollador = new Desarrollador();
            nuevaDesarrollador.setNombre("Ender");
            nuevaDesarrollador.setPassword("741");
            desarrolladorJDBC.insert(nuevaDesarrollador);

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
