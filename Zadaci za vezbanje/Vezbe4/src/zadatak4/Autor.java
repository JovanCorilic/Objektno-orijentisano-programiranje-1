package zadatak4;

import java.util.ArrayList;
import java.util.Objects;

public class Autor {
	private String ime;
	private String prezime;
	private String jmbg;
	private ArrayList<Knjiga>listaKnjiga;
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(ime, jmbg, listaKnjiga, prezime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		return Objects.equals(ime, other.ime) && Objects.equals(jmbg, other.jmbg)
				 && Objects.equals(prezime, other.prezime);
	}

	public Autor(Autor autor) {
		super();
		this.ime = autor.ime;
		this.prezime = autor.prezime;
		this.jmbg = autor.jmbg;
		this.listaKnjiga = autor.listaKnjiga;
	}

	public Autor(String ime, String prezime, String jmbg, ArrayList<Knjiga> listaKnjiga) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.listaKnjiga = listaKnjiga;
	}

	public Autor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Autor [ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg ;
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

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public ArrayList<Knjiga> getListaKnjiga() {
		return listaKnjiga;
	}

	public void setListaKnjiga(ArrayList<Knjiga> listaKnjiga) {
		this.listaKnjiga = listaKnjiga;
	}
	
	
}
