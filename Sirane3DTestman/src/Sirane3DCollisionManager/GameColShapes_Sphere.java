/*
 * 球なり！
 *
 *
 */
package Sirane3DCollisionManager;

public class GameColShapes_Sphere extends GameCollisionShapes {
	public float sp_size = 0f;
	public GameColShapes_Sphere(float size_){
		shapeid = GameCollisionShapes.SHAPETYPE_SHPERE;
		sp_size = size_;
	}
}
