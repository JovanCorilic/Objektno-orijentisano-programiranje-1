package zadaci;

public class Zadatak15 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 7;
		
		for(int i = 0;i<(n*2+1);i++) {
			for(int j = 0;j<(n*2+1);j++) {
				if((n-i)>-1)
					if((j>=n-i) && (j<=n+i))
						System.out.print("*");
					else
						System.out.print("-");
				else {
					//i=8
					if((j<n*2+1-i+n) && (j>=i-n))
						System.out.print("*");
					else
						System.out.print("-");
				}
			}
			System.out.println();
		}
	}

}
