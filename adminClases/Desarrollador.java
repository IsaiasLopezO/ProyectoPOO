import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;


public class Des extends JFrame {
    boolean t1=true,t2=false,t3=false,t4=false;//esto se debería modificar en función de las tareas que tiene asignadas
    public boolean tareas[]={t1,t2,t3,t4};//son las tareas que puede tener el desarrollador

    public Des() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080,720);
        setLayout(null);
        setTitle("nom: Tareas");

        JPanel p=new JPanel();
        p.setLayout(null);
        p.setBounds(0,0,360,720);
        p.setBackground(Color.BLUE);
        add(p);

        JPanel infoTarea=new JPanel();
        infoTarea.setLayout(null);
        infoTarea.setBounds(460,200,520,350);
        infoTarea.setBackground(Color.gray);
        JButton estado=new JButton("Estado");//esto se modificará según quiera el usuario
        estado.setBounds(920,325,100,50);
        estado.setBackground(Color.green);
        infoTarea.add(estado);//No sé porque no aparece el botón
        add(infoTarea);

        for(Boolean t:tareas){
            if(t){
                JButton numTarea=new JButton("tarea n");//n es el número de la tarea asignada
                numTarea.setBounds(460,150,150,50);//(460+i*150,150,150,50) donde i es el índice de una de las 4 tareas, en este caso empezando con 0
                add(numTarea);
            }
        }

        setVisible(true);
    }

    public static void main(String[] args){
        Des des=new Des();
    }
}
