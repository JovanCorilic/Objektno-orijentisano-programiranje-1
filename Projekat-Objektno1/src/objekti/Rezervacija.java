package objekti;

import java.time.LocalDateTime;

import glavni.KonverterDatum;

public class Rezervacija {
	private String status;
	private LocalDateTime datumPocetka;
	private LocalDateTime datumKraja;

	// String prodUrl = Environment.PROD.getUrl();
	public static enum Statusi {
		NACEK("NA ÈEKANJU"), POTVR("POTVRDJENA"), ODBIJ("ODBIJENA"), OTKAZ("OTKAZANA");

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

	public Rezervacija(String status, LocalDateTime datumPocetka, LocalDateTime datumKraja) {
		super();
		this.status = status;
		this.datumPocetka = datumPocetka;
		this.datumKraja = datumKraja;
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

	@Override
	public String toString() {
		return status + "," + KonverterDatum.konvertovanjeUString(datumPocetka) + "," + KonverterDatum.konvertovanjeUString(datumKraja);
	}

}
