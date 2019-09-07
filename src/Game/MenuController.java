package Game;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MenuController {
    private MainController mainController;

    @FXML
    public void newGame(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/resources/fxml/game/game.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameController gameController = loader.getController();
        gameController.setMainController(mainController);
        mainController.setScreen(pane);

    }
    @FXML
    public void loadPreviousGame(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/resources/fxml/game/game.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameController gameController = loader.getController();
        gameController.setMainController(mainController);
        mainController.setScreen(pane);

            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("previousGame.list"));
                Object readMap = ois.readObject();
                if(readMap instanceof GridPane) {
                    gameController.setGridPane((GridPane) readMap);
                }
                ois.close();
            } catch (Exception e) {
                System.out.println("Nie udało się wczytać");
            }

    }
    @FXML
    public void exit(){
        Platform.exit();
    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
