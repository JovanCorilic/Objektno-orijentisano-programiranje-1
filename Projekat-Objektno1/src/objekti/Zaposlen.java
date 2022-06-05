package objekti;

import java.time.LocalDate;

import glavni.KonverterDatum;

public class Zaposlen extends Korisnik {
	private String nivo_strucne_spreme;
	private int godina_staza;
	private String plata;

	public Zaposlen(String email, String lozinka, String ime, String prezime, String pol, LocalDate datumRodjenja,
			String telefon, String adresa, String nivo_strucne_spreme, int godina_staza, String plata) {
		super(email, lozinka, ime, prezime, pol, datumRodjenja, telefon, adresa);
		this.nivo_strucne_spreme = nivo_strucne_spreme;
		this.godina_staza = godina_staza;
		this.plata = plata;
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

	public String getPlata() {
		return plata;
	}

	public void setPlata(String plata) {
		this.plata = plata;
	}

	@Override
	public String toString() {
		return nivo_strucne_spreme + "," + godina_staza + "," + plata + "," + email + "," + lozinka + "," + ime + ","
				+ prezime + "," + pol + "," + KonverterDatum.konvertovanjeSamoDatumUString(datumRodjenja) + "," + telefon + "," + adresa;
	}

}
