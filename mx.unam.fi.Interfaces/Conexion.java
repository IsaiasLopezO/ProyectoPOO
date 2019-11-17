/**
 * Esta clase se encarga de conectar con la base de datos.
 * Aquí separamos las responsabilidades para tener una alta cohesión.
 * @author Arcos Raul, Ferreira Cariel, Lopez Isaias, Soto Ivan
 */
package mx.unam.fi.Interfaces;

import java.sql.*;

public class Conexion {
    //Cadena de conexión a MySQL. Se puede omitir el puerto porque es el puerto por default
    private static final String JDBC_URL = "jdbc:mysql://localhost/test?useSSL=false&serverTimezone=UTC";
    //Nombre de usuario para conectarnos a la BD
    private static final String JDBC_USER = "root";
    //Contraseña de usuario para conectarnos a la BD
    private static final String JDBC_PASS = "admin";
    /**
     * Este metodo crea una conexion con la BD
     * @return Connection de la clase DriverManager
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASS);
    }
    //Se cierran los objetos que fueron trabajados por la base de datos
    /**
     * Este metodo cierra el statement JDBC rs de tipo ResultSet
     * @param rs
     */
    public static void close(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException ex) {
            //Mensaje de excepción
            ex.printStackTrace(System.out);
        }
    }
    /**
     * Este metodo cierra el statement JDBC stmt de tipo PreparedStatement
     * @param stmt
     */
    public static void close(PreparedStatement stmt){
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    /**
     * Este metodo termina la conexion a la BD
     * @param conn
     */
    public static void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
