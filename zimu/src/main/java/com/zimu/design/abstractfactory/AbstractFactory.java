package com.zimu.design.abstractfactory;

public interface AbstractFactory {

    /**
     * 创建CPU对象
     *
     * @return CPU对象
     */
    Cpu createCpu();

    /**
     * 创建主板对象
     *
     * @return 主板对象
     */
    Mainboard createMainboard();
}
