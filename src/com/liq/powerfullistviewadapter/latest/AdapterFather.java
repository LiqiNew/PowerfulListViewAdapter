package com.liq.powerfullistviewadapter.latest;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 *  所有ListViewItem通用BaseAdapter适配器的基类
 */
public class AdapterFather<T extends AdapterDataJudge> extends BaseAdapter {
	protected List<T> data;// 数据集合
	protected Context context;
	protected ArrayList<AdapterItemLayoutData> layoutList;// 此集合是用来装layout布局ID和布局标识
	private AdapterAgencyInterface<T> agencyInterface;

	/**
	 * 
	 * @param data
	 *            要展示的数据集合
	 * @param layouID
	 *            item布局ID集合
	 * @param context
	 *            上下文
	 * @param agencyInterface
	 *            getView操作回调接口
	 */
	public AdapterFather(List<T> data,
			ArrayList<AdapterItemLayoutData> layoutList, Context context,
			AdapterAgencyInterface<T> agencyInterface) throws Exception {
		this.data = data;
		this.context = context;
		if (agencyInterface != null && layoutList != null) {
			if (!layoutList.isEmpty()) {
				this.agencyInterface = agencyInterface;
				this.layoutList = layoutList;
			} else {
				System.out.println("万能适配-->布局集合没有数据");
				throw new NullPointerException("万能适配-->布局集合没有数据");
			}
		} else {
			System.out.println("万能适配-->接口为空或者布局集合为空");
			throw new NullPointerException("万能适配-->接口为空或者布局集合为空");
		}
	}

	@Override
	public int getCount() {
		return data == null ? 0 : data.size();
	}

	@Override
	public Object getItem(int position) {
		return data == null ? 0 : data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * 获取当前展示几个布局
	 */
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return layoutList == null ? 0 : layoutList.size();
	}

	/**
	 * 获取当前要展示的类型(类型必须返回的类型必须是有顺序的从0开始的升序（0，1，2，3），且不能大于要展示布局的长度)
	 */
	@Override
	public int getItemViewType(int position) {
		// 获取要展示的布局总长度
		int size = layoutList.size() - 1;
		T t = data.get(position);
		// 如果传过来的标识大于要展示的布局数量，默认返回要展示布局的总长度-1.因为是从0返回的
		if (t.onJudge() > size) {
			// 设置此对象的标识为要展示布局总长度
			t.setJudeg(size);
			System.out.println("万能适配-->设置的数据标识大于要展示的布局总长度");
			return size;
		}
		return data.get(position).onJudge();
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		T be = data.get(position);
		View view = null;
		AdapterInnerClass snf = null;
		int judgeType = getItemViewType(position);
		if (convertView == null) {
			tag: for (int i = 0; i < layoutList.size(); i++) {
				AdapterItemLayoutData layoutData = layoutList.get(i);
				int tagLayout = layoutData.getLayouTag();
				if (judgeType == tagLayout) {
					int layoutId = layoutData.getLayoutId();
					view = View.inflate(context, layoutId, null);
					snf = new AdapterInnerClass();
					pollWidget(view, snf);
					view.setTag(view.getId(), snf);
					break tag;
				}
			}
		} else {
			view = convertView;
			snf = (AdapterInnerClass) view.getTag(view.getId());
		}
		// 确定优化内部类不会报错才进
		if (snf != null) {
			agencyInterface.setViewData(snf, be);
		}
		return view;
	}

	/**
	 * 轮询获取布局里面的控件
	 * 
	 * @param view
	 * @param snf
	 */
	public void pollWidget(View view, AdapterInnerClass snf) {
		ViewGroup viewGroup = (ViewGroup) view;
		int childCount = viewGroup.getChildCount();
		for (int j = 0; j < childCount; j++) {
			View childAt = viewGroup.getChildAt(j);
			// 布局轮询获取控件
			if (childAt instanceof LinearLayout
					|| childAt instanceof RelativeLayout) {
				pollWidget(childAt, snf);
			} else
				snf.obj.put(childAt.getId(), childAt);
		}
	}

	public class AdapterInnerClass {
		public final SparseArray<Object> obj = new SparseArray<Object>();
	}

}
