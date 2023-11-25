package com.blankj.easy._1114;

import java.util.concurrent.CountDownLatch;

class Foo {

    private CountDownLatch firstLock = new CountDownLatch(1);

    private CountDownLatch secondLock = new CountDownLatch(1);
    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        firstLock.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        firstLock.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        secondLock.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        secondLock.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

    public static void main(String[] args) {
        Foo foo = new Foo();
        new Thread(() -> {
            try {
                foo.first(() -> System.out.println("first"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                foo.second(() -> System.out.println("second"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                foo.third(() -> System.out.println("third"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}