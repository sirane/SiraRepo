/*
 * プレイヤーに関する情報を保持する。
 * 入力を受け取って処理するのもここ。
 *
 *
 *
 */

package test.hoge.hoge;

import android.view.MotionEvent;

public class GamePlayerManager {
	public int HitPoint = 0 ;

	public GamePlayerManager(){

	}

	//タッチ動作。攻撃？
	public void onTouch(MotionEvent me){
		// TODO 自動生成されたメソッド・スタブ
		if(me.getAction() == MotionEvent.ACTION_DOWN){
			me.getX(), me.getY();
		}
	}


}
