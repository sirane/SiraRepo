/*
 * Androidでベクトルが扱えるパッケージがなかなか見当たらないため、再開発
 *
 *
 */
package Sirane3DCollisionManager;

public class GameVector3D {
	public float x = 0f, y = 0f, z = 0f;

	public GameVector3D(){
	}
	public GameVector3D(float x_,float y_,float z_){
			x=x_;	y=y_;	z=z_;
	}
	public GameVector3D(GameVector3D v){
		x=v.x;	y=v.y;	z=v.z;
	}

	//ベクトルとの和、差
	public void AddVec(GameVector3D tg){
		x += tg.x;
		y += tg.y;
		z += tg.z;
	}
	public void SubVec(GameVector3D tg){
		x -= tg.x;
		y -= tg.y;
		z -= tg.z;
	}
	//定数との和、差
	public void Add(float tg){
		x += tg;
		y += tg;
		z += tg;
	}
	public void Sub(float tg){
		x -= tg;
		y -= tg;
		z -= tg;
	}
	//定数との
	public void Mul(float tg){
		x *= tg;
		y *= tg;
		z *= tg;
	}
	public void Div(float tg){
		x /= tg;
		y /= tg;
		z /= tg;
	}
	//内積
	public float Dot(GameVector3D tg){
		return x * tg.x + y * tg.y + z * tg.z;
	}
	//距離
	public float Leng(){
		return (float)Math.sqrt((double)( x*x + y*y + z*z ));
	}
	//単位ベクトル(距離=1)にする
	public void Normalize(){
		Div(Leng());
	}

}
