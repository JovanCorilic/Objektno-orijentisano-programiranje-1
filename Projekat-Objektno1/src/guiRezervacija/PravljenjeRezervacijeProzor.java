package guiRezervacija;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.temporal.ChronoUnit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import guiCenovnik.SviCenovniciProzor;
import guiDodatneUslugeHotela.SveDodatneUslugeHotelaProzor;
import objekti.BazaObjekata;
import objekti.Cenovnik;
import objekti.Rezervacija;
import objekti.Tip_Soba;

public class PravljenjeRezervacijeProzor extends JFrame {

	public void IzracunavanjeCene(Rezervacija rezervacija, BazaObjekata bazaObjekata, String text) {
		boolean granica = false;
		for (Cenovnik cenovnik : bazaObjekata.getMapaCenovnik().values()) {
			if (cenovnik.getTip_sobe().equals(text)) {
				if (cenovnik.getPocetakVazenja().isBefore(rezervacija.getDatumPocetka())
						&& cenovnik.getKrajVazenja().isAfter(rezervacija.getDatumPocetka())) {
					if (cenovnik.getKrajVazenja().isAfter(rezervacija.getDatumKraja())) {
						int broj_dana = (int) rezervacija.getDatumPocetka().until(rezervacija.getDatumKraja(),
								ChronoUnit.DAYS);
						rezervacija.setUkupno_zaduzenje(
								rezervacija.getUkupno_zaduzenje() + broj_dana * cenovnik.getCena());
						break;
					} else {
						int broj_dana = (int) rezervacija.getDatumPocetka().until(cenovnik.getKrajVazenja(),
								ChronoUnit.DAYS);
						rezervacija.setUkupno_zaduzenje(
								rezervacija.getUkupno_zaduzenje() + broj_dana * cenovnik.getCena());
						granica = true;
						break;
					}
				}
			}
		}

		if (granica) {
			for (Cenovnik cenovnik : bazaObjekata.getMapaCenovnik().values()) {
				if (cenovnik.getTip_sobe().equals(text)) {
					if (cenovnik.getPocetakVazenja().isAfter(rezervacija.getDatumPocetka())
							&& cenovnik.getKrajVazenja().isAfter(rezervacija.getDatumKraja())
							&& cenovnik.getPocetakVazenja().isBefore(rezervacija.getDatumKraja())) {
						int broj_dana = (int) cenovnik.getPocetakVazenja().until(rezervacija.getDatumKraja(),
								ChronoUnit.DAYS);
						rezervacija.setUkupno_zaduzenje(
								rezervacija.getUkupno_zaduzenje() + broj_dana * cenovnik.getCena());
						break;
					} else if (cenovnik.getPocetakVazenja().isAfter(rezervacija.getDatumPocetka())
							&& cenovnik.getKrajVazenja().isBefore(rezervacija.getDatumKraja())) {
						int broj_dana = (int) cenovnik.getPocetakVazenja().until(cenovnik.getKrajVazenja(),
								ChronoUnit.DAYS);
						rezervacija.setUkupno_zaduzenje(
								rezervacija.getUkupno_zaduzenje() + broj_dana * cenovnik.getCena());
					}
				}
			}
		}
	}

	public void IzracunavanjeDodatnihUsluga(Rezervacija rezervacija, BazaObjekata bazaObjekata) {
		boolean granica = false;
		if (bazaObjekata.getMapaRezervacijaDodatneUsluge().containsKey(rezervacija.getId())) {
			if (bazaObjekata.getMapaRezervacijaDodatneUsluge().get(rezervacija.getId()) != null) {
				for (String text : bazaObjekata.getMapaRezervacijaDodatneUsluge().get(rezervacija.getId())) {
					for (Cenovnik cenovnik : bazaObjekata.getMapaCenovnik().values()) {
						if (cenovnik.getDodatna_usluga_hotela().equals(text)) {
							if (cenovnik.getPocetakVazenja().isBefore(rezervacija.getDatumPocetka())
									&& cenovnik.getKrajVazenja().isAfter(rezervacija.getDatumPocetka())) {
								if (cenovnik.getKrajVazenja().isAfter(rezervacija.getDatumKraja())) {
									int broj_dana = (int) rezervacija.getDatumPocetka()
											.until(rezervacija.getDatumKraja(), ChronoUnit.DAYS);
									rezervacija.setUkupno_zaduzenje(
											rezervacija.getUkupno_zaduzenje() + broj_dana * cenovnik.getCena());
									break;
								} else {
									int broj_dana = (int) rezervacija.getDatumPocetka().until(cenovnik.getKrajVazenja(),
											ChronoUnit.DAYS);
									rezervacija.setUkupno_zaduzenje(
											rezervacija.getUkupno_zaduzenje() + broj_dana * cenovnik.getCena());
									granica = true;
									break;
								}
							}
						}
					}

					if (granica) {
						for (Cenovnik cenovnik : bazaObjekata.getMapaCenovnik().values()) {
							if (cenovnik.getDodatna_usluga_hotela().equals(text)) {
								if (cenovnik.getPocetakVazenja().isAfter(rezervacija.getDatumPocetka())
										&& cenovnik.getKrajVazenja().isAfter(rezervacija.getDatumKraja())
										&& cenovnik.getPocetakVazenja().isBefore(rezervacija.getDatumKraja())) {
									int broj_dana = (int) cenovnik.getPocetakVazenja()
											.until(rezervacija.getDatumKraja(), ChronoUnit.DAYS);
									rezervacija.setUkupno_zaduzenje(
											rezervacija.getUkupno_zaduzenje() + broj_dana * cenovnik.getCena());
									break;
								} else if (cenovnik.getPocetakVazenja().isAfter(rezervacija.getDatumPocetka())
										&& cenovnik.getKrajVazenja().isBefore(rezervacija.getDatumKraja())) {
									int broj_dana = (int) cenovnik.getPocetakVazenja().until(cenovnik.getKrajVazenja(),
											ChronoUnit.DAYS);
									rezervacija.setUkupno_zaduzenje(
											rezervacija.getUkupno_zaduzenje() + broj_dana * cenovnik.getCena());
								}
							}
						}
					}
				}
			}
		}
	}

