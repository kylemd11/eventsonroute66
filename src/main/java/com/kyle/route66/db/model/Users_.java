package com.kyle.route66.db.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-02-22T16:30:24.763-0600")
@StaticMetamodel(Users.class)
public class Users_ {
	public static volatile SingularAttribute<Users, String> username;
	public static volatile SingularAttribute<Users, String> password;
	public static volatile SingularAttribute<Users, Integer> enabled;
	public static volatile ListAttribute<Users, Authorities> authorities;
}
