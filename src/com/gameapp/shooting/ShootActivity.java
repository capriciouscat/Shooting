package com.gameapp.shooting;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Vibrator;

public class ShootActivity extends Activity {
	static Bitmap bgimage;
	static Bitmap spaceship;
	static Bitmap enemy;
	static Bitmap mybullet;
	static Bitmap bullet1, bullet2;
	static Vibrator vibrator;
	
    /** Called when the activity is first created. */
    @Override // Activity�̃��C�t�T�C�N���̃��\�b�h�B�������ɌĂ΂��
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  
        // SurfaceView��o�^
        setContentView(new ShootSurfaceView(this));
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }
    
    @Override // Activity�̃��C�t�T�C�N���̃��\�b�h
    public void onResume(){
    	super.onResume();
    	// ���\�[�X����摜�t�@�C����ǂݍ���
		Resources res = getResources(); // ���\�[�X�̎擾
		bgimage = BitmapFactory.decodeResource(res, R.drawable.earthrise);
		spaceship = BitmapFactory.decodeResource(res, R.drawable.spaceship);
		enemy = BitmapFactory.decodeResource(res, R.drawable.enemy);
		mybullet = BitmapFactory.decodeResource(res, R.drawable.mybullet);
		bullet1 = BitmapFactory.decodeResource(res, R.drawable.bullet1);
		bullet2 = BitmapFactory.decodeResource(res, R.drawable.bullet2);
    }
    
    @Override  // Activity�̃��C�t�T�C�N���̃��\�b�h
    public void onPause(){
    	super.onPause();
    	finish(); // onDestroy()���Ă΂��
    }
       
    @Override // Activity�̃��C�t�T�C�N���̃��\�b�h�B�I�����ɌĂ�
	public void onDestroy() {
    	super.onDestroy();
    }
}