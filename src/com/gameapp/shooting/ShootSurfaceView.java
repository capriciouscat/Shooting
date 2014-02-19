package com.gameapp.shooting;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.MotionEvent;
import android.view.GestureDetector;

public class ShootSurfaceView extends SurfaceView implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener{
	private ShootSurfaceHolderCallback cb = null;
	private GestureDetector ges;
	
	public ShootSurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		SurfaceHolder holder = getHolder();
		cb = new ShootSurfaceHolderCallback();
		holder.addCallback(cb);
		ges = new GestureDetector(this);
	}
	
	@Override // SurfaceViewのサイズを取得
	protected void onSizeChanged(int w, int h, int oldw, int oldh){
		cb.vieww = w; cb.viewh = h-10;
	}
	
	@Override // タッチされた位置を取得
	public boolean onTouchEvent(MotionEvent event) {
		// TODO 自動生成されたメソッド・スタブ
				ges.onTouchEvent(event); 
				return true;
	}

	@Override
	public boolean onDoubleTap(MotionEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		float x = event.getX(); // 座標を取得
		float y = event.getY(); // 座標を取得
		int action = event.getAction(); // タッチの動作を取得
		cb.getTouchPosition(x, y, action, true);
		return false;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		float x = event.getX(); // 座標を取得
		float y = event.getY(); // 座標を取得
		int action = event.getAction(); // タッチの動作を取得
		cb.getTouchPosition(x, y, action, false);
		return false;
	}

	@Override
	public boolean onDown(MotionEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void onLongPress(MotionEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		float x = event.getX(); // 座標を取得
		float y = event.getY(); // 座標を取得
		int action = event.getAction(); // タッチの動作を取得
		cb.getTouchPosition(x, y, action, false);
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
	