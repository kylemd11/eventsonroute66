package com.kyle.route66.db.model;
// Generated Feb 28, 2012 7:59:49 PM by Hibernate Tools 3.4.0.CR1


import static javax.persistence.GenerationType.IDENTITY;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.kyle.route66.util.Utils;

/**
 * State generated by hbm2java
 */
@Entity
@Table(name="article_image"
    ,catalog="route66Db"
)
public class ArticleImage  implements java.io.Serializable {

	private Integer articleImageSeqId;
     private String name;
     private Long size;
     private byte[] data;
     private Date createDtg;
     private String username;

    public ArticleImage() {
    }

    public ArticleImage(Integer articleImageSeqId, String name, Long size,
			byte[] data, Date createDtg, String username) {
		super();
		this.articleImageSeqId = articleImageSeqId;
		this.name = name;
		this.size = size;
		this.data = data;
		this.createDtg = createDtg;
		this.username = username;
	}
    
    
     @Id @GeneratedValue(strategy=IDENTITY)
     @Column(name="article_image_seq_id", unique=true, nullable=false)
    public Integer getArticleImageSeqId() {
		return articleImageSeqId;
	}

	public void setArticleImageSeqId(Integer articleImageSeqId) {
		this.articleImageSeqId = articleImageSeqId;
	}

	@Column(name="name", nullable=false, length=256)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Transient
	public String getEncodedName() {
		try {
			return URLEncoder.encode(this.name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return "";
	}

	@Column(name="size", nullable=false)
	public Long getSize() {
		return size;
	}

	public void setSize(long l) {
		this.size = l;
	}
	
	@Transient
	public String getPrettySize() {
		return Utils.humanReadableByteCount(this.size, true);
	}

	@Lob
	@Column( name = "data" )
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_dtg", nullable=false, length=19)
	public Date getCreateDtg() {
		return createDtg;
	}

	public void setCreateDtg(Date createDtg) {
		this.createDtg = createDtg;
	}

	@Column(name="username", nullable=false, length=50)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}

