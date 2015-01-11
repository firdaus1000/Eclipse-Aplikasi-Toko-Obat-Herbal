package ui;

import java.awt.Container;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

import object.Barang;
import system.*;
import ui.listener.CustActionListener;
import ui.listener.CustKeyListener;

public class WindowFormBarang extends JFrame {
	private Core core;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JTextField tfId, tfNama, tfidsupp, tfHarga, tfStok;
	private JTable tbl;
	private JLabel lbId, lbNama, lbidsupp, lbHarga, lbStok;

	private Vector<Barang> barang = new Vector<Barang>();
	private Vector<String> nmBarang = new Vector<String>();

	public WindowFormBarang(final Core core) {
		super("Formulir Barang");
		this.core = core;
		setResizable(false);

		setSize(810, 272);
		setLocation((screenSize.width - getWidth()) / 2,
				(screenSize.height - getHeight()) / 2);
		setLayout(null);
		Container container = this.getContentPane();
		container.setBackground(Color.GREEN);
		JMenuBar menu = new JMenuBar();
		this.setJMenuBar(menu);

		JMenu menuUser = new JMenu(
				core.getLoggedInUser().admin() ? " Admin " : " Kasir "
						+ core.getLoggedInUser().getUsername());
		JMenuItem miLogOut = new JMenuItem("Log Out");
		miLogOut.addActionListener(new CustActionListener(core, this, miLogOut,
				CustActionListener.LOGOUT));

		JMenu menuTrans = new JMenu("Transaksi");
		JMenuItem miTransData = new JMenuItem("Data Transaksi");
		miTransData.addActionListener(new CustActionListener(core, this,
				miTransData, CustActionListener.SHOW_DATA_TRANSAKSI));
		/*
		 * JMenuItem miTransCetak = new JMenuItem("Cetak Transaksi");
		 */
		JMenu menuBarang = new JMenu("Barang");
		// JMenuItem miBarangData = new JMenuItem("Data Barang");
		/*
		 * miBarangData.addActionListener(new CustActionListener(core, this,
		 * miBarangData, CustActionListener.SHOW_DATA_BARANG));
		 */
		JMenuItem miBarangCetak = new JMenuItem("Cetak Barang");
		miBarangCetak.addActionListener(new CustActionListener(core, this,
				miBarangCetak, CustActionListener.CETAK_BARANG));
		menu.add(menuUser);
		menuUser.add(miLogOut);

		menu.add(menuTrans);
		// menuTrans.add(miTransCetak);
		menuTrans.add(miTransData);
		menu.add(menuBarang);
		// menuBarang.add(miBarangData);
		menuBarang.add(miBarangCetak);

		ResultSet rs = Operator.getListBarang(core.getConnection());
		try {
			while (rs.next()) {
				barang.add(new Barang(rs.getString(1), rs.getString(2), rs
						.getString(3), rs.getInt(4), rs.getInt(5)));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

		tbl = new JTable(Operator.resultSetToTableModel(Operator
				.getListBarang(core.getConnection())));
		Operator.disableTableEdit(tbl);
		JPanel panTbl = new JPanel();

		panTbl.setLayout(new BorderLayout());
		panTbl.setBackground(Color.GREEN);
		panTbl.add(new JScrollPane(tbl), BorderLayout.CENTER);

		tfId = new JTextField();
		tfNama = new JTextField();
		tfidsupp = new JTextField();
		tfHarga = new JTextField();
		tfStok = new JTextField();

		tfId.setBounds(115, 10, 170, 20);
		tfNama.setBounds(115, 35, 170, 20);
		tfidsupp.setBounds(115, 60, 170, 20);
		tfHarga.setBounds(115, 85, 170, 20);
		tfHarga.addKeyListener(new CustKeyListener(core, this, tfHarga,
				CustKeyListener.NUMBER_ONLY));
		tfStok.setBounds(115, 110, 170, 20);
		tfStok.addKeyListener(new CustKeyListener(core, this, tfStok,
				CustKeyListener.NUMBER_ONLY));

		panTbl.setBounds(295, 10, 500, 200);
		lbId = new JLabel("ID Products");
		lbNama = new JLabel("Nama Products");
		lbidsupp = new JLabel("ID Supplier");
		lbHarga = new JLabel("Harga");
		lbStok = new JLabel("Stok");
		
		lbId.setBounds(10, 10, 100, 20);
		lbId.setHorizontalAlignment(JLabel.RIGHT);
		lbNama.setBounds(10, 35, 100, 20);
		lbNama.setHorizontalAlignment(JLabel.RIGHT);
		lbidsupp.setBounds(10, 60, 100, 20);
		lbidsupp.setHorizontalAlignment(JLabel.RIGHT);
		lbHarga.setBounds(10, 85, 100, 20);
		lbHarga.setHorizontalAlignment(JLabel.RIGHT);
		lbStok.setBounds(10, 110, 100, 20);
		lbStok.setHorizontalAlignment(JLabel.RIGHT);

		JButton buttonTambah = new JButton("Tambah");
		JButton buttonDelete = new JButton("Delete");

		buttonTambah.setBounds(205, 135, 80, 20);
		buttonTambah.addActionListener(new CustActionListener(core, this,tbl,
				buttonTambah, CustActionListener.TAMBAH_BARANG));
		buttonDelete.setBounds(115, 135, 80, 20);
		buttonDelete.addActionListener(new CustActionListener(core, this,tbl,
				buttonDelete, CustActionListener.HAPUS_BARANG));
		// Add Content
		container.add(tfId);
		container.add(tfNama);
		container.add(tfidsupp);
		container.add(tfHarga);
		container.add(tfStok);
		container.add(panTbl);
		container.add(lbId);
		container.add(lbNama);
		container.add(lbidsupp);
		container.add(lbHarga);
		container.add(lbStok);

		container.add(buttonDelete);
		container.add(buttonTambah);
	}

	public Vector<Barang> getListBarang() {
		return barang;
	}

	public Barang getSelectedBarang() {
		return barang.get(tbl.getSelectedRow());
	}

	public void submitToDB() {
		if (Operator.tambahBarang(getBarang(), core.getConnection())) {
			JOptionPane.showMessageDialog(this, "Data Telah Ditambahkan!");
		} else {
			JOptionPane.showMessageDialog(this, "Terjadi Kesalahan");
		}
		
		((DefaultTableModel)tbl.getModel()).addRow(new Object[]{tfId.getText(),tfNama.getText(),tfidsupp.getText(),tfHarga.getText(),tfStok.getText()});

		tfId.setText("");
		tfNama.setText("");
		tfidsupp.setText("");
		tfHarga.setText("");
		tfStok.setText("");
	}

	public void resetForm() {
		tfId.setText("");
		tfNama.setText("");
		tfidsupp.setText("");
		tfHarga.setText("");
		tfStok.setText("");
		
		if (tbl.getSelectedRow() >= 0) {
			((DefaultTableModel) tbl.getModel())
					.removeRow(tbl.getSelectedRow());
		}
	}


	public Barang getBarang() {
		return new Barang(tfId.getText(), tfNama.getText(),tfidsupp.getText(),
				Integer.parseInt(tfHarga.getText()),Integer.parseInt(tfHarga.getText()));
	}
}
