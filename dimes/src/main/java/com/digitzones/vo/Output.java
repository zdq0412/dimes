package com.digitzones.vo;
/**
 * 产量
 * @author zdq
 * 2018年7月9日
 */
public class Output {
	/**当前年的月份*/
	private String month;
	/**产量值*/
	private String outputValue;
	/**z轴文本：人员信息，工序，设备*/
	private String  text;
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getOutputValue() {
		return outputValue;
	}
	public void setOutputValue(String outputValue) {
		this.outputValue = outputValue;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
