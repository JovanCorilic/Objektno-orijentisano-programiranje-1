package objekti;

public class Tip_Soba extends Soba {
	private String naziv_tipa;

	public Tip_Soba(int brojSobe, String status, String naziv_tipa) {
		super(brojSobe, status);
		this.naziv_tipa = naziv_tipa;
	}
	
	public Tip_Soba() {
		// TODO Auto-generated constructor stub
	}

	public String getNaziv_tipa() {
		return naziv_tipa;
	}

	public void setNaziv_tipa(String naziv_tipa) {
		this.naziv_tipa = naziv_tipa;
	}

	@Override
	public String toString() {
		return naziv_tipa + "|" + brojSobe + "|" + status;
	}

	public Tip_Soba(String text) {
		super(text);
		String[] lista = text.split("|");
		this.naziv_tipa = lista[0];
	}

}
