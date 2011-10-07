package	test.hoge.hoge.test;

import	test.hoge.hoge.MainActivity;

import	java.text.SimpleDateFormat;
import	java.util.Calendar;

import	android.content.Context;
import	android.test.ActivityInstrumentationTestCase2;
import	android.test.suitebuilder.annotation.MediumTest;
import	android.widget.TextView;


public	class	TesTesTestCase	extends	ActivityInstrumentationTestCase2	{

	public	TesTesTestCase()	{
		super("test.hoge.hoge",	MainActivity.class);
	}

	@Override
	protected	void	setUp()	throws	Exception	{
		super.setUp();
	}

	/**
		*	アプリケーション初期立ち上げの時のデータ確認
		*/
	@MediumTest
	public	void	testAppicationInit()	{
		assertTrue(true);
	}

	public void testhogehoge(){
		assertTrue(false);
		//TouchUtils.dragViewTo(DragAndDropSampleTest.this, target

	}



}