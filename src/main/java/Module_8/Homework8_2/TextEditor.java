package Module_8.Homework8_2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sun.misc.IOUtils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TextEditor extends Application {
    static final int WINDOW_WIDTH = 800;
    static final int WINDOW_HEIGHT = 600;
    static ExecutorService service = Executors.newFixedThreadPool(1);
    static Task<Void> loadTask;
    static Task<Void> saveTask;
    static Task<Void> fibonacciTask;

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

        TextField textField1 = new TextField();
        textField1.setTranslateX(5);
        textField1.setTranslateY(30);
        textField1.setMinWidth(195);
        textField1.setPromptText("path for file download");

        TextField textField2 = new TextField();
        textField2.setTranslateX(205);
        textField2.setTranslateY(30);
        textField2.setMinWidth(195);
        textField2.setPromptText("path for file save");

        TextField textField3 = new TextField();
        textField3.setTranslateX(405);
        textField3.setTranslateY(30);
        textField3.setPromptText("fibonacci number");

        ProgressBar progressBar = new ProgressBar();
        progressBar.setTranslateX(600);
        progressBar.setTranslateY(30);


        Text text = new Text("Ready");
        text.setTranslateX(610);
        text.setTranslateY(20);
        text.setFont(new Font(16));


        TextArea textArea = new TextArea();
        textArea.setTranslateY(100);
        textArea.setMaxSize(750,450);
        textArea.setMinSize(750,450);

        Button button1 = new Button("  Load  ");
        button1.setTranslateX(80);
        button1.setTranslateY(60);
        button1.setOnAction((event) -> {
            String path = textField1.getText();
           loadTask = createLoadTask(path,textArea,progressBar,text);
            service.submit(loadTask);



        });
        Button button2 = new Button("  Save  ");
        button2.setTranslateX(280);
        button2.setTranslateY(60);
        button2.setOnAction((event) -> {
            String path = textField2.getText();
            saveTask = createSaveTask(path,textArea,progressBar,text);
            service.submit(saveTask);

        });
        Button button3 = new Button("Fibonacci");
        button3.setTranslateX(450);
        button3.setTranslateY(60);
        button3.setOnAction((event) -> {
            try {
                int x = Integer.parseInt(textField3.getText());
                if (x>0) {
                    String path = textField2.getText();
                    fibonacciTask = createFibonacciTask(x, path, progressBar, text);
                    service.submit(fibonacciTask);
                    progressBar.progressProperty().unbind();
                    progressBar.progressProperty().bind(fibonacciTask.progressProperty());
                } else{
                    showAlert("Не коректные данные!!!");
                }

            } catch (Exception e) {
                showAlert("Не коректные данные!!!");

            }

        });


        root.getChildren().addAll(button1,button2,button3,textArea,textField1,textField2,textField3,progressBar,text);
    }

     Task<Void> createFibonacciTask(int x, String path, ProgressBar progressBar, Text text) {
         Task<Void> result = new Task<Void>() {
             @Override
             protected Void call() throws Exception {
                text.setText("X searching");
                BigInteger [] fibonacci = new BigInteger[x];
                for (int i =0;i<x;i++){

                    if (i<2){
                        fibonacci[i]=BigInteger.valueOf(1);
                    } else {
                        fibonacci[i] = fibonacci[i - 1].add(fibonacci[i - 2]);
                    }
                    updateProgress(i, x);

                }
                 try(FileWriter writer = new FileWriter(path)) {
                     writer.write(Arrays.toString(fibonacci));
                     writer.flush();
                 } catch (Exception e) {
                     Platform.runLater(() -> {
                         showAlert("Не удалось сохранить файл");
                     });
                 }
                 text.setText("Ready");
                 return null;
             }
         };

        return result;
    }

    private Task<Void> createSaveTask(String path, TextArea textArea, ProgressBar progressBar,Text text) {
        String text1 =textArea.getText();
        Task<Void> result = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                text.setText("Saving");
                try(FileWriter writer = new FileWriter(path)) {
                    Platform.runLater(() -> {
                        progressBar.progressProperty().unbind();
                        progressBar.progressProperty().bind(saveTask.progressProperty());
                    });
                    writer.write(text1);
                    writer.flush();
                    updateProgress(1, 1);
                } catch (IOException e) {
                    Platform.runLater(() -> {
                        showAlert("Не удалось сохранить файл");
                    });

                }
                text.setText("Ready");
                return null;
            }
        } ;

        return result;

    }

    Task<Void> createLoadTask(String path, TextArea textArea,ProgressBar progressBar,Text text) {
        Task<Void> result = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                text.setText("Loading");
                Platform.runLater(() -> {
                    progressBar.progressProperty().unbind();
                    progressBar.progressProperty().bind(loadTask.progressProperty());
                });
                File file = new File(path);
                if (file.exists()) {
                    try {
                        textArea.setText(new String(Files.readAllBytes(Paths.get(path))));
                        updateProgress(1, 1);

                    } catch (Exception e) {
                        Platform.runLater(() -> {
                            showAlert("Не удалось загрузить файл");
                        });

                    }

                } else {
                    Platform.runLater(() -> {
                        showAlert("Файл не найден!!");
                    });
                }

                text.setText("Ready");
                return null;
            }
        };

        return result;
    }
    void showAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR!!!");
        alert.setHeaderText(s);
        alert.showAndWait();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        windowSetup(primaryStage);
        Pane root = new Pane();
        Pane pane = new Pane();
        pane.setTranslateY(80);
        root.getChildren().addAll(pane);
        drawUI(root,pane);

        primaryStage.setScene(new Scene(root,WINDOW_WIDTH,WINDOW_HEIGHT));
        primaryStage.show();

    }
    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }
}
