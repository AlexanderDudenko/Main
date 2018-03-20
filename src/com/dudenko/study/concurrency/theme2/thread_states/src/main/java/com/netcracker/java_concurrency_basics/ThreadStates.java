package com.dudenko.study.concurrency.theme2.thread_states.src.main.java.com.netcracker.java_concurrency_basics;

public class ThreadStates {
    public static void main(String[] args) {
        printNewState();
        printRunnableState();
        printBlockedState();
    }

    private static void printNewState() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
            }

        };

        Thread t = new Thread(r);
        System.out.println(t.getState());
    }

    private static void printRunnableState() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
            }

        };

        Thread t = new Thread(r);
        t.start();
        System.out.println(t.getState());
    }

    private static void printBlockedState() {


        Runnable r = new Runnable() {
            private Object object = new Object();

            private synchronized void customWait() {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void run() {
                customWait();
            }

        };

        Thread t = new Thread(r);
        t.start();
        System.out.println(t.getState());
    }
}
