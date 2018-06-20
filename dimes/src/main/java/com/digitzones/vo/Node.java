package com.digitzones.vo;

import java.util.List;

/**
 * 树形结构的节点
 * @author zdq
 * 2018年6月19日
 */
public class Node {
	private Long id;
	private String name;
	private List<Node> children;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Node> getChildren() {
		return children;
	}
	public void setChildren(List<Node> children) {
		this.children = children;
	}
}
