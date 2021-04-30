package com.jonathan.springnettytest.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    public static int SRC_LEN = 2;
    public static int DST_LEN = 2;
    public static int MAX_BODY_LEN = 28;
    public static int MAX_LEN = SRC_LEN + DST_LEN + MAX_BODY_LEN;

    private final String src;
    private final String dst;
    private final String body;

    public Message(String src, String dst, String body) {
        if(src.length() != SRC_LEN || dst.length() != DST_LEN || body.length() > MAX_BODY_LEN)
            throw new IllegalArgumentException();
        this.src = src;
        this.dst = dst;
        this.body = body;
    }

    public void show(){
        System.out.println(src);
        System.out.println(dst);
        System.out.println(body);
    }
}
