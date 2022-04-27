package zadatak1;

public class TekuciRacun extends Racun {

	private double mesecna_naknada;

	public TekuciRacun() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TekuciRacun(Osoba vlasnik_racuna, double stanje_racuna, double mesecna_naknada) {
		super(vlasnik_racuna, stanje_racuna);
		// TODO Auto-generated constructor stub

		this.mesecna_naknada = mesecna_naknada;
	}

	public double getMesecna_naknada() {
		return mesecna_naknada;
	}

	public void setMesecna_naknada(double mesecna_naknada) {
		this.mesecna_naknada = mesecna_naknada;
	}

	@Override
	public boolean podigniPare(double suma) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void uplatiPare(double suma) {
		// TODO Auto-generated method stub

	}

	public void obracunajMesecnuNaknadu() {
		double temp = getStanje_racuna();
	}

}
