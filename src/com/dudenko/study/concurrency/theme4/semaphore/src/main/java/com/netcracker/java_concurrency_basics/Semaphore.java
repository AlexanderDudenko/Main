package com.dudenko.study.concurrency.theme4.semaphore.src.main.java.com.netcracker.java_concurrency_basics;

class Semaphore {
    private final int permits;

    public Semaphore(int permits) {
        this.permits = permits;
    }

    public void acquire() {
        // Implement.
    }

    public void release() {
        // Implement.
    }

    public static void main(String[] args) {
        // Run 10 threads that use the semaphore.
        // The group of 3 threads should be able to execute the critical section.
        Semaphore semaphore = new Semaphore(3);

        try {
            semaphore.acquire();

            System.out.println("Permitted.");

            Thread.sleep(2000);

            semaphore.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}