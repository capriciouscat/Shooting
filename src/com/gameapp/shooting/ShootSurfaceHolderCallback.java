package com.gameapp.shooting;

import java.util.Iterator;
import java.util.LinkedList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.view.SurfaceHolder;

public class ShootSurfaceHolderCallback implements SurfaceHolder.Callback, Runnable {
	private SurfaceHolder holder = null;
	private Thread thread = null;
	private boolean isThreadrunning = true;
	private float touchx; // �^�b�`���ꂽx���W
	int vieww, viewh; // SurfaveView�̕��ƍ���
	private int dir = 0; // �Q������e�̕��p�����[�^
	private long t1 = 0, t2 = 0; // �X���[�v�p
	private int interval = 0; // �e���˂̊Ԋu�����p
	private boolean isHit = false;
	Spaceship spaceship;
	Enemy enemy1, enemy2;
	private final int halfsizeofspaceship = 36; // �摜�̃T�C�Y 72x72
	private final int halfsizeofenemy = 36; // �摜�̃T�C�Y 72x72
	private final int halfsizeofmybullet = 8; // �摜�̃T�C�Y 16x16
	private final int sizeofbullet = 9; // �摜�T�C�Y 9��9
	private LinkedList<NwayBullet> bullet1;
	private LinkedList<SnipeBullet> bullet2;
	private LinearBullet mybullet;
	private CollisionDetect cd;
	private Paint paintlife, painthit;
	private int nwaybulletmode = 0, snipebulletmode = 0;
	private long framerate=0, framecount=0, time1forfps=0, time2forfps=0; // FPS�p
	private int gameovercount = 0;
	private int touchaction;
	private int bgimagex = 0;
	private boolean isShake = false;
	// ����`�̃N���X public Rect (int left, int top, int right, int bottom)
	private Rect recthit;
	
	// �R���X�g���N�^
	public ShootSurfaceHolderCallback(){
		bullet1 = new LinkedList<NwayBullet>();
		bullet2 = new LinkedList<SnipeBullet>();
		cd = new CollisionDetect();
		paintlife = new Paint();
		paintlife.setStyle(Style.FILL);
		paintlife.setARGB(100, 0, 255, 0);
		painthit = new Paint();
		painthit.setStyle(Style.FILL);
		painthit.setARGB(100, 255, 0, 0);
		recthit = new Rect();
	}
	
	// �^�b�`���ꂽ�ʒu���擾
	public void getTouchPosition(float x, int action){
		touchx = x;
		touchaction = action; // �Q�[���̍ĊJ�̏����̂���
	}
	
