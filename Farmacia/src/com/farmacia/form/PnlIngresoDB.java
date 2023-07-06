package com.farmacia.form;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;

public class PnlIngresoDB extends JPanel {
	private JTextField textDBusuario;
	private JTextField textDBpassword;
	private JTextField textDBhost;
	private JTextField textDBpuerto;
	private JButton btnDBCancelar ;
	/**
	 * Create the panel.
	 */
	public PnlIngresoDB() {
		GridBagLayout gbl_pnlDB = new GridBagLayout();
		gbl_pnlDB.columnWidths = new int[]{99, 0, 0, 0, 0};
		gbl_pnlDB.rowHeights = new int[]{52, 52, 52, 52, 53, 0};
		gbl_pnlDB.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_pnlDB.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gbl_pnlDB);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textDBusuario = new JTextField();
		GridBagConstraints gbc_textDBusuario = new GridBagConstraints();
		gbc_textDBusuario.insets = new Insets(0, 0, 5, 5);
		gbc_textDBusuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDBusuario.gridx = 2;
		gbc_textDBusuario.gridy = 0;
		add(textDBusuario, gbc_textDBusuario);
		textDBusuario.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Contraseña");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 1;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textDBpassword = new JTextField();
		GridBagConstraints gbc_textDBpassword = new GridBagConstraints();
		gbc_textDBpassword.insets = new Insets(0, 0, 5, 5);
		gbc_textDBpassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDBpassword.gridx = 2;
		gbc_textDBpassword.gridy = 1;
		add(textDBpassword, gbc_textDBpassword);
		textDBpassword.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Host");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 2;
		add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		textDBhost = new JTextField();
		textDBhost.setText("localhost");
		GridBagConstraints gbc_textDBhost = new GridBagConstraints();
		gbc_textDBhost.insets = new Insets(0, 0, 5, 5);
		gbc_textDBhost.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDBhost.gridx = 2;
		gbc_textDBhost.gridy = 2;
		add(textDBhost, gbc_textDBhost);
		textDBhost.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Puerto");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 3;
		add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		textDBpuerto = new JTextField();
		textDBpuerto.setText("3306");
		GridBagConstraints gbc_textDBpuerto = new GridBagConstraints();
		gbc_textDBpuerto.insets = new Insets(0, 0, 5, 5);
		gbc_textDBpuerto.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDBpuerto.gridx = 2;
		gbc_textDBpuerto.gridy = 3;
		add(textDBpuerto, gbc_textDBpuerto);
		textDBpuerto.setColumns(10);
		
		 btnDBCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnDBCancelar = new GridBagConstraints();
		gbc_btnDBCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnDBCancelar.gridx = 1;
		gbc_btnDBCancelar.gridy = 4;
		add(btnDBCancelar, gbc_btnDBCancelar);
		
		JButton btnDBconectar = new JButton("Conectar");
		btnDBconectar.setPreferredSize(new Dimension(113, 23));
		GridBagConstraints gbc_btnDBconectar = new GridBagConstraints();
		gbc_btnDBconectar.insets = new Insets(0, 0, 0, 5);
		gbc_btnDBconectar.gridx = 2;
		gbc_btnDBconectar.gridy = 4;
		add(btnDBconectar, gbc_btnDBconectar);
		
		JButton btnDBprobarCon = new JButton("Probar Conexión");
		GridBagConstraints gbc_btnDBprobarCon = new GridBagConstraints();
		gbc_btnDBprobarCon.gridx = 3;
		gbc_btnDBprobarCon.gridy = 4;
		add(btnDBprobarCon, gbc_btnDBprobarCon);
		
		
	}
	public JButton getBtnDBCancelar() {
		return btnDBCancelar;
	}
	

}
