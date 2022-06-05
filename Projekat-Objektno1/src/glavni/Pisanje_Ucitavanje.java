package glavni;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Pisanje_Ucitavanje {
	public static void pisanjeArrayList(String nazivFajla,ArrayList<Object>lista) throws IOException {
		PrintWriter printWriter = new PrintWriter(new FileWriter(nazivFajla+".csv",false));
		for(Object object : lista)
			printWriter.print(object.toString());
		printWriter.close();
	}
	
	public static void pisanjeHashMap(String nazivFajla,HashMap<Object, Object>mapa) throws IOException {
		PrintWriter printWriter = new PrintWriter(new FileWriter(nazivFajla+".csv",false));
		for(Object object : mapa.values())
			printWriter.print(object.toString());
		printWriter.close();
	}
	
	public static void ucitavanje(String nazivFajla) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(nazivFajla+".csv"));
		String sCurrentLine;
		while((sCurrentLine = br.readLine()) != null) {
			System.out.println(sCurrentLine);
		}
	}
}
