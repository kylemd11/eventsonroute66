package com.kyle.route66.db.model;
// Generated Feb 28, 2012 7:59:49 PM by Hibernate Tools 3.4.0.CR1


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * State generated by hbm2java
 */
@Entity
@Table(name="comment"
    ,catalog="route66Db"
)
public class Comment  implements java.io.Serializable {
	private Integer commentSeqId;
	private String username;
	private String content;
	private Date createDtg;
	private Date updateDtg;
	private Integer eventSeqId;
	private Integer enabled;
	
	private UserAccount user;
	private Event event;
	
	
    public Comment() {
    }
    
    public Comment(String username, String content, Date createDtg) {
		super();
		this.username = username;
		this.content = content;
		this.createDtg = createDtg;
	}

@Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="comment_seq_id", unique=true, nullable=false)
	public Integer getCommentSeqId() {
		return commentSeqId;
	}

	public void setCommentSeqId(Integer commentSeqId) {
		this.commentSeqId = commentSeqId;
	}

	@Column(name="username", nullable=false, length=50)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="content", nullable=false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_dtg", nullable=false, length=19)
	public Date getCreateDtg() {
		return createDtg;
	}

	public void setCreateDtg(Date createDtg) {
		this.createDtg = createDtg;
	}
	
	@Column(name="event_seq_id", unique=true, nullable=false)
	public Integer getEventSeqId() {
		return eventSeqId;
	}

	public void setEventSeqId(Integer eventSeqId) {
		this.eventSeqId = eventSeqId;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_dtg", nullable=false, length=19)
	public Date getUpdateDtg() {
		return updateDtg;
	}
	
	public void setUpdateDtg(Date updateDtg) {
		this.updateDtg = updateDtg;
	}

	@Column(name="enabled", unique=true, nullable=false)
	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name="username", insertable=false, updatable=false)
	public UserAccount getUser() {
		return user;
	}
	
	public void setUser(UserAccount user) {
		this.user = user;
	}	
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.LAZY)
	@JoinColumn(name="event_seq_id", insertable=false, updatable=false)
	public Event getEvent() {
		return event;
	}
	
	public void setEvent(Event event) {
		this.event = event;
	}

}


