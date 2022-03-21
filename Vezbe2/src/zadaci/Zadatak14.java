package zadaci;

public class Zadatak14 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x [] = {2,3,53,22,4,3,1};
		int y [] = {6,23,13,33,1,2,5};
		
		int c[]= new int[5];
		double min = 100000000000000000000000000000000000000.0;
		for (int i = 0;i<x.length;i++) {
			for(int j = 0;j<y.length;j++) {
				if(i==j)
					continue;
				double d = Math.sqrt(Math.pow((x[i]-x[j]), 2)+Math.pow((y[i]-y[j]),2));
				if(min>d) {
					min = d;
					c[0] = (int) min;
					c[1] = x[i];
					c[2] = y[i];
					c[3] = x[j];
					c[4] = y[j];
				}
			}
		}
		for(int i = 0 ;i<5;i++)
			System.out.println(c[i]);
		
	}

}
