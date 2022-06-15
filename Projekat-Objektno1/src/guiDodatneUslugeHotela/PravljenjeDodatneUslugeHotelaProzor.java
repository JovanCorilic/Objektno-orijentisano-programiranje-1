package guiDodatneUslugeHotela;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import objekti.BazaObjekata;
import objekti.Dodatne_Usluge_Hotela;

public class PravljenjeDodatneUslugeHotelaProzor extends JFrame{
	public PravljenjeDodatneUslugeHotelaProzor(BazaObjekata bazaObjekata) {
		setTitle("Pravljenje dodatne usluge hotela");
		setSize(400, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		
		setLayout(new GridLayout(3, 2));
		add(new JLabel("Naziv"));
		JTextField areaNaziv = new JTextField();
		add(areaNaziv);
		add(new JLabel("Deskripcija"));
		JTextField areacena = new JTextField();
		add(areacena);
		
		JButton buttonSave = new JButton("Napravi");
		buttonSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Dodatne_Usluge_Hotela dodatne_usluge_hotela = new Dodatne_Usluge_Hotela();

					dodatne_usluge_hotela.unosObjekta(0, areaNaziv.getText());
					ArithmeticException exception = new ArithmeticException();

					if (bazaObjekata.getMapaCenovnik().containsKey(dodatne_usluge_hotela.getNaziv())) {
						throw exception;
					}
					
					dodatne_usluge_hotela.unosObjekta(1, areacena.getText());

					bazaObjekata.getMapaDodatneUslugeHotela().put(dodatne_usluge_hotela.getNaziv(), dodatne_usluge_hotela);

					SveDodatneUslugeHotelaProzor sveDodatneUslugeHotelaProzor = new SveDodatneUslugeHotelaProzor(bazaObjekata);
					sveDodatneUslugeHotelaProzor.setVisible(true);
					dispose();
				} catch (ArithmeticException exception) {
					JOptionPane.showMessageDialog(null, "Već postoji dodatne usluga hotela sa tim nazivom!", "Greška",
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
				SveDodatneUslugeHotelaProzor sveDodatneUslugeHotelaProzor = new SveDodatneUslugeHotelaProzor(bazaObjekata);
				sveDodatneUslugeHotelaProzor.setVisible(true);
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
