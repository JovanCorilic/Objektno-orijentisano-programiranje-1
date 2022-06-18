package glavni;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import guiCenovnik.SviCenovniciProzor;
import guiDodatneUslugeHotela.SveDodatneUslugeHotelaProzor;
import guiGost.SviGostiProzor;
import guiRezervacija.SveRezervacijeProzor;
import guiSoba.SveSobeProzor;
import guiTipSobe.SviTipoviSobeProzor;
import guiZaposlen.SviZaposleniProzor;
import objekti.Cenovnik;
import objekti.Dodatne_Usluge_Hotela;
import objekti.Korisnik;
import objekti.Rezervacija;
import objekti.Soba;
import objekti.Tip_Soba;
import objekti.Zaposlen;
import objekti.BazaObjekata;

public class GlavniProzor extends JFrame {
	private HashMap<String, Cenovnik> mapaCenovnik;

	public GlavniProzor(BazaObjekata bazaObjekata, String email, String tipKorisnika) {

		

		setTitle("Glavni Prozor");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		setSize(400, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				int choice = JOptionPane.showConfirmDialog(null, "Da li ste sigruni?", "Pitanje",
						JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					dispose();
					System.exit(EXIT_ON_CLOSE);
				}
			}

		});

		JPanel jPanel = new JPanel();

		JButton btnCenovnik = new JButton("Cenovnik");
		btnCenovnik.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SviCenovniciProzor cenovniciProzor = new SviCenovniciProzor(bazaObjekata);
				cenovniciProzor.setVisible(true);

			}
		});
		jPanel.add(btnCenovnik);
		
		JButton btnRezervacija = new JButton("Rezervacija");
		btnRezervacija.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SveRezervacijeProzor rezervacijeProzor = new SveRezervacijeProzor(bazaObjekata);
				rezervacijeProzor.setVisible(true);
				
			}
		});
		jPanel.add(btnRezervacija);
		
		
		JButton btnGosti = new JButton("Gosti");
		btnGosti.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SviGostiProzor rezervacijeProzor = new SviGostiProzor(bazaObjekata);
				rezervacijeProzor.setVisible(true);
				
			}
		});
		jPanel.add(btnGosti);
		
		JButton btnZaposleni = new JButton("Zaposleni");
		btnZaposleni.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SviZaposleniProzor rezervacijeProzor = new SviZaposleniProzor(bazaObjekata);
				rezervacijeProzor.setVisible(true);
				
			}
		});
		jPanel.add(btnZaposleni);
		
		JButton btnDodatne = new JButton("Dodatne usluge hotela");
		btnDodatne.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SveDodatneUslugeHotelaProzor rezervacijeProzor = new SveDodatneUslugeHotelaProzor(bazaObjekata);
				rezervacijeProzor.setVisible(true);
				
			}
		});
		jPanel.add(btnDodatne);
		
		JButton btnSobe = new JButton("Sobe");
		btnSobe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SveSobeProzor rezervacijeProzor = new SveSobeProzor(bazaObjekata);
				rezervacijeProzor.setVisible(true);
				
			}
		});
		jPanel.add(btnSobe);
		
		JButton btnTipSobe = new JButton("Tip sobe");
		btnTipSobe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SviTipoviSobeProzor rezervacijeProzor = new SviTipoviSobeProzor(bazaObjekata);
				rezervacijeProzor.setVisible(true);
				
			}
		});
		jPanel.add(btnTipSobe);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginProzor loginProzor = new LoginProzor(bazaObjekata);
				loginProzor.setVisible(true);
				dispose();
				
			}
		});
		jPanel.add(btnLogout);
		
		add(jPanel);
	}

	
}
