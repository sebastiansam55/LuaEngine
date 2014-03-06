package com.github.sebastiansam55.luaengine.luaj;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ZeroArgFunction;

import com.github.sebastiansam55.luaengine.GameRender;

/**
 * Created by sam on 5/21/13.
 */
public class input extends ZeroArgFunction {

    public input() {

    }

    @Override
    public LuaValue call() {
        LuaTable library = new LuaTable();
        library.set("tilt", new tilt());
        return library;
    }

    private class tilt extends ZeroArgFunction {
        TiltCalc tilter = new TiltCalc(GameRender.ctx);

        @Override
        public LuaValue call() {
            float[] tilts = null;
            tilts = tilter.getTilt();
            LuaTable tiltTable = new LuaTable();
            tiltTable.set(0, LuaValue.valueOf(tilts[0]));
            tiltTable.set(1, LuaValue.valueOf(tilts[1]));
            tiltTable.set(2, LuaValue.valueOf(tilts[2]));
            return tiltTable;
        }
    }

}