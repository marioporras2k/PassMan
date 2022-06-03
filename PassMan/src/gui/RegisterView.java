package gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import conexion.Conexion;
import dao.UsuarioDAO;
import modelos.Usuario;
import utils.HasEspecialCharacter;

import javax.swing.border.EmptyBorder;


import javax.swing.JProgressBar;
import javax.swing.UIManager;


public class RegisterView extends JFrame implements ActionListener  {
	
	private JTextField textField_email;
	private JTextField textField_pass;
	private static final String EMAIL_PATTERN = 
		"^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	private static final String PASS_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,18}$";
	private JTextField txtUsername;
	private JPasswordField passField_Confirm;
	private HasEspecialCharacter miHasEspecialCharacter = new HasEspecialCharacter();
    private int value = 0;
    private int verPass = 0;
	
	 public void CentrarJFrame(){
	      setSize(708, 399);		
	      setLocationRelativeTo(null);		
	      setVisible(true);
	  }
	
	public RegisterView() {
		setUndecorated(true);
		setTitle("Login");
		getContentPane().setBackground(Color.BLACK);
		getContentPane().setLayout(null);
		CentrarJFrame();
		
		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("InternalFrame.border"));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 718, 399);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setForeground(new Color(0, 0, 0));
		panel_1.setLayout(null);
		panel_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(161, 0, 398, 399);
		panel.add(panel_1);
		
		JLabel lblEmail = new JLabel("Introducir correo:");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblEmail.setBackground(Color.WHITE);
		lblEmail.setBounds(116, 32, 165, 25);
		panel_1.add(lblEmail);
		
		JLabel lblPass = new JLabel("Introducir contrase\u00F1a maestra:");
		lblPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblPass.setForeground(Color.WHITE);
		lblPass.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblPass.setBackground(Color.WHITE);
		lblPass.setBounds(72, 187, 254, 25);
		panel_1.add(lblPass);
		
		JButton btnRegistrar = new JButton("");
		btnRegistrar.setForeground(new Color(204, 204, 204));
		btnRegistrar.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		btnRegistrar.setFocusPainted(false);
		btnRegistrar.setContentAreaFilled(false);
		btnRegistrar.setBorder(null);
		btnRegistrar.setBackground(Color.WHITE);
		btnRegistrar.setBounds(126, 346, 146, 31);
		panel_1.add(btnRegistrar);
		
		textField_email = new JTextField();
		textField_email.setToolTipText("ex: user_name@yahoo.corporate.in ");
		textField_email.setBackground(Color.LIGHT_GRAY);
		textField_email.setColumns(10);
		textField_email.setBounds(101, 68, 195, 25);
		panel_1.add(textField_email);
		
		
		
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					UsuarioDAO miUsuarioDAO = new UsuarioDAO();
					Usuario miUsuario = new Usuario();
					String nombre=textField_email.getText();
					if (nombre.matches(EMAIL_PATTERN) && passField_Confirm.getText().contentEquals(textField_pass.getText()) && textField_pass.getText().matches(PASS_PATTERN)) {
						miUsuarioDAO.registrarUsuario(textField_email.getText(), textField_pass.getText(), txtUsername.getText());
						FirstView frame = new FirstView();
						frame.setLocationRelativeTo(null);
						frame.setVisible(true);
						Conexion miConexion = new Conexion();
						miConexion.cierraConexion();
						miConexion.conectar();
						dispose();
						
						
						}else {
						JOptionPane.showMessageDialog(null, "Revisa que los campos no estén vacios y el username no sea mayor de 12 digitos.\r\n" + 
								"								\r\n" +
								"							 Recuerda que la contraseña debe tener al menos 8 caracteres,	\r\n"+			
								"								\r\n" + 
								"								Contener al menos un numero	\r\n" + 
								"								\r\n" + 
								"								Contener una letra mayuscula por lo menos	\r\n" + 
								"								\r\n" + 
								"								Un caracter especial (@#%$^ etc.)	\r\n" + 
								"								\r\n" + 
								"								y no contener espacios	\r\n" + 
								"						");
						}
					}
		});
		
		textField_pass = new JPasswordField();
		textField_pass.setBackground(Color.LIGHT_GRAY);
		textField_pass.setColumns(10);
		textField_pass.setBounds(131, 223, 135, 25);
		panel_1.add(textField_pass);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(126, 259, 146, 14);
		progressBar.setStringPainted(true);
		panel_1.add(progressBar);
		
		JLabel lblUser = new JLabel("Introducir usuario:");
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblUser.setBackground(Color.WHITE);
		lblUser.setBounds(116, 104, 165, 25);
		panel_1.add(lblUser);
		
		txtUsername = new JTextField();
		txtUsername.setToolTipText("ex: username2022");
		txtUsername.setBackground(Color.LIGHT_GRAY);
		txtUsername.setColumns(10);
		txtUsername.setBounds(101, 140, 195, 25);
		panel_1.add(txtUsername);
		
		JLabel lblPass_1 = new JLabel("Repetir contrase\u00F1a maestra:");
		lblPass_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPass_1.setForeground(new Color(204, 204, 204));
		lblPass_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblPass_1.setBackground(Color.WHITE);
		lblPass_1.setBounds(78, 284, 242, 25);
		panel_1.add(lblPass_1);
		
		passField_Confirm = new JPasswordField();
		passField_Confirm.setBackground(Color.LIGHT_GRAY);
		passField_Confirm.setColumns(10);
		passField_Confirm.setBounds(131, 310, 135, 25);
		panel_1.add(passField_Confirm);
		
		JButton btnSeePassword = new JButton("");
		btnSeePassword.setToolTipText("Ver contrase\u00F1a");
		btnSeePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (verPass == 0) {
					((JPasswordField) textField_pass).setEchoChar((char)0); 
					verPass = 1;
				}else {
					((JPasswordField) textField_pass).setEchoChar('•'); 
					verPass = 0;
				}
			}
		});
		btnSeePassword.setForeground(new Color(204, 204, 204));
		btnSeePassword.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		btnSeePassword.setFocusPainted(false);
		btnSeePassword.setContentAreaFilled(false);
		btnSeePassword.setBorder(null);
		btnSeePassword.setBackground(Color.WHITE);
		btnSeePassword.setBounds(54, 223, 72, 37);
		panel_1.add(btnSeePassword);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setForeground(new Color(255, 255, 255));
		lblFondo.setIcon(new ImageIcon(RegisterView.class.getResource("/assets/RegisterView_N.PNG")));
		lblFondo.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblFondo.setBounds(-8, 0, 726, 399);
		panel.add(lblFondo);
		
		JButton btnCerrar = new JButton("");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnCerrar.setToolTipText("cerrar");
		btnCerrar.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		btnCerrar.setFocusPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setBounds(656, 11, 54, 43);
		panel.add(btnCerrar);
		
		JButton btnAtras = new JButton("");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginView frame;
				frame = new LoginView();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
		btnAtras.setToolTipText("atras");
		btnAtras.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		btnAtras.setFocusPainted(false);
		btnAtras.setContentAreaFilled(false);
		btnAtras.setBounds(592, 11, 54, 43);
		panel.add(btnAtras);
		
		//Actualizamos nuestra progressbar
		Timer t=new Timer(100,new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
            	if (textField_pass.getText().length()<=1) {
        			progressBar.setValue(0);
        			progressBar.setForeground(Color.red);
        			progressBar.setString("Muy débil");
        		}else if(textField_pass.getText().length()<6){
        			progressBar.setValue(20);
        			progressBar.setForeground(Color.red);
        			progressBar.setString("Muy débil");
        		}else if(textField_pass.getText().length()<12){
        			if(miHasEspecialCharacter.hasEspecial(textField_pass.getText())==true) {
        				value = 55;
        			}else {
        				value = 35;
        			}
        			progressBar.setValue(value);
        			progressBar.setForeground(Color.ORANGE);
        			progressBar.setString("Débil");
        		}else if(textField_pass.getText().length()<16){
        			if(miHasEspecialCharacter.hasEspecial(textField_pass.getText())==true) {
        				value =75;
        			}else {
        				value = 65;
        			}
        			progressBar.setValue(value);
        			progressBar.setForeground(Color.yellow);
        			progressBar.setString("Aceptable");
        		}else if(textField_pass.getText().length()<19){
        			if(miHasEspecialCharacter.hasEspecial(textField_pass.getText())==true) {
        				value = 100;
        			}else {
        				value =80 ;
        			}
        			progressBar.setForeground(Color.green);
        			progressBar.setValue(value);
        			progressBar.setString("Segura");
        			progressBar.setToolTipText("No deberías de estar viendo esto");
        			
        		}else if(textField_pass.getText().length()>18){
        			progressBar.setForeground(Color.darkGray);
        			progressBar.setValue(100);
        			progressBar.setString("18 caracteres max.");        			
        		}
            }
        });
        t.start();

						
				
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					RegisterView frame = new RegisterView();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}