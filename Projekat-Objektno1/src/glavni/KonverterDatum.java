package glavni;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class KonverterDatum {
	public static String konvertovanjeUString(LocalDateTime temp) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
		return temp.format(dateTimeFormatter);
	}
	public static LocalDateTime konvertovanjeUDateTime(String temp) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
		LocalDateTime parse = LocalDateTime.parse(temp,dateTimeFormatter);
		return parse;
	}

}
