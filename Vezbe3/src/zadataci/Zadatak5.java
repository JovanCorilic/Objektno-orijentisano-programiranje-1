package zadataci;

public class Zadatak5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "Coko plazma|s01|Bambi|85.30|akcija\nSmoki|s02|Stark|55.00|nije na " 
				+"akciji\nCipsi|s03|Marbo |115.20|nije na akciji\nKrem "
				+"Bananica|s04|Stark|11.00|akcija\n" ;
		String[][]matrica = matricaB(text);
		for(String[] temp:matrica) {
			System.out.println(temp[0]);
		}
		for(String[] temp:matrica) {
			System.out.println(temp[1]);
		}
		for(String[] temp:matrica) {
			if(temp[4].contains("nije"))
				System.out.println(temp[0]);
		}
		for(String[] temp:matrica) {
			if(temp[2].contains("Stark"))
				System.out.println(temp[0]);
		}
	}
	
	
	
	
	static public String[][] matricaB(String text) {
		String[] listaVrsta = text.split("\n");
		String[][] matrica = new String[listaVrsta.length][];
		for(int i = 0;i<listaVrsta.length;i++) {
			String[] listaKolona = listaVrsta[i].split("\\|");
			matrica[i] = listaKolona;
		}
		
		String temp = "";
		for(int i = 0;i<matrica.length;i++) {
			for(int j = 0;j<matrica[i].length;j++) {
				temp += matrica[i][j] + " ";
			}
			temp +="\n";
		}
		
		System.out.println(temp);
		return matrica;
	}

}