	public static int pravljenjeID(BazaObjekata bazaObjekata) {
		int max = -1;
		for (Rezervacija rezervacija : bazaObjekata.getMapaRezervacija().values()) {
			if (max < rezervacija.getId()) {
				max = rezervacija.getId();
			}
		}
		return max + 1;
	}

	public PravljenjeRezervacijeProzor(BazaObjekata bazaObjekata) {
		setTitle("Pravljenje rezervacije");
		setSize(700, 240);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JButton buttonSave;
		if (bazaObjekata.getTipKorisnika().equals("")) {
			setLayout(new GridLayout(9, 2));
			add(new JLabel("Status rezervacije"));

			add(new JLabel(Rezervacija.Statusi.NACEK.getVrednost()));
			add(new JLabel("Početak važenja (Mora biti u formatu 24.11.2022 13:20)"));
			JTextField areapocetakVazenja = new JTextField("24.11.2022 13:20");
			add(areapocetakVazenja);
			add(new JLabel("Kraj važenja (Mora biti u formatu 24.11.2022 13:20)"));
			JTextField areakrajVazenja = new JTextField("24.11.2022 13:20");
			add(areakrajVazenja);
			add(new JLabel("Tip sobe"));
			JComboBox<String> boxTipSobe = new JComboBox<>();
			for (Tip_Soba tip_Soba : bazaObjekata.getMapaTipovaSobe().values()) {
				boxTipSobe.addItem(tip_Soba.getNaziv_tipa());
			}
			add(boxTipSobe);
			add(new JLabel("Email gosta"));

			add(new JLabel(bazaObjekata.getEmail()));
			add(new JLabel("Broj pasoša"));

			add(new JLabel(bazaObjekata.getMapaGosti().get(bazaObjekata.getEmail()).getLozinka()));
			add(new JLabel("Broj ljudi"));
			JTextField areaBrojLjudi = new JTextField();
			add(areaBrojLjudi);

			buttonSave = new JButton("Napravi");

			buttonSave.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Rezervacija rezervacija = new Rezervacija();

						rezervacija.unosObjekta(1, Rezervacija.Statusi.NACEK.getVrednost());

						rezervacija.unosObjekta(2, areapocetakVazenja.getText());
						rezervacija.unosObjekta(3, areakrajVazenja.getText());

						rezervacija.setEmail_gosta(bazaObjekata.getEmail());

						rezervacija
								.setBroj_pasosa(bazaObjekata.getMapaGosti().get(bazaObjekata.getEmail()).getLozinka());
						rezervacija.setTip_sobe(boxTipSobe.getSelectedItem().toString());
						rezervacija.setId(pravljenjeID(bazaObjekata));
						rezervacija.unosObjekta(5, areaBrojLjudi.getText());

						rezervacija.setUkupno_zaduzenje(0);

						IzracunavanjeCene(rezervacija, bazaObjekata, rezervacija.getTip_sobe());

						IzracunavanjeDodatnihUsluga(rezervacija, bazaObjekata);

						int choice = JOptionPane.showConfirmDialog(null,
								"Da li ste sigruni ? Ukupno zaduženje je " + rezervacija.getUkupno_zaduzenje() + "din",
								"Pitanje", JOptionPane.YES_NO_OPTION);
						if (choice == JOptionPane.YES_OPTION) {
							bazaObjekata.getMapaRezervacija().put(bazaObjekata.getMapaRezervacija().size(),
									rezervacija);

							SveRezervacijeProzor rezervacijeProzor = new SveRezervacijeProzor(bazaObjekata);
							rezervacijeProzor.setVisible(true);
							dispose();

						}

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Jedno ili više polja ste pogrešno uneli!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			});
		} else {
			setLayout(new GridLayout(9, 2));
			add(new JLabel("Status rezervacije"));
			JComboBox<String> box = new JComboBox<>();
			box.addItem(Rezervacija.Statusi.NACEK.getVrednost());

			add(box);
			add(new JLabel("Početak važenja (Mora biti u formatu 24.11.2022 13:20)"));
			JTextField areapocetakVazenja = new JTextField("24.11.2022 13:20");
			add(areapocetakVazenja);
			add(new JLabel("Kraj važenja (Mora biti u formatu 24.11.2022 13:20)"));
			JTextField areakrajVazenja = new JTextField("24.11.2022 13:20");
			add(areakrajVazenja);
			add(new JLabel("Tip sobe"));
			JComboBox<String> boxTipSobe = new JComboBox<>();
			for (Tip_Soba tip_Soba : bazaObjekata.getMapaTipovaSobe().values()) {
				boxTipSobe.addItem(tip_Soba.getNaziv_tipa());
			}
			add(boxTipSobe);

			add(new JLabel("Email gosta"));
			JTextField areaEmail = new JTextField();
			add(areaEmail);
			add(new JLabel("Broj pasoša"));
			JTextField areaPasos = new JTextField();
			add(areaPasos);
			add(new JLabel("Broj ljudi"));
			JTextField areaBrojLjudi = new JTextField();
			add(areaBrojLjudi);

			buttonSave = new JButton("Napravi");
			buttonSave.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Rezervacija rezervacija = new Rezervacija();

						rezervacija.unosObjekta(1, box.getSelectedItem().toString());

						rezervacija.unosObjekta(2, areapocetakVazenja.getText());
						rezervacija.unosObjekta(3, areakrajVazenja.getText());

						rezervacija.setEmail_gosta(areaEmail.getText());
						ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException = new ArrayIndexOutOfBoundsException();
						if (!bazaObjekata.getMapaGosti().containsKey(rezervacija.getEmail_gosta())
								&& areaPasos.getText().equals(""))
							throw arrayIndexOutOfBoundsException;

						rezervacija.setBroj_pasosa(areaPasos.getText());

						if (areaEmail.getText().equals("") && areaPasos.getText().equals("")
								&& areaBrojLjudi.getText().equals("")) {
							ArithmeticException exception = new ArithmeticException();
							throw exception;
						}
						rezervacija.setId(pravljenjeID(bazaObjekata));
						rezervacija.setTip_sobe(boxTipSobe.getSelectedItem().toString());
						rezervacija.unosObjekta(5, areaBrojLjudi.getText());

						IzracunavanjeCene(rezervacija, bazaObjekata, rezervacija.getTip_sobe());

						IzracunavanjeDodatnihUsluga(rezervacija, bazaObjekata);

						int choice = JOptionPane.showConfirmDialog(null,
								"Da li ste sigruni ? Ukupno zaduženje je " + rezervacija.getUkupno_zaduzenje() + "din",
								"Pitanje", JOptionPane.YES_NO_OPTION);
						if (choice == JOptionPane.YES_OPTION) {
							bazaObjekata.getMapaRezervacija().put(bazaObjekata.getMapaRezervacija().size(),
									rezervacija);

							SveRezervacijeProzor rezervacijeProzor = new SveRezervacijeProzor(bazaObjekata);
							rezervacijeProzor.setVisible(true);
							dispose();

						}

					} catch (ArithmeticException e3) {
						JOptionPane.showMessageDialog(null, "Fale esencijalne stvari za rezervaciju!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} catch (ExceptionInInitializerError e2) {
						JOptionPane.showMessageDialog(null, "Ne postoji soba sa tim brojem!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} catch (ArrayIndexOutOfBoundsException e2) {
						JOptionPane.showMessageDialog(null,
								"Ne postoji gost sa tim email-om i niste upisali broj pasoša!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Jedno ili više polja ste pogrešno uneli!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			});
		}
		JButton buttonCancel = new JButton("Natrag");

		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int kljuc = pravljenjeID(bazaObjekata);
				if (bazaObjekata.getMapaRezervacijaDodatneUsluge().containsKey(kljuc)) {
					bazaObjekata.getMapaRezervacijaDodatneUsluge().remove(kljuc);
				}
				SveRezervacijeProzor rezervacijeProzor = new SveRezervacijeProzor(bazaObjekata);
				rezervacijeProzor.setVisible(true);
				dispose();

			}
		});
		/*
		 * JPanel jPanel = new JPanel(); jPanel.add(buttonSave);
		 * jPanel.add(buttonCancel); add(jPanel,BorderLayout.SOUTH);
		 */
		add(new JLabel("Dodatne usluge hotela"));
		JButton dodatneButton = new JButton("Dodatne usluge hotela");
		dodatneButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bazaObjekata.setPamcenje(bazaObjekata.getTipKorisnika());
				bazaObjekata.setTipKorisnika("Dodatno");
				SveDodatneUslugeHotelaProzor dodatneUslugeHotelaProzor = new SveDodatneUslugeHotelaProzor(bazaObjekata);

				dodatneUslugeHotelaProzor.setVisible(true);

			}
		});
		add(dodatneButton);

		add(buttonSave);
		add(buttonCancel);
	}
}
