package com.jonathan.springnettytest.repository;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class ChannelRepository {
    private static final ConcurrentMap<String, Channel> channelMap = new ConcurrentHashMap<>();

    public static Channel get(String key){
        return channelMap.get(key);
    }

    public static void put(String key, Channel value){
        channelMap.put(key, value);
    }
}
