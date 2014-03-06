package com.github.sebastiansam55.luaengine.luaj;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.ThreeArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;

import android.graphics.Color;
import android.graphics.Paint;

import com.github.sebastiansam55.luaengine.GameRender;

/**
 * Created by sam on 5/21/13.
 */
public class graphics extends ZeroArgFunction {

    private static Paint defPaint = new Paint();
    public static graphics g = null;

    public graphics() {
    	g = this;
    }

    @Override
    public LuaValue call() {    	
        LuaTable library = new LuaTable();
        library.set("point", new point());
        library.set("circle", new circle());
        library.set("rect", new rect());
//        library.set("font", new font());
//        library.set("draw", new draw());
        library.set("print", new print());
        library.set("line", new line());
        library.set("color", new color());
        return library;

    }

    private static class point extends ThreeArgFunction {
        @Override
        public LuaValue call(LuaValue x, LuaValue y, LuaValue color) {
            Paint p = new Paint();
            p.setColor(color.toint());
            p.setStyle(Paint.Style.FILL);
            GameRender.c.drawPoint(x.tofloat(), y.tofloat(), p);
            return LuaValue.NIL;
        }

        @Override
        public LuaValue call(LuaValue x, LuaValue y) {
        	GameRender.c.drawPoint(x.tofloat(), y.tofloat(), defPaint);
            return LuaValue.NIL;
        }
    }

    private static class circle extends FourArgFunction {
        @Override
        public LuaValue call(LuaValue cx, LuaValue cy, LuaValue radius, LuaValue color) {
            Paint p = new Paint();
            p.setColor(color.toint());
            p.setStyle(Paint.Style.FILL);
            GameRender.c.drawCircle(cx.tofloat(), cy.tofloat(), radius.tofloat(), p);
            return LuaValue.NIL;
        }

        public LuaValue call(LuaValue cx, LuaValue cy, LuaValue radius) {
        	GameRender.c.drawCircle(cx.tofloat(), cy.tofloat(), radius.tofloat(), defPaint);
            return LuaValue.NIL;
        }
    }

    private static class rect extends FiveArgFunction {
        @Override
        public LuaValue call(LuaValue x, LuaValue y, LuaValue height, LuaValue width, LuaValue color) {
        	if(color.isnil()){
        		return call(x,y,height,width);
        	}
            Paint p = new Paint();
            p.setColor(color.toint());
            GameRender.c.drawRect(x.tofloat(), y.tofloat(), x.tofloat() + height.tofloat(), y.tofloat() + width.tofloat(), p);
            return LuaValue.NIL;
        }

        @Override
        public LuaValue call(LuaValue x, LuaValue y, LuaValue height, LuaValue width) {
        	GameRender.c.drawRect(x.tofloat(), y.tofloat(), x.tofloat() + height.tofloat(), y.tofloat() + width.tofloat(), defPaint);
            return LuaValue.NIL;
        }
    }

    private static class font extends OneArgFunction {
        @Override
        public LuaValue call(LuaValue fontName) {
            //TODO implement font support for drawing text
            return null;
        }
    }

    private class print extends SixArgFunction {

        @Override
        public LuaValue call(LuaValue string, LuaValue x, LuaValue y, LuaValue size, LuaValue color, LuaValue rotation) {
        	if(rotation.isnil()){
        		return call(string, x,y,size,color);
        	}
        	GameRender.c.save();
        	GameRender.c.rotate(rotation.toint());
            Paint p = new Paint();
            p.setColor(color.toint());
            p.setTextSize(size.tofloat());
            GameRender.c.drawText(string.toString(), x.toint(), y.toint(), p);
            GameRender.c.restore();
            return LuaValue.NIL;
        }

        @Override
        public LuaValue call(LuaValue string, LuaValue x, LuaValue y, LuaValue size, LuaValue color) {
        	if(color.isnil()){
        		return call(string, x, y, size);
        	}
            Paint p = new Paint();
            p.setColor(color.toint());
            p.setTextSize(size.tofloat());
            GameRender.c.drawText(string.toString(), x.toint(), y.toint(), p);
            return LuaValue.NIL;
        }

        @Override
        public LuaValue call(LuaValue string, LuaValue x, LuaValue y, LuaValue size) {
        	if(size.isnil()){
        		return call(string,x,y);
        	}
            Paint p = new Paint();
            p.setTextSize(size.toint());
            p.setColor(Color.BLACK);
            GameRender.c.drawText(string.toString(), x.toint(), y.toint(), p);
            return LuaValue.NIL;
        }

        @Override
        public LuaValue call(LuaValue string, LuaValue x, LuaValue y) {
        	GameRender.c.drawText(string.toString(), x.toint(), y.toint(), defPaint);
            return LuaValue.NIL;
        }


    }

    private static class color extends FourArgFunction {

        @Override
        public LuaValue call(LuaValue r, LuaValue g, LuaValue b, LuaValue a) {
        	if(a.isnil()){
        		return call(r,g,b);
        	}
            return LuaValue.valueOf(Color.argb(a.toint(), r.toint(), g.toint(), b.toint()));
        }

        @Override
        public LuaValue call(LuaValue r, LuaValue g, LuaValue b) {
        	if(g.isnil() || b.isnil()){
        		return call(r);
        	}
            return LuaValue.valueOf(Color.rgb(r.toint(), g.toint(), b.toint()));
        }

        @Override
        public LuaValue call(LuaValue colorName) {
            String colorValue = colorName.tojstring();
            if (colorValue.equals("WHITE")) {
                return LuaValue.valueOf(Color.WHITE);
            } else if (colorValue.equals("BLACK")) {
                return LuaValue.valueOf(Color.BLACK);
            } else if (colorValue.equals("BLUE")) {
                return LuaValue.valueOf(Color.BLUE);
            } else if (colorValue.equals("RED")) {
                return LuaValue.valueOf(Color.RED);
            } else if (colorValue.equals("GREEN")) {
                return LuaValue.valueOf(Color.GREEN);
            } else if (colorValue.equals("GRAY")) {
                return LuaValue.valueOf(Color.GRAY);
            } else if (colorValue.equals("YELLOW")) {
                return LuaValue.valueOf(Color.YELLOW);
            } else {
                return LuaValue.NIL;
            }
        }
    }

    private static class line extends FiveArgFunction {
        @Override
        public LuaValue call(LuaValue x1, LuaValue y1, LuaValue x2, LuaValue y2) {
            Paint p = new Paint();
            p.setColor(Color.BLACK);
            GameRender.c.drawLine(x1.tofloat(), y1.tofloat(), x2.tofloat(), y2.tofloat(), p);
            return LuaValue.NIL;
        }

        @Override
        public LuaValue call(LuaValue x1, LuaValue y1, LuaValue x2, LuaValue y2, LuaValue color) {
        	if(color.isnil()){
        		return call(x1,y1,x2,y2);
        	}
            Paint p = new Paint();
            p.setColor(color.toint());
            GameRender.c.drawLine(x1.tofloat(), y1.tofloat(), x2.tofloat(), y2.tofloat(), p);
            return LuaValue.NIL;
        }
    }
}