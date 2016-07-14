package com.liq.powerfullistviewadapter.activity;

import java.util.ArrayList;
import java.util.List;

import com.liq.powerfullistviewadapter.R;
import com.liq.powerfullistviewadapter.latest.AdapterAgencyInterface;
import com.liq.powerfullistviewadapter.latest.AdapterFather;
import com.liq.powerfullistviewadapter.latest.AdapterFather.AdapterInnerClass;
import com.liq.powerfullistviewadapter.latest.AdapterItemLayoutData;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 如遇到有什么不懂或者有需要优化改进的,请QQ联系我。0_0
 * 
 * @author LiQi
 * @QQ:543945827
 * 
 */
public class LatestMainActivity extends Activity {
	private ListView list_view;
	// 此处定义的布局标识，从0开始自然升序
	private final int TEXT = 0x0, IMAGER = 0x1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		list_view = (ListView) findViewById(R.id.list_view);
		// 适配器里面的适配器数据（数据类型数量不能多于布局展示集合的数量）
		ArrayList<TestData> dataList = new ArrayList<TestData>();
		for (int i = 0; i < 50; i++) {
			TestData data = new TestData();
			if (i % 1 == 0) {
				data.imageID = R.drawable.ic_launcher;
				data.text = "多布局图片图片图片适配器>>>" + i;
				//必须得和你此数据要展示布局集合里面的layoutag一致
				data.judgeWhat = IMAGER;
			}
			if (i % 5 == 0) {
				data.text = "第三三三三三三个>>>" + i;
				//必须得和你此数据要展示布局集合里面的layoutag一致
				data.judgeWhat = TEXT;
			}
			//此数据是用来做数据标识大于要展示布局总长度错误测试
//			if (i % 2 == 0) {
//				data.text = "第三三三三三三个>>测试异常>" + i;
//				//必须得和你要展示布局集合里面的layoutag一致
//				data.judgeWhat = 2;
//			}
			dataList.add(data);
		}
		// 适配器里面的展示布局数据集合
		ArrayList<AdapterItemLayoutData> layoutList = new ArrayList<AdapterItemLayoutData>();

		AdapterItemLayoutData listItemOne = new AdapterItemLayoutData();
		// item布局
		listItemOne.setLayoutId(R.layout.adapter_list_item);
		//此布局标识
		listItemOne.setLayouTag(TEXT);
		// 把当前这个存储item布局信息的对象添加进去list里面
		layoutList.add(listItemOne);

		AdapterItemLayoutData listItemTwo = new AdapterItemLayoutData();
		//item布局
		listItemTwo.setLayoutId(R.layout.adapter_list_item_two);
		//此布局标识
		listItemTwo.setLayouTag(IMAGER);
		layoutList.add(listItemTwo);

		try {
			// 本人测试，用的匿名类测试
			AdapterFather<TestData> adapter = new AdapterFather<TestData>(
					dataList, layoutList, this,
					new AdapterAgencyInterface<TestData>() {

						@SuppressWarnings("rawtypes")
						@Override
						public void setViewData(
								AdapterInnerClass adapterInnerClass, TestData t) {
							//获取标识
							int judge = t.onJudge();
							//判断标识
							switch (judge) {
							case TEXT:
								//根据缓存对象 adapterInnerClass中的obj对象去获取相应的控件，通过你设置的布局ID去获取控件
								TextView textview_id_one = (TextView) adapterInnerClass.obj
										.get(R.id.textview_id_one);
								textview_id_one.setText(t.text);
								TextView textview_id_two = (TextView) adapterInnerClass.obj
										.get(R.id.textview_id_two);
								textview_id_two.setText(t.text);
								break;
							case IMAGER:
								TextView textview = (TextView) adapterInnerClass.obj
										.get(R.id.textview);
								textview.setText(t.text);
								ImageView iamger = (ImageView) adapterInnerClass.obj
										.get(R.id.iamger);
								iamger.setImageResource(t.imageID);
								TextView textview_to = (TextView) adapterInnerClass.obj
										.get(R.id.textview_to);
								textview_to.setText(t.text);
								ImageView iamger_to = (ImageView) adapterInnerClass.obj
										.get(R.id.iamger_to);
								iamger_to.setImageResource(t.imageID);
								break;
							}
						}

					});
			list_view.setAdapter(adapter);
		} catch (Exception e) {
			
		}
	}
}
