//package adminClases;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class adminPrincipalView extends JFrame{

    public adminPrincipalView() {
        //Window name
        super("Admin: Tareas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Basic elements of the window
        Container cont = getContentPane();
        cont.setLayout(null);
        JPanel actions = new JPanel();
        actions.setLayout(null);
        actions.setBounds(0,0,360,720);
        actions.setBackground(Color.BLUE);
        cont.add(actions);

        //Action events
        WorkBoton wB = new WorkBoton();

        //Elements of actions pane
        JButton createWork = new JButton("Crear nueva tarea");
        createWork.setBounds(90,100,200,50);
        createWork.addActionListener(wB);
        actions.add(createWork);

        setSize(1280,720);
        setVisible(true);
    }

    public static void main(String[] args) {
        new adminPrincipalView();
    }

    private class WorkBoton implements ActionListener{
        public void createWork(ActionEvent e){
            JOptionPane.showMessageDialog(Boton.this, "Usted oprimio: " + evento.getActionCommand());
        }
    }
}