package glavni;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello World!");
		System.out.println(KonverterDatum.konvertovanjeSamoDatumUString(LocalDate.now()));
		System.out.println(KonverterDatum.konvertovanjeUString(LocalDateTime.now()));
		
		
	}
	
}
