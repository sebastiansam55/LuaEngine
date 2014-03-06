package com.github.sebastiansam55.luaengine.luaj;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

import com.github.sebastiansam55.luaengine.LuaEngineActivity;

/**
 * Created by sam on 5/21/13.
 */
public class LuaParser {
    private Globals globals;
    private LuaValue func_draw;
    private LuaValue func_load;
    private LuaValue func_update;
    private LuaValue func_touchEvent;

    public LuaParser(String fileloc) {

        globals = JsePlatform.standardGlobals();
        updateGlobalValue("screenX", LuaEngineActivity.screenX);
		updateGlobalValue("screenY", LuaEngineActivity.screenY);
//        globsals.load(new input(globals));
//        globals.load(new android());
//        globals.load(new graphics());
//        globals.set("graphics", new graphics());
        globals.rawset("graphics", new graphics());
        globals.rawset("input", new input());
//        globals.loadFile();
        globals.get("dofile").call(LuaValue.valueOf(fileloc));
        
        //globals.load(new json());
        try {
            func_draw = globals.get("draw");
            func_load = globals.get("load");
            func_update = globals.get("update");
            func_touchEvent = globals.get("touch");
        } catch (LuaError e) {

        }
    }

    public LuaValue getFunc_draw() {
        return func_draw;
    }

    public LuaValue getFunc_load() {
        return func_load;
    }

    public LuaValue getFunc_update() {
        return func_update;
    }
    
    public LuaValue getFunc_touchEvent() {
        return func_touchEvent;
    }

    public boolean updateGlobalValue(String key, int value) {
        globals.set(key, value);
        return true;
    }

}
