package Game;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        Path file = Paths.get("C:\\Users\\Soran\\Documents\\Development\\KursJavaFx-master\\GameKodilla\\src\\resources\\fxml\\game\\previousGame.txt");

        try (Stream<String> stream = Files.lines(file)) {

            stream.forEach(gameController::setPreviousGame);
            List<String> freeList =  Files.lines(file)
                    .map(s->s.substring(4))
                    .filter(s->s.contains("free"))
                    .collect(Collectors.toList());
            gameController.assignFreePlacesCount(freeList);

        } catch (IOException e) {
            System.out.println("wystąpił błąd: " + e);
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
