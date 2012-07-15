package com.kyle.route66.service.converter;

public interface Converter {
	public Object toDto(Object obj, boolean isReference);
	public Object toDb(Object obj);
}
