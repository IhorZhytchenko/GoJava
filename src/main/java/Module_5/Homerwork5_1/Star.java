package Module_5.Homerwork5_1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Star extends Application {
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 700;

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
    void drawUI(Pane root) {
        Text text1 = new Text("X центра звезды:");
        text1.setTranslateX(120);
        text1.setTranslateY(25);
        text1.setFont(new Font(14));
        Text text2 = new Text("Y центра звезды:");
        text2.setTranslateX(290);
        text2.setTranslateY(25);
        text2.setFont(new Font(14));
        Text text3 = new Text("радиус звезды:");
        text3.setTranslateX(465);
        text3.setTranslateY(25);
        text3.setFont(new Font(14));

        TextField textField1 = new TextField();
        textField1.setTranslateX(230);
        textField1.setTranslateY(10);
        textField1.setMaxWidth(50);
        TextField textField2 = new TextField();
        textField2.setTranslateX(400);
        textField2.setTranslateY(10);
        textField2.setMaxWidth(50);
        TextField textField3 = new TextField();
        textField3.setTranslateX(565);
        textField3.setTranslateY(10);
        textField3.setMaxWidth(50);

        Button button1 = new Button("Draw Star");
        button1.setTranslateX(10);
        button1.setTranslateY(10);
        button1.setOnAction((event) -> {
            int radius = Integer.parseInt(textField3.getText());
            double x0 = Double.parseDouble(textField1.getText());
            double y0 = Double.parseDouble(textField2.getText());
            root.getChildren().clear();
            Canvas canvas = new Canvas(1000,700);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            root.getChildren().add(canvas);
            drawUI(root);
            drawStar(gc,radius,x0,y0);
        });



        root.getChildren().addAll(button1, text1,text2, text3, textField1, textField2, textField3);
    }


    private void drawStar(GraphicsContext gc, int radius, double x0,double y0) {
        double [] xpoints = new double[10];
        double [] ypoints = new double[10];
        int a = 18;
        for (int i = 0; i<xpoints.length;i++){
            if (i%2==0){
                xpoints[i]=x0+radius*Math.cos(Math.toRadians(a));
                ypoints[i]=y0-radius*Math.sin(Math.toRadians(a));
            } else {
                xpoints[i]=x0+(radius/2.5)*Math.cos(Math.toRadians(a));
                ypoints[i]=y0-(radius/2.5)*Math.sin(Math.toRadians(a));
            }
            a+=36;
        }

        gc.strokePolygon(xpoints, ypoints, xpoints.length);
    }


    public void start(Stage primaryStage) throws Exception {
        windowSetup(primaryStage);
        Pane root = new Pane();
        drawUI(root);
        primaryStage.setTitle("Star");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
