package personas.actividades;

import java.sql.*;

//ESTA ES LA CLASE DE SEPARACIÓN DE RESPONSABILIDADES
public class Conexion {
    //Cadena de conexión a MYSQL. Se puede omitir el puerto porque es el puerto por default
    private static final String JDBC_URL = "jdbc:mysql://localhost/test?useSSL=false&serverTimezone=UTC";
    //Nombre de usuario para conectarnos a la base de datos
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "admin";

    //Puede arrojar una excepción SQL
    public static Connection getConnection() throws SQLException{
        //Se regresa una conexión utilizando DriverManager
        //Con esto se crea la conexión a la base de datos
        return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASS);
    }

    //Se separan las responsabilidades en cada una de nuestras clases
    public static void close(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement stmt){
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
