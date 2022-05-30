package zadatak;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Prozor extends JFrame{
	public Prozor() {
		// TODO Auto-generated constructor stub
		setTitle("Prvi prozor");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		//sirina,visina
		Dimension screenSize = toolkit.getScreenSize();
		setSize(screenSize.width/4,screenSize.height/4);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Prozor prozor = new Prozor();
				prozor.setTitle("Drugi prozor");
				prozor.setVisible(true);
				
			}
		});
		JPanel jPanel = new JPanel();
		JButton btnCANCEL = new JButton("Cancel");
		
		
		jPanel.add(btnOK);
		jPanel.add(btnCANCEL);
		add(jPanel,BorderLayout.SOUTH);
		
		
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				int choice = JOptionPane.showConfirmDialog(null, "Da li ste sigruni?","Pitanje",JOptionPane.YES_NO_OPTION);
				if(choice==JOptionPane.YES_OPTION)
					dispose();
			}
			
		});
		
		JLabel jLabel = new JLabel();
		jLabel.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				jLabel.setText(e.getX() + " "+ e.getY());
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		add(jLabel);
	}
	
	
}
