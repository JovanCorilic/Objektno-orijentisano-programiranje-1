package guiTipSobe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import objekti.BazaObjekata;
import objekti.Tip_Soba;

public class PravljenjeTipaSobeProzor extends JFrame{
	public PravljenjeTipaSobeProzor(BazaObjekata bazaObjekata) {
		setTitle("Pravljenje dodatne usluge hotela");
		setSize(500, 120);
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
					Tip_Soba tip_sobe = new Tip_Soba();

					tip_sobe.unosObjekta(0, areaNaziv.getText());
					ArithmeticException exception = new ArithmeticException();

					if (bazaObjekata.getMapaTipovaSobe().containsKey(tip_sobe.getNaziv_tipa())) {
						throw exception;
					}
					
					tip_sobe.unosObjekta(1, areacena.getText());

					bazaObjekata.getMapaTipovaSobe().put(tip_sobe.getNaziv_tipa(), tip_sobe);

					SviTipoviSobeProzor sviTipoviSobeProzor = new SviTipoviSobeProzor(bazaObjekata);
					sviTipoviSobeProzor.setVisible(true);
					dispose();
				} catch (ArithmeticException exception) {
					JOptionPane.showMessageDialog(null, "Već postoji tip sobe sa tim nazivom!", "Greška",
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
				SviTipoviSobeProzor sviTipoviSobeProzor = new SviTipoviSobeProzor(bazaObjekata);
				sviTipoviSobeProzor.setVisible(true);
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
