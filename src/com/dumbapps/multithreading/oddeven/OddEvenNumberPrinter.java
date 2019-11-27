package com.dumbapps.multithreading.oddeven;

public class OddEvenNumberPrinter {

    public static void run(){
        Number num = new Number();
        System.out.println("Hello from main "+ Thread.currentThread().getName() );

        Thread odd = new Thread(new ConcurrentOddNumberPrinter(num),"odd");
        Thread even = new Thread(new ConcurrentEvenNumberPrinter(num),"even");

        even.start();
        odd.start();
    }
}


class Number{
    int n;
    public Number(){
        n=0;
    }

    public int getNum() {
        System.out.println("Thread " + Thread.currentThread().getName() + " read num " + n);
        return n;
    }

    public void setNum(int number) {
        System.out.println("Thread " + Thread.currentThread().getName() + " write num "+ n + " " + number);
        n=number;
    }
}

class ConcurrentEvenNumberPrinter implements Runnable{
    Number num;
    public ConcurrentEvenNumberPrinter(Number number){
        num = number;
        System.out.println(num.hashCode());
    }
    @Override
    public void run() {
    //    int n = 0;
        do {
        synchronized (num) {
                int n = num.getNum();
                //   System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getState() + " " + n);
                if (n % 2 == 0 && n<=20) {
                    System.out.println(Thread.currentThread().getName() + " " + n);
                    num.setNum(n + 1);
                    System.out.println(Thread.currentThread().getName() + " notify");
                    num.notify();
                    //  n = num.getNum();
                } else {
                    try {
                        System.out.println(Thread.currentThread().getName() + " wait");
                        if(n<=20)
                        num.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }while (num.getNum() <= 20);

    }
}

class ConcurrentOddNumberPrinter implements Runnable{
    Number num;
    public ConcurrentOddNumberPrinter(Number number){
        num = number;
        System.out.println(num.hashCode());
    }

    @Override
    public void run() {
      //  int n = 0;
        do{
        synchronized (num) {
                int n = num.getNum();
                //n = num.getNum();
              //  System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getState() + " " + num.getNum());
                //  System.out.println(0);
                if (n % 2 != 0 && n<=20) {
                    System.out.println(Thread.currentThread().getName() + " " + n);
                    num.setNum(n + 1);
                    System.out.println(Thread.currentThread().getName() + " notify");
                    num.notify();
                    // n = num.getNum();
                } else {
                    try {
                        System.out.println(Thread.currentThread().getName() + " wait");
                        if(n<=20)
                        num.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        }while (num.getNum() <= 20);

    }
}