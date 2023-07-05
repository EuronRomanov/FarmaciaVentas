package com.farmacia.form;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Label;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JPasswordField;

public class Login {

	private JFrame frame;
	
	private JTextField textUsuario;
	private JTextField textPassword;

	FondoPanel fondo=new FondoPanel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(254, 255, 255));
		frame.setBackground(new Color(255, 248, 71));
		
		frame.setVisible(true);
		
		frame.setBounds(100, 100, 542, 386);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		
		JPanel panel_head = new FondoPanel(){
			private Image imagen1;

			@Override
			public void paint(Graphics g) {
				imagen1=new ImageIcon(getClass().getResource("/com/farmacia/icon/background_login.jpg")).getImage();
				g.drawImage(imagen1,0,0,getWidth(),getHeight(),this);
				// TODO Auto-generated method stub
				super.paint(g);
			}
		};
		panel_head.setOpaque(false);
		panel_head.setBackground(new Color(231, 255, 244));
		frame.getContentPane().add(panel_head, BorderLayout.NORTH);
		
	
		JLabel lblNewLabel = new JLabel("");
		
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/com/farmacia/icon/system-login.png")));
		lblNewLabel.setSize(new Dimension(104, 104));
		
		panel_head.add(lblNewLabel);
		this.escalarLabel(lblNewLabel, "/com/farmacia/icon/system-login.png");
		
		
		
		
		JPanel panel_body =new JPanel() {
			private Image imagen;

			@Override
			public void paint(Graphics g) {
				imagen=new ImageIcon(getClass().getResource("/com/farmacia/icon/background_login.jpg")).getImage();
				g.drawImage(imagen,0,0,getWidth(),getHeight(),this);
				// TODO Auto-generated method stub
				super.paint(g);
			}
		};
		panel_body.setOpaque(false);
		frame.getContentPane().add(panel_body, BorderLayout.CENTER);
		GridBagLayout gbl_panel_body = new GridBagLayout();
		gbl_panel_body.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_body.rowHeights = new int[]{48, 55, 49, 43, 43, 0};
		gbl_panel_body.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_body.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_body.setLayout(gbl_panel_body);
		
		JLabel lblUsuario = new JLabel("Usuario :");
		lblUsuario.setFont(new Font("Verdana", Font.BOLD, 15));
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 1;
		gbc_lblUsuario.gridy = 1;
		panel_body.add(lblUsuario, gbc_lblUsuario);
		
		textUsuario = new JTextField();
		textUsuario.setPreferredSize(new Dimension(30, 26));
		textUsuario.setMinimumSize(new Dimension(30, 26));
		GridBagConstraints gbc_textUsuario = new GridBagConstraints();
		gbc_textUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_textUsuario.gridx = 2;
		gbc_textUsuario.gridy = 1;
		panel_body.add(textUsuario, gbc_textUsuario);
		textUsuario.setColumns(10);
		
		JLabel lblpassword = new JLabel("Contraseña :");
		lblpassword.setFont(new Font("Verdana", Font.BOLD, 15));
		GridBagConstraints gbc_lblpassword = new GridBagConstraints();
		gbc_lblpassword.anchor = GridBagConstraints.BASELINE;
		gbc_lblpassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblpassword.gridx = 1;
		gbc_lblpassword.gridy = 2;
		panel_body.add(lblpassword, gbc_lblpassword);
		
		textPassword = new JPasswordField();
		textPassword.setPreferredSize(new Dimension(30, 26));
		textUsuario.setPreferredSize(new Dimension(30, 26));
		textPassword.setMinimumSize(new Dimension(50, 26));
		GridBagConstraints gbc_textPassword = new GridBagConstraints();
		gbc_textPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPassword.anchor = GridBagConstraints.BASELINE;
		gbc_textPassword.insets = new Insets(0, 0, 5, 5);
		gbc_textPassword.gridx = 2;
		gbc_textPassword.gridy = 2;
		panel_body.add(textPassword, gbc_textPassword);
		textPassword.setColumns(10);
		
		JButton btnLogin = new JButton("Entrar");
		btnLogin.setPreferredSize(new Dimension(80, 40));
		btnLogin.setMaximumSize(new Dimension(80, 50));
		btnLogin.setMinimumSize(new Dimension(80, 50));
		btnLogin.setAlignmentY(Component.TOP_ALIGNMENT);
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.gridwidth = 2;
		gbc_btnLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLogin.anchor = GridBagConstraints.NORTH;
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 1;
		gbc_btnLogin.gridy = 3;
		panel_body.add(btnLogin, gbc_btnLogin);
	}
	
	public void escalarLabel(JLabel label,String rutaImagen) {
		label.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(rutaImagen)).getImage()
				.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT)));
	}
	
	class FondoPanel extends JPanel{
		private Image imagen;

		@Override
		public void paint(Graphics g) {
			imagen=new ImageIcon(getClass().getResource("/com/farmacia/icon/background_login.jpg")).getImage();
			g.drawImage(imagen,0,0,getWidth(),getHeight(),this);
			// TODO Auto-generated method stub
			super.paint(g);
		}
		
	}
	
	
	

}
