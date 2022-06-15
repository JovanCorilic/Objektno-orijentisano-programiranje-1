package guiSoba;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import objekti.BazaObjekata;
import objekti.Soba;
import objekti.Tip_Soba;

public class PravljenjeSobeProzor extends JFrame{
	public PravljenjeSobeProzor(BazaObjekata bazaObjekata) {
		setTitle("Pravljenje sobe");

		setSize(300, 120);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(4, 2));
		setResizable(false);
		
		add(new JLabel("Broj sobe"));
		JTextField areaBrojSobe = new JTextField();
		add(areaBrojSobe);
		add(new JLabel("Status"));
		JComboBox<String>boxStatus = new JComboBox<>();
		boxStatus.addItem(Soba.Statusi.SLO.getVrednost());
		boxStatus.addItem(Soba.Statusi.SPR.getVrednost());
		boxStatus.addItem(Soba.Statusi.ZAU.getVrednost());
		add(boxStatus);
		add(new JLabel("Tip sobe"));
		JComboBox<String>boxTipSobe = new JComboBox<>();
		for(Tip_Soba tip_Soba : bazaObjekata.getMapaTipovaSobe().values()) {
			boxTipSobe.addItem(tip_Soba.getNaziv_tipa());
		}
		add(boxTipSobe);
		JButton buttonSave = new JButton("Napravi");
		buttonSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Soba soba = new Soba();

					soba.unosObjekta(0, areaBrojSobe.getText());
					ArithmeticException exception = new ArithmeticException();

					if (bazaObjekata.getMapaSoba().containsKey(soba.getBrojSobe())) {
						throw exception;
					}
					soba.unosObjekta(1, boxStatus.getSelectedItem().toString());
					soba.unosObjekta(2, boxTipSobe.getSelectedItem().toString());
					
					
					
					
					bazaObjekata.getMapaSoba().put(soba.getBrojSobe(), soba);

					SveSobeProzor sveSobeProzor = new SveSobeProzor(bazaObjekata);
					sveSobeProzor.setVisible(true);
					dispose();
				} catch (ArithmeticException exception) {
					JOptionPane.showMessageDialog(null, "Već postoji soba sa tim brojem!", "Greška",
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
				SveSobeProzor sveSobeProzor = new SveSobeProzor(bazaObjekata);
				sveSobeProzor.setVisible(true);
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
