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


public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField textBuscarCategoria;
	private JTable tblCategoria;
	private JTabbedPane tabPane_Vistas;
    private ArrayList<JButton> listEdit, listDelete;
    
    private CategoriaDao categoriaDao=new CategoriaDao();
    private ControlFormatos controlFormato=new ControlFormatos();
    
    private JTextField textNombreCategoria;
    private JTextField textCodCategoria;
    private DefaultTableModel modelo = new DefaultTableModel();
    private DefaultTableModel tmp = new DefaultTableModel();
    private JButton btnAgregarCategoria,btnCancelar,btnActualizarCategoria, btnEliminarCategoria;
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
		setBounds(100, 100, 1067, 491);
		
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
				limpiarTableCategoria();
				//categoriaDao.ListarCategoriaTable(tblCategoria);
				listarCategoriaTable();
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
				tabPane_Vistas.setSelectedIndex(2);
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
		
		JPanel panel1 = new JPanel();
		contentPane.add(panel1, BorderLayout.CENTER);
		panel1.setLayout(new GridLayout(0, 1, 0, 0));
		
		 tabPane_Vistas = new JTabbedPane(JTabbedPane.TOP);
		panel1.add(tabPane_Vistas);
		
		JPanel pnl_producto = new JPanel();
		tabPane_Vistas.addTab("Producto", null, pnl_producto, null);
		
		JButton btnNewButton = new JButton("New button");
		pnl_producto.add(btnNewButton);
		
		
		/*
		 * Categoria
		 */
		
		
       
        
        
		JPanel pnl_categoria = new JPanel();
		tabPane_Vistas.addTab("Categoria", null, pnl_categoria, null);
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
		
		 btnActualizarCategoria = new JButton("Actualizar");
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
		JPanel pnl_vendedores = new JPanel();
		tabPane_Vistas.addTab("Vendedores", null, pnl_vendedores, null);
		
		JPanel pnl_proveedor = new JPanel();
		tabPane_Vistas.addTab("Proveedor", null, pnl_proveedor, null);
		
		JPanel pnl_factura = new JPanel();
		tabPane_Vistas.addTab("Factura", null, pnl_factura, null);
		
		JPanel pnl_consultas = new JPanel();
		tabPane_Vistas.addTab("Consultas", null, pnl_consultas, null);
		
		JPanel pnl_ventas = new JPanel();
		tabPane_Vistas.addTab("Ventas", null, pnl_ventas, null);
		
		JPanel pnl_caja = new JPanel();
		tabPane_Vistas.addTab("Caja", null, pnl_caja, null);
		
		

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
	 
	 private void limpiarCamposCategoria() {
			textNombreCategoria.setText("");
			textCodCategoria.setText("");
			
		}
	
}
