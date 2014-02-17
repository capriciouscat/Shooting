package com.gameapp.shooting;

import java.lang.Math;

// 多方向弾クラス
public class NwayBullet extends Bullet{
	double dir; // 方向
	int mode; // 0=渦巻き状弾、1=ランダム弾
	
	public NwayBullet(float initx, float inity, int dir, 
			int vieww, int viewh, int mode){
		this.mode = mode;
		x = initx; y = inity;
		if(mode == 0){ // 渦巻き状弾
			this.vx = 2 * Math.cos(Math.PI*dir*10 / 180);
			this.vy = 2 * Math.sin(Math.PI*dir*10 / 180);			
		}else{ // ランダム弾
			this.vx = (5 - (-5) + 1) * Math.random() + (-5);
			if(Math.abs(this.vx) < 2){ // 遅すぎる場合は加算
				if(this.vx > 0)this.vx++; else this.vx--;
			}
			this.vy = (5 - (-5) + 1) * Math.random() + (-5);
			if(Math.abs(this.vy) < 2){ // 遅すぎる場合は加算
				if(this.vy > 0) this.vy++; else this.vy--;
			}
		}
		this.vieww = vieww; this.viewh = viewh;
	}
}
