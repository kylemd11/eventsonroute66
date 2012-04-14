package com.kyle.route66.db.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kyle.route66.db.model.ArticleImage;

@Repository("ImageDao")
public class ImageDao {
	private static final Log log = LogFactory.getLog(ImageDao.class); 
	
	@PersistenceContext
	private EntityManager em;
	
	public ImageDao() {
	}

	public ArticleImage getImage(Integer eventSeqId, String filename) {
		log.debug("event seq id: " + eventSeqId);
		log.debug("filename: " + filename);
		boolean multiple = false;
		
		String sql = "select i from ArticleImage i, EventArticleImage e where i.articleImageSeqId = e.articleImageSeqId and i.name = :filename and e.eventSeqId = :eventSeqId";

		log.debug("JPQL: " + sql);

		Query query = em.createQuery(sql);

		query.setParameter("filename", filename);
		query.setParameter("eventSeqId", eventSeqId);

		query.setMaxResults(1);
		
		return (ArticleImage)query.getSingleResult();
	}
	
	@Transactional
	public void deleteImage(Integer articleImageSeqId) {
		String imageSql = "delete from ArticleImage i where i.articleImageSeqId = :articleImageSeqId";
		String eventImageSql = "delete from EventArticleImage i where i.articleImageSeqId = :articleImageSeqId";
		
		Query query = em.createQuery(imageSql);
		query.setParameter("articleImageSeqId", articleImageSeqId);		
		query.executeUpdate();
		
		query = em.createQuery(eventImageSql);
		query.setParameter("articleImageSeqId", articleImageSeqId);		
		query.executeUpdate();
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}
