package com.gameapp.shooting;

// 自機クラス
public class Spaceship extends MovableObject{
	int life; // ライフ
	private final float halfsizeofspaceship = 36;
	
	public Spaceship(int x, int y, int vieww){
		this.x = (float)x;
		this.y = (float)y;
		life = vieww; // SurfaceViewの横幅をライフとして扱う
	}
	
	// 右移動
	public float right(float touchx){
		float distance = touchx - x + halfsizeofspaceship;
		return x = x  + distance / 10;
	}
	// 左移動
	public float left(float touchx){
		float distance = x + halfsizeofspaceship - touchx;
		return x = x - distance / 10;
	}
}
