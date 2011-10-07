/*
 * 敵一体をあらわす。基本クラス。
 *
 *
 *
 */

package test.hoge.hoge;

import javax.microedition.khronos.opengles.GL10;

import Sirane3DCollisionManager.GameCollisionObj;
import Sirane3DCollisionManager.GameVector3D;

public class GameEnemy extends GameCollisionObj{
	public int HitPoint = 0,DrawShapeID = 0;
	public GameEnemyManager GEM = null;

	public GameEnemy(GameEnemyManager GEM_,int ShapeID_){
		GEM = GEM_;
		DrawShapeID = ShapeID_;
		vec_pos = new GameVector3D();
	}

	public void Draw(GL10 gl){
		//ここに描画関連を色々。
		gl.glLoadIdentity();
		gl.glTranslatef(0, 0, -12f);
		gl.glTranslatef(vec_pos.x, vec_pos.y, vec_pos.z);
		GEM.GLTSMNG.PullShape(DrawShapeID).Draw(gl);
	}

	//毎フレーム移動。
	public void FrameMove(){
		vec_pos.Add((float)(Math.random()*0.2f-0.1f));
	}

	public void hitAttack(int power_){
		HitPoint -= power_;
		if(HitPoint<=0){
			GEM.DeleteEnemy(this);
		}
	}




}
