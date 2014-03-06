package com.github.sebastiansam55.luaengine.luaj;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.LibFunction;

abstract public class FiveArgFunction extends LibFunction {

    private LuaValue env;

    abstract public LuaValue call(LuaValue arg0, LuaValue arg1, LuaValue arg2, LuaValue arg3, LuaValue arg4);

    public FiveArgFunction() {

    }

    public FiveArgFunction(LuaValue env) {
        this.env = env;
    }

    @Override
    public final LuaValue call() {
        return call(NIL, NIL, NIL, NIL, NIL);
    }

    @Override
    public final LuaValue call(LuaValue arg) {
        return call(arg, NIL, NIL, NIL, NIL);
    }

    @Override
    public LuaValue call(LuaValue arg1, LuaValue arg2) {
        return call(arg1, arg2, NIL, NIL, NIL);
    }

    @Override
    public LuaValue call(LuaValue arg1, LuaValue arg2, LuaValue arg3) {
        return call(arg1, arg2, arg3, NIL, NIL);
    }

    public LuaValue call(LuaValue arg1, LuaValue arg2, LuaValue arg3, LuaValue arg4) {
        return call(arg1, arg2, arg3, arg4, NIL);
    }

    @Override
    public Varargs invoke(Varargs varargs) {
        return call(varargs.arg1(), varargs.arg(2), varargs.arg(3), varargs.arg(4), varargs.arg(5));
    }


}