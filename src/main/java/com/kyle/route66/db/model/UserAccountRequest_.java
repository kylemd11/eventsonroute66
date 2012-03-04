package com.kyle.route66.db.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-02-22T16:21:29.581-0600")
@StaticMetamodel(UserAccountRequest.class)
public class UserAccountRequest_ {
	public static volatile SingularAttribute<UserAccountRequest, String> username;
	public static volatile SingularAttribute<UserAccountRequest, String> requestId;
	public static volatile SingularAttribute<UserAccountRequest, Date> expirationDate;
}
