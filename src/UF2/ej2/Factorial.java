package UF2.ej2;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Factorial extends RecursiveTask<Long> {
    private long n;
    private final int LLINDAR = 6;

    public Factorial(long n){
        this.n = n;
    }

    private long factorialS(){
        System.out.println("S ->" + n);
        if(n==1){
            return 1;
        } else {
            long temp = 1;
            for (int i = 1; i <= n; i++) {
                temp = temp *i;
            }
            return temp;
        }
    }

    private long factorialR(){
        System.out.println("R ->" + n);
        Factorial tasca1 = new Factorial(n-1);
        tasca1.fork();
        return tasca1.join() * n;
    }

    protected Long compute(){
        if(n < LLINDAR) return factorialS();
        else return factorialR();
    }

    public static void main(String[] args){
        ForkJoinPool pool = new ForkJoinPool();
        Factorial factorial = new Factorial(5);
        pool.invoke(factorial);
        long res = factorial.join();
        System.out.println(res);
    }
}

