package guiTipSobe;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;

import glavni.ButtonColumn;
import glavni.GhostText;
import glavni.KonverterDatum;
import objekti.BazaObjekata;
import objekti.Cenovnik;
import objekti.Rezervacija;
import objekti.Soba;
import objekti.Tip_Soba;

public class SviTipoviSobeProzor extends JFrame{
	private String cuvanje;
	public SviTipoviSobeProzor(BazaObjekata bazaObjekata) {
		HashMap<String, Tip_Soba>mapa = bazaObjekata.getMapaTipovaSobe();
		setTitle("Svi tipovi sobe");
		setSize(700, 400);
		setLocationRelativeTo(null);
		
		JLabel jLabel = new JLabel("Dupli klik na ćeliju da se edituje");
		JButton createNew = new JButton("Create new");

		createNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PravljenjeTipaSobeProzor pravljenjeTipaSobeProzor = new PravljenjeTipaSobeProzor(bazaObjekata);
				pravljenjeTipaSobeProzor.setVisible(true);
				dispose();

			}
		});

		JPanel jPanel = new JPanel();
		jPanel.add(jLabel);
		jPanel.add(createNew);
		JTextField pretraga = new JTextField();
		GhostText ghostText = new GhostText(pretraga,"Unesite tekst ovde...");
		JButton buttonPretraga = new JButton("Pretraga");
		jPanel.add(new JLabel("             "));
		pretraga.setPreferredSize(new Dimension(200,25));
		jPanel.add(pretraga);
		jPanel.add(buttonPretraga);
		add(jPanel, BorderLayout.NORTH);

		String[] zaglavlja = new String[] { "Naziv", "Deskripcija", "Brisanje" };
		String[][] data = new String[mapa.size()][3];
		int br = 0;
		for (Tip_Soba temp : mapa.values()) {
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

		ButtonColumn buttonColumn = new ButtonColumn(jTable, delete, 2);

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
					if(column == 0) {
						Tip_Soba tipSoba = mapa.get(cuvanje);
						mapa.remove(cuvanje);
						tipSoba.unosObjekta(column, temp2);
						mapa.put(temp2, tipSoba);
						for(Cenovnik cenovnik : bazaObjekata.getMapaCenovnik().values()) {
							if(cenovnik.getTip_sobe().equals(cuvanje))
								cenovnik.setTip_sobe(temp2);
						}
						for(Soba soba : bazaObjekata.getMapaSoba().values()) {
							if(soba.getTip_Soba().equals(cuvanje)) {
								soba.setTip_Soba(temp2);
							}
						}
						for(Rezervacija rezervacija : bazaObjekata.getMapaRezervacija().values()) {
							if(rezervacija.getTip_sobe().equals(cuvanje)) {
								rezervacija.setTip_sobe(temp2);
							}
						}
						
					}else {
						final String kljuc = (String) jTable.getValueAt(row, 0);

						mapa.get(kljuc).unosObjekta(column, temp2);
					}
				} 
				catch (Exception e2) {
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
		
		buttonPretraga.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Integer>listaZaBrisanje = new ArrayList<>();
				String temp = pretraga.getText();
				for(int i = 0;i<data.length;i++) {
					if(!KonverterDatum.daLiSadrzi(data[i], temp)) {
						listaZaBrisanje.add(i);
					}
				}
				for(int j = listaZaBrisanje.size()-1;j>-1;j--) {
					((DefaultTableModel) jTable.getModel()).removeRow(listaZaBrisanje.get(j));
				}
				
			}
		});
		jTable.setAutoCreateRowSorter(true);
		JScrollPane jScrollPane = new JScrollPane(jTable);
		add(jScrollPane);
	}
}