	// �R�[���o�b�N�֐�
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
	}

	// �R�[���o�b�N�֐�
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		// �e��̏����B�X���b�h�̊J�n�O��
		this.holder = holder;
		// �L�����N�^�𐶐�
		spaceship = new Spaceship(vieww/2, viewh - halfsizeofspaceship*2, vieww);
		enemy1 = new Enemy((float)Math.random() * vieww/2, (float)Math.random() * vieww / 2,
				Math.random() * 2, Math.random() * 2, vieww, viewh);
		enemy2 = new Enemy((float)Math.random() * vieww/2, (float)Math.random() * vieww / 2,
				Math.random() * 2, Math.random() * 2, vieww, viewh);
		mybullet = new LinearBullet(spaceship.x, spaceship.y, vieww, viewh);
		// �w�i��SurfaceView�̃T�C�Y�ɕύX
		ShootActivity.bgimage = Bitmap.createScaledBitmap(ShootActivity.bgimage,
				vieww, viewh, true);
		// ����`�̏���
		recthit.top = 0; recthit.bottom = viewh;
		
		// �X���b�h�̊J�n		
		thread = new Thread(this);
		thread.start();	
	}

	// �R�[���o�b�N�֐�
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		// �X���b�h�̏I��
		boolean retry = true;
		synchronized (this.holder) {
			isThreadrunning = false;
		}
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}
		thread = null;	
	}

	public void run() {
		// TODO Auto-generated method stub
		// �Q�[���̃��C�����[�v
		while (isThreadrunning) {
			synchronized (holder) {
				t1 = System.currentTimeMillis();
				// ���@�ړ�
				if(spaceship.x + halfsizeofspaceship < touchx){
					spaceship.right(touchx);
				}else{
					spaceship.left(touchx);
				}
				
				// ���e�ړ�
				if(mybullet.isLive == true && spaceship.life > 0){
					mybullet.move();
				}else{
					mybullet.x = spaceship.x + halfsizeofspaceship;
					mybullet.y = spaceship.y;
					mybullet.isLive = true;
				}
							
				enemy1.move(); // �G�@�ړ�
				enemy2.move();
				
				// �G�e����
				interval++;
				interval = interval & 65535;
				if(interval % 2 == 0){
					NwayBullet nb = new NwayBullet(enemy1.x + halfsizeofenemy, 
						enemy1.y + halfsizeofenemy, dir, vieww, viewh, 
						nwaybulletmode);
					dir = ++dir & 255; // �O�̂���256��0�N���A
					bullet1.add(nb);
				}
				if(interval % 4 == 0){
					SnipeBullet sb = new SnipeBullet(enemy2.x + halfsizeofenemy, 
						enemy2.y + halfsizeofenemy,
						spaceship.x + halfsizeofspaceship, 
						spaceship.y, vieww, viewh, snipebulletmode);
					bullet2.add(sb);
				}
				// �G�e�ړ��Ɠ��蔻��
				for(Iterator<NwayBullet> it = bullet1.iterator(); it.hasNext();){
					NwayBullet nwaybullet = (NwayBullet)it.next();
					nwaybullet.move();
					// ���蔻��
					if(nwaybullet.y > viewh - halfsizeofspaceship*2 - sizeofbullet){
						if(cd.test(nwaybullet, spaceship)){
							spaceship.life--;
							isHit = true;
						}
					}
					if(nwaybullet.isLive == false){
						it.remove();
					}
				}
				
				for(Iterator<SnipeBullet> it = bullet2.iterator(); it.hasNext();){
					SnipeBullet snipebullet = (SnipeBullet)it.next();
					snipebullet.move(spaceship.x);
					// ���蔻��
					if(snipebullet.y > viewh - halfsizeofspaceship*2 - sizeofbullet){
						if(cd.test(snipebullet, spaceship)){
							spaceship.life--;
							isHit = true;
						}
					}
					if(snipebullet.isLive == false){
						it.remove();
					}
				}
			}

			// ���e�ƓG�̓��蔻��
			if(cd.test(mybullet, enemy1)){
				nwaybulletmode = 1 - nwaybulletmode;
				bullet1.removeAll(bullet1);
				mybullet.x = spaceship.x + halfsizeofspaceship;
				mybullet.y = spaceship.y;
				spaceship.life = spaceship.life + 5;
			}
			if(cd.test(mybullet, enemy2)){
				snipebulletmode = 1 - snipebulletmode;
				bullet2.removeAll(bullet2);
				mybullet.x = spaceship.x + halfsizeofspaceship;
				mybullet.y = spaceship.y;
				spaceship.life = spaceship.life + 5;
			}
			if(spaceship.life > vieww){ // ���@���C�t�̏���ݒ�
				spaceship.life = vieww;
			}
			
			// �`��̏���
			Canvas canvas = holder.lockCanvas(); // ���b�N��������
				drawOnCanvas(canvas);
			holder.unlockCanvasAndPost(canvas); // ���b�N������
			
			// �X���[�v
			t2 = System.currentTimeMillis();
			if(t2 - t1 < 16){ // 1000 / 60 = 16.6666
				try {
					Thread.sleep(16 - (t2 - t1));
				} catch (InterruptedException e) {
				}
			}
			
			// Game Over �� �ĊJ
			if(spaceship.life < 0){	
				gameovercount++;
				if(gameovercount > 60*4){ // 4�b���炢�҂�
					if(touchaction == android.view.MotionEvent.ACTION_DOWN && 
							touchx > 1 && touchx < vieww){
						spaceship.life = vieww;
						touchaction = 0;
						gameovercount = 0;
						// �G�e�������A�G�@�̍��W�Ƒ��x����
						bullet1.removeAll(bullet1);
						bullet2.removeAll(bullet2);
						enemy1.init((float)Math.random() * vieww/2,
								(float)Math.random() * vieww/2,
								Math.random() * 2, Math.random() * 2);
						enemy2.init((float)Math.random() * vieww/2,
								(float)Math.random() * vieww/2,
								Math.random() * 2, Math.random() * 2);
					}
				}
			}
			
			// FPS����
			time1forfps = System.currentTimeMillis();
			if(time1forfps - time2forfps > 1000){
				framerate = framecount;
				framecount = 0;
				time2forfps = time1forfps;
			}
			framecount++;
		}	
	}
	
	// �`�惁�\�b�h
	public void drawOnCanvas(Canvas canvas){
		// �w�i�`��
		if(isShake){
			switch (bgimagex){
			case 0:
				bgimagex = 5;
				ShootActivity.vibrator.vibrate(16*3); // ���@��U��
				break;
			case 5:
				bgimagex = -5;
				break;
			case -5:
				bgimagex = 0;
				isShake = false;
			}
		}
		canvas.drawBitmap(ShootActivity.bgimage, bgimagex, 0, null);
		
		// �G�@�`��
		canvas.drawBitmap(ShootActivity.enemy, 
				enemy1.x, enemy1.y, null);
		canvas.drawBitmap(ShootActivity.enemy, 
				enemy2.x, enemy2.y, null);
		
		// �G�e�`��
		for(NwayBullet bullet: bullet1){
			canvas.drawBitmap(ShootActivity.bullet1, bullet.x, bullet.y, null);
		}
		for(SnipeBullet bullet: bullet2){
			canvas.drawBitmap(ShootActivity.bullet2, bullet.x, bullet.y, null);
		}		
		
		if(spaceship.life > 0){
			// ���@�`��
			canvas.drawBitmap(ShootActivity.spaceship, 
				spaceship.x, spaceship.y, null);
			// ���e�`��
			canvas.drawBitmap(ShootActivity.mybullet, 
				mybullet.x-halfsizeofmybullet, mybullet.y, null);
			
			// ���C�t�Q�[�W�`��
			canvas.drawRect(0, 5, spaceship.life, 20, paintlife);

			// hit���̏���
			if(isHit){
				recthit.left = bgimagex;
				recthit.right = bgimagex + vieww;
				canvas.drawRect(recthit, painthit);
				isHit = false;
				if(isShake==false)isShake=true;
			}
		}else{
			// Game Over�`��
			isShake = false;
			bgimagex = 0;
			Paint gameover = new Paint();
			gameover.setARGB(255, 255, 0, 0);
			gameover.setTextSize(16);	
			canvas.drawText("GAME OVER", vieww/2 - 43, viewh/2 - 10, gameover);
		}
		    
		// FPS�\��
		canvas.drawText("FPS : " + String.valueOf(framerate), 1, 40, painthit);
	}
}

