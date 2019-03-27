package com.zimu.design.facade;

public class ModuleFacade {

    private ModuleA a;
    private ModuleB b;
    private ModuleC c;

    public ModuleFacade() {
        a = new ModuleA();
        b = new ModuleB();
        c = new ModuleC();
    }

    /**
     * 下面这些是A、B、C模块对子系统外部提供的方法
     */
    public void a1() {
        a.a1();
    }

    public void b1() {
        b.b1();
    }

    public void c1() {
        c.c1();
    }

}
