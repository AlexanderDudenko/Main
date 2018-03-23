package com.dudenko.study.concurrency.theme6.computable_cache.src.main.java.com.netcracker.java_concurrency_basics;

import java.util.HashMap;
import java.util.Map;

public class ConcurrentComputableCacheWithBlockComputing<K, V> {
    // if use jdk 1.7, need use ConcurrentHashMap
    private final Map<K, V> cache = new HashMap<>();
    private final Map<Object, Object> locks = new HashMap<>();

    public V compute(K key) {
        V res = cache.get(key);

        if (res != null) {
            System.out.println(Thread.currentThread().getName() + ": hit key = " + res);
            return res;
        }

        // lock striping
        final Object lock = lock(key);
        synchronized (lock) {
            res = cache.get(key);

            // double check locking
            if (res != null) {
                System.out.println(Thread.currentThread().getName() + ": hit key = " + res);
                // кэш разогрет, можно удалить лок
                locks.remove(key);
                return res;
            }

            res = computeExpensive(key);
            cache.putIfAbsent(key, res);


            System.out.println(Thread.currentThread().getName() + ": hit key = " + res);
        }
        // кэш разогрет, можно удалить лок
        locks.remove(key);
        return res;

    }

    private Object lock(K key) {
        // Implement
        return null;
    }

    private V computeExpensive(K key) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (V) key;

    }
}
