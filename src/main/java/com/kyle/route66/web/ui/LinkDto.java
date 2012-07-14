package com.kyle.route66.web.ui;

public class LinkDto {
	private Integer linkSeqId;
	private String title;
	private String url;
	private String summary;

	private boolean dirty = false;

	public LinkDto() {
	}

	public LinkDto(Integer linkSeqId, String title, String url, String summary) {
		super();
		this.linkSeqId = linkSeqId;
		this.title = title;
		this.url = url;
		this.summary = summary;
	}

	public Integer getLinkSeqId() {
		return linkSeqId;
	}

	public void setLinkSeqId(Integer linkSeqId) {
		this.linkSeqId = linkSeqId;
		dirty = true;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		dirty = true;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		dirty = true;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
		dirty = true;
	}

	public boolean isDirty() {
		return dirty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((linkSeqId == null) ? 0 : linkSeqId.hashCode());
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinkDto other = (LinkDto) obj;
		if (linkSeqId == null) {
			if (other.linkSeqId != null)
				return false;
		} else if (!linkSeqId.equals(other.linkSeqId))
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	public void resetDirty() {
		dirty = false;
	}
}
