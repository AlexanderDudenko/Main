package com.dudenko.study.concurrency.theme7.deadlocked_account.src.main.java.com.netcracker.java_concurrency_basics;

class DeadlockedAccount {
    private long id;
    private long amount;

    void deposit(long m) {
        amount += m;
    }

    void withdraw(long m) {
        amount -= m;
    }

    static void transfer(DeadlockedAccount from, DeadlockedAccount to, long m) {
        synchronized (from) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (to) {
                from.withdraw(m);
                to.deposit(m);
            }
        }
    }

    public static void main(String[] args) {
        final DeadlockedAccount a1 = new DeadlockedAccount();
        final DeadlockedAccount a2 = new DeadlockedAccount();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                transfer(a1, a2, 3);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                transfer(a2, a1, 3);
            }
        });

        t1.start();
        t2.start();
    }
}