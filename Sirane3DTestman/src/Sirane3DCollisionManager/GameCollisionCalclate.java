/*
 * 当たり判定の具体的な処理ほげほげ。
 *
 *
 */

package Sirane3DCollisionManager;

public class GameCollisionCalclate {

	public class GCC_Result{
		//速度補正タイプ
		public static final int VTYPE_POINT = 0;//頂点間の補正
		public static final int VTYPE_BOUND = 1;//壁との補正。

		public boolean putf = false;	//衝突フラグ！
		public GameVector3D putv = null;//補正方向。(単位ベクトルであること！)
		public float putsize = 0f;		//補正サイズ。
		public int vtype = 0;			//速度補正タイプ
	}

	//演算、処理をする。
	public boolean Accept(GameCollisionObj coA,GameCollisionObj coB){
		GCC_Result result = new GCC_Result();

		if(coA.Shape.shapeid == GameCollisionShapes.SHAPETYPE_SHPERE && coB.Shape.shapeid == GameCollisionShapes.SHAPETYPE_SHPERE)
			GCC_SphereAndSphere(coA, coB, result);

		if(result.putf)
			Revision(coA, coB, result);

		return true;
	}


	//球と球の判定
	public boolean GCC_SphereAndSphere(GameCollisionObj coA,GameCollisionObj coB,GCC_Result res){
		float	rA = ((GameColShapes_Sphere)(coA.Shape)).sp_size,
				rB = ((GameColShapes_Sphere)(coB.Shape)).sp_size,
				leng = 0f;
		GameVector3D lengv = new GameVector3D(coA.vec_pos);
		lengv.SubVec(coB.vec_pos); //この差ベクトルは後からresに使う。
		leng = lengv.Leng();
		leng = rA + rB - leng;
		res.putf = (leng >= 0 );
		if(res.putf){
			lengv.Normalize();
			res.putv = lengv;
			res.putsize = leng;
			res.vtype = GCC_Result.VTYPE_POINT;
			return true;
		}
		return false;
	}


	///////////////////////////////////////
	//補正処理！
	///////////////////////////////////////
	public boolean Revision(GameCollisionObj coA,GameCollisionObj coB,GCC_Result res){
		if(res.vtype == GCC_Result.VTYPE_POINT){
			// 質量トータルから、各質量レートを概算。
			float masst = coA.mass + coB.mass,massrateA = 0f, massrateB = 0f;
			massrateA = (coB.mass/masst);
			massrateB = (coA.mass/masst);
			// 位置補正 実行
			GameVector3D dvecA = new GameVector3D(res.putv);
			GameVector3D dvecB = new GameVector3D(res.putv);
			dvecA.Mul( massrateA * res.putsize );
			dvecB.Mul( massrateB * res.putsize );
			coA.vec_pos.AddVec(dvecA);
			coB.vec_pos.SubVec(dvecB);
			// 速度補正 実行
			if(true){
				float e=1,
					sA = res.putv.Dot(coA.vec_vlcty),
					sB = res.putv.Dot(coB.vec_vlcty);
				dvecA = new GameVector3D(res.putv);
				dvecB = new GameVector3D(res.putv);
				dvecA.Mul( (sB - sA) * (e+1) * coB.mass / (coA.mass + coB.mass) );
				dvecB.Mul( (sA - sB) * (e+1) * coA.mass / (coA.mass + coB.mass) );
				coA.vec_vlcty.SubVec(dvecA);
				coB.vec_vlcty.SubVec(dvecB);
			}
		}
		return true;
	}

}
