package com.gameapp.shooting;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.MotionEvent;

public class ShootSurfaceView extends SurfaceView{
	private ShootSurfaceHolderCallback cb = null;
	
	public ShootSurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		SurfaceHolder holder = getHolder();
		cb = new ShootSurfaceHolderCallback();
		holder.addCallback(cb);
	}
	
	@Override // SurfaceView�̃T�C�Y���擾
	protected void onSizeChanged(int w, int h, int oldw, int oldh){
		cb.vieww = w; cb.viewh = h;
	}
	
	@Override // �^�b�`���ꂽ�ʒu���擾
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX(); // ���W���擾
		//float y = event.getY(); // ���W���擾
		int action = event.getAction(); // �^�b�`�̓�����擾
		cb.getTouchPosition(x, action);
		return true;
	}
}
