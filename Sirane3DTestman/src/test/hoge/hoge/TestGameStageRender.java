package test.hoge.hoge;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;

public class TestGameStageRender implements Renderer {
	private GameStage GStage = null;
	private GameUIWrapper UIWRAPEPR;

	public TestGameStageRender(GameUIWrapper UIWRAPEPR_){
		GStage = new GameStage();
		GStage.Setup();
		UIWRAPEPR = UIWRAPEPR_;
		UIWRAPEPR.CurrentTarget = GStage;
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO 自動生成されたメソッド・スタブ
		try{
			UIWRAPEPR.onDraw(gl);
			gl.glFlush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//たてよこの切り替えメン
	@Override
	public void onSurfaceChanged(GL10 gl,int width,int height){
		gl.glViewport(0, 0, width, height);

		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();

		GLU.gluPerspective(gl, 45f, (float)width/height, 1f, 50f);
	}

	//初期化メン
	@Override
	public void onSurfaceCreated(GL10 gl,EGLConfig config){
		gl.glClearColor(1,1,0,1);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);

	}


}
