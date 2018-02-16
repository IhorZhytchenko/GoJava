package Module_8.Homework8.Geometry;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static boolean isCollide = false;
    private static int start =0;
    private static int finish = 0;

    void windowSetup(Stage primaryStage) {
        primaryStage.setWidth(WINDOW_WIDTH);
        primaryStage.setHeight(WINDOW_HEIGHT);

        primaryStage.setMaxWidth(WINDOW_WIDTH);
        primaryStage.setMaxHeight(WINDOW_HEIGHT);

        primaryStage.setMinWidth(WINDOW_WIDTH);
        primaryStage.setMinHeight(WINDOW_HEIGHT);
    }

    void drawUI(Pane root,Pane rectanglePane) {
        TextField textField1 = new TextField();
        textField1.setTranslateX(100);
        textField1.setTranslateY(45);
        textField1.setMaxWidth(45);

        TextField textField2 = new TextField();
        textField2.setTranslateX(250);
        textField2.setTranslateY(45);
        textField2.setMaxWidth(45);

        TextField textField3 = new TextField();
        textField3.setTranslateX(370);
        textField3.setTranslateY(45);
        textField3.setMaxWidth(45);

        TextField textField4 = new TextField();
        textField4.setTranslateX(490);
        textField4.setTranslateY(45);
        textField4.setMaxWidth(45);

        TextField textField5 = new TextField();
        textField5.setTranslateX(610);
        textField5.setTranslateY(45);
        textField5.setMaxWidth(45);

        TextField textField6 = new TextField();
        textField6.setTranslateX(735);
        textField6.setTranslateY(45);
        textField6.setMaxWidth(45);

        Button button1 = new Button("Multy Threads");
        button1.setTranslateX(40);
        button1.setTranslateY(10);
        button1.setOnAction((event) -> {
            multyThreads(createRectangles(rectanglePane,textField1,textField2,textField3,textField4,textField5,textField6));

        });

        Button button2 = new Button("Single Thread");
        button2.setTranslateX(220);
        button2.setTranslateY(10);
        button2.setOnAction((event) -> {
            singleThread(createRectangles(rectanglePane,textField1,textField2,textField3,textField4,textField5,textField6));

        });

        Button button3 = new Button("Optimal Threads");
        button3.setTranslateX(400);
        button3.setTranslateY(10);
        button3.setOnAction((event) -> {
            optimalTreads(createRectangles(rectanglePane,textField1,textField2,textField3,textField4,textField5,textField6));

        });

        CheckBox checkBox = new CheckBox("Collide with each other");
        checkBox.setTranslateX(560);
        checkBox.setTranslateY(15);
        checkBox.selectedProperty().addListener(event -> {
            if (checkBox.isSelected()){
                isCollide=true;
            } else {
                isCollide = false;
            }
        });

        Text text1 = new Text("_______________________________________________________________________________________" +
                "__________________________________________________________________________________________________");
        text1.setTranslateY(79);

        Text text2 = new Text("min rectangles:");
        text2.setTranslateX(5);
        text2.setTranslateY(60);
        text2.setFont(new Font(14));

        Text text3 = new Text("max rectangles:");
        text3.setTranslateX(150);
        text3.setTranslateY(60);
        text3.setFont(new Font(14));

        Text text4 = new Text("min width:");
        text4.setTranslateX(305);
        text4.setTranslateY(60);
        text4.setFont(new Font(14));

        Text text5 = new Text("max width:");
        text5.setTranslateX(420);
        text5.setTranslateY(60);
        text5.setFont(new Font(14));

        Text text6 = new Text("min height:");
        text6.setTranslateX(540);
        text6.setTranslateY(60);
        text6.setFont(new Font(14));

        Text text7 = new Text("max height:");
        text7.setTranslateX(660);
        text7.setTranslateY(60);
        text7.setFont(new Font(14));


        root.getChildren().addAll(button1,button2,text1,text2,text3,text4,text5,text6,text7,button3,textField1
                ,textField2,textField3,textField4,textField5,textField6,checkBox);
    }

    private void optimalTreads(MyRectangle[] rectangles) {
        int treadsCount = Runtime.getRuntime().availableProcessors();
        int count =0;
        MyRectangle[] arr ;
        for (int i =0;i<treadsCount;i++){
            int arrSize;
            if (i<(rectangles.length%treadsCount)){
                arrSize = rectangles.length/treadsCount+1;
            } else {
                arrSize = rectangles.length/treadsCount;
            }
            arr = new MyRectangle[arrSize];
            for (int j=0;j<arrSize;j++){
                arr[j]=rectangles[count];
                count++;
            }
            singleThread(arr);
        }
    }

    private void singleThread(MyRectangle[] rectangles) {
        new Thread(() -> {
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    for (int i=0;i<rectangles.length;i++){
                        rectangles[i].move();
                    }
                }
            };
            timer.start();

        }).start();
    }

    private MyRectangle[] createRectangles(Pane rectanglePane, TextField textField1, TextField textField2
            , TextField textField3, TextField textField4, TextField textField5, TextField textField6) {
        int minRectangles = Integer.parseInt(textField1.getText());
        int maxRectangles = Integer.parseInt(textField2.getText());
        int minWidth = Integer.parseInt(textField3.getText());
        int maxWidth = Integer.parseInt(textField4.getText());
        int minHeight = Integer.parseInt(textField5.getText());
        int maxHeight = Integer.parseInt(textField6.getText());
        int rectangleCount = (int)(minRectangles + Math.random()*(maxRectangles-minRectangles+1));
        MyRectangle [] rectangles = new MyRectangle[rectangleCount];
        for (int i =0;i<rectangleCount;i++){
            rectangles[i]=new MyRectangle(minWidth,maxWidth,minHeight,maxHeight);
            rectanglePane.getChildren().addAll(rectangles[i]);
        }
        return rectangles;
    }

    private void multyThreads(MyRectangle[] rectangles) {
        for (int i =0;i<rectangles.length;i++){
         MyRectangle rectangle = rectangles[i];
        new Thread(() -> {
              AnimationTimer timer = new AnimationTimer() {
                  @Override
                  public void handle(long now) {
                     rectangle.move();
                  }
              };
              timer.start();

          }).start();}
   }

    @Override
    public void start(Stage primaryStage) throws Exception {
        windowSetup(primaryStage);
        Pane root = new Pane();
        Pane rectanglePane = new Pane();
        rectanglePane.setTranslateY(80);
        root.getChildren().addAll(rectanglePane);
        drawUI(root,rectanglePane);
        primaryStage.setScene(new Scene(root,WINDOW_WIDTH,WINDOW_HEIGHT));
        primaryStage.show();


    }
}
