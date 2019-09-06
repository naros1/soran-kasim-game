package Game;
import javafx.fxml.FXML;

public class FinishGameController {
    private MainController mainController;

    @FXML
    public void ok(){
        mainController.loadMenuScreen();

    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
