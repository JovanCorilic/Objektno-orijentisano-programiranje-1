package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.plaf.OptionPaneUI;

import objekti.BazaObjekata;
import objekti.Cenovnik;
import objekti.Tip_Soba;

public class PravljenjeCenovnikaProzor extends JFrame {
	public PravljenjeCenovnikaProzor(BazaObjekata bazaObjekata) {
		setTitle("Pravljenje cenovnika");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		setSize(700, 200);
		setLocationRelativeTo(null);

		/*
		 * setLayout(new GridBagLayout()); GridBagConstraints bagConstraints = new
		 * GridBagConstraints(); JLabel jLabel1 = new JLabel("Testiranje");
		 * bagConstraints.gridx=0; bagConstraints.gridy=0; bagConstraints.insets=new
		 * Insets(10, 10, 10, 10); add(jLabel1,bagConstraints);
		 */

		setLayout(new GridLayout(7, 2));
		add(new JLabel("Naziv"));
		JTextArea areaNaziv = new JTextArea();
		// areaNaziv.setSize(20,20);
		add(areaNaziv);
		add(new JLabel("Cena"));
		JTextArea areacena = new JTextArea();
		add(areacena);
		add(new JLabel("Poèetak važenja (Mora biti u formatu 24.11.2022 13:20)"));
		JTextArea areapocetakVazenja = new JTextArea("24.11.2022 13:20");
		add(areapocetakVazenja);
		add(new JLabel("Kraj važenja (Mora biti u formatu 24.11.2022 13:20)"));
		JTextArea areakrajVazenja = new JTextArea("24.11.2022 13:20");
		add(areakrajVazenja);
		add(new JLabel("Broj sobe"));
		JTextArea areabroj_sobe = new JTextArea();
		add(areabroj_sobe);
		add(new JLabel("Dodatna usluga hotela"));
		JTextArea areadodatna_usluga_hotela = new JTextArea();
		add(areadodatna_usluga_hotela);

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

		JButton buttonCancel = new JButton("Natrag");

		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SviCenovniciProzor cenovniciProzor = new SviCenovniciProzor(bazaObjekata);
				cenovniciProzor.setVisible(true);
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
