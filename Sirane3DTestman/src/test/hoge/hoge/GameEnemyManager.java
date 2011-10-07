/*
 *
 * GameEnemyの総括。
 *
 *
 *
 */

package test.hoge.hoge;

import java.util.ArrayList;
import java.util.Iterator;

import javax.microedition.khronos.opengles.GL10;

import SiraneGL10Manager.*;

public class GameEnemyManager {
	public ArrayList<GameEnemy> list_ge;
	GLTemplateShapeManager GLTSMNG;

	public GameEnemyManager(GLTemplateShapeManager GLTSMNG_){
		GLTSMNG = GLTSMNG_;
		list_ge = new ArrayList<GameEnemy>();
	}

	public void AddEnemy(GameEnemy ge_){
		list_ge.add(ge_);
	}

	public void DeleteEnemy(GameEnemy ge_){
		list_ge.remove(ge_);
	}

	public void AllDraw(GL10 gl){
		Iterator<GameEnemy> it = list_ge.iterator();
		while(it.hasNext()){
			it.next().Draw(gl);
		}
	}
	public void AllFrameMove(){
		Iterator<GameEnemy> it = list_ge.iterator();
		while(it.hasNext()){
			it.next().FrameMove();
		}
	}








}
