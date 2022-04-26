package zadatak4;

import java.util.ArrayList;
import java.util.Collections;

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
			} else if (br == 5) {
				pretragaPoNaslovu(listaKnjiga);
			} else if (br == 6) {
				ispis(pretragaPoAutoru(listaKnjiga));
			} else if (br == 7) {
				sortiranjePoGodIzda(listaKnjiga);
			} else if (br == 8) {
				sortiranjePoNaslovu(listaKnjiga);
			} else if (br == 9) {
				sortiranjeJedinicnojCeni(listaKnjiga);
			}
		}
	}

	public static void sortiranjeJedinicnojCeni(ArrayList<Knjiga> lista) {
		Collections.sort(lista, new poCeni());
		ispis(lista);
		Collections.sort(lista, Collections.reverseOrder(new poCeni()));
		ispis(lista);

	}

	public static void sortiranjePoNaslovu(ArrayList<Knjiga> lista) {
		Collections.sort(lista, new poNazivu());
		ispis(lista);
		Collections.sort(lista, Collections.reverseOrder(new poNazivu()));
		ispis(lista);

	}

	public static void sortiranjePoGodIzda(ArrayList<Knjiga> lista) {
		Collections.sort(lista, new poGodiniIzdanja());
		ispis(lista);
		Collections.sort(lista, Collections.reverseOrder(new poGodiniIzdanja()));
		ispis(lista);

	}

	public static ArrayList<Knjiga> pretragaPoAutoru(ArrayList<Knjiga> lista) {
		UnosPodataka unosPodataka = new UnosPodataka();
		System.out.println("Unesite autora");
		Autor autor = new Autor();
		System.out.println("Unesi ime");
		autor.setIme(unosPodataka.unosString());
		System.out.println("Unesi prezime");
		autor.setPrezime(unosPodataka.unosString());
		System.out.println("Unos jbmg");
		autor.setJmbg(unosPodataka.unosString());
		ArrayList<Knjiga> listaNadjenih = new ArrayList<>();
		for (Knjiga knjiga : lista) {
			for (Autor temp : knjiga.getAutori()) {
				if (temp.equals(autor))
					listaNadjenih.add(knjiga);
			}
		}
		return listaNadjenih;
	}

	public static Knjiga pretragaPoNaslovu(ArrayList<Knjiga> lista) {
		UnosPodataka unosPodataka = new UnosPodataka();
		String naslov = unosPodataka.unosString();
		for (Knjiga knjiga : lista)
			if (knjiga.getNaslov().equalsIgnoreCase(naslov))
				return knjiga;
		return null;
	}

	public static void ispis(ArrayList<Knjiga> lista) {
		for (Knjiga knjiga : lista)
			System.out.println(knjiga);
		System.out.println();
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

		int br = -1;
		knjiga.setAutori(new ArrayList<>());
		while (br != 0) {
			System.out.println("Unesi opciju");
			br = unosPodataka.unosInt();
			if (br == 1) {
				Autor autor = new Autor();
				System.out.println("Unesi ime");
				autor.setIme(unosPodataka.unosString());
				System.out.println("Unesi prezime");
				autor.setPrezime(unosPodataka.unosString());
				System.out.println("Unos jbmg");
				autor.setJmbg(unosPodataka.unosString());
				autor.setListaKnjiga(new ArrayList<>());
				knjiga.getAutori().add(autor);
			}
		}

		System.out.println("Unos cena");
		knjiga.setCena(unosPodataka.unosDouble());
		for (Autor autor : knjiga.getAutori()) {
			autor.getListaKnjiga().add(knjiga);
		}
		lista.add(knjiga);
	}

}
