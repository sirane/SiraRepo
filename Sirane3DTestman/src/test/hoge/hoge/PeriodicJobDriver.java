/*
 * 時間差で定期処理をするような実装を手軽に行えるインターフェイスを作ってみる。
 *
 * Periodic->定期的な
 *
 * ◆つかいかた
 * 1.定期処理を書きたいクラスにPeriodicJobHandlerを実装(implements)する。
 * 2.どっかでPeriodivJobDriverのインスタンスをつくり、コンストラクタに上記実装したクラスとFPS(秒間何度呼ぶか)を渡す。
 * 3.start()する。
 * 4.まわります。
 *
 */

package test.hoge.hoge;

import android.os.Handler;
import android.os.Message;


//インターフェイス。定期処理をしたいクラスに実装。
interface PeriodicJobHandler{
	public void PeriodicJob(); //このメソッドが定期的に呼ばれる事になる。
}

//PeriodicJobhandlerを実装したものをこのクラスのインスタンスに登録。
public class PeriodicJobDriver extends Handler {
	//変数たち
	private int TIMEOUT_MESSAGE=1,FPS;
	private PeriodicJobHandler pjh;
	private boolean stopf=false;

	public PeriodicJobDriver(PeriodicJobHandler pjh_,int FPS_){
		pjh=pjh_;
		FPS=FPS_;
		stopf=false;
	}
	@Override
	public void handleMessage(Message msg){
		if(msg.what==TIMEOUT_MESSAGE){
			pjh.PeriodicJob(); //ジョブ実行
			sleep();
		}
	}
	private void sleep(){
		removeMessages(0);								//現在のメッセージがあった場合削除。
		if(!stopf)sendEmptyMessageDelayed(TIMEOUT_MESSAGE,1000/FPS); 	//空のメッセージ送信
	}
	public void start(){
		sleep();
		stopf=false;
	}
	public void stop(){
		removeMessages(0);
		stopf=true;
	}

}



