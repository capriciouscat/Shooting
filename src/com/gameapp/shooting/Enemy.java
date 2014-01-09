package com.gameapp.shooting;

// 敵のクラス
public class Enemy extends MovableObject{
	double vx, vy; // 速度
	int vieww, viewh; // 画面の幅と高さ
	private final int halfsizeofspaceship = 36; // 画像のサイズ 72x72の半分
	private final int halfsizeofenemy = 36; // 画像のサイズ 72x72の半分
	// コンストラクタ
	public Enemy(float initx, float inity, double vx, double vy, 
			int vieww, int viewh){
		x = initx; y = inity;
		this.vx = vx; this.vy = vy;
		this.vieww = vieww; this.viewh = viewh;
	}
	
	// 座標と速度初期化
	public void init(float initx, float inity, double vx, double vy){
		x = initx; y = inity;
		this.vx = vx; this.vy = vy;
	}
	
	// 移動メソッド
	public void move(){
		x = x + (float)vx; y = y + (float)vy;
		// 画面の外周で反転
		if(x > vieww - halfsizeofenemy || x < -halfsizeofenemy){
			vx = -vx;
		}
		if(y > viewh - halfsizeofspaceship * 6 || y < -halfsizeofenemy){
			vy = -vy;
		}
	}
}
