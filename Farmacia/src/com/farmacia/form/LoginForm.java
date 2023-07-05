package com.farmacia.form;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class LoginForm extends JFrame {

	private JPanel contentPane;
	private PnlLogin pnlLogin=new PnlLogin();
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 515, 316);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnlLogin);
		pnlLogin.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		pnlLogin.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{100, 0, 55, 123, 0};
		gbl_panel.rowHeights = new int[]{50, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnLoginApp = new JButton("Ingresar a App");
		btnLoginApp.setMinimumSize(new Dimension(200, 50));
		btnLoginApp.setMaximumSize(new Dimension(300, 50));
		GridBagConstraints gbc_btnLoginApp = new GridBagConstraints();
		gbc_btnLoginApp.anchor = GridBagConstraints.SOUTH;
		gbc_btnLoginApp.insets = new Insets(0, 0, 5, 5);
		gbc_btnLoginApp.gridx = 2;
		gbc_btnLoginApp.gridy = 3;
		panel.add(btnLoginApp, gbc_btnLoginApp);
		btnLoginApp.setPreferredSize(new Dimension(200, 50));
		
		JLabel lblNewLabel = new JLabel("-O-");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 4;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Ingresar a Base Datos");
		btnNewButton_1.setMinimumSize(new Dimension(200, 50));
		btnNewButton_1.setMaximumSize(new Dimension(300, 50));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 5;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		btnNewButton_1.setPreferredSize(new Dimension(200, 50));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{424, 0};
		gbl_contentPane.rowHeights = new int[]{251, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
	}

	
	public void escalarLabel(JLabel label,String rutaImagen) {
	}
}
