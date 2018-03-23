package com.dudenko.study.concurrency.theme6.computable_cache.src.main.java.com.netcracker.java_concurrency_basics;

public class MainApp {


    public static void main(String[] args) {
        //concurrentComputableCache();
//        concurrentComputableCacheWithBlockComputing();

        concurrentComputableCacheWithBlockComputingUseJDK8();


    }

    private static void concurrentComputableCache() {
        ConcurrentComputableCache<String, String> computableCache = new ConcurrentComputableCache<>();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                computableCache.compute(String.valueOf("1"));
            }
        };

        for (int i = 0; i < 5; i++) {
            new Thread(runnable).run();
        }
    }

    private static void concurrentComputableCacheWithBlockComputing() {
        ConcurrentComputableCacheWithBlockComputing<Object, Object> computableCache = new ConcurrentComputableCacheWithBlockComputing<>();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int i = Math.random() > 0.5 ? 0 : 1;
                System.out.println(String.valueOf(i));
                computableCache.compute("1");
            }
        };

        for (int i = 0; i < 5; i++) {
            new Thread(runnable).run();
        }
    }

    private static void concurrentComputableCacheWithBlockComputingUseJDK8() {
        ConcurrentComputableCacheWithBlockComputingUseJDK8<Object, Object> computableCache = new ConcurrentComputableCacheWithBlockComputingUseJDK8<>();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int zeroOrOne = Math.random() > 0.5 ? 0 : 1;
//                System.out.println(String.valueOf(i));
                Object compute = computableCache.compute(String.valueOf(zeroOrOne));
                System.out.println(compute);
            }
        };

        for (int i = 0; i < 5; i++) {
            new Thread(runnable).run();
        }
    }
}
