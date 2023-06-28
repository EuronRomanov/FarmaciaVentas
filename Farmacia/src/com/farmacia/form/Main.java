package com.farmacia.form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.Cursor;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.farmacia.entidades.Categoria;
import com.farmacia.entidades.Producto;
import com.farmacia.entidades.Proveedor;
import com.farmacia.entidades.Usuario;
import com.farmacia.utils.ControlFormatos;
import com.farmacia.utils.GenerdorDocumentos;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.farmacia.controlador.*;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import java.awt.TextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;


public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField textBuscarCategoria;
	private JTable tblCategoria;
	private JTabbedPane tabPane_Vistas;
    private ArrayList<JButton> listEdit, listDelete;
	JPanel pnl_factura = new JPanel();
	JPanel pnl_consultas = new JPanel();
    
    private JPanel pnl_usuarios = new JPanel();
    private JPanel pnl_producto = new JPanel();
    private JPanel pnl_categoria = new JPanel();
    private JPanel pnl_proveedor = new JPanel();
    
    JPanel pnl_ventas = new JPanel();
	JPanel pnl_caja = new JPanel();
    
    private CategoriaDao categoriaDao=new CategoriaDao();
    private ProveedorDao proveedorDao=new ProveedorDao();
    private ControlFormatos controlFormato=new ControlFormatos();
    private ProductoDao productoDao=new ProductoDao();
    private VentaDao ventaDao=new VentaDao();
    private UsuarioDao usuarioDao=new UsuarioDao();
    
    private JTextField textNombreCategoria;
    private JTextField textCodCategoria;
    private DefaultTableModel modelo = new DefaultTableModel();
    private DefaultTableModel tmp = new DefaultTableModel();
    private JButton btnAgregarCategoria,btnCancelar,btnActualizarCategoria, btnEliminarCategoria;
    private JButton btnAgregarProveedor,btnModificarProveedor,btnEliminarProveedor,btnCancelarProveedor,btnBuscarProveedor;
    private boolean flagAdministrador=false;
    private JTable tblProveedores;
    private JTextField textBuscarProveedor;
    private JTextField textNombreProveedor;
    private JTextField textNombreRepresentante;
    private JTextField textCelularproveedor;
    private JTextField textTelefonoProveedor;
    private JTextField textDireccionProveedor;
    private JTextField textCodProveedor;
    private JTextField textRucProveedor;
    private JTable tblProductos;
    private JTextField textProductoNombre;
    private JTextField textProductoUmedida;
    private JTextField textProductoPresentacion;
    private JTextField textProductoMarca;
    private JTextField textProductoFRegistro;
    private JTextField textProductoCantidad;
    private JTextField textProductoBuscar;
    private JTextField textProductoCodigo;
    private JTextField textProductoCodBarra;
    private JTextField textProductoFormFarmaceutica;
    private JTextArea textProductoObservacion ;
    private JComboBox cmbProductoProveedor,cmbProductoCategoria;
    private JDateChooser textProductoFCaducidad;
    private JFormattedTextField textProductoPcompra, textProductoPventa;
    private  JButton btnProductoAgregar,btnProductoBuscar,btnProductoModificar,btnProductoEliminar,btnProductoCancelar ;
    private JButton btnVolverCategoriaProducto;
    private JButton btnProveedorRegresarProducto;
    private JTextField textProductoCantCodBarras;
    private JLabel lblProductoCantCodBarras;
    private JButton btnProductoDescarga;
    private JTextField textVentasCodProd;
    private JLabel label;
    private JLabel label_1;
    private JPanel pnlVentasLadoDerecho;
    private JScrollPane scrollPane_3;
    private JTable tblVentas;
    private JLabel lblVentasTotal;
    private JLabel lblTotalpagar;
    private JPanel panel_6;
    private JTextField textVentasCliente;
    private JLabel lblNewLabel_16;
    private JLabel lblNewLabel_19;
    private JTextField textVentasRuc;
    private JTextField textVentasCedula;
    JTextArea textVentasObservaciones;
    private JLabel lblNewLabel_22;
    private JLabel lblNewLabel_23;
    private JTextField textVendedorUsuario;
    private JTextField textVendedorPassword;
    private JLabel lblNewLabel_25;
    private JTextField textVendedorCedula;
    private JComboBox cmbVendedorEstado;
    private JComboBox cmbVendedorAdmin;
    private JLabel lblNewLabel_26;
    private JLabel lblNewLabel_27;
    private JTextField textVendedorCodigo;
    private JScrollPane scrollPane_4;
    private JTable tblUsuarios;
    private JTextField textVendedorBuscar;
    private JButton btnVendedorBuscar;
    private JButton btnVendedorCancelar;
    private JButton btnVendedorEliminar;
    private JButton btnVendedorModificar;
    private JButton btnVendendorAgregar;
	
    /**
	 * Launch the application. 
	 * author : 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1304, 607);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnUsuario = new JMenu("Usuario");
		menuBar.add(mnUsuario);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Cerrar sesión");
		mntmNewMenuItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmNewMenuItem.setIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/logout_icon_32.png")));
		mnUsuario.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(200, 400));
		panel.setMinimumSize(new Dimension(200, 400));
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);

		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel middlePanel = new JPanel();
		middlePanel.setOpaque(false);
		middlePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		middlePanel.setLayout(new FlowLayout(4,4,4));
		
		JPanel gridPanel=new JPanel();
		gridPanel.setBorder(new EmptyBorder(40, 0, 0, 0));
		gridPanel.setLayout(new GridLayout(5,1,5,5));
		
		middlePanel.add(gridPanel);
		
		JButton btnProducto = new JButton("Producto");
		btnProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//limpiarTableCategoria();
				//categoriaDao.ListarCategoriaTable(tblCategoria);
				//listarCategoriaTable();
				//limpiarTableProveedor();
				//proveedorDao.ListarProveedorTable(tblProveedores);
				
				
				
				eliminarTab("Ventas");
				tabPane_Vistas.addTab("Productos", null, pnl_producto, null);
				tabPane_Vistas.setSelectedIndex(tabPane_Vistas.indexOfTab("Productos"));
				productoDao.ListarProductoTable(tblProductos);
				proveedorDao.cargarListaProveedor(cmbProductoProveedor);
				categoriaDao.cargarListaCategorias(cmbProductoCategoria);
				
				
				
				
				
				
			}
		});
		btnProducto.setIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/icon-producto.png")));
		btnProducto.setBorder(new LineBorder(new Color(238, 238, 238)));
		btnProducto.setBackground(new Color(233, 233, 233));
		btnProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnProducto.setBackground(new Color(174,214, 241));
			}
			
			public void mouseExited(MouseEvent e) {
				btnProducto.setBackground(new Color(240, 240, 240));
			}
		});
		btnProducto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		gridPanel.add(btnProducto);
		
		JButton btnUsuario = new JButton("Usuarios");
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarTab("Productos");
				eliminarTab("Ventas");
				eliminarTab("Categorias");
				eliminarTab("Proveedores");
				tabPane_Vistas.addTab("Usuarios", null, pnl_usuarios, null);
				tabPane_Vistas.setBackgroundAt(2, new Color(214, 214, 214));
				tabPane_Vistas.setSelectedIndex(tabPane_Vistas.indexOfTab("Usuarios"));
				
				usuarioDao.listarUsuarioTable(tblUsuarios);
				
				
			}
		});
		btnUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuario.setSelectedIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/users_icon-32.png")));
		btnUsuario.setIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/users_icon-48.png")));
		btnUsuario.setBorder(new LineBorder(new Color(238, 238, 238)));
		btnUsuario.setBackground(new Color(233, 233, 233));
		btnUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnUsuario.setBackground(new Color(174,214, 241));
			}
			
			public void mouseExited(MouseEvent e) {
				btnUsuario.setBackground(new Color(240, 240, 240));
			}
		});
		gridPanel.add(btnUsuario);
		
		JButton btnFactura = new JButton("Facturas");
		btnFactura.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFactura.setSelectedIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/payment_icon_32.png")));
		btnFactura.setIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/payment_icon_48.png")));
		btnFactura.setBorder(new LineBorder(new Color(238, 238, 238)));
		btnFactura.setBackground(new Color(233, 233, 233));
		btnFactura.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnFactura.setBackground(new Color(174,214, 241));
			}
			
			public void mouseExited(MouseEvent e) {
				btnFactura.setBackground(new Color(240, 240, 240));
			}
		});
		gridPanel.add(btnFactura);
		
		JButton btnCaja = new JButton("Caja");
		btnCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarAdministrador();
			}
		});
		btnCaja.setSelectedIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/cashier_icon-32.png")));
		btnCaja.setIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/cashier_icon-48.png")));
		btnCaja.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCaja.setBorder(new LineBorder(new Color(238, 238, 238)));
		btnCaja.setBackground(new Color(233, 233, 233));
		btnCaja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCaja.setBackground(new Color(174,214, 241));
			}
			
			public void mouseExited(MouseEvent e) {
				btnCaja.setBackground(new Color(240, 240, 240));
			}
		});
		gridPanel.add(btnCaja);
		
		JButton btnVentas = new JButton("Ventas");
		btnVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarTab("Categorias");
				eliminarTab("Proveedores");
				eliminarTab("Productos");
				tabPane_Vistas.addTab("Ventas", null, pnl_ventas, null);
				pnl_ventas.setLayout(new BorderLayout(0, 0));
				tabPane_Vistas.setSelectedIndex(tabPane_Vistas.indexOfTab("Ventas"));
		 		textVentasCodProd.requestFocus();
			}
		});
		btnVentas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVentas.setSelectedIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/compra-icon-34.png")));
		btnVentas.setIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/compra-icon-48.png")));
		btnVentas.setBorder(new LineBorder(new Color(238, 238, 238)));
		btnVentas.setBackground(new Color(233, 233, 233));
		btnVentas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnVentas.setBackground(new Color(174,214, 241));
				

			}
			
			

			public void mouseExited(MouseEvent e) {
				btnVentas.setBackground(new Color(240, 240, 240));
			}
		});
		gridPanel.add(btnVentas);
		contentPane.add(middlePanel, BorderLayout.WEST);
		
		JPanel centralPanel = new JPanel();
		contentPane.add(centralPanel, BorderLayout.CENTER);
		centralPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		 tabPane_Vistas = new JTabbedPane(JTabbedPane.TOP);
		centralPanel.add(tabPane_Vistas);
		
		
		/*
		 * Categoria
		 */
		
		
       
        
        
		
		
		GridBagLayout gbl_pnl_categoria = new GridBagLayout();
		gbl_pnl_categoria.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnl_categoria.rowHeights = new int[]{0, 0, 0, 0};
		gbl_pnl_categoria.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_pnl_categoria.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		pnl_categoria.setLayout(gbl_pnl_categoria);
		
		textBuscarCategoria = new JTextField();
		textBuscarCategoria.setPreferredSize(new Dimension(400, 40));
		GridBagConstraints gbc_textBuscarCategoria = new GridBagConstraints();
		gbc_textBuscarCategoria.gridwidth = 19;
		gbc_textBuscarCategoria.insets = new Insets(0, 0, 5, 5);
		gbc_textBuscarCategoria.fill = GridBagConstraints.HORIZONTAL;
		gbc_textBuscarCategoria.gridx = 1;
		gbc_textBuscarCategoria.gridy = 0;
		pnl_categoria.add(textBuscarCategoria, gbc_textBuscarCategoria);
		textBuscarCategoria.setColumns(10);
		
		JButton btnBuscarCaatgoria = new JButton("Buscar");
		btnBuscarCaatgoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textBuscarCategoria.getText().length()>0) {
					categoriaDao.searchCategoria(textBuscarCategoria.getText(), tblCategoria);
				}else {
					//limpiarTableCategoria();
					categoriaDao.ListarCategoriaTable(tblCategoria);
					//listarCategoriaTable();
				}
			}
		});
		btnBuscarCaatgoria.setPreferredSize(new Dimension(117, 40));
		GridBagConstraints gbc_btnBuscarCaatgoria = new GridBagConstraints();
		gbc_btnBuscarCaatgoria.gridwidth = 2;
		gbc_btnBuscarCaatgoria.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscarCaatgoria.gridx = 21;
		gbc_btnBuscarCaatgoria.gridy = 0;
		pnl_categoria.add(btnBuscarCaatgoria, gbc_btnBuscarCaatgoria);
		
		tblCategoria = new JTable();
		tblCategoria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				/* if ("".equals(textCodCategoria.getText())) {
			            JOptionPane.showMessageDialog(null, "Seleecione una fila");
			        } else {*/
			        	limpiarCamposCategoria();
			        	int fila = tblCategoria.rowAtPoint(e.getPoint());
			           
			         if(fila>=0)  {
			        	 String key= tblCategoria.getValueAt(fila, 0).toString();
					        Categoria ca=categoriaDao.searchCategoriaId(Integer.parseInt(key));
					        textCodCategoria.setText(String.valueOf(ca.getCodCategoria()  ));
					        textNombreCategoria.setText(ca.getNombreCategoria());
					        
					       // btnAgregarCategoria.setVisible(false);
					        btnAgregarCategoria.setEnabled(false);
					        btnActualizarCategoria.setEnabled(true);
					        btnEliminarCategoria.setEnabled(true);
					        btnCancelar.setEnabled(true);
					        btnCancelar.setVisible(true);
			         }
			        	
			      //  }
				
			}
		});
		Object[][] data=new Object[][] {
		};
		String [] columnas=new String[] {
				"Codigo", "Nombre"
			};
		tblCategoria.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nombre"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblCategoria.getColumnModel().getColumn(0).setPreferredWidth(332);
		tblCategoria.getColumnModel().getColumn(1).setPreferredWidth(293);
		GridBagConstraints gbc_tblCategoria = new GridBagConstraints();
		JScrollPane scrollPane_2 = new JScrollPane();
		gbc_tblCategoria.gridwidth = 22;
		gbc_tblCategoria.insets = new Insets(0, 0, 0, 5);
		gbc_tblCategoria.fill = GridBagConstraints.BOTH;
		gbc_tblCategoria.gridx = 0;
		gbc_tblCategoria.gridy = 2;
		pnl_categoria.add(scrollPane_2, gbc_tblCategoria);
		scrollPane_2.setViewportView(tblCategoria);
	
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 3;
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 22;
		gbc_panel_2.gridy = 2;
		pnl_categoria.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{172, 0, 0};
		gbl_panel_2.rowHeights = new int[]{20, 30, 56, 40, 40, 40, 40, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		textCodCategoria = new JTextField();
		
		textCodCategoria.setToolTipText("");
		textCodCategoria.setEnabled(false);
		textCodCategoria.setSelectedTextColor(new Color(235, 235, 235));
		textCodCategoria.setBorder(null);
		textCodCategoria.setVisible(false);
		GridBagConstraints gbc_textCodCategoria = new GridBagConstraints();
		gbc_textCodCategoria.anchor = GridBagConstraints.NORTH;
		gbc_textCodCategoria.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCodCategoria.insets = new Insets(0, 0, 5, 5);
		gbc_textCodCategoria.gridx = 0;
		gbc_textCodCategoria.gridy = 0;
		panel_2.add(textCodCategoria, gbc_textCodCategoria);
		textCodCategoria.setColumns(10);
		
		 btnAgregarCategoria = new JButton("Agregar");
		btnAgregarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String [] campos=new String[1]; 
				campos[0]=textNombreCategoria.getText();
				
				
				if (!controlFormato.hayEspaciosVacios(campos)) {
					categoriaDao.registrarCategoria(new Categoria(textNombreCategoria.getText()));
					//limpiarTableCategoria();
					categoriaDao.ListarCategoriaTable(tblCategoria);
					limpiarCamposCategoria();
					//listarCategoriaTable();
					
				}else {
					JOptionPane.showMessageDialog(null, "Hay un campo vacio");
				}
				
			}

			
		});
		
		textNombreCategoria = new JTextField();
		textNombreCategoria.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key=e.getKeyChar();
				
				
				if (controlFormato.validarSoloLetras(key)) {
					e.consume();
				}
			
			}
		});
		textNombreCategoria.setToolTipText("");
		textNombreCategoria.setPreferredSize(new Dimension(7, 30));
		GridBagConstraints gbc_textNombreCategoria = new GridBagConstraints();
		gbc_textNombreCategoria.gridwidth = 2;
		gbc_textNombreCategoria.anchor = GridBagConstraints.NORTH;
		gbc_textNombreCategoria.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombreCategoria.insets = new Insets(0, 0, 5, 0);
		gbc_textNombreCategoria.gridx = 0;
		gbc_textNombreCategoria.gridy = 1;
		panel_2.add(textNombreCategoria, gbc_textNombreCategoria);
		textNombreCategoria.setColumns(10);
		GridBagConstraints gbc_btnAgregarCategoria = new GridBagConstraints();
		gbc_btnAgregarCategoria.gridwidth = 2;
		gbc_btnAgregarCategoria.anchor = GridBagConstraints.NORTH;
		gbc_btnAgregarCategoria.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAgregarCategoria.insets = new Insets(0, 0, 5, 0);
		gbc_btnAgregarCategoria.gridx = 0;
		gbc_btnAgregarCategoria.gridy = 3;
		panel_2.add(btnAgregarCategoria, gbc_btnAgregarCategoria);
		btnAgregarCategoria.setPreferredSize(new Dimension(117, 40));
		
		 btnEliminarCategoria = new JButton("Eliminar");
		 btnEliminarCategoria.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		if (textCodCategoria.getText().length()>0) {
		 			categoriaDao.deleteCategoria(Integer.parseInt(textCodCategoria.getText()));
			 		btnAgregarCategoria.setEnabled(true);
					btnActualizarCategoria.setEnabled(false);
			 		btnEliminarCategoria.setEnabled(false);
			        btnCancelar.setEnabled(false);
			        btnCancelar.setVisible(false);
			        
			        limpiarCamposCategoria();
			        categoriaDao.ListarCategoriaTable(tblCategoria);
			        //limpiarTableCategoria();
			       //listarCategoriaTable();
				}
		 		
		 	}
		 });
		btnEliminarCategoria.setEnabled(false);
		btnEliminarCategoria.setOpaque(true);
		GridBagConstraints gbc_btnEliminarCategoria = new GridBagConstraints();
		gbc_btnEliminarCategoria.gridwidth = 2;
		gbc_btnEliminarCategoria.anchor = GridBagConstraints.NORTH;
		gbc_btnEliminarCategoria.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEliminarCategoria.insets = new Insets(0, 0, 5, 0);
		gbc_btnEliminarCategoria.gridx = 0;
		gbc_btnEliminarCategoria.gridy = 4;
		panel_2.add(btnEliminarCategoria, gbc_btnEliminarCategoria);
		btnEliminarCategoria.setPreferredSize(new Dimension(117, 40));
		
		 btnActualizarCategoria = new JButton("Modificar");
		btnActualizarCategoria.setEnabled(false);
		btnActualizarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String categoriaActualizar=textNombreCategoria.getText();
				if (categoriaActualizar.length()>0) {
					Categoria ca=new Categoria(Integer.parseInt(textCodCategoria.getText()), textNombreCategoria.getText());
					categoriaDao.editCategoria(ca);
					//limpiarTableCategoria();
					categoriaDao.ListarCategoriaTable(tblCategoria);
					//listarCategoriaTable();
				} else {

				}
				
				btnAgregarCategoria.setEnabled(true);
				btnActualizarCategoria.setEnabled(false);
		 		btnEliminarCategoria.setEnabled(false);
		        btnCancelar.setEnabled(false);
		        btnCancelar.setVisible(false);
		        
		        limpiarCamposCategoria();
				
			}
		});
		GridBagConstraints gbc_btnActualizarCategoria = new GridBagConstraints();
		gbc_btnActualizarCategoria.gridwidth = 2;
		gbc_btnActualizarCategoria.anchor = GridBagConstraints.NORTH;
		gbc_btnActualizarCategoria.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnActualizarCategoria.insets = new Insets(0, 0, 5, 0);
		gbc_btnActualizarCategoria.gridx = 0;
		gbc_btnActualizarCategoria.gridy = 5;
		panel_2.add(btnActualizarCategoria, gbc_btnActualizarCategoria);
		btnActualizarCategoria.setPreferredSize(new Dimension(117, 40));
		
		 btnCancelar = new JButton("Cancelar");
		 btnCancelar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		btnAgregarCategoria.setEnabled(true);
		 		btnActualizarCategoria.setEnabled(false);
		 		btnEliminarCategoria.setEnabled(false);
		        btnCancelar.setEnabled(false);
		        btnCancelar.setVisible(false);
		        limpiarCamposCategoria();
		 	}
		 });
		 btnCancelar.setPreferredSize(new Dimension(170, 40));
		 btnCancelar.setVisible(false);
		 btnCancelar.setOpaque(true);
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 0);
		gbc_btnCancelar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancelar.gridwidth = 2;
		gbc_btnCancelar.gridx = 0;
		gbc_btnCancelar.gridy = 6;
		panel_2.add(btnCancelar, gbc_btnCancelar);
		
		btnVolverCategoriaProducto = new JButton("Volver");
		btnVolverCategoriaProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabPane_Vistas.addTab("Productos", null, pnl_producto, null);
				tabPane_Vistas.remove(tabPane_Vistas.indexOfTab("Categoria"));
				
				productoDao.ListarProductoTable(tblProductos);
				proveedorDao.cargarListaProveedor(cmbProductoProveedor);
				categoriaDao.cargarListaCategorias(cmbProductoCategoria);
			}
		});
		GridBagConstraints gbc_btnVolverCategoriaProducto = new GridBagConstraints();
		gbc_btnVolverCategoriaProducto.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnVolverCategoriaProducto.gridwidth = 2;
		gbc_btnVolverCategoriaProducto.insets = new Insets(0, 0, 0, 5);
		gbc_btnVolverCategoriaProducto.gridx = 0;
		gbc_btnVolverCategoriaProducto.gridy = 7;
		panel_2.add(btnVolverCategoriaProducto, gbc_btnVolverCategoriaProducto);
		
		
		
		
		/*
		 * ---------------categorias------------
		 */
	/*	
		if (flagAdministrador==false)  {
			
			tabPane_Vistas.addTab("Ventas", null, pnl_ventas, null);
			
			
			tabPane_Vistas.addTab("Caja", null, pnl_caja, null);
		}*/
		pnl_ventas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textVentasCodProd.requestFocus();
			}
		});
		
		tabPane_Vistas.addTab("Ventas", null, pnl_ventas, null);
		pnl_ventas.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		pnl_ventas.add(panel_4, BorderLayout.NORTH);
		
		textVentasCodProd = new JTextField();
		textVentasCodProd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
			
			}
			@Override
			public void focusLost(FocusEvent e) {if (!textVentasCodProd.getText().equalsIgnoreCase("")) {
				productoDao.agregarProductoProCodigo(textVentasCodProd.getText(), tblVentas);
				textVentasCodProd.setText("");
				ventaDao.totalizar(tblVentas,lblTotalpagar);
				textVentasCodProd.requestFocus();
			}// JOptionPane.showMessageDialog(null, "El bastior ya se encuentra registrado en la base de datos","Duplicado",JOptionPane.ERROR_MESSAGE);
                return;
			}
		});
		textVentasCodProd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key=e.getKeyChar();
				
				
				if (controlFormato.validarNumerosEnteros(key)) {
					e.consume();
				}
			}
		});
		panel_4.add(textVentasCodProd);
		textVentasCodProd.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Agregar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (	textVentasCodProd.getText().length()>10) {
					productoDao.agregarProductoProCodigo(textVentasCodProd.getText(), tblVentas);
					textVentasCodProd.setText("");
					ventaDao.totalizar(tblVentas, lblTotalpagar);
					textVentasCodProd.requestFocus();
				}
				
			}
		});
		panel_4.add(btnNewButton_1);
		
		JPanel panel_5 = new JPanel();
		pnl_ventas.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new GridLayout(0, 6, 0, 0));
		
		label = new JLabel("");
		panel_5.add(label);
		
		label_1 = new JLabel("");
		panel_5.add(label_1);
		
		 lblVentasTotal = new JLabel("Total");
		lblVentasTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_5.add(lblVentasTotal);
		
		lblTotalpagar = new JLabel("0,00");
		lblTotalpagar.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalpagar.setFont(new Font("Times New Roman", Font.BOLD, 21));
		panel_5.add(lblTotalpagar);
		
		pnlVentasLadoDerecho = new JPanel();
		pnlVentasLadoDerecho.setMinimumSize(new Dimension(200, 200));
		pnlVentasLadoDerecho.setPreferredSize(new Dimension(200, 200));
		pnl_ventas.add(pnlVentasLadoDerecho, BorderLayout.EAST);
		pnlVentasLadoDerecho.setLayout(null);
		
		panel_6 = new JPanel();
		panel_6.setBounds(10, 11, 186, 374);
		pnlVentasLadoDerecho.add(panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[] {2, 46, 86, 0};
		gbl_panel_6.rowHeights = new int[]{0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_6.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);
		
		lblNewLabel_16 = new JLabel("Cliente");
		GridBagConstraints gbc_lblNewLabel_16 = new GridBagConstraints();
		gbc_lblNewLabel_16.gridwidth = 3;
		gbc_lblNewLabel_16.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_16.gridx = 0;
		gbc_lblNewLabel_16.gridy = 0;
		panel_6.add(lblNewLabel_16, gbc_lblNewLabel_16);
		
		textVentasCliente = new JTextField();
		GridBagConstraints gbc_textVentasCliente = new GridBagConstraints();
		gbc_textVentasCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_textVentasCliente.gridwidth = 3;
		gbc_textVentasCliente.insets = new Insets(0, 0, 5, 0);
		gbc_textVentasCliente.anchor = GridBagConstraints.NORTH;
		gbc_textVentasCliente.gridx = 0;
		gbc_textVentasCliente.gridy = 1;
		panel_6.add(textVentasCliente, gbc_textVentasCliente);
		textVentasCliente.setColumns(10);
		
		lblNewLabel_19 = new JLabel("RUC");
		GridBagConstraints gbc_lblNewLabel_19 = new GridBagConstraints();
		gbc_lblNewLabel_19.gridwidth = 3;
		gbc_lblNewLabel_19.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_19.gridx = 0;
		gbc_lblNewLabel_19.gridy = 2;
		panel_6.add(lblNewLabel_19, gbc_lblNewLabel_19);
		
		textVentasRuc = new JTextField();
		GridBagConstraints gbc_textVentasRuc = new GridBagConstraints();
		gbc_textVentasRuc.gridwidth = 3;
		gbc_textVentasRuc.insets = new Insets(0, 0, 5, 0);
		gbc_textVentasRuc.fill = GridBagConstraints.HORIZONTAL;
		gbc_textVentasRuc.gridx = 0;
		gbc_textVentasRuc.gridy = 3;
		panel_6.add(textVentasRuc, gbc_textVentasRuc);
		textVentasRuc.setColumns(10);
		
		JLabel lblNewLabel_20 = new JLabel("Cedula");
		GridBagConstraints gbc_lblNewLabel_20 = new GridBagConstraints();
		gbc_lblNewLabel_20.gridwidth = 3;
		gbc_lblNewLabel_20.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_20.gridx = 0;
		gbc_lblNewLabel_20.gridy = 4;
		panel_6.add(lblNewLabel_20, gbc_lblNewLabel_20);
		
		textVentasCedula = new JTextField();
		GridBagConstraints gbc_textVentasCedula = new GridBagConstraints();
		gbc_textVentasCedula.gridwidth = 3;
		gbc_textVentasCedula.insets = new Insets(0, 0, 5, 0);
		gbc_textVentasCedula.fill = GridBagConstraints.HORIZONTAL;
		gbc_textVentasCedula.gridx = 0;
		gbc_textVentasCedula.gridy = 5;
		panel_6.add(textVentasCedula, gbc_textVentasCedula);
		textVentasCedula.setColumns(10);
		
		JLabel lblNewLabel_21 = new JLabel("Observaciones");
		GridBagConstraints gbc_lblNewLabel_21 = new GridBagConstraints();
		gbc_lblNewLabel_21.gridwidth = 3;
		gbc_lblNewLabel_21.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_21.gridx = 0;
		gbc_lblNewLabel_21.gridy = 6;
		panel_6.add(lblNewLabel_21, gbc_lblNewLabel_21);
		
		textVentasObservaciones = new JTextArea();
		GridBagConstraints gbc_textVentasObservaciones = new GridBagConstraints();
		gbc_textVentasObservaciones.gridwidth = 3;
		gbc_textVentasObservaciones.gridheight = 3;
		gbc_textVentasObservaciones.insets = new Insets(0, 0, 5, 0);
		gbc_textVentasObservaciones.fill = GridBagConstraints.BOTH;
		gbc_textVentasObservaciones.gridx = 0;
		gbc_textVentasObservaciones.gridy = 7;
		panel_6.add(textVentasObservaciones, gbc_textVentasObservaciones);
		
		JButton btnVentasFinalizar = new JButton("Finalizar");
		btnVentasFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				limpiarCamposVentas();
			}
		});
		GridBagConstraints gbc_btnVentasFinalizar = new GridBagConstraints();
		gbc_btnVentasFinalizar.anchor = GridBagConstraints.SOUTH;
		gbc_btnVentasFinalizar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnVentasFinalizar.gridwidth = 3;
		gbc_btnVentasFinalizar.insets = new Insets(0, 0, 5, 0);
		gbc_btnVentasFinalizar.gridx = 0;
		gbc_btnVentasFinalizar.gridy = 12;
		panel_6.add(btnVentasFinalizar, gbc_btnVentasFinalizar);
		
		JButton btnVentasCancelar = new JButton("Cancelar");
		btnVentasCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCamposVentas();
			}
		});
		GridBagConstraints gbc_btnVentasCancelar = new GridBagConstraints();
		gbc_btnVentasCancelar.anchor = GridBagConstraints.SOUTH;
		gbc_btnVentasCancelar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnVentasCancelar.gridwidth = 3;
		gbc_btnVentasCancelar.gridx = 0;
		gbc_btnVentasCancelar.gridy = 13;
		panel_6.add(btnVentasCancelar, gbc_btnVentasCancelar);
		
		scrollPane_3 = new JScrollPane();
		pnl_ventas.add(scrollPane_3, BorderLayout.CENTER);
		
		tblVentas = new JTable();
		tblVentas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int column = tblVentas.getColumnModel().getColumnIndexAtX(e.getX());
				 int row    = e.getY()/tblVentas.getRowHeight();
				 if (row < tblVentas.getRowCount() && row >= 0  && column < tblVentas.getColumnCount() && column >= 0)  {
	                 Object value = tblVentas.getValueAt(row, column);
	                 if (value instanceof JButton) {
	                     //perform a click event
	                     ((JButton)value).doClick();
	                     JButton botones=(JButton)value;
	                     if (botones.getName().equals("btnEliminarVenta")) {
							//JOptionPane.showMessageDialog(null, "Elimino");
	                    	 productoDao.eliminarFilaJTable(row, tblVentas);
	                    	 //ventaDao.totalizar(tblVentas, textVentasTotal);
	                    	 ventaDao.totalizar(tblVentas, lblTotalpagar);
	                    	 if (tblVentas.getRowCount()==0) {
								pnlVentasLadoDerecho.setVisible(false);
							}
	                    	 textVentasCodProd.requestFocus();
						}
	                 }
	             }
			}
		});
		tblVentas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Producto", "Precio por Unidad", "Cantidad", "Acci\u00F3n"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false,  false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblVentas.getColumnModel().getColumn(0).setPreferredWidth(126);
		tblVentas.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblVentas.getColumnModel().getColumn(2).setPreferredWidth(128);
		tblVentas.getColumnModel().getColumn(3).setPreferredWidth(127);
		tblVentas.getColumnModel().getColumn(4).setPreferredWidth(95);
		scrollPane_3.setViewportView(tblVentas);
		 tabPane_Vistas.add("Usuarios", pnl_usuarios);
		 GridBagLayout gbl_pnl_usuarios = new GridBagLayout();
		 gbl_pnl_usuarios.columnWidths = new int[]{88, 88, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		 gbl_pnl_usuarios.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		 gbl_pnl_usuarios.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		 gbl_pnl_usuarios.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		 pnl_usuarios.setLayout(gbl_pnl_usuarios);
		 
		 lblNewLabel_22 = new JLabel("Nombre Y Apellidos");
		 GridBagConstraints gbc_lblNewLabel_22 = new GridBagConstraints();
		 gbc_lblNewLabel_22.anchor = GridBagConstraints.WEST;
		 gbc_lblNewLabel_22.insets = new Insets(0, 0, 5, 5);
		 gbc_lblNewLabel_22.gridx = 0;
		 gbc_lblNewLabel_22.gridy = 0;
		 pnl_usuarios.add(lblNewLabel_22, gbc_lblNewLabel_22);
		 
		 textVendedorUsuario = new JTextField();
		 textVendedorUsuario.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyTyped(KeyEvent e) {
		 		int key=e.getKeyChar();
				
				
				if (controlFormato.validarSoloLetras(key)) {
					e.consume();
				}
		 	}
		 });
		 GridBagConstraints gbc_textVendedorUsuario = new GridBagConstraints();
		 gbc_textVendedorUsuario.insets = new Insets(0, 0, 5, 5);
		 gbc_textVendedorUsuario.fill = GridBagConstraints.HORIZONTAL;
		 gbc_textVendedorUsuario.gridx = 1;
		 gbc_textVendedorUsuario.gridy = 0;
		 pnl_usuarios.add(textVendedorUsuario, gbc_textVendedorUsuario);
		 textVendedorUsuario.setColumns(10);
		 
		 lblNewLabel_26 = new JLabel("Estado");
		 GridBagConstraints gbc_lblNewLabel_26 = new GridBagConstraints();
		 gbc_lblNewLabel_26.insets = new Insets(0, 0, 5, 5);
		 gbc_lblNewLabel_26.anchor = GridBagConstraints.WEST;
		 gbc_lblNewLabel_26.gridx = 3;
		 gbc_lblNewLabel_26.gridy = 0;
		 pnl_usuarios.add(lblNewLabel_26, gbc_lblNewLabel_26);
		 
		 cmbVendedorEstado = new JComboBox();
		 cmbVendedorEstado.setModel(new DefaultComboBoxModel(new String[] {"Activo", "Desactivado"}));
		 GridBagConstraints gbc_cmbVendedorEstado = new GridBagConstraints();
		 gbc_cmbVendedorEstado.insets = new Insets(0, 0, 5, 5);
		 gbc_cmbVendedorEstado.fill = GridBagConstraints.HORIZONTAL;
		 gbc_cmbVendedorEstado.gridx = 4;
		 gbc_cmbVendedorEstado.gridy = 0;
		 pnl_usuarios.add(cmbVendedorEstado, gbc_cmbVendedorEstado);
		 
		 lblNewLabel_23 = new JLabel("Contraseña");
		 GridBagConstraints gbc_lblNewLabel_23 = new GridBagConstraints();
		 gbc_lblNewLabel_23.anchor = GridBagConstraints.WEST;
		 gbc_lblNewLabel_23.insets = new Insets(0, 0, 5, 5);
		 gbc_lblNewLabel_23.gridx = 0;
		 gbc_lblNewLabel_23.gridy = 2;
		 pnl_usuarios.add(lblNewLabel_23, gbc_lblNewLabel_23);
		 
		 textVendedorPassword = new JTextField();
		 GridBagConstraints gbc_textVendedorPassword = new GridBagConstraints();
		 gbc_textVendedorPassword.insets = new Insets(0, 0, 5, 5);
		 gbc_textVendedorPassword.fill = GridBagConstraints.HORIZONTAL;
		 gbc_textVendedorPassword.gridx = 1;
		 gbc_textVendedorPassword.gridy = 2;
		 pnl_usuarios.add(textVendedorPassword, gbc_textVendedorPassword);
		 textVendedorPassword.setColumns(10);
		 
		 lblNewLabel_27 = new JLabel("Administrador");
		 GridBagConstraints gbc_lblNewLabel_27 = new GridBagConstraints();
		 gbc_lblNewLabel_27.insets = new Insets(0, 0, 5, 5);
		 gbc_lblNewLabel_27.anchor = GridBagConstraints.WEST;
		 gbc_lblNewLabel_27.gridx = 3;
		 gbc_lblNewLabel_27.gridy = 2;
		 pnl_usuarios.add(lblNewLabel_27, gbc_lblNewLabel_27);
		 
		 cmbVendedorAdmin = new JComboBox();
		 cmbVendedorAdmin.setModel(new DefaultComboBoxModel(new String[] {"No", "Si"}));
		 GridBagConstraints gbc_cmbVendedorAdmin = new GridBagConstraints();
		 gbc_cmbVendedorAdmin.insets = new Insets(0, 0, 5, 5);
		 gbc_cmbVendedorAdmin.fill = GridBagConstraints.HORIZONTAL;
		 gbc_cmbVendedorAdmin.gridx = 4;
		 gbc_cmbVendedorAdmin.gridy = 2;
		 pnl_usuarios.add(cmbVendedorAdmin, gbc_cmbVendedorAdmin);
		 
		 lblNewLabel_25 = new JLabel("Cedula");
		 GridBagConstraints gbc_lblNewLabel_25 = new GridBagConstraints();
		 gbc_lblNewLabel_25.anchor = GridBagConstraints.WEST;
		 gbc_lblNewLabel_25.insets = new Insets(0, 0, 5, 5);
		 gbc_lblNewLabel_25.gridx = 0;
		 gbc_lblNewLabel_25.gridy = 4;
		 pnl_usuarios.add(lblNewLabel_25, gbc_lblNewLabel_25);
		 
		 textVendedorCodigo = new JTextField();
		 textVendedorCodigo.setVisible(false);
		 
		 textVendedorCedula = new JTextField();
		 textVendedorCedula.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyTyped(KeyEvent e) {
		 		int key=e.getKeyChar();
				
				
				if (controlFormato.validarNumerosEnteros(key) ||textVendedorCedula.getText().length()>9 ) {
					e.consume();
				}
		 	}
		 });
		 GridBagConstraints gbc_textVendedorCedula = new GridBagConstraints();
		 gbc_textVendedorCedula.insets = new Insets(0, 0, 5, 5);
		 gbc_textVendedorCedula.fill = GridBagConstraints.HORIZONTAL;
		 gbc_textVendedorCedula.gridx = 1;
		 gbc_textVendedorCedula.gridy = 4;
		 pnl_usuarios.add(textVendedorCedula, gbc_textVendedorCedula);
		 textVendedorCedula.setColumns(10);
		 GridBagConstraints gbc_textVendedorCodigo = new GridBagConstraints();
		 gbc_textVendedorCodigo.insets = new Insets(0, 0, 5, 5);
		 gbc_textVendedorCodigo.fill = GridBagConstraints.HORIZONTAL;
		 gbc_textVendedorCodigo.gridx = 4;
		 gbc_textVendedorCodigo.gridy = 4;
		 pnl_usuarios.add(textVendedorCodigo, gbc_textVendedorCodigo);
		 textVendedorCodigo.setColumns(10);
		 
		 scrollPane_4 = new JScrollPane();
		 GridBagConstraints gbc_scrollPane_4 = new GridBagConstraints();
		 gbc_scrollPane_4.gridheight = 9;
		 gbc_scrollPane_4.gridwidth = 6;
		 gbc_scrollPane_4.insets = new Insets(0, 0, 0, 5);
		 gbc_scrollPane_4.fill = GridBagConstraints.BOTH;
		 gbc_scrollPane_4.gridx = 0;
		 gbc_scrollPane_4.gridy = 6;
		 pnl_usuarios.add(scrollPane_4, gbc_scrollPane_4);
		 
		 tblUsuarios = new JTable();
		 tblUsuarios.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		
		 		limpiarCamposUsuario();
	        	int fila = tblUsuarios.rowAtPoint(e.getPoint());
	           
	         if(fila>=0)  {
	        	 String key= tblUsuarios.getValueAt(fila, 0).toString();
	        	 Usuario usuario=usuarioDao.searchUsuarioId(key);
	        	 textVendedorUsuario.setText(usuario.getNombre());
	     		textVendedorCedula.setText(usuario.getCedula());
	     		
	     		textVendedorCodigo.setText(String.valueOf(usuario.getCodUsuario()) );
	     		cmbVendedorAdmin.setSelectedIndex(usuario.getAdministrador());
	     		cmbVendedorEstado.setSelectedIndex(usuario.getEstado());
	     		
	     		btnVendendorAgregar.setEnabled(false);
	     		btnVendedorModificar.setEnabled(true);
	     		btnVendedorEliminar.setEnabled(true);
	     		btnVendedorCancelar.setEnabled(true);
	     		btnVendedorCancelar.setVisible(true);
	         }
		 	}
		 });
		 tblUsuarios.setModel(new DefaultTableModel(
		 	new Object[][] {
		 	},
		 	new String[] {
		 		"Codigo", "Nombre", "Estado", "Administrador", "Cedula"
		 	}
		 ) {
		 	boolean[] columnEditables = new boolean[] {
		 		false, false, false, false, false
		 	};
		 	public boolean isCellEditable(int row, int column) {
		 		return columnEditables[column];
		 	}
		 });
		 scrollPane_4.setViewportView(tblUsuarios);
		 
		 textVendedorBuscar = new JTextField();
		 GridBagConstraints gbc_textVendedorBuscar = new GridBagConstraints();
		 gbc_textVendedorBuscar.gridwidth = 5;
		 gbc_textVendedorBuscar.insets = new Insets(0, 0, 5, 5);
		 gbc_textVendedorBuscar.fill = GridBagConstraints.HORIZONTAL;
		 gbc_textVendedorBuscar.gridx = 7;
		 gbc_textVendedorBuscar.gridy = 6;
		 pnl_usuarios.add(textVendedorBuscar, gbc_textVendedorBuscar);
		 textVendedorBuscar.setColumns(10);
		 
		 btnVendedorBuscar = new JButton("Buscar");
		 btnVendedorBuscar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		if(textVendedorBuscar.getText().length()>0) {
					usuarioDao.searchUsuario(textVendedorBuscar.getText(), tblUsuarios);
				}else {
					//limpiarTableCategoria();
					categoriaDao.ListarCategoriaTable(tblUsuarios);
					//listarCategoriaTable();
				}
		 	}
		 });
		 GridBagConstraints gbc_btnVendedorBuscar = new GridBagConstraints();
		 gbc_btnVendedorBuscar.fill = GridBagConstraints.HORIZONTAL;
		 gbc_btnVendedorBuscar.gridwidth = 5;
		 gbc_btnVendedorBuscar.insets = new Insets(0, 0, 5, 5);
		 gbc_btnVendedorBuscar.gridx = 7;
		 gbc_btnVendedorBuscar.gridy = 7;
		 pnl_usuarios.add(btnVendedorBuscar, gbc_btnVendedorBuscar);
		 
		 btnVendendorAgregar = new JButton("Agregar");
		 btnVendendorAgregar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		if (!controlFormato.hayEspaciosVacios(textVendedorUsuario.getText(),textVendedorCedula.getText(),
		 				textVendedorPassword.getText()) && controlFormato.controlarCedula(textVendedorCedula.getText()) ) {
		 			int estado=cmbVendedorEstado.getSelectedIndex();
		 			int administrador=cmbVendedorAdmin.getSelectedIndex();
		 			usuarioDao.registrarUsuario(new Usuario(textVendedorUsuario.getText(),textVendedorPassword.getText(),  estado,  administrador, textVendedorCedula.getText()));
				    limpiarCamposUsuario();
		 			usuarioDao.listarUsuarioTable(tblUsuarios);
		 		}else {
		 			JOptionPane.showMessageDialog(null, "Se detectó un campo vacio o cedula no tiene 10 dígitos");
		 		}
		 	
		 	}
		 });
		 GridBagConstraints gbc_btnVendendorAgregar = new GridBagConstraints();
		 gbc_btnVendendorAgregar.anchor = GridBagConstraints.SOUTH;
		 gbc_btnVendendorAgregar.fill = GridBagConstraints.HORIZONTAL;
		 gbc_btnVendendorAgregar.gridwidth = 5;
		 gbc_btnVendendorAgregar.insets = new Insets(0, 0, 5, 5);
		 gbc_btnVendendorAgregar.gridx = 7;
		 gbc_btnVendendorAgregar.gridy = 11;
		 pnl_usuarios.add(btnVendendorAgregar, gbc_btnVendendorAgregar);
		 
		 btnVendedorModificar = new JButton("Modificar");
		 btnVendedorModificar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		if (!controlFormato.hayEspaciosVacios(textVendedorUsuario.getText(),textVendedorCedula.getText()) && controlFormato.controlarCedula(textVendedorCedula.getText()) ) {
		 			int estado=cmbVendedorEstado.getSelectedIndex();
		 			int administrador=cmbVendedorAdmin.getSelectedIndex();
		 			usuarioDao.editUsuario(new Usuario(Integer.parseInt(textVendedorCodigo.getText()),textVendedorUsuario.getText(),  estado,  administrador, textVendedorCedula.getText()),textVendedorPassword.getText());
				    limpiarCamposUsuario();
		 			usuarioDao.listarUsuarioTable(tblUsuarios);
		 		}else {
		 			JOptionPane.showMessageDialog(null, "Se detectó un campo vacio o cedula no tiene 10 dígitos");
		 		}
		 	
		 	}
		 });
		 btnVendedorModificar.setEnabled(false);
		 GridBagConstraints gbc_btnVendedorModificar = new GridBagConstraints();
		 gbc_btnVendedorModificar.anchor = GridBagConstraints.SOUTH;
		 gbc_btnVendedorModificar.fill = GridBagConstraints.HORIZONTAL;
		 gbc_btnVendedorModificar.gridwidth = 5;
		 gbc_btnVendedorModificar.insets = new Insets(0, 0, 5, 5);
		 gbc_btnVendedorModificar.gridx = 7;
		 gbc_btnVendedorModificar.gridy = 12;
		 pnl_usuarios.add(btnVendedorModificar, gbc_btnVendedorModificar);
		 
		 btnVendedorEliminar = new JButton("Eliminar");
		 btnVendedorEliminar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		if (!controlFormato.hayEspaciosVacios(textVendedorCodigo.getText())) {
					usuarioDao.deleteUsuario(Integer.parseInt(textVendedorCodigo.getText()));
					limpiarCamposUsuario();
					usuarioDao.listarUsuarioTable(tblUsuarios);
					btnVendendorAgregar.setEnabled(true);
		     		btnVendedorModificar.setEnabled(false);
		     		btnVendedorEliminar.setEnabled(false);
		     		btnVendedorCancelar.setEnabled(false);
		     		btnVendedorCancelar.setVisible(false);
				}
		 	}
		 });
		 btnVendedorEliminar.setEnabled(false);
		 GridBagConstraints gbc_btnVendedorEliminar = new GridBagConstraints();
		 gbc_btnVendedorEliminar.anchor = GridBagConstraints.SOUTH;
		 gbc_btnVendedorEliminar.fill = GridBagConstraints.HORIZONTAL;
		 gbc_btnVendedorEliminar.gridwidth = 5;
		 gbc_btnVendedorEliminar.insets = new Insets(0, 0, 5, 5);
		 gbc_btnVendedorEliminar.gridx = 7;
		 gbc_btnVendedorEliminar.gridy = 13;
		 pnl_usuarios.add(btnVendedorEliminar, gbc_btnVendedorEliminar);
		 
		 btnVendedorCancelar = new JButton("Cancelar");
		 btnVendedorCancelar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		limpiarCamposUsuario();
		 		btnVendendorAgregar.setEnabled(true);
	     		btnVendedorModificar.setEnabled(false);
	     		btnVendedorEliminar.setEnabled(false);
	     		btnVendedorCancelar.setEnabled(false);
	     		btnVendedorCancelar.setVisible(false);
		 		
		 	}
		 });
		 btnVendedorCancelar.setVisible(false);
		 GridBagConstraints gbc_btnVendedorCancelar = new GridBagConstraints();
		 gbc_btnVendedorCancelar.anchor = GridBagConstraints.SOUTH;
		 gbc_btnVendedorCancelar.fill = GridBagConstraints.HORIZONTAL;
		 gbc_btnVendedorCancelar.gridwidth = 5;
		 gbc_btnVendedorCancelar.insets = new Insets(0, 0, 0, 5);
		 gbc_btnVendedorCancelar.gridx = 7;
		 gbc_btnVendedorCancelar.gridy = 14;
		 pnl_usuarios.add(btnVendedorCancelar, gbc_btnVendedorCancelar);

			tabPane_Vistas.setBackgroundAt(0, new Color(214, 214, 214));
			
			tabPane_Vistas.add("Proveedor",pnl_proveedor);
			GridBagLayout gbl_pnl_proveedor = new GridBagLayout();
			gbl_pnl_proveedor.columnWidths = new int[]{615, 334, -172, 0};
			gbl_pnl_proveedor.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 34, 94, 0, 0, 0, 0, 0, 0};
			gbl_pnl_proveedor.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_pnl_proveedor.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			pnl_proveedor.setLayout(gbl_pnl_proveedor);
			
			JPanel pnl_FormProveedor = new JPanel();
			GridBagConstraints gbc_pnl_FormProveedor = new GridBagConstraints();
			gbc_pnl_FormProveedor.gridheight = 7;
			gbc_pnl_FormProveedor.insets = new Insets(0, 0, 5, 5);
			gbc_pnl_FormProveedor.fill = GridBagConstraints.BOTH;
			gbc_pnl_FormProveedor.gridx = 0;
			gbc_pnl_FormProveedor.gridy = 0;
			pnl_proveedor.add(pnl_FormProveedor, gbc_pnl_FormProveedor);
			GridBagLayout gbl_pnl_FormProveedor = new GridBagLayout();
			gbl_pnl_FormProveedor.columnWidths = new int[]{0, 198, 0, 0, 0};
			gbl_pnl_FormProveedor.rowHeights = new int[]{0, 33, 0, 33, 0, 0, 0, 0};
			gbl_pnl_FormProveedor.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_pnl_FormProveedor.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			pnl_FormProveedor.setLayout(gbl_pnl_FormProveedor);
			
			JLabel lblNewLabel_5 = new JLabel("Campos con * son obligatorios");
			GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
			gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_5.gridx = 1;
			gbc_lblNewLabel_5.gridy = 0;
			pnl_FormProveedor.add(lblNewLabel_5, gbc_lblNewLabel_5);
			
			JLabel lblNewLabel = new JLabel("Empresa*");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 1;
			pnl_FormProveedor.add(lblNewLabel, gbc_lblNewLabel);
			
			textNombreProveedor = new JTextField();
			textNombreProveedor.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					int key=e.getKeyChar();
					
					
					if (controlFormato.validarSoloLetras(key)) {
						e.consume();
					}
				
				}
			});
			lblNewLabel.setLabelFor(textNombreProveedor);
			GridBagConstraints gbc_textNombreProveedor = new GridBagConstraints();
			gbc_textNombreProveedor.insets = new Insets(0, 0, 5, 5);
			gbc_textNombreProveedor.fill = GridBagConstraints.HORIZONTAL;
			gbc_textNombreProveedor.gridx = 1;
			gbc_textNombreProveedor.gridy = 1;
			pnl_FormProveedor.add(textNombreProveedor, gbc_textNombreProveedor);
			textNombreProveedor.setColumns(10);
			
			JLabel lblNewLabel_3 = new JLabel("Celular");
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_3.gridx = 2;
			gbc_lblNewLabel_3.gridy = 1;
			pnl_FormProveedor.add(lblNewLabel_3, gbc_lblNewLabel_3);
			
			textCelularproveedor = new JTextField();
			textCelularproveedor.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					int key=e.getKeyChar();
					
					
					if (controlFormato.validarNumerosEnteros(key) || textCelularproveedor.getText().length()>10) {
						e.consume();
					}
					
				
				}
			});
			GridBagConstraints gbc_textCelularproveedor = new GridBagConstraints();
			gbc_textCelularproveedor.insets = new Insets(0, 0, 5, 0);
			gbc_textCelularproveedor.fill = GridBagConstraints.HORIZONTAL;
			gbc_textCelularproveedor.gridx = 3;
			gbc_textCelularproveedor.gridy = 1;
			pnl_FormProveedor.add(textCelularproveedor, gbc_textCelularproveedor);
			textCelularproveedor.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Representante");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 3;
			pnl_FormProveedor.add(lblNewLabel_1, gbc_lblNewLabel_1);
			
			textNombreRepresentante = new JTextField();
			textNombreRepresentante.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					int key=e.getKeyChar();
					
					
					if (controlFormato.validarSoloLetras(key)) {
						e.consume();
					}
				
				}
			});
			GridBagConstraints gbc_textNombreRepresentante = new GridBagConstraints();
			gbc_textNombreRepresentante.insets = new Insets(0, 0, 5, 5);
			gbc_textNombreRepresentante.fill = GridBagConstraints.HORIZONTAL;
			gbc_textNombreRepresentante.gridx = 1;
			gbc_textNombreRepresentante.gridy = 3;
			pnl_FormProveedor.add(textNombreRepresentante, gbc_textNombreRepresentante);
			textNombreRepresentante.setColumns(10);
			
			JLabel lblNewLabel_4 = new JLabel("Telefono");
			GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
			gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_4.gridx = 2;
			gbc_lblNewLabel_4.gridy = 3;
			pnl_FormProveedor.add(lblNewLabel_4, gbc_lblNewLabel_4);
			
			textTelefonoProveedor = new JTextField();
			textTelefonoProveedor.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					int key=e.getKeyChar();
					
					
					if (controlFormato.validarNumerosEnteros(key) || textTelefonoProveedor.getText().length()>9) {
						e.consume();
					}
				
				}
			});
			GridBagConstraints gbc_textTelefonoProveedor = new GridBagConstraints();
			gbc_textTelefonoProveedor.insets = new Insets(0, 0, 5, 0);
			gbc_textTelefonoProveedor.fill = GridBagConstraints.HORIZONTAL;
			gbc_textTelefonoProveedor.gridx = 3;
			gbc_textTelefonoProveedor.gridy = 3;
			pnl_FormProveedor.add(textTelefonoProveedor, gbc_textTelefonoProveedor);
			textTelefonoProveedor.setColumns(10);
			
			JLabel lblNewLabel_2 = new JLabel("Dirección");
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_2.gridx = 0;
			gbc_lblNewLabel_2.gridy = 5;
			pnl_FormProveedor.add(lblNewLabel_2, gbc_lblNewLabel_2);
			
			textDireccionProveedor = new JTextField();
			GridBagConstraints gbc_textDireccionProveedor = new GridBagConstraints();
			gbc_textDireccionProveedor.insets = new Insets(0, 0, 5, 5);
			gbc_textDireccionProveedor.fill = GridBagConstraints.HORIZONTAL;
			gbc_textDireccionProveedor.gridx = 1;
			gbc_textDireccionProveedor.gridy = 5;
			pnl_FormProveedor.add(textDireccionProveedor, gbc_textDireccionProveedor);
			textDireccionProveedor.setColumns(10);
			
			textCodProveedor = new JTextField();
			textCodProveedor.setVisible(false);
			GridBagConstraints gbc_textCodProveedor = new GridBagConstraints();
			gbc_textCodProveedor.insets = new Insets(0, 0, 5, 0);
			gbc_textCodProveedor.fill = GridBagConstraints.HORIZONTAL;
			gbc_textCodProveedor.gridx = 3;
			gbc_textCodProveedor.gridy = 5;
			pnl_FormProveedor.add(textCodProveedor, gbc_textCodProveedor);
			textCodProveedor.setColumns(10);
			
			JLabel lblNewLabel_6 = new JLabel("RUC");
			GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
			gbc_lblNewLabel_6.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel_6.gridx = 0;
			gbc_lblNewLabel_6.gridy = 6;
			pnl_FormProveedor.add(lblNewLabel_6, gbc_lblNewLabel_6);
			
			textRucProveedor = new JTextField();
			textRucProveedor.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					int key=e.getKeyChar();
					
					
					if (controlFormato.validarNumerosEnteros(key) || textRucProveedor.getText().length()>11) {
						e.consume();
					}
				
				}
			});
			GridBagConstraints gbc_textRucProveedor = new GridBagConstraints();
			gbc_textRucProveedor.insets = new Insets(0, 0, 0, 5);
			gbc_textRucProveedor.fill = GridBagConstraints.HORIZONTAL;
			gbc_textRucProveedor.gridx = 1;
			gbc_textRucProveedor.gridy = 6;
			pnl_FormProveedor.add(textRucProveedor, gbc_textRucProveedor);
			textRucProveedor.setColumns(10);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridheight = 7;
			gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 7;
			pnl_proveedor.add(scrollPane, gbc_scrollPane);
			
			tblProveedores = new JTable();
			tblProveedores.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					limpiarCamposProveedor();
		        	int fila = tblProveedores.rowAtPoint(e.getPoint());
		           
		         if(fila>=0)  {
		        	 String key= tblProveedores.getValueAt(fila, 0).toString();
				       Proveedor ca=proveedorDao.searchProveedorId(Integer.parseInt(key));
				        textCodProveedor.setText(String.valueOf(ca.getCodProveedor()  ));
				        textNombreProveedor.setText(ca.getNombreProveedor());
				        textNombreRepresentante.setText(ca.getRepresentante());
				        textCelularproveedor.setText(ca.getCelular());
				        textTelefonoProveedor.setText(ca.getTelefono());
				        textDireccionProveedor.setText(ca.getDireccion());
				        textRucProveedor.setText(ca.getRuc());
				        
				        
				       // btnAgregarCategoria.setVisible(false);
				        btnAgregarProveedor.setEnabled(false);
				        btnModificarProveedor.setEnabled(true);
				        btnEliminarProveedor.setEnabled(true);
				        btnCancelarProveedor.setEnabled(true);
				        btnCancelarProveedor.setVisible(true);
		         }
		         
		         
				}
			});
			tblProveedores.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00F3digo", "Empresa", "representante", "direcci\u00F3n", "celular", "telefono","RUC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			
			});
			tblProveedores.getColumnModel().getColumn(0).setPreferredWidth(15);
			tblProveedores.getColumnModel().getColumn(2).setPreferredWidth(113);
			tblProveedores.getColumnModel().getColumn(3).setPreferredWidth(137);
			tblProveedores.getColumnModel().getColumn(4).setPreferredWidth(94);
			tblProveedores.getColumnModel().getColumn(5).setPreferredWidth(87);
			scrollPane.setViewportView(tblProveedores);
			
			textBuscarProveedor = new JTextField();
			textBuscarProveedor.setPreferredSize(new Dimension(20, 40));
			GridBagConstraints gbc_textBuscarProveedor = new GridBagConstraints();
			gbc_textBuscarProveedor.insets = new Insets(0, 0, 5, 5);
			gbc_textBuscarProveedor.fill = GridBagConstraints.BOTH;
			gbc_textBuscarProveedor.gridx = 1;
			gbc_textBuscarProveedor.gridy = 7;
			pnl_proveedor.add(textBuscarProveedor, gbc_textBuscarProveedor);
			textBuscarProveedor.setColumns(10);
			
			 btnBuscarProveedor = new JButton("Buscar");
			 btnBuscarProveedor.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		if (textBuscarProveedor.getText().length()>0) {
			 			
			 			proveedorDao.searchProveedor(textBuscarProveedor.getText(), tblProveedores);
					} else {
						limpiarCamposProveedor();
						
						proveedorDao.ListarProveedorTable(tblProveedores);
					}
			 		
			 	}
			 });
			GridBagConstraints gbc_btnBuscarProveedor = new GridBagConstraints();
			gbc_btnBuscarProveedor.anchor = GridBagConstraints.NORTH;
			gbc_btnBuscarProveedor.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnBuscarProveedor.insets = new Insets(0, 0, 5, 5);
			gbc_btnBuscarProveedor.gridx = 1;
			gbc_btnBuscarProveedor.gridy = 8;
			pnl_proveedor.add(btnBuscarProveedor, gbc_btnBuscarProveedor);
			
		    btnAgregarProveedor = new JButton("Agregar");
		    btnAgregarProveedor.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		
		    		Proveedor pro=new Proveedor(textNombreProveedor.getText(),
		    				textNombreRepresentante.getText(),
		    				textDireccionProveedor.getText(),
		    				textCelularproveedor.getText(),
		    				textTelefonoProveedor.getText(),
		    				textRucProveedor.getText());
		    		
		    		if (!controlFormato.hayEspaciosVacios(pro.getNombreProveedor())) {
		    			proveedorDao.registrarProveedor(pro);
		    			limpiarCamposProveedor();
		    			//limpiarTableProveedor();
		    			proveedorDao.ListarProveedorTable(tblProveedores);
		    		} else {
		    			 JOptionPane.showMessageDialog(null, "Se detectó un campo vacio");
		    		}
		    		
		    	}

		    	
		    });
		    btnAgregarProveedor.setPreferredSize(new Dimension(240, 23));
		    GridBagConstraints gbc_btnAgregarProveedor = new GridBagConstraints();
		    gbc_btnAgregarProveedor.fill = GridBagConstraints.HORIZONTAL;
		    gbc_btnAgregarProveedor.insets = new Insets(0, 0, 5, 5);
		    gbc_btnAgregarProveedor.gridx = 1;
		    gbc_btnAgregarProveedor.gridy = 9;
		    pnl_proveedor.add(btnAgregarProveedor, gbc_btnAgregarProveedor);
			
			
			 btnModificarProveedor = new JButton("Modificar");
			 btnModificarProveedor.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		
			 		Proveedor ca=new Proveedor(Integer.parseInt(textCodProveedor.getText()),
			 				textNombreProveedor.getText(),
							textNombreRepresentante.getText(),
							textDireccionProveedor.getText(),
							textCelularproveedor.getText(),
							textTelefonoProveedor.getText(),
							textRucProveedor.getText());
					
					if (!controlFormato.hayEspaciosVacios(ca.getNombreProveedor())) {
						proveedorDao.editProveedor(ca);
						//limpiarTableProveedor();
						//categoriaDao.ListarCategoriaTable(tblCategoria);
						proveedorDao.ListarProveedorTable(tblProveedores);
					} 
					
					btnAgregarProveedor.setEnabled(true);
					btnModificarProveedor.setEnabled(false);
			 		btnEliminarProveedor.setEnabled(false);
			        btnCancelarProveedor.setEnabled(false);
			        btnCancelarProveedor.setVisible(false);
			        
			        limpiarCamposProveedor(); 
			 	}
			 });
			 btnModificarProveedor.setEnabled(false);
			 GridBagConstraints gbc_btnModificarProveedor = new GridBagConstraints();
			 gbc_btnModificarProveedor.fill = GridBagConstraints.HORIZONTAL;
			 gbc_btnModificarProveedor.insets = new Insets(0, 0, 5, 5);
			 gbc_btnModificarProveedor.gridx = 1;
			 gbc_btnModificarProveedor.gridy = 10;
			 pnl_proveedor.add(btnModificarProveedor, gbc_btnModificarProveedor);
			
			 btnEliminarProveedor = new JButton("Eliminar");
			 btnEliminarProveedor.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		if (textCodProveedor.getText().length()>0) {
						proveedorDao.deleteProveedor(Integer.valueOf(textCodProveedor.getText()));
						btnAgregarProveedor.setEnabled(true);
				 		btnModificarProveedor.setEnabled(false);
				 		btnEliminarProveedor.setEnabled(false);
				        btnCancelarProveedor.setEnabled(false);
				        btnCancelarProveedor.setVisible(false);
				        
						limpiarCamposProveedor();
						//limpiarTableProveedor();
						proveedorDao.ListarProveedorTable(tblProveedores);
					}
			 	}
			 });
			 btnEliminarProveedor.setEnabled(false);
			 GridBagConstraints gbc_btnEliminarProveedor = new GridBagConstraints();
			 gbc_btnEliminarProveedor.fill = GridBagConstraints.HORIZONTAL;
			 gbc_btnEliminarProveedor.insets = new Insets(0, 0, 5, 5);
			 gbc_btnEliminarProveedor.gridx = 1;
			 gbc_btnEliminarProveedor.gridy = 11;
			 pnl_proveedor.add(btnEliminarProveedor, gbc_btnEliminarProveedor);
			
			 btnCancelarProveedor = new JButton("Cancelar");
			 btnCancelarProveedor.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		
			 		btnAgregarProveedor.setEnabled(true);
			 		btnModificarProveedor.setEnabled(false);
			 		btnEliminarProveedor.setEnabled(false);
			        btnCancelarProveedor.setEnabled(false);
			        btnCancelarProveedor.setVisible(false);
			 		limpiarCamposProveedor();
			 		
			 	}
			 });
			 btnCancelarProveedor.setVisible(false);
			 GridBagConstraints gbc_btnCancelarProveedor = new GridBagConstraints();
			 gbc_btnCancelarProveedor.fill = GridBagConstraints.HORIZONTAL;
			 gbc_btnCancelarProveedor.insets = new Insets(0, 0, 5, 5);
			 gbc_btnCancelarProveedor.gridx = 1;
			 gbc_btnCancelarProveedor.gridy = 12;
			 pnl_proveedor.add(btnCancelarProveedor, gbc_btnCancelarProveedor);
			 
			 btnProveedorRegresarProducto = new JButton("Regresar");
			 btnProveedorRegresarProducto.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		
					tabPane_Vistas.addTab("Productos", null, pnl_producto, null);
					tabPane_Vistas.remove(tabPane_Vistas.indexOfTab("Proveedor"));
					productoDao.ListarProductoTable(tblProductos);
					proveedorDao.cargarListaProveedor(cmbProductoProveedor);
					categoriaDao.cargarListaCategorias(cmbProductoCategoria);
			 	}
			 });
			 GridBagConstraints gbc_btnProveedorRegresarProducto = new GridBagConstraints();
			 gbc_btnProveedorRegresarProducto.fill = GridBagConstraints.HORIZONTAL;
			 gbc_btnProveedorRegresarProducto.insets = new Insets(0, 0, 0, 5);
			 gbc_btnProveedorRegresarProducto.gridx = 1;
			 gbc_btnProveedorRegresarProducto.gridy = 13;
			 pnl_proveedor.add(btnProveedorRegresarProducto, gbc_btnProveedorRegresarProducto);
