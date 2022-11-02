package zadataci;

import java.util.Scanner;

public class Utility {
//while (scanner.hasNext()) {
	private Scanner scanner = new Scanner(System.in);
	
	public void ocitavanjeTeksta() {
		System.out.println(scanner.nextLine());
	}
	
	public void ceoBroj() {
		System.out.println(scanner.nextInt());
		
	}
	
	public void realniBroj32() {
		System.out.println(scanner.nextFloat());
	}
	public void double64() {
		System.out.println(scanner.nextDouble());
	}
	public void osmobitnaVrednost() {
		System.out.println(scanner.nextByte());
	}
	public void karakteri() {
		System.out.println(scanner.next().charAt(0));
	}
	public void boolewdw() {
		System.out.println(scanner.nextBoolean());
	}
	public void daNe() {
		String temp = scanner.nextLine();
		if(temp.equals("da"))
			System.out.println(true);
		else
			System.out.println(false);
	}
}
