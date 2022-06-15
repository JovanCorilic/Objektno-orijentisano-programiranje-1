package guiSoba;

import java.awt.BorderLayout;
import java.awt.Button;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import glavni.ButtonColumn;
import objekti.BazaObjekata;
import objekti.Cenovnik;
import objekti.Rezervacija;
import objekti.Soba;
import objekti.Tip_Soba;

public class SveSobeProzor extends JFrame{
	private String cuvanje;
	public SveSobeProzor(BazaObjekata bazaObjekata) {
		HashMap<Integer, Soba>mapa = bazaObjekata.getMapaSoba();
		setTitle("Sve sobe");
		setSize(800, 400);
		setLocationRelativeTo(null);
		JLabel jLabel = new JLabel("Dupli klik na ćeliju da se edituje");
		JButton createNew = new JButton("Create new");

		createNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PravljenjeSobeProzor pravljenjeSobeProzor = new PravljenjeSobeProzor(bazaObjekata);
				pravljenjeSobeProzor.setVisible(true);
				dispose();

			}
		});

		JPanel jPanel = new JPanel();
		jPanel.add(jLabel);
		jPanel.add(createNew);
		add(jPanel, BorderLayout.NORTH);

		String[] zaglavlja = new String[] { "Broj sobe", "Status", "Tip sobe", "Brisanje" };
		
		String[][] data = new String[mapa.size()][4];
		int br = 0;
		for (Soba temp : mapa.values()) {
			String[] lista2 = temp.toString().split("\\|");
			for (int i = 0; i < lista2.length; i++) {
				data[br][i] = lista2[i];
			}
			data[br][lista2.length] = "Delete";
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

		ButtonColumn buttonColumn = new ButtonColumn(jTable, delete, 3);

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
						Soba soba = mapa.get(cuvanje);
						mapa.remove(cuvanje);
						soba.unosObjekta(column, temp2);
						mapa.put(Integer.parseInt(temp2), soba);
						for(Rezervacija rezervacija : bazaObjekata.getListaRezervacija()) {
							if(rezervacija.getBroj_sobe()==Integer.parseInt(cuvanje)) {
								rezervacija.setBroj_sobe(Integer.parseInt(temp2));
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

		JComboBox<String> box = new JComboBox<>();
		box.addItem(Soba.Statusi.SLO.getVrednost());
		box.addItem(Soba.Statusi.SPR.getVrednost());
		box.addItem(Soba.Statusi.ZAU.getVrednost());
		

		TableColumn column = jTable.getColumnModel().getColumn(1);
		DefaultCellEditor cellEditor2 = new DefaultCellEditor(box);
		cellEditor2.addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingStopped(ChangeEvent e) {
				try {

					final DefaultCellEditor defaultCellEditor = (DefaultCellEditor) e.getSource();
					final int row = jTable.getSelectedRow();
					final int column = jTable.getSelectedColumn();

					String temp2 = defaultCellEditor.getCellEditorValue().toString();

					final String kljuc = (String) jTable.getValueAt(row, 0);

					mapa.get(kljuc).unosObjekta(column, temp2);
				} catch (Exception e2) {
					/*
					 * final int row = jTable.getSelectedRow(); final int column =
					 * jTable.getSelectedColumn(); jTable.setValueAt(cuvanje, row, column);
					 */
				}

			}

			@Override
			public void editingCanceled(ChangeEvent e) {
				// TODO Auto-generated method stub

			}
		});
		column.setCellEditor(cellEditor2);
		
		JComboBox<String> box2 = new JComboBox<>();
		for(Tip_Soba tip_Soba : bazaObjekata.getMapaTipovaSobe().values())
			box2.addItem(tip_Soba.getNaziv_tipa());
		
		

		TableColumn column2 = jTable.getColumnModel().getColumn(2);
		DefaultCellEditor cellEditor3 = new DefaultCellEditor(box2);
		cellEditor3.addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingStopped(ChangeEvent e) {
				try {

					final DefaultCellEditor defaultCellEditor = (DefaultCellEditor) e.getSource();
					final int row = jTable.getSelectedRow();
					final int column = jTable.getSelectedColumn();

					String temp2 = defaultCellEditor.getCellEditorValue().toString();

					final String kljuc = (String) jTable.getValueAt(row, 0);

					mapa.get(kljuc).unosObjekta(column, temp2);
				} catch (Exception e2) {
					/*
					 * final int row = jTable.getSelectedRow(); final int column =
					 * jTable.getSelectedColumn(); jTable.setValueAt(cuvanje, row, column);
					 */
				}

			}

			@Override
			public void editingCanceled(ChangeEvent e) {
				// TODO Auto-generated method stub

			}
		});
		column2.setCellEditor(cellEditor3);

		JScrollPane jScrollPane = new JScrollPane(jTable);
		add(jScrollPane);
	}
}
