package gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
import java.awt.SystemColor;
import dao.OrganizadorDAO;
import dao.UsuarioDAO;
import modelos.Organizador;
import utils.HasEspecialCharacter;

import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import java.awt.event.KeyEvent;
import javax.swing.JProgressBar;
import javax.swing.UIManager;


public class CreateSSpaceView extends JFrame implements ActionListener  {
	
	private JTextField textField_titulo;
	private JTextField textField_email;
	private JTextField textField_contrasena;
	private JTextField textField_URL;
	private JTextField textField_usuario;
	OrganizadorDAO miOrganizadorDAO = new OrganizadorDAO();
	private JTextField textField_categoria;
	private int verPass = 0;
	private HasEspecialCharacter miHasEspecialCharacter = new HasEspecialCharacter();
    private int value = 0;

	
	 public void CentrarJFrame(){
	      Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	      setSize(720, 399);		
	      setLocationRelativeTo(null);		
	      setVisible(true);   
	  }
	
	public CreateSSpaceView() {
		setUndecorated(true);
		setTitle("Nueva cuenta");
		getContentPane().setBackground(Color.BLACK);
		getContentPane().setLayout(null);
		CentrarJFrame();
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 720, 399);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setLayout(null);
		panel_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(165, 11, 424, 378);
		panel.add(panel_1);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblTitulo.setBackground(Color.WHITE);
		lblTitulo.setBounds(144, 44, 135, 25);
		panel_1.add(lblTitulo);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblEmail.setBackground(Color.WHITE);
		lblEmail.setBounds(144, 92, 136, 25);
		panel_1.add(lblEmail);
		
		
	
		JLabel lblNewLabel_2 = new JLabel("GUARDA TU CUENTA");
		lblNewLabel_2.setForeground(UIManager.getColor("ToolBar.dockingForeground"));
		lblNewLabel_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		lblNewLabel_2.setBounds(115, 11, 194, 32);
		panel_1.add(lblNewLabel_2);
		
		textField_titulo = new JTextField();
		textField_titulo.setToolTipText("20 caracteres");
		textField_titulo.setBackground(Color.LIGHT_GRAY);
		textField_titulo.setColumns(10);
		textField_titulo.setBounds(142, 66, 139, 17);
		panel_1.add(textField_titulo);
		
		textField_email = new JTextField();
		textField_email.setToolTipText("4 caracteres max.");
		textField_email.setBackground(Color.LIGHT_GRAY);
		textField_email.setColumns(10);
		textField_email.setBounds(97, 118, 229, 17);
		panel_1.add(textField_email);
		
		JLabel lblContrasena = new JLabel("Contrase\u00F1a");
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena.setForeground(Color.WHITE);
		lblContrasena.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblContrasena.setBackground(Color.WHITE);
		lblContrasena.setBounds(144, 189, 136, 22);
		panel_1.add(lblContrasena);
		
		textField_contrasena = new JPasswordField();
		textField_contrasena.setBackground(Color.LIGHT_GRAY);
		textField_contrasena.setColumns(10);
		textField_contrasena.setBounds(132, 212, 159, 17);
		panel_1.add(textField_contrasena);
		
		JLabel lblUrl = new JLabel("URL");
		lblUrl.setHorizontalAlignment(SwingConstants.CENTER);
		lblUrl.setForeground(Color.WHITE);
		lblUrl.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblUrl.setBackground(Color.WHITE);
		lblUrl.setBounds(143, 266, 136, 25);
		panel_1.add(lblUrl);
		
		textField_URL = new JTextField();
		textField_URL.setToolTipText("60 caracteres max.");
		textField_URL.setBackground(Color.LIGHT_GRAY);
		textField_URL.setColumns(10);
		textField_URL.setBounds(121, 293, 181, 17);
		panel_1.add(textField_URL);
		
