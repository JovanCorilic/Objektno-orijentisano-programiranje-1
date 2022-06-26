package guiSoba;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
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
import glavni.GhostText;
import glavni.KonverterDatum;
import glavni.OperacijePretrage;
import objekti.BazaObjekata;
import objekti.Cenovnik;
import objekti.OciscenaSoba;
import objekti.Rezervacija;
import objekti.Soba;
import objekti.Tip_Soba;
import objekti.Zaposlen;

public class SveSobeProzor extends JFrame {
	private String cuvanje;
	private int ponavljanje;

	public SveSobeProzor(BazaObjekata bazaObjekata) {
		HashMap<Integer, Soba> mapaTemp = new HashMap<>();
		if (bazaObjekata.getTipKorisnika().equals(Zaposlen.tipovi.SOBARICA.getTip())) {
			ArrayList<Integer> listaSoba = bazaObjekata.getMapaSobarica().get(bazaObjekata.getEmail());
			for (Integer broj_Sobe : listaSoba) {
				mapaTemp.put(broj_Sobe, bazaObjekata.getMapaSoba().get(broj_Sobe));
			}
		} else {
			mapaTemp = bazaObjekata.getMapaSoba();
		}
		HashMap<Integer, Soba> mapa = mapaTemp;
		setTitle("Sve sobe");
		setSize(700, 400);
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
		if (!bazaObjekata.getTipKorisnika().equals(Zaposlen.tipovi.SOBARICA.getTip())
				&& !bazaObjekata.getTipKorisnika().equals(Zaposlen.tipovi.REC.getTip())) {
			jPanel.add(jLabel);

			jPanel.add(createNew);
		}
		JTextField pretraga = new JTextField();
		GhostText ghostText = new GhostText(pretraga, "Unesite tekst ovde...");
		JButton buttonPretraga = new JButton("Pretraga");
		jPanel.add(new JLabel("             "));
		pretraga.setPreferredSize(new Dimension(200, 25));
		jPanel.add(pretraga);
		jPanel.add(buttonPretraga);
		add(jPanel, BorderLayout.NORTH);
		String[] zaglavljatemp;
		if (bazaObjekata.getTipKorisnika().equals(Zaposlen.tipovi.SOBARICA.getTip())) {
			zaglavljatemp = new String[] { "Broj sobe", "Status", "Tip sobe", "Spremanje" };
		} else if (bazaObjekata.getTipKorisnika().equals(Zaposlen.tipovi.REC.getTip())) {
			zaglavljatemp = new String[] { "Broj sobe", "Status", "Tip sobe" };
		} else {
			zaglavljatemp = new String[] { "Broj sobe", "Status", "Tip sobe", "Brisanje" };
		}
		String[] zaglavlja = zaglavljatemp;
		String[][] data;
		if (!bazaObjekata.getTipKorisnika().equals(Zaposlen.tipovi.REC.getTip())) {
			data = new String[mapa.size()][4];
			int br = 0;
			for (Soba temp : mapa.values()) {
				String[] lista2 = temp.toString().split("\\|");
				for (int i = 0; i < lista2.length; i++) {
					data[br][i] = lista2[i];
				}
				if (bazaObjekata.getTipKorisnika().equals(Zaposlen.tipovi.SOBARICA.getTip()))
					data[br][lista2.length] = "Ocisćena";
				else
					data[br][lista2.length] = "Delete";
				br++;
			}
		} else {
			data = new String[mapa.size()][3];
			int br = 0;
			for (Soba temp : mapa.values()) {
				String[] lista2 = temp.toString().split("\\|");
				for (int i = 0; i < lista2.length; i++) {
					data[br][i] = lista2[i];
				}

				br++;
			}
		}

		DefaultTableModel defaultTableModel = new DefaultTableModel(data, zaglavlja);
		JTable jTable = new JTable(defaultTableModel);

		Action delete = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTable jTable = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				int kljuc = Integer.parseInt(jTable.getValueAt(jTable.convertRowIndexToModel(modelRow), 0).toString());
				if (bazaObjekata.getTipKorisnika().equals(Zaposlen.tipovi.SOBARICA.getTip())) {
					bazaObjekata.getMapaSoba().get(kljuc).setStatus(Soba.Statusi.SLO.getVrednost());
					ArrayList<Integer> listaSoba = bazaObjekata.getMapaSobarica().get(bazaObjekata.getEmail());
					for (int i = 0; i < listaSoba.size(); i++) {
						if (listaSoba.get(i) == kljuc) {
							bazaObjekata.getMapaSobarica().get(bazaObjekata.getEmail()).remove(i);
							break;
						}
					}
					if (!bazaObjekata.getMapaOciscenihSobaSobarica().containsKey(bazaObjekata.getEmail())) {
						bazaObjekata.getMapaOciscenihSobaSobarica().put(bazaObjekata.getEmail(), new ArrayList<>());
					}
					bazaObjekata.getMapaOciscenihSobaSobarica().get(bazaObjekata.getEmail())
							.add(new OciscenaSoba(kljuc, LocalDateTime.now()));

				} else {
					mapa.remove(kljuc);

				}
				((DefaultTableModel) jTable.getModel()).removeRow(modelRow);
				// mapa.remove(jTable.getValueAt(modelRow, 0).toString());

			}
		};
		Button deleteButton = new Button("Delete");
		ButtonColumn buttonColumn;
		if (!bazaObjekata.getTipKorisnika().equals(Zaposlen.tipovi.REC.getTip()))
			buttonColumn = new ButtonColumn(jTable, delete, 3);

		jTable.setCellSelectionEnabled(true);

		DefaultCellEditor cellEditor = new DefaultCellEditor(new JTextField());
		cellEditor.addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingStopped(ChangeEvent e) {
				try {
					if (bazaObjekata.getTipKorisnika().equals(Zaposlen.tipovi.SOBARICA.getTip())
							|| bazaObjekata.getTipKorisnika().equals(Zaposlen.tipovi.REC.getTip()))
						throw new Exception();

					final DefaultCellEditor defaultCellEditor = (DefaultCellEditor) e.getSource();
					final int row = jTable.getSelectedRow();
					final int column = jTable.getSelectedColumn();

					String temp2 = defaultCellEditor.getCellEditorValue().toString();
					if (column == 0) {
						Soba soba = mapa.get(Integer.parseInt(cuvanje));
						mapa.remove(Integer.parseInt(cuvanje));
						soba.unosObjekta(column, temp2);
						mapa.put(Integer.parseInt(temp2), soba);
						for (Rezervacija rezervacija : bazaObjekata.getMapaRezervacija().values()) {
							if (rezervacija.getBroj_sobe() == Integer.parseInt(cuvanje)) {
								rezervacija.setBroj_sobe(Integer.parseInt(temp2));
							}
						}
						for (ArrayList<Integer> lista : bazaObjekata.getMapaSobarica().values()) {
							if (lista.contains(Integer.parseInt(cuvanje))) {
								for (int t : lista) {
									if (t == Integer.parseInt(cuvanje)) {
										t = Integer.parseInt(temp2);
									}
								}
							}
						}

					} else {
						final String kljuc = (String) jTable.getValueAt(row, 0);

						mapa.get(Integer.parseInt(kljuc)).unosObjekta(column, temp2);
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

		JComboBox<String> box = new JComboBox<>();
		if (bazaObjekata.getTipKorisnika().equals(Zaposlen.tipovi.SOBARICA.getTip())) {
			box.addItem(Soba.Statusi.SPR.getVrednost());
		} else {
			box.addItem(Soba.Statusi.SLO.getVrednost());
			box.addItem(Soba.Statusi.SPR.getVrednost());
			box.addItem(Soba.Statusi.ZAU.getVrednost());
		}

		TableColumn column = jTable.getColumnModel().getColumn(1);
		DefaultCellEditor cellEditor2 = new DefaultCellEditor(box);
		cellEditor2.addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingStopped(ChangeEvent e) {

				try {
					ponavljanje++;
					if(ponavljanje==1)
						throw new Exception();
					else if(ponavljanje==2) {
						ponavljanje=0;
					}
					final DefaultCellEditor defaultCellEditor = (DefaultCellEditor) e.getSource();
					final int row = jTable.getSelectedRow();
					final int column = jTable.getSelectedColumn();

					String temp2 = defaultCellEditor.getCellEditorValue().toString();

					final String kljuc = (String) jTable.getValueAt(row, 0);

					if (column == 1 && temp2.equals(Soba.Statusi.SPR.getVrednost())) {
						String nesto = bazaObjekata.NajslobodnijaSobarica();
						bazaObjekata.getMapaSobarica().get(nesto).add(Integer.parseInt(kljuc));
					}

					mapa.get(Integer.parseInt(kljuc)).unosObjekta(column, temp2);
				} catch (Exception e2) {

				}

			}

			@Override
			public void editingCanceled(ChangeEvent e) {
				// TODO Auto-generated method stub

			}
		});
		column.setCellEditor(cellEditor2);

		JComboBox<String> box2 = new JComboBox<>();
		if (!bazaObjekata.getTipKorisnika().equals(Zaposlen.tipovi.SOBARICA.getTip())
				&& !bazaObjekata.getTipKorisnika().equals(Zaposlen.tipovi.REC.getTip()))
			for (Tip_Soba tip_Soba : bazaObjekata.getMapaTipovaSobe().values())
				box2.addItem(tip_Soba.getNaziv_tipa());

		TableColumn column2 = jTable.getColumnModel().getColumn(2);
		DefaultCellEditor cellEditor3 = new DefaultCellEditor(box2);
		cellEditor3.addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingStopped(ChangeEvent e) {
				try {
					if (bazaObjekata.getTipKorisnika().equals(Zaposlen.tipovi.SOBARICA.getTip())
							|| bazaObjekata.getTipKorisnika().equals(Zaposlen.tipovi.REC.getTip()))
						throw new Exception();
					ponavljanje++;
					if(ponavljanje==1)
						throw new Exception();
					else if(ponavljanje==2) {
						ponavljanje=0;
					}
					final DefaultCellEditor defaultCellEditor = (DefaultCellEditor) e.getSource();
					final int row = jTable.getSelectedRow();
					final int column = jTable.getSelectedColumn();

					String temp2 = defaultCellEditor.getCellEditorValue().toString();

					final String kljuc = (String) jTable.getValueAt(row, 0);

					mapa.get(Integer.parseInt(kljuc)).unosObjekta(column, temp2);
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

		buttonPretraga.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ArrayList<Integer> listaZaBrisanje = new ArrayList<>();
				String[][] tempData = new String[jTable.getRowCount()][jTable.getColumnCount()];
				for (int t = 0; t < jTable.getRowCount(); t++) {
					for (int k = 0; k < jTable.getColumnCount(); k++) {
						tempData[t][k] = (String) jTable.getValueAt(t, k);
					}
				}

				String temp = pretraga.getText();
				for (int i = 0; i < tempData.length; i++) {
					if (!OperacijePretrage.daLiSadrzi(tempData[i], temp)) {
						listaZaBrisanje.add(i);
					}
				}
				for (int j = listaZaBrisanje.size() - 1; j > -1; j--) {
					((DefaultTableModel) jTable.getModel())
							.removeRow(jTable.convertRowIndexToModel(listaZaBrisanje.get(j)));

				}

			}
		});
		jTable.setAutoCreateRowSorter(true);
		jTable.getTableHeader().setReorderingAllowed(false);
		JScrollPane jScrollPane = new JScrollPane(jTable);
		add(jScrollPane);
	}
}
