package Module_5.Homework5.MotorTransport;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Перед началом работы нужно создать автомобиль.");
        System.out.println("Выберите с какими параметрами создавать автомобиль:");
        System.out.println("1 - дата производства, тип двигателя, максимальная скорость, время разгона до 100км/ч," +
                "пассажировместимость, кол-во пассажиров внутри, текущая скорость.");
        System.out.println("2 - дата производства, все остальные параметры будут заданы по умолчанию. ");
        System.out.println("(нужно ввести 1 или 2)");
        int a = scanner.nextInt();
        Car car;
        if (a==1){
            System.out.println("Введите дату производства в формате - мм.гггг");
            scanner.nextLine();
            String date = scanner.nextLine();
            System.out.println("Введите тип двигателя:");
            String engine = scanner.nextLine();
            System.out.println("Введите максимальную скорость в км/ч");
            int maxSpeed = scanner.nextInt();
            System.out.println("Введите время разгона до 100км/ч в секундах (пример - 12,3):");
            double acceleration = scanner.nextDouble();
            System.out.println("Введите пассажировместимость:");
            int maxPassengers = scanner.nextInt();
            System.out.println("Введите кол-во пассажиров внутри:");
            int passengersCount = scanner.nextInt();
            System.out.println("Введите текущая скорость в км/ч");
            int speed = scanner.nextInt();
             car = new Car(date,engine,maxSpeed,acceleration,maxPassengers,passengersCount,speed);
        } else if(a==2){
            System.out.println("Введите дату производства в формате - мм.гггг");
            scanner.nextLine();
            String date = scanner.nextLine();
             car = new Car(date);

        } else {
            System.out.println("Вы ввели не коректные данные, програма будет закрыта!");
            return;
        }
        do {
            System.out.println("Выберите операцию с автомобилем:");
            System.out.println("1 - Изменить текущую скорость;");
            System.out.println("2 - Посадить 1 пассажира в машину;");
            System.out.println("3 - Высадить 1 пассажира;");
            System.out.println("4 - Высадить всех пассажиров;");
            System.out.println("5 - Снять все колеса с машины;");
            System.out.println("6 - Установить на машину X новых колес;");
            System.out.println("7 - Вычислить текущую возможную максимальную скорость;");
            System.out.println("8 - Операци с дверьми;");
            System.out.println("9 - Операци с колесами;");
            System.out.println("10 - Вывести в консоль данные об автомобиле");
            int n = scanner.nextInt();
            switch (n) {
                case 1:
                    System.out.println("Внимание!Если в автомобиле 0 пасажиров или менше 4 колес текущая скорость будет равна-0!!!");
                    System.out.println("Введите новую текущую скорость:");
                    car.setSpeed(scanner.nextInt());
                    break;
                case 2:
                    car.putPassenger();
                    break;
                case 3:
                    car.dropPassenger();
                    break;
                case 4:
                    car.dropAllPassengers();
                    break;
                case 5:
                    car.removeAllWheels();
                    break;
                case 6:
                    System.out.println("Введите количество колес:");
                    car.addWheels(scanner.nextInt());
                    break;
                case 7:
                    System.out.println("Текущая возможная максимальная скорость -" + car.currentMaxSpeed() + "км/ч.");
                    break;
                case 8:
                    System.out.println("Введите индекс двери (от 1 до 4): ");
                    int index = scanner.nextInt();
                    if (index >=1 && index<=4){
                        System.out.println("Выберите операцию:");
                        System.out.println("1 - открыть дверь;");
                        System.out.println("2 - закрыть дверь;");
                        System.out.println("3 - открыть/закрыть дверь;");
                        System.out.println("4 - открыть окно;");
                        System.out.println("5 - закрыть окно;");
                        System.out.println("6 - открыть/закрыть окно;");
                        System.out.println("7 - вывести в консоль данные об двери;");
                        switch (scanner.nextInt()) {
                            case 1:
                                car.getDoor(index-1).openDoor();
                                break;
                            case 2:
                                car.getDoor(index-1).closeDoor();
                                break;
                            case 3:
                                car.getDoor(index-1).changeDoorCondition();
                                break;
                            case 4:
                                car.getDoor(index-1).openWindow();
                                break;
                            case 5:
                                car.getDoor(index-1).closeWindow();
                                break;
                            case 6:
                                car.getDoor(index-1).changeWindowCondition();
                                break;
                            case 7:
                                car.getDoor(index-1).showInformation();
                                break;
                            default:
                                System.out.println("Ви ввели не верное значение!!!");
                                break;
                        }
                    } else {
                        System.out.println("Ви ввели не верное значение!!!");
                    }

                    break;
                case 9:
                    if (car.getWheelsLength()>0){
                        System.out.println("Введите индекс колеса от 1 до "+car.getWheelsLength());
                        index = scanner.nextInt();
                        if (index >=1 && index<=car.getWheelsLength()) {
                            System.out.println("Выберите операцию:");
                            System.out.println("1 - Сменить шину на новую;");
                            System.out.println("2 - Стереть шину на X%;");
                            System.out.println("3 - Вывести в консоль данные об колесе;");
                            switch (scanner.nextInt()) {
                                case 1:
                                    car.getWheel(index-1).newWheel();
                                    break;
                                case 2:
                                    System.out.println("Введите на сколько % нужно стереть шину");
                                    car.getWheel(index-1).wearWheel(scanner.nextInt());
                                    break;
                                case 3:
                                    car.getDoor(index - 1).showInformation();
                                    break;
                                default:
                                    System.out.println("Ви ввели не верное значение!!!");
                                    break;
                            }
                        }
                        else {
                            System.out.println("Ви ввели не верное значение!!!");
                        }

                    }else {
                        System.out.println("На автомобиле отсутствуют колеса!!!");
                    }
                    break;
                case 10:
                    car.showCarInformation();
                    break;
                default:
                    System.out.println("Ви ввели не верное значение!!!");
                    break;
            }
            System.out.println("Желаете продолжить?");
            System.out.println("1 - продолжить;");
            System.out.println("2 - вывести в консоль данные об автомобиле и выйти;");
            System.out.println("Любое другое число -  выйти.");
            a = scanner.nextInt();
            if(a==2) car.showCarInformation();
        }while (a==1);



    }

}
