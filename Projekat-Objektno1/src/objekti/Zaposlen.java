package objekti;

import java.time.LocalDate;

import glavni.KonverterDatum;

public class Zaposlen extends Korisnik {
	private String nivo_strucne_spreme;
	private int godina_staza;
	private Double plata;
	private String tip_zaposlen;

	public static enum tipovi {
		ADMIN("Administrator"), REC("Recepcioner / Agent za usluge gostiju"), SOBARICA("Sobarica");

		private String tip;

		private tipovi(String tip) {
			this.tip = tip;
		}

		public String getTip() {
			return tip;
		}

		public void setTip(String tip) {
			this.tip = tip;
		}

	}
	
	public Double racunanjePlate() {
		Double temp = plata;
		temp += 1.2 * temp*godina_staza*10/100;
		return temp;
	}

	public void unosObjektaZaposlen(Integer column, String text) {
		switch (column) {
		case 0:
			email = text;
			break;
		case 1:
			lozinka = text;
			break;
		case 2:
			ime = text;
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
		case 8:
			nivo_strucne_spreme = text;
			break;
		case 9:
			godina_staza = Integer.parseInt(text);
			break;
		case 10:
			plata = Double.parseDouble(text);
			break;
		case 11:
			tip_zaposlen = text;
			break;
		default:
			break;
		}
	}

	public Zaposlen() {
		// TODO Auto-generated constructor stub
	}

	public Zaposlen(String email, String lozinka, String ime, String prezime, String pol, LocalDate datumRodjenja,
			String telefon, String adresa, String nivo_strucne_spreme, int godina_staza, Double plata,
			String tip_zaposlen) {
		super(email, lozinka, ime, prezime, pol, datumRodjenja, telefon, adresa);
		this.nivo_strucne_spreme = nivo_strucne_spreme;
		this.godina_staza = godina_staza;
		this.plata = plata;
		this.tip_zaposlen = tip_zaposlen;
	}

	public Zaposlen(String text) {
		super(text);
		String[] lista = text.split("\\|");
		this.nivo_strucne_spreme = lista[8];
		this.godina_staza = Integer.parseInt(lista[9]);
		this.plata = Double.parseDouble(lista[10]);
		this.tip_zaposlen = lista[11];
	}

	public String getNivo_strucne_spreme() {
		return nivo_strucne_spreme;
	}

	public void setNivo_strucne_spreme(String nivo_strucne_spreme) {
		this.nivo_strucne_spreme = nivo_strucne_spreme;
	}

	public int getGodina_staza() {
		return godina_staza;
	}

	public void setGodina_staza(int godina_staza) {
		this.godina_staza = godina_staza;
	}

	public Double getPlata() {
		return plata;
	}

	public void setPlata(Double plata) {
		this.plata = plata;
	}

	public String getTip_zaposlen() {
		return tip_zaposlen;
	}

	public void setTip_zaposlen(String tip_zaposlen) {
		this.tip_zaposlen = tip_zaposlen;
	}

	@Override
	public String toString() {
		return email + "|" + lozinka + "|" + ime + "|" + prezime + "|" + pol + "|"
				+ KonverterDatum.konvertovanjeSamoDatumUString(datumRodjenja) + "|" + telefon + "|" + adresa + "|"
				+ nivo_strucne_spreme + "|" + godina_staza + "|" + plata + "|" + tip_zaposlen;
	}

}
