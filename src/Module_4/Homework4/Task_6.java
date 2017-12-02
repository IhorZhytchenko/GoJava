package Module_4.Homework4;

public class Task_6 {
    public static void main(String[] args) {
        drawRectangle(3,4);

    }
    static void drawRectangle (int a, int b){
        if (b==1){
            if (a == 1) {
                System.out.println("+ ");

            } else {
                System.out.print("+ ");
                drawRectangle(a - 1,b);

            }
        }
        else {
            drawRectangle(a, b - 1);
            drawRectangle(a,1);



        }
    }
}
