package br.com.reservation.court.util;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import org.springframework.stereotype.Service;

@Service
public class DateFormatter {
	
	public DateTimeFormatter formatter = new DateTimeFormatterBuilder()
			.appendOptional( DateTimeFormatter.ISO_LOCAL_DATE )
			.optionalStart().appendLiteral( "T" ).optionalEnd()
			.appendOptional( DateTimeFormatter.ISO_LOCAL_TIME )
			.toFormatter();
}
