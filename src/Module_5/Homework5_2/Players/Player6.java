package Module_5.Homework5_2.Players;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.Collections;

public class Player6 extends Player3 {
    public Player6(double price) {
        super(price);
    }

    public void shuffle(){
        Collections.shuffle(Arrays.asList(playlist));
    }


    @Override
    public void show(Pane root) {
        super.show(root);

        Text text = (Text) root.getChildren().get(1);


        Button button1 = new Button("shuffle");
        button1.setTranslateX(320);
        button1.setTranslateY(320);
        button1.setStyle("-fx-background-color: blue");
        button1.setTextFill(Color.WHITE);
        button1.setOnAction((event) -> {
            this.shuffle();
            text.setText(playlistToString());
        });

        root.getChildren().addAll(button1);
    }

    }
