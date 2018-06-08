package com.digitzones.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.Pager;
import com.digitzones.model.RelatedDocument;
import com.digitzones.service.IRelatedDocumentService;
/**
 * 部门管理控制器
 * @author zdq
 * 2018年6月7日
 */
@Controller
@RequestMapping("/relatedDocument")
public class RelatedDocumentController {
	private IRelatedDocumentService relatedDocumentService;
	@Autowired
	public void setRelatedDocumentService(IRelatedDocumentService relatedDocumentService) {
		this.relatedDocumentService = relatedDocumentService;
	}
	
	
} 
