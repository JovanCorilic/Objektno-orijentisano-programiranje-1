package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import glavni.Pisanje_Ucitavanje;
import objekti.Cenovnik;
import objekti.BazaObjekata;

public class GlavniProzor extends JFrame {
	private HashMap<String, Cenovnik>mapaCenovnik;
	
	public GlavniProzor(BazaObjekata bazaObjekata) throws IOException {
		
		ucitavanje(bazaObjekata);
		
		setTitle("Glavni Prozor");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		setSize(400, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				int choice = JOptionPane.showConfirmDialog(null, "Da li ste sigruni?", "Pitanje",
						JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION)
					dispose();
				
			}

		});
		
		JPanel jPanel = new JPanel();
		
		JButton btnCenovnik = new JButton("Cenovnik");
		btnCenovnik.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SviCenovniciProzor cenovniciProzor = new SviCenovniciProzor(bazaObjekata);
				cenovniciProzor.setVisible(true);
				
			}
		});
		jPanel.add(btnCenovnik);
		
		add(jPanel);
	}
	
	public void ucitavanje(BazaObjekata bazaObjekata) throws IOException {
		//mapaCenovnik= Pisanje_Ucitavanje.UcitavanjeCenovnik();
		
		Cenovnik cenovnik = new Cenovnik("test", 100, LocalDateTime.now(), LocalDateTime.now(), 12, "izmisljeno");
		bazaObjekata.getMapaCenovnik().put("test", cenovnik);
	}
}
