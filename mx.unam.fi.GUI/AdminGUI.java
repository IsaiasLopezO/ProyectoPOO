/**
 * Esta clase se encarga de generar la ventana del administrador con ayuda de una GUI
 * @author Arcos Raul, Ferreira Cariel, Lopez Isaias, Soto Ivan
 */
package mx.unam.fi.GUI;

import mx.unam.fi.PersonasBeans.*;
import mx.unam.fi.Interfaces.*;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class AdminGUI{
    static int numActividades = 0;
    static JScrollPane scrollVentana = new JScrollPane();
    static JScrollPane scrollPanel = new JScrollPane();
    public static JFrame frame = new JFrame("ADMINISTRADOR");
    JPanel actions = new JPanel();

    Connection conexion = null;
    JDBCLiderProyectoDAO liderJDBC = new JDBCLiderProyectoDAO(conexion);
    Actividad actividad = new Actividad();
    Desarrollador desarrollador = new Desarrollador();

    public int contador=0;
    boolean d1=true,d2=true,d3=true,d4=true;//esto se debería modificar en función de las tareas que tiene asignadas, true es que si está asignada, false lo contrario
    public boolean desarrolladores[]={d1,d2,d3,d4};//son las tareas que puede tener el desarrollador
    /**
    * El constructor recibe todas las partes de la interfaz
    */
    public AdminGUI() {
        try {
            conexion = Conexion.getConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }

            scrollPanel.setLayout(null);
            scrollPanel.setBounds(260, 0, 820, 720);
            scrollPanel.setBackground(Color.DARK_GRAY);
            //infoDes.setBounds(290,40+contador*100,90,400);
            mostrarDesarrolladores("1");

            actions.setLayout(null);
            actions.setBounds(0,0,260,720);
            actions.setBackground(Color.BLUE);

            frame.add(scrollPanel);
            frame.add(modificarActividad());
            frame.add(eliminarActividad());
            frame.add(modificarMiEstatus());

            frame.setResizable(false);
            frame.setLayout(null);
            frame.add(actions);
            frame.add(scrollVentana);
            frame.setSize(1080,720);
            frame.setVisible(true);

            conexion.commit();
            System.out.println("Se ha hecho COMMIT de la transacción");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        }catch (SQLException ex) {
            ex.printStackTrace(System.out);
            //Hacer rollback sucede cuando, en caso de que falle,
            //no se guardan las modificaciones. Las operaciones no se ejecutan
            System.out.println("Entramos al ROLLBACK");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }
    /**
     * El metodo cambia a la ventana de desarrollador para cambiar los estados
     * @return boton para modificar estado
     */
    private JButton modificarMiEstatus(){
        JButton modificarEstado = new JButton("Modificar mi estado"); //Work Button
        modificarEstado.setBounds(50,180,150,50);
        modificarEstado.addActionListener((ActionEvent ae) -> {
            DesarrolladorGUI desarrolladorGUI = new DesarrolladorGUI("1");
            frame.setVisible(false);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        });
        return modificarEstado;
    }
    /**
     * El metodo cambia el contenido de la actividad y la establece a PENDIENTE
     * pues es como si apenas se hubiese asignado
     * @return boton para modificar actividad
     */
    private JButton modificarActividad(){
        JButton actualizarAct = new JButton("Actualizar actividad"); //Work Button
        actualizarAct.setBounds(50,250,150,50);
        actualizarAct.addActionListener((ActionEvent ae) -> {
            try {
                conexion = Conexion.getConnection();
                if(conexion.getAutoCommit()){
                    conexion.setAutoCommit(false);
                }
                int id = Integer.parseInt(JOptionPane.showInputDialog("ID de desarrollador: "));
                int idAct = Integer.parseInt(JOptionPane.showInputDialog("Numero de actividad: "));
                String nombre = JOptionPane.showInputDialog("Nueva actividad: ");
                actividad.setId_actividad(id);
                if(idAct == 1){
                    actividad.setNombre1(nombre);
                    actividad.setStatus1("PENDIENTE");
                }
                if(idAct == 2){
                    actividad.setNombre2(nombre);
                    actividad.setStatus2("PENDIENTE");

                }
                if(idAct == 3){
                    actividad.setNombre3(nombre);
                    actividad.setStatus3("PENDIENTE");

                }
                if(idAct == 4){
                    actividad.setNombre4(nombre);
                    actividad.setStatus4("PENDIENTE");

                }
                liderJDBC.modificarAct(actividad);
            }catch (SQLException ex) {
                ex.printStackTrace(System.out);
                JOptionPane.showMessageDialog(null,"Error: No se ha podido actualizar");
            }
        });
        return actualizarAct;
    }
    /**
     * Establece a null las actividades
     * @return boton de eliminar actividad
     */
    private JButton eliminarActividad(){
        JButton eliminarAct = new JButton("Eliminar actividad"); //Work Button
        eliminarAct.setBounds(50,320,150,50);
        eliminarAct.addActionListener((ActionEvent ae) -> {
            try {
                conexion = Conexion.getConnection();
                if(conexion.getAutoCommit()){
                    conexion.setAutoCommit(false);
                }
                int id = Integer.parseInt(JOptionPane.showInputDialog("ID de desarrollador: "));
                int idAct = Integer.parseInt(JOptionPane.showInputDialog("Numero de actividad a eliminar: "));

                actividad.setId_actividad(id);
                if(idAct == 1){
                    actividad.setNombre1(null);
                    actividad.setStatus1(null);

                }
                if(idAct == 2){
                    actividad.setNombre2(null);
                    actividad.setStatus2(null);

                }
                if(idAct == 3){
                    actividad.setNombre3(null);
                    actividad.setStatus3(null);

                }
                if(idAct == 4){
                    actividad.setNombre4(null);
                    actividad.setStatus4(null);
                }
                liderJDBC.modificarAct(actividad);
            }catch (SQLException ex) {
                ex.printStackTrace(System.out);
                JOptionPane.showMessageDialog(null,"Error: No se ha podido eliminar");
            }
        });
        return eliminarAct;
    }
    /**
     * Muestra los desarrolladores
     * @param id_desarrollador
     */
    private void mostrarDesarrolladores(String id_desarrollador){

        for(Boolean d:desarrolladores){
            if(d){//Aquí se debería poner algo que reciba los datos de la tarea y los agregue al panel
                JPanel infoDes=new JPanel();//Crea el panel de la tarea
                infoDes.setLayout(new FlowLayout(FlowLayout.RIGHT));
                //X(der, izq),Y(arr,abaj),WIDTH(anchura), HEIGHT(altura)
                infoDes.setBounds(290+contador*200,50,165,500);
                infoDes.setBackground(Color.white);
                insertLabels(contador, id_desarrollador);
                frame.add(infoDes);
                contador+=1;
            }
        }
    }
    /**
     * Inserta las etiquetas de los desarrolladores
     * @param contador
     * @param id_desarrollador
     */
    private void insertLabels(int contador, String id_desarrollador){
        JLabel desarrolladorEt = new JLabel();
        desarrolladorEt.setLayout(null);

        desarrolladorEt.setForeground(Color.BLUE);
        desarrolladorEt.setBounds(300+contador*200,0,190,200);
        frame.add(desarrolladorEt);

        JLabel contenidoDesarrollador = new JLabel();
        contenidoDesarrollador.setLayout(null);
        contenidoDesarrollador.setBounds(305+contador*200,20,190,200);

        try {
            conexion = Conexion.getConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }

            desarrollador = liderJDBC.verDesarrollador(id_desarrollador);
            actividad = liderJDBC.verAct(id_desarrollador);

            if(contador == 0){
                desarrollador = liderJDBC.verDesarrollador("1");
                String nombreD1 = desarrollador.getNombre();
                String id1 = desarrollador.getId_desarrollador();
                actividad = liderJDBC.verAct("1");
                desarrolladorEt.setText("DESARROLLADOR " + id1 + ":");
                contenidoDesarrollador.setText("<html><p><p><p>NOMBRE: " + nombreD1 + "<p>ACTIVIDAD: " + actividad.getNombre1() + "<p>Estatus: " + actividad.getStatus1());

            }
            if(contador == 1){
                desarrollador = liderJDBC.verDesarrollador("2");
                String nombreD2 = desarrollador.getNombre();
                String id2 = desarrollador.getId_desarrollador();
                actividad = liderJDBC.verAct("2");
                desarrolladorEt.setText("DESARROLLADOR " + id2 + ":");
                contenidoDesarrollador.setText("<html><p><p><p>NOMBRE: " + nombreD2 + "<p>ACTIVIDAD: " + actividad.getNombre1() + "<p>Estatus: " + actividad.getStatus1());
            }
            if(contador == 2){
                desarrollador = liderJDBC.verDesarrollador("3");
                String nombreD3 = desarrollador.getNombre();
                String id3 = desarrollador.getId_desarrollador();
                actividad = liderJDBC.verAct("3");
                desarrolladorEt.setText("DESARROLLADOR " + id3 + ":");
                contenidoDesarrollador.setText("<html><p><p><p>NOMBRE: " + nombreD3 + "<p>ACTIVIDAD: " + actividad.getNombre1() + "<p>Estatus: " + actividad.getStatus1());
            }
            if(contador == 3){
                desarrollador = liderJDBC.verDesarrollador("4");
                String nombreD4 = desarrollador.getNombre();
                String id4 = desarrollador.getId_desarrollador();
                actividad = liderJDBC.verAct("4");
                desarrolladorEt.setText("DESARROLLADOR " + id4 + ":");
                contenidoDesarrollador.setText("<html><p><p><p>NOMBRE: " + nombreD4 + "<p>ACTIVIDAD: " + actividad.getNombre1() + "<p>Estatus: " + actividad.getStatus1());
            }
            frame.add(contenidoDesarrollador);

        }catch (SQLException ex) {
            ex.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null,"Error: La operacion insertar label no ha sido ejecutada.(ROLLBACK)");
        }
    }
}
