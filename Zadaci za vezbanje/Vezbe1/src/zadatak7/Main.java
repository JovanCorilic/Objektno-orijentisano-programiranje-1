package zadatak7;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x = 5;
		int y = 7;
		if (x<y) {
			double z = Math.max(x, y)/1+Math.abs(Math.min(x, y));
			System.out.println(z);
		}
		else if(x>=y) {
			double z = Math.max(x, y)/1+Math.min(x, y);
			System.out.println(z);
		}
	}

}
