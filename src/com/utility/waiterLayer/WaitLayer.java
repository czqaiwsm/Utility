package com.utility.waiterLayer;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.utility.R;

public class WaitLayer {
	private TextView tipTextView;
	private  Dialog loadingDialog;
	private Animation hyperspaceJumpAnimation;
	private ImageView spaceshipImage;
	private ImageView logoImage;

	public static WaitLayer getWaitLayer(Context context){
		return  new WaitLayer(context);
	}

	public WaitLayer(Context context) {
		creatDialog(context, true, false);
	}

	public WaitLayer(Context context, boolean isCancel, boolean isRight) {
		creatDialog(context, isCancel, isRight);
	}

	private void creatDialog(Context context, boolean isCancel, boolean isRight) {
		LinearLayout.LayoutParams wrap_content = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		RelativeLayout.LayoutParams wrap_content0 = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		RelativeLayout.LayoutParams wrap_content1 = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		LinearLayout main = new LinearLayout(context);
		RelativeLayout picMian =new RelativeLayout(context);

		if (isRight) {
			main.setOrientation(LinearLayout.HORIZONTAL);
			wrap_content.setMargins(10, 0, 35, 0);
			wrap_content0.setMargins(35, 25, 0, 25);
		} else {
			main.setOrientation(LinearLayout.VERTICAL);
			wrap_content.setMargins(10, 5, 10, 15);
			wrap_content0.setMargins(35, 25, 35, 0);
		}
		wrap_content1.setMargins(35, 25, 35, 0);

		main.setGravity(Gravity.CENTER);
		spaceshipImage = new ImageView(context);
//		spaceshipImage.setImageResource(NewsURL.getInstance().getProgressImg());
        Integer id = -1;
        if(id>0){
            spaceshipImage.setImageResource(id);
        }else{
            spaceshipImage.setImageResource(R.drawable.ym_progress);
        }
		logoImage = new ImageView(context);
		logoImage.setImageResource(R.drawable.ym_progress_icon);
		tipTextView = new TextView(context);
		tipTextView.setText("正在为您加载...");

		// 加载旋转动画
		hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context,
				R.anim.loading_animation);
		// 使用ImageView显示动画
		spaceshipImage.startAnimation(hyperspaceJumpAnimation);
		loadingDialog = null;
		loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
		// loadingDialog.setOwnerActivity((Activity) context);
		loadingDialog.setCancelable(isCancel);// 是否可以用返回键取消
		loadingDialog.setCanceledOnTouchOutside(false);
		picMian.addView(spaceshipImage, wrap_content0);
		picMian.addView(logoImage, wrap_content1);
		main.addView(picMian, wrap_content);
		main.addView(tipTextView, wrap_content);
		LinearLayout.LayoutParams fill_parent = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		loadingDialog.addContentView(main, fill_parent);// 设置布局
	}

	/**
	 * 显示等待层
	 *
	 */
	public void show() {
		if (loadingDialog.getWindow() != null
				&& !loadingDialog.getWindow().isActive()) {
			spaceshipImage.startAnimation(hyperspaceJumpAnimation);
			loadingDialog.show();
		}
	}

	/**
	 * 显示等待层
	 *
	 * @param msg
	 */
	public void show(String msg) {
		if (loadingDialog.getWindow() != null
				&& !loadingDialog.getWindow().isActive()) {
			if (msg != null) {
				tipTextView.setText(msg);// 设置加载信息,否则加载默认值
			}else {
				tipTextView.setText("");
			}
			spaceshipImage.startAnimation(hyperspaceJumpAnimation);
			loadingDialog.show();
		}
	}

	/**
	 * 关闭等待层
	 */
	public void close() {
		if (loadingDialog != null && loadingDialog.isShowing()) {
			loadingDialog.dismiss();
			loadingDialog = null;
		}
		hyperspaceJumpAnimation.cancel();
	}

}



