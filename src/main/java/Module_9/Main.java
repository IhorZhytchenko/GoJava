package Module_9;

import Module_9.ChannelEntity.ResponseChannel;
import Module_9.Entity.ResponseSearch;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main extends Application {
    static final int WINDOW_WIDTH = 950;
    static final int WINDOW_HEIGHT = 600;
    static final String URL_VIDEO ="http://www.youtube.com/embed/";
    static final String AUTOPLAY ="?autoplay=1";

    static ResponseSearch search;
    static ResponseChannel searchChannel;
    static  WebView webview = new WebView();
    static boolean isAdvance = false;


    public static void main(String[] args) throws UnirestException {
        launch(args); }

    void windowSetup(Stage primaryStage) {
        primaryStage.setWidth(WINDOW_WIDTH);
        primaryStage.setHeight(WINDOW_HEIGHT);

        primaryStage.setMaxWidth(WINDOW_WIDTH);
        primaryStage.setMaxHeight(WINDOW_HEIGHT);

        primaryStage.setMinWidth(WINDOW_WIDTH);
        primaryStage.setMinHeight(WINDOW_HEIGHT);
    }

    void drawUI(Pane root) {

        VBox vBox = new VBox();
        vBox.setMaxWidth(280);
        ScrollPane sp = new ScrollPane();
        sp.setContent(vBox);
        sp.setPrefSize(275,565);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        Pane pane = new Pane();
        pane.setTranslateY(150);
        pane.setTranslateX(280);

        Pane pane1 = new Pane();
        pane1.setTranslateY(70);
        pane1.setTranslateX(400);
        pane1.setMaxHeight(75);


        TextField textField1 = new TextField();
        textField1.setTranslateX(400);
        textField1.setTranslateY(40);
        textField1.setMinWidth(300);


        TextField textField2 = new TextField();
        textField2.setMinWidth(160);
        textField2.setPromptText("enter the number of results");



        TextField textField3 = new TextField();
        textField3.setTranslateY(30);
        textField3.setMinWidth(160);
        textField3.setPromptText("enter the number of days");




        Button button1 = new Button("Search");
        button1.setTranslateX(700);
        button1.setTranslateY(40);
        button1.setOnAction((event) -> {
            String query = textField1.getText();
            if (isAdvance){
                int maxResults =Integer.parseInt(textField2.getText());
                int days = Integer.parseInt(textField2.getText());
                try {
                    advansedSearch(query,maxResults,days,vBox,pane);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {

                 try {
                     simpleSearch(query,vBox,pane);
                 } catch (Exception e) {
                    e.printStackTrace();
                 }
            }


        });
        Button button2 = new Button("Advanced");
        button2.setTranslateX(760);
        button2.setTranslateY(40);
        button2.setOnAction((event) -> {
            if(isAdvance){
                isAdvance=false;
                pane1.getChildren().clear();
            } else {
                isAdvance = true;
                pane1.getChildren().addAll(textField2,textField3);

            }

        });




        root.getChildren().addAll(vBox,sp,button1,textField1,pane,button2,pane1);
    }

    private void advansedSearch(String query, int maxResults, int days, VBox vBox, Pane pane) throws InterruptedException, ExecutionException {

        Thread thread = new Thread(() -> {
            try {
                search = YouTubeSearch.advancedSearch(query,maxResults,days);
            } catch (UnirestException e) {
                e.printStackTrace();
            }

        });
        thread.start();
        thread.join();



        showResult(vBox,pane,search);
    }

    private void simpleSearch(String query, VBox vBox, Pane pane) throws ExecutionException, InterruptedException {
       Thread thread = new Thread(() -> {
           try {
               search = YouTubeSearch.simpleSearch(query);
           } catch (UnirestException e) {
               e.printStackTrace();
           }

       });
       thread.start();
       thread.join();



        showResult(vBox,pane,search);
    }

    private void showResult(VBox vBox, Pane pane, ResponseSearch search) throws ExecutionException, InterruptedException {
        vBox.getChildren().clear();
        for (int i =0;i<search.getItems().length;i++){
            Text name = new Text((i+1)+". Name - "+search.getItems()[i].getSnippet().getTitle());
            Text channelName = new Text("Chanel name - "+search.getItems()[i].getSnippet().getChannelTitle());
            channelName.setUserData(search.getItems()[i].getSnippet().getChannelId());
            channelName.setOnMouseClicked((event) ->
                    {
                        String id = (String) channelName.getUserData();
                        try {
                            channelSeach(id,vBox,pane);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                    }
            );
            Text date = new Text("Publication date - "+search.getItems()[i].getSnippet().getPublishedAt().substring(0,10));
            ImageView imageView = createImageView(search,i);
            Button button = new Button("View");
            button.setUserData(search.getItems()[i].getId().getVideoId());
            button.setOnAction((event) -> {
                String url = URL_VIDEO+button.getUserData()+AUTOPLAY;
                view(url,pane);

            });

            vBox.getChildren().addAll(name,channelName,date,imageView,button);
            vBox.getChildren().add(new Text("   "));
        }
    }

    private void channelSeach(String  id, VBox vBox, Pane pane) throws InterruptedException, ExecutionException {
        Thread thread = new Thread(() -> {
            try {
                searchChannel = YouTubeSearch.channelSearch(id);
                search=YouTubeSearch.SearchFromChannel(id);
            } catch (UnirestException e) {
                e.printStackTrace();
            }

        });
        thread.start();
        thread.join();



        showChannelResult(vBox,pane,search,searchChannel);

    }

    private void showChannelResult(VBox vBox, Pane pane, ResponseSearch search, ResponseChannel searchChannel) throws ExecutionException, InterruptedException {
        vBox.getChildren().clear();
        Text text = new Text("CHANNEL:");
        ImageView imageView = createChannelImageView(searchChannel);
        Text nameC = new Text("Name - "+searchChannel.getItems()[0].getSnippet().getTitle());
        Text description =new Text("Description - "+searchChannel.getItems()[0].getSnippet().getDescription());
        Text text1 = new Text("VIDEOS:");
        vBox.getChildren().addAll(text,imageView,nameC,description,text1);
        for (int i =0;i<search.getItems().length;i++){
            Text name = new Text((i+1)+". Name - "+search.getItems()[i].getSnippet().getTitle());
            Text channelName = new Text("Chanel name - "+search.getItems()[i].getSnippet().getChannelTitle());
            Text date = new Text("Publication date - "+search.getItems()[i].getSnippet().getPublishedAt().substring(0,10));
            Button button = new Button("View");
            button.setUserData(search.getItems()[i].getId().getVideoId());
            button.setOnAction((event) -> {
                String url = URL_VIDEO+button.getUserData()+AUTOPLAY;
                view(url,pane);

            });

            vBox.getChildren().addAll(name,channelName,date,button);
            vBox.getChildren().add(new Text("   "));
        }
    }

    private ImageView createChannelImageView(ResponseChannel searchChannel) throws ExecutionException, InterruptedException {
        FutureTask<ImageView> task = new FutureTask<ImageView>(() ->
        {
            Image image = new Image(searchChannel.getItems()[0].getSnippet().getThumbnails().getMedium().getUrl());
            ImageView result = new ImageView(image);
            return result;
        }
        );
        new Thread(task).start();

        return task.get();
    }

    private void view(String url, Pane pane) {
        webview.getEngine().load(url);
        webview.setPrefSize(640, 390);
        pane.getChildren().clear();
        pane.getChildren().add(webview);

    }

    private ImageView createImageView(ResponseSearch search,int i) throws ExecutionException, InterruptedException {
        FutureTask<ImageView> task = new FutureTask<ImageView>(() ->
        {
            Image image = new Image(search.getItems()[i].getSnippet().getThumbnails().getMedium().getUrl());
            ImageView result = new ImageView(image);
            return result;
        }
        );
        new Thread(task).start();

        return task.get();
    }

    @Override
    public void start(Stage  primaryStage) throws Exception {

        windowSetup(primaryStage);
        Pane root = new Pane();

        drawUI(root);
        primaryStage.setScene(new Scene(root,WINDOW_WIDTH,WINDOW_HEIGHT));
        primaryStage.show();
    }
}

