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

	@Override
	public String toString() {
		return naziv + "," + deskripcija;
	}

}
