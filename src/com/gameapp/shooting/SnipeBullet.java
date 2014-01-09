package com.gameapp.shooting;

import java.lang.Math;

// �_�������e�N���X
public class SnipeBullet extends Bullet{
	private final float halfsizeofspaceship = 36;
	float spaceshipy;
	double ax = 0, ay = 0; // �����x
	int mode; // 0=�����A1=�z�[�~���O
	
	public SnipeBullet(float initx, float inity, float spaceshipx, float spaceshipy,
			int vieww, int viewh, int mode){
		x = initx;
		y = inity;
		this.spaceshipy = spaceshipy;
		this.mode = mode;
		
		if(mode == 0){ // ����
			double rad = Math.atan2(spaceshipy - y, spaceshipx - x);
			vx = 4 * Math.cos(rad);
			vy = 4 * Math.sin(rad);
		}else{ // �z�[�~���O
			vx = 0;
			vy = -5;
		}
		
		this.vieww = vieww;
		this.viewh = viewh;
	}
	
	//�@�ړ����\�b�h
	public void move(float spaceshipx){
		if(mode == 0){ // ����
			x = x + (float)vx;
			y = y + (float)vy;
			// ��ʂ̊O�ɏo����e������
			if(x > vieww || x < -9){
				isLive = false;
			}
			if(y > viewh || y < -9){
				isLive = false;
			}
		}else{ // �z�[�~���O
			double rad = Math.atan2(spaceshipy - y,
					spaceshipx + halfsizeofspaceship - x);
			ax = Math.cos(rad) / 12;
			vx = vx + ax;
			if(vy < 3){
				ay = Math.sin(rad) / 12;
				vy = vy + ay;
			}
			x = x + (float)vx;
			y = y + (float)vy;
			// ��ʂ̊O�ɏo����e�������B�z�[�~���O�̏ꍇ�A��ʊO�ł��}100�͏����Ȃ�
			if(x > vieww + 100 || x < -100){
				isLive = false;
			}
			if(y > viewh || y < -100){
				isLive = false;
			}
		}
	}
}
