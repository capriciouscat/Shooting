package com.gameapp.shooting;

// 当り判定クラス
public class CollisionDetect {
	private final int sizeofenemy = 72; // 画像のサイズ 72x72
	private final int sizeofspaceship = 72; // 画像のサイズ 72x72
	private final int sizeofmybullet = 16; // 画像のサイズ 16x16
	private final int halfsizeofbullet = 4; // 画像のサイズ 9x9
	
	public CollisionDetect(){
		
	}
	
	public boolean test(LinearBullet lb, Enemy e){
		if(		(lb.x-sizeofmybullet/2 < e.x + sizeofenemy) &&
				(lb.x-sizeofmybullet/2 + sizeofmybullet > e.x) &&
				(lb.y < e.y + sizeofenemy)&&
				(lb.y + sizeofmybullet > e.y)
		){
			return true;
		}
		return false;
	}
	
	// 自機の当り判定は一回り～二回り小さく ここでは-10小さくしている
	public boolean test(Bullet b, Spaceship s){
		if(		(b.x-halfsizeofbullet < s.x + sizeofspaceship -10) &&
				(b.x-halfsizeofbullet + halfsizeofbullet*2 > s.x+10) &&
				(b.y < s.y + sizeofspaceship-10)&&
				(b.y + halfsizeofbullet*2 > s.y+10)
		){
			return true;
		}
		return false;
	}
}
