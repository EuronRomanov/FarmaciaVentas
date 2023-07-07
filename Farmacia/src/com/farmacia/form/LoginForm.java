package com.farmacia.form;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.farmacia.controlador.UsuarioDao;
import com.farmacia.entidades.SesionUsuario;

import com.farmacia.entidades.Usuario;

import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Window.Type;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	private PnlLogin pnlLogin=new PnlLogin();
	private PnlIngresoApp pnlIngresoApp=new PnlIngresoApp();
	private JPanel pnlMenu = new JPanel();
	private PnlIngresoDB pnlIngresoDB =new PnlIngresoDB();
	private Usuario usuario=new Usuario();
	private UsuarioDao usuarioDao=new UsuarioDao();
		/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		setType(Type.UTILITY);
		setResizable(false);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 515, 316);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnlLogin);
		pnlLogin.setLayout(new BorderLayout(0, 0));
		pnlMenu.setOpaque(false);
		pnlLogin.add(pnlMenu, BorderLayout.CENTER);
		/*pnlIngresoApp.setOpaque(false);
		pnlLogin.add(pnlIngresoApp, BorderLayout.NORTH);
		pnlIngresoDB.setOpaque(false);
		pnlLogin.add(pnlIngresoDB, BorderLayout.SOUTH);
		 pnlIngresoApp.setVisible(false);
		 pnlIngresoDB.setVisible(false);*/
		 pnlMenu.setVisible(true);
		 
		GridBagLayout gbl_pnlMenu = new GridBagLayout();
		gbl_pnlMenu.columnWidths = new int[]{100, 0, 55, 123, 0};
		gbl_pnlMenu.rowHeights = new int[]{50, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlMenu.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_pnlMenu.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlMenu.setLayout(gbl_pnlMenu);
		
		JButton btnLoginApp = new JButton("Ingresar a App");
		btnLoginApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 //PnlIngresoApp ingreso= new PnlIngresoApp();
				    pnlMenu.setVisible(false);
				    
				    pnlLogin.add(pnlIngresoApp, BorderLayout.CENTER);
				    pnlIngresoApp.setVisible(true);
				   
					
			}
		});
		btnLoginApp.setMinimumSize(new Dimension(200, 50));
		btnLoginApp.setMaximumSize(new Dimension(300, 50));
		GridBagConstraints gbc_btnLoginApp = new GridBagConstraints();
		gbc_btnLoginApp.anchor = GridBagConstraints.SOUTH;
		gbc_btnLoginApp.insets = new Insets(0, 0, 5, 5);
		gbc_btnLoginApp.gridx = 2;
		gbc_btnLoginApp.gridy = 3;
		pnlMenu.add(btnLoginApp, gbc_btnLoginApp);
		btnLoginApp.setPreferredSize(new Dimension(200, 50));
		
		JLabel lblNewLabel = new JLabel("-O-");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 4;
		pnlMenu.add(lblNewLabel, gbc_lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Ingresar a Base Datos");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				   pnlMenu.setVisible(false);
				   pnlLogin.add(pnlIngresoDB, BorderLayout.CENTER);
				   pnlIngresoDB.setVisible(true);
				
				   
			}
		});
		btnNewButton_1.setMinimumSize(new Dimension(200, 50));
		btnNewButton_1.setMaximumSize(new Dimension(300, 50));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 5;
		pnlMenu.add(btnNewButton_1, gbc_btnNewButton_1);
		btnNewButton_1.setPreferredSize(new Dimension(200, 50));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{424, 0};
		gbl_contentPane.rowHeights = new int[]{251, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		pnlIngresoApp.getBtnLoginCancelar().addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		pnlLogin.add(pnlIngresoApp,BorderLayout.SOUTH);
		 		pnlLogin.add(pnlMenu,BorderLayout.CENTER);
		 		pnlIngresoApp.setVisible(false);
		 		 pnlMenu.setVisible(true);
		 		  
		 	}
		 });
		
		pnlIngresoApp.getBtnLoginIngresar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usuario=usuarioDao.loginUsuario(pnlIngresoApp.getTextLoginUsuario().getText(), String.valueOf(pnlIngresoApp.getTextLoginPassword().getPassword()));
				if (usuario!=null) {
					SesionUsuario.setCodUsuario(usuario.getCodUsuario());
					SesionUsuario.setNombreUsuario(usuario.getNombre());
					desactivarVenta();
				}
			
			}
		});
		
		
		pnlIngresoDB.getBtnDBCancelar().addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		pnlLogin.add(pnlIngresoDB,BorderLayout.SOUTH);
		 		pnlLogin.add(pnlMenu,BorderLayout.CENTER);
		 		pnlIngresoDB.setVisible(false);
		 		 pnlMenu.setVisible(true);
		 		  
		 	}
		 });

		
		
	}

	public PnlIngresoApp getPnlIngresoApp() {
		return pnlIngresoApp;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	protected void desactivarVenta() {
		this.dispose();
	}
	
}
