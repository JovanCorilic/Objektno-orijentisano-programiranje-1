package objekti;

import java.time.LocalDateTime;

import glavni.KonverterDatum;

public class Rezervacija {
	private String status;
	private LocalDateTime datumPocetka;
	private LocalDateTime datumKraja;
	private int broj_sobe;
	private String email_gosta;
	private String broj_pasosa;
	
	public void unosObjekta(Integer column,String text) throws Exception {
		switch (column) {
		case 0:
			status=text;
			break;
		case 1:
			LocalDateTime dateTime = KonverterDatum.konvertovanjeUDateTime(text);
			if(datumKraja!=null)
				if(dateTime.isAfter(datumKraja))
					throw new Exception();
			datumPocetka = dateTime;
			break;
		case 2:
			LocalDateTime dateTime2 = KonverterDatum.konvertovanjeUDateTime(text);
			if(dateTime2.isBefore(datumPocetka))
				throw new Exception();
			datumKraja = dateTime2;
			break;
		case 3:
			broj_sobe = Integer.parseInt(text);
			break;
		case 4:
			email_gosta = text;
			break;
		case 5:
			broj_pasosa=text;
		default:
			break;
		}
	}
	
	public Rezervacija() {
		// TODO Auto-generated constructor stub
	}

	// String prodUrl = Environment.PROD.getUrl();
	public static enum Statusi {
		NACEK("NA �EKANJU"), POTVR("POTVRDJENA"), ODBIJ("ODBIJENA"), OTKAZ("OTKAZANA");

		private String vrednost;

		private Statusi(String vrednost) {
			this.vrednost = vrednost;
		}

		public String getVrednost() {
			return vrednost;
		}

		public void setVrednost(String vrednost) {
			this.vrednost = vrednost;
		}

	}

	public Rezervacija(String text) {
		String[] lista = text.split("|");
		this.status = lista[0];
		this.datumPocetka = KonverterDatum.konvertovanjeUDateTime(lista[1]);
		this.datumKraja = KonverterDatum.konvertovanjeUDateTime(lista[2]);
		this.broj_sobe = Integer.parseInt(lista[3]);
		this.email_gosta = lista[4];
		this.broj_pasosa = lista[5];
	}

	public Rezervacija(String status, LocalDateTime datumPocetka, LocalDateTime datumKraja, int broj_sobe,
			String email_gosta, String broj_pasosa) {
		super();
		this.status = status;
		this.datumPocetka = datumPocetka;
		this.datumKraja = datumKraja;
		this.broj_sobe = broj_sobe;
		this.email_gosta = email_gosta;
		this.broj_pasosa = broj_pasosa;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(LocalDateTime datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public LocalDateTime getDatumKraja() {
		return datumKraja;
	}

	public void setDatumKraja(LocalDateTime datumKraja) {
		this.datumKraja = datumKraja;
	}

	public int getBroj_sobe() {
		return broj_sobe;
	}

	public void setBroj_sobe(int broj_sobe) {
		this.broj_sobe = broj_sobe;
	}

	public String getEmail_gosta() {
		return email_gosta;
	}

	public void setEmail_gosta(String email_gosta) {
		this.email_gosta = email_gosta;
	}

	public String getBroj_pasosa() {
		return broj_pasosa;
	}

	public void setBroj_pasosa(String broj_pasosa) {
		this.broj_pasosa = broj_pasosa;
	}

	@Override
	public String toString() {
		return status + "|" + KonverterDatum.konvertovanjeUString(datumPocetka) + "|"
				+ KonverterDatum.konvertovanjeUString(datumKraja) + "|" + broj_sobe + "|" + email_gosta + "|"
				+ broj_pasosa;
	}

}
