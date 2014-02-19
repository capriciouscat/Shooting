package com.gameapp.shooting;

// 直線弾クラス
public class LinearBullet extends Bullet{
	
	public LinearBullet(int vieww, int viewh ){
		isLive = false;
		vx = 0;
		vy = -7;	
		this.vieww = vieww; 
		this.viewh = viewh;
	}
	
	//移動メソッド
		public void move(Spaceship spaceship){
			x = x + (float)vx;
			y = y + (float)vy;
			// 画面の外に出たら弾を消す。9は弾のサイズ
			if(x > vieww || x < -9){
				isLive = false;
				spaceship.touched = false;
			}
			if(y > viewh || y < -9){
				isLive = false;
				spaceship.touched =false;
			}
		}
}
