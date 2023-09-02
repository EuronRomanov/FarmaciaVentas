package com.farmacia.form;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;

public class buscadorproductoForm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textBuscadorPalabra;
	private JTable tblBuscadorProducto;
	private JTable tblBuscadorBodega;
	private JTextField textField;
	private JTextField textBuscadorCodBodega;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			buscadorproductoForm dialog = new buscadorproductoForm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public buscadorproductoForm() {
		setBounds(100, 100, 1059, 462);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("Buscar");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			textBuscadorPalabra = new JTextField();
			GridBagConstraints gbc_textBuscadorPalabra = new GridBagConstraints();
			gbc_textBuscadorPalabra.gridwidth = 3;
			gbc_textBuscadorPalabra.insets = new Insets(0, 0, 5, 5);
			gbc_textBuscadorPalabra.fill = GridBagConstraints.HORIZONTAL;
			gbc_textBuscadorPalabra.gridx = 1;
			gbc_textBuscadorPalabra.gridy = 0;
			contentPanel.add(textBuscadorPalabra, gbc_textBuscadorPalabra);
			textBuscadorPalabra.setColumns(10);
		}
		{
			JButton btnBuscadorProducto = new JButton("Buscar");
			GridBagConstraints gbc_btnBuscadorProducto = new GridBagConstraints();
			gbc_btnBuscadorProducto.insets = new Insets(0, 0, 5, 0);
			gbc_btnBuscadorProducto.gridx = 4;
			gbc_btnBuscadorProducto.gridy = 0;
			contentPanel.add(btnBuscadorProducto, gbc_btnBuscadorProducto);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Cantidad");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 1;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			textField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 1;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(10);
		}
		{
			textBuscadorCodBodega = new JTextField();
			textBuscadorCodBodega.setVisible(false);
			GridBagConstraints gbc_textBuscadorCodBodega = new GridBagConstraints();
			gbc_textBuscadorCodBodega.insets = new Insets(0, 0, 5, 5);
			gbc_textBuscadorCodBodega.fill = GridBagConstraints.HORIZONTAL;
			gbc_textBuscadorCodBodega.gridx = 2;
			gbc_textBuscadorCodBodega.gridy = 1;
			contentPanel.add(textBuscadorCodBodega, gbc_textBuscadorCodBodega);
			textBuscadorCodBodega.setColumns(10);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridwidth = 4;
			gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 2;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				tblBuscadorProducto = new JTable();
				tblBuscadorProducto.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Cod", "Precio", "Cantidad", "Producto", "Forma Farmaceutica", "Categoria"
					}
				) {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					boolean[] columnEditables = new boolean[] {
						false, false, false, false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				tblBuscadorProducto.getColumnModel().getColumn(0).setPreferredWidth(36);
				tblBuscadorProducto.getColumnModel().getColumn(1).setPreferredWidth(44);
				tblBuscadorProducto.getColumnModel().getColumn(2).setPreferredWidth(53);
				tblBuscadorProducto.getColumnModel().getColumn(3).setPreferredWidth(242);
				tblBuscadorProducto.getColumnModel().getColumn(4).setPreferredWidth(71);
				tblBuscadorProducto.getColumnModel().getColumn(5).setPreferredWidth(57);
				scrollPane.setViewportView(tblBuscadorProducto);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 4;
			gbc_scrollPane.gridy = 2;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				tblBuscadorBodega = new JTable();
				tblBuscadorBodega.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Cod", "Info"
					}
				) {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					boolean[] columnEditables = new boolean[] {
						false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				scrollPane.setViewportView(tblBuscadorBodega);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
