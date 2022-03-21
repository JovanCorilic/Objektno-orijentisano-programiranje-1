package zadaci;

public class Zadatak10 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int matrica [][] = new int [3][3];
		int br = 0;
		for(int i = 0;i<3;i++) {
			for(int j = 0;j<3;j++) {
				br++;
				matrica[i][j] = br;
			}
		}
		
		for(int i = 0;i<3;i++) {
			for(int j = 0;j<3;j++) {
				if(i==j) {
				int temp = matrica[i][j];
				matrica[i][j] = matrica[i][matrica[i].length-1-j];
				matrica[i][matrica[i].length-1-j] = temp;
				}
			}
			
		}
		for(int i = 0;i<3;i++) {
			for(int j = 0;j<3;j++) {
				System.out.print(matrica[i][j]+"\t");
			}
			System.out.println();
		}
		
	}

}
