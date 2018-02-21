package Module_8.Homework8_2;

import com.alibaba.fastjson.JSON;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task3 extends Application {
    static final String PATH = "files/Module8/URL_IMG.txt";
    static final List<String> URL =createURL() ;
    static final int WINDOW_WIDTH = 520;
    static final int WINDOW_HEIGHT = 620;
    static ExecutorService service = Executors.newCachedThreadPool();
    static Random random = new Random();

    private static  List<String> createURL() {
        List<String> result = new ArrayList<String>();
        try (Stream<String> stream = Files.lines(Paths.get(PATH))) {

            result = stream
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

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

    void drawUI(Pane root,Pane pane) {


        Button button1 = new Button("Обновить");
        button1.setTranslateX(200);
        button1.setTranslateY(10);
        button1.setOnAction((event) -> {
            pane.getChildren().clear();
            update(pane);


        });

        root.getChildren().addAll(button1);
    }

     void update(Pane pane) {
         for(int i = 0; i < 5; i++) {
             for (int j=0;j<5;j++){
                 final int indexi = i;
                 final int indexj = j;
                 service.submit(() -> {
                    Image image = new Image(URL.get(random.nextInt(URL.size())),true);
                    ImageView imageView = new ImageView(image);
                     ProgressBar bar = new ProgressBar();
                     bar.progressProperty().bind(image.progressProperty());
                     Platform.runLater(() -> {
                         imageView.setTranslateX(indexj * 100);
                         imageView.setTranslateY(indexi*100);
                         imageView.setFitWidth(100);
                         imageView.setFitHeight(100);
                         bar.setTranslateX(indexj * 100);
                         bar.setTranslateY(indexi*100);

                         pane.getChildren().addAll(imageView,bar);
                 });
             });
         }}
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        windowSetup(primaryStage);
        Pane root = new Pane();
        Pane pane = new Pane();
        pane.setTranslateY(80);
        root.getChildren().addAll(pane);
        drawUI(root,pane);
        update(pane);
        primaryStage.setScene(new Scene(root,WINDOW_WIDTH,WINDOW_HEIGHT));
        primaryStage.show();

    }
    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }
}
