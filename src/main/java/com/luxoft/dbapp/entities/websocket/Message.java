package com.luxoft.dbapp.entities.websocket;

import lombok.Data;

@Data
public class Message {

    private String from;
    private String text;
}