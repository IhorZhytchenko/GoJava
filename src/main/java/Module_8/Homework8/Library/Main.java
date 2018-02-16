package Module_8.Homework8.Library;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {
    static int peopleCount;
    static Integer  maxAmount;
    static Semaphore semaphore1 = new Semaphore(1);
    static Semaphore semaphore;
    static Random rnd = new Random();

    static void peopleThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "  пришел ко входу в библиотеку");
                try {
                    if(semaphore.availablePermits()==0)
                        System.out.println(Thread.currentThread().getName() + "  ждет входа в библиотеку");
                    semaphore.acquire();
                    door(true);
                    System.out.println(Thread.currentThread().getName() + "  вошел в библиотеку");
                    System.out.println(Thread.currentThread().getName() + "  читает книгу");
                    Thread.sleep(1000 + rnd.nextInt(4001));
                    door(false);
                    System.out.println(Thread.currentThread().getName() + "  вышел из библиотеки");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private static void door(boolean b) throws InterruptedException {
        semaphore1.acquire();
        if (b){
            System.out.println(Thread.currentThread().getName() + "  подошел к двери с улицы");
            System.out.println(Thread.currentThread().getName() + "  проходит через дверь внутрь");
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + "  прошел через дверь внутрь");
        } else {
            System.out.println(Thread.currentThread().getName() + "  подошел к двери изнутри");
            System.out.println(Thread.currentThread().getName() + "  проходит через дверь наружу");
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + "  прошел через дверь наружу");

        }
        semaphore1.release();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        peopleCount = scanner.nextInt();
        maxAmount = scanner.nextInt();
        semaphore = new Semaphore(maxAmount);
        for (int i = 0;i<peopleCount;i++) peopleThread();



    }

}
