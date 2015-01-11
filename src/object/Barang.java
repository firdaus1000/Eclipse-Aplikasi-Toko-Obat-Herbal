package object;

public class Barang {
	private String idproducts, namaproducts, idsupplier;
	private int harga, stok;
	
	public Barang(String idproducts, String namaproducts ,String idsupplier, int harga, int stok){
		this.idproducts=idproducts;
		this.namaproducts=namaproducts;
		this.idsupplier=idsupplier;
		this.harga=harga;
		this.stok=stok;
	}
	
	public Barang(String namaproducts ,String idsupplier, int harga, int stok){
		this.namaproducts=namaproducts;
		this.idsupplier=idsupplier;
		this.harga=harga;
		this.stok=stok;
	}

	public String getIdproducts() {
		return idproducts;
	}

	public String getNamaproducts() {
		return namaproducts;
	}

	public String getIdsupplier() {
		return idsupplier;
	}
	
	public int getHarga() {
		return harga;
	}

	public int getStok() {
		return stok;
	}
}
