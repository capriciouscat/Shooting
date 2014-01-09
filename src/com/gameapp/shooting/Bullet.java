package com.gameapp.shooting;

// �e�̒��ۃN���X
public abstract class Bullet {
	float x, y; // �ʒu
	double vx, vy; // ���x
	int vieww, viewh; // ��ʂ̕��ƍ���
	boolean isLive = true; // �e�̐����t���O
	
	//�@�ړ����\�b�h
	public void move(){
		x = x + (float)vx;
		y = y + (float)vy;
		// ��ʂ̊O�ɏo����e�������B9�͒e�̃T�C�Y
		if(x > vieww || x < -9){
			isLive = false;
		}
		if(y > viewh || y < -9){
			isLive = false;
		}
	}
}