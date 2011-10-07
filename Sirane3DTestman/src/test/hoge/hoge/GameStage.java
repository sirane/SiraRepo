/*
 * ゲームステージの基本クラス。
 *
 * EnemyManagerやPlayerManagerを保持し、ループをまわす。
 * これをViewは保持して、描画を担う。
 *
 *
 */

package test.hoge.hoge;

import java.util.Iterator;

import javax.microedition.khronos.opengles.GL10;

import android.view.MotionEvent;

import Sirane3DCollisionManager.GameCollisionManager;
import SiraneGL10Manager.*;

public class GameStage implements GameUIWrapper_Interface{
	public GameCollisionManager GCM;
	public GameEnemyManager GEMNG;
	public GamePlayerManager GPMNG;
	public GLTemplateShapeManager GLTSMNG;
	int ShapeID_Cube = 0;

	public GameStage(){
		GCM = new GameCollisionManager();
		GLTSMNG = new GLTemplateShapeManager();
		GEMNG = new GameEnemyManager(GLTSMNG);
		GPMNG = new GamePlayerManager();

		//てすてす。キューブ作り。
		ShapeID_Cube = GLTSMNG.AddShape(new GLTShape_Cube());
	}

	public boolean Setup(){
		GEMNG.AddEnemy(new GameEnemy(GEMNG,ShapeID_Cube));
		GEMNG.AddEnemy(new GameEnemy(GEMNG,ShapeID_Cube));
		GEMNG.AddEnemy(new GameEnemy(GEMNG,ShapeID_Cube));
		//ここでGCMに登録
		return true;
	}

	@Override
	public void onDraw(GL10 gl) {
		gl.glClear( GL10.GL_COLOR_BUFFER_BIT);// | GL10.GL_DEPTH_BITS);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_FASTEST);

		gl.glMatrixMode(GL10.GL_MODELVIEW);

		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_LIGHT0);

		GEMNG.AllDraw(gl);
	}

	@Override
	public void onFrame() {
		GEMNG.AllFrameMove();
		GCM.ProceedAll();
	}

	@Override
	public void onTouch(MotionEvent me){
		GPMNG.onTouch(me);

	}


}
