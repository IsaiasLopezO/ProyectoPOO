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
import javax.swing.JOptionPane;
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
    private static final String SQL_UPDATE = "UPDATE actividad SET status1=?, status2=?, status3=?, status4=? WHERE id_actividad = ?";

    private static final String SQL_SELECT = "SELECT * FROM actividad WHERE id_actividad = ?";

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
     * @return Desarrollador resultado de los datos del usuario que ha iniciado sesion
     * @throws AccesoLoginEx
     */
    @Override
    public String validar(String id_desarrollador, String email) throws AccesoLoginEx{
        //Sentencias SQL para conectarnos
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

         try {
            //Lo que recibe el objeto conn es lo siguiente:
            //Si el objeto de conexion transaccional es diferente de nulo, entonces usamos el objeto de Conexion Transaccional,
            //de lo contrario se obtiene una nueva conexion, en cuyo caso la conexion se cierra aqui mismo
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            System.out.println("EJECUTANDO QUERY:" + SQL_SEARCH);
            stmt = conn.prepareStatement(SQL_SEARCH);
            stmt.setString(1, id_desarrollador);
            rs = stmt.executeQuery();
            //Recorre sobre la tabla
            if(rs.next()){
                //Encuentra la coincidencia de los datos insertados en el login
                if(rs.getString("email").equals(email)){
                    JOptionPane.showMessageDialog(null,"Acceso correcto");
                    return id_desarrollador;
                    //System.out.println("Bienvenido: " + desarrolladorDatos);
                //En caso contrario, no puede entrar
                }else{
                    JOptionPane.showMessageDialog(null,"Usuario/Contrasena incorrecto");
                    //System.out.println("DATOS INCORRECTOS");
                }
            }else{
                JOptionPane.showMessageDialog(null,"NO HAY DATOS ENCONTRADOS");
                //System.out.println("NO HAY DATOS ENCONTRADOS");
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
        return null;
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
            System.out.println("EJECUTANDO QUERY:" + SQL_SELECT);
            stmt = conn.prepareStatement(SQL_SELECT);
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

            stmt.setString(1, actividad.getStatus1());
            stmt.setString(2, actividad.getStatus2());
            stmt.setString(3, actividad.getStatus3());
            stmt.setString(4, actividad.getStatus4());
            //Parámetro que nos indica elemento a modificar
            stmt.setInt(5, actividad.getId_actividad());
            rows = stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,"Estado modificado correctamente");
            //System.out.println("Actividades modificadas: " + rows);
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
