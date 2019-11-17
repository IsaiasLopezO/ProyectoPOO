/**
 * Esta clase lanza la excepcion pertinente cuando
 * falla al eliminar una actividad
 *  @author Arcos Raul, Ferreira Cariel, Lopez Isaias, Soto Ivan
 */
package mx.unam.fi.Excepciones;

import java.sql.*;

public class ModificarActEx extends SQLException{
    String mensaje;

    public ModificarActEx(String mensaje) {
        this.mensaje = mensaje;
    }
}
