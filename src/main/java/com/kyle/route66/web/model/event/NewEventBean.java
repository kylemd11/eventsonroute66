package com.kyle.route66.web.model.event;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.imgscalr.Scalr;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.kyle.route66.db.dao.ArticleImageRepository;
import com.kyle.route66.db.dao.CommentRepository;
import com.kyle.route66.db.dao.EventArticleImageRepository;
import com.kyle.route66.db.dao.EventRepository;
import com.kyle.route66.db.dao.EventStatusRepository;
import com.kyle.route66.db.dao.EventTypeRepository;
import com.kyle.route66.db.dao.ImageDao;
import com.kyle.route66.db.dao.StateRepository;
import com.kyle.route66.db.model.ArticleImage;
import com.kyle.route66.db.model.Comment;
import com.kyle.route66.db.model.Event;
import com.kyle.route66.db.model.EventArticleImage;
import com.kyle.route66.db.model.EventStatus;
import com.kyle.route66.db.model.EventType;
import com.kyle.route66.db.model.State;
import com.kyle.route66.web.model.user.UserSession;

@Service("NewEventBean")
@Scope("session")
public class NewEventBean {
	private static final Log log = LogFactory.getLog(NewEventBean.class);

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private EventTypeRepository eventTypeRepository;

	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private ArticleImageRepository articleImageRepository;
	
	@Autowired
	private EventArticleImageRepository eventArticleImageRepository;

	@Autowired
	private EventStatusRepository eventStatusRepository;
	
	@Autowired
	private UserSession session;
	
	@Autowired
	private ImageDao imageDao;

	private Event event = new Event();
	
	private boolean isNew = false;

	public List<EventType> getEventTypes() {
		return eventTypeRepository.findAll();
	}

	public List<State> getStates() {
		List<State> states = new ArrayList<State>();

		for (State state : stateRepository.findAll()) {
			states.add(state);
		}

		return states;
	}
	
