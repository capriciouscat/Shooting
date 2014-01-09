package com.gameapp.shooting;

// ���@�N���X
public class Spaceship extends MovableObject{
	int life; // ���C�t
	private final float halfsizeofspaceship = 36;
	
	public Spaceship(int x, int y, int vieww){
		this.x = (float)x;
		this.y = (float)y;
		life = vieww; // SurfaceView�̉��������C�t�Ƃ��Ďg��
	}
	
	// �E�ړ�
	public float right(float touchx){
		float distance = touchx - x + halfsizeofspaceship;
		return x = x  + distance / 10;
	}
	// ���ړ�
	public float left(float touchx){
		float distance = x + halfsizeofspaceship - touchx;
		return x = x - distance / 10;
	}
}
