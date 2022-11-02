package zadaci;

public class Zadatak8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int matrica [][] = new int [7][5];
		int broj = -1;
		for (int i =0;i<7;i++) {
			broj++;
			for (int j = broj;j<(5+broj);j++) {
				System.out.print(j+"\t");
				matrica[i][j-broj] = j;
			}
			System.out.println();
		}
		int d = 0;
		for(int i = 0;i<matrica.length;i++) {
			for(int j = 0;j<matrica[i].length;j++) {
				System.out.print(matrica[i][j]+"\t");
				if(j==i)
					d += matrica[i][j];
			}
			System.out.println();
		}
		
		System.out.println("Dijagonala " + d);
		
		
		
	}

}
