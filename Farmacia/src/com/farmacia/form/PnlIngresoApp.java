package com.farmacia.form;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Dimension;

import com.farmacia.controlador.UsuarioDao;
import com.farmacia.entidades.Usuario;

public class PnlIngresoApp extends JPanel {
	private JTextField textLoginUsuario;
	private JPasswordField textLoginPassword;
	private JButton btnLoginCancelar;
	private JLabel lblLogo;
	
	
	private JButton btnLoginIngresar;
	/**
	 * Create the panel.
	 */
	
	public PnlIngresoApp() {
		setOpaque(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 276, 276, 276, 276, 0};
		gridBagLayout.rowHeights = new int[]{42, 45, 53, 49, 41, 44, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblLogo = new JLabel("");
		lblLogo.setSize(new Dimension(104, 104));
		lblLogo.setIcon(new ImageIcon(PnlIngresoApp.class.getResource("/com/farmacia/icon/system-login.png")));
		this.escalarLabel(lblLogo, "/com/farmacia/icon/system-login.png");
		GridBagConstraints gbc_lblLogo = new GridBagConstraints();
		gbc_lblLogo.gridheight = 2;
		gbc_lblLogo.gridwidth = 2;
		gbc_lblLogo.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogo.gridx = 2;
		gbc_lblLogo.gridy = 0;
		add(lblLogo, gbc_lblLogo);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setOpaque(true);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);
		
		textLoginUsuario = new JTextField();
		GridBagConstraints gbc_textLoginUsuario = new GridBagConstraints();
		gbc_textLoginUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_textLoginUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textLoginUsuario.gridx = 3;
		gbc_textLoginUsuario.gridy = 2;
		add(textLoginUsuario, gbc_textLoginUsuario);
		textLoginUsuario.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Contraseña");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 3;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textLoginPassword = new JPasswordField();
		GridBagConstraints gbc_textLoginPassword = new GridBagConstraints();
		gbc_textLoginPassword.insets = new Insets(0, 0, 5, 5);
		gbc_textLoginPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_textLoginPassword.gridx = 3;
		gbc_textLoginPassword.gridy = 3;
		add(textLoginPassword, gbc_textLoginPassword);
		
		btnLoginIngresar = new JButton("Ingresar");
		btnLoginIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//usuario=usuarioDao.loginUsuario(textLoginUsuario.getText(), String.valueOf(textLoginPassword.getPassword()));
				
			}
		});
		GridBagConstraints gbc_btnLoginIngresar = new GridBagConstraints();
		gbc_btnLoginIngresar.insets = new Insets(0, 0, 5, 5);
		gbc_btnLoginIngresar.gridx = 2;
		gbc_btnLoginIngresar.gridy = 4;
		add(btnLoginIngresar, gbc_btnLoginIngresar);
		
		 btnLoginCancelar = new JButton("Cancelar");
		 btnLoginCancelar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 	}
		 });
		GridBagConstraints gbc_btnLoginCancelar = new GridBagConstraints();
		gbc_btnLoginCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnLoginCancelar.gridx = 3;
		gbc_btnLoginCancelar.gridy = 4;
		add(btnLoginCancelar, gbc_btnLoginCancelar);

	}
	public JButton getBtnLoginCancelar() {
		return btnLoginCancelar;
	}
	
	public void escalarLabel(JLabel label,String rutaImagen) {
		label.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(rutaImagen)).getImage()
				.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT)));
	}
	
	public JButton getBtnLoginIngresar() {
		return btnLoginIngresar;
	}
	public JTextField getTextLoginUsuario() {
		return textLoginUsuario;
	}
	public JPasswordField getTextLoginPassword() {
		return textLoginPassword;
	}
	
	public void limpiarCampos() {
		textLoginUsuario.setText("");
		textLoginUsuario.setText("");
	}
	

}
