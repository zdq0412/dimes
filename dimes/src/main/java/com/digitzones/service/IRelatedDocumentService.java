package com.digitzones.service;

import java.io.Serializable;

import com.digitzones.model.RelatedDocument;
/**
 * 相关文档操作业务接口
 * @author zdq
 * 2018年6月8日
 */
public interface IRelatedDocumentService extends ICommonService<RelatedDocument> {
	/**
	 * 添加相关文档
	 * @param relatedDocument
	 * @return
	 */
	public Serializable addRelatedDocument(RelatedDocument relatedDocument);
	/**
	 * 删除相关文档
	 * @param id
	 */
	public void deleteRelatedDocument(Serializable id);
	/**
	 * 更新相关文档
	 * @param relatedDocument
	 */
	public void updateRelatedDocument(RelatedDocument relatedDocument);
}
