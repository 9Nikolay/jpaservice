package com.luxoft.dbapp.jms;

import com.luxoft.dbapp.entities.security.User;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderTransactionReceiver {

    @JmsListener(destination = "OrderTransactionQueue", containerFactory = "myFactory")
    public void receiveMessage(User user) {
        System.out.println("Received <" + user + ">");
    }
}
