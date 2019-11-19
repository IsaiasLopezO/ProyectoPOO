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

public interface LiderProyectoDAO {
    /**
     * Este metodo permite visualizar todos los datos de todos los participantes
     * en el proyecto. En palabras mas tecnicas, es un SELECT hacia la base de datos.
     * @return List es una lista de todos los participantes en el proyecto
     * @throws VerDesarrolladoresEx
     */
    List<Desarrollador> verDesarrolladores() throws VerDesarrolladoresEx;
    /**
     * Este metodo permite modificar los datos de una actividad en el proyecto.En palabras mas tecnicas, es un UPDATE hacia alguna actividad de la base de datos.
     * @param actividad
     * @return int resultado de las modificaciones realizadas
     * @throws ModificarActEx
     */
    int modificarAct(Actividad actividad) throws ModificarActEx;
    /**
     * Este metodo permite crear una actividad en el proyecto.En palabras mas tecnicas, es un INSERT de una actividad en la base de datos.
     * @param actividad
     * @return int resultado de las actividades insertadas
     * @throws CrearActEx
     */
    int crearAct(Actividad actividad) throws CrearActEx;
    /**
     * Este metodo permite ver las actividades de cada desarrollador
     * @param id_desarrollador
     * @return Actividad a ver
     * @throws VerActividadesEx
     */
    public Actividad verAct(String id_desarrollador) throws VerActividadesEx;
    /**
     * Este metodo permite ver un desarrollador en particular
     * @param id_desarrollador
     * @return String[][] datos
     * @throws VerActividadesEx
     */
    public Desarrollador verDesarrollador(String id_desarrollador) throws VerActividadesEx;

}
