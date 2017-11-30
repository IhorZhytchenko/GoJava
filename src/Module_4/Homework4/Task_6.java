package Module_4.Homework4;

public class Task_6 {
    public static void main(String[] args) {
        drawRectangle(6,6);

    }
    static void drawRectangle (int a, int b){
        if (b==1){
            drawRec(a);
        }
        else {
            drawRec(a);
            System.out.println();
            drawRectangle(a,b-1);
        }

    }
    static void drawRec (int a) {
        if (a == 1) {
            System.out.print("+ ");
        } else {
            drawRec(a - 1);
            System.out.print("+ ");
        }

    }
}
