package com.dudenko.study.concurrency.theme6.computable_cache.src.main.java.com.netcracker.java_concurrency_basics;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentComputableCacheWithBlockComputingUseJDK8<K, V> {
    // if use jdk 1.7, need use ConcurrentHashMap
    private final Map<K, V> cache = new HashMap<>();
    private final Map<Object, Object> locks = new ConcurrentHashMap<>();

    public V compute(K key) {
        return cache.computeIfAbsent(key, this::computeExpensive);
    }


    private V computeExpensive(K key) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (V) new Object();

    }
}
