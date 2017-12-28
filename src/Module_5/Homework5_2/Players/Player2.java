package Module_5.Homework5_2.Players;

import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Player2 extends Player1 {

    public Player2(double price) {
        super(price);
    }
    @Override
    public void playSong(Text text, ProgressBar progressBar) throws InterruptedException {
        text.setFill(Color.RED);
        text.setText("ERROR");
        text.setTranslateX(150);
        Thread.sleep(5000);
        text.setText(null);

    }


}
