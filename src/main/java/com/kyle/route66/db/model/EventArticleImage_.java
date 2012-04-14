package com.kyle.route66.db.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-03-31T19:48:48.344-0500")
@StaticMetamodel(EventArticleImage.class)
public class EventArticleImage_ {
	public static volatile SingularAttribute<EventArticleImage, Integer> eventArticleImageSeqId;
	public static volatile SingularAttribute<EventArticleImage, Integer> eventSeqId;
	public static volatile SingularAttribute<EventArticleImage, Integer> articleImageSeqId;
	public static volatile SingularAttribute<EventArticleImage, Event> event;
	public static volatile SingularAttribute<EventArticleImage, ArticleImage> image;
}
