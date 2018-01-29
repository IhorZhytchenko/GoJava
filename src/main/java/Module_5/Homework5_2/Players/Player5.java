package Module_5.Homework5_2.Players;

import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;

public class Player5 extends Player3 {
    public Player5(double price) {
        super(price);
    }

    @Override
    public void playAllSongs(Text text, ProgressBar progressBar) throws InterruptedException {
        for (int i =playlist.length-1; i>=0;i--){
            play(text,progressBar,playlist[i]);
        }
    }
}
