package guiZaposlen;

import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import glavni.KonverterDatum;
import objekti.BazaObjekata;

public class PravljenjeZaposlenogProzor extends JFrame{
	public PravljenjeZaposlenogProzor(BazaObjekata bazaObjekata) {
		setTitle("Upisivanje gosta");

		setSize(700, 200);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(13, 2));
		setResizable(false);
		
		
		
		
	}

}
