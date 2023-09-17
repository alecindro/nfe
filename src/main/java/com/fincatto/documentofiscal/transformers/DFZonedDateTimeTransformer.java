package com.fincatto.documentofiscal.transformers;

import org.simpleframework.xml.transform.Transform;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DFZonedDateTimeTransformer implements Transform<ZonedDateTime> {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[XXX]");

	@Override
	public ZonedDateTime read(final String data) {
		String value = data;
		if (!value.matches("(\\d{4}(-)\\d{2}(-)\\d{2}(T))((\\d{2})(:)(\\d{2})(:)(\\d{2})(-)(\\d{2})(:)(\\d{2}))")) {
			value = value + "-03:00";
		}
		return ZonedDateTime.parse(value, DFZonedDateTimeTransformer.FORMATTER);
	}

	@Override
	public String write(final ZonedDateTime data) {
		return DFZonedDateTimeTransformer.FORMATTER.format(data);
	}
}