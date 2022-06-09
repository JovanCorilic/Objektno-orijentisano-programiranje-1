package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;

public class PravljenjeCenovnikaProzor extends JFrame{
	public PravljenjeCenovnikaProzor() {
		setTitle("Pravljenje cenovnika");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		setSize(700, 200);
		setLocationRelativeTo(null);
		
		/*setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		JLabel jLabel1 = new JLabel("Testiranje");
		bagConstraints.gridx=0;
		bagConstraints.gridy=0;
		bagConstraints.insets=new Insets(10, 10, 10, 10);
		add(jLabel1,bagConstraints);*/
		
		setLayout(new GridLayout(7,2));
		add(new JLabel("Naziv"));
		JTextArea areaNaziv = new JTextArea();
		//areaNaziv.setSize(20,20);
		add(areaNaziv);
		add(new JLabel("Cena"));
		JTextArea areacena = new JTextArea();
		add(areacena);
		add(new JLabel("Poèetak važenja (Mora biti u formatu 24.11.2022 13:20)"));
		JTextArea areapocetakVazenja = new JTextArea();
		add(areapocetakVazenja);
		add(new JLabel("Kraj važenja (Mora biti u formatu 24.11.2022 13:20)"));
		JTextArea areakrajVazenja = new JTextArea();
		add(areakrajVazenja);
		add(new JLabel("Broj sobe"));
		JTextArea areabroj_sobe = new JTextArea();
		add(areabroj_sobe);
		add(new JLabel("Dodatna usluga hotela"));
		JTextArea areadodatna_usluga_hotela = new JTextArea();
		add(areadodatna_usluga_hotela);
		
		JButton buttonSave = new JButton("Napravi");
		JButton buttonCancel = new JButton("Natrag");
		/*JPanel jPanel = new JPanel();
		jPanel.add(buttonSave);
		jPanel.add(buttonCancel);
		add(jPanel,BorderLayout.SOUTH);*/
		add(buttonSave);
		add(buttonCancel);
	}

}
