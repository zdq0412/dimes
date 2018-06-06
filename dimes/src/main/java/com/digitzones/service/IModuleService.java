package com.digitzones.service;
import java.io.Serializable;
import java.util.List;

import com.digitzones.model.Module;
/**
 * 功能模块操作
 * @author zdq
 * 2018年6月6日
 */
public interface IModuleService {
	/**
	 * 添加一个功能模块
	 * @param module
	 * @return
	 */
	public Serializable addModule(Module module);
	/**
	 * 更新功能模块
	 * @param module
	 */
	public void updateModule(Module module);
	/**
	 * 删除模块
	 * @param id
	 */
	public void deleteModule(Serializable id);
	/**
	 * 查找顶级模块
	 * @return
	 */
	public List<Module> queryTopModule();
	/**
	 * 根据父模块id查找子模块
	 * @param id
	 * @return
	 */
	public List<Module> querySubModule(Serializable id);
}
