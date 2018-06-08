package com.digitzones.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.digitzones.dao.IRelatedDocumentDao;
import com.digitzones.model.Department;
import com.digitzones.model.Pager;
import com.digitzones.model.RelatedDocument;

public class RelatedDocumentDaoImpl extends CommonDaoImpl<RelatedDocument> implements IRelatedDocumentDao {

	public RelatedDocumentDaoImpl() {
		super(RelatedDocument.class);
	}
}
