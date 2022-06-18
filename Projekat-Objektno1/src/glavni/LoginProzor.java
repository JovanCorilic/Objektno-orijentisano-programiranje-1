package glavni;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import objekti.BazaObjekata;


public class LoginProzor extends JFrame {
	public LoginProzor(BazaObjekata bazaObjekata){
		
		
		setSize(300, 150);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTextField pretragaArea = new JTextField();
		GhostText ghostText = new GhostText(pretragaArea, "Unesite ovde email...");
		pretragaArea.setPreferredSize(new Dimension(200, 25));
		JPasswordField lozinka = new JPasswordField();
		lozinka.setPreferredSize(new Dimension(200, 25));
		JPanel jPanel = new JPanel();
		jPanel.add(pretragaArea);
		jPanel.add(lozinka);
		add(jPanel);
		JButton loginDugme = new JButton("Uloguj se");
		jPanel.add(loginDugme);
		loginDugme.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = pretragaArea.getText();
				String tempLozinka = String.valueOf(lozinka.getPassword());
				if (bazaObjekata.getMapaZaposlenih().containsKey(temp)) {
					if (bazaObjekata.getMapaZaposlenih().get(temp).getLozinka().equals(tempLozinka)) {
						GlavniProzor glavniProzor = new GlavniProzor(bazaObjekata, temp,
								bazaObjekata.getMapaZaposlenih().get(temp).getTip_zaposlen());
						glavniProzor.setVisible(true);
						dispose();
						
					}else {
						JOptionPane.showMessageDialog(null, "Ne valja lozinka!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					}
				}else if(bazaObjekata.getMapaGosti().containsKey(temp)) {
					if (bazaObjekata.getMapaGosti().get(temp).getLozinka().equals(tempLozinka)) {
						GlavniProzor glavniProzor = new GlavniProzor(bazaObjekata, temp,"");
						glavniProzor.setVisible(true);
						dispose();
						
					}else {
						JOptionPane.showMessageDialog(null, "Ne valja lozinka!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Akaunt sa ovim emailom ne postoji!", "Greška",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

	}
	
	
}
