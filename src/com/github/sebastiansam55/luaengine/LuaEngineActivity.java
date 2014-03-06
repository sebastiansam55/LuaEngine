package com.github.sebastiansam55.luaengine;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class LuaEngineActivity extends Activity {
	GameRender gr;
	public static int screenX, screenY;
	public LuaEngineActivity(){
		super();		
	}
	
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//make run full screen 
		//TODO make this configurable
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//get the screen size
		Display display = getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        screenX = p.x;
        screenY = p.y;
		
		gr = new GameRender(this);
		setContentView(gr);
	}
}

