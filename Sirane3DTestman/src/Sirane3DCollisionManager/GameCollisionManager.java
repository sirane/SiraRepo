/*
 *
 * 衝突判定の管理をする。
 *
 *
 * 衝突判定を持つ対象は
 * 敵OBJ <-> AttackOBJ
 *
 *
 *
 *
 */
package Sirane3DCollisionManager;

import java.util.ArrayList;

public class GameCollisionManager {
	GameCollisionCalclate GCC;
	private ArrayList<GameCollisionObj> List_ColObj;

	public GameCollisionManager(){
		GCC = new GameCollisionCalclate();
		List_ColObj = new ArrayList<GameCollisionObj>();
	}

	public boolean Add(GameCollisionObj co_){
		return List_ColObj.add(co_);
	}

	public boolean Remove(GameCollisionObj co_){
		return List_ColObj.remove(co_);
	}

	//当たり判定、処理を実行する。
	//判定はいいけど処理は色々依存するよ??????????？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
	public boolean ProceedAll(){
		int objnum = List_ColObj.size();
		for(int i=0 ; i < objnum-1 ; i++ )
			for(int j=0 ; j < objnum ; j++ )
				AcceptCol(List_ColObj.get(i),List_ColObj.get(j));
		return true;
	}

	private boolean AcceptCol(GameCollisionObj coA,GameCollisionObj coB){
		return GCC.Accept(coA, coB);
	}
}
