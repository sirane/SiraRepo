/*
 * てすてす
 *
 */
package test.hoge.hoge;

import android.content.Context;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class TestmansGLView extends GLSurfaceView implements PeriodicJobHandler{
	PeriodicJobDriver PJD;
	TestmansRenderer tesRenderer;
	TestGameStageRender tesGSR;
	GameUIWrapper UIWRAPEPR ;
	Point touchp = new Point();
	Context context;

	public TestmansGLView(Context cx){
		super(cx);
		context=cx;
		PJD = new PeriodicJobDriver(this, 30);
		PJD.start();

		UIWRAPEPR = new GameUIWrapper();

		tesRenderer = new TestmansRenderer();
		//setRenderer(tesRenderer);

		tesGSR = new TestGameStageRender(UIWRAPEPR);
		setRenderer(tesGSR);

	}

	public boolean onTouch(MotionEvent me){
		// TODO 自動生成されたメソッド・スタブ
		if(me.getAction() == MotionEvent.ACTION_DOWN){
			touchp.set((int)me.getX(), (int)me.getY());
		}else if(me.getAction() == MotionEvent.ACTION_MOVE){
			tesRenderer.SetTouchLegth((int)(me.getX()-touchp.x), (int)(me.getY()-touchp.y));
		}
		UIWRAPEPR.onTouch(me);
		return false;
	}

	@Override
	public void PeriodicJob() {
		// TODO 自動生成されたメソッド・スタブ
		tesRenderer.PeriodicJob();
	}



}
