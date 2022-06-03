package gui;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.mysql.jdbc.Statement;

import conexion.Conexion;
import dao.UsuarioDAO;
import utils.AES;
import utils.Scrapping;

import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.UIManager;


public class TableView extends JFrame {
	
	private int fila = 0;
	private int columna = 0;
	private String password;
	private String url;
	private String correo;
	private String titulo;
	private String usuario;
	private Scrapping miScrap = new Scrapping();
	Statement st;
	private JLabel lblDatos = new JLabel("", SwingConstants.CENTER);
	private String categoria;

	
	public TableView()
	{		
		DefaultTableModel tm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				
					return false;
			}
		};

		// Titulos para la cabecera superior. 
		tm.setColumnIdentifiers(new String[] { "Titulo", "Email", "Usuario", "Password", "sitioweb", " Categoria" });
		
		JTable t = new JTable(tm);
		t.setBorder(null);
		t.setOpaque(false);
		t.setSelectionForeground(Color.BLACK);
		t.setSelectionBackground(UIManager.getColor("ToolBar.dockingForeground"));
		t.setGridColor(Color.BLACK);
		t.setBackground(Color.LIGHT_GRAY);
		t.setForeground(Color.BLACK);

		
		Conexion miConexion = new Conexion();
		Connection conexion = miConexion.conectar(); 
		String sql = "SELECT titulo,correo,username,password,sitioweb,categoria FROM organizador where organizador.id="+UsuarioDAO.iD;		
		
		String dato[] = new String[6];
		try {
			st = (Statement) conexion.createStatement();
			ResultSet result = st.executeQuery(sql);
			
			while (result.next()) {
				dato[0] = result.getString(1);
				dato[1] = result.getString(2);
				dato[2] = result.getString(3);
				dato[3] = result.getString(4);
				dato[4] = result.getString(5);
				dato[5] = result.getString(6);
				tm.addRow(dato);
			}

		}catch(SQLException ex) {
			ex.printStackTrace();
	}
		
		setUndecorated(true);
		getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane(t);
		scrollPane.setBorder((BorderFactory.createEmptyBorder()));
		scrollPane.setOpaque(false);
		scrollPane.setBounds(0, 81, 720, 231);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setVisible(true);
		getContentPane().add(scrollPane);
		setSize(720,399);
		setVisible(true);
		setLocationRelativeTo(null);		

		  t.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent me) {
					doMouseClicked(me);
				}
					public void doMouseClicked(MouseEvent me) {
						t.repaint();
						fila = t.rowAtPoint(me.getPoint());
						columna = t.columnAtPoint(me.getPoint());
						if (columna == 0) {
							System.out.println("Esto es un titulo");
							titulo = String.valueOf(t.getValueAt(fila,columna));
							lblDatos.setText("Titulo: " + titulo);
						}else if(columna == 1) {
							System.out.println("Esto es un correo");
							correo = String.valueOf(t.getValueAt(fila,columna));
							lblDatos.setText("Correo: "+correo);
						}else if(columna == 2) {
							System.out.println("Esto es un usuario");
							usuario = String.valueOf(t.getValueAt(fila,columna));
							lblDatos.setText("Usuario: " + usuario);
						}else if(columna == 3) {
							password=String.valueOf(t.getValueAt(fila,columna));
							System.out.println(password);
							lblDatos.setText("Contraseña: " +password);
						}else if(columna == 4) {
							url=String.valueOf(t.getValueAt(fila,columna));
							lblDatos.setText("URL: " + url);
							correo = String.valueOf(t.getValueAt(fila,1));
							titulo = String.valueOf(t.getValueAt(fila,0));
						}else if(columna == 5) {
							categoria=String.valueOf(t.getValueAt(fila,columna));
							lblDatos.setText("Categoria: " + categoria);
						}
					  }
					});
		  
		    TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(tm);
		    t.setRowSorter(elQueOrdena);
			t.setModel(tm);
			
			JButton btnDecrypt = new JButton("");
			btnDecrypt.setToolTipText("Ver contrase\u00F1a seleccionada");
			btnDecrypt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(columna == 3) {
							String passDecrypt =AES.decrypt(password, "patrondelmal");
							lblDatos.setText("Esta es tu contraseña: " + passDecrypt);
					}else {
						System.out.println("cant decrypt");
					}	
				}
			});
			btnDecrypt.setFocusPainted(false);
			btnDecrypt.setContentAreaFilled(false);
			btnDecrypt.setBorder(null);
			btnDecrypt.setBounds(206, 22, 64, 23);
			getContentPane().add(btnDecrypt);

			JButton btnCopiar = new JButton();
			btnCopiar.setToolTipText("Copiar dato seleccionado, desencripta campo contrase\u00F1a");
			btnCopiar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				if (columna == 0) {
					System.out.println("Esto es un titulo");
					titulo = String.valueOf(tm.getValueAt(fila,columna));
					Toolkit.getDefaultToolkit()
			        .getSystemClipboard()
			        .setContents(
			                (Transferable) new StringSelection(titulo),
			                null
			        );
				}else if(columna == 1) {
					System.out.println("Esto es un correo");
					correo = String.valueOf(tm.getValueAt(fila,columna));
					Toolkit.getDefaultToolkit()
			        .getSystemClipboard()
			        .setContents(
			                (Transferable) new StringSelection(correo),
			                null
			        );
				}else if(columna == 2) {
					System.out.println("Esto es un usuario");
					usuario = String.valueOf(tm.getValueAt(fila,columna));
					Toolkit.getDefaultToolkit()
			        .getSystemClipboard()
			        .setContents(
			                (Transferable) new StringSelection(usuario),
			                null
			        );
				}else if(columna == 3) {
					String passDecrypt2 =AES.decrypt(password, "patrondelmal");
					System.out.println(passDecrypt2);
					Toolkit.getDefaultToolkit()
			        .getSystemClipboard()
			        .setContents(
			                (Transferable) new StringSelection(passDecrypt2),
			                null
			        );
				}else if(columna == 4) {
					url=String.valueOf(tm.getValueAt(fila,columna));
					Toolkit.getDefaultToolkit()
			        .getSystemClipboard()
			        .setContents(
			                (Transferable) new StringSelection(url),
			                null
			        );
				}else if(columna == 5) {
					categoria =String.valueOf(tm.getValueAt(fila,columna));
					Toolkit.getDefaultToolkit()
			        .getSystemClipboard()
			        .setContents(
			                (Transferable) new StringSelection(categoria),
			                null
			        );
				}
				}
			});
			btnCopiar.setFocusPainted(false);
			btnCopiar.setContentAreaFilled(false);
			btnCopiar.setBorder(null);
			btnCopiar.setBounds(321, 11, 38, 39);
			getContentPane().add(btnCopiar);
			
			
			JButton btn_scrap = new JButton();
			btn_scrap.setToolTipText("Notificar si ha cambiado la p\u00E1gina(url) desde el \u00FAltimo uso de este bot\u00F3n");
			btn_scrap.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {			
						miScrap.scrap(correo,url,titulo);	
				}
			});
			btn_scrap.setFocusPainted(false);
			btn_scrap.setContentAreaFilled(false);
			btn_scrap.setBorder(null);
			btn_scrap.setBounds(419, 16, 57, 34);
			getContentPane().add(btn_scrap);
			
			JLabel lbl_username = new JLabel("New label");
			lbl_username.setForeground(Color.WHITE);
			lbl_username.setBounds(35, 11, 99, 28);
			lbl_username.setText(UsuarioDAO.user);
			getContentPane().add(lbl_username);
			
			JButton btnCrear = new JButton("");
			btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateSSpaceView menu = new CreateSSpaceView(); 
				menu.setLocationRelativeTo(null);
				dispose();
				menu.setVisible(true);
				}
			});
			btnCrear.setFocusPainted(false);
			btnCrear.setContentAreaFilled(false);
			btnCrear.setBorder(null);
			btnCrear.setBounds(291, 360, 141, 28);
			getContentPane().add(btnCrear);
			
			JButton btn_atras = new JButton();
			btn_atras.setToolTipText("Atr\u00E1s");
			btn_atras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						TreeView frame;
						frame = new TreeView();
						frame.setLocationRelativeTo(null);
						frame.setVisible(true);
						dispose();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
			});
			btn_atras.setFocusPainted(false);
			btn_atras.setContentAreaFilled(false);
			btn_atras.setBorder(null);
			btn_atras.setBounds(583, 11, 57, 45);
			getContentPane().add(btn_atras);
			
			JButton btn_cerrar = new JButton();
			btn_cerrar.setToolTipText("Cerrar programa");
			btn_cerrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
			btn_cerrar.setFocusPainted(false);
			btn_cerrar.setContentAreaFilled(false);
			btn_cerrar.setBorder(null);
			btn_cerrar.setBounds(659, 11, 48, 45);
			getContentPane().add(btn_cerrar);
			
			lblDatos.setForeground(Color.WHITE);
			lblDatos.setBounds(164, 323, 392, 14);
			getContentPane().add(lblDatos);
			
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(TableView.class.getResource("/assets/TableView_n.PNG")));
			lblNewLabel.setBounds(0, 0, 720, 399);
			getContentPane().add(lblNewLabel);
			
	}
}