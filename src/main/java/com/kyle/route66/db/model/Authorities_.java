package com.kyle.route66.db.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-02-22T16:24:30.283-0600")
@StaticMetamodel(Authorities.class)
public class Authorities_ {
	public static volatile SingularAttribute<Authorities, String> username;
	public static volatile SingularAttribute<Authorities, String> authority;
	public static volatile SingularAttribute<Authorities, Users> user;
}
