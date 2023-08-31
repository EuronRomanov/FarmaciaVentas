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
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.farmacia.controlador.ProductoDao;
import com.farmacia.controlador.VentaDao;
import com.farmacia.entidades.Bodega;
import com.farmacia.entidades.Detalle;
import com.farmacia.entidades.Producto;
import com.farmacia.utils.ControlFormatos;
import com.farmacia.controlador.BodegaDao;
import com.farmacia.controlador.DetalleDao;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;

public class DetalleForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textDetalleCantidad;
	private JTextField textDetalleValor;
	private JTextField textDetalleCodCarrito;
	private JTable tblDetalles;
	private JComboBox<Producto> cmbProductos;
    private ProductoDao productoDao=new ProductoDao();
    private DetalleDao detalleDao=new DetalleDao();
    private BodegaDao bodegaDao=new BodegaDao();
    private int facturaId;
    private JTextField textDetalleCodFac;
    private JButton btnDetalleCancelar, btnDetalleAgregar, btnDetalleActualizar,btnDetalleEliminar;
    private JTextField textDetallePrecio;
    private ControlFormatos controlFormato=new ControlFormatos();
    private JButton btnCancelar;
    private JLabel lblValorPagar;
    private VentaDao ventaDao=new VentaDao();
    private JTextField textDetallecodBodega;
    private JLabel lblDetalleCodBodega;
    private JComboBox<Bodega> cmbBodega;
   
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(DetalleForm2.class.getResource("/com/farmacia/icon/icon-producto.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				//productoDao.cargarListaProductos(cmbProductos);
				detalleDao.listarDetalleTable(facturaId, tblDetalles);
				System.out.println("activated");
				
			}
			@Override
			public void windowClosed(WindowEvent e) {
				limpiarCamposDetalle();
				habilitarBotonAgregar();
			}
			@Override
			public void windowOpened(WindowEvent e) {
				productoDao.cargarListaProductos(cmbProductos);
				System.out.println("open");
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 726, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Cantidad");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		textDetalleCantidad = new JTextField();
		textDetalleCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!textDetalleCantidad.getText().isEmpty() && !textDetallePrecio.getText().isEmpty()) {
					double precio=Double.parseDouble(textDetallePrecio.getText());
					double cantidad=Double.parseDouble(textDetalleCantidad.getText());
					textDetalleValor.setText(new ControlFormatos().redondearDosDecimales(precio*cantidad));
					//textDetalleValor.requestFocus();
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				int key=e.getKeyChar();
				
				
				if (controlFormato.validarNumerosEnteros(key)) {
					e.consume();
				}
			}
		});
		textDetalleCantidad.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
			}
			@Override
			public void focusGained(FocusEvent e) {
				
				
				
			}
		});
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
		
		 cmbProductos = new JComboBox<Producto>();
		 cmbProductos.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		Producto producto=(Producto)cmbProductos.getSelectedItem();
		 	bodegaDao.cargarProductosBodegasById(cmbBodega,producto.getCodProducto());
		 	}
		 });
		 cmbProductos.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		
		 	}
		 });
		GridBagConstraints gbc_cmbProductos = new GridBagConstraints();
		gbc_cmbProductos.insets = new Insets(0, 0, 5, 5);
		gbc_cmbProductos.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbProductos.gridx = 3;
		gbc_cmbProductos.gridy = 0;
		contentPane.add(cmbProductos, gbc_cmbProductos);
		
		 lblValorPagar = new JLabel("Valor Pagar");
		 lblValorPagar.setVisible(false);
		
		textDetalleCodFac = new JTextField();
		textDetalleCodFac.setVisible(false);
		GridBagConstraints gbc_textDetalleCodFac = new GridBagConstraints();
		gbc_textDetalleCodFac.insets = new Insets(0, 0, 5, 0);
		gbc_textDetalleCodFac.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDetalleCodFac.gridx = 4;
		gbc_textDetalleCodFac.gridy = 0;
		contentPane.add(textDetalleCodFac, gbc_textDetalleCodFac);
		textDetalleCodFac.setColumns(10);
		GridBagConstraints gbc_lblValorPagar = new GridBagConstraints();
		gbc_lblValorPagar.insets = new Insets(0, 0, 5, 5);
		gbc_lblValorPagar.anchor = GridBagConstraints.EAST;
		gbc_lblValorPagar.gridx = 0;
		gbc_lblValorPagar.gridy = 1;
		contentPane.add(lblValorPagar, gbc_lblValorPagar);
		
		textDetalleValor = new JTextField();
		textDetalleValor.setEnabled(false);
		textDetalleValor.setVisible(false);
		GridBagConstraints gbc_textDetalleValor = new GridBagConstraints();
		gbc_textDetalleValor.insets = new Insets(0, 0, 5, 5);
		gbc_textDetalleValor.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDetalleValor.gridx = 1;
		gbc_textDetalleValor.gridy = 1;
		contentPane.add(textDetalleValor, gbc_textDetalleValor);
		textDetalleValor.setColumns(10);
		
		textDetallePrecio = new JTextField();
		textDetallePrecio.setVisible(false);
		
		textDetallecodBodega = new JTextField();
		textDetallecodBodega.setVisible(false);
		
		lblDetalleCodBodega = new JLabel("Inf. Bodega");
		GridBagConstraints gbc_lblDetalleCodBodega = new GridBagConstraints();
		gbc_lblDetalleCodBodega.anchor = GridBagConstraints.EAST;
		gbc_lblDetalleCodBodega.insets = new Insets(0, 0, 5, 5);
		gbc_lblDetalleCodBodega.gridx = 2;
		gbc_lblDetalleCodBodega.gridy = 1;
		contentPane.add(lblDetalleCodBodega, gbc_lblDetalleCodBodega);
		
		cmbBodega = new JComboBox<Bodega>();
		cmbBodega.setModel(new DefaultComboBoxModel(new String[] {"Producto en bodega"}));
		GridBagConstraints gbc_cmbBodega = new GridBagConstraints();
		gbc_cmbBodega.insets = new Insets(0, 0, 5, 5);
		gbc_cmbBodega.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbBodega.gridx = 3;
		gbc_cmbBodega.gridy = 1;
		contentPane.add(cmbBodega, gbc_cmbBodega);
		GridBagConstraints gbc_textDetallecodBodega = new GridBagConstraints();
		gbc_textDetallecodBodega.insets = new Insets(0, 0, 5, 0);
		gbc_textDetallecodBodega.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDetallecodBodega.gridx = 4;
		gbc_textDetallecodBodega.gridy = 1;
		contentPane.add(textDetallecodBodega, gbc_textDetallecodBodega);
		textDetallecodBodega.setColumns(10);
		GridBagConstraints gbc_textDetallePrecio = new GridBagConstraints();
		gbc_textDetallePrecio.insets = new Insets(0, 0, 5, 5);
		gbc_textDetallePrecio.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDetallePrecio.gridx = 1;
		gbc_textDetallePrecio.gridy = 2;
		contentPane.add(textDetallePrecio, gbc_textDetallePrecio);
		textDetallePrecio.setColumns(10);
		
		textDetalleCodCarrito = new JTextField();
		textDetalleCodCarrito.setVisible(false);
		GridBagConstraints gbc_textDetalleCodCarrito = new GridBagConstraints();
		gbc_textDetalleCodCarrito.insets = new Insets(0, 0, 5, 0);
		gbc_textDetalleCodCarrito.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDetalleCodCarrito.gridx = 4;
		gbc_textDetalleCodCarrito.gridy = 2;
		contentPane.add(textDetalleCodCarrito, gbc_textDetalleCodCarrito);
		textDetalleCodCarrito.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 6;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		tblDetalles = new JTable();
		tblDetalles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tblDetalles.rowAtPoint(e.getPoint());
		           
		         if(fila>=0)  {
		        	 limpiarCamposDetalle();
		        	 String key= tblDetalles.getValueAt(fila, 0).toString();
				        Detalle ca=detalleDao.searchDetalleId(Integer.parseInt(key));
				      if (ca!=null) {
				    	  textDetalleValor.setVisible(true);
				    	  textDetalleCantidad.setText(String.valueOf(ca.getCantidad()) );
							textDetalleCodCarrito.setText(String.valueOf(ca.getCodCarrito()));
							textDetalleValor.setText(String.valueOf(ca.getV_total()));
							textDetalleCodFac.setText(String.valueOf(ca.getCodFactura()));
							cmbProductos.setSelectedIndex(buscarIdComboProducto(ca.getCodCodProductoBodega()));
					       textDetallePrecio.setText(tblDetalles.getValueAt(fila, 3).toString());
					       textDetallecodBodega.setText(String.valueOf(ca.getCodProducto()));
					       cmbBodega.setSelectedIndex(buscarIdComboBodega(ca.getCodProducto()));
					       
					       
					        btnDetalleAgregar.setEnabled(false);
					        btnDetalleActualizar.setEnabled(true);
					        btnDetalleEliminar.setEnabled(true);
					        btnCancelar.setVisible(true);
					       lblValorPagar.setVisible(true);
					       cmbProductos.setEnabled(false);
					} else {
                        msgbox("Ese producto fue borrado");
					}
				       
		         }
			}
		});
		tblDetalles.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo",  "Producto", "Cantidad",  "Precio","Valor Total"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
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
		
		
		
		 btnDetalleAgregar = new JButton("Agregar");
		 btnDetalleAgregar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		Producto p=(Producto)cmbProductos.getSelectedItem();
		 		if (!controlFormato.hayEspaciosVacios(textDetalleCantidad.getText()) ) {
		 		//	int codProducto=p.getCodProducto();
		 			int codProducto=Integer.parseInt(textDetallecodBodega.getText());
			 		int cantidad=Integer.parseInt(textDetalleCantidad.getText());
			 		double valor=Double.valueOf(cantidad)*p.getPrecioVenta();
			 		
			 		Detalle de=detalleDao.existeDetalle(p.getNombreProducto(),tblDetalles);
			 		if (de==null && Integer.parseInt(textDetalleCantidad.getText())!=0) {
			 			ventaDao.agregarDetalle(new Detalle( cantidad,  codProducto, valor,  facturaId));
					} else if(de!=null && Integer.parseInt(textDetalleCantidad.getText())!=0){
						//int idc=new ProductoDao().searchCanitdadProductoId(codProducto);
						
						ventaDao.agregarDetalleExistente(new Detalle( de.getCodCarrito(),(cantidad+de.getCantidad()),  codProducto, (valor+de.getV_total()),  facturaId));
					}
			 		
			 		detalleDao.listarDetalleTable(facturaId, tblDetalles);
			 		limpiarCamposDetalle();
			 		
			 		
			 		 
				} else {
					msgbox("Campo Vacio");
				}
		 		
		 		
		 	}
		 });
		GridBagConstraints gbc_btnDetalleAgregar = new GridBagConstraints();
		gbc_btnDetalleAgregar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDetalleAgregar.insets = new Insets(0, 0, 5, 0);
		gbc_btnDetalleAgregar.gridx = 4;
		gbc_btnDetalleAgregar.gridy = 4;
		contentPane.add(btnDetalleAgregar, gbc_btnDetalleAgregar);
		
		 btnDetalleActualizar = new JButton("Actualizar");
		 btnDetalleActualizar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		Producto p=(Producto)cmbProductos.getSelectedItem();
		 		if (!controlFormato.hayEspaciosVacios(textDetalleCantidad.getText(),textDetalleCodCarrito.getText()) ) {
		 			//int codProducto=p.getCodProducto();
		 			int codProducto=Integer.parseInt(textDetallecodBodega.getText());
			 		int cantidad=Integer.parseInt(textDetalleCantidad.getText());
			 		double valor=Double.valueOf(cantidad)*p.getPrecioVenta();
			 		
			 		
			 		 if(Integer.parseInt(textDetalleCantidad.getText())!=0){
						ventaDao.actualizarDetalle(new Detalle( Integer.parseInt(textDetalleCodCarrito.getText()),cantidad,  codProducto, (valor),  facturaId));
					}
			 		
			 		detalleDao.listarDetalleTable(facturaId, tblDetalles);
			 		limpiarCamposDetalle();
			 		habilitarBotonAgregar();
			 		 
				} else {
					msgbox("Campo Vacio");
				}
		 		
		 	}
		 });
		 btnDetalleActualizar.setEnabled(false);
		GridBagConstraints gbc_btnDetalleActualizar = new GridBagConstraints();
		gbc_btnDetalleActualizar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDetalleActualizar.insets = new Insets(0, 0, 5, 0);
		gbc_btnDetalleActualizar.gridx = 4;
		gbc_btnDetalleActualizar.gridy = 5;
		contentPane.add(btnDetalleActualizar, gbc_btnDetalleActualizar);
		
		 btnDetalleEliminar = new JButton("Eliminar");
		 btnDetalleEliminar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		if (textDetalleCodCarrito.getText().length()>0) {
					detalleDao.deleteProducto(Integer.parseInt(textDetalleCodCarrito.getText()));
					limpiarCamposDetalle();
					habilitarBotonAgregar();
					detalleDao.listarDetalleTable(facturaId, tblDetalles);
					
				}
		 	}
		 });
		 btnDetalleEliminar.setEnabled(false);
		GridBagConstraints gbc_btnDetalleEliminar = new GridBagConstraints();
		gbc_btnDetalleEliminar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDetalleEliminar.insets = new Insets(0, 0, 5, 0);
		gbc_btnDetalleEliminar.gridx = 4;
		gbc_btnDetalleEliminar.gridy = 6;
		contentPane.add(btnDetalleEliminar, gbc_btnDetalleEliminar);
		 
		 btnCancelar = new JButton("Cancelar");
		 btnCancelar.setVisible(false);
		 btnCancelar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		limpiarCamposDetalle();
		 		habilitarBotonAgregar();
		 	}
		 });
		 GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		 gbc_btnCancelar.fill = GridBagConstraints.HORIZONTAL;
		 gbc_btnCancelar.insets = new Insets(0, 0, 5, 0);
		 gbc_btnCancelar.gridx = 4;
		 gbc_btnCancelar.gridy = 7;
		 contentPane.add(btnCancelar, gbc_btnCancelar);
		
		 btnDetalleCancelar = new JButton("Salir");
		 btnDetalleCancelar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 	}
		 });
		GridBagConstraints gbc_btnDetalleCancelar = new GridBagConstraints();
		gbc_btnDetalleCancelar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDetalleCancelar.gridx = 4;
		gbc_btnDetalleCancelar.gridy = 8;
		contentPane.add(btnDetalleCancelar, gbc_btnDetalleCancelar);
	}

	protected void limpiarCamposDetalle() {
		// TODO Auto-generated method stub
		textDetalleCantidad.setText("");
		textDetalleCodCarrito.setText("");
		textDetalleValor.setText("");
		textDetalleCodFac.setText("");
		textDetallePrecio.setText("");
		textDetallecodBodega.setText("");
		cmbProductos.setSelectedIndex(0);
		cmbBodega.setSelectedIndex(0);
		
		btnDetalleAgregar.setEnabled(true);
        btnDetalleActualizar.setEnabled(false);
        btnDetalleEliminar.setEnabled(false);
        btnCancelar.setVisible(false);
       lblValorPagar.setVisible(false);
       cmbProductos.setEnabled(true);
       textDetalleValor.setVisible(false);
		
	}

	public int getFacturaId() {
		return facturaId;
	}
 
	public void setFacturaId(int facturaId) {
		this.facturaId = facturaId;
	}

	protected int buscarIdComboProducto(int j) {

        int y=-1;
         for (int i = 0; i < cmbProductos.getItemCount(); i++) {
			 Producto p=(Producto)cmbProductos.getItemAt(i);
			 if (p.getCodProducto()==j) {
				 y=i;
				break;
			}
		}
		return y;
	}
	
	
	protected int buscarIdComboBodega(int j) {

        int y=-1;
         for (int i = 0; i < cmbBodega.getItemCount(); i++) {
			 Bodega p=(Bodega)cmbBodega.getItemAt(i);
			 if (p.getCodBodega()==j) {
				 y=i;
				break;
			}
		}
		return y;
	}

	
	private void msgbox(String s){
		   JOptionPane.showMessageDialog(null, s);
		}

	public JButton getBtnDetalleCancelar() {
		return btnDetalleCancelar;
	}

	public void setBtnDetalleCancelar(JButton btnDetalleCancelar) {
		this.btnDetalleCancelar = btnDetalleCancelar;
	}
	
	private void habilitarBotonAgregar(){
		 btnDetalleAgregar.setEnabled(true);
	        btnDetalleActualizar.setEnabled(false);
	        btnDetalleEliminar.setEnabled(false);
	       btnCancelar.setVisible(false);
	       lblValorPagar.setVisible(false);
	       textDetalleValor.setVisible(false);
	       cmbProductos.setEnabled(true);
	        
	}
	
	public void cerrarVentana() {
		// TODO Auto-generated method stub
		limpiarCamposDetalle();
		this.dispose();
	}
	
}