tabPane_Vistas.addTab("Factura", null, pnl_factura, null);
			
			
			tabPane_Vistas.addTab("Consultas", null, pnl_consultas, null);
		
			tabPane_Vistas.addTab("Productos", null, pnl_producto, null);
			GridBagLayout gbl_pnl_producto = new GridBagLayout();
			gbl_pnl_producto.columnWidths = new int[]{0, 139, 0, 0, 0, 0, 0};
			gbl_pnl_producto.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_pnl_producto.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_pnl_producto.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			pnl_producto.setLayout(gbl_pnl_producto);
			
			JLabel lblProductoNombre = new JLabel("Nombre");
			GridBagConstraints gbc_lblProductoNombre = new GridBagConstraints();
			gbc_lblProductoNombre.anchor = GridBagConstraints.WEST;
			gbc_lblProductoNombre.insets = new Insets(0, 0, 5, 5);
			gbc_lblProductoNombre.gridx = 0;
			gbc_lblProductoNombre.gridy = 0;
			pnl_producto.add(lblProductoNombre, gbc_lblProductoNombre);
			
			textProductoNombre = new JTextField();
			textProductoNombre.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
						int key=e.getKeyChar();
					
					
					if (controlFormato.validarSoloLetras(key)) {
						e.consume();
					}
				}
			});
			GridBagConstraints gbc_textProductoNombre = new GridBagConstraints();
			gbc_textProductoNombre.insets = new Insets(0, 0, 5, 5);
			gbc_textProductoNombre.fill = GridBagConstraints.HORIZONTAL;
			gbc_textProductoNombre.gridx = 1;
			gbc_textProductoNombre.gridy = 0;
			pnl_producto.add(textProductoNombre, gbc_textProductoNombre);
			textProductoNombre.setColumns(10);
			
			JLabel lblNewLabel_13 = new JLabel("Tipo Medicamento");
			GridBagConstraints gbc_lblNewLabel_13 = new GridBagConstraints();
			gbc_lblNewLabel_13.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_13.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_13.gridx = 2;
			gbc_lblNewLabel_13.gridy = 0;
			pnl_producto.add(lblNewLabel_13, gbc_lblNewLabel_13);
			
			 cmbProductoCategoria = new JComboBox();
			GridBagConstraints gbc_cmbProductoCategoria = new GridBagConstraints();
			gbc_cmbProductoCategoria.insets = new Insets(0, 0, 5, 5);
			gbc_cmbProductoCategoria.fill = GridBagConstraints.HORIZONTAL;
			gbc_cmbProductoCategoria.gridx = 3;
			gbc_cmbProductoCategoria.gridy = 0;
			pnl_producto.add(cmbProductoCategoria, gbc_cmbProductoCategoria);
			
			JButton btnProductoAddCategoria = new JButton("");
			btnProductoAddCategoria.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tabPane_Vistas.remove(tabPane_Vistas.indexOfTab("Productos"));
					tabPane_Vistas.addTab("Categoria", null, pnl_categoria, null);
					categoriaDao.ListarCategoriaTable(tblCategoria);
					tabPane_Vistas.setSelectedIndex(tabPane_Vistas.indexOfTab("Categoria"));
				}
			});
			btnProductoAddCategoria.setIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/boton-agregar.png")));
			GridBagConstraints gbc_btnProductoAddCategoria = new GridBagConstraints();
			gbc_btnProductoAddCategoria.anchor = GridBagConstraints.WEST;
			gbc_btnProductoAddCategoria.insets = new Insets(0, 0, 5, 5);
			gbc_btnProductoAddCategoria.gridx = 4;
			gbc_btnProductoAddCategoria.gridy = 0;
			pnl_producto.add(btnProductoAddCategoria, gbc_btnProductoAddCategoria);
			
			JLabel lblNewLabel_7 = new JLabel("Precio  de compra");
			GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
			gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_7.gridx = 0;
			gbc_lblNewLabel_7.gridy = 1;
			pnl_producto.add(lblNewLabel_7, gbc_lblNewLabel_7);
			
			 textProductoPcompra = new JFormattedTextField();
			 textProductoPcompra.addKeyListener(new KeyAdapter() {
			 	@Override
			 	public void keyTyped(KeyEvent e) {
			 		
			 			int key=e.getKeyChar();
					
					
					if (controlFormato.validarNumerosDecimal(key)) {
						e.consume();
					}
			 	}
			 });
			GridBagConstraints gbc_textProductoPcompra = new GridBagConstraints();
			gbc_textProductoPcompra.insets = new Insets(0, 0, 5, 5);
			gbc_textProductoPcompra.fill = GridBagConstraints.HORIZONTAL;
			gbc_textProductoPcompra.gridx = 1;
			gbc_textProductoPcompra.gridy = 1;
			pnl_producto.add(textProductoPcompra, gbc_textProductoPcompra);
			
			JLabel lblNewLabel_14 = new JLabel("Proveedor");
			GridBagConstraints gbc_lblNewLabel_14 = new GridBagConstraints();
			gbc_lblNewLabel_14.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_14.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_14.gridx = 2;
			gbc_lblNewLabel_14.gridy = 1;
			pnl_producto.add(lblNewLabel_14, gbc_lblNewLabel_14);
			
			 cmbProductoProveedor = new JComboBox();
			GridBagConstraints gbc_cmbProductoProveedor = new GridBagConstraints();
			gbc_cmbProductoProveedor.insets = new Insets(0, 0, 5, 5);
			gbc_cmbProductoProveedor.fill = GridBagConstraints.HORIZONTAL;
			gbc_cmbProductoProveedor.gridx = 3;
			gbc_cmbProductoProveedor.gridy = 1;
			pnl_producto.add(cmbProductoProveedor, gbc_cmbProductoProveedor);
			
			JButton btnProductoAddProveedor = new JButton("");
			btnProductoAddProveedor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					tabPane_Vistas.remove(tabPane_Vistas.indexOfTab("Productos"));
					tabPane_Vistas.add("Proveedor",pnl_proveedor);
					proveedorDao.ListarProveedorTable(tblProveedores);
					tabPane_Vistas.setSelectedIndex(tabPane_Vistas.indexOfTab("Proveedor"));
					
					
				}
			});
			btnProductoAddProveedor.setIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/boton-agregar.png")));
			GridBagConstraints gbc_btnProductoAddProveedor = new GridBagConstraints();
			gbc_btnProductoAddProveedor.insets = new Insets(0, 0, 5, 5);
			gbc_btnProductoAddProveedor.gridx = 4;
			gbc_btnProductoAddProveedor.gridy = 1;
			pnl_producto.add(btnProductoAddProveedor, gbc_btnProductoAddProveedor);
			
			JLabel lblNewLabel_8 = new JLabel("Precio de venta");
			GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
			gbc_lblNewLabel_8.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_8.gridx = 0;
			gbc_lblNewLabel_8.gridy = 2;
			pnl_producto.add(lblNewLabel_8, gbc_lblNewLabel_8);
			 textProductoPventa = new JFormattedTextField();
			 textProductoPventa.addKeyListener(new KeyAdapter() {
			 	@Override
			 	public void keyTyped(KeyEvent e) {
			 			int key=e.getKeyChar();
					
					
					if (controlFormato.validarNumerosDecimal(key)) {
						e.consume();
					}
			 	}
			 });
			
			GridBagConstraints gbc_textProductoPventa = new GridBagConstraints();
			gbc_textProductoPventa.insets = new Insets(0, 0, 5, 5);
			gbc_textProductoPventa.fill = GridBagConstraints.HORIZONTAL;
			gbc_textProductoPventa.gridx = 1;
			gbc_textProductoPventa.gridy = 2;
			pnl_producto.add(textProductoPventa, gbc_textProductoPventa);
			
			JLabel lblNewLabel_15 = new JLabel("Observaciones");
			GridBagConstraints gbc_lblNewLabel_15 = new GridBagConstraints();
			gbc_lblNewLabel_15.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_15.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_15.gridx = 2;
			gbc_lblNewLabel_15.gridy = 2;
			pnl_producto.add(lblNewLabel_15, gbc_lblNewLabel_15);
			
			 textProductoObservacion = new JTextArea();
			textProductoObservacion.setTabSize(6);
			textProductoObservacion.setMinimumSize(new Dimension(250, 30));
			textProductoObservacion.setPreferredSize(new Dimension(400, 5));
			GridBagConstraints gbc_textProductoObservacion = new GridBagConstraints();
			gbc_textProductoObservacion.fill = GridBagConstraints.VERTICAL;
			gbc_textProductoObservacion.anchor = GridBagConstraints.WEST;
			gbc_textProductoObservacion.insets = new Insets(0, 0, 5, 5);
			gbc_textProductoObservacion.gridx = 3;
			gbc_textProductoObservacion.gridy = 2;
			pnl_producto.add(textProductoObservacion, gbc_textProductoObservacion);
			
			JLabel lblNewLabel_9 = new JLabel("Unidad Medida");
			GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
			gbc_lblNewLabel_9.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_9.gridx = 0;
			gbc_lblNewLabel_9.gridy = 3;
			pnl_producto.add(lblNewLabel_9, gbc_lblNewLabel_9);
			
			textProductoUmedida = new JTextField();
			GridBagConstraints gbc_textProductoUmedida = new GridBagConstraints();
			gbc_textProductoUmedida.insets = new Insets(0, 0, 5, 5);
			gbc_textProductoUmedida.fill = GridBagConstraints.HORIZONTAL;
			gbc_textProductoUmedida.gridx = 1;
			gbc_textProductoUmedida.gridy = 3;
			pnl_producto.add(textProductoUmedida, gbc_textProductoUmedida);
			textProductoUmedida.setColumns(10);
			
			JLabel lblProductoFIngreso = new JLabel("Fecha de Ingreso");
			lblProductoFIngreso.setVisible(false);
			GridBagConstraints gbc_lblProductoFIngreso = new GridBagConstraints();
			gbc_lblProductoFIngreso.anchor = GridBagConstraints.WEST;
			gbc_lblProductoFIngreso.insets = new Insets(0, 0, 5, 5);
			gbc_lblProductoFIngreso.gridx = 2;
			gbc_lblProductoFIngreso.gridy = 3;
			pnl_producto.add(lblProductoFIngreso, gbc_lblProductoFIngreso);
			
			textProductoFRegistro = new JTextField();
			textProductoFRegistro.setVisible(false);
			textProductoFRegistro.setEditable(false);
			GridBagConstraints gbc_textProductoFRegistro = new GridBagConstraints();
			gbc_textProductoFRegistro.insets = new Insets(0, 0, 5, 5);
			gbc_textProductoFRegistro.fill = GridBagConstraints.HORIZONTAL;
			gbc_textProductoFRegistro.gridx = 3;
			gbc_textProductoFRegistro.gridy = 3;
			pnl_producto.add(textProductoFRegistro, gbc_textProductoFRegistro);
			textProductoFRegistro.setColumns(10);
			
			JLabel lblNewLabel_10 = new JLabel("Presentación");
			GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
			gbc_lblNewLabel_10.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_10.gridx = 0;
			gbc_lblNewLabel_10.gridy = 4;
			pnl_producto.add(lblNewLabel_10, gbc_lblNewLabel_10);
			
			textProductoPresentacion = new JTextField();
			textProductoPresentacion.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					int key=e.getKeyChar();
					
					
					if (controlFormato.validarNumerosDecimal(key)) {
						e.consume();
					}
				}
			});
			GridBagConstraints gbc_textProductoPresentacion = new GridBagConstraints();
			gbc_textProductoPresentacion.insets = new Insets(0, 0, 5, 5);
			gbc_textProductoPresentacion.fill = GridBagConstraints.HORIZONTAL;
			gbc_textProductoPresentacion.gridx = 1;
			gbc_textProductoPresentacion.gridy = 4;
			pnl_producto.add(textProductoPresentacion, gbc_textProductoPresentacion);
			textProductoPresentacion.setColumns(10);
			
			JLabel lblNewLabel_17 = new JLabel("Cantidad");
			GridBagConstraints gbc_lblNewLabel_17 = new GridBagConstraints();
			gbc_lblNewLabel_17.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_17.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_17.gridx = 2;
			gbc_lblNewLabel_17.gridy = 4;
			pnl_producto.add(lblNewLabel_17, gbc_lblNewLabel_17);
			
			textProductoCantidad = new JTextField();
			textProductoCantidad.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
						int key=e.getKeyChar();
					
					
					if (controlFormato.validarNumerosEnteros(key)) {
						e.consume();
					}
				}
			});
			GridBagConstraints gbc_textProductoCantidad = new GridBagConstraints();
			gbc_textProductoCantidad.insets = new Insets(0, 0, 5, 5);
			gbc_textProductoCantidad.fill = GridBagConstraints.HORIZONTAL;
			gbc_textProductoCantidad.gridx = 3;
			gbc_textProductoCantidad.gridy = 4;
			pnl_producto.add(textProductoCantidad, gbc_textProductoCantidad);
			textProductoCantidad.setColumns(10);
			
			JLabel lblNewLabel_11 = new JLabel("Marca");
			GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
			gbc_lblNewLabel_11.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_11.gridx = 0;
			gbc_lblNewLabel_11.gridy = 5;
			pnl_producto.add(lblNewLabel_11, gbc_lblNewLabel_11);
			
			textProductoMarca = new JTextField();
			textProductoMarca.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					int key=e.getKeyChar();
					
					
					if (controlFormato.validarSoloLetras(key)) {
						e.consume();
					}
				}
			});
			GridBagConstraints gbc_textProductoMarca = new GridBagConstraints();
			gbc_textProductoMarca.insets = new Insets(0, 0, 5, 5);
			gbc_textProductoMarca.fill = GridBagConstraints.HORIZONTAL;
			gbc_textProductoMarca.gridx = 1;
			gbc_textProductoMarca.gridy = 5;
			pnl_producto.add(textProductoMarca, gbc_textProductoMarca);
			textProductoMarca.setColumns(10);
			MaskFormatter mascara=null;
			try {
				 mascara =new MaskFormatter("####.##");
				 mascara.setPlaceholderCharacter('0');
				 mascara.setOverwriteMode(true);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			JLabel lblNewLabel_18 = new JLabel("Forma Farmaceutica");
			GridBagConstraints gbc_lblNewLabel_18 = new GridBagConstraints();
			gbc_lblNewLabel_18.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_18.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_18.gridx = 2;
			gbc_lblNewLabel_18.gridy = 5;
			pnl_producto.add(lblNewLabel_18, gbc_lblNewLabel_18);
			
			textProductoFormFarmaceutica = new JTextField();
			textProductoFormFarmaceutica.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					int key=e.getKeyChar();
					
					
					if (controlFormato.validarSoloLetras(key) ) {
						e.consume();
					}
				}
			});
			GridBagConstraints gbc_textProductoFormFarmaceutica = new GridBagConstraints();
			gbc_textProductoFormFarmaceutica.insets = new Insets(0, 0, 5, 5);
			gbc_textProductoFormFarmaceutica.fill = GridBagConstraints.HORIZONTAL;
			gbc_textProductoFormFarmaceutica.gridx = 3;
			gbc_textProductoFormFarmaceutica.gridy = 5;
			pnl_producto.add(textProductoFormFarmaceutica, gbc_textProductoFormFarmaceutica);
			textProductoFormFarmaceutica.setColumns(10);
			
			JLabel lblNewLabel_12 = new JLabel("Fecha Caducidad");
			GridBagConstraints gbc_lblNewLabel_12 = new GridBagConstraints();
			gbc_lblNewLabel_12.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_12.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_12.gridx = 0;
			gbc_lblNewLabel_12.gridy = 6;
			pnl_producto.add(lblNewLabel_12, gbc_lblNewLabel_12);
			
			 textProductoFCaducidad = new JDateChooser();
			lblNewLabel_12.setLabelFor(textProductoFCaducidad);
			
			textProductoFCaducidad.setPreferredSize(new Dimension(400, 60));
			textProductoFCaducidad.setDateFormatString("yyyy-MM-dd");
			GridBagConstraints gbc_textProductoFCaducidad = new GridBagConstraints();
			gbc_textProductoFCaducidad.anchor = GridBagConstraints.NORTH;
			gbc_textProductoFCaducidad.insets = new Insets(0, 0, 5, 5);
			gbc_textProductoFCaducidad.fill = GridBagConstraints.HORIZONTAL;
			gbc_textProductoFCaducidad.gridx = 1;
			gbc_textProductoFCaducidad.gridy = 6;
			pnl_producto.add(textProductoFCaducidad, gbc_textProductoFCaducidad);
			
			textProductoCodigo = new JTextField();
			textProductoCodigo.setVisible(false);
			
			JLabel lblProductoCodBarra = new JLabel("Codigo de  Barra");
			lblProductoCodBarra.setVisible(false);
			GridBagConstraints gbc_lblProductoCodBarra = new GridBagConstraints();
			gbc_lblProductoCodBarra.insets = new Insets(0, 0, 5, 5);
			gbc_lblProductoCodBarra.anchor = GridBagConstraints.WEST;
			gbc_lblProductoCodBarra.gridx = 2;
			gbc_lblProductoCodBarra.gridy = 6;
			pnl_producto.add(lblProductoCodBarra, gbc_lblProductoCodBarra);
			
			textProductoCodBarra = new JTextField();
			textProductoCodBarra.setEditable(false);
			textProductoCodBarra.setVisible(false);
			lblProductoCodBarra.setLabelFor(textProductoCodBarra);
			GridBagConstraints gbc_textProductoCodBarra = new GridBagConstraints();
			gbc_textProductoCodBarra.insets = new Insets(0, 0, 5, 5);
			gbc_textProductoCodBarra.fill = GridBagConstraints.HORIZONTAL;
			gbc_textProductoCodBarra.gridx = 3;
			gbc_textProductoCodBarra.gridy = 6;
			pnl_producto.add(textProductoCodBarra, gbc_textProductoCodBarra);
			textProductoCodBarra.setColumns(10);
			GridBagConstraints gbc_textProductoCodigo = new GridBagConstraints();
			gbc_textProductoCodigo.insets = new Insets(0, 0, 5, 5);
			gbc_textProductoCodigo.fill = GridBagConstraints.HORIZONTAL;
			gbc_textProductoCodigo.gridx = 1;
			gbc_textProductoCodigo.gridy = 7;
			pnl_producto.add(textProductoCodigo, gbc_textProductoCodigo);
			textProductoCodigo.setColumns(10);
			
			textProductoCantCodBarras = new JTextField();
			textProductoCantCodBarras.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
						int key=e.getKeyChar();
					
					
					if (controlFormato.validarNumerosEnteros(key)) {
						e.consume();
					}
				}
			});
			textProductoCantCodBarras.setVisible(false);
			
			lblProductoCantCodBarras = new JLabel("Cantidad de Cod. de barras");
			lblProductoCantCodBarras.setVisible(false);
			GridBagConstraints gbc_lblProductoCantCodBarras = new GridBagConstraints();
			gbc_lblProductoCantCodBarras.insets = new Insets(0, 0, 5, 5);
			gbc_lblProductoCantCodBarras.anchor = GridBagConstraints.WEST;
			gbc_lblProductoCantCodBarras.gridx = 2;
			gbc_lblProductoCantCodBarras.gridy = 7;
			pnl_producto.add(lblProductoCantCodBarras, gbc_lblProductoCantCodBarras);
			GridBagConstraints gbc_textProductoCantCodBarras = new GridBagConstraints();
			gbc_textProductoCantCodBarras.insets = new Insets(0, 0, 5, 5);
			gbc_textProductoCantCodBarras.fill = GridBagConstraints.HORIZONTAL;
			gbc_textProductoCantCodBarras.gridx = 3;
			gbc_textProductoCantCodBarras.gridy = 7;
			pnl_producto.add(textProductoCantCodBarras, gbc_textProductoCantCodBarras);
			textProductoCantCodBarras.setColumns(10);
			
			btnProductoDescarga = new JButton("");
			btnProductoDescarga.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser selectCarpeta=new JFileChooser();
					selectCarpeta.setCurrentDirectory(new File("."));
					selectCarpeta.setDialogTitle("Seleccionar la carpeta para guardar los archivos");
					selectCarpeta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					selectCarpeta.setAcceptAllFileFilterUsed(false);
					if (selectCarpeta.showOpenDialog(contentPane)==JFileChooser.APPROVE_OPTION) {
						
						
						GenerdorDocumentos generador=new GenerdorDocumentos();
						if (!controlFormato.hayEspaciosVacios(textProductoCodBarra.getText(),textProductoCantCodBarras.getText())) {
							generador.generarPDFs(textProductoCodBarra.getText(),Integer.parseInt(textProductoCantCodBarras.getText()) ,selectCarpeta.getSelectedFile().toPath().toString());
						}
						
					}
				}
			});
			btnProductoDescarga.setVisible(false);
			btnProductoDescarga.setIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/download-icon.png")));
			GridBagConstraints gbc_btnProductoDescarga = new GridBagConstraints();
			gbc_btnProductoDescarga.insets = new Insets(0, 0, 5, 5);
			gbc_btnProductoDescarga.gridx = 4;
			gbc_btnProductoDescarga.gridy = 7;
			pnl_producto.add(btnProductoDescarga, gbc_btnProductoDescarga);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
			gbc_scrollPane_1.gridwidth = 5;
			gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
			gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
			gbc_scrollPane_1.gridx = 0;
			gbc_scrollPane_1.gridy = 8;
			pnl_producto.add(scrollPane_1, gbc_scrollPane_1);
			
			tblProductos = new JTable();
			tblProductos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					limpiarCamposProducto();
		        	int fila = tblProductos.rowAtPoint(e.getPoint());
		           
		         if(fila>=0)  {
		        	 String key= tblProductos.getValueAt(fila, 0).toString();
				        Producto ca=productoDao.searchProductoId(Integer.parseInt(key));
				        textProductoCodigo.setText(String.valueOf(ca.getCodProducto()  ));
				        textProductoNombre.setText(ca.getNombreProducto());
				        textProductoCantidad.setText(String.valueOf(ca.getCantidad()));
						 textProductoCodBarra.setText(ca.getCodigobarra());
						 textProductoFormFarmaceutica.setText(ca.getFormaFarmaceutica());
						 textProductoFRegistro.setText(ca.getFechaRegistro().toString());
						 textProductoMarca.setText(ca.getMarca());
						 textProductoPresentacion.setText(String.valueOf(ca.getPresentacion()));
						 textProductoUmedida.setText(ca.getUnidadMedida());
						 textProductoPcompra.setText(String.valueOf(ca.getPrecioCompra()));
						 textProductoPventa.setText(String.valueOf(ca.getPrecioVenta()));
						 cmbProductoProveedor.setSelectedIndex(buscarIdComboProveedor(ca.getCodProveedor()));
						 cmbProductoCategoria.setSelectedIndex(buscarIdComboCategoria(ca.getCodCategoria()));
						 textProductoObservacion.setText(ca.getObservaciones());
						
						 textProductoFCaducidad.setDate(controlFormato.toDate(ca.getFechaCaduca()));
						
						
						 lblProductoCodBarra.setVisible(true);
						 textProductoCodBarra.setVisible(true);
						 lblProductoFIngreso.setVisible(true);
						 textProductoFRegistro.setVisible(true);
						 lblProductoCantCodBarras.setVisible(true);
						 textProductoCantCodBarras.setVisible(true);
						 btnProductoDescarga.setVisible(true);
				       // btnAgregarCategoria.setVisible(false);
				        btnProductoAgregar.setEnabled(false);
				        btnProductoModificar.setEnabled(true);
				        btnProductoEliminar.setEnabled(true);
				        btnProductoCancelar.setEnabled(true);
				        btnProductoCancelar.setVisible(true);
		         }
		        	
				}
			});
			tblProductos.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Codigo", "Nombre", "C\u00F3digo Barra", "Precio Compra", "Precio Venta", "Cantidad", "Unidades", "Presentaci\u00F3n", "Marca", "Ingreso", "Caduca", "Observaciones","Forma", "Proveedor", "Categor\u00EDa"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false, false, false, false, false, false,false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			scrollPane_1.setViewportView(tblProductos);
			
			JPanel panel_3 = new JPanel();
			GridBagConstraints gbc_panel_3 = new GridBagConstraints();
			gbc_panel_3.fill = GridBagConstraints.BOTH;
			gbc_panel_3.gridx = 5;
			gbc_panel_3.gridy = 8;
			pnl_producto.add(panel_3, gbc_panel_3);
			GridBagLayout gbl_panel_3 = new GridBagLayout();
			gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0, 0};
			gbl_panel_3.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel_3.setLayout(gbl_panel_3);
			
			textProductoBuscar = new JTextField();
			GridBagConstraints gbc_textProductoBuscar = new GridBagConstraints();
			gbc_textProductoBuscar.gridwidth = 11;
			gbc_textProductoBuscar.insets = new Insets(0, 0, 5, 0);
			gbc_textProductoBuscar.fill = GridBagConstraints.HORIZONTAL;
			gbc_textProductoBuscar.gridx = 0;
			gbc_textProductoBuscar.gridy = 0;
			panel_3.add(textProductoBuscar, gbc_textProductoBuscar);
			textProductoBuscar.setColumns(10);
			
			
			 btnProductoBuscar = new JButton("Buscar");
			 btnProductoBuscar.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		if (textProductoBuscar.getText().length()>0) {
						productoDao.searchProducto(textProductoBuscar.getText(), tblProductos);
					} else {
						productoDao.ListarProductoTable(tblProductos);
					}
			 	}
			 });
			GridBagConstraints gbc_btnProductoBuscar = new GridBagConstraints();
			gbc_btnProductoBuscar.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnProductoBuscar.anchor = GridBagConstraints.NORTH;
			gbc_btnProductoBuscar.insets = new Insets(0, 0, 5, 5);
			gbc_btnProductoBuscar.gridwidth = 11;
			gbc_btnProductoBuscar.gridx = 0;
			gbc_btnProductoBuscar.gridy = 1;
			panel_3.add(btnProductoBuscar, gbc_btnProductoBuscar);
			
			 btnProductoAgregar = new JButton("Agregar");
			 btnProductoAgregar.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		  if (textProductoFCaducidad.getDate()!=null) {
			 		 	
			 			
			 			
			 			 Proveedor p=(Proveedor)cmbProductoProveedor.getSelectedItem();
			 			 Categoria c=(Categoria)cmbProductoCategoria.getSelectedItem();
			 			
						
						
						if (!controlFormato.hayEspaciosVacios(textProductoNombre.getText(),
								textProductoFormFarmaceutica.getText(),
								textProductoPventa.getText(),
								textProductoPcompra.getText(),
								textProductoCantidad.getText(),
								textProductoUmedida.getText(),
								textProductoPresentacion.getText())) {

							Producto producto=new Producto(textProductoNombre.getText(), 
									textProductoCodBarra.getText(),
									Double.valueOf( textProductoPcompra.getText()),
									Double.valueOf( textProductoPventa.getText()), 
									Integer.parseInt(textProductoCantidad.getText()),
									textProductoUmedida.getText(),
									Double.valueOf(textProductoPresentacion.getText()), 
									textProductoMarca.getText(),
									controlFormato.fromDateToLocalDate(textProductoFCaducidad.getDate()), 
									textProductoObservacion.getText(),
									textProductoFormFarmaceutica.getText(),
									c.getCodCategoria(), 
									p.getCodProveedor());
							
							
							
							productoDao.registrarProducto(producto);
							limpiarCamposProducto();
							productoDao.ListarProductoTable(tblProductos);
							
						}else {
							JOptionPane.showMessageDialog(null, "Hay un campo vacio");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Fecha de caducidad no es correcta");
					}
			 		
			 	}
			 });
			GridBagConstraints gbc_btnProductoAgregar = new GridBagConstraints();
			gbc_btnProductoAgregar.anchor = GridBagConstraints.SOUTH;
			gbc_btnProductoAgregar.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnProductoAgregar.insets = new Insets(0, 0, 5, 0);
			gbc_btnProductoAgregar.gridwidth = 11;
			gbc_btnProductoAgregar.gridx = 0;
			gbc_btnProductoAgregar.gridy = 4;
			panel_3.add(btnProductoAgregar, gbc_btnProductoAgregar);
			
			 btnProductoModificar = new JButton("Modificar");
			 btnProductoModificar.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		 if (textProductoFCaducidad.getDate()!=null) {
				 		 	
				 			
				 			
			 			 Proveedor p=(Proveedor)cmbProductoProveedor.getSelectedItem();
			 			 Categoria c=(Categoria)cmbProductoCategoria.getSelectedItem();
			 			
						
						
						if (!controlFormato.hayEspaciosVacios(textProductoNombre.getText(),
								textProductoCodigo.getText(),
								textProductoFormFarmaceutica.getText(),
								textProductoPventa.getText(),
								textProductoPcompra.getText(),
								textProductoCantidad.getText(),
								textProductoUmedida.getText(),
								textProductoPresentacion.getText())) {
							
							
							
							
							Producto producto=new Producto();
							      producto.setCodProducto(Integer.parseInt(textProductoCodigo.getText()));    
							      producto.setNombreProducto(textProductoNombre.getText());	
							      producto.setPrecioCompra(Double.valueOf( textProductoPcompra.getText()));
							      producto.setPrecioVenta(Double.valueOf( textProductoPventa.getText()));
									producto.setCantidad(Integer.parseInt(textProductoCantidad.getText())); 
									producto.setUnidadMedida(textProductoUmedida.getText());
									producto.setPresentacion(Double.valueOf(textProductoPresentacion.getText()));
									producto.setMarca(textProductoMarca.getText());
									producto.setFechaCaduca(controlFormato.fromDateToLocalDate(textProductoFCaducidad.getDate()));
									producto.setObservaciones(textProductoObservacion.getText());
									producto.setFormaFarmaceutica(textProductoFormFarmaceutica.getText());
								    producto.setCodCategoria(c.getCodCategoria());
									producto.setCodProveedor(p.getCodProveedor());
									
							
							productoDao.editProducto(producto);
							limpiarCamposProducto();
							productoDao.ListarProductoTable(tblProductos);
							
						}else {
							JOptionPane.showMessageDialog(null, "Hay un campo vacio");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Fecha de caducidad no es correcta");
					}
			 	}
			 });
			btnProductoModificar.setEnabled(false);
			GridBagConstraints gbc_btnProductoModificar = new GridBagConstraints();
			gbc_btnProductoModificar.anchor = GridBagConstraints.SOUTH;
			gbc_btnProductoModificar.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnProductoModificar.insets = new Insets(0, 0, 5, 5);
			gbc_btnProductoModificar.gridwidth = 11;
			gbc_btnProductoModificar.gridx = 0;
			gbc_btnProductoModificar.gridy = 5;
			panel_3.add(btnProductoModificar, gbc_btnProductoModificar);
			
			 btnProductoEliminar = new JButton("Eliminar");
			 btnProductoEliminar.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		if (textProductoCodigo.getText().length()>0) {
			 			
			 			productoDao.deleteProducto(Integer.parseInt(textProductoCodigo.getText()));
			 			limpiarCamposProducto();
			 			productoDao.ListarProductoTable(tblProductos);
					}
			 	}
			 });
			btnProductoEliminar.setEnabled(false);
			GridBagConstraints gbc_btnProductoEliminar = new GridBagConstraints();
			gbc_btnProductoEliminar.anchor = GridBagConstraints.SOUTH;
			gbc_btnProductoEliminar.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnProductoEliminar.insets = new Insets(0, 0, 5, 5);
			gbc_btnProductoEliminar.gridwidth = 11;
			gbc_btnProductoEliminar.gridx = 0;
			gbc_btnProductoEliminar.gridy = 6;
			panel_3.add(btnProductoEliminar, gbc_btnProductoEliminar);
			
			 btnProductoCancelar = new JButton("Cancelar");
			 btnProductoCancelar.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		limpiarCamposProducto();
			 		 lblProductoCodBarra.setVisible(false);
					 textProductoCodBarra.setVisible(false);
					 lblProductoFIngreso.setVisible(false);
					 textProductoFRegistro.setVisible(false);
					 lblProductoCantCodBarras.setVisible(false);
					 textProductoCantCodBarras.setVisible(false);
					 btnProductoDescarga.setVisible(false);
			       // btnAgregarCategoria.setVisible(false);
			        btnProductoAgregar.setEnabled(true);
			        btnProductoModificar.setEnabled(false);
			        btnProductoEliminar.setEnabled(false);
			        btnProductoCancelar.setEnabled(false);
			        btnProductoCancelar.setVisible(true);
			 	}
			 });
			 btnProductoCancelar.setVisible(false);
			GridBagConstraints gbc_btnProductoCancelar = new GridBagConstraints();
			gbc_btnProductoCancelar.anchor = GridBagConstraints.SOUTH;
			gbc_btnProductoCancelar.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnProductoCancelar.gridwidth = 11;
			gbc_btnProductoCancelar.gridx = 0;
			gbc_btnProductoCancelar.gridy = 8;
			panel_3.add(btnProductoCancelar, gbc_btnProductoCancelar);
			
			tabPane_Vistas.addTab("Categoria", null, pnl_categoria, null);
		
		

	}
	 protected void limpiarCamposUsuario() {
		// TODO Auto-generated method stub
		textVendedorUsuario.setText("");
		textVendedorCedula.setText("");
		textVendedorPassword.setText("");
		textVendedorCodigo.setText("");
		cmbVendedorAdmin.setSelectedIndex(0);
		cmbVendedorEstado.setSelectedIndex(0);
	}

	protected void eliminarTab(String tab) {
		int i=tabPane_Vistas.indexOfTab(tab);
		 if (i>=0) {
			 tabPane_Vistas.remove(i);
		}
		
	}

	protected int buscarIdComboCategoria(int j) {
		 int y=-1;
         for (int i = 0; i < cmbProductoCategoria.getItemCount(); i++) {
			 Categoria p=(Categoria)cmbProductoCategoria.getItemAt(i);
			 if (p.getCodCategoria()==j) {
				 y=i;
				break;
			}
		}
		return y;
	}

	protected int buscarIdComboProveedor(int j) {

        int y=-1;
         for (int i = 0; i < cmbProductoProveedor.getItemCount(); i++) {
			 Proveedor p=(Proveedor)cmbProductoProveedor.getItemAt(i);
			 if (p.getCodProveedor()==j) {
				 y=i;
				break;
			}
		}
		return y;
	}

	public void listarCategoriaTable() {
	        List<Categoria> ListarCl = categoriaDao.ListarCategoria();
	        String[] columnNames = new String[] {"Column Header1", "Column Header2"};
			
	        modelo = (DefaultTableModel) tblCategoria.getModel();
	        modelo.setColumnIdentifiers(columnNames);
	        Object[] ob = new Object[2];
	        for (int i = 0; i < ListarCl.size(); i++) {
	            ob[0] = ListarCl.get(i).getCodCategoria();
	            ob[1] = ListarCl.get(i).getNombreCategoria();
	           
	            modelo.addRow(ob);
	        }
	        tblCategoria.setModel(modelo);

	    }
	
	
	
	 private void limpiarCamposCategoria() {
			textNombreCategoria.setText("");
			textCodCategoria.setText("");
			
		}
	 
	 private void limpiarCamposProveedor() {
		    textCodProveedor.setText("");
			textNombreProveedor.setText("");
			textNombreRepresentante.setText("");
			textDireccionProveedor.setText("");
			textTelefonoProveedor.setText("");
			textCelularproveedor.setText("");
			textRucProveedor.setText("");
		}
	 
	 private void limpiarCamposProducto() {
		 textProductoCantidad.setText("");
		 textProductoCodBarra.setText("");
		 textProductoCodigo.setText("");
		 textProductoFormFarmaceutica.setText("");
		 textProductoFRegistro.setText("");
		 textProductoMarca.setText("");
		 textProductoNombre.setText("");
		 textProductoPresentacion.setText("");
		 textProductoUmedida.setText("");
		 cmbProductoProveedor.setSelectedIndex(0);
		 cmbProductoCategoria.setSelectedIndex(0);
		 textProductoObservacion.setText("");
		 textProductoFCaducidad.setDate(null);
		 textProductoPcompra.setText("");
		 textProductoPventa.setText("");
		 textProductoCantCodBarras.setText("");
		 
	 }
	 
	 private void limpiarCamposVentas() {
		 textVentasCliente.setText("");
		 textVentasRuc.setText("");
		 textVentasCedula.setText("");
		 textVentasObservaciones.setText("");
		 DefaultTableModel modeloVentas = (DefaultTableModel) tblVentas.getModel();
		 modeloVentas.setRowCount(0);
		 lblTotalpagar.setText("0,00");
		 pnlVentasLadoDerecho.setVisible(false);
		 textVentasCodProd.setText("");
		 textVentasCodProd.requestFocus();
	 }
	 
	 public void cambiarAdministrador() {
		 flagAdministrador=true;
		 tabPane_Vistas.removeAll();
		
		 tabPane_Vistas.add("Vendedores", pnl_usuarios);

			tabPane_Vistas.setBackgroundAt(0, new Color(214, 214, 214));
			
			//tabPane_Vistas.add("Proveedor",pnl_proveedor);
			tabPane_Vistas.addTab("Factura", null, pnl_factura, null);
			
			
			tabPane_Vistas.addTab("Consultas", null, pnl_consultas, null);
		
			tabPane_Vistas.addTab("Productos", null, pnl_producto, null);
			
			//tabPane_Vistas.addTab("Categoria", null, pnl_categoria, null);
	 }
}
