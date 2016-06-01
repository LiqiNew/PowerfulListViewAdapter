package com.liq.powerfullistviewadapter.latest;

import com.liq.powerfullistviewadapter.latest.AdapterFather.AdapterInnerClass;


/**
 * 适配器回调接口
 * 
 * @author Administrator
 * 
 * @param <T>
 */
public interface AdapterAgencyInterface<T> {
	/**
	 * 适配器回调方法
	 * 
	 * @param adapterInnerClass
	 *            保存控件对象
	 * @param t
	 *            保存数据对象
	 */
	@SuppressWarnings("rawtypes")
	public void setViewData(AdapterInnerClass adapterInnerClass, T t);
}
