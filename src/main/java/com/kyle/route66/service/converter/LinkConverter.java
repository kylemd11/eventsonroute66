package com.kyle.route66.service.converter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kyle.route66.db.model.Link;
import com.kyle.route66.web.model.LinksBean;
import com.kyle.route66.web.ui.LinkDto;

public class LinkConverter implements Converter {
	private static final Log log = LogFactory.getLog(LinkConverter.class);
	
	@Override
	public Object toDto(Object obj, boolean isReference) {
		log.debug("toDto()");
		Link db = (Link)obj;
		if(!isReference) {
			return new LinkDto(db.getLinkSeqId(), db.getTitle(), db.getUrl(), db.getSummary());
		}
		else {
			return new LinkDto(db.getLinkSeqId(), null, null, null);
		}
	}

	@Override
	public Object toDb(Object obj) {
		log.debug("toDb()");
		LinkDto dto = (LinkDto)obj;
		return new Link(dto.getLinkSeqId(), dto.getTitle(), dto.getUrl(), dto.getSummary());
	}
}
