package Module_5.Homework5.MotorTransport;

public class CarDoor {
    private boolean doorCondition;
    private boolean windowCondition;

    public CarDoor(boolean doorCondition,boolean windowCondition){
        this.doorCondition = doorCondition;
        this.windowCondition = windowCondition;
    }
    public CarDoor(){
        this(true,true);
    }

    public void openDoor(){
        this.doorCondition = false;
    }
    public void closeDoor(){
        this.doorCondition = true;
    }
    public void changeDoorCondition(){
        if (this.doorCondition){
            this.doorCondition = false;
        }
        else {
            this.doorCondition = true;
        }
    }

    public void openWindow(){
        this.windowCondition = false;
    }
    public void closeWindow(){
        this.windowCondition = true;
    }
    public void changeWindowCondition(){
        if (this.windowCondition){
            this.windowCondition = false;
        }
        else {
            this.windowCondition = true;
        }
    }

    public void showCondition(){
        String door = this.doorCondition ? "закрыта" : "открыта";
        String window = this.windowCondition ? "закрыто" : "открыто";
        System.out.println("состояние двери - " + door + ", состояние окна - " + window);
    }

}
