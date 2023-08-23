package com.farmacia.form;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import com.farmacia.controlador.BodegaDao;
import com.farmacia.entidades.Bodega;
import com.farmacia.entidades.Detalle;
import com.farmacia.utils.ControlFormatos;
import com.farmacia.utils.GenerdorDocumentos;
import java.awt.Toolkit;
import java.awt.Window.Type;

public class StockFrm extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textProductoCantidad;
	private JTextField textProductoCod;
	private JTextField textBodegaCod;
	private JTable tblProductos;
	private JButton btnBodegaAgregar,btnBodegaActualizar, btnBodegaEliminar, btnBodegaLimpiar, cancelButton ;
	private JTextField textBodegaCantBarras;
	private JButton btnBodegaPDF;
	private JLabel lblBodegaBarras;
	private int codProducto;
	private JDateChooser textProductoFechaE;
	private BodegaDao bodegaDao=new BodegaDao();
	 private ControlFormatos controlFormato=new ControlFormatos();
	 private JTextField textProductoCodbarras;
	 private JLabel lblProductoCodbarras;
	 private GenerdorDocumentos generarDocumento= new GenerdorDocumentos();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			StockFrm dialog = new StockFrm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public StockFrm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(StockFrm.class.getResource("/com/farmacia/icon/icon-producto.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				limpiarCampos();
				bodegaDao.searchProducto(codProducto, tblProductos);
			}
			@Override
			public void windowClosed(WindowEvent e) {
			}
		});
		setBounds(100, 100, 697, 384);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 112, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("Cantidad");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			textProductoCantidad = new JTextField();
			GridBagConstraints gbc_textProductoCantidad = new GridBagConstraints();
			gbc_textProductoCantidad.insets = new Insets(0, 0, 5, 5);
			gbc_textProductoCantidad.fill = GridBagConstraints.HORIZONTAL;
			gbc_textProductoCantidad.gridx = 1;
			gbc_textProductoCantidad.gridy = 0;
			contentPanel.add(textProductoCantidad, gbc_textProductoCantidad);
			textProductoCantidad.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Fecha Expiración");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 2;
			gbc_lblNewLabel_1.gridy = 0;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			 textProductoFechaE = new JDateChooser();
			textProductoFechaE.setDateFormatString("yyyy-MM-dd");
			GridBagConstraints gbc_textProductoFechaE = new GridBagConstraints();
			gbc_textProductoFechaE.insets = new Insets(0, 0, 5, 5);
			gbc_textProductoFechaE.fill = GridBagConstraints.BOTH;
			gbc_textProductoFechaE.gridx = 3;
			gbc_textProductoFechaE.gridy = 0;
			contentPanel.add(textProductoFechaE, gbc_textProductoFechaE);
		}
		{
			textProductoCod = new JTextField();
			textProductoCod.setVisible(false);
			GridBagConstraints gbc_textProductoCod = new GridBagConstraints();
			gbc_textProductoCod.insets = new Insets(0, 0, 5, 5);
			gbc_textProductoCod.fill = GridBagConstraints.HORIZONTAL;
			gbc_textProductoCod.gridx = 1;
			gbc_textProductoCod.gridy = 1;
			contentPanel.add(textProductoCod, gbc_textProductoCod);
			textProductoCod.setColumns(10);
		}
		{
			textBodegaCod = new JTextField();
			textBodegaCod.setVisible(false);
			GridBagConstraints gbc_textBodegaCod = new GridBagConstraints();
			gbc_textBodegaCod.insets = new Insets(0, 0, 5, 5);
			gbc_textBodegaCod.fill = GridBagConstraints.HORIZONTAL;
			gbc_textBodegaCod.gridx = 3;
			gbc_textBodegaCod.gridy = 1;
			contentPanel.add(textBodegaCod, gbc_textBodegaCod);
			textBodegaCod.setColumns(10);
		}
		{
			lblBodegaBarras = new JLabel("# código barras");
			lblBodegaBarras.setVisible(false);
			{
				textProductoCodbarras = new JTextField();
				textProductoCodbarras.setVisible(false);
				{
					lblProductoCodbarras = new JLabel("Cod. Barras");
					lblProductoCodbarras.setVisible(false);
					lblProductoCodbarras.setLabelFor(textProductoCodbarras);
					GridBagConstraints gbc_lblProductoCodbarras = new GridBagConstraints();
					gbc_lblProductoCodbarras.insets = new Insets(0, 0, 5, 5);
					gbc_lblProductoCodbarras.anchor = GridBagConstraints.EAST;
					gbc_lblProductoCodbarras.gridx = 0;
					gbc_lblProductoCodbarras.gridy = 2;
					contentPanel.add(lblProductoCodbarras, gbc_lblProductoCodbarras);
				}
				textProductoCodbarras.setEnabled(false);
				GridBagConstraints gbc_textProductoCodbarras = new GridBagConstraints();
				gbc_textProductoCodbarras.insets = new Insets(0, 0, 5, 5);
				gbc_textProductoCodbarras.fill = GridBagConstraints.HORIZONTAL;
				gbc_textProductoCodbarras.gridx = 1;
				gbc_textProductoCodbarras.gridy = 2;
				contentPanel.add(textProductoCodbarras, gbc_textProductoCodbarras);
				textProductoCodbarras.setColumns(10);
			}
			GridBagConstraints gbc_lblBodegaBarras = new GridBagConstraints();
			gbc_lblBodegaBarras.insets = new Insets(0, 0, 5, 5);
			gbc_lblBodegaBarras.anchor = GridBagConstraints.EAST;
			gbc_lblBodegaBarras.gridx = 2;
			gbc_lblBodegaBarras.gridy = 2;
			contentPanel.add(lblBodegaBarras, gbc_lblBodegaBarras);
		}
		{
			textBodegaCantBarras = new JTextField();
			textBodegaCantBarras.setVisible(false);
			GridBagConstraints gbc_textBodegaCantBarras = new GridBagConstraints();
			gbc_textBodegaCantBarras.insets = new Insets(0, 0, 5, 5);
			gbc_textBodegaCantBarras.fill = GridBagConstraints.HORIZONTAL;
			gbc_textBodegaCantBarras.gridx = 3;
			gbc_textBodegaCantBarras.gridy = 2;
			contentPanel.add(textBodegaCantBarras, gbc_textBodegaCantBarras);
			textBodegaCantBarras.setColumns(10);
		}
		{
			btnBodegaPDF = new JButton("");
			btnBodegaPDF.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser selectCarpeta=new JFileChooser();
					selectCarpeta.setCurrentDirectory(new File("."));
					selectCarpeta.setDialogTitle("Seleccionar la carpeta para guardar los archivos");
					selectCarpeta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					selectCarpeta.setAcceptAllFileFilterUsed(false);
					if (selectCarpeta.showOpenDialog(contentPanel)==JFileChooser.APPROVE_OPTION) {
						
						
						GenerdorDocumentos generador=new GenerdorDocumentos();
						if (!controlFormato.hayEspaciosVacios(textBodegaCantBarras.getText(),
								textProductoCantidad.getText())) {
							//String nomPro=textProductoNombre.getText()+" "+textProductoFormFarmaceutica.getText()+" "+textProductoPresentacion.getText()+" "+textProductoUmedida.getText();
							String nomPro="";
							generador.generarPDFs(textProductoCodbarras.getText(),Integer.parseInt(textBodegaCantBarras.getText()) ,selectCarpeta.getSelectedFile().toPath().toString(),nomPro);
						}else {
							msgbox("No hay cantidad de codigos de barras");
						}
						
					}
					
				}
			});
			btnBodegaPDF.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnBodegaPDF.setVisible(false);
			btnBodegaPDF.setIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/download-icon.png")));
			GridBagConstraints gbc_btnBodegaPDF = new GridBagConstraints();
			gbc_btnBodegaPDF.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnBodegaPDF.insets = new Insets(0, 0, 5, 5);
			gbc_btnBodegaPDF.gridx = 4;
			gbc_btnBodegaPDF.gridy = 2;
			
			contentPanel.add(btnBodegaPDF, gbc_btnBodegaPDF);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridheight = 5;
			gbc_scrollPane.gridwidth = 5;
			gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 3;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				tblProductos = new JTable();
				tblProductos.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int fila = tblProductos.rowAtPoint(e.getPoint());
				           
				         if(fila>=0)  {
				        	 limpiarCampos();
				        	 String key= tblProductos.getValueAt(fila, 0).toString();
						        Bodega ca=bodegaDao.searchBodegaId(Integer.parseInt(key));
						      if (ca!=null) {
						    	  textBodegaCantBarras.setVisible(true);
						    	  textProductoCantidad.setText(String.valueOf(ca.getCantidadIngresada()) );
									textProductoFechaE.setDate(controlFormato.toDate(ca.getFechaCaducidad()));
									textBodegaCod.setText(String.valueOf(ca.getCodBodega()));
									textProductoCodbarras.setText(ca.getCodigoBarra());
									
							        btnBodegaAgregar.setEnabled(false);
							        btnBodegaActualizar.setEnabled(true);
							        btnBodegaEliminar.setEnabled(true);
							        btnBodegaLimpiar.setVisible(true);
							        btnBodegaLimpiar.setEnabled(true);
							       lblBodegaBarras.setVisible(true);
							       btnBodegaPDF.setEnabled(true);
							       btnBodegaPDF.setVisible(true);
							       lblProductoCodbarras.setVisible(true);
							       textProductoCodbarras.setVisible(true);
							} 
						       
				         }
					}
				});
				
				tblProductos.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Cod","C\u00F3digo Barra"  ,"Cantidad"," Fecha de Ingreso", "Fecha Caducidad", "Caducidad (+90 dias)"
						}
					) {
						private static final long serialVersionUID = 1L;
						boolean[] columnEditables = new boolean[] {
							false, false, false, false, false,false
						};
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					});
				scrollPane.setViewportView(tblProductos);
			}
		}
		{
			
			 btnBodegaAgregar = new JButton("Agregar");
			btnBodegaAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					 if (textProductoFechaE.getDate()!=null) {
						 if (!controlFormato.hayEspaciosVacios(textProductoCantidad.getText()) ) {
							 bodegaDao.agregarBodega(new Bodega(Integer.parseInt(textProductoCantidad.getText()),
										controlFormato.fromDateToLocalDate(textProductoFechaE.getDate()),
										codProducto));
							 limpiarCampos();
							 bodegaDao.searchProducto(codProducto, tblProductos);
							}else {
								JOptionPane.showMessageDialog(null, "Hay un campo vacio");
							}
							
					 }else {
							JOptionPane.showMessageDialog(null, "Fecha de caducidad no es correcta");
						}
					
					
				}
			});
			GridBagConstraints gbc_btnBodegaAgregar = new GridBagConstraints();
			gbc_btnBodegaAgregar.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnBodegaAgregar.insets = new Insets(0, 0, 5, 0);
			gbc_btnBodegaAgregar.gridx = 5;
			gbc_btnBodegaAgregar.gridy = 4;
			contentPanel.add(btnBodegaAgregar, gbc_btnBodegaAgregar);
		}
		{
			 btnBodegaActualizar = new JButton("Actualizar");
			 btnBodegaActualizar.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		if (textProductoFechaE.getDate()!=null) {
			 			if (!controlFormato.hayEspaciosVacios(textProductoCantidad.getText(),textBodegaCod.getText()) ) {
							 bodegaDao.actualizarBodega(new Bodega(Integer.parseInt(textBodegaCod.getText()),
									 Integer.parseInt(textProductoCantidad.getText()),
										controlFormato.fromDateToLocalDate(textProductoFechaE.getDate())));
							 limpiarCampos();
							 bodegaDao.searchProducto(codProducto, tblProductos);
							}else {
								JOptionPane.showMessageDialog(null, "Hay un campo vacio");
							}
					}else {
						msgbox("Fecha no correcta");
					}
			 	}
			 });
			 btnBodegaActualizar.setEnabled(false);
			GridBagConstraints gbc_btnBodegaActualizar = new GridBagConstraints();
			gbc_btnBodegaActualizar.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnBodegaActualizar.insets = new Insets(0, 0, 5, 0);
			gbc_btnBodegaActualizar.gridx = 5;
			gbc_btnBodegaActualizar.gridy = 5;
			contentPanel.add(btnBodegaActualizar, gbc_btnBodegaActualizar);
		}
		{
			 btnBodegaEliminar = new JButton("Eliminar");
			 btnBodegaEliminar.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		if (!controlFormato.hayEspaciosVacios(textBodegaCod.getText()) ) {
						 bodegaDao.eliminarBodega(Integer.parseInt(textBodegaCod.getText()));
						 limpiarCampos();
						 
						 bodegaDao.searchProducto(codProducto, tblProductos);
						}else {
							JOptionPane.showMessageDialog(null, "Hay un campo vacio");
						}
			 		
			 	}
			 });
			 btnBodegaEliminar.setEnabled(false);
			GridBagConstraints gbc_btnBodegaEliminar = new GridBagConstraints();
			gbc_btnBodegaEliminar.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnBodegaEliminar.insets = new Insets(0, 0, 5, 0);
			gbc_btnBodegaEliminar.gridx = 5;
			gbc_btnBodegaEliminar.gridy = 6;
			contentPanel.add(btnBodegaEliminar, gbc_btnBodegaEliminar);
		}
		{
			 btnBodegaLimpiar = new JButton("Cancelar");
			 btnBodegaLimpiar.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		limpiarCampos();
			 	}
			 });
			 btnBodegaLimpiar.setVisible(false);
			 btnBodegaLimpiar.setEnabled(false);
			GridBagConstraints gbc_btnBodegaLimpiar = new GridBagConstraints();
			gbc_btnBodegaLimpiar.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnBodegaLimpiar.gridx = 5;
			gbc_btnBodegaLimpiar.gridy = 7;
			contentPanel.add(btnBodegaLimpiar, gbc_btnBodegaLimpiar);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				 cancelButton = new JButton("Salir");
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

	

	protected void cerrarVentana() {
		// TODO Auto-generated method stub
		limpiarCampos();
		this.dispose();
	}

	public void setCodProducto(int codProducto) {
		this.codProducto = codProducto;
	}

	public void limpiarCampos() {
		textBodegaCantBarras.setText("");
		textBodegaCod.setText("");
		textProductoCantidad.setText("");
		textProductoCod.setText("");
		textProductoFechaE.setDate(null);
		textProductoCodbarras.setText("");
		
		
		 btnBodegaAgregar.setEnabled(true);
	        btnBodegaActualizar.setEnabled(false);
	        btnBodegaEliminar.setEnabled(false);
	        btnBodegaLimpiar.setVisible(false);
	        btnBodegaLimpiar.setEnabled(false);
	       lblBodegaBarras.setVisible(false);
	       btnBodegaPDF.setVisible(false);
	       lblProductoCodbarras.setVisible(false);
	       textProductoCodbarras.setVisible(false);
	       textBodegaCantBarras.setVisible(false);
		
		}
			
	private void msgbox(String s){
		   JOptionPane.showMessageDialog(null, s);
		}

	public JButton getCancelButton() {
		return cancelButton;
	}

	
	
	
	
}
