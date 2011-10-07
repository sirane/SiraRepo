/*
 *
 * GLTemplateShapeの派生。
 *
 * 3Dの球。半径が一様な球。
 * 半径は0.5fとなる。
 *
 * インデックスバッファ利用
 *
 * 法線、頂点 計float6こ
 *
 */

package SiraneGL10Manager;

import Sirane3DCollisionManager.GameVector3D;
import android.util.Log;

public class GLTShape_Sphere extends GLTemplateShape {
	int indeces[] = null;
	public GLTShape_Sphere() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public boolean Setup(){
		if(setupok)return true;
		try{
			setSphereVertices SET = new setSphereVertices(8,8,0.5f,0.5f);
			SetupFloatBuffer(SET.vertices, 64,3);
			indeces = SET.indeces;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		SetupOK();
		return true;
	}
	//頂点作成くん
	class setSphereVertices{
		float vertices[] = null;
		int indeces[] = null;
		float csize=0.5f;
		int nowpos = 0,nmlpos = 0;//１頂点単位,1面単位。

		//000
		public void setpoint(GameVector3D v){
			vertices[nowpos*6+3  ] = v.x;
			vertices[nowpos*6+3+1] = v.y;
			vertices[nowpos*6+3+2] = v.z;
			nowpos++;
		}
		//000
		public void setnml(GameVector3D v){
			vertices[nmlpos*6  ] = v.x;
			vertices[nmlpos*6+1] = v.y;
			vertices[nmlpos*6+2] = v.z;
			nmlpos++;
		}

		public setSphereVertices(
				int vn1, int vn2,
				float size1,float size2) {
			// TODO 自動生成されたコンストラクター・スタブ
			nowpos = 0;
			nmlpos = 0;
			float rot1 = 0.5f, rot2 = 0f;
			GameVector3D setp = new GameVector3D();
			vertices = new float[ 3 * vn1*vn2 * 2 ]; //xyz * 頂点数 * 法線と頂点
			indeces = new int[ (vn1-1)*vn2 ];
			float ang_p1 = (1f/(vn1-1)), ang_p2 = (2f/(vn2)),nr;//単位角度y,xz ,現在xz面半径
			//座標代入
			for(int i=0; i<vn1 ; i++){
				nr = (float)Math.cos((i*ang_p1+rot1)*Math.PI) *size2;
				setp.y = (float)Math.sin((i*ang_p1+rot1)*Math.PI) *size1;
				for(int j=0;j<vn2;j++){
					setp.x = (float)Math.cos((j*ang_p2+rot2)*Math.PI) * nr;
					setp.z = (float)Math.sin((j*ang_p2+rot2)*Math.PI) * nr;
					setpoint(setp);
					setnml(setp);//リファクタ候補
				}
			}
			//インデックス代入
			int ni,nin=0;
			for(int i=0 ; i<(vn1-1) ; i++)
				for(int j=0 ; j<(vn2) ; j++){
					ni = (i+1) * vn2 + j % vn2 ;
					indeces[nin]=ni;
					nin++;
					ni = (i) * vn2 + j % vn2 ;
					indeces[nin]=ni;
					nin++;
				}
		}
	}
}
