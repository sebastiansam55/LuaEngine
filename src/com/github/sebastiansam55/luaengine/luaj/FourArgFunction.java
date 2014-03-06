package com.github.sebastiansam55.luaengine.luaj;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.LibFunction;

abstract public class FourArgFunction extends LibFunction {

    private LuaValue env;

    abstract public LuaValue call(LuaValue arg0, LuaValue arg1, LuaValue arg2, LuaValue arg3);

    public FourArgFunction() {
    }

    public FourArgFunction(LuaValue env) {
        this.env = env;
    }

    @Override
    public final LuaValue call() {
        return call(NIL, NIL, NIL, NIL);
    }

    @Override
    public LuaValue call(LuaValue arg) {
        return call(arg, NIL, NIL, NIL);
    }

    @Override
    public LuaValue call(LuaValue arg1, LuaValue arg2) {
        return call(arg1, arg2, NIL, NIL);
    }

    @Override
    public LuaValue call(LuaValue arg1, LuaValue arg2, LuaValue arg3) {
        return call(arg1, arg2, arg3, NIL);
    }

    @Override
    public Varargs invoke(Varargs varargs) {
        return call(varargs.arg1(), varargs.arg(2), varargs.arg(3), varargs.arg(4));
    }

}