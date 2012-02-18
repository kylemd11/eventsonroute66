package com.kyle.route66.db.model;
// Generated Feb 18, 2012 12:52:29 PM by Hibernate Tools 3.4.0.CR1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * UserAccountRequest generated by hbm2java
 */
@Entity
@Table(name="user_account_request"
    ,catalog="route66Db"
)
public class UserAccountRequest  implements java.io.Serializable {


     private String username;
     private String requestId;
     private Date expirationDate;

    public UserAccountRequest() {
    }

    public UserAccountRequest(String username, String requestId, Date expirationDate) {
       this.username = username;
       this.requestId = requestId;
       this.expirationDate = expirationDate;
    }
   
     @Id 

    
    @Column(name="username", unique=true, nullable=false, length=50)
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    
    @Column(name="requestId", nullable=false)
    public String getRequestId() {
        return this.requestId;
    }
    
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="expiration_date", nullable=false, length=10)
    public Date getExpirationDate() {
        return this.expirationDate;
    }
    
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }




}


