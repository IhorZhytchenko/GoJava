package Module_8.Homework8_2;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.*;

public class Task2 {
    static final int SIZE = 80000000;
    static final int THREAD_COUNT = 4;
    static int  count = 0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int [] arr = createArray(SIZE);
        FutureTask[] tasks = createTasks(arr);
        long startTime;
        System.out.println("Введите число N(количество подсчетов)");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        System.out.println("Програма работает в режиме - Thread");
        startTime  = System.currentTimeMillis();
        for (int i = 0;i<n;i++){
            threadCount(tasks);
            tasks = createTasks(arr);
        }
        System.out.println("Время работи - "+(System.currentTimeMillis()-startTime)+"миллисекунд");


        System.out.println("Програма работает в режиме - Thread Pool");
        startTime  = System.currentTimeMillis();
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0;i<n;i++) {
            threadPoolCount(tasks, threadPool);
            tasks = createTasks(arr);
        }
        threadPool.shutdown();
        System.out.println("Время работи - "+(System.currentTimeMillis()-startTime)+"миллисекунд");
    }

    static void threadPoolCount(FutureTask[] tasks, ExecutorService threadPool) throws ExecutionException, InterruptedException {
        for (int i =0;i<tasks.length;i++){
            threadPool.submit(tasks[i]);
        }
        Double sum =0.0;
        for (int i =0;i<tasks.length;i++){
            Double x = (Double) tasks[i].get();
            sum+=x;
        }
        System.out.println("Результат - "+sum);
    }


    static void threadCount(FutureTask[] tasks) throws ExecutionException, InterruptedException {
        for (int i =0;i<tasks.length;i++){
            new Thread(tasks[i]).start();
        }
        Double sum =0.0;
        for (int i =0;i<tasks.length;i++){
            Double x = (Double) tasks[i].get();
            sum+=x;
        }
        System.out.println("Результат - "+sum);
    }

    static FutureTask[] createTasks(int[] arr) {
        FutureTask [] result = new FutureTask[THREAD_COUNT];
        int step = SIZE/THREAD_COUNT;
        for (int i =0;i<THREAD_COUNT;i++) {
            result[i] = new FutureTask<Double>(() -> {
                count++;
                double summ = 0.0;
                int begin =(((count-1)%THREAD_COUNT)*step);
                int finish;
                if (begin ==SIZE-step){
                     finish = SIZE;
                } else {
                     finish = ((count % THREAD_COUNT) * step);
                }
                //System.out.println(Thread.currentThread().getName()+"Begin"+begin);
                //System.out.println(Thread.currentThread().getName()+"Fin"+finish);
                for (int j = begin; j < finish; j++) {
                    summ += sumSinCos(arr[j]);
                }

                return summ;
            });
        }


        return result;
    }

    static int[] createArray(int size){
        int [] result = new int[size];
        for (int i=0;i<size;){
            result[i]=++i;
        }
        return result;
    }

    static double sumSinCos(int x) {
        return (Math.sin(x)+ Math.cos(x));
    }
}
