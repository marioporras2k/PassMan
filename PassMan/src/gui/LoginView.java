package gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import dao.UsuarioDAO;
import modelos.Usuario;
import javax.swing.border.EmptyBorder;

public class LoginView extends JFrame implements ActionListener  {
	private JTextField textField_email;
	private JPasswordField passwordField;
	private int verPass = 0;
	
	 public void CentrarJFrame(){
	      Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	      setSize(720, 399);		
	      setLocationRelativeTo(null);		
	      setVisible(true);
	  }
	
	public LoginView() {
		setUndecorated(true);
		setTitle("Login");
		getContentPane().setBackground(Color.BLACK);
		getContentPane().setLayout(null);
		CentrarJFrame();
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 720, 399);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setLayout(null);
		panel_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(203, 64, 292, 284);
		panel.add(panel_1);
		
		JLabel lblEmail = new JLabel("Enter email:");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblEmail.setBackground(Color.WHITE);
		lblEmail.setBounds(85, 29, 135, 25);
		panel_1.add(lblEmail);
		
		JLabel lblPass = new JLabel("Enter password:");
		lblPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblPass.setForeground(Color.WHITE);
		lblPass.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblPass.setBackground(Color.WHITE);
		lblPass.setBounds(85, 117, 136, 25);
		panel_1.add(lblPass);
		
		JButton btnRegistrar = new JButton("");
		btnRegistrar.setForeground(Color.LIGHT_GRAY);
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterView frame = new RegisterView();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				dispose();
			}
		});
		btnRegistrar.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		btnRegistrar.setFocusPainted(false);
		btnRegistrar.setContentAreaFilled(false);
		btnRegistrar.setBorder(null);
		btnRegistrar.setBackground(Color.WHITE);
		btnRegistrar.setBounds(85, 259, 142, 25);
		panel_1.add(btnRegistrar);
		
		JButton btnLogin = new JButton("");
		btnLogin.setForeground(Color.LIGHT_GRAY);
		btnLogin.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		btnLogin.setFocusPainted(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBorder(null);
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setBounds(85, 204, 142, 25);
		panel_1.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	Usuario miUsuario = new Usuario();
				UsuarioDAO miUsuarioDAO = new UsuarioDAO();
				String contra = new String(passwordField.getPassword());
				
				if(!passwordField.getPassword().toString().contentEquals("") && !contra.contentEquals("")) {
					miUsuario.setEmail(textField_email.getText());
					miUsuario.setPassword(contra);
					
					try {
						if(miUsuarioDAO.pruebaLogin(miUsuario)) {
							try {
								System.out.println(UsuarioDAO.user);
								JOptionPane.showMessageDialog(null, "Logeado correctamente");
								TreeView menu = new TreeView(); 
								menu.setLocationRelativeTo(null);
								dispose();
								menu.setVisible(true);
							}catch(Exception ex) {
								
							}
							
						}else {
							JOptionPane.showMessageDialog(null, "Contraseña o usuario incorrectamente");

						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					}else { 					
						JOptionPane.showMessageDialog(null, "Contraseña o usuario incorrectamente");						
					};
				};
		});
		
		textField_email = new JTextField();
		textField_email.setBackground(Color.LIGHT_GRAY);
		textField_email.setColumns(10);
		textField_email.setBounds(51, 65, 204, 25);
		panel_1.add(textField_email);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(Color.LIGHT_GRAY);
		passwordField.setBounds(85, 153, 146, 25);
		panel_1.add(passwordField);
		
		JButton btnVerPass = new JButton("");
		btnVerPass.setToolTipText("Ver contrase\u00F1a");
		btnVerPass.setBorder(null);
		btnVerPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verPass == 0) {
					((JPasswordField) passwordField).setEchoChar((char)0); 
					verPass = 1;
				}else {
					((JPasswordField) passwordField).setEchoChar('•'); 
					verPass = 0;
				}
			}	
		});
		btnVerPass.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		btnVerPass.setFocusPainted(false);
		btnVerPass.setContentAreaFilled(false);
		btnVerPass.setBounds(10, 142, 54, 43);
		panel_1.add(btnVerPass);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LoginView.class.getResource("/assets/LoginView_N.PNG")));
		lblNewLabel.setBounds(0, 0, 720, 410);
		panel.add(lblNewLabel);
		
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
				FirstView frame;
				frame = new FirstView();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				dispose();
			}
		});
		btnAtras.setToolTipText("atras");
		btnAtras.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		btnAtras.setFocusPainted(false);
		btnAtras.setContentAreaFilled(false);
		btnAtras.setBounds(592, 11, 54, 43);
		panel.add(btnAtras);	
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}