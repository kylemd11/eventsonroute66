package com.kyle.route66.db.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-03-31T20:35:07.475-0500")
@StaticMetamodel(ArticleImage.class)
public class ArticleImage_ {
	public static volatile SingularAttribute<ArticleImage, Integer> articleImageSeqId;
	public static volatile SingularAttribute<ArticleImage, String> name;
	public static volatile SingularAttribute<ArticleImage, byte[]> data;
	public static volatile SingularAttribute<ArticleImage, Date> createDtg;
	public static volatile SingularAttribute<ArticleImage, String> username;
}
