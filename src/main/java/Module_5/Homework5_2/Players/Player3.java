package Module_5.Homework5_2.Players;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Player3 {
    protected final double price;
    protected String[] playlist = new String[] {"The best song", "Good song", "Super Song"};

    public Player3(final double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void playSong(Text text, ProgressBar progressBar) throws InterruptedException {
        play(text,progressBar,playlist[0]);
    }
    public void playAllSongs(Text text, ProgressBar progressBar) throws InterruptedException {
        for (int i =0; i<playlist.length;i++){
            play(text,progressBar,playlist[i]);
        }
    }

    protected void play(Text text, ProgressBar progressBar,String song) throws InterruptedException {
        text.setText("Playing : "+ song);
        for (int i =1;i<=100;i++){
            double progress = i/100.0;
            Platform.runLater(() -> progressBar.setProgress(progress));
            Thread.sleep(100);

        }
        text.setText(null);
        progressBar.setProgress(0);

    }

    protected String playlistToString (){
       String result ="\tPlaylist :" ;
       for (int i = 0;i<playlist.length;i++){
           result+="\n"+playlist[i];
       }
       return result;
    }

    public void addSong(String song) {
        int size = playlist.length +1;
        String [] copy = new String[size];
        System.arraycopy(playlist,0,copy,0,playlist.length);
        copy[size-1]=song;
        playlist = new String[size];
        System.arraycopy(copy,0,playlist,0,size);
    }

    public void show(Pane root) {

        Text text1 = new Text(getClass().getSimpleName());
        text1.setTranslateX(140);
        text1.setTranslateY(35);
        text1.setFill(Color.BLUE);
        text1.setFont(new Font(32));

        Text text2 = new Text(playlistToString());
        text2.setTranslateX(30);
        text2.setTranslateY(155);
        text2.setFill(Color.BLUE);
        text2.setFont(new Font(16));

        TextField textField = new TextField();
        textField.setTranslateX(230);
        textField.setTranslateY(190);
        textField.setPromptText("    enter song name   ");

        Button button1 = new Button("add song");
        button1.setTranslateX(265);
        button1.setTranslateY(220);
        button1.setStyle("-fx-background-color: blue");
        button1.setTextFill(Color.WHITE);
        button1.setOnAction((event) -> {
            addSong(textField.getText());
            text2.setText(playlistToString());
            textField.clear();
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
        button2.setTranslateX(110);
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

        Button button3 = new Button("playAllSongs");
        button3.setTranslateX(190);
        button3.setTranslateY(110);
        button3.setStyle("-fx-background-color: blue");
        button3.setTextFill(Color.WHITE);
        button3.setOnAction((event) -> {
            new Thread(() -> {
                try {
                    playAllSongs(text3,progressBar);

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

        Button button4 = new Button("Get Price:");
        button4.setTranslateX(3);
        button4.setTranslateY(340);
        button4.setStyle("-fx-background-color: blue");
        button4.setTextFill(Color.WHITE);
        button4.setOnAction((event) -> {
            text4.setText("" + this.getPrice());

        });



        root.getChildren().addAll(text1,text2,text3,button2, textField,button1,progressBar,text4,button3,button4);
    }


}



