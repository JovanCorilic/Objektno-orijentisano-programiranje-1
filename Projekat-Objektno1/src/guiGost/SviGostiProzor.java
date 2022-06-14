package guiGost;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;

import glavni.ButtonColumn;
import objekti.BazaObjekata;
import objekti.Korisnik;
import objekti.Rezervacija;

public class SviGostiProzor extends JFrame {
	private String cuvanje;

	public SviGostiProzor(BazaObjekata bazaObjekata) {
		HashMap<String, Korisnik> mapa = bazaObjekata.getMapaGosti();
		setTitle("Svi gosti");
		setSize(800, 400);
		setLocationRelativeTo(null);
		JLabel jLabel = new JLabel("Dupli klik na ćeliju da se edituje");
		JButton createNew = new JButton("Create new");

		createNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PravljenjeGostaProzor pravljenjeGostaProzor = new PravljenjeGostaProzor(bazaObjekata);
				pravljenjeGostaProzor.setVisible(true);
				dispose();

			}
		});

		JPanel jPanel = new JPanel();
		jPanel.add(jLabel);
		jPanel.add(createNew);
		add(jPanel, BorderLayout.NORTH);

		String[] zaglavlja = new String[] { "Email", "Broj pasoša", "Ime", "Prezime", "Pol", "Datum rođenja", "Telefon",
				"Adresa", "Brisanje" };

		String[][] data = new String[mapa.size()][9];
		int br = 0;
		for (Korisnik temp : mapa.values()) {
			String[] lista = temp.toString().split("\\|");
			for (int i = 0; i < lista.length; i++) {
				data[br][i] = lista[i];
			}
			data[br][lista.length] = "Delete";
			br++;
		}

		DefaultTableModel defaultTableModel = new DefaultTableModel(data, zaglavlja);
		JTable jTable = new JTable(defaultTableModel);

		Action delete = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTable jTable = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				mapa.remove(jTable.getValueAt(modelRow, 0).toString());
				((DefaultTableModel) jTable.getModel()).removeRow(modelRow);
				// mapa.remove(jTable.getValueAt(modelRow, 0).toString());

			}
		};

		Button deleteButton = new Button("Delete");

		ButtonColumn buttonColumn = new ButtonColumn(jTable, delete, 8);

		jTable.setCellSelectionEnabled(true);

		DefaultCellEditor cellEditor = new DefaultCellEditor(new JTextField());
		cellEditor.addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingStopped(ChangeEvent e) {
				try {

					final DefaultCellEditor defaultCellEditor = (DefaultCellEditor) e.getSource();
					final int row = jTable.getSelectedRow();
					final int column = jTable.getSelectedColumn();

					String temp2 = defaultCellEditor.getCellEditorValue().toString();
					
					if (column == 0) {
						String tempKljuc = cuvanje;
						Korisnik korisnik = mapa.get(tempKljuc);
						mapa.remove(tempKljuc);
						korisnik.setEmail(temp2);
						mapa.put(temp2, korisnik);
						for (Rezervacija rezervacija : bazaObjekata.getListaRezervacija()) {
							if(rezervacija.getEmail_gosta().equals(tempKljuc)) {
								rezervacija.setEmail_gosta(temp2);
							}
						}
					} else {
						final String kljuc = (String) jTable.getValueAt(row, 0);
						mapa.get(kljuc).unosObjekta(column, temp2);
					}

				} catch (Exception e2) {
					final int row = jTable.getSelectedRow();
					final int column = jTable.getSelectedColumn();
					jTable.setValueAt(cuvanje, row, column);
				}
			}

			@Override
			public void editingCanceled(ChangeEvent e) {

			}
		});

		for (int i = 0; i < jTable.getColumnCount(); i++) {
			jTable.setDefaultEditor(jTable.getColumnClass(i), cellEditor);
		}

		jTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					final JTable jTable = (JTable) e.getSource();
					final int row = jTable.getSelectedRow();
					final int column = jTable.getSelectedColumn();
					final String valueInCell = (String) jTable.getValueAt(row, column);
					cuvanje = (String) jTable.getValueAt(row, column);
				} catch (Exception e2) {
					cuvanje = "";
				}
			}

		});

		JScrollPane jScrollPane = new JScrollPane(jTable);
		add(jScrollPane);

	}
}
