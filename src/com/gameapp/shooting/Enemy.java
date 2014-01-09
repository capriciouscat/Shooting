package com.gameapp.shooting;

// �G�̃N���X
public class Enemy extends MovableObject{
	double vx, vy; // ���x
	int vieww, viewh; // ��ʂ̕��ƍ���
	private final int halfsizeofspaceship = 36; // �摜�̃T�C�Y 72x72�̔���
	private final int halfsizeofenemy = 36; // �摜�̃T�C�Y 72x72�̔���
	// �R���X�g���N�^
	public Enemy(float initx, float inity, double vx, double vy, 
			int vieww, int viewh){
		x = initx; y = inity;
		this.vx = vx; this.vy = vy;
		this.vieww = vieww; this.viewh = viewh;
	}
	
	// ���W�Ƒ��x������
	public void init(float initx, float inity, double vx, double vy){
		x = initx; y = inity;
		this.vx = vx; this.vy = vy;
	}
	
	// �ړ����\�b�h
	public void move(){
		x = x + (float)vx; y = y + (float)vy;
		// ��ʂ̊O���Ŕ��]
		if(x > vieww - halfsizeofenemy || x < -halfsizeofenemy){
			vx = -vx;
		}
		if(y > viewh - halfsizeofspaceship * 6 || y < -halfsizeofenemy){
			vy = -vy;
		}
	}
}
