package zadatak1;

import java.util.Comparator;

class SortByNaziv implements Comparator<Artikal>{

	@Override
	public int compare(Artikal o1, Artikal o2) {
		// TODO Auto-generated method stub
		return o1.getNaziv().compareTo(o2.getNaziv()) ;
	}
	
}

class SortByCeni implements Comparator<Artikal>{

	@Override
	public int compare(Artikal o1, Artikal o2) {
		// TODO Auto-generated method stub
		return (int) (o1.getCena()-o2.getCena());
	}
	
}

class SortByKategorija implements Comparator<Artikal>{

	@Override
	public int compare(Artikal o1, Artikal o2) {
		// TODO Auto-generated method stub
		return o1.getKategorija().compareTo(o2.getKategorija()) ;
	}
	
}

public class Artikal {
	private String sifra;
	private String naziv;
	private double cena;
	private int raspoloziva_kolicina;
	private String opis;
	private String kategorija;
	
	

	@Override
	public String toString() {
		return "Artikal : sifra=" + sifra + ", naziv=" + naziv + ", cena=" + cena + ", raspoloziva_kolicina="
				+ raspoloziva_kolicina + ", opis=" + opis + ", kategorija=" + kategorija + "";
	}
	
	public String IspisUFormatu() {
		return "naziv= "+naziv+", cena= "+cena+" , kolicina= "+raspoloziva_kolicina+" , šifra= "+sifra;
	}

	public Artikal(Artikal artikal) {
		super();
		this.sifra = artikal.sifra;
		this.naziv = artikal.naziv;
		this.cena = artikal.cena;
		this.raspoloziva_kolicina = artikal.raspoloziva_kolicina;
		this.opis = artikal.opis;
		this.kategorija = artikal.kategorija;
	}

	public Artikal(String sifra, String naziv, double cena, int raspoloziva_kolicina, String opis, String kategorija) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.cena = cena;
		this.raspoloziva_kolicina = raspoloziva_kolicina;
		this.opis = opis;
		this.kategorija = kategorija;
	}

	public Artikal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public int getRaspoloziva_kolicina() {
		return raspoloziva_kolicina;
	}

	public void setRaspoloziva_kolicina(int raspoloziva_kolicina) {
		this.raspoloziva_kolicina = raspoloziva_kolicina;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getKategorija() {
		return kategorija;
	}

	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}

}
