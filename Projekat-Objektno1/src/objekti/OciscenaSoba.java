package objekti;

import java.time.LocalDateTime;

import glavni.KonverterDatum;

public class OciscenaSoba {
	private Integer broj_sobe;
	private LocalDateTime vreme_kada_je_ocisceno;
	public OciscenaSoba(Integer broj_sobe, LocalDateTime vreme_kada_je_ocisceno) {
		super();
		this.broj_sobe = broj_sobe;
		this.vreme_kada_je_ocisceno = vreme_kada_je_ocisceno;
	}
	
	
	
	public OciscenaSoba(String text) {
		String[]lista = text.split("\\|");
		broj_sobe=Integer.parseInt(lista[0]);
		vreme_kada_je_ocisceno=KonverterDatum.konvertovanjeUDateTime(lista[1]);
		
	}

	

	@Override
	public String toString() {
		return broj_sobe + "|" + vreme_kada_je_ocisceno;
	}



	public OciscenaSoba() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getBroj_sobe() {
		return broj_sobe;
	}
	public void setBroj_sobe(Integer broj_sobe) {
		this.broj_sobe = broj_sobe;
	}
	public LocalDateTime getVreme_kada_je_ocisceno() {
		return vreme_kada_je_ocisceno;
	}
	public void setVreme_kada_je_ocisceno(LocalDateTime vreme_kada_je_ocisceno) {
		this.vreme_kada_je_ocisceno = vreme_kada_je_ocisceno;
	}
	
	
	
	

}
