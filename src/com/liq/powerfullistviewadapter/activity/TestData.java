package com.liq.powerfullistviewadapter.activity;

import com.liq.powerfullistviewadapter.latest.AdapterDataJudge;


//测试对象
public class TestData extends AdapterDataJudge {
	public String text;
	public int imageID;
	/**
	 * 用来判断是那个布局 存储要展示布局在布局集合里面位置的索引
	 */
	public int judgeWhat=0;

	@Override
	public int onJudge() {
		return judgeWhat;
	}

	@Override
	public void setJudeg(int judge) {
		this.judgeWhat=judge;
	}

}
