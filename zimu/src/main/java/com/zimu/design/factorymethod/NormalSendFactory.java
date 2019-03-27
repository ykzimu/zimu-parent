package com.zimu.design.factorymethod;

/**
 * 1、工厂方法模式（Factory Method）
 * 1.1：普通工厂模式
 */
public class NormalSendFactory {

    public Sender produce(String type) {
        if ("mail".equals(type)) {
            return new MailSender();
        } else if ("sms".equals(type)) {
            return new SmsSender();
        } else {
            System.out.println("请输入正确的类型!");
            return null;
        }
    }
}
