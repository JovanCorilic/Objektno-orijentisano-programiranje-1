package objekti;

public class Soba {
	protected int brojSobe;
	protected String status;

	public static enum Statusi {
		ZAU("ZAUZETO"), SPR("SPREMANJE"), SLO("SLOBODNA");

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

	public Soba(int brojSobe, String status) {
		super();
		this.brojSobe = brojSobe;
		this.status = status;
	}

	public Soba(String text) {
		// TODO Auto-generated constructor stub
		String[] lista = text.split("|");
		this.brojSobe = Integer.parseInt(lista[1]);
		this.status = lista[2];
	}

	public int getBrojSobe() {
		return brojSobe;
	}

	public void setBrojSobe(int brojSobe) {
		this.brojSobe = brojSobe;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return brojSobe + "|" + status;
	}

}
