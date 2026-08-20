package com.gauthier.lab.springcore.di.fieldsetterinjection;

public class ConsoleAlertSender implements AlertSender {

    @Override
    public void send(String message) {
        System.out.println("ALERT: " + message);
    }
}
