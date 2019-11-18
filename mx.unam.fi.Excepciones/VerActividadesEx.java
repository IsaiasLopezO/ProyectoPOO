/**
 * Esta clase lanza la excepcion pertinente cuando falla al
 * ver/seleccionar el estado de una actividad
 *  @author Arcos Raul, Ferreira Cariel, Lopez Isaias, Soto Ivan
 */
package mx.unam.fi.Excepciones;

import java.sql.*;

public class VerActividadesEx extends SQLException{
    String mensaje;

    public VerActividadesEx(String mensaje){
        this.mensaje = mensaje;
    }
}
