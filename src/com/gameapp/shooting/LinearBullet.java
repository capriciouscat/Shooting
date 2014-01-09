package com.gameapp.shooting;

// 直線弾クラス
public class LinearBullet extends Bullet{
	
	public LinearBullet(float spaceshipx, float spaceshipy, int vieww, int viewh){
		x = spaceshipx;
		y = spaceshipy;
		vx = 0;
		vy = -5;	
		this.vieww = vieww; 
		this.viewh = viewh;
	}
}
