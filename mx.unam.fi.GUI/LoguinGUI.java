/**
 * Esta clase se encarga de generar el login con ayuda de una GUI
 * @author Arcos Raul, Ferreira Cariel, Lopez Isaias, Soto Ivan
 */
package mx.unam.fi.GUI;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class LoginGUI {
    private final JPanel panel = new JPanel();
    public static JFrame frame = new JFrame("LOGIN");

    public static void main(String[] args) {
        LoginGUI loginGUI = new LoginGUI();
    }
    /**
     * El constructor recibe todas las partes de la interfaz
     */
    public LoginGUI(){
	insertLabels();
	//Botón recibido de iniciar sesión
	JButton boton = insertButton();
	insertTextBox(boton);

	panel.setBackground(Color.CYAN);
        //Se agrega el panel a la ventana
	frame.add(panel);
	//Creación de capa principal
	frame.add(panel);
	frame.setSize(500,500);
	frame.setLocationRelativeTo(null);//null->CENTRO, es como un location
	//No es moldeable la capa
	frame.setResizable(false);
	frame.setVisible(true);
	//Cierra el programa
	frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	//Mata al programa una vez cerrado
	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    /**
     * El metodo inserta las etiquetas como lo son las imagenes de la UNAM y de la FI,
     * etiquetas para identifcar campos de usuario y contrasena
     */
    private void insertLabels(){
	JLabel correoEt = new JLabel();

	correoEt.setText("Correo electronico");
	correoEt.setBounds(200,100,200,250);
	//X(der, izq),Y(arr,abaj),WIDTH(anchura), HEIGHT(altura)

	JLabel passwordEt = new JLabel();
	passwordEt.setText("Contrasena");
	passwordEt.setBounds(220,200,200,250);

        ImageIcon unam = new ImageIcon("C:\\Users\\Mr. Hyde\\Documents\\unam-escudo.png");
	JLabel labelUnam = new JLabel();
	labelUnam.setBounds(60,60,100,100);
	labelUnam.setIcon(new ImageIcon(unam.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH))); //WIDTH, HEIGHT, HINTS

	ImageIcon escudofi = new ImageIcon("C:\\Users\\Mr. Hyde\\Documents\\escudofi.png");
	JLabel labelFi = new JLabel();
	labelFi.setBounds(340,60,100,100);
	labelFi.setIcon(new ImageIcon(escudofi.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH)));

	//DESHABILITAR layout por defecto
	panel.setLayout(null);
	panel.add(correoEt);
	panel.add(passwordEt);
	panel.add(labelUnam);
	panel.add(labelFi);
    }
    /**
     * El metodo crea el boton de iniciar sesion
     * @return JButton un boton para inciar sesion
     */
    private JButton insertButton(){
	JButton boton = new JButton();
	boton.setText("Iniciar sesion");
	boton.setBounds(190,400,120,30);
	//boton.setLocation(100,100);
	boton.setEnabled(true);
	boton.setForeground(Color.red);
	panel.add(boton);

	return boton;
    }
    /**
     * El metodo inserta las cajas de texto donde el usuario escribira
     * e interactuara con el programa. Ademas, se valida que lo que se
     * haya insertado sea correcto o no
     * @param boton
     */
    private void insertTextBox(JButton boton){
	//Se crean los cuadros en donde se insertará el texto de correo y password
	JTextArea correo = new JTextArea();
	correo.setBounds(170,180,180,20);
	correo.setText("");

	JPasswordField password = new JPasswordField();
	password.setBounds(170,280,180,20);

	panel.add(correo);
	panel.add(password);

	boton.addActionListener((ActionEvent ae) -> {
            if(correo.getText().equals("admin") && String.valueOf(password.getPassword()).equals("admin")){
                //Cierra la ventana 1
                frame.setVisible(false);
                //Abre la siguiente ventana

            }else{
                JOptionPane.showMessageDialog(null,"Usuario/Contrasena incorrecto");
            }
        });
    }
}
