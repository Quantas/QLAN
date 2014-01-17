package com.quantasnet.qlan.web.controllers.editors;

import java.beans.PropertyEditorSupport;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeEditor extends PropertyEditorSupport {

	private static final DateTimeFormatter FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:SS");
	
	@Override
	public void setAsText(final String text) throws IllegalArgumentException {
		setValue(FORMAT.parseDateTime(text));
	}
	
	@Override
	public String getAsText() {
		return FORMAT.print((DateTime)getValue());
	}
}
