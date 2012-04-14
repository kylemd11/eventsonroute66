package com.kyle.route66.db.model;
// Generated Feb 28, 2012 7:59:49 PM by Hibernate Tools 3.4.0.CR1


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Event generated by hbm2java
 */
@Entity
@Table(name="event"
    ,catalog="route66Db"
)
public class Event  implements java.io.Serializable {


     private Integer eventSeqId;
     private String username;
     private String title;
     private Date startDtg;
     private Date endDtg;
     private String address1;
     private String address2;
     private String city;
     private String stateCd;
     private String zipCode;
     private String content;
     private String eventTypeCd;
     private Date createDtg;
     private Date updateDtg;
     private State state;
     private EventType eventType;
     private UserAccount user;
     private BigDecimal	latitude;
     private BigDecimal	longitude;
     
     private List<Comment> comments;
	private EventStatus eventStatus;
	private String eventStatusCd;
	
	private boolean isNew;
     
    public Event() {
    }

	
    public Event(String username, String title, Date startDtg, Date endDtg, String city, String stateCd, String zipCode, String content, String eventTypeCd, Date createDtg, BigDecimal latitude, BigDecimal longitude) {
        this.username = username;
        this.title = title;
        this.startDtg = startDtg;
        this.endDtg = endDtg;
        this.city = city;
        this.stateCd = stateCd;
        this.zipCode = zipCode;
        this.content = content;
        this.eventTypeCd = eventTypeCd;
        this.createDtg = createDtg;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public Event(String username, String title, Date startDtg, Date endDtg, String address1, String address2, String city, String stateCd, String zipCode, String content, String eventTypeCd, Date createDtg, BigDecimal latitude, BigDecimal longitude, Date updateDtg, boolean isNew) {
       this.username = username;
       this.title = title;
       this.startDtg = startDtg;
       this.endDtg = endDtg;
       this.address1 = address1;
       this.address2 = address2;
       this.city = city;
       this.stateCd = stateCd;
       this.zipCode = zipCode;
       this.content = content;
       this.eventTypeCd = eventTypeCd;
       this.createDtg = createDtg;
       this.latitude = latitude;
       this.longitude = longitude;
       this.updateDtg = updateDtg;
       this.isNew = isNew;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="event_seq_id", unique=true, nullable=false)
    public Integer getEventSeqId() {
        return this.eventSeqId;
    }
    
    public void setEventSeqId(Integer eventSeqId) {
        this.eventSeqId = eventSeqId;
    }

    
    @Column(name="username", nullable=false, length=50)
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    
    @Column(name="title", nullable=false, length=256)
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="start_dtg", nullable=false, length=19)
    public Date getStartDtg() {
        return this.startDtg;
    }
    
    public void setStartDtg(Date startDtg) {
        this.startDtg = startDtg;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="end_dtg", nullable=false, length=19)
    public Date getEndDtg() {
        return this.endDtg;
    }
    
    public void setEndDtg(Date endDtg) {
        this.endDtg = endDtg;
    }

    
    @Column(name="address_1", length=256)
    public String getAddress1() {
        return this.address1;
    }
    
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    
    @Column(name="address_2", length=256)
    public String getAddress2() {
        return this.address2;
    }
    
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    
    @Column(name="city", nullable=false, length=128)
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }

    
    @Column(name="state_cd", nullable=false, length=2)
    public String getStateCd() {
        return this.stateCd;
    }
    
    public void setStateCd(String stateCd) {
        this.stateCd = stateCd;
    }

    
    @Column(name="zip_code", nullable=false, length=10)
    public String getZipCode() {
        return this.zipCode;
    }
    
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    
    @Column(name="content", nullable=false)
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    @Column(name="event_type_cd", nullable=false)
    public String getEventTypeCd() {
        return this.eventTypeCd;
    }
    
    public void setEventTypeCd(String eventTypeCd) {
        this.eventTypeCd = eventTypeCd;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_dtg", nullable=false, length=19)
    public Date getCreateDtg() {
        return this.createDtg;
    }
    
    public void setCreateDtg(Date createDtg) {
        this.createDtg = createDtg;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_dtg", length=19)
    public Date getUpdateDtg() {
        return this.updateDtg;
    }
    
    public void setUpdateDtg(Date updateDtg) {
        this.updateDtg = updateDtg;
    }

    @Column(name="latitude", nullable=false)
    public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	@Column(name="longitude", nullable=false)
	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	
	
	@Column(name="is_new", nullable = false)
	public boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(boolean isNew) {
		this.isNew = isNew;
	}

	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name="state_cd", insertable=false, updatable=false)
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name="username", insertable=false, updatable=false)
	public UserAccount getUser() {
		return user;
	}

	public void setUser(UserAccount user) {
		this.user = user;
	}
	
	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name="event_type_cd", insertable=false, updatable=false)
	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "event")
	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	@Column(name="event_status_cd", nullable=false)
    public String getEventStatusCd() {
        return this.eventStatusCd;
    }
    
    public void setEventStatusCd(String eventStatusCd) {
        this.eventStatusCd = eventStatusCd;
    }
	
	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name="event_status_cd", insertable=false, updatable=false)
	public EventStatus getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(EventStatus eventStatus) {
		this.eventStatus = eventStatus;
	}
	
	@Transient
	public boolean getIsPending() {
		if(this.eventStatusCd.equals("P")) {
			return true;
		}
		else {
			return false;
		}
	}
	
}


