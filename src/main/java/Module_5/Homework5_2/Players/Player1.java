package Module_5.Homework5_2.Players;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;


public class Player1 extends AbstractPlayer {
    protected final double price;

    public Player1(final double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void playSong(Text text, ProgressBar progressBar) throws InterruptedException {
        text.setText("Playing : "+ this.song1);
        for (int i =1;i<=100;i++){
            double progress = i/100.0;
            Platform.runLater(() -> progressBar.setProgress(progress));
            Thread.sleep(100);

        }
        text.setText(null);
        progressBar.setProgress(0);

    }

    @Override
    public void show(Pane root) {

        Text text1 = new Text(getClass().getSimpleName());
        text1.setTranslateX(140);
        text1.setTranslateY(35);
        text1.setFill(Color.BLUE);
        text1.setFont(new Font(32));

        Text text2 = new Text("Song : "+ this.song1);
        text2.setTranslateX(50);
        text2.setTranslateY(200);
        text2.setFill(Color.BLUE);
        text2.setFont(new Font(18));

        TextField textField = new TextField();
        textField.setTranslateX(50);
        textField.setTranslateY(230);
        textField.setPromptText("    enter song name   ");

        Button button1 = new Button("change song");
        button1.setTranslateX(75);
        button1.setTranslateY(260);
        button1.setStyle("-fx-background-color: blue");
        button1.setTextFill(Color.WHITE);
        button1.setOnAction((event) -> {
            this.setSong1(textField.getText());
            text2.setText("Song : "+ this.song1);
        });

        Text text3 = new Text();
        text3.setTranslateX(100);
        text3.setTranslateY(75);
        text3.setFill(Color.BLUE);
        text3.setFont(new Font(18));

        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setTranslateX(50);
        progressBar.setTranslateY(90);
        progressBar.setPrefWidth(290);

        Button button2 = new Button("playSong");
        button2.setTranslateX(160);
        button2.setTranslateY(110);
        button2.setStyle("-fx-background-color: blue");
        button2.setTextFill(Color.WHITE);
        button2.setOnAction((event) -> {
            new Thread(() -> {
                try {
                    playSong(text3,progressBar);

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }).start();

        });

        Text text4 = new Text();
        text4.setTranslateX(75);
        text4.setTranslateY(357);
        text4.setFill(Color.BLUE);
        text4.setFont(new Font(16));

        Button button3 = new Button("Get Price:");
        button3.setTranslateX(3);
        button3.setTranslateY(340);
        button3.setStyle("-fx-background-color: blue");
        button3.setTextFill(Color.WHITE);
        button3.setOnAction((event) -> {
            text4.setText("" + this.getPrice());

        });



        root.getChildren().addAll(text1,text2,text3,button2, textField,button1,progressBar,text4,button3);
    }
}
