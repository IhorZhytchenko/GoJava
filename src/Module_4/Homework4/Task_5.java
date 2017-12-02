package Module_4.Homework4;

public class Task_5 {
    public static void main(String[] args) {

    }
    static void Counting (int x){
        if (x==1){
            System.out.println(x);
        }
        else{
            Counting(x-1);
            System.out.println(x);
        }
    }
}
