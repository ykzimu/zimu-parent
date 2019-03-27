package com.zimu.design.factorymethod;

import java.io.Serializable;

public class MailSender implements Sender, Serializable {
    @Override
    public void send() {
        System.out.println("this is mail sender!");
    }

}
