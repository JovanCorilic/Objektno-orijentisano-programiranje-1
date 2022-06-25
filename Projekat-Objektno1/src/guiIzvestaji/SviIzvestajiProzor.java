package guiIzvestaji;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import glavni.KonverterDatum;
import objekti.BazaObjekata;
import objekti.OciscenaSoba;

public class SviIzvestajiProzor extends JFrame {
	private boolean kliknutoDugme;
	private LocalDateTime pocetak;
	private LocalDateTime kraj;
	
	public SviIzvestajiProzor(BazaObjekata bazaObjekata) {
		setTitle("Svi izveštaji");
		setSize(500, 500);
		setLocationRelativeTo(null);
		JTextField pocetniDatum = new JTextField("24.11.2022 11:00");
		JTextField krajnjiDatum = new JTextField("24.11.2022 11:00");
		JButton btnOpseg = new JButton("U ospegu unetih datuma");
		kliknutoDugme=false;
		
		btnOpseg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					LocalDateTime temp1 = KonverterDatum.konvertovanjeUDateTime(pocetniDatum.getText());
					LocalDateTime temp2 = KonverterDatum.konvertovanjeUDateTime(krajnjiDatum.getText());
					if(pocetak.isAfter(kraj))
						throw new Exception();
					kliknutoDugme = true;
					pocetak=temp1;
					kraj=temp2;
					JOptionPane.showMessageDialog(null, "Postavljen je opseg za date datume","Info",JOptionPane.INFORMATION_MESSAGE);
					
				}catch (Exception e23) {
					JOptionPane.showMessageDialog(null, "Datumi su pogrešno uneti!", "Greška",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		JPanel jPanel = new JPanel();
		jPanel.add(pocetniDatum);
		jPanel.add(krajnjiDatum);
		jPanel.add(btnOpseg);
		add(jPanel, BorderLayout.NORTH);

		JButton btnSobarica = new JButton("Koliko je soba sobarica spremila");
		btnSobarica.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String text = "";
				for (Map.Entry<String, ArrayList<OciscenaSoba>> entity : bazaObjekata.getMapaOciscenihSobaSobarica()
						.entrySet()) {
					if (!text.equals(""))
						text += "\n";
					text += "Sobarica : " + entity.getKey() + " "
							+ bazaObjekata.getMapaZaposlenih().get(entity.getKey()).getIme() + " "
							+ bazaObjekata.getMapaZaposlenih().get(entity.getKey()).getPrezime();
					
					for(OciscenaSoba ociscenaSoba : entity.getValue()) {
						if(kliknutoDugme) {
							if(ociscenaSoba.getVreme_kada_je_ocisceno().isBefore(pocetak) || ociscenaSoba.getVreme_kada_je_ocisceno().isAfter(kraj))
								continue;
						}
						text+="\n";
						text +="Broj sobe: "+ ociscenaSoba.getBroj_sobe() + " Vreme: " + KonverterDatum.konvertovanjeUString(ociscenaSoba.getVreme_kada_je_ocisceno());
					}
				}
				try {
					PrintWriter printWriter = new PrintWriter(new FileWriter("IzvestajOciscenihSoba.txt",false));
					printWriter.print(text);
					printWriter.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		JPanel jPanel2 = new JPanel();
		jPanel2.add(btnSobarica);
		
		
		
		
		add(jPanel2);
	}
}
