package com.dudenko.study.concurrency.theme6.computable_cache.src.main.java.com.netcracker.java_concurrency_basics;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentComputableCache<K, V> {
    // if use jdk 1.7, need use ConcurrentHashMap
//    private final Map<K, V> cache = new HashMap<>();
    private final Map<K, V> cache = new ConcurrentHashMap<>();

    public V compute(K key) {
        V res = cache.get(key);

        if (res != null) {
            System.out.println(Thread.currentThread().getName() + ": hit key = " + res);
            return res;
        }

        V res0 = computeExpensive(key);

        res = cache.putIfAbsent(key, res0);

        V result;
        if (res == null) {
            System.out.println(Thread.currentThread().getName() + ": miss key = " + res0);
            result = res0;
        } else {
            System.out.println(Thread.currentThread().getName() + ": hit key = " + res);
            result = res;
        }

        return result;

    }

    private V computeExpensive(K key) {
        // Very expensive computation.
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return (V) new Object();
        return (V) key;
    }
}
