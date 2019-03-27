package com.zimu.design.factorymethod;

/**
 * 1、工厂方法模式（Factory Method）
 * 1.2：多个工厂方法模式
 */
public class MoreSendFactory {

    public Sender produceMail() {
        return new MailSender();
    }

    public Sender produceSms() {
        return new SmsSender();
    }

}
