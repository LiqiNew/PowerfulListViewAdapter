package com.liq.powerfullistviewadapter.latest;


/**
 * listView传输的Item布局和控件集合对象
 * 
 * @author Administrator
 * 
 */
public class AdapterItemLayoutData{
	/**
	 * 布局ID（必传）
	 */
	private int layoutId;
	/**
	 * 布局标识
	 */
	private int layouTag=0;
	
	
	public int getLayoutId() {
		return layoutId;
	}


	public void setLayoutId(int layoutId) {
		this.layoutId = layoutId;
	}


	public int getLayouTag() {
		return layouTag;
	}


	public void setLayouTag(int layouTag) {
		this.layouTag = layouTag;
	}


	@Override
	public String toString() {
		return "AdapterItemLayoutData [layoutId=" + layoutId + ", layouTag="
				+ layouTag + "]";
	}


	public AdapterItemLayoutData() {
	}

}
