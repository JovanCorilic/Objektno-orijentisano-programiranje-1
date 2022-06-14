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

import glavni.KonverterDatum;
import objekti.BazaObjekata;
import objekti.Korisnik;

public class PravljenjeGostaProzor extends JFrame{
	public PravljenjeGostaProzor(BazaObjekata bazaObjekata) {
		setTitle("Upisivanje gosta");
		
		setSize(700, 200);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(9, 2));
		
		add(new JLabel("Email"));
		JTextArea areaemail = new JTextArea();
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
		add(new JLabel("Datum roðenja"));
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
					
					gost.unosObjekta(1, areacena.getText());
					gost.unosObjekta(2, areapocetakVazenja.getText());
					gost.unosObjekta(3, areakrajVazenja.getText());
					if (areabroj_sobe.getText().equals("")) {
						gost.unosObjekta(5, areadodatna_usluga_hotela.getText());
						ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException = new ArrayIndexOutOfBoundsException();
						if (!bazaObjekata.getMapaDodatneUslugeHotela().containsKey(gost.getDodatna_usluga_hotela()))
							throw arrayIndexOutOfBoundsException;
					} else {
						gost.unosObjekta(4, areabroj_sobe.getText());
						ExceptionInInitializerError expError = new ExceptionInInitializerError();
						if (!bazaObjekata.getMapaSoba().containsKey(gost.getBroj_sobe()))
							throw expError;
					}

					

					bazaObjekata.getMapaCenovnik().put(gost.getNaziv(), gost);

					SviGostiProzor gostiProzor = new SviGostiProzor(bazaObjekata);
					gostiProzor.setVisible(true);
					dispose();
				} catch (ArithmeticException exception) {
					JOptionPane.showMessageDialog(null, "Veæ postoji gost sa tim email-om!", "Greška",
							JOptionPane.ERROR_MESSAGE);
				} catch (ExceptionInInitializerError e2) {
					JOptionPane.showMessageDialog(null, "Ne postoji soba sa tim brojem!", "Greška",
							JOptionPane.ERROR_MESSAGE);
				} catch (ArrayIndexOutOfBoundsException e2) {
					JOptionPane.showMessageDialog(null, "Ne postoji dodatna usluga sa tim nazivom!", "Greška",
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
