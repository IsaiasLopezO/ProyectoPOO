import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Des extends JFrame {
    boolean t1=false,t2=false,t3=false,t4=false;//esto se debería modificar en función de las tareas que tiene asignadas
    public boolean tareas[]={t1,t2,t3,t4};//son las tareas que puede tener el desarrollador

    public Des() {
        //setLayout(new FlowLayout(FlowLayout.RIGHT));//estos son experimentos xd
        //setLayout(null);
        setLayout(new FlowLayout(FlowLayout.TRAILING));
        setTitle("nom: Tareas");
        JPanel p=new JPanel();
        p.setLayout(null);
        p.setBounds(0,0,360,720);
        p.setBackground(Color.BLUE);
        add(p);
        setSize(1080,720);
        setVisible(true);
        for(Boolean t:tareas){
            NuevaTarea(t);
        }
    }
    public void NuevaTarea(Boolean T){
        if(!T){
            setT();
        }
    }
    public void setT(){//añade la tarea al frame, pero no funciona como debería :T
        JPanel t=new JPanel();
        t.setLayout(new FlowLayout(FlowLayout.TRAILING));
        t.setPreferredSize(new Dimension(650,100));
        t.setBackground(Color.RED);
        Button b=new Button("ejemplo");
        t.add(b);
        addImpl(t,b,WIDTH);
        //validate();
    }
    public static void main(String[] args){

        Des des=new Des();

    }
}
