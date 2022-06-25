package objekti;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import glavni.Pisanje_Ucitavanje;

public class BazaObjekata {
	private String email;
	private String tipKorisnika;
	private String pamcenje;
	private int id;
	private HashMap<String, Cenovnik> mapaCenovnik;
	private HashMap<String, Dodatne_Usluge_Hotela> mapaDodatneUslugeHotela;
	private HashMap<String, Korisnik> mapaGosti;
	private HashMap<Integer, Rezervacija> mapaRezervacija;
	private HashMap<Integer, Soba> mapaSoba;
	private HashMap<String, Zaposlen> mapaZaposlenih;
	private HashMap<String, Tip_Soba> mapaTipovaSobe;
	private HashMap<String, ArrayList<Integer>> mapaSobarica;
	private HashMap<Integer, ArrayList<String>> mapaRezervacijaDodatneUsluge;
	private HashMap<String, ArrayList<OciscenaSoba>> mapaOciscenihSobaSobarica;
	private ArrayList<VremePromenaStatusaRezervacije>listaPromeneStatusaRezervacija;

	public String NajslobodnijaSobarica() {
		int min = -1;
		String temp = "";
		for (Map.Entry<String, ArrayList<Integer>> entry : mapaSobarica.entrySet()) {
			if (min == -1) {
				temp = entry.getKey();
				min = entry.getValue().size();
			} else if (min > entry.getValue().size()) {
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
		for (Map.Entry<String, Zaposlen> entry : mapaZaposlenih.entrySet()) {
			if (entry.getValue().getTip_zaposlen().equals(Zaposlen.tipovi.SOBARICA.getTip())) {
				if (!mapaSobarica.containsKey(entry.getKey())) {
					mapaSobarica.put(entry.getKey(), new ArrayList<Integer>());
				}
			}
		}
		mapaRezervacijaDodatneUsluge = Pisanje_Ucitavanje.UcitavanjeRezervacijaDodatneUsluge();
		listaPromeneStatusaRezervacija=Pisanje_Ucitavanje.UcitavanjePromeneStatusaRezervacije();
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
		Pisanje_Ucitavanje.PisanjeRezervacijaDodatneUsluge(mapaRezervacijaDodatneUsluge);
		Pisanje_Ucitavanje.PisanjeOciscenihSobaSobarica(mapaOciscenihSobaSobarica);
		Pisanje_Ucitavanje.PisanjeProemnaStatusaRezervacija(listaPromeneStatusaRezervacija);
	}

	

	public BazaObjekata(String email, String tipKorisnika, HashMap<String, Cenovnik> mapaCenovnik,
			HashMap<String, Dodatne_Usluge_Hotela> mapaDodatneUslugeHotela, HashMap<String, Korisnik> mapaGosti,
			HashMap<Integer, Rezervacija> mapaRezervacija, HashMap<Integer, Soba> mapaSoba,
			HashMap<String, Zaposlen> mapaZaposlenih, HashMap<String, Tip_Soba> mapaTipovaSobe,
			HashMap<String, ArrayList<Integer>> mapaSobarica,
			HashMap<Integer, ArrayList<String>> mapaRezervacijaDodatneUsluge,
			HashMap<String, ArrayList<OciscenaSoba>> mapaOciscenihSobaSobarica) {
		super();
		this.email = email;
		this.tipKorisnika = tipKorisnika;
		this.mapaCenovnik = mapaCenovnik;
		this.mapaDodatneUslugeHotela = mapaDodatneUslugeHotela;
		this.mapaGosti = mapaGosti;
		this.mapaRezervacija = mapaRezervacija;
		this.mapaSoba = mapaSoba;
		this.mapaZaposlenih = mapaZaposlenih;
		this.mapaTipovaSobe = mapaTipovaSobe;
		this.mapaSobarica = mapaSobarica;
		this.mapaRezervacijaDodatneUsluge = mapaRezervacijaDodatneUsluge;
		this.mapaOciscenihSobaSobarica = mapaOciscenihSobaSobarica;
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
		mapaRezervacijaDodatneUsluge = new HashMap<>();
		mapaOciscenihSobaSobarica=new HashMap<>();
		listaPromeneStatusaRezervacija = new ArrayList<>();
	}
	
	
	
	
	

	public ArrayList<VremePromenaStatusaRezervacije> getListaPromeneStatusaRezervacija() {
		return listaPromeneStatusaRezervacija;
	}

	public void setListaPromeneStatusaRezervacija(
			ArrayList<VremePromenaStatusaRezervacije> listaPromeneStatusaRezervacija) {
		this.listaPromeneStatusaRezervacija = listaPromeneStatusaRezervacija;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPamcenje() {
		return pamcenje;
	}

	public void setPamcenje(String pamcenje) {
		this.pamcenje = pamcenje;
	}

	public HashMap<String, ArrayList<Integer>> getMapaSobarica() {
		return mapaSobarica;
	}

	public HashMap<String, ArrayList<OciscenaSoba>> getMapaOciscenihSobaSobarica() {
		return mapaOciscenihSobaSobarica;
	}

	public void setMapaOciscenihSobaSobarica(HashMap<String, ArrayList<OciscenaSoba>> mapaOciscenihSobaSobarica) {
		this.mapaOciscenihSobaSobarica = mapaOciscenihSobaSobarica;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipKorisnika() {
		return tipKorisnika;
	}

	public void setTipKorisnika(String tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
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

	public HashMap<Integer, ArrayList<String>> getMapaRezervacijaDodatneUsluge() {
		return mapaRezervacijaDodatneUsluge;
	}

	public void setMapaRezervacijaDodatneUsluge(HashMap<Integer, ArrayList<String>> mapaRezervacijaDodatneUsluge) {
		this.mapaRezervacijaDodatneUsluge = mapaRezervacijaDodatneUsluge;
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
