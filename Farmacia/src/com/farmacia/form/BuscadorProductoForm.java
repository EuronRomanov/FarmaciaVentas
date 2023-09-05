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
import javax.swing.RowFilter;

import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.farmacia.controlador.BodegaDao;
import com.farmacia.controlador.ProductoDao;
import com.farmacia.utils.ControlFormatos;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.Toolkit;

public class BuscadorProductoForm extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textBuscadorPalabra;
	private JTable tblBuscadorProducto = new JTable();;
	private JTable tblBuscadorBodega;
	private JTextField textBuscadorCantidad;
	private JTextField textBuscadorCodBodega;
	private ProductoDao productoDao=new ProductoDao();
	private BodegaDao bodegaDao=new BodegaDao();
	private TableRowSorter<TableModel> sorter ;
	private JButton cancelButton;
	private JButton okButton;
	private String key;
	private int cantidad ;
    private ControlFormatos controlFormato=new ControlFormatos();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscadorProductoForm dialog = new BuscadorProductoForm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BuscadorProductoForm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuscadorProductoForm.class.getResource("/com/farmacia/icon/icon-producto.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				productoDao.cargarDatosTblBuscadorProducto(tblBuscadorProducto);
				 sorter = new TableRowSorter<TableModel>( tblBuscadorProducto.getModel());
		  		 tblBuscadorProducto.setRowSorter(sorter);
			}
			@Override
			public void windowOpened(WindowEvent e) {
				productoDao.cargarDatosTblBuscadorProducto(tblBuscadorProducto);
				 sorter = new TableRowSorter<TableModel>( tblBuscadorProducto.getModel());
		  		 tblBuscadorProducto.setRowSorter(sorter);
			}
		});
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
			textBuscadorPalabra.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
				}
			});
			textBuscadorPalabra.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					String text = textBuscadorPalabra.getText();
					 if (text.trim().length() == 0) {
		                    sorter.setRowFilter(null);
		                } else {
		                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
		                }
					
				}
			});
			
			
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
			JLabel lblNewLabel_1 = new JLabel("Cantidad");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 1;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			textBuscadorCantidad = new JTextField();
			textBuscadorCantidad.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					int key=e.getKeyChar();
					
					
					if (controlFormato.validarNumerosEnteros(key)) {
						e.consume();
					}
				}
			});
			GridBagConstraints gbc_textBuscadorCantidad = new GridBagConstraints();
			gbc_textBuscadorCantidad.insets = new Insets(0, 0, 5, 5);
			gbc_textBuscadorCantidad.fill = GridBagConstraints.HORIZONTAL;
			gbc_textBuscadorCantidad.gridx = 1;
			gbc_textBuscadorCantidad.gridy = 1;
			contentPanel.add(textBuscadorCantidad, gbc_textBuscadorCantidad);
			textBuscadorCantidad.setColumns(10);
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
				
				
				tblBuscadorProducto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				
				tblBuscadorProducto.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						int fila = tblBuscadorProducto.rowAtPoint(e.getPoint());
						
						 if(fila>=0)  {
							 String key= tblBuscadorProducto.getValueAt(fila, 0).toString();
							 bodegaDao.cargarProductosBodegasById(tblBuscadorBodega, Integer.parseInt(key));
						 }
					}
				});
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
				tblBuscadorBodega.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int fila = tblBuscadorBodega.rowAtPoint(e.getPoint());
						
						 if(fila>=0)  {
							 String key= tblBuscadorBodega.getValueAt(fila, 0).toString();
							 textBuscadorCodBodega.setText(key);
						 }
					}
				});
				tblBuscadorBodega.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"cod", "Info"
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
				tblBuscadorBodega.getColumnModel().getColumn(0).setPreferredWidth(41);
				tblBuscadorBodega.getColumnModel().getColumn(1).setPreferredWidth(200);
				scrollPane.setViewportView(tblBuscadorBodega);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				 okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if (!controlFormato.hayEspaciosVacios(textBuscadorCodBodega.getText(),textBuscadorCantidad.getText())) {
							key=textBuscadorCodBodega.getText();
							cantidad=Integer.parseInt(textBuscadorCantidad.getText());
							//System.out.println("Datos almacenados"+key + "  "+ cantidad);
							cerrarVentana();
						}else {
							msgbox("Hay campos vacios");
						}
						
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				 cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cerrarVentana();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void cerrarVentana() {
		// TODO Auto-generated method stub
		limpiarCampos();
		this.dispose();
	}

	private void limpiarCampos() {
		// TODO Auto-generated method stub
		textBuscadorCodBodega.setText("");
		textBuscadorPalabra.setText("");
		textBuscadorPalabra.setText("");
		
	}

	public JTextField getTextBuscadorCantidad() {
		return textBuscadorCantidad;
	}

	public JTextField getTextBuscadorCodBodega() {
		return textBuscadorCodBodega;
	}

	

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getCantidad() {
		return cantidad;
	}
	private void msgbox(String s){
		   JOptionPane.showMessageDialog(null, s);
		}
	

}
