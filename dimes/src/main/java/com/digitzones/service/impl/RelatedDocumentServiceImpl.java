package com.digitzones.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IRelatedDocumentDao;
import com.digitzones.model.Pager;
import com.digitzones.model.RelatedDocument;
import com.digitzones.service.IRelatedDocumentService;
@SuppressWarnings("rawtypes")
@Service
public class RelatedDocumentServiceImpl implements IRelatedDocumentService {
	private IRelatedDocumentDao relatedDocumentDao;
	@Autowired
	public void setRelatedDocumentDao(IRelatedDocumentDao relatedDocumentDao) {
		this.relatedDocumentDao = relatedDocumentDao;
	}
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return relatedDocumentDao.findByPage(hql, pageNo, pageSize, values);
	}
	@Override
	public Serializable addRelatedDocument(RelatedDocument relatedDocument) {
		return relatedDocumentDao.save(relatedDocument);
	}
	@Override
	public void deleteRelatedDocument(Serializable id) {
		relatedDocumentDao.deleteById(id);
	}
	@Override
	public void updateRelatedDocument(RelatedDocument relatedDocument) {
		relatedDocumentDao.update(relatedDocument);
	}
}
