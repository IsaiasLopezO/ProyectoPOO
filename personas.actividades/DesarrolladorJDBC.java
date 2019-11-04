package personas.actividades;

import personas.domain.Desarrollador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DesarrolladorJDBC {
    //Esta variable sirve para aplicar transacción
    private Connection conexionTransaccional;

    private static final String SQL_SELECT = "SELECT id_desarrollador, nombre, password, password FROM desarrollador";
    //El id es una llave primaria que se incrementa automáticamente, por tanto no se incluye
    private static final String SQL_INSERT = "INSERT INTO desarrollador(nombre, password) VALUES(?,?)";
    private static final String SQL_UPDATE = "UPDATE desarrollador SET nombre=?, password=? WHERE id_desarrollador = ?";
    private static final String SQL_DELETE = "DELETE FROM desarrollador WHERE id_desarrollador=?";

    //Para recibir la conexión transaccional
    public DesarrolladorJDBC(){
    }

    /*Recibe el objeto de tipo conexión externo a esta clase. Esta conexión
    //se mantiene abierta y la transacción se hace fuera de esta clase. La
    //clase externa es la que decide cuándo hacer commit o rollback de la transacción*/
    public DesarrolladorJDBC(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }

    //Definición del método para modificar registro
    public List<Desarrollador> select() throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Desarrollador desarrollador = null;

        List<Desarrollador> desarrolladores = new ArrayList<Desarrollador>();

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
                int id_desarrollador = rs.getInt("id_desarrollador");
                String nombre = rs.getString("nombre");
                String password = rs.getString("password");

                //Construcción de objeto tipo Desarrollador (división de responsabilidades)
                desarrollador = new Desarrollador();
                desarrollador.setId_Desarrollador(id_desarrollador);
                desarrollador.setNombre(nombre);
                desarrollador.setPassword(password);
                //Agrega cada objeto a la lista
                desarrolladores.add(desarrollador);
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
        return desarrolladores;
    }

    //El entero que regresa indica cuántps regsitros han sido afectados
    public int insert(Desarrollador desarrollador) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //Rows sirve para saber cuántos registros han sido afectados
        int rows = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            //Primer parámetro es índice, el segundo es el valor que queremos proporcionar
            stmt.setString(1, desarrollador.getNombre());
            stmt.setString(2, desarrollador.getPassword());

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

    public int update(Desarrollador desarrollador) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            //Mensaje para comprobar qué operación se está ejecutando
            System.out.println("EJECUTANDO QUERY: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, desarrollador.getNombre());
            stmt.setString(2, desarrollador.getPassword());
            //Un tercer parámetro id
            stmt.setInt(3, desarrollador.getId_Desarrollador());

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

    public int delete(Desarrollador desarrollador) throws SQLException{
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
            stmt.setInt(1, desarrollador.getId_Desarrollador());
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
