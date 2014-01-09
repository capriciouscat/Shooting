package com.gameapp.shooting;

import java.lang.Math;

// �������e�N���X
public class NwayBullet extends Bullet{
	double dir; // ����
	int mode; // 0=�Q������e�A1=�����_���e
	
	public NwayBullet(float initx, float inity, int dir, 
			int vieww, int viewh, int mode){
		this.mode = mode;
		x = initx; y = inity;
		if(mode == 0){ // �Q������e
			this.vx = 2 * Math.cos(Math.PI*dir*10 / 180);
			this.vy = 2 * Math.sin(Math.PI*dir*10 / 180);			
		}else{ // �����_���e
			this.vx = (5 - (-5) + 1) * Math.random() + (-5);
			if(Math.abs(this.vx) < 2){ // �x������ꍇ�͉��Z
				if(this.vx > 0)this.vx++; else this.vx--;
			}
			this.vy = (5 - (-5) + 1) * Math.random() + (-5);
			if(Math.abs(this.vy) < 2){ // �x������ꍇ�͉��Z
				if(this.vy > 0) this.vy++; else this.vy--;
			}
		}
		this.vieww = vieww; this.viewh = viewh;
	}
}