		JButton btnGenerar = new JButton("");
		btnGenerar.setToolTipText("Generar contrase\u00F1a segura");
		btnGenerar.setBorder(null);
		//btnGenerar.setBorder(null);
		//btnGenerar.setForeground(Color.RED);
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = String.copyValueOf(miOrganizadorDAO.generatePass());
				textField_contrasena.setText(password);
			}
		});
		btnGenerar.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		btnGenerar.setFocusPainted(false);
		btnGenerar.setContentAreaFilled(false);
		//btnGenerar.setBackground(Color.WHITE);
		//btnGenerar.setForeground(Color.BLACK);    
		btnGenerar.setBounds(301, 200, 47, 44);
		panel_1.add(btnGenerar);
		
		JButton btnVerPass = new JButton("");
		btnVerPass.setToolTipText("Ver contrase\u00F1a");
		btnVerPass.setBorder(null);
		btnVerPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verPass == 0) {
					((JPasswordField) textField_contrasena).setEchoChar((char)0); 
					verPass = 1;
				}else {
					((JPasswordField) textField_contrasena).setEchoChar('•'); 
					verPass = 0;
				}
			}
			
		});
		btnVerPass.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		btnVerPass.setFocusPainted(false);
		btnVerPass.setContentAreaFilled(false);
		btnVerPass.setBounds(349, 200, 40, 44);
		panel_1.add(btnVerPass);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblUsuario.setBackground(Color.WHITE);
		lblUsuario.setBounds(144, 140, 136, 25);
		panel_1.add(lblUsuario);
		
		textField_usuario = new JTextField();
		textField_usuario.setToolTipText("12 caracteres max.");
		textField_usuario.setBackground(Color.LIGHT_GRAY);
		textField_usuario.setColumns(10);
		textField_usuario.setBounds(150, 161, 123, 17);
		panel_1.add(textField_usuario);
		
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategoria.setForeground(Color.WHITE);
		lblCategoria.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblCategoria.setBackground(Color.WHITE);
		lblCategoria.setBounds(144, 321, 136, 25);
		panel_1.add(lblCategoria);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.LIGHT_GRAY);
		comboBox.setBounds(16, 345, 189, 22);
		try {
			miOrganizadorDAO.llenarCombo(comboBox);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panel_1.add(comboBox);
		
		textField_categoria = new JTextField();
		textField_categoria.setToolTipText("16 caracteres max.");
		textField_categoria.setBackground(Color.LIGHT_GRAY);
		textField_categoria.setColumns(10);
		textField_categoria.setBounds(215, 346, 123, 21);
		panel_1.add(textField_categoria);
		
		JButton btnCrear = new JButton("");
		btnCrear.setToolTipText("Crear categoria");
		btnCrear.setBorder(null);
		//btnCrear.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(miOrganizadorDAO.crearCategoria(textField_categoria.getText())) {
						JOptionPane.showMessageDialog(null, "Categoria creada correctamente");
						comboBox.removeAllItems();
						miOrganizadorDAO.llenarCombo(comboBox);
					}else {
						JOptionPane.showMessageDialog(null, "Categoria existente");
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Categoria existente");
				}
			}
		});
		
		btnCrear.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		btnCrear.setFocusPainted(false);
		btnCrear.setContentAreaFilled(false);
		//btnCrear.setBackground(Color.WHITE);
		btnCrear.setBounds(356, 336, 58, 31);
		panel_1.add(btnCrear);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(133, 241, 158, 14);
		panel_1.add(progressBar);
		//Actualizamos nuestra progressbar
				Timer t=new Timer(100,new ActionListener(){
		            public void actionPerformed(ActionEvent ae)
		            {
		            	//miHasEspecialCharacter.hasEspecial("Micontra*seña");
		            	if (textField_contrasena.getText().length()<=1) {
		        			progressBar.setValue(0);
		        			progressBar.setForeground(Color.red);
		        			progressBar.setString("Muy débil");
		        		}else if(textField_contrasena.getText().length()<6){
		        			progressBar.setValue(20);
		        			progressBar.setForeground(Color.red);
		        			progressBar.setString("Muy débil");
		        		}else if(textField_contrasena.getText().length()<12){
		        			if(miHasEspecialCharacter.hasEspecial(textField_contrasena.getText())==true) {
		        				value = 55;
		        			}else {
		        				value = 35;
		        			}
		        			progressBar.setValue(value);
		        			progressBar.setForeground(Color.ORANGE);
		        			progressBar.setString("Débil");
		        		}else if(textField_contrasena.getText().length()<16){
		        			if(miHasEspecialCharacter.hasEspecial(textField_contrasena.getText())==true) {
		        				value =75;
		        			}else {
		        				value = 65;
		        			}
		        			progressBar.setValue(value);
		        			progressBar.setForeground(Color.yellow);
		        			progressBar.setString("Aceptable");
		        		}else if(textField_contrasena.getText().length()<19){
		        			if(miHasEspecialCharacter.hasEspecial(textField_contrasena.getText())==true) {
		        				value = 100;
		        			}else {
		        				value =80 ;
		        			}
		        			progressBar.setForeground(Color.green);
		        			progressBar.setValue(value);
		        			progressBar.setString("Segura");
		        			progressBar.setToolTipText("No deberías de estar viendo esto");
		        			
		        		}else if(textField_contrasena.getText().length()>18){
		        			progressBar.setForeground(Color.darkGray);
		        			progressBar.setValue(100);
		        			progressBar.setString("18 caracteres max.");        			
		        		}
		            }
		        });
		        t.start();
		
		JButton btnAtras = new JButton("");
		btnAtras.setToolTipText("Atr\u00E1s");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TreeView frame;
				try {
					frame = new TreeView();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					dispose();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnAtras.setBounds(581, 19, 73, 35);
		panel.add(btnAtras);
		btnAtras.setForeground(SystemColor.desktop);
		btnAtras.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		btnAtras.setFocusPainted(false);
		btnAtras.setContentAreaFilled(false);
		btnAtras.setBorder(null);
		btnAtras.setBackground(Color.WHITE);
		
		JButton btnGuardar = new JButton();
		btnGuardar.setToolTipText("Guardar cuenta");
		btnGuardar.setBorder(null);
		btnGuardar.setBounds(10, 317, 81, 71);
		panel.add(btnGuardar);
		btnGuardar.setFocusPainted(false);
		btnGuardar.setContentAreaFilled(false);
		btnAtras.setBorder(null);

		
		JButton btnCerrar = new JButton("");
		btnCerrar.setBorder(null);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnCerrar.setToolTipText("cerrar");
		btnCerrar.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		btnCerrar.setFocusPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setBounds(656, 11, 54, 51);
		panel.add(btnCerrar);
		
		JLabel lblUser = new JLabel("",  SwingConstants.CENTER);
		lblUser.setForeground(Color.WHITE);
		lblUser.setBorder(null);
		lblUser.setBounds(34, 19, 99, 25);
		panel.add(lblUser);
		try {
			lblUser.setText(UsuarioDAO.user);
			System.out.println(UsuarioDAO.user);
		}catch(Exception e) {
			System.out.println(e);
		}
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(CreateSSpaceView.class.getResource("/assets/Create.PNG")));
		lblFondo.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblFondo.setDisplayedMnemonic(KeyEvent.VK_ENTER);
		lblFondo.setBounds(0, 0, 722, 400);
		panel.add(lblFondo);
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Organizador miOrganizador = new Organizador();
				miOrganizador.setId(UsuarioDAO.iD);
				miOrganizador.setTitulo(textField_titulo.getText());
				miOrganizador.setCorreo(textField_email.getText());
				miOrganizador.setUsername(textField_usuario.getText());
				miOrganizador.setPassword(textField_contrasena.getText());
				miOrganizador.setSitioweb(textField_URL.getText());
				miOrganizador.setCategoria(comboBox.getSelectedItem().toString());
				miOrganizadorDAO.crearDatos(miOrganizador);
				
			}	
		});
			
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					CreateSSpaceView frame = new CreateSSpaceView();
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