package zadatak1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Prodavnica {

	private Scanner scanner = new Scanner(System.in);

	public Prodavnica() {
		super();
	}

	public static void main(String[] args) {
		Prodavnica prodavnica = new Prodavnica();
		ArrayList<Artikal> listaArtikala = new ArrayList<>();
		int br = -1;
		while (br != 0) {
			System.out.println("Unesite opciju");
			br = prodavnica.scanner.nextInt();
			if (br == 1)
				listaArtikala.add(prodavnica.unosNovogArtikla());
			else if (br == 2) {
				System.out.println("Unesite broj artikla u listi:");
				int index = prodavnica.scanner.nextInt();
				prodavnica.izmenaPodatka(listaArtikala.get(index));
			} else if (br == 3) {
				prodavnica.ispisPodataka(listaArtikala);
			} else if (br == 4) {
				System.out.println("Po nazivu");

				Collections.sort(listaArtikala, new SortByNaziv());
				prodavnica.ispisListe(listaArtikala);

				Collections.sort(listaArtikala, Collections.reverseOrder());
				prodavnica.ispisListe(listaArtikala);

				Collections.sort(listaArtikala, new SortByCeni());
				prodavnica.ispisListe(listaArtikala);

				Collections.sort(listaArtikala, Collections.reverseOrder());
				prodavnica.ispisListe(listaArtikala);

				Collections.sort(listaArtikala, new SortByKategorija());
				prodavnica.ispisListe(listaArtikala);

				Collections.sort(listaArtikala, Collections.reverseOrder());
				prodavnica.ispisListe(listaArtikala);

			}
		}

	}

	public void ispisListe(ArrayList<Artikal> lista) {
		for (Artikal artikal : lista)
			System.out.println(artikal);
		System.out.println();
	}

	public Artikal unosNovogArtikla() {
		Scanner scanner = new Scanner(System.in);
		Artikal artikal = new Artikal();
		
		System.out.println("Unesite sifru:");
		artikal.setSifra(scanner.nextLine());
		System.out.println("Unesite naziv");
		artikal.setNaziv(scanner.nextLine());
		System.out.println("Unesite cenu");
		artikal.setCena(scanner.nextDouble());
		System.out.println("Unosite raspolozivu kolicinu");
		artikal.setRaspoloziva_kolicina(scanner.nextInt());
		System.out.println("Unesite opis");
		artikal.setOpis(scanner.nextLine());
		System.out.println("Unesite kategoriju");
		artikal.setKategorija(scanner.nextLine());
		scanner.close();
		return artikal;
	}

	public void izmenaPodatka(Artikal artikal) {
		int br = -1;
		while (br != 0) {
			System.out.println("Unesite opciju");
			if (br == 1) {
				artikal.setSifra(scanner.nextLine());
			} else if (br == 2) {
				artikal.setNaziv(scanner.nextLine());
			}

		}
	}

	public void ispisPodataka(ArrayList<Artikal> lista) {
		System.out.println("Ispis podataka, odaberite opciju:");
		int br = -1;
		while (br == 0) {
			if (br == 1) {
				for (int i = 0; i < lista.size(); i++) {
					System.out.println(i + 1 + " : " + lista.get(i));
				}
			} else if (br == 2) {
				for (int i = 0; i < lista.size(); i++) {
					System.out.println(i + 1 + " : " + lista.get(i).IspisUFormatu());
				}
			} else if (br == 3) {
				System.out.println("Unesite artikal");
				String kategorija = scanner.nextLine();
				for (Artikal artikal : lista) {
					if (artikal.getKategorija().equalsIgnoreCase(kategorija)) {
						System.out.println(artikal);
					}
				}
			}
		}
	}

}
