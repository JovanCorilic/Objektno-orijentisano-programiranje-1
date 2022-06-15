package objekti;

public class Soba {
	private int brojSobe;
	private String status;
	private String tip_Soba;
	
	public void unosObjekta(Integer column,String text) {
		switch (column) {
		case 0:
			brojSobe = Integer.parseInt(text);
			break;
		case 1:
			status = text;
			break;
		case 2:
			tip_Soba = text;
			break;
		default:
			break;
		}
		
	}

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

	public Soba() {
		// TODO Auto-generated constructor stub
	}

	public Soba(int brojSobe, String status, String tip_Soba) {
		super();
		this.brojSobe = brojSobe;
		this.status = status;
		this.tip_Soba = tip_Soba;
	}

	public Soba(String text) {
		// TODO Auto-generated constructor stub
		String[] lista = text.split("|");
		this.brojSobe = Integer.parseInt(lista[0]);
		this.status = lista[1];
		this.tip_Soba = lista[2];
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

	public String getTip_Soba() {
		return tip_Soba;
	}

	public void setTip_Soba(String tip_Soba) {
		this.tip_Soba = tip_Soba;
	}

	@Override
	public String toString() {
		return brojSobe + "|" + status + "|" + tip_Soba;
	}

}
