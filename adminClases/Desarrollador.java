import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;


public class Des extends JFrame {
    public int contador=0;//cuenta el número de tareas para añadirlas a la pantalla
    boolean t1=true,t2=true,t3=true,t4=true;//esto se debería modificar en función de las tareas que tiene asignadas, true es que si está asignada, false lo contrario
    public boolean tareas[]={t1,t2,t3,t4};//son las tareas que puede tener el desarrollador

    public Des() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080,720);
        setLayout(null);
        setTitle("nombre");//Es el nombre del desarrollador

        JPanel p=new JPanel();
        p.setLayout(null);
        p.setBounds(0,0,360,720);
        p.setBackground(Color.BLUE);
        add(p);

        for(Boolean t:tareas){//Crea un panel por tarea
            if(t){//Aquí se debería poner algo que reciba los datos de la tarea y los agregue al panel
                JPanel infoTarea=new JPanel();//Crea el panel de la tarea
                infoTarea.setLayout(new FlowLayout(FlowLayout.RIGHT));
                infoTarea.setBounds(460,50+contador*150,520,100);
                infoTarea.setBackground(Color.gray);
                JButton estado=new JButton("Estado");//esto se modificará según quiera el usuario, falta un método que le de esa acción al botón
                estado.setPreferredSize(new Dimension(100,50));
                estado.setBackground(Color.DARK_GRAY);
                infoTarea.add(estado);
                add(infoTarea);
                contador+=1;
            }
        }
        setVisible(true);
    }

    public static void main(String[] args){//Esto es solo para probar que si funciona
        Des des=new Des();
    }
}
