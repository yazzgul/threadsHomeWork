package org.example;

public class Task1 {
    private static Object ob1 = new Object();
    private static Object ob2 = new Object();
    public static void main( String[] args ) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (ob1) {
                    System.out.println("thread1 --- ob1");
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (ob2) {
                        System.out.println("thread1 --- ob2");
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (ob2) {
                    System.out.println("thread2 --- ob2");
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (ob1) {
                        System.out.println("thread2 --- ob1");
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
//программа создает 2 потока, каждый из которых сначала берет один объект
// и работает относительно него(блокирует его, тк мы синхронизируем относительно объекта),
// а потом для работы требуется другой объект,
// но этот объект заблокирован другим потоком.
// в итоге ни 1 поток не может получить доступ ко 2 объекту, ни 2 поток к 1 объекту.
