package ej2;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask<Long> {
    private long num;
    private final int LLINDAR = 10000;

    public Fibonacci(long num){
        this.num = num;
    }

    private long fibonacciI(){
        if (num <= 1){
            return num;
        }
        long fib = 1;
        long prevFib = 1;

        for (int i = 2; i < num; i++) {
            long temp = fib;
            fib += prevFib;
            prevFib = temp;
        }
        return fib;
    }

    private long fibonacciR(){
        if (num == 1 || num == 2) return 1L;
        Fibonacci t1 = new Fibonacci(num-1);
        Fibonacci t2 = new Fibonacci(num-2);
        t1.fork();
        t2.fork();
        return t1.join() + t2.join();
    }

    @Override
    protected Long compute() {
        if(num < LLINDAR) return fibonacciI();
        else return fibonacciR();
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        Fibonacci fibonacci = new Fibonacci(44);
        pool.invoke(fibonacci);
        Long res = fibonacci.join();
        System.out.println(res);
    }
}

