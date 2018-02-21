package Module_8.Homework8_2;

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Task1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Введите числа А и В");
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println("Введите действие : +, - , *, /, %, ==, >, < ");
        scanner.nextLine();
        String action = scanner.nextLine();
        FutureTask<String> task = new FutureTask<String>(() -> mathematicalAction(a,b,action));
        new Thread(task).start();
        System.out.println(task.get());


    }

     static String mathematicalAction(int a, int b, String action) {
        if (action.equals("+")) return Integer.toString(a+b);
        if (action.equals("-")) return Integer.toString(a-b);
        if (action.equals("*")) return Integer.toString(a*b);
        if (action.equals("/")) return Integer.toString(a/b);
        if (action.equals("%")) return Integer.toString(a%b);
        if (action.equals("==")) return Boolean.toString(a==b);
        if (action.equals("<")) return Boolean.toString(a<b);
        if (action.equals(">")) return Boolean.toString(a>b);
        return null;
    }

}
