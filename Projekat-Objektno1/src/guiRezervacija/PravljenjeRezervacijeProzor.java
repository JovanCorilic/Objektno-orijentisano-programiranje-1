package guiRezervacija;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import guiCenovnik.SviCenovniciProzor;
import objekti.BazaObjekata;
import objekti.Cenovnik;
import objekti.Rezervacija;

public class PravljenjeRezervacijeProzor extends JFrame {
	public PravljenjeRezervacijeProzor(BazaObjekata bazaObjekata) {
		setTitle("Pravljenje rezervacije");
		setSize(700, 200);
		setLocationRelativeTo(null);

		setLayout(new GridLayout(7, 2));
		add(new JLabel("Status rezervacije"));
		JComboBox<String> box = new JComboBox<>();
		box.addItem(Rezervacija.Statusi.NACEK.getVrednost());
		box.addItem(Rezervacija.Statusi.ODBIJ.getVrednost());
		box.addItem(Rezervacija.Statusi.OTKAZ.getVrednost());
		box.addItem(Rezervacija.Statusi.POTVR.getVrednost());
		add(box);
		add(new JLabel("Poèetak važenja (Mora biti u formatu 24.11.2022 13:20)"));
		JTextArea areapocetakVazenja = new JTextArea("24.11.2022 13:20");
		add(areapocetakVazenja);
		add(new JLabel("Kraj važenja (Mora biti u formatu 24.11.2022 13:20)"));
		JTextArea areakrajVazenja = new JTextArea("24.11.2022 13:20");
		add(areakrajVazenja);
		add(new JLabel("Broj sobe"));
		JTextArea areabroj_sobe = new JTextArea();
		add(areabroj_sobe);
		add(new JLabel("Email gosta"));
		JTextArea areaEmail = new JTextArea();
		add(areaEmail);
		add(new JLabel("Broj pasoša"));
		JTextArea areaPasos = new JTextArea();
		add(areaPasos);

		JButton buttonSave = new JButton("Napravi");
		buttonSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Cenovnik cenovnik = new Cenovnik();

					cenovnik.unosObjekta(0, areaNaziv.getText());
					cenovnik.unosObjekta(1, areacena.getText());
					cenovnik.unosObjekta(2, areapocetakVazenja.getText());
					cenovnik.unosObjekta(3, areakrajVazenja.getText());
					if (areabroj_sobe.getText().equals("")) {
						cenovnik.unosObjekta(5, areadodatna_usluga_hotela.getText());
						ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException = new ArrayIndexOutOfBoundsException();
						if (!bazaObjekata.getMapaDodatneUslugeHotela().containsKey(cenovnik.getDodatna_usluga_hotela()))
							throw arrayIndexOutOfBoundsException;
					} else {
						cenovnik.unosObjekta(4, areabroj_sobe.getText());
						ExceptionInInitializerError expError = new ExceptionInInitializerError();
						if (!bazaObjekata.getMapaSoba().containsKey(cenovnik.getBroj_sobe()))
							throw expError;
					}

					ArithmeticException exception = new ArithmeticException();

					if (bazaObjekata.getMapaCenovnik().containsKey(cenovnik.getNaziv())) {
						throw exception;
					}

					bazaObjekata.getMapaCenovnik().put(cenovnik.getNaziv(), cenovnik);

					SviCenovniciProzor cenovniciProzor = new SviCenovniciProzor(bazaObjekata);
					cenovniciProzor.setVisible(true);
					dispose();
				} catch (ArithmeticException exception) {
					JOptionPane.showMessageDialog(null, "Veæ postoji cenovnik sa tim nazivom!", "Greška",
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
	}
}
