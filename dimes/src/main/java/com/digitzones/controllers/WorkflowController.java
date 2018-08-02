package com.digitzones.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.util.StringUtil;
import com.digitzones.vo.ProcessDefinitionVO;

/**
 * 工作流控制器
 * @author zdq
 * 2018年8月1日
 */
@Controller
@RequestMapping("/workflow")
public class WorkflowController {
	private ProcessEngine engine;
	@Autowired
	public void setEngine(ProcessEngine engine) {
		this.engine = engine;
	}
	/**
	 * 部署流程定义
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/deploy.do",method=RequestMethod.POST)
	@ResponseBody
	public ModelMap deploymentProcessDefinition(@RequestParam("file")Part file) {
		ModelMap modelMap = new ModelMap();
		RepositoryService repositoryService = engine.getRepositoryService();
		String fileName = file.getSubmittedFileName();
		InputStream fileInputStream = null;
		try {
			fileInputStream = file.getInputStream();
			String extension = FilenameUtils.getExtension(fileName);
			DeploymentBuilder deployment = repositoryService.createDeployment();
			if(extension.equals("zip")||extension.equals("bar")) {
				ZipInputStream zipStream = new ZipInputStream(fileInputStream);
				deployment.addZipInputStream(zipStream);
			}else {
				deployment.addInputStream(fileName, fileInputStream);
			}
			deployment.deploy();
			modelMap.addAttribute("success",true);
			modelMap.addAttribute("msg","部署成功!");
		} catch (IOException e) {
			e.printStackTrace();
			modelMap.addAttribute("success",false);
			modelMap.addAttribute("msg","部署失败!");
		}finally {
			if(fileInputStream!=null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return modelMap;
	}
	/**
	 * 查询所有流程定义
	 * @return
	 */
	@RequestMapping("/queryAllProcessDefinitions.do")
	@ResponseBody
	public List<ProcessDefinitionVO> queryAllProcessDefinitions(){
		List<ProcessDefinition> list = engine.getRepositoryService()//与流程定义和部署对象相关的Service
				.createProcessDefinitionQuery()//创建一个流程定义查询
				.orderByProcessDefinitionVersion().asc()//按照版本的升序排列
				.list();//返回一个集合列表，封装流程定义


		List<ProcessDefinitionVO> vos = new ArrayList<>();
		//只保留最新版本
		Map<String, ProcessDefinition> map = new LinkedHashMap<>();
		if(list!=null &&list.size()>0) {
			for(ProcessDefinition pd : list) {
				map.put(pd.getKey(), pd);
			}
		}

		Collection<ProcessDefinition> lastVersionList = map.values();
		if(lastVersionList!=null &&lastVersionList.size()>0) {
			Iterator<ProcessDefinition> it = lastVersionList.iterator();
			while(it.hasNext()) {
				vos.add(definitionModel2vo(it.next()));
			}
		}
		return vos;
	}
	/**
	 * 将流程定义实体对象转换为VO对象
	 * @param pd
	 * @return
	 */
	private ProcessDefinitionVO definitionModel2vo(ProcessDefinition pd) {
		if(pd == null) {
			return null;
		}

		ProcessDefinitionVO vo = new ProcessDefinitionVO();
		vo.setId(pd.getId());
		vo.setDeploymentId(pd.getDeploymentId());
		vo.setDescription(pd.getDescription());
		vo.setDiagramResourceName(pd.getDiagramResourceName());
		vo.setVersion(pd.getVersion());
		vo.setResourceName(pd.getResourceName());
		vo.setName(pd.getName());
		vo.setKey(pd.getKey());
		return vo;
	}
	/**
	 * 根据deploymentId删除流程定义信息
	 * @param deploymentId
	 * @return
	 */
	@RequestMapping("/deleteProcessDeployment.do")
	@ResponseBody
	public ModelMap deleteProcessDeployment(String deploymentId) {
		deploymentId = StringUtil.remove(deploymentId, "'");
		ModelMap modelMap = new ModelMap();
		engine.getRepositoryService().deleteDeployment(deploymentId,true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
	/**
	 * 根据部署ID和图片名称查找流程图片
	 * @param deploymentId
	 * @return
	 */
	@RequestMapping("/queryProcessPic.do")
	@ResponseBody
	public ModelMap queryProcessPic(String deploymentId,String resourceName,HttpServletRequest request) {
		String dir = request.getServletContext().getRealPath("/")+"workflow/imgs/";
		File output = new File(dir,resourceName);
		ModelMap modelMap = new ModelMap();
		InputStream is = engine.getRepositoryService().getResourceAsStream(deploymentId, resourceName);
		try {
			FileUtils.copyInputStreamToFile(is, output);
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("path", "workflow/imgs/" +  resourceName);
			
		} catch (IOException e) {
			e.printStackTrace();
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "查找失败!");
		}
		return modelMap;
	}
}
