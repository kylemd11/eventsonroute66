package com.kyle.route66.web.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.kyle.route66.db.model.Link;
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
	
	private Integer linkSeqId;
	private String title;
	private String url;
	private String summary;
	
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
	
	
	
	
}
