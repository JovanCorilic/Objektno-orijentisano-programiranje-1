package glavni;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class KonverterDatum {
	public static String konvertovanjeUString(LocalDateTime temp) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
		return temp.format(dateTimeFormatter);
	}

	public static LocalDateTime konvertovanjeUDateTime(String temp) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
		LocalDateTime parse = LocalDateTime.parse(temp, dateTimeFormatter);
		return parse;
	}

	public static String konvertovanjeSamoDatumUString(LocalDate temp) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		return temp.format(dateTimeFormatter);
	}

	public static LocalDate konvertovanjeSamoDatumUDate(String temp) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate parse = LocalDate.parse(temp, dateTimeFormatter);
		return parse;
	}

}
