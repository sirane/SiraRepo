/*
 * 何度も使用するシェイプをためこんで、流用しまくるお手軽クラス。
 *
 *
 * 1.新たなシェイプを作って、ShapeIDを発行、
 * 2.そのIDですぐ呼び出せる。
 *
 * 途中削除は今の所できない仕様で＾＾
 *
 */
package SiraneGL10Manager;

import java.util.ArrayList;

public class GLTemplateShapeManager {
	public ArrayList<GLTemplateShape> List_GLTS;
	private final int IDTALE = 123000;

	public GLTemplateShapeManager(){
		List_GLTS = new ArrayList<GLTemplateShape>();
	}

	public int AddShape(GLTemplateShape GLTS_){
		if(GLTS_.Setup())
			List_GLTS.add(GLTS_);
		else return -1;
		return List_GLTS.size() - 1 + IDTALE;
	}

	public GLTemplateShape PullShape(int id){
		return List_GLTS.get(id - IDTALE);
	}

}


