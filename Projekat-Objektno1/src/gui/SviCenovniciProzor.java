package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import objekti.Cenovnik;

public class SviCenovniciProzor extends JFrame {
	public SviCenovniciProzor(HashMap<String, Cenovnik> mapa) {
		setTitle("Svi cenovnici");
		// kliknuti na celiju da se edituje
		JLabel jLabel = new JLabel("Kliknuti na celiju da se edituje");
		JButton createNew = new JButton("Create new");
		JPanel jPanel = new JPanel();
		jPanel.add(jLabel);
		jPanel.add(createNew);
		add(jPanel, BorderLayout.NORTH);

		String[] zaglavlja = new String[] { "Naziv", "Cena", "Poèetak važenja", "Kraj važenja", "Broj sobe",
				"Dodatna usluga hotela", "Brisanje" };
		String[][] data = new String[mapa.size()][7];
		int br = 0;
		for (Cenovnik temp : mapa.values()) {
			String[] lista = temp.toString().split("|");
			for (int i = 0; i < lista.length; i++) {
				data[br][i] = lista[i];
			}
			data[br][lista.length] = "Delete";
			br++;
		}
		
		JTable jTable = new JTable(data,zaglavlja);
		Action delete = new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JTable jTable = (JTable)e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				((DefaultTableModel)jTable.getModel()).removeRow(modelRow);
				
			}
		};
		Button deleteButton = new Button("Delete");
		ButtonColumn buttonColumn = new ButtonColumn(jTable, delete, 7);
		JScrollPane jScrollPane = new JScrollPane(jTable);
		add(jScrollPane);
		
	}
}
