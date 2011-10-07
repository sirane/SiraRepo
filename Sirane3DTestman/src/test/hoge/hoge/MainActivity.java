package test.hoge.hoge;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

public class MainActivity extends Activity {

	TestmansGLView tesglview;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.main);
		tesglview = new TestmansGLView(this);
		setContentView(tesglview);
	}

	@Override
	protected void onResume(){
		super.onResume();
		tesglview.onResume();
	}

	@Override
	protected void onPause(){
		super.onPause();
		tesglview.onPause();
	}

	@Override
	public boolean onTouchEvent(MotionEvent me){
		tesglview.onTouch(me);

		return super.onTouchEvent(me);
	}


}