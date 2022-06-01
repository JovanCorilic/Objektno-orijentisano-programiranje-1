package glavni;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello World!");
		/*PrintWriter printWriter = new PrintWriter(new File("test.csv"));
		StringBuffer csvHeader = new StringBuffer("");
		csvHeader.append("Test1,wwfw,ert,we,rt\n");
		printWriter.write(csvHeader.toString());
		StringBuffer csvData = new StringBuffer("");
		csvData.append("1,2,3,4,5\n");
		printWriter.write(csvData.toString());
		printWriter.close();
		*/
		List<String[]> dataLines = new ArrayList<>();
		dataLines.add(new String[] 
		  { "John", "Doe", "38", "Comment Data\nAnother line of comment data" });
		dataLines.add(new String[] 
		  { "Jane", "Doe, Jr.", "19", "She said \"I'm being quoted\"" });
		File csvOutputFile = new File("test.csv");
		Main main = new Main();
	    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
	        dataLines.stream()
	          .map(main::convertToCSV)
	          .forEach(pw::println);
	    }
	}
	public String convertToCSV(String[] data) {
	    return Stream.of(data)
	      .map(this::escapeSpecialCharacters)
	      .collect(Collectors.joining(","));
	}
	public String escapeSpecialCharacters(String data) {
	    String escapedData = data.replaceAll("\\R", " ");
	    if (data.contains(",") || data.contains("\"") || data.contains("'")) {
	        data = data.replace("\"", "\"\"");
	        escapedData = "\"" + data + "\"";
	    }
	    return escapedData;
	}

}
