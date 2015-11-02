package com.hanyu.car.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.hanyu.car.R;
import com.hanyu.car.ui.wheelView.AbstractWheelTextAdapter;
import com.hanyu.car.ui.wheelView.OnWheelChangedListener;
import com.hanyu.car.ui.wheelView.WheelView;
import com.hanyu.car.utils.ShowAgeDialog.OnsaveDayListener;

public class SelectionDialog {
	protected static final String tag = "SelectionDialog";
	private Display display;
	private Context context;
	private RelativeLayout dialog_sure;
	private WheelView wv_filter;
	private String filters[] = { "全部", "已预约", "使用中" };
	private String filter;
	private Dialog dialog;

	public SelectionDialog(Context context) {
		this.context = context;
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		display = windowManager.getDefaultDisplay();
	}

	public SelectionDialog builder() {
		View view = View.inflate(context, R.layout.dialog_item, null);
		wv_filter = (WheelView) view.findViewById(R.id.wv_filter);
		dialog_sure = (RelativeLayout) view.findViewById(R.id.dialog_sure);
		dialog_sure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(Selection!=null)
					Selection.setSelection(filter);
				dialog.cancel();
			}
		});
		wv_filter.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				filter = filters[wv_filter.getCurrentItem()];
			}
		});
		wv_filter.setCurrentItem(1);
		wv_filter.setViewAdapter(new SelectionAdapter(context, filters));
		dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(view);
		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
		dialogWindow.setLayout(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.x = 0;
		lp.y = 0;
		dialogWindow.setAttributes(lp);
		return this;
	}
	
	public void show(){
		dialog.show();
	}

	private class SelectionAdapter extends AbstractWheelTextAdapter {
		private String selection[];

		protected SelectionAdapter(Context context, String selection[]) {
			super(context, R.layout.wheelcity_age_layout, NO_RESOURCE);
			setItemTextResource(R.id.tvAge);
			this.selection = selection;
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			return view;
		}

		@Override
		public int getItemsCount() {
			return selection.length;
		}

		@Override
		protected CharSequence getItemText(int index) {
			return selection[index];
		}

	}
	
	private SelectionListener Selection;
	public interface SelectionListener{
		public void setSelection(String Selection);
	}

	public SelectionListener getSelection() {
		return Selection;
	}

	public void setSelection(SelectionListener Selection) {
		this.Selection = Selection;
	}
}
