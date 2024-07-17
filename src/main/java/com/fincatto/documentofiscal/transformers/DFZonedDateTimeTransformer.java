package com.fincatto.documentofiscal.transformers;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.simpleframework.xml.transform.Transform;

public class DFZonedDateTimeTransformer implements Transform<ZonedDateTime> {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[XXX]");

	@Override
	public ZonedDateTime read(final String data) {
		String value = data;
		if (!value.matches("(\\d{4}(-)\\d{2}(-)\\d{2}(T))((\\d{2})(:)(\\d{2})(:)(\\d{2})(-)?(\\+)?(\\d{2})(:)(\\d{2}))")) {
			value = value.substring(0, 10).concat("T12:00:00-03:00");
		}
		
		return ZonedDateTime.parse(value, DFZonedDateTimeTransformer.FORMATTER);
	}

	@Override
	public String write(final ZonedDateTime data) {
		return DFZonedDateTimeTransformer.FORMATTER.format(data);
	}
	//2024-07-04T12:35:00-04:00}
		//2024-06-14T:21:57-03:00
}