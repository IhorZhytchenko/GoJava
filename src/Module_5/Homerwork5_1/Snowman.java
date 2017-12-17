package Module_5.Homerwork5_1;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;



import java.util.Random;

public class Snowman extends Application {
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 700;
    private static Circle[] circles;
    private static int maxRadius;
    private static int minRadius;
    private static int circlesCount;


    public static void main(String[] args) {
        launch(args);
    }

    void windowSetup(Stage primaryStage) {
        primaryStage.setWidth(WINDOW_WIDTH);
        primaryStage.setHeight(WINDOW_HEIGHT);
    }
    void drawUI(Pane root, Pane circlesRoot) {
        Text text1 = new Text("number of circles:");
        text1.setTranslateX(120);
        text1.setTranslateY(25);
        text1.setFont(new Font(14));
        Text text2 = new Text("max radius:");
        text2.setTranslateX(290);
        text2.setTranslateY(25);
        text2.setFont(new Font(14));
        Text text3 = new Text("min radius:");
        text3.setTranslateX(420);
        text3.setTranslateY(25);
        text3.setFont(new Font(14));

        TextField textField1 = new TextField();
        textField1.setTranslateX(230);
        textField1.setTranslateY(10);
        textField1.setMaxWidth(50);
        TextField textField2 = new TextField();
        textField2.setTranslateX(360);
        textField2.setTranslateY(10);
        textField2.setMaxWidth(50);
        TextField textField3 = new TextField();
        textField3.setTranslateX(488);
        textField3.setTranslateY(10);
        textField3.setMaxWidth(50);

        Button button1 = new Button("Draw Snowman");
        button1.setTranslateX(10);
        button1.setTranslateY(10);
        button1.setOnAction((event) -> {
            circlesRoot.getChildren().clear();
            circlesCount=Integer.parseInt(textField1.getText());
            maxRadius=Integer.parseInt(textField2.getText());
            minRadius=Integer.parseInt(textField3.getText());
            drawSnowman(circlesCount,maxRadius,minRadius,circlesRoot);

        });
        Button button2 = new Button("Dye in red");
        button2.setTranslateX(700);
        button2.setTranslateY(10);
        button2.setStyle("-fx-background-color: red");
        button2.setOnAction((event) -> {
            dyeRed();
        });
        Button button3 = new Button("Gradient");
        button3.setTranslateX(800);
        button3.setTranslateY(10);
        button3.setOnAction((event) -> {
            gradientGrey();
        });


        root.getChildren().addAll(button1,button2,button3, text1,text2, text3, textField1, textField2, textField3);
    }

    private void gradientGrey() {
        double r = 0.0;
        double g = 0.0;
        double b = 0.0;
        double step = 0.9/circlesCount;

        for (int i =0;i<circles.length;i++){
            if(i>0&&i<circlesCount){
                r+=step;
                g+=step;
                b+=step;
            }
            circles[i].setStroke(Color.color(r,g,b));
        }

    }

    private void dyeRed() {
        for (int i =0;i<circles.length;i++){
            circles[i].setStroke(Color.color(1.0,0,0));
        }
    }

    private void drawSnowman(int circlesCount, int maxRadius, int minRadius, Pane circlesRoot) {
        if (circlesCount>0){
            circles = new Circle[circlesCount+3];
            int x=500;
            int y=0;
            int radius=0;
            for (int i = 0; i<circles.length-3;i++){
                if (i==0){
                    y=circlesCount*maxRadius*2+50;
                    radius =(int)( minRadius + Math.random()*(maxRadius-minRadius));
                } else if (i<circlesCount){
                    y = y-radius-2;
                    radius =(int)( minRadius + Math.random()*(maxRadius-minRadius));
                    y = y -radius;
                }
                circles[i]=createCircle(x,y,radius);
                circlesRoot.getChildren().addAll(circles[i]);
            }
            Random r = new Random();

            circles[circlesCount]=createCircle(x+radius/2,y-radius/2,r.nextInt(radius/4));
            circles[circlesCount+1]=createCircle(x-radius/2,y-radius/2,r.nextInt(radius/4));
            circles[circlesCount+2]=createCircle(x,y+radius/3,r.nextInt(radius/4));
            circlesRoot.getChildren().addAll(circles[circlesCount],circles[circlesCount+1],circles[circlesCount+2]);

        }
    }

    private Circle createCircle( int x, int y ,int radius) {
        Circle c = new Circle(x,y,radius);
        c.setStrokeWidth(3);
        c.setStroke(Color.color(Math.random(),Math.random(),Math.random()));
        c.setFill(Paint.valueOf("#00000000"));

        return c;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        Pane circlesRoot = new Pane();
        root.getChildren().addAll(circlesRoot);
        drawUI(root,circlesRoot);
        windowSetup(primaryStage);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    }

