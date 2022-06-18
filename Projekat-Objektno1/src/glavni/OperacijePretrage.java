package glavni;

import java.util.ArrayList;

public class OperacijePretrage {
	public static boolean daLiSadrzi(String[]lista,String text) {
		for(int i = 0;i<lista.length-1;i++) {
			if(lista[i].toUpperCase().contains(text.toUpperCase()))
				return true;
		}
		return false;
	}
	
	public static String[][] azuriranjeNiza(String [][]data, ArrayList<Integer>listaZaBrisanje) {
		String[][] niz = new String[data.length-listaZaBrisanje.size()][data[0].length];
		int br=0;
		for(String[] temp : data) {
			if(!listaZaBrisanje.contains(br)) {
				niz[br] = temp;
				br++;
			}
		}
		return niz;
		
	}

}
