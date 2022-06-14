package guiGost;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import glavni.KonverterDatum;
import objekti.BazaObjekata;
import objekti.Korisnik;

public class PravljenjeGostaProzor extends JFrame {
	public PravljenjeGostaProzor(BazaObjekata bazaObjekata) {
		setTitle("Upisivanje gosta");

		setSize(700, 200);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(9, 2));

		add(new JLabel("Email"));
		JTextField areaemail = new JTextField();
		add(areaemail);
		add(new JLabel("Broj pasoša"));
		JTextArea arealozinka = new JTextArea();
		add(arealozinka);
		add(new JLabel("Ime"));
		JTextArea areaime = new JTextArea();
		add(areaime);
		add(new JLabel("Prezime"));
		JTextArea areaprezime = new JTextArea();
		add(areaprezime);
		add(new JLabel("Pol"));
		JTextArea areapol = new JTextArea();
		add(areapol);
		add(new JLabel("Datum rođenja (Mora biti u formatu 24.11.2022)"));
		JTextArea areadatumRodjenja = new JTextArea(KonverterDatum.konvertovanjeSamoDatumUString(LocalDate.now()));
		add(areadatumRodjenja);
		add(new JLabel("Telefon"));
		JTextArea areatelefon = new JTextArea();
		add(areatelefon);
		add(new JLabel("Adresa"));
		JTextArea areaadresa = new JTextArea();
		add(areaadresa);

		JButton buttonSave = new JButton("Napravi");
		buttonSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Korisnik gost = new Korisnik();

					gost.unosObjekta(0, areaemail.getText());
					ArithmeticException exception = new ArithmeticException();

					if (bazaObjekata.getMapaCenovnik().containsKey(gost.getEmail())) {
						throw exception;
					}

					gost.unosObjekta(1, arealozinka.getText());
					ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException = new ArrayIndexOutOfBoundsException();
					for (Korisnik temp : bazaObjekata.getMapaGosti().values()) {
						if (temp.getLozinka().equals(gost.getLozinka()))
							throw arrayIndexOutOfBoundsException;
					}

					gost.unosObjekta(2, areaime.getText());
					gost.unosObjekta(3, areaprezime.getText());
					gost.unosObjekta(4, areapol.getText());
					gost.unosObjekta(5, areadatumRodjenja.getText());
					gost.unosObjekta(6, areatelefon.getText());
					gost.unosObjekta(7, areaadresa.getText());
					

					bazaObjekata.getMapaGosti().put(gost.getEmail(), gost);

					SviGostiProzor gostiProzor = new SviGostiProzor(bazaObjekata);
					gostiProzor.setVisible(true);
					dispose();
				} catch (ArithmeticException exception) {
					JOptionPane.showMessageDialog(null, "Već postoji gost sa tim email-om!", "Greška",
							JOptionPane.ERROR_MESSAGE);
				} catch (ArrayIndexOutOfBoundsException e2) {
					JOptionPane.showMessageDialog(null, "Već postoji gost sa tim brojem pasoša!", "Greška",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Jedno ili više polja ste pogrešno uneli!", "Greška",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		JButton buttonCancel = new JButton("Natrag");

		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SviGostiProzor gostiProzor = new SviGostiProzor(bazaObjekata);
				gostiProzor.setVisible(true);
				dispose();

			}
		});
		/*
		 * JPanel jPanel = new JPanel(); jPanel.add(buttonSave);
		 * jPanel.add(buttonCancel); add(jPanel,BorderLayout.SOUTH);
		 */
		add(buttonSave);
		add(buttonCancel);
	}
}
