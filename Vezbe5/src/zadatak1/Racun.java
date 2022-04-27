package zadatak1;

public abstract class Racun {
	private Osoba vlasnik_racuna;
	private double stanje_racuna;

	public abstract boolean podigniPare(double suma);

	public abstract void uplatiPare(double suma);

	@Override
	public String toString() {
		return "Racun [vlasnik_racuna=" + vlasnik_racuna + ", stanje_racuna=" + stanje_racuna + "]";
	}

	public Racun() {
		super();
	}

	public Racun(Osoba vlasnik_racuna, double stanje_racuna) {
		super();
		this.vlasnik_racuna = vlasnik_racuna;
		this.stanje_racuna = stanje_racuna;
	}

	public Osoba getVlasnik_racuna() {
		return vlasnik_racuna;
	}

	public void setVlasnik_racuna(Osoba vlasnik_racuna) {
		this.vlasnik_racuna = vlasnik_racuna;
	}

	public double getStanje_racuna() {
		return stanje_racuna;
	}

	public void setStanje_racuna(double stanje_racuna) {
		this.stanje_racuna = stanje_racuna;
	}

}
