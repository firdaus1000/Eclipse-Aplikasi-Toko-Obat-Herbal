package ui;

import java.awt.*;

import javax.swing.*;

import system.*;
import ui.listener.CustActionListener;

public class WindowLogin extends JFrame {

	final private Core core;

	private JButton btnLogin;
	private JTextField txUsr;
	private JPasswordField txPsw;
	private JLabel lblUsr, lblPsw;

	private Container container;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public WindowLogin(Core core) {
		super("LOGIN");
		this.core = core;

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 200);
		setLocation((screenSize.width - getWidth()) / 2,
				(screenSize.height - getHeight()) / 2);
		setResizable(false);
		JLabel labelHeader = new JLabel("APLIKASI PENJUALAN TOKO OBAT HERBAL");
		labelHeader.setBounds(25,10,250,20);
		 
		container = this.getContentPane();
		container.setLayout(null);
		container.setBackground(Color.GREEN);
		btnLogin = new JButton("Login");
		txUsr = new JTextField(15);
		txPsw = new JPasswordField(15);
		lblUsr = new JLabel("Username");
		lblPsw = new JLabel("Password");

		lblUsr.setHorizontalAlignment(JLabel.RIGHT);
		lblPsw.setHorizontalAlignment(JLabel.RIGHT);

		lblUsr.setBounds(30, 40, 60, 20);
		txUsr.setBounds(95, 40, 170, 20);
		lblPsw.setBounds(30, 80, 60, 20);
		txPsw.setBounds(95, 80, 170, 20);
		btnLogin.setBounds(30, 115, 235, 25);

		btnLogin.addActionListener(new CustActionListener(core, this, btnLogin));
		container.add(labelHeader);
		container.add(lblUsr);
		container.add(txUsr);
		container.add(lblPsw);
		container.add(txPsw);
		container.add(btnLogin);
	}

	public String getUser() {
		return txUsr.getText();
	}

	public String getPass() {
		return txPsw.getText();
	}
}
