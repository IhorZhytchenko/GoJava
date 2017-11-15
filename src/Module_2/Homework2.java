package Module_2;

import java.util.Scanner;

public class Homework2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя");
        String name = scanner.nextLine();
        System.out.println("Введите город проживания");
        String city = scanner.nextLine();
        System.out.println("Введите возраст");
        int age =scanner.nextInt();
        System.out.println("Введите хобби");
        String hobby = scanner.nextLine();
        System.out.println();
        System.out.println("Имя:    " + name);
        System.out.println("Город:  " + city);
        System.out.println("Возраст:" + age);
        System.out.println("Хобби:  " + hobby);
        System.out.println();
        System.out.println("Человек по имени " + name + " живет в городе " + city + ".");
        System.out.println("Этому человеку " + age + " лет и он любит заниматься " + hobby + ".");
        System.out.println();
        System.out.println(name + " - имя");
        System.out.println(city + " - город");
        System.out.println(age + " - возраст");
        System.out.println(hobby + " - хобби");

    }





}
