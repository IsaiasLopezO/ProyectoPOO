import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class adminPrincipalView extends JFrame{

    public adminPrincipalView(){
        //Window name
        super("Admin: Tareas");

        //Basic elements of the window
        Container cont = getContentPane();
        cont.setLayout(null);
        JPanel actions = new JPanel();
        actions.setLayout(null);
        actions.setBounds(0,0,360,720);
        actions.setBackground(Color.BLUE);
        cont.add(actions);

        //Elements of actions pane
        JButton createWork = new JButton("Crear nueva tarea");
        createWork.setBounds(90,100,200,50);
        actions.add(createWork);

        setSize(1080,720);
        setVisible(true);
    }

    public static void main(String[] args) {
        new adminPrincipalView();
    }
}