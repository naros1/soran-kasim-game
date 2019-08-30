package Game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/resources/fxml/game/game.fxml"));
        Pane pane = loader.load();
        Scene scene = new Scene(pane, 600, 490);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Warcaby");
        stage.setResizable(false);
        stage.show();
    }



}
