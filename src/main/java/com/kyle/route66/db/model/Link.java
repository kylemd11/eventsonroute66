package com.kyle.route66.db.model;

// Generated Feb 28, 2012 7:59:49 PM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * State generated by hbm2java
 */
@Entity
@Table(name = "link")
public class Link implements java.io.Serializable {

	private Integer linkSeqId;
	private String title;
	private String url;
	private String summary;

	public Link() {
	}

	public Link(Integer linkSeqId, String title, String url) {
		this.linkSeqId = linkSeqId;
		this.title = title;
		this.url = url;
	}

	public Link(Integer linkSeqId, String title, String url, String summary) {
		this.linkSeqId = linkSeqId;
		this.title = title;
		this.url = url;
		this.summary = summary;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "link_seq_id", unique = true, nullable = false)
	public Integer getLinkSeqId() {
		return linkSeqId;
	}

	public void setLinkSeqId(Integer linkSeqId) {
		this.linkSeqId = linkSeqId;
	}

	@Column(name = "title", unique = true, nullable = false, length = 2)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "url", unique = true, nullable = false, length = 250)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "summary", unique = true, nullable = true)
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
}
