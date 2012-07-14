package com.kyle.route66.service.converter;

public interface Converter {
	public Object toDto(Object obj);
	public Object toDb(Object obj);
}
