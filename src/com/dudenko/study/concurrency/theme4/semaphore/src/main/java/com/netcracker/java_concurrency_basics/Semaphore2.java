package com.dudenko.study.concurrency.theme4.semaphore.src.main.java.com.netcracker.java_concurrency_basics;

public class Semaphore2 {
    //    private final int permits;
    private int permits;

    public Semaphore2(int permits) {
        this.permits = permits;
    }

    public void acquire() {
            synchronized (this) {
                if (permits == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                --permits;
                System.out.println(Thread.currentThread().getName() + " acquire semaphore");
            }
    }

    public void release() {
        synchronized (this) {
            if (permits < 3) {
                ++permits;
                notify();
            }

        }
        //System.out.println(Thread.currentThread().getName() + " release semaphore");
    }

    public static void main(String[] args) {
        // Run 10 threads that use the semaphore.
        // The group of 3 threads should be able to execute the critical section.
        final Semaphore2 semaphore = new Semaphore2(3);


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();

//                    System.out.println("Permitted.");

                    Thread.sleep(2000);

                    semaphore.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        for (int i = 0; i < 10; ++i) {
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}
