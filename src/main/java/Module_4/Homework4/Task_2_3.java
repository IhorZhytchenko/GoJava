package Module_4.Homework4;

public class Task_2_3 {
    public static void main(String[] args) {
        drawRectangle(2,5);
    }
    // Task 2
    static void drawRectangle (int a, int b){
        for (int i=0; i<b;i++){
            for (int j=0; j<a; j++){
                System.out.print("+ ");
            }
            System.out.println();
        }
    }
     // Task 3
     static void drawRectangle (int a){
        drawRectangle(a,a);
     }
}
