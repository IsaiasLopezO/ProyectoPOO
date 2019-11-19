/**
 * Esta interface implementa la interfaz LiderProyectoDAO con los métodos
 * de las operaciones disponibles que posee cada desarrollador.
 * Se utiliza DML (Data Manipulation Language) para interactuar con la
 * base de datos. Además, utilizamos el concepto de Transacción, que nos
 * permite realizar varias operaciones durante la ejececución del mismo
 * programa y no crear muchas nuevas conexiones entre estas acciones.
 * @author Arcos Raul, Ferreira Cariel, Lopez Isaias, Soto Ivan
 */
package mx.unam.fi.Interfaces;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import mx.unam.fi.PersonasBeans.*;
import mx.unam.fi.Excepciones.*;

public class JDBCLiderProyectoDAO implements LiderProyectoDAO{

    //Con esta variable se extrae de esta clase el manejo de la conexión
    private Connection conexionTransaccional;

    //**SENTENCIAS SQL PARA DEFINIR LAS OPERACIONES SOBRE LA BASE DE DATOS**

    //Selecciona todos los atributos de la tabla desarrollador y actividad, que se conectan con sus llaves principales
    private static final String SQL_SELECT = "SELECT * FROM desarrollador, actividad WHERE desarrollador.id_desarrollador = actividad.id_actividad";
    //Inserta atributos requeridos en la tabla actividad
    private static final String SQL_INSERT = "INSERT INTO actividad(nombre1, status1, nombre2, status2, nombre3, status3, nombre4, status4) VALUES(?,?,?,?,?,?,?,?)";
    //Actualiza todo el contenido de alguna actividad. Esta variable también será utilizada
    //para el método de eliminar, pues no queremos eliminar la plantilla Actividad, si no
    //que vaciaremos lo que existe en el objeto Actividad
    private static final String SQL_UPDATE = "UPDATE actividad SET nombre1=?, status1=?, nombre2=?, status2=?, nombre3=?, status3=?, nombre4=?, status4=? WHERE id_actividad = ?";
    private static final String SQL_SELECDES = "SELECT * FROM desarrollador WHERE id_desarrollador = ?";
    private static final String SQL_SELECTACT = "SELECT * FROM actividad WHERE id_actividad = ?";

