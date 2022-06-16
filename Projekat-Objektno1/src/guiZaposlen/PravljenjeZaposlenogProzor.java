package guiZaposlen;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import glavni.KonverterDatum;
import objekti.BazaObjekata;
import objekti.Zaposlen;

public class PravljenjeZaposlenogProzor extends JFrame{
	public PravljenjeZaposlenogProzor(BazaObjekata bazaObjekata) {
		setTitle("Upisivanje gosta");

		setSize(700, 400);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(13, 2));
		setResizable(false);
		
		add(new JLabel("Email"));
		JTextField areaemail = new JTextField();
		add(areaemail);
		add(new JLabel("Broj pasoša"));
		JTextField arealozinka = new JTextField();
		add(arealozinka);
		add(new JLabel("Ime"));
		JTextField areaime = new JTextField();
		add(areaime);
		add(new JLabel("Prezime"));
		JTextField areaprezime = new JTextField();
		add(areaprezime);
		add(new JLabel("Pol"));
		JTextField areapol = new JTextField();
		add(areapol);
		add(new JLabel("Datum rođenja (Mora biti u formatu 24.11.2022)"));
		JTextField areadatumRodjenja = new JTextField(KonverterDatum.konvertovanjeSamoDatumUString(LocalDate.now()));
		add(areadatumRodjenja);
		add(new JLabel("Telefon"));
		JTextField areatelefon = new JTextField();
		add(areatelefon);
		add(new JLabel("Adresa"));
		JTextField areaadresa = new JTextField();
		add(areaadresa);
		add(new JLabel("Nivo stručne spreme"));
		JTextField areanivo_strucne_spreme = new JTextField();
		add(areanivo_strucne_spreme);
		add(new JLabel("Godina staža"));
		JTextField areagodina_staza = new JTextField();
		add(areagodina_staza);
		add(new JLabel("Plata"));
		JTextField areaplata = new JTextField();
		add(areaplata);
		add(new JLabel("Tip zaposlenog"));
		JComboBox<String> box = new JComboBox<>();
		
		box.addItem(Zaposlen.tipovi.REC.getTip());
		box.addItem(Zaposlen.tipovi.SOBARICA.getTip());
		add(box);
		
		JButton buttonSave = new JButton("Napravi");
		buttonSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Zaposlen zaposlen = new Zaposlen();

					zaposlen.unosObjektaZaposlen(0, areaemail.getText());
					ArithmeticException exception = new ArithmeticException();

					if (bazaObjekata.getMapaCenovnik().containsKey(zaposlen.getEmail())) {
						throw exception;
					}

					zaposlen.unosObjektaZaposlen(1, arealozinka.getText());
					zaposlen.unosObjektaZaposlen(2, areaime.getText());
					zaposlen.unosObjektaZaposlen(3, areaprezime.getText());
					zaposlen.unosObjektaZaposlen(4, areapol.getText());
					zaposlen.unosObjektaZaposlen(5, areadatumRodjenja.getText());
					zaposlen.unosObjektaZaposlen(6, areatelefon.getText());
					zaposlen.unosObjektaZaposlen(7, areaadresa.getText());
					zaposlen.unosObjektaZaposlen(8, areanivo_strucne_spreme.getText());
					zaposlen.unosObjektaZaposlen(9, areagodina_staza.getText());
					
					ExceptionInInitializerError exceptionInInitializerError = new ExceptionInInitializerError();
					if(zaposlen.getGodina_staza()<0)
						throw exceptionInInitializerError;
					
					zaposlen.unosObjektaZaposlen(10, areaplata.getText());
					ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException = new ArrayIndexOutOfBoundsException();
					if(zaposlen.getPlata()<0)
						throw arrayIndexOutOfBoundsException;
					
					zaposlen.unosObjektaZaposlen(11, box.getSelectedItem().toString());
					
					bazaObjekata.getMapaZaposlenih().put(zaposlen.getEmail(), zaposlen);

					SviZaposleniProzor sviZaposleniProzor = new SviZaposleniProzor(bazaObjekata);
					sviZaposleniProzor.setVisible(true);
					dispose();
				} catch (ArithmeticException exception) {
					JOptionPane.showMessageDialog(null, "Već postoji zaposlen sa tim email-om!", "Greška",
							JOptionPane.ERROR_MESSAGE);
				}catch (ArrayIndexOutOfBoundsException e2) {
					JOptionPane.showMessageDialog(null, "Plata ne sme biti manja od 0!", "Greška",
							JOptionPane.ERROR_MESSAGE);
				}
				catch (ExceptionInInitializerError e2) {
					JOptionPane.showMessageDialog(null, "Godina staža ne sme biti manja od 0!", "Greška",
							JOptionPane.ERROR_MESSAGE);
				} 
				catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Jedno ili više polja ste pogrešno uneli!", "Greška",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		JButton buttonCancel = new JButton("Natrag");

		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SviZaposleniProzor sviZaposleniProzor = new SviZaposleniProzor(bazaObjekata);
				sviZaposleniProzor.setVisible(true);
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
