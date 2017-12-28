package Module_5.Homework5_2.Players;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.*;

public class Main extends Application {
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 400;

    public static void main(String[] args) {
        launch(args);
    }

    void windowSetup(Stage primaryStage) {
        primaryStage.setWidth(WINDOW_WIDTH);
        primaryStage.setHeight(WINDOW_HEIGHT);

        primaryStage.setMaxWidth(WINDOW_WIDTH);
        primaryStage.setMaxHeight(WINDOW_HEIGHT);

        primaryStage.setMinWidth(WINDOW_WIDTH);
        primaryStage.setMinHeight(WINDOW_HEIGHT);
    }

    void drawUI(Pane root) {
        Text text1 = new Text("Выберите плеер");
        text1.setTranslateX(120);
        text1.setTranslateY(25);
        text1.setFont(new Font(18));


        Button button1 = new Button("  Player1  ");
        button1.setTranslateX(100);
        button1.setTranslateY(90);
        button1.setOnAction((event) -> {
            root.getChildren().clear();
            creatPlayerUI(root,1);
        });

        Button button2 = new Button(" Player2  ");
        button2.setTranslateX(230);
        button2.setTranslateY(90);
        button2.setOnAction((event) -> {
            root.getChildren().clear();
            creatPlayerUI(root,2);
        });


        Button button3 = new Button("  Player3  ");
        button3.setTranslateX(100);
        button3.setTranslateY(130);
        button3.setOnAction((event) -> {
            root.getChildren().clear();
            creatPlayerUI(root,3);
        });

        Button button4 = new Button(" Player4  ");
        button4.setTranslateX(230);
        button4.setTranslateY(130);
        button4.setOnAction((event) -> {
            root.getChildren().clear();
            creatPlayerUI(root,4);
        });

        Button button5 = new Button("  Player5  ");
        button5.setTranslateX(100);
        button5.setTranslateY(170);
        button5.setOnAction((event) -> {
            root.getChildren().clear();
            creatPlayerUI(root,5);
        });

        Button button6 = new Button(" Player6  ");
        button6.setTranslateX(230);
        button6.setTranslateY(170);
        button6.setOnAction((event) -> {
            root.getChildren().clear();
            creatPlayerUI(root,6);
        });


        root.getChildren().addAll(button1,button2,button3, button4,button5,button6, text1);
    }

     void creatPlayerUI(Pane root, int number) {

         Text text2 = new Text("Для создания плеера укажите цену");
         text2.setTranslateX(50);
         text2.setTranslateY(50);
         text2.setFont(new Font(16));

         TextField textField = new TextField();
         textField.setTranslateX(110);
         textField.setTranslateY(170);
         textField.setPromptText("укажите цену");


         Button button = new Button("Создать плеер");
         button.setTranslateX(135);
         button.setTranslateY(200);
         button.setOnAction((event) -> {
             root.getChildren().clear();
             double price =Double.parseDouble(textField.getText());
             creatPlayer(root,number,price);
         });
         root.getChildren().addAll(button,text2,textField);

    }
    void creatPlayer(Pane root, int number, double price){
    switch (number){
        case 1 :
            Player1 player1 =new Player1(price);
            player1.show(root);
            break;
        case 2 :
            Player2 player2 =new Player2(price);
            player2.show(root);
            break;
        case 3 :
            Player3 player3 =new Player3(price);
            player3.show(root);
            break;
        case 4 :
            Player4 player4 =new Player4(price);
            player4.show(root);
            break;
        case 5 :
            Player5 player5 =new Player5(price);
            player5.show(root);
            break;
        case 6 :
            Player6 player6 =new Player6(price);
            player6.show(root);
            break;
    }

    }


    @Override
    public void start(Stage primaryStage)   {
        windowSetup(primaryStage);
        Pane root = new Pane();
        drawUI(root);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}