    public JDBCLiderProyectoDAO(){}
    /**
     * Constructor que recibe el objeto de tipo conexion externo a esta clase.
     * Esta conexion no se cierra al termino de cada metodo, sino que se mantiene
     * abierta y la transaccion se hace fuera de esta clase. La clase externa es
     * la que decide cuando hacer commit o rollback de la transaccion
     * @param conexionTransaccional
     */
    public JDBCLiderProyectoDAO(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }
    /**
     * Este metodo permite visualizar todos los datos de todos los participantes en el proyecto.
     * @return List es una lista de todos los participantes en el proyecto
     * @throws VerDesarrolladoresEx
     */
    @Override
    public List<Desarrollador> verDesarrolladores() throws VerDesarrolladoresEx{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Desarrollador desarrollador = null;
        Actividad actividad = null;

        List<Desarrollador> desarrolladores = new ArrayList<Desarrollador>();
        List<Actividad> actividades = new ArrayList<Actividad>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            System.out.println("EJECUTANDO QUERY:" + SQL_SELECT);
            stmt = conn.prepareStatement(SQL_SELECT);

            rs = stmt.executeQuery();
            //Recorrido de cada uno de los registros
            while(rs.next()){
                //Recuperación de cada uno de los valores de la sentencia
                String id_desarrollador = rs.getString("id_desarrollador");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");

                int id_actividad = rs.getInt("id_actividad");
                String nombre1 = rs.getString("nombre1");
                String status1 = rs.getString("status1");
                String nombre2 = rs.getString("nombre2");
                String status2 = rs.getString("status2");
                String nombre3 = rs.getString("nombre3");
                String status3 = rs.getString("status3");
                String nombre4 = rs.getString("nombre4");
                String status4 = rs.getString("status4");

                actividad = new Actividad();
                actividad.setId_actividad(id_actividad);
                actividad.setNombre1(nombre1);
                actividad.setStatus1(status1);
                actividad.setNombre2(nombre2);
                actividad.setStatus2(status2);
                actividad.setNombre3(nombre3);
                actividad.setStatus3(status3);
                actividad.setNombre4(nombre4);
                actividad.setStatus4(status4);

                actividades.add(actividad);

                desarrollador = new Desarrollador();
                desarrollador.setId_desarrollador(id_desarrollador);
                desarrollador.setNombre(nombre);
                desarrollador.setApellido(apellido);
                desarrollador.setEmail(email);
                desarrollador.setActividades(actividades);

                //Se agrega un desarrollador a la lista desarrolladores
                desarrolladores.add(desarrollador);
            }
        }catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            if(this.conexionTransaccional == null){
                Conexion.close(conn);
            }
        }
        //Regresa la lista de desarrolladores en la tabla
        return desarrolladores;
    }
    /**
     * Este metodo permite visualizar un desarrollador en particular
     * @param id_desarrollador
     * @return String[][] de datos del desarrollador: Nombre y status
     * @throws VerActividadesEx
     */
    @Override
    public Desarrollador verDesarrollador(String id_desarrollador) throws VerActividadesEx{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Desarrollador desarrollador = null;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            System.out.println("EJECUTANDO QUERY:" + SQL_SELECDES);
            stmt = conn.prepareStatement(SQL_SELECDES);
            stmt.setString(1, id_desarrollador);
            rs = stmt.executeQuery();
            if(rs.next()){
                desarrollador = new Desarrollador();
                desarrollador.setId_desarrollador(id_desarrollador);

                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");

                desarrollador.setNombre(nombre);
                desarrollador.setApellido(apellido);
                desarrollador.setEmail(email);
            }
        }catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            if(this.conexionTransaccional == null){
                Conexion.close(conn);
            }
        }
        return desarrollador;
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

            JOptionPane.showMessageDialog(null,"Actividad modificada correctamente");
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
    /**
     * Este metodo permite crear una actividad en el proyecto.
     * @param actividad
     * @return int resultado de las actividades insertadas
     * @throws CrearActEx
     */
    @Override
    public int crearAct(Actividad actividad) throws CrearActEx{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            System.out.println("EJECUTANDO QUERY: " + SQL_INSERT);
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, actividad.getNombre1());
            stmt.setString(2, actividad.getStatus1());
            stmt.setString(3, actividad.getNombre2());
            stmt.setString(4, actividad.getStatus2());
            stmt.setString(5, actividad.getNombre3());
            stmt.setString(6, actividad.getStatus3());
            stmt.setString(7, actividad.getNombre4());
            stmt.setString(8, actividad.getStatus4());

            rows = stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Actividad creada correctamente");

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
    /**
     * Este metodo permite visualizar todos los datos de todas las actividades del desarrollador
     * @param id_desarrollador
     * @return Actividad
     * @throws VerActividadesEx
     */
    @Override
    public Actividad verAct(String id_desarrollador) throws VerActividadesEx{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Actividad actividad = null;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            System.out.println("EJECUTANDO QUERY:" + SQL_SELECTACT);
            stmt = conn.prepareStatement(SQL_SELECTACT);
            stmt.setString(1, id_desarrollador);
            rs = stmt.executeQuery();
            if(rs.next()){
                int id_actividad = rs.getInt("id_actividad");
                actividad = new Actividad();
                actividad.setId_actividad(id_actividad);

                String nombre1 = rs.getString("nombre1");
                String status1 = rs.getString("status1");
                String nombre2 = rs.getString("nombre2");
                String status2 = rs.getString("status2");
                String nombre3 = rs.getString("nombre3");
                String status3 = rs.getString("status3");
                String nombre4 = rs.getString("nombre4");
                String status4 = rs.getString("status4");

                actividad.setNombre1(nombre1);
                actividad.setStatus1(status1);
                actividad.setNombre2(nombre2);
                actividad.setStatus2(status2);
                actividad.setNombre3(nombre3);
                actividad.setStatus3(status3);
                actividad.setNombre4(nombre4);
                actividad.setStatus4(status4);
            }
        }catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            if(this.conexionTransaccional == null){
                Conexion.close(conn);
            }
        }
        return actividad;
    }
}
