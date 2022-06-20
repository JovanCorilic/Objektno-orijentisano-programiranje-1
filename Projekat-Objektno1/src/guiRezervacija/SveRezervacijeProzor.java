package guiRezervacija;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Toolkit;
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
import javax.swing.JOptionPane;
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
import guiCenovnik.PravljenjeCenovnikaProzor;
import guiDodatneUslugeHotela.SveDodatneUslugeHotelaProzor;
import objekti.BazaObjekata;
import objekti.Cenovnik;
import objekti.Rezervacija;
import objekti.Zaposlen;

public class SveRezervacijeProzor extends JFrame {
	private String cuvanje;

	public SveRezervacijeProzor(BazaObjekata bazaObjekata) {
		HashMap<Integer, Rezervacija>tempMapa;
		if(bazaObjekata.getTipKorisnika().equals("")) {
			tempMapa = new HashMap<>();
			for(Rezervacija rezervacija : bazaObjekata.getMapaRezervacija().values()) {
				if(bazaObjekata.getEmail().equals(rezervacija.getEmail_gosta()))
					tempMapa.put(rezervacija.getId(), rezervacija);
			}
		}else {
			tempMapa = bazaObjekata.getMapaRezervacija();
		}
		
		HashMap<Integer, Rezervacija> mapa = tempMapa;
		
		setTitle("Sve rezervacije");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		setSize(800, 400);
		setLocationRelativeTo(null);
		JLabel jLabel = new JLabel("Dupli klik na ćeliju da se edituje");
		JButton createNew = new JButton("Create new");

		createNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PravljenjeRezervacijeProzor pravljenjeRezervacije = new PravljenjeRezervacijeProzor(bazaObjekata);
				pravljenjeRezervacije.setVisible(true);
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
		
		String[]zaglavljaTemp;
		if(bazaObjekata.getTipKorisnika().equals("")) {
			zaglavljaTemp = new String[] { "ID","Status", "Datum početka", "Datum kraja", "Tip sobe","Broj ljudi", "Email gosta",
					"Broj pasoša gosta","Dodatne usluge" };
		}else if(bazaObjekata.getTipKorisnika().equals(Zaposlen.tipovi.REC.getTip())){
			zaglavljaTemp = new String[] { "ID","Status", "Datum početka", "Datum kraja", "Tip sobe","Broj ljudi","Broj sobe", "Email gosta",
					"Broj pasoša gosta","Dodatne usluge" };
		}
		else {
			zaglavljaTemp = new String[] { "ID","Status", "Datum početka", "Datum kraja", "Tip sobe","Broj ljudi","Broj sobe", "Email gosta",
					"Broj pasoša gosta", "Brisanje" };
		}

		String[] zaglavlja = zaglavljaTemp;
		String[][] dataTemp;
		if(bazaObjekata.getTipKorisnika().equals("")) {
			dataTemp = new String[mapa.size()][9];
			int br = 0;
			for (Rezervacija temp : mapa.values()) {
				String[] lista2 = temp.toString().split("\\|");
				
				dataTemp[br][0] = temp.getId() + "";
				for (int i = 1; i < lista2.length+1; i++) {
					if(i==6)
						break;
					dataTemp[br][i] = lista2[i-1];
				}
				dataTemp[br][6]=temp.getEmail_gosta();
				dataTemp[br][7]=temp.getBroj_pasosa();
				dataTemp[br][8] = "Dodatne usluge";
				br++;
			}
		}else if(bazaObjekata.getTipKorisnika().equals(Zaposlen.tipovi.REC.getTip())){
			dataTemp = new String[mapa.size()][10];
			int br = 0;
			for (Rezervacija temp : mapa.values()) {
				String[] lista2 = temp.toString().split("\\|");
				dataTemp[br][0] = temp.getId() + "";
				for (int i = 1; i < lista2.length+1; i++) {
					dataTemp[br][i] = lista2[i-1];
				}
				dataTemp[br][9] = "Dodatne usluge";
				br++;
			}
		}
		else {
			dataTemp = new String[mapa.size()][10];
			int br = 0;
			for (Rezervacija temp : mapa.values()) {
				String[] lista2 = temp.toString().split("\\|");
				dataTemp[br][0] = temp.getId() + "";
				for (int i = 1; i < lista2.length+1; i++) {
					dataTemp[br][i] = lista2[i-1];
				}
				dataTemp[br][lista2.length+1] = "Delete";
				br++;
			}
		}
		
		String[][] data=dataTemp;
		DefaultTableModel defaultTableModel = new DefaultTableModel(data, zaglavlja);
		JTable jTable = new JTable(defaultTableModel);
		
		Action delete = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTable jTable = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				mapa.remove(Integer.parseInt(jTable.getValueAt(jTable.convertRowIndexToModel(modelRow), 0).toString()));
				((DefaultTableModel) jTable.getModel()).removeRow(modelRow);
				// mapa.remove(jTable.getValueAt(modelRow, 0).toString());

			}
		};
		
		Action dodatne = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTable jTable = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				int kljuc = Integer.parseInt(jTable.getValueAt(jTable.convertRowIndexToModel(modelRow), 0).toString());
				bazaObjekata.setPamcenje(bazaObjekata.getTipKorisnika());
				bazaObjekata.setTipKorisnika("Jednistveno");
				bazaObjekata.setId(kljuc);
				SveDodatneUslugeHotelaProzor dodatneUslugeHotelaProzor = new SveDodatneUslugeHotelaProzor(bazaObjekata);
				dodatneUslugeHotelaProzor.setVisible(true);
				
				// mapa.remove(jTable.getValueAt(modelRow, 0).toString());

			}
		};
		
		Button deleteButton = new Button("Delete");
		ButtonColumn buttonColumn;
		
		if (bazaObjekata.getTipKorisnika().equals(Zaposlen.tipovi.ADMIN.getTip()))
			buttonColumn= new ButtonColumn(jTable, delete, 9);
		else if(bazaObjekata.getTipKorisnika().equals(Zaposlen.tipovi.REC.getTip()))
			buttonColumn= new ButtonColumn(jTable, dodatne, 9);
		else if( bazaObjekata.getTipKorisnika().equals(""))
			buttonColumn= new ButtonColumn(jTable, dodatne, 8);
		
		jTable.setCellSelectionEnabled(true);

		DefaultCellEditor cellEditor = new DefaultCellEditor(new JTextField());
		cellEditor.addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingStopped(ChangeEvent e) {
				try {
					if (!bazaObjekata.getTipKorisnika().equals(""))
						throw new Exception();
					final DefaultCellEditor defaultCellEditor = (DefaultCellEditor) e.getSource();
					final int row = jTable.getSelectedRow();
					final int column = jTable.getSelectedColumn();

					String temp2 = defaultCellEditor.getCellEditorValue().toString();
					if(column==4 || column ==6 || column == 7 || column == 8) {
						throw new ArithmeticException() ;
					}
					ExceptionInInitializerError error = new ExceptionInInitializerError();
					if(column==0)
						throw error;
					
					final int kljuc =Integer.parseInt((String) jTable.getValueAt(row, 0));

					mapa.get(kljuc).unosObjekta(column, temp2);
				}catch (ArithmeticException e5) {
					JOptionPane.showMessageDialog(null, "Polja email, tip sobe ,broj sobe i broj pasoša se menjaju u svojoj listi respektivno!", "Greška",
							JOptionPane.ERROR_MESSAGE);
					final int row = jTable.getSelectedRow();
					final int column = jTable.getSelectedColumn();
					jTable.setValueAt(cuvanje, row, column);
				}catch (ExceptionInInitializerError e3) {
					JOptionPane.showMessageDialog(null, "ID se ne dira!", "Greška",
							JOptionPane.ERROR_MESSAGE);
					final int row = jTable.getSelectedRow();
					final int column = jTable.getSelectedColumn();
					jTable.setValueAt(cuvanje, row, column);
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
		box.addItem(Rezervacija.Statusi.NACEK.getVrednost());
		
		box.addItem(Rezervacija.Statusi.OTKAZ.getVrednost());
		if (!bazaObjekata.getTipKorisnika().equals("")) { 
		box.addItem(Rezervacija.Statusi.POTVR.getVrednost());
		box.addItem(Rezervacija.Statusi.ODBIJ.getVrednost());
		}
		TableColumn column;
		if (!bazaObjekata.getTipKorisnika().equals(""))  
			 column = jTable.getColumnModel().getColumn(1);
		else
			 column = jTable.getColumnModel().getColumn(1);
		DefaultCellEditor cellEditor2 = new DefaultCellEditor(box);
		cellEditor2.addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingStopped(ChangeEvent e) {
				try {

					final DefaultCellEditor defaultCellEditor = (DefaultCellEditor) e.getSource();
					final int row = jTable.getSelectedRow();
					final int column = jTable.getSelectedColumn();

					String temp2 = defaultCellEditor.getCellEditorValue().toString();

					final int kljuc =Integer.parseInt((String) jTable.getValueAt(row, 0));

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
		
		
		buttonPretraga.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Integer>listaZaBrisanje = new ArrayList<>();
				String[][]tempData = new String[jTable.getRowCount()][jTable.getColumnCount()];
				for(int t = 0;t<jTable.getRowCount();t++) {
					for(int k = 0;k<jTable.getColumnCount();k++) {
						tempData[t][k]=(String) jTable.getValueAt(t, k);
					}
				}
				
				String temp = pretraga.getText();
				for(int i = 0;i<tempData.length;i++) {
					if(!OperacijePretrage.daLiSadrzi(tempData[i], temp)) {
						listaZaBrisanje.add(i);
					}
				}
				for(int j = listaZaBrisanje.size()-1;j>-1;j--) {
					((DefaultTableModel) jTable.getModel()).removeRow(jTable.convertRowIndexToModel(listaZaBrisanje.get(j)));
					
				}
				
			}
		});
		jTable.setAutoCreateRowSorter(true);
		jTable.getTableHeader().setReorderingAllowed(false);
		JScrollPane jScrollPane = new JScrollPane(jTable);
		add(jScrollPane);
	}
}
