package Zadatak2;

public class Knjiga {
	private String sifra;
	private String naslov;
	private int godinaIzdanja;
	private String autori;
	private double cena;

	@Override
	public String toString() {
		return "Knjiga sifra=" + sifra + ", naslov=" + naslov + ", godinaIzdanja=" + godinaIzdanja + ", autori="
				+ autori + ", cena=" + cena + "";
	}

	public Knjiga() {
		// TODO Auto-generated constructor stub
	}

	public Knjiga(String sifra, String naslov, int godinaIzdanja, String autori, double cena) {
		super();
		this.sifra = sifra;
		this.naslov = naslov;
		this.godinaIzdanja = godinaIzdanja;
		this.autori = autori;
		this.cena = cena;
	}

	public Knjiga(Knjiga knjiga) {
		super();
		this.sifra = knjiga.sifra;
		this.naslov = knjiga.naslov;
		this.godinaIzdanja = knjiga.godinaIzdanja;
		this.autori = knjiga.autori;
		this.cena = knjiga.cena;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public int getGodinaIzdanja() {
		return godinaIzdanja;
	}

	public void setGodinaIzdanja(int godinaIzdanja) {
		this.godinaIzdanja = godinaIzdanja;
	}

	public String getAutori() {
		return autori;
	}

	public void setAutori(String autori) {
		this.autori = autori;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

}
