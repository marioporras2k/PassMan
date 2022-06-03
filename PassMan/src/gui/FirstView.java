package gui;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

public class FirstView extends JFrame implements ActionListener  {
	
	 public void CentrarJFrame(){
	      setSize(720, 399);		
	      setLocationRelativeTo(null);		
	      setVisible(true);
	  }
	
	public FirstView() {
		setUndecorated(true);
		getContentPane().setBackground(Color.white);
		getContentPane().setLayout(null);
		((JComponent) getContentPane()).setBorder(new LineBorder ((Color) new Color(225, 225, 225)));
		CentrarJFrame();
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.WHITE));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 720, 399);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setIcon(null);
		logo.setBounds(10, 312, 33, 61);
		panel.add(logo);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FirstView.class.getResource("/assets/Fondo_2_FirstView.PNG")));
		lblNewLabel.setBounds(0, 0, 724, 400);
		panel.add(lblNewLabel);
		
		JButton btnNo_1_1 = new JButton("");
		btnNo_1_1.setForeground(new Color(245, 245, 245));
		btnNo_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginView frame = new LoginView();
				frame.setLocationRelativeTo(null);
				dispose();
				frame.setVisible(true);
				
			}
		});
		btnNo_1_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		btnNo_1_1.setFocusPainted(false);
		btnNo_1_1.setContentAreaFilled(false);
		btnNo_1_1.setBorderPainted(false);
		btnNo_1_1.setBorder(null);
		btnNo_1_1.setBackground(Color.WHITE);
		btnNo_1_1.setBounds(0, 0, 724, 400);
		panel.add(btnNo_1_1);
		;
		Dimension pantallaTamano = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension tamano = (pantallaTamano);		
	}
	
   
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					FirstView frame = new FirstView();
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