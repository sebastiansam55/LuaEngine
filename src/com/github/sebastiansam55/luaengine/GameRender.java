package com.github.sebastiansam55.luaengine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaValue;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.github.sebastiansam55.luaengine.luaj.LuaParser;

public class GameRender extends SurfaceView implements SurfaceHolder.Callback {
	SurfaceHolder sh;
	public static Context ctx;
	GThread renderThread;
	public static Canvas c;
	public static int BGCOLOR = Color.WHITE;

	public GameRender(Context context) {
		super(context);
        sh = getHolder();
        sh.addCallback(this);        
        ctx = context;
        setFocusable(true);
        this.requestFocus();
        this.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                try{
                	renderThread.parser.getFunc_touchEvent().call(LuaValue.valueOf(x),LuaValue.valueOf(y));
                }catch(LuaError e){
                	e.printStackTrace();
                }
                return true;
            }
        });
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		renderThread = new GThread();
		renderThread.start();
		renderThread.setRunning(true);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		renderThread.setRunning(false);
		
	}
	
	private class GThread extends Thread{
		private String jsonfname = "data.json";
		public boolean run;
		public LuaParser parser;
		public GThread(){
			File jsonfile = new File(ctx.getExternalFilesDir(null)+"/"+jsonfname);
			String filename = "";
			AssetManager am = ctx.getAssets();
			try {
				String[] files = am.list("");
				String overwrite = "";
				for(String fname : files){
					Log.w("LUAENGINE", fname);
					if(fname.equals(jsonfname)){
						JSONFileParser p;
						if(jsonfile.exists()){
							p = new JSONFileParser(jsonfile);							
						}else{
							makeFile(jsonfname);
							p = new JSONFileParser(jsonfile);						
						}
						overwrite = p.getThis("overwrite");
						filename = p.getThis("filename");
					}
				}
				for(String fname: files){
					if(fname.equals("sounds") || fname.equals("webkit") || fname.equals("images") || fname.equals("data.json")){
						Log.w("LUAENGINE", fname);
					}else{
						if(overwrite.equals("overwrite")){
							makeFile(fname);
						}
					}
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			parser = new LuaParser(ctx.getExternalFilesDir(null)+"/"+filename);
		}
		
		public void run(){
			try{
				parser.getFunc_load().call();
			}catch(LuaError e){
				
			}
			while(run){
				try{
					parser.getFunc_update().call();
				}catch(LuaError e){
					
				}
				c = null;
				try{
					c = sh.lockCanvas();
					synchronized (sh){
						if(c!=null){
							c.restore();
							c.drawColor(BGCOLOR);
							parser.getFunc_draw().call();
						}
					}
				}finally{
					if(c!=null){
						sh.unlockCanvasAndPost(c);
					}
				}
			}
		}
		
		public void setRunning(boolean state){
			run = state;
		}
		
		//credit to drew noakes from stackoverflow http://stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string
		private String fromStream(InputStream in) throws IOException{
		    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		    StringBuilder out = new StringBuilder();
		    String newLine = System.getProperty("line.separator");
		    String line;
		    while ((line = reader.readLine()) != null) {
		        out.append(line);
		        out.append(newLine);
		    }
		    return out.toString();
		}
		
		private void makeFile(String filename){
			Log.w("LUAENGINE", "Making file: "+filename);
			String content = "";
			AssetManager am = ctx.getAssets();
			try {
				InputStream is = am.open(filename);
				content = fromStream(is);				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			Log.w("LUAENGINE", content);
			File location = new File(ctx.getExternalFilesDir(null)+"/"+filename);
			try{
				if(!location.exists()){
					location.createNewFile();
				}
				
				FileWriter fw = new FileWriter(location.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(content);
				bw.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

}
