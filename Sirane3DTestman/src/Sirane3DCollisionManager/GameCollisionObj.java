/*
 * 3D判定持ちオブジェクトを規定。
 *
 * xyz位置、形状データ、質量,xyz速度を持つ
 *
 *
 */

package Sirane3DCollisionManager;


public class GameCollisionObj {
	public GameCollisionShapes Shape = null;
	public GameVector3D vec_pos, vec_vlcty;
	public float mass = 0f;

	public GameCollisionObj(){
		vec_pos = new GameVector3D();
		vec_vlcty = new GameVector3D();
	}
}
