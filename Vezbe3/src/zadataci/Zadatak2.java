package zadataci;

public class Zadatak2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "4,3,2,1;0,1,0;1,2,3,4";
		System.out.println(matricaB(text));
				
	}
	
	static public String matricaB(String text) {
		String[] listaVrsta = text.split(";");
		String[][] matrica = new String[listaVrsta.length][];
		for(int i = 0;i<listaVrsta.length;i++) {
			String[] listaKolona = listaVrsta[i].split(",");
			matrica[i] = listaKolona;
		}
		
		String temp = "";
		for(int i = 0;i<matrica.length;i++) {
			for(int j = 0;j<matrica[i].length;j++) {
				temp += matrica[i][j] + " ";
			}
			temp +="\n";
		}
		return temp;
	}

}
