package Module_5.Homework5_2.Players;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public abstract class AbstractPlayer {
   protected String song1 = "The Best Song";

    public void setSong1(String song1) {
        this.song1 = song1;
    }

    public abstract void playSong(Text text, ProgressBar progressBar) throws InterruptedException;

    public abstract void show(Pane root);


        }
