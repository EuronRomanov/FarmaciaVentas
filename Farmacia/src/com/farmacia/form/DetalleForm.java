package com.farmacia.form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.farmacia.controlador.ProductoDao;
import com.farmacia.controlador.DetalleDao;

public class DetalleForm extends JFrame {

	private JPanel contentPane;
	private JTextField textDetalleCantidad;
	private JTextField textDetalleValor;
	private JTextField textDetalleCodCarrito;
	private JTable tblDetalles;
	private JComboBox cmbProductos;
    private ProductoDao productoDao=new ProductoDao();
    private DetalleDao detalleDao=new DetalleDao();
    private int facturaId;
    private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetalleForm frame = new DetalleForm();
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
	public DetalleForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				productoDao.cargarListaProductos(cmbProductos);
				detalleDao.listarDetalleTable(facturaId, tblDetalles);
				
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 702, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Cantidad");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		textDetalleCantidad = new JTextField();
		GridBagConstraints gbc_textDetalleCantidad = new GridBagConstraints();
		gbc_textDetalleCantidad.insets = new Insets(0, 0, 5, 5);
		gbc_textDetalleCantidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDetalleCantidad.gridx = 1;
		gbc_textDetalleCantidad.gridy = 0;
		contentPane.add(textDetalleCantidad, gbc_textDetalleCantidad);
		textDetalleCantidad.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Producto");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 0;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		 cmbProductos = new JComboBox();
		GridBagConstraints gbc_cmbProductos = new GridBagConstraints();
		gbc_cmbProductos.insets = new Insets(0, 0, 5, 5);
		gbc_cmbProductos.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbProductos.gridx = 3;
		gbc_cmbProductos.gridy = 0;
		contentPane.add(cmbProductos, gbc_cmbProductos);
		
		JLabel lblNewLabel_2 = new JLabel("Valor Pagar");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textDetalleValor = new JTextField();
		GridBagConstraints gbc_textDetalleValor = new GridBagConstraints();
		gbc_textDetalleValor.insets = new Insets(0, 0, 5, 5);
		gbc_textDetalleValor.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDetalleValor.gridx = 1;
		gbc_textDetalleValor.gridy = 1;
		contentPane.add(textDetalleValor, gbc_textDetalleValor);
		textDetalleValor.setColumns(10);
		
		textDetalleCodCarrito = new JTextField();
		textDetalleCodCarrito.setVisible(false);
		GridBagConstraints gbc_textDetalleCodCarrito = new GridBagConstraints();
		gbc_textDetalleCodCarrito.insets = new Insets(0, 0, 5, 5);
		gbc_textDetalleCodCarrito.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDetalleCodCarrito.gridx = 3;
		gbc_textDetalleCodCarrito.gridy = 1;
		contentPane.add(textDetalleCodCarrito, gbc_textDetalleCodCarrito);
		textDetalleCodCarrito.setColumns(10);
		
		textField = new JTextField();
		textField.setVisible(false);
		GridBagConstraints textDetalleCodFactura = new GridBagConstraints();
		textDetalleCodFactura.insets = new Insets(0, 0, 5, 5);
		textDetalleCodFactura.fill = GridBagConstraints.HORIZONTAL;
		textDetalleCodFactura.gridx = 3;
		textDetalleCodFactura.gridy = 2;
		contentPane.add(textField, textDetalleCodFactura);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		tblDetalles = new JTable();
		tblDetalles.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo",  "Producto", "Cantidad",  "Precio","Valor Total"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblDetalles.getColumnModel().getColumn(0).setPreferredWidth(111);
		tblDetalles.getColumnModel().getColumn(1).setPreferredWidth(147);
		tblDetalles.getColumnModel().getColumn(2).setPreferredWidth(241);
		tblDetalles.getColumnModel().getColumn(3).setPreferredWidth(153);
		scrollPane.setViewportView(tblDetalles);
		
		JButton btnDetalleAgregar = new JButton("Agregar");
		GridBagConstraints gbc_btnDetalleAgregar = new GridBagConstraints();
		gbc_btnDetalleAgregar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDetalleAgregar.insets = new Insets(0, 0, 5, 0);
		gbc_btnDetalleAgregar.gridx = 4;
		gbc_btnDetalleAgregar.gridy = 4;
		contentPane.add(btnDetalleAgregar, gbc_btnDetalleAgregar);
		
		JButton btnVentasActualizar = new JButton("Actualizar");
		GridBagConstraints gbc_btnVentasActualizar = new GridBagConstraints();
		gbc_btnVentasActualizar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnVentasActualizar.insets = new Insets(0, 0, 5, 0);
		gbc_btnVentasActualizar.gridx = 4;
		gbc_btnVentasActualizar.gridy = 5;
		contentPane.add(btnVentasActualizar, gbc_btnVentasActualizar);
		
		JButton btnDetalleEliminar = new JButton("Eliminar");
		GridBagConstraints gbc_btnDetalleEliminar = new GridBagConstraints();
		gbc_btnDetalleEliminar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDetalleEliminar.insets = new Insets(0, 0, 5, 0);
		gbc_btnDetalleEliminar.gridx = 4;
		gbc_btnDetalleEliminar.gridy = 6;
		contentPane.add(btnDetalleEliminar, gbc_btnDetalleEliminar);
		
		JButton btnDetalleCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnDetalleCancelar = new GridBagConstraints();
		gbc_btnDetalleCancelar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDetalleCancelar.gridx = 4;
		gbc_btnDetalleCancelar.gridy = 7;
		contentPane.add(btnDetalleCancelar, gbc_btnDetalleCancelar);
	}

	public int getFacturaId() {
		return facturaId;
	}

	public void setFacturaId(int facturaId) {
		this.facturaId = facturaId;
	}

}
