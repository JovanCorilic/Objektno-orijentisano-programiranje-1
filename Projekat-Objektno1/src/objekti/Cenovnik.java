package objekti;

import java.time.LocalDateTime;

import glavni.KonverterDatum;

public class Cenovnik {
	private String naziv;
	private double cena;
	private LocalDateTime pocetakVazenja;
	private LocalDateTime krajVazenja;
	private String tip_sobe;
	private String dodatna_usluga_hotela;
	
	public void unosObjekta(int column, String object) throws Exception {
		switch (column) {
		case 0:
			naziv = object;
			break;
		case 1:
			cena = Double.parseDouble(object);
			break;
		case 2:
			LocalDateTime dateTime = KonverterDatum.konvertovanjeUDateTime(object);
			if(krajVazenja!=null)
				if(dateTime.isAfter(krajVazenja))
					throw new Exception();
			pocetakVazenja = dateTime;
			break;
		case 3:
			LocalDateTime dateTime2 = KonverterDatum.konvertovanjeUDateTime(object);
			if(dateTime2.isBefore(pocetakVazenja))
				throw new Exception();
			krajVazenja = dateTime2;
			break;
		case 4:
			tip_sobe = object;
			break;
		case 5:
			dodatna_usluga_hotela = object;
			break;
		default:
			break;
		}
	}
	
	public Cenovnik() {
		// TODO Auto-generated constructor stub
	}

	

	public Cenovnik(String naziv, double cena, LocalDateTime pocetakVazenja, LocalDateTime krajVazenja, String tip_sobe,
			String dodatna_usluga_hotela) {
		super();
		this.naziv = naziv;
		this.cena = cena;
		this.pocetakVazenja = pocetakVazenja;
		this.krajVazenja = krajVazenja;
		this.tip_sobe = tip_sobe;
		this.dodatna_usluga_hotela = dodatna_usluga_hotela;
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

	

	public String getTip_sobe() {
		return tip_sobe;
	}

	public void setTip_sobe(String tip_sobe) {
		this.tip_sobe = tip_sobe;
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
				+ KonverterDatum.konvertovanjeUString(krajVazenja) + "|" + tip_sobe + "|" + dodatna_usluga_hotela;
	}

	public Cenovnik(String text) {
		String[] lista = text.split("|");
		this.naziv = lista[0];
		this.cena = Double.parseDouble(lista[1]);
		this.pocetakVazenja = KonverterDatum.konvertovanjeUDateTime(lista[2]);
		this.krajVazenja = KonverterDatum.konvertovanjeUDateTime(lista[3]);
		this.tip_sobe = lista[4];
		this.dodatna_usluga_hotela = lista[5];
	}

}
