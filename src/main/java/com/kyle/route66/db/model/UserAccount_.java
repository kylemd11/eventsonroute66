package com.kyle.route66.db.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-02-28T20:07:59.804-0600")
@StaticMetamodel(UserAccount.class)
public class UserAccount_ {
	public static volatile SingularAttribute<UserAccount, String> username;
	public static volatile SingularAttribute<UserAccount, String> firstName;
	public static volatile SingularAttribute<UserAccount, String> lastName;
	public static volatile SingularAttribute<UserAccount, String> emailAddr;
	public static volatile SingularAttribute<UserAccount, String> streetAddr1;
	public static volatile SingularAttribute<UserAccount, String> streetAddr2;
	public static volatile SingularAttribute<UserAccount, String> city;
	public static volatile SingularAttribute<UserAccount, String> state;
	public static volatile SingularAttribute<UserAccount, String> zip;
	public static volatile SingularAttribute<UserAccount, Users> user;
	public static volatile SingularAttribute<UserAccount, UserAccountRequest> accountRequest;
	public static volatile ListAttribute<UserAccount, Event> events;
}
