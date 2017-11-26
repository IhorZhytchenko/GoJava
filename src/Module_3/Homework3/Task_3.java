package Module_3.Homework3;


import java.util.Arrays;
import java.util.Scanner;

public class Task_3 {
    public static void main(String[] args) {

     Scanner scan = new Scanner(System.in);
     String s = scan.nextLine();
     int[] arr = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();

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
        System.out.println();
        // Task 2

        int [] arrRepeat = new int [arr.length];
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j< arr.length; j++){
                if (arr[i] == arr[j]) arrRepeat[i]++;
            }
        }
        int minRepeat = arrRepeat[0];
        int maxRepeat = arrRepeat[0];
        for (int i = 0; i < arrRepeat.length; i++){
            if (minRepeat > arrRepeat[i]) minRepeat = arrRepeat[i];
            if (maxRepeat < arrRepeat[i]) maxRepeat = arrRepeat[i];
        }
        System.out.println("Максимальное количество повторений чисел в массиве - " + maxRepeat);
        System.out.println("Минимальное количество повторений чисел в массиве - " + minRepeat);


    }
}
