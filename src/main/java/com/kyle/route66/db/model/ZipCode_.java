package com.kyle.route66.db.model;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-03-07T13:52:41.050-0600")
@StaticMetamodel(ZipCode.class)
public class ZipCode_ {
	public static volatile SingularAttribute<ZipCode, String> zipCode;
	public static volatile SingularAttribute<ZipCode, String> state;
	public static volatile SingularAttribute<ZipCode, String> fips_region;
	public static volatile SingularAttribute<ZipCode, String> city;
	public static volatile SingularAttribute<ZipCode, BigDecimal> latitude;
	public static volatile SingularAttribute<ZipCode, BigDecimal> longitude;
}