	public List<EventStatus> getStatusCodeTypes() {
		List<EventStatus> statuses = new ArrayList<EventStatus>();
		
		for(EventStatus status : eventStatusRepository.findAll()) {
			if(!status.getCode().equalsIgnoreCase("N")) {
				statuses.add(status);
			}
		}
				
		return statuses;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public void setEventRepository(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	public void setEventTypeRepository(EventTypeRepository eventTypeRepository) {
		this.eventTypeRepository = eventTypeRepository;
	}

	public void setSession(UserSession session) {
		this.session = session;
	}

	public void setEventType(String eventType) {
		log.debug("eventType: " + eventType);
	}

	public String getEventType() {
		return "";
	}

	public void setStateRepository(StateRepository stateRepository) {
		this.stateRepository = stateRepository;
	}
	
	public void setArticleImageRepository(
			ArticleImageRepository articleImageRepository) {
		this.articleImageRepository = articleImageRepository;
	}

	public void setEventArticleImageRepository(
			EventArticleImageRepository eventArticleImageRepository) {
		this.eventArticleImageRepository = eventArticleImageRepository;
	}

	public void setEventStatusRepository(EventStatusRepository eventStatusRepository) {
		this.eventStatusRepository = eventStatusRepository;
	}

	public void setImageDao(ImageDao imageDao) {
		this.imageDao = imageDao;
	}

	public void validateNotEmpty(FacesContext context, UIComponent component, Object obj) {
		String code = (String)obj;
		
		if(code.equalsIgnoreCase("NONE")) {
			 FacesMessage message = new FacesMessage();
	            message.setDetail("Required");
	            message.setSummary("Required");
	            message.setSeverity(FacesMessage.SEVERITY_ERROR);
	            throw new ValidatorException(message);
		}
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		UploadedFile file = event.getFile();
		
		byte [] resizedBytes = null;
		
		try {
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(file.getContents()));
			log.debug("width: " + img.getWidth(null));
			log.debug("height: " + img.getHeight(null));
			BufferedImage bufferedImage = Scalr.resize(img, 640);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpeg", baos);
			resizedBytes = baos.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArticleImage image = new ArticleImage();
		image.setData(resizedBytes);
		image.setName(file.getFileName());
		image.setSize(file.getSize());
		image.setUsername(session.getUserAccount().getUsername());
		
		articleImageRepository.save(image);
		
		EventArticleImage eventImage = new EventArticleImage();
		eventImage.setEventSeqId(this.event.getEventSeqId());
		eventImage.setArticleImageSeqId(image.getArticleImageSeqId());
		
		eventArticleImageRepository.save(eventImage);
	}
	
	public void createNewEvent(ActionEvent ae) {
		this.event = new Event();
		
		this.event.setTitle("");
		this.event.setStartDtg(new Date());
		this.event.setEndDtg(new Date());
		this.event.setCity("");
		this.event.setStateCd("AK");
		this.event.setZipCode("");
		this.event.setContent("");
		this.event.setEventTypeCd("PU");
		this.event.setLatitude(BigDecimal.ZERO);
		this.event.setLongitude(BigDecimal.ZERO);
		this.event.setUsername(session.getUserAccount().getUsername());
		this.event.setEventStatusCd("P");
		this.event.setIsNew(true);
		
		this.eventRepository.save(this.event);
		
		log.debug("event: " + this.event.getEventSeqId());
		
		this.event.setTitle(null);
		this.event.setStartDtg(null);
		this.event.setEndDtg(null);
		this.event.setCity(null);
		this.event.setStateCd(null);
		this.event.setZipCode(null);
		this.event.setContent(null);
		this.event.setEventTypeCd(null);
		this.event.setLatitude(null);
		this.event.setLongitude(null);
		
		isNew = true;
	}
	
	public void save(ActionEvent ae) {
	}
	
	public String save() {
		final Geocoder geocoder = new Geocoder();
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(getEventAddress()).setLanguage("en").getGeocoderRequest();
		GeocodeResponse geocode = geocoder.geocode(geocoderRequest);
		
		this.event.setLatitude(geocode.getResults().get(0).getGeometry().getLocation().getLat());
		this.event.setLongitude(geocode.getResults().get(0).getGeometry().getLocation().getLng());
		
		log.debug(this.event.getLatitude() + ":" + this.event.getLongitude());
		
		this.event.setIsNew(false);
		
		if(!session.getIsModerator()) {
			this.event.setEventStatusCd("P");
		}
		
		this.eventRepository.save(this.event);
		
		return "events";
	}
	
	public String delete() {
		if(isNew) {
			this.eventRepository.delete(this.event);
		}
		
		return "events";
	}
	
	public MapModel getEventMapModel() {
		DefaultMapModel simpleModel = new DefaultMapModel();

		if(this.event.getLatitude() != null && this.event.getLongitude() != null) {
			simpleModel.addOverlay(new Marker(new LatLng(this.event.getLatitude()
				.doubleValue(), this.event.getLongitude().doubleValue()),
				this.event.getTitle(), this.event.getCity()));
		}

		return simpleModel;
	}
	
	public String getEventAddress() {
		return this.event.getAddress1() + ", " + this.event.getCity() + ", " + this.event.getStateCd() + " " + this.event.getZipCode();
	}
	
	public List<EventArticleImage> getImages() {
		return this.eventArticleImageRepository.findByEventSeqId(this.event.getEventSeqId());
	}
	
	public String deleteImage(){
		 log.debug("deleteImage()");
		 
		 Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		 String imageSeqId = (String)params.get("imageSeqId");
		log.debug("id: " + imageSeqId);
		
		imageDao.deleteImage(Integer.parseInt(imageSeqId));
		
		return "";
	  }
	
	public String editEvent() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		this.event = eventRepository.findByEventSeqId(Integer
				.valueOf((String) params.get("id")));
		
		isNew = false;

		return "new_event";
	}
}
