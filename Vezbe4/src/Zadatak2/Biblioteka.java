package Zadatak2;

import java.util.ArrayList;

public class Biblioteka {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Knjiga> listaKnjiga = new ArrayList<>();
		UnosPodataka unosPodataka = new UnosPodataka();
		int br = -1;
		while (br != 0) {
			System.out.println("Unesi");
			br = unosPodataka.unosInt();
			if (br == 1) {
				dodavanje(listaKnjiga);
			} else if (br == 2) {
				brisanje(listaKnjiga);
			} else if (br == 3) {
				izmena(listaKnjiga);
			} else if (br == 4) {
				ispis(listaKnjiga);
			}
		}
	}

	public static void ispis(ArrayList<Knjiga> lista) {
		for (Knjiga knjiga : lista)
			System.out.println(knjiga);
	}

	public static void izmena(ArrayList<Knjiga> lista) {
		UnosPodataka unosPodataka = new UnosPodataka();
		System.out.println("Unesi index");

		System.out.println("Izmeni naziv");
		lista.get(unosPodataka.unosInt()).setNaslov(unosPodataka.unosString());
	}

	public static void brisanje(ArrayList<Knjiga> lista) {
		UnosPodataka podataka = new UnosPodataka();
		lista.remove(podataka.unosInt());
	}

	public static void dodavanje(ArrayList<Knjiga> lista) {
		UnosPodataka unosPodataka = new UnosPodataka();
		System.out.println("Unos sifre");
		Knjiga knjiga = new Knjiga();
		knjiga.setSifra(unosPodataka.unosString());
		System.out.println("Unos naslova");
		knjiga.setNaslov(unosPodataka.unosString());
		System.out.println("Unos godina izgradnje");
		knjiga.setGodinaIzdanja(unosPodataka.unosInt());
		System.out.println("Unos autori");
		knjiga.setAutori(unosPodataka.unosString());
		System.out.println("Unos cena");
		knjiga.setCena(unosPodataka.unosDouble());
		lista.add(knjiga);
	}

}
