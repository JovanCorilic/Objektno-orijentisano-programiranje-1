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
import javax.swing.JTextField;

import guiCenovnik.SviCenovniciProzor;
import objekti.BazaObjekata;
import objekti.Cenovnik;
import objekti.Rezervacija;

public class PravljenjeRezervacijeProzor extends JFrame {
	public PravljenjeRezervacijeProzor(BazaObjekata bazaObjekata) {
		setTitle("Pravljenje rezervacije");
		setSize(700, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		JButton buttonSave;
		if (bazaObjekata.getTipKorisnika().equals("")) {
			setLayout(new GridLayout(6, 2));
			add(new JLabel("Status rezervacije"));
			
			add(new JLabel(Rezervacija.Statusi.NACEK.getVrednost()));
			add(new JLabel("Početak važenja (Mora biti u formatu 24.11.2022 13:20)"));
			JTextField areapocetakVazenja = new JTextField("24.11.2022 13:20");
			add(areapocetakVazenja);
			add(new JLabel("Kraj važenja (Mora biti u formatu 24.11.2022 13:20)"));
			JTextField areakrajVazenja = new JTextField("24.11.2022 13:20");
			add(areakrajVazenja);
			add(new JLabel("Email gosta"));
			
			add(new JLabel(bazaObjekata.getEmail()));
			add(new JLabel("Broj pasoša"));
			
			add(new JLabel(bazaObjekata.getMapaGosti().get(bazaObjekata.getEmail()).getLozinka()));
			buttonSave = new JButton("Napravi");
			
			buttonSave.addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Rezervacija rezervacija = new Rezervacija();
	
						rezervacija.unosObjekta(0, Rezervacija.Statusi.NACEK.getVrednost());
	
						rezervacija.unosObjekta(1, areapocetakVazenja.getText());
						rezervacija.unosObjekta(2, areakrajVazenja.getText());
	
						rezervacija.unosObjekta(4, bazaObjekata.getEmail());
						
	
						rezervacija.unosObjekta(5, bazaObjekata.getMapaGosti().get(bazaObjekata.getEmail()).getLozinka());
	
						rezervacija.setId(bazaObjekata.getMapaRezervacija().size());
						bazaObjekata.getMapaRezervacija().put(bazaObjekata.getMapaRezervacija().size() , rezervacija);
	
						SveRezervacijeProzor rezervacijeProzor = new SveRezervacijeProzor(bazaObjekata);
						rezervacijeProzor.setVisible(true);
						dispose();
	
					}  catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Jedno ili više polja ste pogrešno uneli!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					}
	
				}
			});
		}else {
			setLayout(new GridLayout(7, 2));
			add(new JLabel("Status rezervacije"));
			JComboBox<String> box = new JComboBox<>();
			box.addItem(Rezervacija.Statusi.NACEK.getVrednost());
			box.addItem(Rezervacija.Statusi.ODBIJ.getVrednost());
			box.addItem(Rezervacija.Statusi.OTKAZ.getVrednost());
			box.addItem(Rezervacija.Statusi.POTVR.getVrednost());
			add(box);
			add(new JLabel("Početak važenja (Mora biti u formatu 24.11.2022 13:20)"));
			JTextField areapocetakVazenja = new JTextField("24.11.2022 13:20");
			add(areapocetakVazenja);
			add(new JLabel("Kraj važenja (Mora biti u formatu 24.11.2022 13:20)"));
			JTextField areakrajVazenja = new JTextField("24.11.2022 13:20");
			add(areakrajVazenja);
			add(new JLabel("Broj sobe"));
			JTextField areabroj_sobe = new JTextField();
			add(areabroj_sobe);
			add(new JLabel("Email gosta"));
			JTextField areaEmail = new JTextField();
			add(areaEmail);
			add(new JLabel("Broj pasoša"));
			JTextField areaPasos = new JTextField();
			add(areaPasos);
		
		
		
			buttonSave = new JButton("Napravi");
			buttonSave.addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Rezervacija rezervacija = new Rezervacija();
	
						rezervacija.unosObjekta(0, box.getSelectedItem().toString());
	
						rezervacija.unosObjekta(1, areapocetakVazenja.getText());
						rezervacija.unosObjekta(2, areakrajVazenja.getText());
	
						rezervacija.unosObjekta(4, areaEmail.getText());
						ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException = new ArrayIndexOutOfBoundsException();
						if (!bazaObjekata.getMapaGosti().containsKey(rezervacija.getEmail_gosta()) && areaPasos.getText().equals(""))
							throw arrayIndexOutOfBoundsException;
	
						rezervacija.unosObjekta(5, areaPasos.getText());
	
						rezervacija.unosObjekta(3, areabroj_sobe.getText());
						ExceptionInInitializerError expError = new ExceptionInInitializerError();
						if (!bazaObjekata.getMapaSoba().containsKey(rezervacija.getBroj_sobe()))
							throw expError;
	
						if (areaEmail.getText().equals("") && areaPasos.getText().equals("")
								&& areabroj_sobe.getText().equals("")) {
							ArithmeticException exception = new ArithmeticException();
							throw exception;
						}
						rezervacija.setId(bazaObjekata.getMapaRezervacija().size());
						bazaObjekata.getMapaRezervacija().put(bazaObjekata.getMapaRezervacija().size() , rezervacija);
	
						SveRezervacijeProzor rezervacijeProzor = new SveRezervacijeProzor(bazaObjekata);
						rezervacijeProzor.setVisible(true);
						dispose();
	
					} catch (ArithmeticException e3) {
						JOptionPane.showMessageDialog(null, "Fale esencijalne stvari za rezervaciju!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} catch (ExceptionInInitializerError e2) {
						JOptionPane.showMessageDialog(null, "Ne postoji soba sa tim brojem!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} catch (ArrayIndexOutOfBoundsException e2) {
						JOptionPane.showMessageDialog(null, "Ne postoji gost sa tim email-om i niste upisali broj pasoša!", "Greška",
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
				SveRezervacijeProzor rezervacijeProzor = new SveRezervacijeProzor(bazaObjekata);
				rezervacijeProzor.setVisible(true);
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
