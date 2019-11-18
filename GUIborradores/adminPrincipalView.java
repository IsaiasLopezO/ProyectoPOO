import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javafx.scene.control.ChoiceBox;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.ComponentUI;

public class AdminPrincipalView extends JFrame{

  static int numOfWorks = 0;
  static JScrollPane works = new JScrollPane();
  static JScrollPane aux = new JScrollPane();

	public AdminPrincipalView() {
  	super("Admin: Tareas");

		//Action events, instances
    WorkBoton wB = new WorkBoton();

    String data[][] = {{"IsaLo", "Pausa"},
                    {"Soto", "Pausa"},
                    {"Coriel", "Pausa"},
                    {"Coriel", "Pausa"},{"IsaLo", "Pausa"},
                    {"Soto", "Pausa"},
                    {"Coriel", "Pausa"},
                    {"Coriel", "Pausa"},{"IsaLo", "Pausa"},
                    {"Soto", "Pausa"},
                    {"Coriel", "Pausa"},
                    {"Coriel", "Pausa"},{"IsaLo", "Pausa"},
                    {"Soto", "Pausa"},
                    {"Coriel", "Pausa"},
                    {"Coriel", "Pausa"},{"IsaLo", "Pausa"},
                    {"Soto", "Pausa"},
                    {"Coriel", "Pausa"},
                    {"Coriel", "Pausa"},{"IsaLo", "Pausa"},
                    {"Soto", "Pausa"},
                    {"Coriel", "Pausa"},
                    {"Coriel", "Pausa"},{"IsaLo", "Pausa"},
                    {"Soto", "Pausa"},
                    {"Coriel", "Pausa"},
                    {"Coriel", "Pausa"},{"IsaLo", "Pausa"},
                    {"Soto", "Pausa"},
                    {"Coriel", "Pausa"},
                    {"Coriel", "Pausa"},{"IsaLo", "Pausa"},
                    {"Soto", "Pausa"},
                    {"Coriel", "Pausa"},
                    {"Coriel", "Pausa"},{"IsaLo", "Pausa"},
                    {"Soto", "Pausa"},
                    {"Coriel", "Pausa"},
                    {"Coriel", "Pausa"},{"IsaLo", "Pausa"},
                    {"Soto", "Pausa"},
                    {"Coriel", "Pausa"},
                    {"Coriel", "Pausa"},{"IsaLo", "Pausa"},
                    {"Soto", "Pausa"},
                    {"Coriel", "Pausa"},
                    {"Coriel", "Pausa"},{"IsaLo", "Pausa"},
                    {"Soto", "Pausa"},
                    {"Coriel", "Pausa"},
                    {"Coriel", "Pausa"}};

		//Works contains all existing works
    aux.setBackground(Color.DARK_GRAY);
    aux.setLayout(null);
    aux.add(workPanel("Tarea 1", data));
    aux.add(workPanel("Tarea 2", data));
    aux.add(workPanel("Tarea 3", data));
    aux.add(workPanel("Tarea 4", data));
    aux.setPreferredSize(new Dimension(645*numOfWorks-(200),680));

    works.setViewportView(aux);
    works.setPreferredSize(new Dimension(500,680));
    works.setBounds(260,0,640,680);

    //Actions contains buttons
    //Elements of actions pane
    JPanel actions = new JPanel();
    actions.setLayout(null);
    actions.setBounds(0,0,260,720);
    actions.setBackground(Color.BLUE);
    JButton createWork = new JButton("Crear tarea"); //Work Button
    createWork.setBounds(50,100,150,50);
    createWork.addActionListener(wB);
    actions.add(createWork);
    JButton deleteWork = new JButton("Borrar tarea"); //Work Button
    deleteWork.setBounds(50,250,150,50);
    deleteWork.addActionListener(wB);
    actions.add(deleteWork);


    setLayout(null);
    add(actions);
    add(works);
    setSize(900,720);
    setVisible(true);
  }

  private static JPanel workPanel(String workName, String[][] data){
		//panel will be added on works
    JPanel workPanel = new JPanel();
    workPanel.setLayout(null);

    //Work tittle settings
    JLabel workHeader = new JLabel(workName);
    workHeader.setBounds(180, 0, 100, 100);

		//Scroll Panel
    JScrollPane collaborators = new JScrollPane();

		//TableModel with collaborators
    String[] rowsName = {"Nombre","Estado"}; //Data tittles
    DefaultTableModel table = new DefaultTableModel(data, rowsName){
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
		final JTable colTable = new JTable(table); //Real table
		colTable.setBounds(0,0,400,200);
    colTable.setBackground(Color.WHITE);
    collaborators.setLayout(new ScrollPaneLayout());
    collaborators.setViewportView(colTable);
		collaborators.setBounds(0,100,400,300);

    //adding to principal Panel
		workPanel.add(workHeader);
		workPanel.add(collaborators);
		workPanel.setVisible(true);
    workPanel.setBackground(Color.lightGray);
    workPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
    workPanel.setBounds(100+(numOfWorks*600), 160, 400, 400);
    numOfWorks++;
    return workPanel;
  }

  public static void main(String[] args) {
    AdminPrincipalView app = new AdminPrincipalView();
    app.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  private class WorkBoton implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      WorkBoton wb = new WorkBoton();
      if (e.getActionCommand() == "Crear tarea") {
        wb.createNewWorkPanel();
        JOptionPane.showMessageDialog(AdminPrincipalView.this, "Una tarea fue creada");
      }

      if (e.getActionCommand() == "Borrar tarea") {
        wb.deleteWorkPanel();
        JOptionPane.showMessageDialog(AdminPrincipalView.this, "Una tarea fue borrada");
      }
    }

    public void createNewWorkPanel() {
      String name = JOptionPane.showInputDialog(AdminPrincipalView.this, "Nombre:");
      String[][] developer = { {name, "Asignada" } };
      AdminPrincipalView.aux.add(workPanel("Tarea " + AdminPrincipalView.numOfWorks, developer));
      aux.setPreferredSize(new Dimension(645 * numOfWorks, 680));
      AdminPrincipalView.works.setViewportView(aux);
    }

    public void deleteWorkPanel(){
      Component[] worksPanels = AdminPrincipalView.aux.getComponents();
      int x = worksPanels.length;
      AdminPrincipalView.aux.remove(worksPanels[x-1]);
      numOfWorks--;
      aux.setPreferredSize(new Dimension(645 * numOfWorks, 680));
      AdminPrincipalView.works.setViewportView(aux);
    }
  }
}
