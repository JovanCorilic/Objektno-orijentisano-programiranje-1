package objekti;

public class Dodatne_Usluge_Hotela {
	private String naziv;
	private String deskripcija;

	public Dodatne_Usluge_Hotela(String naziv, String deskripcija) {
		super();
		this.naziv = naziv;
		this.deskripcija = deskripcija;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getDeskripcija() {
		return deskripcija;
	}

	public void setDeskripcija(String deskripcija) {
		this.deskripcija = deskripcija;
	}

	public Dodatne_Usluge_Hotela(String text) {
		// TODO Auto-generated constructor stub
		String[] lista = text.split("|");
		this.naziv = lista[0];
		this.deskripcija = lista[1];
	}

	@Override
	public String toString() {
		return naziv + "|" + deskripcija;
	}

}
