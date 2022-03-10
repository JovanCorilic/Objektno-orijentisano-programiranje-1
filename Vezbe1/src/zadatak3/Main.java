package zadatak3;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random random = new Random();
		int godina =1538 + random.nextInt(10000-1538);
		godina = 2004;
		if((godina%400)==0) {
			System.out.println("Prestupna je");
		}
		else if((godina%100)==0)
			System.out.println("Nije prestupna");
		else if((godina%4)==0)
			System.out.println("Prestupna je");
		else
			System.out.println("Nije prestupna");
	}

}
