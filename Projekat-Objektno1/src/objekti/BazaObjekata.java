package objekti;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import glavni.Pisanje_Ucitavanje;

public class BazaObjekata {
	private HashMap<String, Cenovnik> mapaCenovnik;
	private HashMap<String, Dodatne_Usluge_Hotela> mapaDodatneUslugeHotela;
	private HashMap<String, Korisnik> mapaGosti;
	private ArrayList<Rezervacija> listaRezervacija;
	private HashMap<Integer, Soba> mapaSoba;
	private HashMap<String, Zaposlen> mapaZaposlenih;
	private ArrayList<Tip_Soba> listaTipovaSoba;

	public void ucitavanje() throws IOException {
		mapaCenovnik = Pisanje_Ucitavanje.UcitavanjeCenovnik();
		mapaDodatneUslugeHotela = Pisanje_Ucitavanje.UcitavanjeDodatne_Usluge_Hotela();
		mapaGosti = Pisanje_Ucitavanje.UcitavanjeGosti();
		listaRezervacija = Pisanje_Ucitavanje.UcitavanjeRezervacije();
		listaTipovaSoba=Pisanje_Ucitavanje.UcitavanjeTipSobe();
		mapaSoba = Pisanje_Ucitavanje.UcitavanjeSobe();
		mapaZaposlenih = Pisanje_Ucitavanje.UcitavanjeZaposleni();
	}

	public void Cuvanje() throws IOException {
		Pisanje_Ucitavanje.PisanjeCenovnik(mapaCenovnik);
		Pisanje_Ucitavanje.PisanjeDodatneUslugeHotela(mapaDodatneUslugeHotela);
		Pisanje_Ucitavanje.PisanjeKorisnik(mapaGosti);
		Pisanje_Ucitavanje.PisanjeRezervacija(listaRezervacija);
		Pisanje_Ucitavanje.PisanjeSobe(mapaSoba);
		Pisanje_Ucitavanje.PisanjeZaposlen(mapaZaposlenih);
		Pisanje_Ucitavanje.PisanjeTipaSobe(listaTipovaSoba);

	}

	public BazaObjekata(HashMap<String, Cenovnik> mapaCenovnik,
			HashMap<String, Dodatne_Usluge_Hotela> mapaDodatneUslugeHotela, HashMap<String, Korisnik> mapaGosti,
			ArrayList<Rezervacija> listaRezervacija, HashMap<Integer, Soba> mapaSoba,
			HashMap<String, Zaposlen> mapaZaposlenih, ArrayList<Tip_Soba> listaTipovaSoba) {
		super();
		this.mapaCenovnik = mapaCenovnik;
		this.mapaDodatneUslugeHotela = mapaDodatneUslugeHotela;
		this.mapaGosti = mapaGosti;
		this.listaRezervacija = listaRezervacija;
		this.mapaSoba = mapaSoba;
		this.mapaZaposlenih = mapaZaposlenih;
		this.listaTipovaSoba = listaTipovaSoba;
	}

	public BazaObjekata() {
		mapaCenovnik = new HashMap<>();
		mapaDodatneUslugeHotela = new HashMap<>();
		mapaGosti = new HashMap<>();
		mapaSoba = new HashMap<>();
		mapaZaposlenih = new HashMap<>();
		listaRezervacija = new ArrayList<>();
		listaTipovaSoba = new ArrayList<>();
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

	public ArrayList<Rezervacija> getListaRezervacija() {
		return listaRezervacija;
	}

	public void setListaRezervacija(ArrayList<Rezervacija> listaRezervacija) {
		this.listaRezervacija = listaRezervacija;
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

	public ArrayList<Tip_Soba> getListaTipovaSoba() {
		return listaTipovaSoba;
	}

	public void setListaTipovaSoba(ArrayList<Tip_Soba> listaTipovaSoba) {
		this.listaTipovaSoba = listaTipovaSoba;
	}

}
