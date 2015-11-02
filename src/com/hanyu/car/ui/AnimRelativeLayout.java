package com.hanyu.car.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

/**
 * 带动画的点击RelativeLayout
 * 
 * @author 
 * 
 */
public class AnimRelativeLayout extends RelativeLayout {
	/**
	 * 手指按下的动画
	 */
	private Animation animDown;
	/**
	 * 手指离开的动画
	 */
	private Animation animUp;
	/**
	 * 监听器
	 */
	private AnimClickLisner animClickLisner;
	/**
	 * 判断
	 */
	private boolean flag = true;

	public AnimRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAnim();
	}

	private void initAnim() {
		animDown = new ScaleAnimation(1.0f, 0.96f, 1.0f, 0.96f, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		animDown.setDuration(100);
		animDown.setFillAfter(true);

		animUp = new ScaleAnimation(0.96f, 1.0f, 0.96f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		animUp.setDuration(100);
		animUp.setFillAfter(true);

	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// 还原
			startAnimation(animDown);
			flag = true;
			break;
		case MotionEvent.ACTION_MOVE:
			float x = event.getX();
			float y = event.getY();
			if (x < 0 || y < 0 || x > getWidth() || y > getHeight()) {
				// 手指超出范围
				if (flag) {
					startAnimation(animUp);
					flag = false;
				}

			} else {
				// 手指没有超出范围
				if (!flag) {
					startAnimation(animDown);
					flag = true;
				}

			}
			break;

		case MotionEvent.ACTION_UP:
			if (flag) {
				startAnimation(animUp);
			}
			float xx = event.getX();
			float yy = event.getY();
			// 在控件区域内出发点击事件
			if (animClickLisner != null && xx > 0 && yy > 0 && xx < getWidth() && yy < getHeight() && flag) {
				animClickLisner.animClick(this);
			}

			break;

		default:
			break;
		}
		return true;
	}

	public AnimClickLisner getAnimClickLisner() {
		return animClickLisner;
	}

	public void setAnimClickLisner(AnimClickLisner animClickLisner) {
		this.animClickLisner = animClickLisner;
	}

	/**
	 * 点击回调接口
	 * 
	 * @author
	 */
	public interface AnimClickLisner {
		public void animClick(AnimRelativeLayout animRelativeLayout);
	}

}
