package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GlavniProzor extends JFrame {
	public GlavniProzor() {
		setTitle("Glavni Prozor");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		setSize(dimension.width, dimension.height);
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
		
		JButton btnCenovnik = new JButton();
		btnCenovnik.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SviCenovniciProzor cenovniciProzor = new SviCenovniciProzor();
				cenovniciProzor.setVisible(true);
				
			}
		});
		
		add(jPanel);
	}
}
