/*
 * UIのラッパーをうけもつ。
 * このクラスのインスタンスをほいほい渡して入出力の管理をする。
 *
 */
package test.hoge.hoge;

import javax.microedition.khronos.opengles.GL10;

import android.view.MotionEvent;

interface GameUIWrapper_Interface{

	public abstract void onTouch(MotionEvent me);
	public abstract void onFrame();
	public abstract void onDraw(GL10 gl);

}

public class GameUIWrapper implements PeriodicJobHandler{
	public GameUIWrapper_Interface CurrentTarget;
	private PeriodicJobDriver PJD;

	public GameUIWrapper(){
		PJD = new PeriodicJobDriver(this, 30);
		PJD.start();
	}

	public void onTouch(MotionEvent me){
		CurrentTarget.onTouch(me);
	}
	public void onDraw(GL10 gl){
		CurrentTarget.onDraw(gl);
	}

	@Override
	public void PeriodicJob() {
		// TODO 自動生成されたメソッド・スタブ
		CurrentTarget.onFrame();

	}

}
