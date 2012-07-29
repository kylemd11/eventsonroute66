package com.kyle.route66.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultScheduleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderAddressComponent;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.LatLng;
import com.kyle.route66.db.dao.EventDao;
import com.kyle.route66.db.dao.EventRepository;
import com.kyle.route66.db.model.Event;
import com.kyle.route66.db.model.EventType;
import com.kyle.route66.db.model.State;
import com.kyle.route66.service.model.EventCriteria;

@Service("LocationService")
@Scope("singleton")
public class LocationService {
	private static final Log log = LogFactory.getLog(LocationService.class);

	public Map<String, BigDecimal> getGeoLocationForAddress(String address, String city, String state, String zipcode) {
		Map<String, BigDecimal> results = new HashMap<String, BigDecimal>();
		
		final Geocoder geocoder = new Geocoder();
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(formatEventAddress(address, city, state, zipcode)).setLanguage("en").getGeocoderRequest();
		GeocodeResponse geocode = geocoder.geocode(geocoderRequest);
		
		results.put("latitude", geocode.getResults().get(0).getGeometry().getLocation().getLat());
		results.put("longitude", geocode.getResults().get(0).getGeometry().getLocation().getLng());
		
		return results;
	}
	
	private String formatEventAddress(String address, String city, String state, String zipcode) {
		return address + ", " + city + ", " + state + " " + zipcode;
	}
	
	public Map<String, String> getAddressForGeoLocation(BigDecimal latitude, BigDecimal longitude) {
		final Geocoder geocoder = new Geocoder();
		
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setLocation(new LatLng(latitude, longitude)).getGeocoderRequest();
		
		GeocodeResponse geocode = geocoder.geocode(geocoderRequest);
		
		return parseFormattedAddress(geocode.getResults().get(0).getFormattedAddress());
	}

	private Map<String, String> parseFormattedAddress(String formattedAddress) {
		Map<String, String> address = new HashMap<String, String>();
		
		String[] split1 = formattedAddress.split(",");
		
		address.put("address", split1[0].trim());
		address.put("city", split1[1].trim());
		
		String[] split2 = split1[2].split(" ");
		
		address.put("state", split2[1].trim());
		address.put("zip", split2[2].trim());

		return address;
	}
}
