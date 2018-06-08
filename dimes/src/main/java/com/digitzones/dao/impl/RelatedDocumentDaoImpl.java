package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IRelatedDocumentDao;
import com.digitzones.model.RelatedDocument;
@Repository
public class RelatedDocumentDaoImpl extends CommonDaoImpl<RelatedDocument> implements IRelatedDocumentDao {
	public RelatedDocumentDaoImpl() {
		super(RelatedDocument.class);
	}
}
