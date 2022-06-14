package objekti;

import java.time.LocalDate;
import java.time.LocalDateTime;

import glavni.KonverterDatum;

public class Korisnik {
	protected String email;
	protected String lozinka;// <- broj pasosa lozinka kod gosta
	protected String ime;
	protected String prezime;
	protected String pol;
	protected LocalDate datumRodjenja;
	protected String telefon;
	protected String adresa;
	
	public void unosObjekta(Integer column,String text) {
		switch (column) {
		case 0:
			email = text;
			break;
		case 1:
			lozinka = text;
			break;
		case 2:
			ime=text;
			break;
		case 3:
			prezime = text;
			break;
		case 4:
			pol = text;
			break;
		case 5:
			datumRodjenja = KonverterDatum.konvertovanjeSamoDatumUDate(text);
			break;
		case 6:
			telefon = text;
			break;
		case 7:
			adresa = text;
			break;
		default:
			break;
		}
		
	}

	public Korisnik(String email, String lozinka, String ime, String prezime, String pol, LocalDate datumRodjenja,
			String telefon, String adresa) {
		super();
		this.email = email;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.datumRodjenja = datumRodjenja;
		this.telefon = telefon;
		this.adresa = adresa;
	}
	
	public Korisnik() {
		// TODO Auto-generated constructor stub
	}

	public Korisnik(String text) {
		// TODO Auto-generated constructor stub
		String[] lista = text.split("|");
		this.email = lista[0];
		this.lozinka = lista[1];
		this.ime = lista[2];
		this.prezime = lista[3];
		this.pol = lista[4];
		this.datumRodjenja = KonverterDatum.konvertovanjeSamoDatumUDate(lista[5]);
		this.telefon = lista[6];
		this.adresa = lista[7];
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
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

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	@Override
	public String toString() {
		return email + "|" + lozinka + "|" + ime + "|" + prezime + "|" + pol + "|"
				+ KonverterDatum.konvertovanjeSamoDatumUString(datumRodjenja) + "|" + telefon + "|" + adresa;
	}

}
