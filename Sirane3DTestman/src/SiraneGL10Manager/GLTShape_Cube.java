/*
 *
 * GLTemplateShapeの派生。
 *
 * 3Dのキューブ。立方体。
 * １辺は1fとなる。つまり中心から-0.5f ~ 0.5fの直線が８本。
 *
 *
 *
 *
 */

package SiraneGL10Manager;

import android.util.Log;

public class GLTShape_Cube extends GLTemplateShape {

	public GLTShape_Cube() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public boolean Setup(){
		if(setupok)return true;
		try{
			setCubeVertices SET = new setCubeVertices();
			SetupFloatBuffer(SET.vertices, 6,3);
			normals = SET.normals;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		SetupOK();
		return true;
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
			vertices = new float[3*4*6];//xyz3 * 4点(1面) * 6面
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
	}
}
