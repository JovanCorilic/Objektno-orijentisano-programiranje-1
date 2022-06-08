package objekti;

import java.time.LocalDateTime;

import glavni.KonverterDatum;

public class Cenovnik {
	private String naziv;
	private double cena;
	private LocalDateTime pocetakVazenja;
	private LocalDateTime krajVazenja;
	private int broj_sobe;
	private String dodatna_usluga_hotela;

	public Cenovnik(String naziv, double cena, LocalDateTime pocetakVazenja, LocalDateTime krajVazenja, int soba,
			String dodatne_Usluge_Hotela) {
		super();
		this.naziv = naziv;
		this.cena = cena;
		this.pocetakVazenja = pocetakVazenja;
		this.krajVazenja = krajVazenja;
		this.broj_sobe = soba;
		this.dodatna_usluga_hotela = dodatne_Usluge_Hotela;
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

	public int getBroj_sobe() {
		return broj_sobe;
	}

	public void setBroj_sobe(int broj_sobe) {
		this.broj_sobe = broj_sobe;
	}

	public String getDodatna_usluga_hotela() {
		return dodatna_usluga_hotela;
	}

	public void setDodatna_usluga_hotela(String dodatna_usluga_hotela) {
		this.dodatna_usluga_hotela = dodatna_usluga_hotela;
	}

	@Override
	public String toString() {
		return naziv + "|" + cena + "|" + KonverterDatum.konvertovanjeUString(pocetakVazenja) + "|"
				+ KonverterDatum.konvertovanjeUString(krajVazenja) + "|" + broj_sobe + "|" + dodatna_usluga_hotela;
	}

	public Cenovnik(String text) {
		String[] lista = text.split("|");
		this.naziv = lista[0];
		this.cena = Double.parseDouble(lista[1]);
		this.pocetakVazenja = KonverterDatum.konvertovanjeUDateTime(lista[2]);
		this.krajVazenja = KonverterDatum.konvertovanjeUDateTime(lista[3]);
		this.broj_sobe = Integer.parseInt(lista[4]);
		this.dodatna_usluga_hotela = lista[5];
	}

}
