/**
 * Esta clase se encarga de generar la ventana del desarrollador con ayuda de una GUI
 * @author Arcos Raul, Ferreira Cariel, Lopez Isaias, Soto Ivan
 */
package mx.unam.fi.GUI;

import mx.unam.fi.PersonasBeans.*;
import mx.unam.fi.Interfaces.*;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class DesarrolladorGUI{
    public int contador=0;//cuenta el número de tareas para añadirlas a la pantalla
    boolean a1=true,a2=true,a3=true,a4=true;//esto se debería modificar en función de las tareas que tiene asignadas, true es que si está asignada, false lo contrario
    public boolean actividades[]={a1,a2,a3,a4};//son las tareas que puede tener el desarrollador


    public static JFrame frame = new JFrame("DESARROLLADOR");
    private final JPanel panel = new JPanel();
    Connection conexion = null;
    JDBCDesarrolladorDAO desarrolladorJDBC = new JDBCDesarrolladorDAO(conexion);
    Actividad actividad = new Actividad();

    public static void main(String[] args){//Esto es solo para probar que si funciona
        DesarrolladorGUI desarrolladorGUI =new DesarrolladorGUI("1");
    }

    public DesarrolladorGUI(String id_desarrollador){

        frame.setSize(1080,720);
        frame.setBackground(Color.DARK_GRAY);
        frame.setLayout(null);
        frame.setResizable(false);
        panel.setLayout(null);
        panel.setBounds(0,0,360,720);
        panel.setBackground(Color.BLUE);

        mostrarActividades(id_desarrollador);
        //insertLabels();

        frame.add(panel);
        frame.setVisible(true);

        //Cierra el programa
	frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	//Mata al programa una vez cerrado
	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void mostrarActividades(String id_desarrollador){
        for(Boolean a:actividades){//Crea un panel por tarea
            if(a){//Aquí se debería poner algo que reciba los datos de la tarea y los agregue al panel
                JPanel infoAct=new JPanel();//Crea el panel de la tarea
                infoAct.setLayout(new FlowLayout(FlowLayout.RIGHT));
                //X(der, izq),Y(arr,abaj),WIDTH(anchura), HEIGHT(altura)
                infoAct.setBounds(460,50+contador*150,520,100);
                infoAct.setBackground(Color.LIGHT_GRAY);
                infoAct.add(listasDesplegables(contador));
                insertLabels(contador, id_desarrollador);
                frame.add(infoAct);
                contador+=1;
            }
        }
    }

    private void insertLabels(int contador, String id_desarrollador){
        JLabel actividadEt = new JLabel();
        actividadEt.setLayout(null);
        actividadEt.setText("ACTIVIDAD " + contador + ":");
        actividadEt.setBounds(480,10+contador*150,520,100);
        frame.add(actividadEt);
        try {
            conexion = Conexion.getConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }

            JLabel contenidoAct = new JLabel();
            contenidoAct.setLayout(null);

            actividad = desarrolladorJDBC.verAct(id_desarrollador);

            if(contador == 0){
                contenidoAct.setText("Realizar: " + actividad.getNombre1());
                contenidoAct.setBounds(480,30+contador*150,520,100);
            }
            if(contador == 1){
                contenidoAct.setText("Realizar: " + actividad.getNombre2());
                contenidoAct.setBounds(480,30+contador*150,520,100);
            }
            if(contador == 2){
                contenidoAct.setText("Realizar: " + actividad.getNombre3());
                contenidoAct.setBounds(480,30+contador*150,520,100);
            }
            if(contador == 3){
                contenidoAct.setText("Realizar: " + actividad.getNombre4());
                contenidoAct.setBounds(480,30+contador*150,520,100);
            }

            frame.add(contenidoAct);

        }catch (SQLException ex) {
            ex.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null,"Error: La operacion insertar label no ha sido ejecutada.(ROLLBACK)");
        }
    }

    private JComboBox listasDesplegables(int contador){
        String [] estados = {"Pendiente","En Progreso","Terminado","Entregado"};
        JComboBox listaDesplegable1 = new JComboBox(estados);
        listaDesplegable1.setPreferredSize(new Dimension(100,50));
        listaDesplegable1.setBackground(Color.DARK_GRAY);

        listaDesplegable1.addActionListener((ActionEvent ae) -> {
            try {
                conexion = Conexion.getConnection();
                if(conexion.getAutoCommit()){
                    conexion.setAutoCommit(false);
                }

                String valorLista = (String)listaDesplegable1.getSelectedItem();
                if(contador == 0){
                    if(valorLista.equals("Pendiente")){
                        actividad.setStatus1("PENDIENTE");
                    }
                    if(valorLista.equals("En Progreso")){
                        actividad.setStatus1("EN PROGRESO");
                    }
                    if(valorLista.equals("Terminado")){
                        actividad.setStatus1("TERMINADO");
                    }
                    if(valorLista.equals("Entregado")){
                        actividad.setStatus1("ENTREGADO");
                    }
                }

                if(contador == 1){
                    if(valorLista.equals("Pendiente")){
                        actividad.setStatus2("PENDIENTE");
                    }
                    if(valorLista.equals("En Progreso")){
                        actividad.setStatus2("EN PROGRESO");
                    }
                    if(valorLista.equals("Terminado")){
                        actividad.setStatus2("TERMINADO");
                    }
                    if(valorLista.equals("Entregado")){
                        actividad.setStatus2("ENTREGADO");
                    }
                }

                if(contador == 2){
                    if(valorLista.equals("Pendiente")){
                        actividad.setStatus3("PENDIENTE");
                    }
                    if(valorLista.equals("En Progreso")){
                        actividad.setStatus3("EN PROGRESO");
                    }
                    if(valorLista.equals("Terminado")){
                        actividad.setStatus3("TERMINADO");
                    }
                    if(valorLista.equals("Entregado")){
                        actividad.setStatus3("ENTREGADO");
                    }
                }

                if(contador == 3){
                    if(valorLista.equals("Pendiente")){
                        actividad.setStatus4("PENDIENTE");
                    }
                    if(valorLista.equals("En Progreso")){
                        actividad.setStatus4("EN PROGRESO");
                    }
                    if(valorLista.equals("Terminado")){
                        actividad.setStatus4("TERMINADO");
                    }
                    if(valorLista.equals("Entregado")){
                        actividad.setStatus4("ENTREGADO");
                    }
                }
                desarrolladorJDBC.modificarAct(actividad);
            }catch (SQLException ex) {
            ex.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null,"Error: La operacion lista desplegable no ha sido ejecutada.(ROLLBACK)");
            }
        });

        return listaDesplegable1;
    }

    private void modificarEstado(){

    }
}
