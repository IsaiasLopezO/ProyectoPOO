import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Desarrollador extends JFrame{

    public Container cont = getContentPane();
    public Desarrollador(){
        //Window name
        super("nom: Tareas");//nom es el nombre que se manda desde la base de datos
        getContentPane().setLayout(new FlowLayout(FlowLayout.RIGHT));
        //Basic elements of the window
        //Container cont = getContentPane();
        cont.setLayout(null);
        JPanel actions = new JPanel();
        actions.setLayout(null);
        actions.setBounds(0,0,360,720);
        actions.setBackground(Color.BLUE);
        cont.add(actions);

        setSize(1080,720);
        setVisible(true);
    }
    private void NuevaTarea(){//sigue sin jalar D:
        JPanel t=new JPanel();
        t.setLayout(null);
        t.setPreferredSize(new Dimension(250,50));
        t.setBackground(Color.RED);
        Button b=new Button("ejemplo");
        t.add(b);
        cont.add(t);

    }

    public static void main(String[] args) {
        Desarrollador d=new Desarrollador();
        d.NuevaTarea();
        //d.NuevaTarea();
        //d.NuevaTarea();
        //d.NuevaTarea();
    }
}
