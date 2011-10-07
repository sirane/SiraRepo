package test.hoge.hoge;

import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import SiraneGL10Manager.*;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;

public class TestmansRenderer implements Renderer{



	TestmansCube tescube = new TestmansCube();
	int testick = 0 ;
	int tlx=0,tly=0;
	float subsize=2.8f;
	GLTemplateShapeManager GLTSMNG;
	int ShapeID_Cube = 0;

	IntBuffer tesbuf = null;

	public TestmansRenderer(){
		super();
		GLTSMNG = new GLTemplateShapeManager();
		ShapeID_Cube = GLTSMNG.AddShape(new GLTShape_Cube());
	}

	public void SetTouchLegth(int x,int y){
		tlx=x;
		tly=y;
	}

	//描画メン！！
	@Override
	public void onDrawFrame(GL10 gl){
		try{

			gl.glClear( GL10.GL_COLOR_BUFFER_BIT);// | GL10.GL_DEPTH_BITS);
			gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_FASTEST);

			gl.glMatrixMode(GL10.GL_MODELVIEW);

			double rothoge =  Math.PI * (testick * 0.01);

			gl.glEnable(GL10.GL_LIGHTING);
			gl.glEnable(GL10.GL_LIGHT0);

			for(int i=3*3*3;i>=0;i--){
				gl.glLoadIdentity();
				gl.glTranslatef(0, 0, -12f);
				gl.glRotatef(testick*0.2f, 0, (float)Math.sin(rothoge)*180, (float)Math.cos(rothoge)*180);
				gl.glScalef(0.01f*tlx, 0.01f*tly, 0.01f*tlx);
				gl.glTranslatef(
						-subsize + (i%3 * subsize ),
						-subsize + (i/3%3 * subsize),
						-subsize + (i/3/3%3 * subsize ));
				gl.glRotatef(testick*1.0f, 0, (float)Math.sin(rothoge)*180, (float)Math.cos(rothoge)*180);
				GLTSMNG.PullShape(ShapeID_Cube).Draw(gl);
			}
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


	public void PeriodicJob() {
		// TODO 自動生成されたメソッド・スタブ
		testick++;
	}



}
