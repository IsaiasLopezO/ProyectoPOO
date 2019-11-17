/**
 * Esta clase lanza la excepcion pertinente cuando falla al buscar/validar el login
 *  @author Arcos Raul, Ferreira Cariel, Lopez Isaias, Soto Ivan
 */
package mx.unam.fi.Excepciones;

import java.sql.*;

public class AccesoLoginEx extends SQLException{
    String mensaje;

    public AccesoLoginEx(String mensaje) {
        this.mensaje = mensaje;
    }
}
