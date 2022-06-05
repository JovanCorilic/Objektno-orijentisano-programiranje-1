package objekti;

import java.time.LocalDateTime;

import glavni.KonverterDatum;

public class Cenovnik {
	private String naziv;
	private double cena;
	private LocalDateTime pocetakVazenja;
	private LocalDateTime krajVazenja;
	private Tip_Soba soba;
	private Dodatne_Usluge_Hotela dodatne_Usluge_Hotela;

	public Cenovnik(String naziv, double cena, LocalDateTime pocetakVazenja, LocalDateTime krajVazenja, Tip_Soba soba,
			Dodatne_Usluge_Hotela dodatne_Usluge_Hotela) {
		super();
		this.naziv = naziv;
		this.cena = cena;
		this.pocetakVazenja = pocetakVazenja;
		this.krajVazenja = krajVazenja;
		this.soba = soba;
		this.dodatne_Usluge_Hotela = dodatne_Usluge_Hotela;
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

	public LocalDateTime getPocetakVazenja() {
		return pocetakVazenja;
	}

	public void setPocetakVazenja(LocalDateTime pocetakVazenja) {
		this.pocetakVazenja = pocetakVazenja;
	}

	public LocalDateTime getKrajVazenja() {
		return krajVazenja;
	}

	public void setKrajVazenja(LocalDateTime krajVazenja) {
		this.krajVazenja = krajVazenja;
	}

	public Tip_Soba getSoba() {
		return soba;
	}

	public void setSoba(Tip_Soba soba) {
		this.soba = soba;
	}

	public Dodatne_Usluge_Hotela getDodatne_Usluge_Hotela() {
		return dodatne_Usluge_Hotela;
	}

	public void setDodatne_Usluge_Hotela(Dodatne_Usluge_Hotela dodatne_Usluge_Hotela) {
		this.dodatne_Usluge_Hotela = dodatne_Usluge_Hotela;
	}

	@Override
	public String toString() {
		return naziv + "," + cena + "," + KonverterDatum.konvertovanjeUString(pocetakVazenja) + "," + KonverterDatum.konvertovanjeUString(krajVazenja) + "," + soba + "," + dodatne_Usluge_Hotela;
	}

}
