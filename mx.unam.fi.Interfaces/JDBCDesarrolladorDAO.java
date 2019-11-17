/**
 * Esta interface implementa la interfaz DesarrolladorDAO con los métodos
 * de las operaciones disponibles que posee cada desarrollador.
 * Se utiliza DML (Data Manipulation Language) para interactuar con la
 * base de datos. Además, utilizamos el concepto de Transacción, que nos
 * permite realizar varias operaciones durante la ejececución del mismo
 * programa y no crear muchas nuevas conexiones entre estas acciones.
 * @author Arcos Raul, Ferreira Cariel, Lopez Isaias, Soto Ivan
 */
package mx.unam.fi.Interfaces;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import mx.unam.fi.Excepciones.*;
import mx.unam.fi.PersonasBeans.*;

public class JDBCDesarrolladorDAO implements DesarrolladorDAO{
    //Con esta variable se extrae de esta clase el manejo de la conexión
    private Connection conexionTransaccional;

    //**SENTENCIAS SQL PARA DEFINIR LAS OPERACIONES SOBRE LA BASE DE DATOS**

    //Busca en la tabla desarrollador si existe el id_desarrollador para permitir el login o no
    private static final String SQL_SEARCH = "SELECT * FROM desarrollador WHERE id_desarrollador = ?";
    //Actualiza todo el contenido de alguna actividad. Esta variable también será utilizada
    //para el método de eliminar, pues no queremos eliminar la plantilla Actividad, si no
    //que vaciaremos lo que existe en el objeto Actividad
    private static final String SQL_UPDATE = "UPDATE actividad SET nombre1=?, status1=?, nombre2=?, status2=?, nombre3=?, status3=?, nombre4=?, status4=? WHERE id_actividad = ?";

    public JDBCDesarrolladorDAO(){}
    /**
     * Constructor que recibe el objeto de tipo conexion externo a esta clase.
     * Esta conexion no se cierra al termino de cada metodo, sino que se mantiene
     * abierta y la transaccion se hace fuera de esta clase. La clase externa es
     * la que decide cuando hacer commit o rollback de la transaccion
     * @param conexionTransaccional
     */
    public JDBCDesarrolladorDAO(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }
    /**
     * Este metodo valida que los datos de inicio de sesion coincidan con los de cada desarrollador.
     * @param id_desarrollador
     * @param email
     * @return List resultado de los datos del usuario que ha iniciado sesion
     * @throws AccesoLoginEx
     */
    @Override
    public List<Desarrollador> validar(int id_desarrollador, String email) throws AccesoLoginEx{
        //Sentencias SQL para conectarnos
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Desarrollador desarrollador = null;

        List<Desarrollador> desarrolladorDatos = new ArrayList<Desarrollador>();

         try {
            //Lo que recibe el objeto conn es lo siguiente:
            //Si el objeto de conexion transaccional es diferente de nulo, entonces usamos el objeto de Conexion Transaccional,
            //de lo contrario se obtiene una nueva conexion, en cuyo caso la conexion se cierra aqui mismo
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            System.out.println("EJECUTANDO QUERY:" + SQL_SEARCH);
            stmt = conn.prepareStatement(SQL_SEARCH);
            stmt.setInt(1, id_desarrollador);
            rs = stmt.executeQuery();
            //Recorre sobre la tabla
            if(rs.next()){
                //Encuentra la coincidencia de los datos insertados en el login
                if(rs.getString("email").equals(email)){
                    desarrollador = new Desarrollador();
                    desarrollador.setId_desarrollador(id_desarrollador);
                    desarrollador.setNombre(rs.getString("nombre"));
                    desarrollador.setApellido(rs.getString("apellido"));

                    desarrolladorDatos.add(desarrollador);
                    System.out.println("Bienvenido: " + desarrollador);
                //En caso contrario, no puede entrar
                }else{
                    System.out.println("DATOS INCORRECTOS");
                }
            }else{
                System.out.println("NO HAY DATOS ENCONTRADOS");
            }
        }catch (SQLException ex) {
            //Imprime el rastro de la excepcion
            ex.printStackTrace(System.out);
        } finally{
            //Cierre de cada objeto
            Conexion.close(rs);
            Conexion.close(stmt);
            //Preguntamos de donde obtuvimos la conexión:
            //Si el objeto transaccional es nulo, quiere decir que se creó una transacción en el método,
            //por tanto, se cierra aquí mismo (en el método), pero si no es nulo no se cierra
            if(this.conexionTransaccional == null){
                Conexion.close(conn);
            }
        }
        //Datos del desarrollador que ingresó
        return desarrolladorDatos;
    }
    /**
     * Este metodo permite modificar los datos de una actividad en el proyecto.
     * Tambien es utilizado para eliminar el contenido de alguna columna en la BD
     * @param actividad
     * @return int resultado de las modificaciones realizadas
     * @throws ModificarActEx
     */
    @Override
    public int modificarAct(Actividad actividad) throws ModificarActEx{
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            System.out.println("EJECUTANDO QUERY: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, actividad.getNombre1());
            stmt.setString(2, actividad.getStatus1());
            stmt.setString(3, actividad.getNombre2());
            stmt.setString(4, actividad.getStatus2());
            stmt.setString(5, actividad.getNombre3());
            stmt.setString(6, actividad.getStatus3());
            stmt.setString(7, actividad.getNombre4());
            stmt.setString(8, actividad.getStatus4());
            //Parámetro que nos indica elemento a modificar
            stmt.setInt(9, actividad.getId_actividad());

            rows = stmt.executeUpdate();

            System.out.println("Actividades modificadas: " + rows);
        }catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
            Conexion.close(stmt);
            if(this.conexionTransaccional == null){
                Conexion.close(conn);
            }
        }
        return rows;
    }
}
