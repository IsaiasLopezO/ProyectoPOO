import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import javax.swing.*;

public class AdminPrincipalView extends JFrame{

	public AdminPrincipalView() {
  	super("Admin: Tareas");

		//Action events, instances
    WorkBoton wB = new WorkBoton();

    //Basic elements of the window
    Container cont = getContentPane();
    cont.setLayout(null);

		//Works contains all existing works
    //Elements of action pane
    JScrollPane works = new JScrollPane();
		works.setBackground(Color.DARK_GRAY);

		//data example
		String data[][] = {{"IsaLo", "Pausa"},
												 {"Soto", "Pausa"},
												 {"Coriel", "Pausa"}};
    works.setLayout(null);
    works.setBounds(360,0,920,720);
		works.add(workPanel("Tarea 1", data));

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

    cont.add(actions);
    cont.add(works);
    setSize(1280,720);
    setVisible(true);
  }

  private static JPanel workPanel(String workName, String[][] data){
		//panel will be added on works
    JPanel workPanel = new JPanel();
		workPanel.setLayout(null);

    //Work tittle settings
    //JLabel workHeader = new JLabel(workName);
    //workHeader.setBounds(200, 200, 100, 100);
    //workHeader.setBackground(Color.WHITE);

		//Scroll Panel
		JScrollPane collaborators = new JScrollPane();
		collaborators.setBounds(0,200,500,500);

		//TableModel with collaborators
    String[] rowsName = {"Nombre", "Commits","Estado"}; //Data tittles
    DefaultTableModel table = new DefaultTableModel(data, rowsName);
		JTable colTable = new JTable(table); //Real table
		colTable.setBounds(0,0,400,400);
		colTable.setBackground(Color.orange);
		collaborators.add(colTable);

		//adding to principal Panel
		//workPanel.add(workHeader);
		workPanel.add(collaborators);
		workPanel.setSize(960,600);
		workPanel.setVisible(true);
		workPanel.setBackground(Color.magenta);
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
