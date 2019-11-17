import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdminPrincipalView extends JFrame{

	public AdminPrincipalView() {
  	super("Admin: Tareas");

    //Basic elements of the window
    Container cont = getContentPane();
    cont.setLayout(null);

    //Actions contains buttons
    //Elements of actions pane
    JPanel actions = new JPanel();
    actions.setLayout(null);
    actions.setBounds(0,0,360,720);
    actions.setBackground(Color.blue);
    JButton createWork = new JButton("Crear nueva tarea");
    createWork.setBounds(90,100,200,50);
    createWork.addActionListener(wB);
    actions.add(createWork);

    //Works contains all existing works
    //Elements of action pane
    JScrollPane works = new JScrollPane();
		//data example
		Object[][] data = {"IsaLo", "Pausa"},
											{"Soto", "Pausa"},
											{"Coriel", "Pausa"};
    works.setLayout(null);
    works.setBounds(360,0,920,50);

    //Action events
    WorkBoton wB = new WorkBoton();

    cont.add(actions);
    cont.add(works);
    setSize(1280,720);
    setVisible(true);
  }

  private JPanel workPanel(String workName, int numC, Object[][] data){
		//panel will be added on works
    JPanel workPanel = new JPanel();
    //Work tittle settings
    JLabel workHeader = new JLabel(workName);
    workHeader.setBounds(0, 0, 300, 300);
    workHeader.setBackground(Color.gray);
		//Scroll Panel
		JScrollPane collaborators = new JScrollPane();
    //TableModel with collaborators
    String[] rowsName = {"Nombre", "Commits","Estado"} //Data tittles
    DefaultTableModel table = new DefaultTableModel(data, rowsName);
		JTable colTable = new JTable(table); //Real table
		workPanel.add(w)
    return workPanel;
  }

  public static void main(String[] args) {
    AdminPrincipalView app = new AdminPrincipalView();
    app.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  private class WorkBoton implements ActionListener {
  	public void actionPerformed(ActionEvent e) {
    	JOptionPane.showMessageDialog(AdminPrincipalView.this, "Una tarea fue creada");
    }
  }
}
