package Module_5.Homework5.MotorTransport;

public class Car {
    private String date;
    private String engine;
    private int maxSpeed;
    private double acceleration;
    private int maxPassengers;
    private int passengersCount;
    private int speed;
    private CarWheel [] wheels;
    private CarDoor [] doors;

    public Car(String date, String engine, int maxSpeed, double acceleration, int maxPassengers, int passengersCount, int speed){
        this.date = date;
        this.engine = engine;
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.maxPassengers = maxPassengers;
        this.passengersCount = passengersCount;
        this.wheels = new CarWheel[]{new CarWheel(), new CarWheel(), new CarWheel(), new CarWheel()};
        this.doors = new CarDoor[]{new CarDoor(),new CarDoor(),new CarDoor(),new CarDoor()};
        this.setSpeed(speed);
    }
    public Car(String date){
        this(date,"нет данных",180,12.0,5,0,0 );
    }

    public void setSpeed(int speed){
        if (passengersCount<1||wheels.length<4) {
            this.speed = 0;
        } else {

            this.speed = speed;
        }
    }
    public void putPassenger(){
        if (passengersCount<maxPassengers){
            passengersCount++;
        }
    }
    public void dropPassenger(){
        if (passengersCount>0){
            passengersCount--;
        }
       setSpeed(speed);
    }
    public void dropAllPassengers(){
        passengersCount = 0;
        this.setSpeed(speed);
    }
    public CarDoor getDoor(int index){
        return doors[index];
    }
    public CarWheel getWheel(int index){
        return wheels[index];
    }
    public void removeAllWheels(){

        wheels = new CarWheel[0];
        this.setSpeed(speed);
    }
    public void addWheels(int x){
        int size = wheels.length + x;
        CarWheel[] copy = new CarWheel[wheels.length];
        for (int i = 0; i < wheels.length; i++) {
            copy[i]= wheels[i];
        }
        wheels = new CarWheel[size];
        for (int i = 0; i < size; i++){
            if (copy.length > i){
                wheels[i]=copy[i];
            } else {
                wheels[i]=new CarWheel();
            }
        }

    }
    public int currentMaxSpeed(){
        if (passengersCount==0 || wheels.length<4){
            return 0;
        }
        else {
            double min = wheels[0].getWheelCondition();
            for (int i = 0; i<wheels.length;i++){
                if(min>wheels[i].getWheelCondition()) min = wheels[i].getWheelCondition();
            }
            return (int) (maxSpeed*min);
        }
    }
    public void showCarInformation(){
        System.out.println("Дата производства - " + date + ", тип двигателя - "
                + engine + ", максимальная скорость - " + maxSpeed + ", время разгона до 100км/ч - " + acceleration);
        System.out.println("пассажировместимость - " + maxPassengers + ", кол-во пассажиров внутри -"
                + passengersCount + ", текущая скорость - " + speed +
                    ", текущая возможная максимальная скорость - " + this.currentMaxSpeed());
        for (int i = 0; i<doors.length;i++) {
            System.out.print("дверь " + (i+1) + ": ");
            doors[i].showInformation();
        }
        for (int i = 0; i<wheels.length;i++) {
            System.out.print("колесо " + (i+1) + ": ");
            wheels[i].showInformation();
        }

    }
    public int getWheelsLength(){
        return this.wheels.length;
    }
    
}
