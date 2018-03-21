package com.dudenko.study.concurrency.theme4.semaphore.src.main.java.com.netcracker.java_concurrency_basics;

class Semaphore {
    //    private final int permits;
    private int permits;

    public Semaphore(int permits) {
        this.permits = permits;
    }

    public void acquire() {
        if (permits == 0) {
            System.out.println(Thread.currentThread().getName() + " couldn't acquire semaphore");
        } else {
            synchronized (this) {
                --permits;
                System.out.println(Thread.currentThread().getName() + " acquire semaphore");

                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void release() {
        synchronized (this) {
            if (permits < 3) {
                ++permits;
            }
            notifyAll();
        }
        System.out.println(Thread.currentThread().getName() + " release semaphore");
    }

    public static void main(String[] args) {
        // Run 10 threads that use the semaphore.
        // The group of 3 threads should be able to execute the critical section.
        final Semaphore semaphore = new Semaphore(3);


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

            // for pretty demo
            if (i == 5) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            thread.start();
        }
    }
}