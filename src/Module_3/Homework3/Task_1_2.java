package Module_3.Homework3;


import java.util.Scanner;

public class Task_1_2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите размер массива");
        int lenght = scan.nextInt();
        int [] arr = new  int[lenght];
        System.out.println("Введите элементы массива");
        for (int i =0; i<arr.length;i++){
            arr[i]=scan.nextInt();
        }
        int min = arr[0];
        int max = arr[0];
        int repeat = 0;
        for (int i = 0; i<arr.length;i++){
            if (min > arr[i]) min=arr[i];
            if (max < arr[i]) max=arr[i];
            if (arr[i]==5) repeat++;

        }
        for (int i = 0; i<arr.length;i++){
            for(int j=1; j < (arr.length-i); j++){
                if(arr[j-1] > arr[j]) {
                    int x = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = x;
                }
            }
        }

        System.out.println("Минимальное число в массиве - " +min);
        System.out.println("Максимальное число в массиве - " +max);
        System.out.println("Число 5 встречаеться в массиве " + repeat + " раз");
        for(int value : arr) {
            System.out.print(value + " ");
        }

    }


}
