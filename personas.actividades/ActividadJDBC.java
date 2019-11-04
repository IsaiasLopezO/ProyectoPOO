package personas.actividades;

import personas.domain.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Esta clase se encarga de realizar las OPERACIONES sobre la BD de Actividad
public class ActividadJDBC {

    //Esta variable sirve para aplicar transacción
    private Connection conexionTransaccional;

    private static final String SQL_SELECT = "SELECT id_actividad, grade, status FROM actividad";
    //El id es una llave primaria que se incrementa automáticamente, por tanto no se incluye
    private static final String SQL_INSERT = "INSERT INTO actividad(grade, status) VALUES(?,?)";
    private static final String SQL_UPDATE = "UPDATE actividad SET grade=?, status=? WHERE id_actividad = ?";
    private static final String SQL_DELETE = "DELETE FROM actividad WHERE id_actividad=?";

    //Para recibir la conexión transaccional
    public ActividadJDBC(){
    }

    /*Recibe el objeto de tipo conexión externo a esta clase. Esta conexión
    //se mantiene abierta y la transacción se hace fuera de esta clase. La
    //clase externa es la que decide cuándo hacer commit o rollback de la transacción*/
    public ActividadJDBC(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }

    //Definición del método para modificar registro
    public List<Actividad> select() throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Actividad actividad = null;

        List<Actividad> actividades = new ArrayList<Actividad>();

        try {
            /*Si el objeto de Conexión transaccional es diferente de nulo, entonces
            //usamos el objeto de Conexión Transaccional, de lo contrario se obtiene
            //una nueva conexión, en cuyo caso, la conexión se cierra aquí mismo*/
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            //Ejecución de query
            rs = stmt.executeQuery();

            //Recorrido de cada uno de los registros
            while(rs.next()){
                //Vars temporales. Recuperación de cada uno de los valores de la sentencia
                int id_actividad = rs.getInt("id_actividad");
                float grade = rs.getFloat("grade");
                String status = rs.getString("status");

                //Construcción de objeto tipo Actividad (división de responsabilidades)
                actividad = new Actividad();
                actividad.setId_actividad(id_actividad);
                actividad.setGrade(grade);
                actividad.setStatus(status);
                //Agrega cada objeto a la lista
                actividades.add(actividad);
            }
        /*Los CATCH se quitaron para que, en lugar de que
        //se procese la excepción dentro de estos métodos, se propague la excepción*/
        } finally{
            //Cierre de cada objeto
            Conexion.close(rs);
            Conexion.close(stmt);
            /*Preguntamos de donde obtuvimos la conexión.
            //Si el objeto transaccional es nulo, quiere decir que se creó una transacción en el método,
            //por tanto, se cierra aquí mismo (en el método), pero si no es nulo no se cierra y continúa operando*/
            if(this.conexionTransaccional == null){
                Conexion.close(conn);
            }

        }
        return actividades;
    }

    //El entero que regresa indica cuántps regsitros han sido afectados
    public int insert(Actividad actividad) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //Rows sirve para saber cuántos registros han sido afectados
        int rows = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            //Primer parámetro es índice, el segundo es el valor que queremos proporcionar
            stmt.setFloat(1, actividad.getGrade());
            stmt.setString(2, actividad.getStatus());

            //Mensaje para comprobar qué operación se está ejecutando
            System.out.println("EJECUTANDO QUERY: " + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } finally{
            //Cerradura de objetos a conexión de la base de datos
            Conexion.close(stmt);
            if(this.conexionTransaccional == null){
                Conexion.close(conn);
            }
        }
        return rows;
    }

    public int update(Actividad actividad) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            //Mensaje para comprobar qué operación se está ejecutando
            System.out.println("EJECUTANDO QUERY: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setFloat(1, actividad.getGrade());
            stmt.setString(2, actividad.getStatus());
            //Un tercer parámetro id
            stmt.setInt(3, actividad.getId_actividad());

            rows = stmt.executeUpdate();
            System.out.println("Registros actualizados: " + rows);
        } finally{
            Conexion.close(stmt);
            if(this.conexionTransaccional == null){
                Conexion.close(conn);
            }
        }
        return rows;
    }

    public int delete(Actividad actividad) throws SQLException{
        //Sólo debe reconocer el valor de la llave primaria
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows =0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            //Mensaje para comprobar qué operación se está ejecutando
            System.out.println("EJECUTANDO QUERY:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            //Para saber qué registro se eliminará basta con el id
            stmt.setInt(1, actividad.getId_actividad());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados: " + rows);

        } finally{
            Conexion.close(stmt);
            if(this.conexionTransaccional == null){
                Conexion.close(conn);
            }
        }

        return rows;
    }
}
