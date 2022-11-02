package zadatak1;

import java.util.Objects;

public class Osoba {

	private String JMBG;
	private String ime;
	private String prezime;

	public Osoba() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		return Objects.hash(JMBG, ime, prezime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Osoba other = (Osoba) obj;
		return Objects.equals(JMBG, other.JMBG) && Objects.equals(ime, other.ime)
				&& Objects.equals(prezime, other.prezime);
	}

	@Override
	public String toString() {
		return "Osoba [JMBG=" + JMBG + ", ime=" + ime + ", prezime=" + prezime + "]";
	}

	public Osoba(Osoba osoba) {
		super();
		this.JMBG = osoba.JMBG;
		this.ime = osoba.ime;
		this.prezime = osoba.prezime;
	}

	public Osoba(String jMBG, String ime, String prezime) {
		super();
		JMBG = jMBG;
		this.ime = ime;
		this.prezime = prezime;
	}

	public String getJMBG() {
		return JMBG;
	}

	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

}
