package zadatak2;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 4;
		int b = 6;
		double ha =  Math.sqrt((Math.pow(b, 2)-(Math.pow(a, 2)/4)));
		double P = (a*ha)/2;
		double hb = (2*P)/b;
		System.out.println(ha + " "+P+" "+hb);
	}

}
