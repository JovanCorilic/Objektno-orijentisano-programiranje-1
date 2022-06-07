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
import objekti.Tip_Soba;
import objekti.Zaposlen;

public class Pisanje_Ucitavanje {
	
	public static void PisanjeCenovnik(HashMap<String, Cenovnik> mapa) throws IOException {
		PrintWriter printWriter = new PrintWriter(new FileWriter("cenovnici.csv"),false);
		for(Cenovnik temp : mapa.values()) {
			printWriter.println(temp.toString());
		}
		printWriter.close();
	}
	
	public static void PisanjeDodatneUslugeHotela(HashMap<String, Dodatne_Usluge_Hotela>mapa) throws IOException {
		PrintWriter printWriter = new PrintWriter(new FileWriter("dodatne_usluge_hotela.csv"),false);
		for(Dodatne_Usluge_Hotela temp : mapa.values()) {
			printWriter.println(temp.toString());
		}
		printWriter.close();
	}
	
	public static void PisanjeZaposlen(HashMap<String, Zaposlen> mapa) throws IOException {
		PrintWriter printWriter = new PrintWriter(new FileWriter("zaposleni.csv"),false);
		for(Zaposlen temp : mapa.values()) {
			printWriter.println(temp.toString());
		}
		printWriter.close();
	}
	
	public static void PisanjeKorisnik(HashMap<String, Korisnik> mapa, HashMap<String, Korisnik> zaposleni) throws IOException {
		PrintWriter printWriter = new PrintWriter(new FileWriter("gosti.csv"),false);
		for(Korisnik temp : mapa.values()) {
			if(!zaposleni.containsKey(temp.getEmail()))
				printWriter.println(temp.toString());
		}
		printWriter.close();
	}
	
	public static void PisanjeRezervacija(HashMap<String, Rezervacija> mapa) throws IOException {
		PrintWriter printWriter = new PrintWriter(new FileWriter("rezervacije.csv"),false);
		for(Rezervacija temp : mapa.values()) {
			printWriter.println(temp.toString());
		}
		printWriter.close();
	}
	
	public static void PisanjeSobe(HashMap<Integer, Tip_Soba> mapa) throws IOException {
		PrintWriter printWriter = new PrintWriter(new FileWriter("sobe.csv"),false);
		for(Tip_Soba temp : mapa.values()) {
			printWriter.println(temp.toString());
		}
		printWriter.close();
	}
	
	public static HashMap<String, Cenovnik> UcitavanjeCenovnik() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cenovnici.csv"));
		String currentLine;
		HashMap<String, Cenovnik>mapa = new HashMap<>();
		while((currentLine=br.readLine())!=null) {
			mapa.put(currentLine.split("|")[0], new Cenovnik(currentLine));
		}
		br.close();
		return mapa;
	}
	
	public static HashMap<String, Dodatne_Usluge_Hotela> UcitavanjeDodatne_Usluge_Hotela() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("dodatne_usluge_hotela.csv"));
		String currentLine;
		HashMap<String, Dodatne_Usluge_Hotela>mapa = new HashMap<>();
		while((currentLine=br.readLine())!=null) {
			mapa.put(currentLine.split("|")[0], new Dodatne_Usluge_Hotela(currentLine));
		}
		br.close();
		return mapa;
	}
}
