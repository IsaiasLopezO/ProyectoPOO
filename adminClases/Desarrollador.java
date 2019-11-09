import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Des extends JFrame {
    public boolean T1=false,T2=false,T3=false,T4=false;//son las tareas que puede tener el desarrolladro
    public JFrame f=new JFrame();
    public Des() {
        f.setLayout(null);
        setTitle("nom: Tareas");
        JPanel p=new JPanel();
        p.setLayout(null);
        p.setBounds(0,0,360,720);
        p.setBackground(Color.BLUE);
        f.add(p);
        setSize(1080,720);
        setVisible(true);

    }
    public void setT1(){//Hacer un método así por tarea
        JPanel t=new JPanel();
        t.setLayout(null);
        t.setPreferredSize(new Dimension(250,50));
        t.setBackground(Color.RED);
        Button b=new Button("ejemplo");
        t.add(b);
        f.add(t);

    }
    public static void main(String[] args){
        Des des=new Des();
        des.setT1();
    }
}
