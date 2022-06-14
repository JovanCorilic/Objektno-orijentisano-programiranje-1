package guiRezervacija;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
import guiCenovnik.PravljenjeCenovnikaProzor;
import objekti.BazaObjekata;
import objekti.Cenovnik;
import objekti.Rezervacija;

public class SveRezervacijeProzor extends JFrame {
	private String cuvanje;

	public SveRezervacijeProzor(BazaObjekata bazaObjekata) {
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
		add(jPanel, BorderLayout.NORTH);

		String[] zaglavlja = new String[] { "Status", "Datum početka", "Datum kraja", "Broj sobe", "Email gosta",
				"Broj pasoša gosta", "Brisanje" };
		ArrayList<Rezervacija> lista = bazaObjekata.getListaRezervacija();
		String[][] data = new String[lista.size()][7];
		int br = 0;
		for (Rezervacija temp : lista) {
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
				lista.remove(modelRow);
				((DefaultTableModel) jTable.getModel()).removeRow(modelRow);
				// mapa.remove(jTable.getValueAt(modelRow, 0).toString());

			}
		};
		Button deleteButton = new Button("Delete");

		ButtonColumn buttonColumn = new ButtonColumn(jTable, delete, 6);

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
					if(column==4) {
						throw new ArithmeticException() ;
					}
					
					lista.get(row).unosObjekta(column, temp2);
				}catch (ArithmeticException e5) {
					JOptionPane.showMessageDialog(null, "Email se menja samo kod korisnika!", "Greška",
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
		box.addItem(Rezervacija.Statusi.ODBIJ.getVrednost());
		box.addItem(Rezervacija.Statusi.OTKAZ.getVrednost());
		box.addItem(Rezervacija.Statusi.POTVR.getVrednost());

		TableColumn column = jTable.getColumnModel().getColumn(0);
		DefaultCellEditor cellEditor2 = new DefaultCellEditor(box);
		cellEditor2.addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingStopped(ChangeEvent e) {
				try {

					final DefaultCellEditor defaultCellEditor = (DefaultCellEditor) e.getSource();
					final int row = jTable.getSelectedRow();
					final int column = jTable.getSelectedColumn();

					String temp2 = defaultCellEditor.getCellEditorValue().toString();

					lista.get(row).unosObjekta(column, temp2);
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

		JScrollPane jScrollPane = new JScrollPane(jTable);
		add(jScrollPane);
	}
}
