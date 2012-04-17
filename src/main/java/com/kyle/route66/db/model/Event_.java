package com.kyle.route66.db.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-04-16T20:09:58.572-0500")
@StaticMetamodel(Event.class)
public class Event_ {
	public static volatile SingularAttribute<Event, Integer> eventSeqId;
	public static volatile SingularAttribute<Event, String> username;
	public static volatile SingularAttribute<Event, String> title;
	public static volatile SingularAttribute<Event, Date> startDtg;
	public static volatile SingularAttribute<Event, Date> endDtg;
	public static volatile SingularAttribute<Event, String> address1;
	public static volatile SingularAttribute<Event, String> address2;
	public static volatile SingularAttribute<Event, String> city;
	public static volatile SingularAttribute<Event, String> stateCd;
	public static volatile SingularAttribute<Event, String> zipCode;
	public static volatile SingularAttribute<Event, String> content;
	public static volatile SingularAttribute<Event, String> eventTypeCd;
	public static volatile SingularAttribute<Event, Date> createDtg;
	public static volatile SingularAttribute<Event, Date> updateDtg;
	public static volatile SingularAttribute<Event, BigDecimal> latitude;
	public static volatile SingularAttribute<Event, BigDecimal> longitude;
	public static volatile SingularAttribute<Event, Boolean> isNew;
	public static volatile SingularAttribute<Event, State> state;
	public static volatile SingularAttribute<Event, UserAccount> user;
	public static volatile SingularAttribute<Event, EventType> eventType;
	public static volatile ListAttribute<Event, Comment> comments;
	public static volatile SingularAttribute<Event, String> eventStatusCd;
	public static volatile SingularAttribute<Event, EventStatus> eventStatus;
	public static volatile SingularAttribute<Event, Boolean> allDay;
}
