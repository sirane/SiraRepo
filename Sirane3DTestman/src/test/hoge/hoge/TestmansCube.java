/*
 * はいてすキューブ!！！！！！！！！！！！！！！！！
 * ぬふ！！！！！！！
 *
 *
 */

package test.hoge.hoge;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

public class TestmansCube {

	private FloatBuffer mVertexBuffer = null;
	float normals[] = null;

	public TestmansCube(){
		//頂点作成
		try{
			setCubeVertices SET = new setCubeVertices();
			mVertexBuffer = SET.getBuffer();
			normals = SET.getNmls();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void Draw(GL10 gl){

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);

		for(int i = 0 ; i < 6 ; i++){
			gl.glNormal3f(normals[i*3], normals[i*3+1], normals[i*3+2]);
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, i*4, 4);
		}
	}

	//頂点作成くん
	class setCubeVertices{
		float vertices[] = null, normals[] = null ;
		float csize=0.5f;
		int nowpos = 0,nmlpos = 0;//１頂点単位,1面単位。

		//000
		public void setpoint(int x,int y,int z){
			vertices[nowpos*3  ] = (float)(x*2-1)*csize;
			vertices[nowpos*3+1] = (float)(y*2-1)*csize;
			vertices[nowpos*3+2] = (float)(z*2-1)*csize;
			nowpos++;
		}
		//000
		public void setnml(int x,int y,int z){
			normals[nmlpos*3  ] = (float)(x);
			normals[nmlpos*3+1] = (float)(y);
			normals[nmlpos*3+2] = (float)(z);
			nmlpos++;
		}

		public setCubeVertices() {
			// TODO 自動生成されたコンストラクター・スタブ
			nowpos = 0;
			nmlpos = 0;
			vertices = new float[3*4*6];
			normals = new float[3*6];
			//左の面
			setpoint(0,0,0);
			setpoint(0,0,1);
			setpoint(0,1,0);
			setpoint(0,1,1);
			setnml(-1,0,0);
			//右の面
			setpoint(1,0,0);
			setpoint(1,0,1);
			setpoint(1,1,0);
			setpoint(1,1,1);
			setnml(1,0,0);
			//前
			setpoint(0,0,1);
			setpoint(1,0,1);
			setpoint(0,1,1);
			setpoint(1,1,1);
			setnml(0,0,1);
			//後
			setpoint(0,0,0);
			setpoint(1,0,0);
			setpoint(0,1,0);
			setpoint(1,1,0);
			setnml(0,0,-1);
			//上
			setpoint(0,1,1);
			setpoint(1,1,1);
			setpoint(0,1,0);
			setpoint(1,1,0);
			setnml(0,1,0);
			//下
			setpoint(0,0,1);
			setpoint(1,0,1);
			setpoint(0,0,0);
			setpoint(1,0,0);
			setnml(0,-1,0);
			if(nowpos != 4*6 || nmlpos != 6)
				Log.d("!!!!!!!!!!!!!!","ぬへ！"+nowpos+"_"+nmlpos);
		}

		public FloatBuffer getBuffer(){
			FloatBuffer mVertexBuffer;

			if(vertices.length != 4*3*6){
				Log.d("!!!!!!!!!!!!!!","ほげえ！"+vertices.length);
				return null;
			}
			ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length *4 );
			vbb.order(ByteOrder.nativeOrder());

			mVertexBuffer = vbb.asFloatBuffer();
			mVertexBuffer.put(vertices);
			mVertexBuffer.position(0);
			return mVertexBuffer;
		}
		public float[] getNmls(){
			return normals;
		}


	}

}
