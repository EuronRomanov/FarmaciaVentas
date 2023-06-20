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

import com.farmacia.entidades.Categoria;
import com.farmacia.entidades.Proveedor;
import com.farmacia.utils.ControlFormatos;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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


public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField textBuscarCategoria;
	private JTable tblCategoria;
	private JTabbedPane tabPane_Vistas;
    private ArrayList<JButton> listEdit, listDelete;
	JPanel pnl_factura = new JPanel();
	JPanel pnl_consultas = new JPanel();
    
    private JPanel pnl_vendedores = new JPanel();
    private JPanel pnl_producto = new JPanel();
    private JPanel pnl_categoria = new JPanel();
    private JPanel pnl_proveedor = new JPanel();
    
    JPanel pnl_ventas = new JPanel();
	JPanel pnl_caja = new JPanel();
    
    private CategoriaDao categoriaDao=new CategoriaDao();
    private ProveedorDao proveedorDao=new ProveedorDao();
    private ControlFormatos controlFormato=new ControlFormatos();
    
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
		setBounds(100, 100, 1102, 548);
		
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
				proveedorDao.ListarProveedorTable(tblProveedores);
				
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
				/*tabPane_Vistas.addTab("Vendedores", null, pnl_vendedores, null);
				tabPane_Vistas.setBackgroundAt(2, new Color(214, 214, 214));*/
				flagAdministrador=true;
				//tabPane_Vistas.setSelectedIndex(2);
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
		
	
		
		
		JButton btnNewButton = new JButton("New button");
		pnl_producto.add(btnNewButton);
		
		
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
					limpiarTableCategoria();
					//categoriaDao.ListarCategoriaTable(tblCategoria);
					listarCategoriaTable();
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
		tblCategoria.setModel(new DefaultTableModel(data,columnas){
			public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
		});
		
		

		tblCategoria.getColumnModel().getColumn(0).setPreferredWidth(15);
		tblCategoria.getColumnModel().getColumn(1).setPreferredWidth(621);
		GridBagConstraints gbc_tblCategoria = new GridBagConstraints();
		gbc_tblCategoria.gridwidth = 22;
		gbc_tblCategoria.insets = new Insets(0, 0, 0, 5);
		gbc_tblCategoria.fill = GridBagConstraints.BOTH;
		gbc_tblCategoria.gridx = 0;
		gbc_tblCategoria.gridy = 2;
		pnl_categoria.add(tblCategoria, gbc_tblCategoria);
		
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
		gbl_panel_2.rowHeights = new int[]{20, 30, 56, 40, 40, 40, 40, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
					limpiarTableCategoria();
					//categoriaDao.ListarCategoriaTable(tblCategoria);
					listarCategoriaTable();
					limpiarCamposCategoria();
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
		gbc_btnAgregarCategoria.anchor = GridBagConstraints.NORTH;
		gbc_btnAgregarCategoria.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAgregarCategoria.insets = new Insets(0, 0, 5, 5);
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
			        limpiarTableCategoria();
			        listarCategoriaTable();
				}
		 		
		 	}
		 });
		btnEliminarCategoria.setEnabled(false);
		btnEliminarCategoria.setOpaque(true);
		GridBagConstraints gbc_btnEliminarCategoria = new GridBagConstraints();
		gbc_btnEliminarCategoria.anchor = GridBagConstraints.NORTH;
		gbc_btnEliminarCategoria.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEliminarCategoria.insets = new Insets(0, 0, 5, 5);
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
					limpiarTableCategoria();
					//categoriaDao.ListarCategoriaTable(tblCategoria);
					listarCategoriaTable();
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
		gbc_btnActualizarCategoria.anchor = GridBagConstraints.NORTH;
		gbc_btnActualizarCategoria.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnActualizarCategoria.insets = new Insets(0, 0, 5, 5);
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
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 0;
		gbc_btnCancelar.gridy = 6;
		panel_2.add(btnCancelar, gbc_btnCancelar);
		
		
		
		
		/*
		 * ---------------categorias------------
		 */
	/*	
		if (flagAdministrador==false)  {
			
			tabPane_Vistas.addTab("Ventas", null, pnl_ventas, null);
			
			
			tabPane_Vistas.addTab("Caja", null, pnl_caja, null);
		}*/
		
		
		 tabPane_Vistas.add("Vendedores", pnl_vendedores);

			tabPane_Vistas.setBackgroundAt(0, new Color(214, 214, 214));
			
			tabPane_Vistas.add("Proveedor",pnl_proveedor);
			GridBagLayout gbl_pnl_proveedor = new GridBagLayout();
			gbl_pnl_proveedor.columnWidths = new int[]{615, 334, -172, 0};
			gbl_pnl_proveedor.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 34, 94, 0, 0, 0, 0, 0};
			gbl_pnl_proveedor.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_pnl_proveedor.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
			gbc_scrollPane.gridheight = 6;
			gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 7;
			pnl_proveedor.add(scrollPane, gbc_scrollPane);
			
			tblProveedores = new JTable();
			tblProveedores.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					limpiarCamposProveedor();;
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
			gbc_btnCancelarProveedor.insets = new Insets(0, 0, 0, 5);
			gbc_btnCancelarProveedor.gridx = 1;
			gbc_btnCancelarProveedor.gridy = 12;
			pnl_proveedor.add(btnCancelarProveedor, gbc_btnCancelarProveedor);
tabPane_Vistas.addTab("Factura", null, pnl_factura, null);
			
			
			tabPane_Vistas.addTab("Consultas", null, pnl_consultas, null);
		
			tabPane_Vistas.addTab("Productos", null, pnl_producto, null);
			
			tabPane_Vistas.addTab("Categoria", null, pnl_categoria, null);
		
		

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
	
	 private void limpiarTableCategoria() {
	        tmp = (DefaultTableModel) tblCategoria.getModel();
	        int fila = tblCategoria.getRowCount();
	        for (int i = 0; i < fila; i++) {
	            tmp.removeRow(0);
	        }
	    }
	 private void limpiarTableProveedor() {
		 
	        tmp = (DefaultTableModel) tblProveedores.getModel();
	        int fila = tblProveedores.getRowCount();
	        for (int i = 0; i < fila; i++) {
	            tmp.removeRow(0);
	        }
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
	 
	 public void cambiarAdministrador() {
		 flagAdministrador=true;
		 tabPane_Vistas.removeAll();
		
		 tabPane_Vistas.add("Vendedores", pnl_vendedores);

			tabPane_Vistas.setBackgroundAt(0, new Color(214, 214, 214));
			
			tabPane_Vistas.add("Proveedor",pnl_proveedor);
tabPane_Vistas.addTab("Factura", null, pnl_factura, null);
			
			
			tabPane_Vistas.addTab("Consultas", null, pnl_consultas, null);
		
			tabPane_Vistas.addTab("Productos", null, pnl_producto, null);
			
			tabPane_Vistas.addTab("Categoria", null, pnl_categoria, null);
	 }
	
}
