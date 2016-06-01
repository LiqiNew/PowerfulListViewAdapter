package com.liq.powerfullistviewadapter.latest;

/**
 * 适配器用来判断是否显示那个布局的抽象方法
 * 
 * @author Administrator
 * 
 */
public abstract class AdapterDataJudge {
	/**
	 * (告诉我你要展示的布局在展示的布局集合里面的标识)
	 * 显示那个布局显示判断方法(根据布局AdapterItemLayoutData对象中LayoutTag定义来返回,如果要显示当前布局,
	 * 要求你此方法里面返回的值必须和AdapterItemLayoutData中LayoutTag设置的一致)
	 * 
	 * 
	 * @return (类型必须返回的类型必须是有顺序的从0开始的升序（0，1，2，3），最大数且不能大于要展示布局的集合长度)
	 */
	public abstract int onJudge();
	/**
	 * 更改数据标识
	 * @param judge
	 */
	public abstract void setJudeg(int judge);
}
