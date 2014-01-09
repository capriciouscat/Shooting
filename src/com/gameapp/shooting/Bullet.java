package com.gameapp.shooting;

// 弾の抽象クラス
public abstract class Bullet {
	float x, y; // 位置
	double vx, vy; // 速度
	int vieww, viewh; // 画面の幅と高さ
	boolean isLive = true; // 弾の生死フラグ
	
	//　移動メソッド
	public void move(){
		x = x + (float)vx;
		y = y + (float)vy;
		// 画面の外に出たら弾を消す。9は弾のサイズ
		if(x > vieww || x < -9){
			isLive = false;
		}
		if(y > viewh || y < -9){
			isLive = false;
		}
	}
}