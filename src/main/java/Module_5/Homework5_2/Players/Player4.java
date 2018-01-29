package Module_5.Homework5_2.Players;

import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;

public class Player4 extends Player3 {

    public Player4(double price) {
        super(price);
    }
    @Override
    public void playSong(Text text, ProgressBar progressBar) throws InterruptedException {
        play(text,progressBar,playlist[playlist.length-1]);
    }

}
