package com.farmacia.form;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import com.farmacia.bd.ConexionBD;

public class PnlIngresoDB extends JPanel {
	private JTextField textDBusuario;
	private JTextField textDBpassword;
	private JTextField textDBhost;
	private JTextField textDBpuerto;
	private JButton btnDBCancelar ;
	private JTextField textDBnombre;
	/**
	 * Create the panel.
	 */
	public PnlIngresoDB() {
		GridBagLayout gbl_pnlDB = new GridBagLayout();
		gbl_pnlDB.columnWidths = new int[]{99, 0, 0, 0, 0};
		gbl_pnlDB.rowHeights = new int[]{32, 52, 52, 52, 52, 53, 0};
		gbl_pnlDB.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_pnlDB.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gbl_pnlDB);
		
		JLabel lblNewLabel = new JLabel("Base Datos");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		textDBnombre = new JTextField();
		GridBagConstraints gbc_textDBnombre = new GridBagConstraints();
		gbc_textDBnombre.insets = new Insets(0, 0, 5, 5);
		gbc_textDBnombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDBnombre.gridx = 2;
		gbc_textDBnombre.gridy = 0;
		add(textDBnombre, gbc_textDBnombre);
		textDBnombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textDBusuario = new JTextField();
		GridBagConstraints gbc_textDBusuario = new GridBagConstraints();
		gbc_textDBusuario.insets = new Insets(0, 0, 5, 5);
		gbc_textDBusuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDBusuario.gridx = 2;
		gbc_textDBusuario.gridy = 1;
		add(textDBusuario, gbc_textDBusuario);
		textDBusuario.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Contraseña");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 2;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textDBpassword = new JTextField();
		GridBagConstraints gbc_textDBpassword = new GridBagConstraints();
		gbc_textDBpassword.insets = new Insets(0, 0, 5, 5);
		gbc_textDBpassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDBpassword.gridx = 2;
		gbc_textDBpassword.gridy = 2;
		add(textDBpassword, gbc_textDBpassword);
		textDBpassword.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Host");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 3;
		add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		textDBhost = new JTextField();
		textDBhost.setText("localhost");
		GridBagConstraints gbc_textDBhost = new GridBagConstraints();
		gbc_textDBhost.insets = new Insets(0, 0, 5, 5);
		gbc_textDBhost.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDBhost.gridx = 2;
		gbc_textDBhost.gridy = 3;
		add(textDBhost, gbc_textDBhost);
		textDBhost.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Puerto");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 4;
		add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		textDBpuerto = new JTextField();
		textDBpuerto.setText("3306");
		GridBagConstraints gbc_textDBpuerto = new GridBagConstraints();
		gbc_textDBpuerto.insets = new Insets(0, 0, 5, 5);
		gbc_textDBpuerto.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDBpuerto.gridx = 2;
		gbc_textDBpuerto.gridy = 4;
		add(textDBpuerto, gbc_textDBpuerto);
		textDBpuerto.setColumns(10);
		
		 btnDBCancelar = new JButton("Volver");
		GridBagConstraints gbc_btnDBCancelar = new GridBagConstraints();
		gbc_btnDBCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnDBCancelar.gridx = 1;
		gbc_btnDBCancelar.gridy = 5;
		add(btnDBCancelar, gbc_btnDBCancelar);
		
		JButton btnDBconectar = new JButton("Conectar");
		btnDBconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ConexionBD.obtenerNuevasCredenciales(textDBnombre.getText(), 
													textDBhost.getText(), 
													textDBusuario.getText(), 
													textDBpassword.getText(),
													textDBpuerto.getText());
			}
		});
		btnDBconectar.setPreferredSize(new Dimension(113, 23));
		GridBagConstraints gbc_btnDBconectar = new GridBagConstraints();
		gbc_btnDBconectar.insets = new Insets(0, 0, 0, 5);
		gbc_btnDBconectar.gridx = 2;
		gbc_btnDBconectar.gridy = 5;
		add(btnDBconectar, gbc_btnDBconectar);
		
		JButton btnDBprobarCon = new JButton("Probar Conexión");
		btnDBprobarCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensage="";
				if (ConexionBD.probarConexion()) {
					mensage="Hay conexion con la base de datos";
					
				} else {
					mensage= "No hay conexion con la base de datos";
					
				}
				JOptionPane.showMessageDialog(null,mensage);
				
			}
		});
		GridBagConstraints gbc_btnDBprobarCon = new GridBagConstraints();
		gbc_btnDBprobarCon.gridx = 3;
		gbc_btnDBprobarCon.gridy = 5;
		add(btnDBprobarCon, gbc_btnDBprobarCon);
		
		
	}
	public JButton getBtnDBCancelar() {
		return btnDBCancelar;
	}
	

}
