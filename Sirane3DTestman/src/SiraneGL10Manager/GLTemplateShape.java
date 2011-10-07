
/*
 * シェイプ１この基本クラス
 *
 * x,yの2次元、zもあわせた3次元両方サポート。
 * float利用は固定。
 *
 *
 */

package SiraneGL10Manager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public abstract class GLTemplateShape {
	private FloatBuffer mVertexBuffer;
	float normals[] = null;
	public int dimension = 3, faces = 0;
	public boolean setupok = false;

	public GLTemplateShape(){
	}

	//Setupを用意。
	public abstract boolean Setup();
	protected void SetupOK(){setupok = true;} //Setupの最後に必ず呼び出す

	//Setup内で呼び出す必要がある。float配列を渡して頂点の準備。
	public boolean SetupFloatBuffer(float vertices[], int faces_, int dimension_){
		faces = faces_;
		dimension = dimension_;
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length *4 );
		vbb.order(ByteOrder.nativeOrder());
		mVertexBuffer = vbb.asFloatBuffer();
		mVertexBuffer.put(vertices);
		mVertexBuffer.position(0);
		return true;
	}

	public void Draw(GL10 gl){
		if(!setupok)return;
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(dimension, GL10.GL_FLOAT, 0, mVertexBuffer);//x,y,zの３つ
		for(int i = 0 ; i < faces ; i++){
			//ここ！！！！！！！球かけない！！法線込みの頂点配列を考慮した設計にすべし
			gl.glNormal3f(normals[i*dimension], normals[i*dimension+1], normals[i*dimension+2]);
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, i*4, 4);
		}
	}

}