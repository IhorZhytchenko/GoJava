package Module_5.Homework5.MotorTransport;

public class CarWheel {
    private double wheelCondition;

    public CarWheel(double wheelCondition){
        this.wheelCondition = wheelCondition;
    }
    public CarWheel(){
        this(1.0);
    }

    public void newWheel(){
        this.wheelCondition = 1.0;
    }
    public void wearWheel(int x){
        this.wheelCondition -= (double)x/100.0;
    }
    public double getWheelCondition(){
        return this.wheelCondition;
    }
    public void showInformation(){
        System.out.println("Состояние шины - " + this.wheelCondition*100 +"%" );

    }

}
