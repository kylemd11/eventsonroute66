package com.kyle.route66.db.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-03-26T20:42:08.775-0500")
@StaticMetamodel(Comment.class)
public class Comment_ {
	public static volatile SingularAttribute<Comment, Integer> commentSeqId;
	public static volatile SingularAttribute<Comment, String> username;
	public static volatile SingularAttribute<Comment, String> content;
	public static volatile SingularAttribute<Comment, Date> createDtg;
	public static volatile SingularAttribute<Comment, Integer> eventSeqId;
	public static volatile SingularAttribute<Comment, Date> updateDtg;
	public static volatile SingularAttribute<Comment, Integer> enabled;
	public static volatile SingularAttribute<Comment, UserAccount> user;
	public static volatile SingularAttribute<Comment, Event> event;
}
