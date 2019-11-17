/**
 * Esta clase lanza la excepcion pertinente cuando falla al
 * crear/insertar una actividad
 *  @author Arcos Raul, Ferreira Cariel, Lopez Isaias, Soto Ivan
 */
package mx.unam.fi.Excepciones;

import java.sql.*;

public class CrearActEx extends SQLException{
    String mensaje;

    public CrearActEx(String mensaje){
        this.mensaje = mensaje;
    }
}
