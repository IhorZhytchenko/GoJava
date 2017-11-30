package Module_4.Homework4;

public class Task_5 {
    public static void main(String[] args) {
numbers(10);
    }
    static void numbers (int x){
        if (x==1){
            System.out.println(x);
        }
        else{
            numbers(x-1);
            System.out.println(x);
        }
    }
}
