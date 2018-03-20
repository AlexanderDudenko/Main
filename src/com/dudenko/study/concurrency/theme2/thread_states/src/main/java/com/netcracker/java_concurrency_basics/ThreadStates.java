package com.dudenko.study.concurrency.theme2.thread_states.src.main.java.com.netcracker.java_concurrency_basics;

public class ThreadStates {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                // Implement.
            }
        };

        Thread t = new Thread(r);

        // Implement.

        System.out.println(t.getState());
    }
}
