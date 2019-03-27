package com.zimu.design.factorymethod;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 1、工厂方法模式（Factory Method）
 * 1.3：静态工厂方法模式
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StaticSendFactory {

    public static Sender produceMail() {
        return new MailSender();
    }

    public static Sender produceSms() {
        return new SmsSender();
    }
}
