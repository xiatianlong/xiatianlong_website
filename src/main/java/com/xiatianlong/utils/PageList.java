package com.xiatianlong.utils;

import java.util.List;
/**
 * 分页查询结果封装类
 * Created by xiatianlong on 2017/4/16.
 */
@SuppressWarnings("unchecked")
public class PageList {
	private int curPageNO;
	private int offset;
	private String toolBar;// 分页工具条
	private int count;
	private List resultList = null;// 结果集

	public PageList() {

	}

	/**
	 * 不使用分页标签的初始化构造方法
	 * 
	 * @param resultList 结果列表
	 * @param toolBar 分页工具条
	 * @param offset 偏移
	 * @param curPageNO 当前页码
	 * @param count 计数
	 */
	public PageList(List resultList, String toolBar, int offset, int curPageNO, int count) {
		this.curPageNO = curPageNO;
		this.offset = offset;
		this.toolBar = toolBar;
		this.resultList = resultList;
		this.count = count;
	}

	public <T> List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getToolBar() {
		return toolBar;
	}

	public int getCount() {
		return count;
	}

	public int getCurPageNO() {
		return curPageNO;
	}

	public void setCurPageNO(int curPageNO) {
		this.curPageNO = curPageNO;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

}
