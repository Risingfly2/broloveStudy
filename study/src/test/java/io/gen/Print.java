package io.gen;

public class Print {
    private static volatile int count;

    static class A implements Runnable{
        private final Object lock;
        public A(Object lock){
            this.lock = lock;
        }

        @Override
        public void run() {
            for (;;){
                synchronized (lock){
                    System.out.println("A");
                    System.out.println(++count);
                    lock.notify();
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + "wait");
                        lock.wait();
                        System.out.println(Thread.currentThread().getName() + "free");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
    }

    static class B implements Runnable{
        private final Object lock;
        public B(Object lock){
            this.lock = lock;
        }

        @Override
        public void run() {
            for (;;){
                synchronized (lock){
                    System.out.println("B");
                    System.out.println(++count);
                    lock.notify();
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + "wait");
                        lock.wait();
                        System.out.println(Thread.currentThread().getName() + "free");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public static void main(String[] args) {

        Object lock = new Object();
        Thread a = new Thread(new A(lock));
        Thread b = new Thread(new B(lock));
        a.start();
        b.start();
    }
}
