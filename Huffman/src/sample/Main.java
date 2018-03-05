package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private String text = "Een, twee, drie, vier\n" +
            "Hoedje van, hoedje van\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van papier";

    @Override
    public void start(Stage primaryStage) throws Exception{
        new Huffman(text);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
