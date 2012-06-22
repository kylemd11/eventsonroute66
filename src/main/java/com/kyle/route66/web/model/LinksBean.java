package com.kyle.route66.web.model;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kyle.route66.db.dao.LinkRepository;
import com.kyle.route66.db.dao.UserAccountRepository;
import com.kyle.route66.db.model.Event;
import com.kyle.route66.db.model.Link;
import com.kyle.route66.service.LinkService;
import com.kyle.route66.service.model.EventCriteria;
import com.kyle.route66.web.model.user.StatusBean;
import com.kyle.route66.web.model.user.UserSession;

@Service("LinksBean")
@Scope("request")
public class LinksBean {
	private static final Log log = LogFactory.getLog(LinksBean.class);

	@Autowired
	@Qualifier("UserSession")
	private UserSession session;

	@Autowired
	private LinkRepository linkRepository;
	
	@Autowired
	private LinkService linkService;
	
	private LazyDataModel<Link> lazyModel = null;
	
	private Integer linkSeqId;
	private String title;
	private String url;
	private String summary;
	
	public LinksBean() {
		lazyModel = new LazyDataModel<Link>() {
			
			@Override
			public void setRowIndex(final int rowIndex) {
				if (rowIndex == -1 || getPageSize() == 0) {
					super.setRowIndex(-1);
				} else {
					super.setRowIndex(rowIndex % getPageSize());
				}
			}
			
			@Override
			public Link getRowData(String rowKey) {
				return linkService.find(Integer.parseInt(rowKey));
			}

			@Override
			public Object getRowKey(Link link) {
				return link.getLinkSeqId();
			}

//			@Override
//			public int getRowCount() {
//				return linkService.getCount();
//			}

			@Override
			public List<Link> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
				log.debug("first: " + first);
				log.debug("pageSize: " + pageSize);
				setRowCount(linkService.getCount());
				
				return linkService.getLinks(first, pageSize);
			}
		};
	}
	
	public LazyDataModel<Link> getLazyModel() {
		return lazyModel;
	}
	
	public List<Link> getLinks() {
		List<Link> links = new ArrayList<Link>();
		
		for(Link link : linkRepository.findAll()) {
			links.add(link);
		}
		
		return links;
	}
	
	public String setLink() {
		log.debug("setLink()");
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();

		Link link = linkRepository.findOne(Integer.parseInt((String) params
				.get("linkSeqId")));
		
		this.linkSeqId = link.getLinkSeqId();
		this.title = link.getTitle();
		this.url = link.getUrl();
		this.summary = link.getSummary();

		return "";
	}
	
	public String newLink() {
		log.debug("newLink()");
		
		this.linkSeqId = null;
		this.title = "";
		this.url = "";
		this.summary = "";
		
		return "";
	}
	
	public String save() {
		Link link;
		if(this.linkSeqId != null) {
			link = linkRepository.findOne(this.linkSeqId);
		}
		else {
			link = new Link();			
		}
		
		link.setTitle(this.title);
		link.setUrl(this.url);
		link.setSummary(this.summary);
		
		linkRepository.save(link);
		return "";
	}
	
	public String delete() {
		linkRepository.delete(this.linkSeqId);
		return "";
	}

	public void setSession(UserSession session) {
		this.session = session;
	}

	public void setLinkRepository(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
	}

	public Integer getLinkSeqId() {
		return linkSeqId;
	}

	public void setLinkSeqId(Integer linkSeqId) {
		this.linkSeqId = linkSeqId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}
	
	
	
	
}
