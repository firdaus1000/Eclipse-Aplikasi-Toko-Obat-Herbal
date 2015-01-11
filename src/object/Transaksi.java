package object;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Transaksi {
	private int id;
	private Vector<DetilTransaksi> detilTransaksi = new Vector<DetilTransaksi>();
	private Date tgl;
	private User usr;
	private SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");

	public Transaksi(int id, Vector<DetilTransaksi> detilTransaksi, Date tgl,
			User usr) {
		this.id = id;
		this.detilTransaksi = detilTransaksi;
		this.tgl = tgl;
		this.usr = usr;
	}

	public Transaksi(Date tgl, User usr) {
		this.tgl = tgl;
		this.usr = usr;
	}

	public Transaksi(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public Vector<DetilTransaksi> getDetilTransaksi() {
		return detilTransaksi;
	}

	public Date getTgl() {
		return tgl;
	}

	public String getTglAsString() {
		return format.format(tgl.getTime());
	}

	public User getUser() {
		return usr;
	}

	public int getTotalItem() {
		int total = 0;
		for (int i = 0; i < detilTransaksi.size(); i++) {
			total += detilTransaksi.get(i).getquantity();
		}
		return total;
	}

	public int getTotalHrg() {
		int total = 0;
		for (int i = 0; i < detilTransaksi.size(); i++) {
			total += detilTransaksi.get(i).getBarang().getHarga()
					* detilTransaksi.get(i).getquantity();
		}
		return total;
	}

	public void addDetilTransaksi(DetilTransaksi dt) {
		detilTransaksi.add(dt);
	}
}
