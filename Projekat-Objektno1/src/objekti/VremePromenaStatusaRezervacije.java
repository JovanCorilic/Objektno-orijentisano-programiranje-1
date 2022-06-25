package objekti;

import java.time.LocalDateTime;

import glavni.KonverterDatum;

public class VremePromenaStatusaRezervacije {
	private int ID;
	private String status;
	private LocalDateTime vremePromeneStatusa;
	
	
	public VremePromenaStatusaRezervacije(int iD, String status, LocalDateTime vremePromeneStatusa) {
		super();
		ID = iD;
		this.status = status;
		this.vremePromeneStatusa = vremePromeneStatusa;
	}
	
	public VremePromenaStatusaRezervacije(String text) {
		String[] lista = text.split("\\|");
		ID = Integer.parseInt(lista[0]);
		status=lista[1];
		vremePromeneStatusa=KonverterDatum.konvertovanjeUDateTime(lista[2]);
				
	}

	public VremePromenaStatusaRezervacije() {
		// TODO Auto-generated constructor stub
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public LocalDateTime getVremePromeneStatusa() {
		return vremePromeneStatusa;
	}

	public void setVremePromeneStatusa(LocalDateTime vremePromeneStatusa) {
		this.vremePromeneStatusa = vremePromeneStatusa;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return ID + "|" + status + "|" + KonverterDatum.konvertovanjeUString(vremePromeneStatusa);
	}
	
	
	
	

}
