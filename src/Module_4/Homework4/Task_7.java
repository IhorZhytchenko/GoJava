package Module_4.Homework4;

import java.util.Scanner;

public class Task_7 {
    static  Scanner scanner = new Scanner(System.in);
    static boolean rec = false;

    public static void main(String[] args) {
        do {
            System.out.println("Введите номер задачи(от 1 до 6):");
            int n = scanner.nextInt();
            switch (n) {
                case 1:
                    Counting();
                    break;
                case 2:
                    drawRectangle();
                    break;
                case 3:
                    drawSquare();
                    break;
                case 4:
                    System.out.println(getMax());
                    break;
                case 5 :
                    CountingRecursion(1);
                    rec = false;
                    break;
                case 6:
                    drawRectangleRecursion(1,1);
                    rec = false;
                    break;
                default:
                    System.out.println("Не коректный ввод номера задачи");
                    break;
            }
            System.out.println("Хотите продолжить решать задачи?");
            scanner.nextLine();
        }while (scanner.nextLine().equals("да"));

    }
    // Task 1
    static void Counting(){
        System.out.println("Введите число х:");
        int x = scanner.nextInt();
        for (int i=1; i<=x;i++) System.out.println(i);
    }
    //Task 2
    static void drawRectangle (){
        System.out.println("Введите ширину и высоту прямоугольника:");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        for (int i=0; i<b;i++){
            for (int j=0; j<a; j++){
                System.out.print("+ ");
            }
            System.out.println();
        }
    }
    //Task 3
    static void drawSquare (){
        System.out.println("Введите ширину квадрата:");
        int a = scanner.nextInt();
        for (int i=0; i<a;i++){
            for (int j=0; j<a; j++){
                System.out.print("+ ");
            }
            System.out.println();
        }
    }
    //Task 4
    static int getMax() {
        System.out.println("Для поиска максимального значения введите 2 числа:");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        if (a < b) {
            return b;
        } else {
            return a;
        }
    }

    //Task 5
    static void CountingRecursion (int x){
        if (!rec){
            System.out.println("Введите число х:");
            rec = true;
            CountingRecursion(scanner.nextInt());
            return;
        }
        if (x==1){
            System.out.println(x);
        }
        else{
            CountingRecursion(x-1);
            System.out.println(x);
        }
    }
    //Task 6
    static void drawRectangleRecursion (int a, int b){
        if (!rec){
            System.out.println("Введите ширину и высоту прямоугольника:");
            rec = true;
            drawRectangleRecursion(scanner.nextInt(),scanner.nextInt());
            return;
        }
        if (b==1){
            if (a == 1) {
                System.out.println("+ ");

            } else {
                System.out.print("+ ");
                drawRectangleRecursion(a - 1,b);

            }
        }
        else {
            drawRectangleRecursion(a, b - 1);
            drawRectangleRecursion(a,1);



        }
    }


}
