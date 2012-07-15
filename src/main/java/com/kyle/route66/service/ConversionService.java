package com.kyle.route66.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kyle.route66.db.model.Link;
import com.kyle.route66.service.converter.Converter;
import com.kyle.route66.service.converter.LinkConverter;
import com.kyle.route66.web.ui.LinkDto;

public class ConversionService {
	private static final Log log = LogFactory.getLog(ConversionService.class);
	
	public static Object toDto(Object obj) {
		return getConverter(obj).toDto(obj, false);
	}
	
	public static Object toDto(Object obj, boolean isReference) {
		return getConverter(obj).toDto(obj, isReference);
	}
	
	public static List toDto(List objs) {
		List dtos = new ArrayList(objs.size());
		
		Converter converter = getConverter(objs.get(0));
		
		for(Object obj : objs) {
			dtos.add(converter.toDto(obj, false));
		}
		
		return dtos;
	}
	
	
	public static Object toDb(Object obj) {
		return getConverter(obj).toDb(obj);
	}
	
	public static List toDb(List objs) {
		List dbs = new ArrayList();
		
		Converter converter = getConverter(objs.get(0));
		
		for(Object obj : objs) {
			dbs.add(converter.toDb(obj));
		}
		
		return dbs;
	}
	
	private static Converter getConverter(Object obj) {
		if(obj instanceof Link || obj instanceof LinkDto) {
			return new LinkConverter();
		}
		else {
			return null;
		}
	}
}
