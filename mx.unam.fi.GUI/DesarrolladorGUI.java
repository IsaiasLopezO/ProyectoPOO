/**
 * Esta clase se encarga de generar la ventana del desarrollador con ayuda de una GUI
 * @author Arcos Raul, Ferreira Cariel, Lopez Isaias, Soto Ivan
 */
package mx.unam.fi.GUI;

import mx.unam.fi.PersonasBeans.*;
import mx.unam.fi.Interfaces.*;
import java.sql.*;
import mx.unam.fi.Excepciones.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class DesarrolladorGUI{
    public int contador=0;//cuenta el número de tareas para añadirlas a la pantalla
    boolean t1=true,t2=true,t3=true,t4=true;//esto se debería modificar en función de las tareas que tiene asignadas, true es que si está asignada, false lo contrario
    public boolean tareas[]={t1,t2,t3,t4};//son las tareas que puede tener el desarrollador


    public static JFrame frame = new JFrame("DESARROLLADOR");
    private final JPanel panel = new JPanel();


    public static void main(String[] args){//Esto es solo para probar que si funciona
        DesarrolladorGUI desarrolladorGUI =new DesarrolladorGUI("1");
    }

    public DesarrolladorGUI(String id_desarrollador){

        frame.setSize(1080,720);
        frame.setLayout(null);
        frame.setResizable(false);
        panel.setLayout(null);
        panel.setBounds(0,0,360,720);
        panel.setBackground(Color.BLUE);

        mostrarTareas();

        frame.add(panel);
        frame. setVisible(true);

        //Cierra el programa
	frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	//Mata al programa una vez cerrado
	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void mostrarTareas(){
        for(Boolean t:tareas){//Crea un panel por tarea
            if(t){//Aquí se debería poner algo que reciba los datos de la tarea y los agregue al panel
                JPanel infoTarea=new JPanel();//Crea el panel de la tarea
                infoTarea.setLayout(new FlowLayout(FlowLayout.RIGHT));
                infoTarea.setBounds(460,50+contador*150,520,100);
                infoTarea.setBackground(Color.gray);
                infoTarea.add(listasDesplegables());
                frame.add(infoTarea);
                contador+=1;
            }
        }
    }

    private void insertLabels(){

    }

    private JComboBox listasDesplegables(){
        String [] estados = {"Pendiente","En Progreso","Terminado","Entregado"};
        JComboBox listaDesplegable1 = new JComboBox(estados);
        listaDesplegable1.setPreferredSize(new Dimension(100,50));
        listaDesplegable1.setBackground(Color.DARK_GRAY);

        return listaDesplegable1;
    }    
}
