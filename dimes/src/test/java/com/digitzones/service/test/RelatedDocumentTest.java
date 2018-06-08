package com.digitzones.service.test;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.digitzones.constants.Constant;
import com.digitzones.model.Department;
import com.digitzones.model.RelatedDocument;
import com.digitzones.service.IDepartmentService;
import com.digitzones.service.IRelatedDocumentService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:springContext-*.xml"})
public class RelatedDocumentTest {
	private IRelatedDocumentService relatedDocumentService;
	private IDepartmentService departmentService;
	@Autowired
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	@Autowired
	public void setRelatedDocumentService(IRelatedDocumentService relatedDocumentService) {
		this.relatedDocumentService = relatedDocumentService;
	}
	@Test
	public void testAddRelatedDocument() {
		Department d = departmentService.queryDepartmentByProperty("name","研发部一");

		for(int i = 0;i<100;i++) {
			RelatedDocument rd0 = new RelatedDocument();
			rd0.setDocumentName("需求文档" + i);
			rd0.setNote("内部资料" + i);
			rd0.setRelatedType(Constant.RelatedDoc.DEPARTMENT);
			rd0.setRelatedId(d.getId());
			rd0.setRelatedCode("RD0" + i);
			rd0.setRelatedName("测试1" + i);
			rd0.setUploadDate(new Date());
			rd0.setUploadUsername("admin");

			relatedDocumentService.addRelatedDocument(rd0);
		}
	}
}
