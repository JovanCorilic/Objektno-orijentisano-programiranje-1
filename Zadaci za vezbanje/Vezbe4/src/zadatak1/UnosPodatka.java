package zadatak1;

import java.util.Scanner;

public class UnosPodatka {

	public String unosString() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	
	public int unosInt() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextInt();
	}
	
	public double unosDouble() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextDouble();
	}
}
