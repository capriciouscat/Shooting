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
	static Bitmap bulletimage;
	static Bitmap mybullet;
	static Bitmap comebullet;
	static Bitmap bullet1, bullet2;
	static Vibrator vibrator;
	
    /** Called when the activity is first created. */
    @Override // Activityのライフサイクルのメソッド。生成時に呼ばれる
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  
        // SurfaceViewを登録
        setContentView(new ShootSurfaceView(this));
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }
    
    @Override // Activityのライフサイクルのメソッド
    public void onResume(){
    	super.onResume();
    	// リソースから画像ファイルを読み込む
		Resources res = getResources(); // リソースの取得
		bgimage = BitmapFactory.decodeResource(res, R.drawable.earthrise);
		spaceship = BitmapFactory.decodeResource(res, R.drawable.spaceship);
		enemy = BitmapFactory.decodeResource(res, R.drawable.enemy);
		bulletimage = BitmapFactory.decodeResource(res, R.drawable.mybullet);
		mybullet = BitmapFactory.decodeResource(res, R.drawable.mybullet);
		comebullet = BitmapFactory.decodeResource(res, R.drawable.comebullet);
		bullet1 = BitmapFactory.decodeResource(res, R.drawable.bullet1);
		bullet2 = BitmapFactory.decodeResource(res, R.drawable.bullet2);
    }
    
    @Override  // Activityのライフサイクルのメソッド
    public void onPause(){
    	super.onPause();
    	finish(); // onDestroy()が呼ばれる
    }
       
    @Override // Activityのライフサイクルのメソッド。終了時に呼ぶ
	public void onDestroy() {
    	super.onDestroy();
    }
}