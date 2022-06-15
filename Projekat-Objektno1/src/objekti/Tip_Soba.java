package objekti;

import java.util.ArrayList;

public class Tip_Soba {
	private String naziv_tipa;
	private String deskripcija;

	public Tip_Soba(String text) {
		String[]lista = text.split("\\|");
		this.naziv_tipa=lista[0];
		this.deskripcija=lista[1];
	}
	
	public void unosObjekta(Integer column,String text) {
		switch (column) {
		case 0:
			naziv_tipa=text;
			break;
		case 1:
			deskripcija=text;
			break;
		default:
			break;
		}
		
	}

	public Tip_Soba(String naziv_tipa, String deskripcija) {
		super();
		this.naziv_tipa = naziv_tipa;
		this.deskripcija = deskripcija;
	}
	
	public Tip_Soba() {
		// TODO Auto-generated constructor stub
	}

	public String getNaziv_tipa() {
		return naziv_tipa;
	}

	public void setNaziv_tipa(String naziv_tipa) {
		this.naziv_tipa = naziv_tipa;
	}
	
	

	public String getDeskripcija() {
		return deskripcija;
	}

	public void setDeskripcija(String deskripcija) {
		this.deskripcija = deskripcija;
	}

	@Override
	public String toString() {
		return naziv_tipa + "|" + deskripcija;
	}

}
