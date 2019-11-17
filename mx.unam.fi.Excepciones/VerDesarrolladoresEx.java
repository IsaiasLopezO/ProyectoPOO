/**
 * Esta clase lanza la excepcion pertinente cuando falla al
 * ver/seleccionar el estado de una actividad
 *  @author Arcos Raul, Ferreira Cariel, Lopez Isaias, Soto Ivan
 */
package mx.unam.fi.Excepciones;

import java.sql.*;

public class VerDesarrolladoresEx extends SQLException{
    String mensaje;

    public VerDesarrolladoresEx(String mensaje){
        this.mensaje = mensaje;
    }
}
