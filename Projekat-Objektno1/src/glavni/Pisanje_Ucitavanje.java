package glavni;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import objekti.Cenovnik;
import objekti.Dodatne_Usluge_Hotela;
import objekti.Korisnik;
import objekti.Rezervacija;
import objekti.Soba;
import objekti.Tip_Soba;
import objekti.Zaposlen;

public class Pisanje_Ucitavanje {

	public static void PisanjeCenovnik(HashMap<String, Cenovnik> mapa) throws IOException {
		PrintWriter printWriter = new PrintWriter(new FileWriter("cenovnici.csv"), false);
		for (Cenovnik temp : mapa.values()) {
			printWriter.println(temp.toString());
		}
		printWriter.close();
	}

	public static void PisanjeDodatneUslugeHotela(HashMap<String, Dodatne_Usluge_Hotela> mapa) throws IOException {
		PrintWriter printWriter = new PrintWriter(new FileWriter("dodatne_usluge_hotela.csv"), false);
		for (Dodatne_Usluge_Hotela temp : mapa.values()) {
			printWriter.println(temp.toString());
		}
		printWriter.close();
	}

	public static void PisanjeZaposlen(HashMap<String, Zaposlen> mapa) throws IOException {
		PrintWriter printWriter = new PrintWriter(new FileWriter("zaposleni.csv"), false);
		for (Zaposlen temp : mapa.values()) {
			printWriter.println(temp.toString());
		}
		printWriter.close();
	}

	public static void PisanjeKorisnik(HashMap<String, Korisnik> mapa) throws IOException {
		PrintWriter printWriter = new PrintWriter(new FileWriter("gosti.csv"), false);
		for (Korisnik temp : mapa.values()) {
			printWriter.println(temp.toString());
		}
		printWriter.close();
	}

	public static void PisanjeRezervacija(ArrayList<Rezervacija> lista) throws IOException {
		PrintWriter printWriter = new PrintWriter(new FileWriter("rezervacije.csv"), false);
		for (Rezervacija temp : lista) {
			printWriter.println(temp.toString());
		}
		printWriter.close();
	}

	public static void PisanjeSobe(HashMap<Integer, Soba> mapa) throws IOException {
		PrintWriter printWriter = new PrintWriter(new FileWriter("sobe.csv"), false);
		for (Soba temp : mapa.values()) {
			printWriter.println(temp.toString());
		}
		printWriter.close();
	}
	
	public static void PisanjeTipaSobe(HashMap<String,Tip_Soba>mapa) throws IOException {
		PrintWriter printWriter = new PrintWriter(new FileWriter("tipSobe.csv"), false);
		for (Tip_Soba temp : mapa.values()) {
			printWriter.println(temp.toString());
		}
		printWriter.close();
		
	}
	
	public static HashMap<String,Tip_Soba> UcitavanjeTipSobe() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("tipSobe.csv"));
			String currentLine;
			HashMap<String,Tip_Soba> mapa = new HashMap<>();
			while ((currentLine = br.readLine()) != null) {
				mapa.put(currentLine.split("\\|")[0],new Tip_Soba(currentLine));
			}
			br.close();
			return mapa;
		} catch (Exception e) {
			return new HashMap<>();
		}
	}

	public static HashMap<String, Cenovnik> UcitavanjeCenovnik() throws IOException {
		try {
			BufferedReader br = new BufferedReader(new FileReader("cenovnici.csv"));
			String currentLine;
			HashMap<String, Cenovnik> mapa = new HashMap<>();
			while ((currentLine = br.readLine()) != null) {
				mapa.put(currentLine.split("\\|")[0], new Cenovnik(currentLine));
			}
			br.close();
			return mapa;
		} catch (Exception e) {
			return new HashMap<>();
		}
	}

	public static HashMap<String, Dodatne_Usluge_Hotela> UcitavanjeDodatne_Usluge_Hotela() throws IOException {
		try {
			BufferedReader br = new BufferedReader(new FileReader("dodatne_usluge_hotela.csv"));
			String currentLine;
			HashMap<String, Dodatne_Usluge_Hotela> mapa = new HashMap<>();
			while ((currentLine = br.readLine()) != null) {
				mapa.put(currentLine.split("\\|")[0], new Dodatne_Usluge_Hotela(currentLine));
			}
			br.close();
			return mapa;
		} catch (Exception e) {
			return new HashMap<>();
		}
	}

	public static HashMap<String, Zaposlen> UcitavanjeZaposleni() throws IOException {
		try {
			BufferedReader br = new BufferedReader(new FileReader("zaposleni.csv"));
			String currentLine;
			HashMap<String, Zaposlen> mapa = new HashMap<>();
			while ((currentLine = br.readLine()) != null) {
				mapa.put(currentLine.split("\\|")[0], new Zaposlen(currentLine));
			}
			br.close();
			return mapa;
		} catch (Exception e) {
			return new HashMap<>();
		}
	}

	public static HashMap<String, Korisnik> UcitavanjeGosti() throws IOException {
		try {
			BufferedReader br = new BufferedReader(new FileReader("gosti.csv"));
			String currentLine;
			HashMap<String, Korisnik> mapa = new HashMap<>();
			while ((currentLine = br.readLine()) != null) {
				mapa.put(currentLine.split("\\|")[0], new Korisnik(currentLine));
			}
			br.close();
			return mapa;
		} catch (Exception e) {
			return new HashMap<>();
		}
	}

	public static ArrayList<Rezervacija> UcitavanjeRezervacije() throws IOException {
		try {
			BufferedReader br = new BufferedReader(new FileReader("rezervacije.csv"));
			String currentLine;
			ArrayList<Rezervacija> lista = new ArrayList<>();
			while ((currentLine = br.readLine()) != null) {
				lista.add(new Rezervacija(currentLine));
			}
			br.close();
			return lista;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	public static HashMap<Integer, Soba> UcitavanjeSobe() throws IOException {
		try {
			BufferedReader br = new BufferedReader(new FileReader("sobe.csv"));
			String currentLine;
			HashMap<Integer, Soba> mapa = new HashMap<>();
			while ((currentLine = br.readLine()) != null) {
				mapa.put(Integer.parseInt(currentLine.split("\\|")[0]), new Soba(currentLine));
			}
			br.close();
			return mapa;
		} catch (Exception e) {
			return new HashMap<>();
		}
	}
}
