/**
 * Esta interface será implementada con los métodos de las operaciones
 * disponibles que posee cada desarrollador. En palabras más técnicas,
 * son las operaciones SQL que serán realizadas hacia la base de datos.
 * @author Arcos Raul, Ferreira Cariel, Lopez Isaias, Soto Ivan
 */
package mx.unam.fi.Interfaces;

import java.util.List;
import mx.unam.fi.PersonasBeans.*;
import mx.unam.fi.Excepciones.*;

public interface DesarrolladorDAO {
    /**
     * Este metodo valida que los datos de inicio de sesion
     * coincidan con los de cada desarrollador.
     * @param email
     * @param id_desarrollador
     * @return List resultado de de datos en caso correcto/incorrecto
     * @throws AccesoLoginEx
     */
    public List<Desarrollador> validar(int id_desarrollador, String email) throws AccesoLoginEx;
    /**
     * Este metodo permite modificar los datos de una actividad en el proyecto.En palabras mas tecnicas, es un UPDATE hacia alguna actividad de la base de datos.
     * @param actividad
     * @return int resultado de las modificaciones realizadas
     * @throws ModificarActEx
     */
    int modificarAct(Actividad actividad) throws ModificarActEx;
}
