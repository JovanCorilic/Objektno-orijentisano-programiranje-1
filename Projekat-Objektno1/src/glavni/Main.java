package glavni;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import objekti.BazaObjekata;
import objekti.Cenovnik;
import objekti.Dodatne_Usluge_Hotela;
import objekti.Korisnik;
import objekti.Rezervacija;
import objekti.Soba;
import objekti.Tip_Soba;
import objekti.Zaposlen;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BazaObjekata bazaObjekata = new BazaObjekata();
		bazaObjekata.ucitavanje();
		ucitavanje(bazaObjekata);
		
		LoginProzor loginProzor = new LoginProzor(bazaObjekata);
		loginProzor.setVisible(true);
	}
	
	public static void ucitavanje(BazaObjekata bazaObjekata) throws IOException {
		// mapaCenovnik= Pisanje_Ucitavanje.UcitavanjeCenovnik();

		Cenovnik cenovnik = new Cenovnik("test", 100, LocalDateTime.now(), LocalDateTime.now(), "Obična", "izmisljeno");
		Cenovnik cenovnik2 = new Cenovnik("test123", 600, LocalDateTime.now(), LocalDateTime.now(), "Obična", "izmisljeno");
		bazaObjekata.getMapaCenovnik().put("test", cenovnik);
		bazaObjekata.getMapaCenovnik().put(cenovnik2.getNaziv(), cenovnik2);
		Dodatne_Usluge_Hotela dodatne_Usluge_Hotela = new Dodatne_Usluge_Hotela("testDodatne",
				"wefuwfuwhfuwhf wfwjfiwj fiwfihwfhw ihfiwhfiwhfiw hwfihfwih fwihf wi wifhwihfwihfuiggsjfs wfwf.");
		bazaObjekata.getMapaDodatneUslugeHotela().put(dodatne_Usluge_Hotela.getNaziv(), dodatne_Usluge_Hotela);
		Korisnik korisnik = new Korisnik("test@hotmail.com", "35435435454", "Neko", "Nesto", "M", LocalDate.now(),
				"2656545454", "Tamo amo 57");
		bazaObjekata.getMapaGosti().put(korisnik.getEmail(), korisnik);
		Rezervacija rezervacija = new Rezervacija(0,Rezervacija.Statusi.NACEK.getVrednost(), LocalDateTime.now(),
				LocalDateTime.now(),"Obična",3, 12, korisnik.getEmail(), korisnik.getLozinka());
		bazaObjekata.getMapaRezervacija().put(0,rezervacija);
		Tip_Soba tip_Soba = new Tip_Soba("Obična","widfhwuwfwuwufvwsbfvwbv wjkfvwjbfvjwbf");
		bazaObjekata.getMapaTipovaSobe().put(tip_Soba.getNaziv_tipa(),tip_Soba);
		Soba temp = new Soba(12, Soba.Statusi.SLO.getVrednost(), tip_Soba.getNaziv_tipa());
		bazaObjekata.getMapaSoba().put(temp.getBrojSobe(), temp);
		Zaposlen zaposlen = new Zaposlen("1", "1", "Pera", "Perić", "Srednji",
				LocalDate.now(), "45454355454", "Ulica 23", "Srednji", 6, 78952.20, Zaposlen.tipovi.ADMIN.getTip());
		Zaposlen zaposlen2 = new Zaposlen("2", "1", "Nikola", "Perić", "Srednji",
				LocalDate.now(), "45454355454", "Ulica 23", "Srednji", 6, 78952.20, Zaposlen.tipovi.REC.getTip());
		Zaposlen zaposlen3 = new Zaposlen("3", "1", "Tanja", "Perić", "Srednji",
				LocalDate.now(), "45454355454", "Ulica 23", "Srednji", 6, 78952.20, Zaposlen.tipovi.SOBARICA.getTip());
		
		bazaObjekata.getMapaZaposlenih().put(zaposlen2.getEmail(), zaposlen2);
		bazaObjekata.getMapaZaposlenih().put(zaposlen.getEmail(), zaposlen);
		bazaObjekata.getMapaZaposlenih().put(zaposlen3.getEmail(), zaposlen3);
	}
	
}
