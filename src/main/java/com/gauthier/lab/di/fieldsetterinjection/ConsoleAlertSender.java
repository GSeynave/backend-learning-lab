package com.gauthier.lab.di.fieldsetterinjection;

public class ConsoleAlertSender implements AlertSender {

    @Override
    public void send(String message) {
        System.out.println("ALERT: " + message);
    }
}
