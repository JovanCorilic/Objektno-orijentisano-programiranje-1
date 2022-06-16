package objekti;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import glavni.Pisanje_Ucitavanje;

public class BazaObjekata {
	private HashMap<String, Cenovnik> mapaCenovnik;
	private HashMap<String, Dodatne_Usluge_Hotela> mapaDodatneUslugeHotela;
	private HashMap<String, Korisnik> mapaGosti;
	private HashMap<Integer, Rezervacija> mapaRezervacija;
	private HashMap<Integer, Soba> mapaSoba;
	private HashMap<String, Zaposlen> mapaZaposlenih;
	private HashMap<String, Tip_Soba> mapaTipovaSobe;
	private HashMap<String, ArrayList<Integer>> mapaSobarica;
	
	public String NajslobodnijaSobarica() {
		int min = -1;
		String temp="";
		for(Map.Entry<String, ArrayList<Integer>>entry : mapaSobarica.entrySet()) {
			if(min==-1) {
				temp = entry.getKey();
				min = entry.getValue().size();
			}else if(min>entry.getValue().size()) {
				temp = entry.getKey();
				min = entry.getValue().size();
			}
		}
		return temp;
	}

	public void ucitavanje() throws IOException {
		mapaCenovnik = Pisanje_Ucitavanje.UcitavanjeCenovnik();
		mapaDodatneUslugeHotela = Pisanje_Ucitavanje.UcitavanjeDodatne_Usluge_Hotela();
		mapaGosti = Pisanje_Ucitavanje.UcitavanjeGosti();
		mapaRezervacija = Pisanje_Ucitavanje.UcitavanjeRezervacije();
		mapaTipovaSobe = Pisanje_Ucitavanje.UcitavanjeTipSobe();
		mapaSoba = Pisanje_Ucitavanje.UcitavanjeSobe();
		mapaZaposlenih = Pisanje_Ucitavanje.UcitavanjeZaposleni();
		mapaSobarica = Pisanje_Ucitavanje.UcitavanjeSobarice();
		for(Map.Entry<String, Zaposlen>entry : mapaZaposlenih.entrySet()) {
			if(entry.getValue().getTip_zaposlen().equals(Zaposlen.tipovi.SOBARICA.getTip())) {
				if(!mapaSobarica.containsKey(entry.getKey())) {
					mapaSobarica.put(entry.getKey(), new ArrayList<Integer>());
				}
			}
		}
	}

	public void Cuvanje() throws IOException {
		Pisanje_Ucitavanje.PisanjeCenovnik(mapaCenovnik);
		Pisanje_Ucitavanje.PisanjeDodatneUslugeHotela(mapaDodatneUslugeHotela);
		Pisanje_Ucitavanje.PisanjeKorisnik(mapaGosti);
		Pisanje_Ucitavanje.PisanjeRezervacija(mapaRezervacija);
		Pisanje_Ucitavanje.PisanjeSobe(mapaSoba);
		Pisanje_Ucitavanje.PisanjeZaposlen(mapaZaposlenih);
		Pisanje_Ucitavanje.PisanjeTipaSobe(mapaTipovaSobe);
		Pisanje_Ucitavanje.PisanjeSobarice(mapaSobarica);
	}

	

	

	public BazaObjekata(HashMap<String, Cenovnik> mapaCenovnik,
			HashMap<String, Dodatne_Usluge_Hotela> mapaDodatneUslugeHotela, HashMap<String, Korisnik> mapaGosti,
			HashMap<Integer, Rezervacija> mapaRezervacija, HashMap<Integer, Soba> mapaSoba,
			HashMap<String, Zaposlen> mapaZaposlenih, HashMap<String, Tip_Soba> mapaTipovaSobe,
			HashMap<String, ArrayList<Integer>> mapaSobarica) {
		super();
		this.mapaCenovnik = mapaCenovnik;
		this.mapaDodatneUslugeHotela = mapaDodatneUslugeHotela;
		this.mapaGosti = mapaGosti;
		this.mapaRezervacija = mapaRezervacija;
		this.mapaSoba = mapaSoba;
		this.mapaZaposlenih = mapaZaposlenih;
		this.mapaTipovaSobe = mapaTipovaSobe;
		this.mapaSobarica = mapaSobarica;
	}

	public BazaObjekata() {
		mapaCenovnik = new HashMap<>();
		mapaDodatneUslugeHotela = new HashMap<>();
		mapaGosti = new HashMap<>();
		mapaSoba = new HashMap<>();
		mapaZaposlenih = new HashMap<>();
		mapaRezervacija = new HashMap<>();
		mapaTipovaSobe = new HashMap<>();
		mapaSobarica = new HashMap<>();
	}

	public HashMap<String, ArrayList<Integer>> getMapaSobarica() {
		return mapaSobarica;
	}

	public void setMapaSobarica(HashMap<String, ArrayList<Integer>> mapaSobarica) {
		this.mapaSobarica = mapaSobarica;
	}

	public HashMap<String, Cenovnik> getMapaCenovnik() {
		return mapaCenovnik;
	}

	public void setMapaCenovnik(HashMap<String, Cenovnik> mapaCenovnik) {
		this.mapaCenovnik = mapaCenovnik;
	}

	public HashMap<String, Dodatne_Usluge_Hotela> getMapaDodatneUslugeHotela() {
		return mapaDodatneUslugeHotela;
	}

	public void setMapaDodatneUslugeHotela(HashMap<String, Dodatne_Usluge_Hotela> mapaDodatneUslugeHotela) {
		this.mapaDodatneUslugeHotela = mapaDodatneUslugeHotela;
	}

	public HashMap<String, Korisnik> getMapaGosti() {
		return mapaGosti;
	}

	public void setMapaGosti(HashMap<String, Korisnik> mapaGosti) {
		this.mapaGosti = mapaGosti;
	}

	

	

	public HashMap<Integer, Rezervacija> getMapaRezervacija() {
		return mapaRezervacija;
	}

	public void setMapaRezervacija(HashMap<Integer, Rezervacija> mapaRezervacija) {
		this.mapaRezervacija = mapaRezervacija;
	}

	public HashMap<Integer, Soba> getMapaSoba() {
		return mapaSoba;
	}

	public void setMapaSoba(HashMap<Integer, Soba> mapaSoba) {
		this.mapaSoba = mapaSoba;
	}

	public HashMap<String, Zaposlen> getMapaZaposlenih() {
		return mapaZaposlenih;
	}

	public void setMapaZaposlenih(HashMap<String, Zaposlen> mapaZaposlenih) {
		this.mapaZaposlenih = mapaZaposlenih;
	}

	public HashMap<String, Tip_Soba> getMapaTipovaSobe() {
		return mapaTipovaSobe;
	}

	public void setMapaTipovaSobe(HashMap<String, Tip_Soba> mapaTipovaSobe) {
		this.mapaTipovaSobe = mapaTipovaSobe;
	}

}
