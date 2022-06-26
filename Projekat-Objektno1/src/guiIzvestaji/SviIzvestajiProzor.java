package guiIzvestaji;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import glavni.KonverterDatum;
import objekti.BazaObjekata;
import objekti.OciscenaSoba;
import objekti.Rezervacija;
import objekti.Soba;
import objekti.VremePromenaStatusaRezervacije;
import objekti.Zaposlen;

public class SviIzvestajiProzor extends JFrame {
	private boolean kliknutoDugme;
	private LocalDateTime pocetak;
	private LocalDateTime kraj;
	private int br;

	public SviIzvestajiProzor(BazaObjekata bazaObjekata) {
		setTitle("Svi izveštaji");
		setSize(500, 500);
		setLocationRelativeTo(null);
		JTextField pocetniDatum = new JTextField("24.11.2022 11:00");
		JTextField krajnjiDatum = new JTextField("24.11.2022 11:00");
		JButton btnOpseg = new JButton("U ospegu unetih datuma da bude izveštaj");
		kliknutoDugme = false;

		btnOpseg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					LocalDateTime temp1 = KonverterDatum.konvertovanjeUDateTime(pocetniDatum.getText());
					LocalDateTime temp2 = KonverterDatum.konvertovanjeUDateTime(krajnjiDatum.getText());
					if (temp1.isAfter(temp2))
						throw new Exception();
					kliknutoDugme = true;
					pocetak = temp1;
					kraj = temp2;
					JOptionPane.showMessageDialog(null, "Postavljen je opseg za date datume", "Info",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception e23) {
					JOptionPane.showMessageDialog(null, "Datumi su pogrešno uneti!", "Greška",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		JPanel jPanel = new JPanel();
		jPanel.add(pocetniDatum);
		jPanel.add(krajnjiDatum);
		jPanel.add(btnOpseg);
		add(jPanel, BorderLayout.NORTH);

		JButton btnSobarica = new JButton("Koliko je soba sobarica spremila");
		btnSobarica.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String text = "";
				for (Map.Entry<String, ArrayList<OciscenaSoba>> entity : bazaObjekata.getMapaOciscenihSobaSobarica()
						.entrySet()) {
					if (!text.equals(""))
						text += "\n";
					text += "Sobarica : " + entity.getKey() + " "
							+ bazaObjekata.getMapaZaposlenih().get(entity.getKey()).getIme() + " "
							+ bazaObjekata.getMapaZaposlenih().get(entity.getKey()).getPrezime()
							+ " Ukupno očišćenih soba : " + entity.getValue().size();

					for (OciscenaSoba ociscenaSoba : entity.getValue()) {
						if (kliknutoDugme) {
							if (ociscenaSoba.getVreme_kada_je_ocisceno().isBefore(pocetak)
									|| ociscenaSoba.getVreme_kada_je_ocisceno().isAfter(kraj))
								continue;
						}
						text += "\n";
						text += "Broj sobe: " + ociscenaSoba.getBroj_sobe() + " Vreme: "
								+ KonverterDatum.konvertovanjeUString(ociscenaSoba.getVreme_kada_je_ocisceno());
					}
				}

				try {
					PrintWriter printWriter = new PrintWriter(new FileWriter("IzvestajOciscenihSoba.txt", false));
					printWriter.print(text);
					printWriter.close();
					JOptionPane.showMessageDialog(null, "Uspešno napravljen izveštaj", "Info",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		JPanel jPanel2 = new JPanel();
		jPanel2.add(btnSobarica);

		JButton btnRezervacijaPotvrdjeno = new JButton("Potvrđene rezervacije");
		btnRezervacijaPotvrdjeno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ispisRezervacije(Rezervacija.Statusi.POTVR.getVrednost(), "IzvestajPotvrdjenihRezervacija.txt",
						bazaObjekata);
				JOptionPane.showMessageDialog(null, "Uspešno napravljen izveštaj, " + br + " je rezervacija ovde",
						"Info", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		jPanel2.add(btnRezervacijaPotvrdjeno);

		JButton btnRezervacijaOdbijeno = new JButton("Odbijene rezervacije");
		btnRezervacijaOdbijeno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ispisRezervacije(Rezervacija.Statusi.ODBIJ.getVrednost(), "IzvestajOdbijeneRezervacije.txt",
						bazaObjekata);
				JOptionPane.showMessageDialog(null, "Uspešno napravljen izveštaj, " + br + " je rezervacija ovde",
						"Info", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		jPanel2.add(btnRezervacijaOdbijeno);

		JButton btnRezervacijaOtkazana = new JButton("Otkazane rezervacije");
		btnRezervacijaOtkazana.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ispisRezervacije(Rezervacija.Statusi.OTKAZ.getVrednost(), "IzvestajOtkazaneRezervacije.txt",
						bazaObjekata);
				JOptionPane.showMessageDialog(null, "Uspešno napravljen izveštaj, " + br + " je rezervacija ovde",
						"Info", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		jPanel2.add(btnRezervacijaOtkazana);

		JButton btnSobe = new JButton("Izveštaj o sobama");
		btnSobe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String text = "";
				for (Soba soba : bazaObjekata.getMapaSoba().values()) {
					if (!text.equals(""))
						text += "\n";
					text += "Broj sobe " + soba.getBrojSobe() + " Tip sobe " + soba.getTip_Soba() + " ";
					int brojNocenja = 0;
					double ukupnaCena = 0;
					for (Rezervacija rezervacija : bazaObjekata.getMapaRezervacija().values()) {
						if (rezervacija.getStatus().equals(Rezervacija.Statusi.POTVR.getVrednost())) {
							if (rezervacija.getBroj_sobe() == soba.getBrojSobe()) {
								if (kliknutoDugme) {
									if (rezervacija.getDatumKraja().isBefore(pocetak))
										continue;
									else if (rezervacija.getDatumPocetka().isAfter(kraj))
										continue;
								}
								brojNocenja += (int) rezervacija.getDatumPocetka().until(rezervacija.getDatumKraja(),
										ChronoUnit.DAYS);
								ukupnaCena += rezervacija.getUkupno_zaduzenje();
							}
						}
					}

					text += "Ukupan broj noćenja " + brojNocenja;
					text += " Ukupan prihod " + ukupnaCena;
				}
				try {
					PrintWriter printWriter = new PrintWriter(
							new FileWriter("IzvestajUkupanBrojNocenjaIPrihodiSobe.txt"));
					printWriter.print(text);
					printWriter.close();
					JOptionPane.showMessageDialog(null, "Uspešno napravljen izveštaj", "Info",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		jPanel2.add(btnSobe);

		JButton btnPrihodiRaskodi = new JButton("Prihodi i rashodi");
		btnPrihodiRaskodi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String text = "Prihodi:";
				for (VremePromenaStatusaRezervacije rezervacije : bazaObjekata.getListaPromeneStatusaRezervacija()) {
					if (rezervacije.getStatus().equals(Rezervacija.Statusi.POTVR.getVrednost())
							|| rezervacije.getStatus().equals(Rezervacija.Statusi.OTKAZ.getVrednost())) {
						text += "\n";
						text += "ID rezervacije " + rezervacije.getID() + " Ukupan iznos te rezervacije "
								+ bazaObjekata.getMapaRezervacija().get(rezervacije.getID()).getUkupno_zaduzenje()
								+ "din";
					}
				}
				text += "\nRashodi";
				for (Zaposlen zaposlen : bazaObjekata.getMapaZaposlenih().values()) {
					text += "\n";
					text += zaposlen.getIme() + " " + zaposlen.getPrezime() + " " + zaposlen.getTip_zaposlen()
							+ " Plata : " + zaposlen.racunanjePlate() + "din";
				}
				text += "\nOdbijene rezervacije";
				for (VremePromenaStatusaRezervacije rezervacije : bazaObjekata.getListaPromeneStatusaRezervacija()) {
					if (rezervacije.getStatus().equals(Rezervacija.Statusi.ODBIJ.getVrednost())) {
						text += "\n";
						text += "ID rezervacije " + rezervacije.getID() + " Ukupan iznos te rezervacije "
								+ bazaObjekata.getMapaRezervacija().get(rezervacije.getID()).getUkupno_zaduzenje()
								+ "din";
					}
				}
				try {
					PrintWriter printWriter = new PrintWriter(new FileWriter("PrihodiIRashodi.txt"));
					printWriter.print(text);
					printWriter.close();
					JOptionPane.showMessageDialog(null, "Uspešno napravljen izveštaj", "Info",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		jPanel2.add(btnPrihodiRaskodi);

		add(jPanel2);
	}

	public void ispisRezervacije(String status, String lokacija, BazaObjekata bazaObjekata) {
		String text = "";
		br = 0;
		for (VremePromenaStatusaRezervacije rezervacije : bazaObjekata.getListaPromeneStatusaRezervacija()) {
			if (rezervacije.getStatus().equals(status)) {
				if (kliknutoDugme) {
					if (rezervacije.getVremePromeneStatusa().isBefore(pocetak)
							|| rezervacije.getVremePromeneStatusa().isAfter(kraj))
						continue;
				}
				if (!text.equals(""))
					text += "\n";
				text += "Vreme potvrde " + KonverterDatum.konvertovanjeUString(rezervacije.getVremePromeneStatusa())
						+ " Rezervacija: "
						+ bazaObjekata.getMapaRezervacija().get(rezervacije.getID()).toString().replace("|", " ");
				br++;
			}

		}
		text += "\n" + br + " je rezervacija ovde";
		try {
			PrintWriter printWriter = new PrintWriter(new FileWriter(lokacija, false));
			printWriter.print(text);
			printWriter.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
