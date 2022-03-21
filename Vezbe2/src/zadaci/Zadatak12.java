package zadaci;

public class Zadatak12 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int matrica [][] = new int[7][5];
		
		
		int proizvod = 1;
		for (int i = 0;i<matrica.length;i++) {
			for(int j = 0;j<matrica[i].length;j++) {
				System.out.print(Math.pow(i+1, 2) + "\t");
				matrica[i][j] = (int) Math.pow(i+1, 2);
				if(i<j)
					proizvod =(int) (proizvod *  Math.pow(i+1, 2));
			}
			System.out.println();
		}
		
		System.out.println(proizvod);
	}

}
