package Game;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;



import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class GameController {


    @FXML
    private GridPane gridPane;

    @FXML
    private Label label;




    private ImageView pickedId;
    private ImageView pickedOld;
    private Node pickedBlack;
    private Mouse mouseClicked = new Mouse(0, 0);
    private Mouse oldMouseClicked = new Mouse(0, 0);
    private ObjectsCounter killedWhite = new ObjectsCounter(0);
    private ObjectsCounter killedBlack = new ObjectsCounter(0);
    private ObjectsCounter freePlaces = new ObjectsCounter(8);
    private ObjectsCounter playerRoundCounter = new ObjectsCounter(0);
    private ObjectsCounter blackQueens = new ObjectsCounter(0);
    private int previousRoundNumber;
    private boolean isKillFrontRight;
    private boolean isKillFrontLeft;
    private boolean isKillBackRight;
    private boolean isKillBackLeft;
    private boolean noBlackQueen;
    private List<Node> blacksPawns = new ArrayList<>();
    private List<Node> anyBlacks = new ArrayList<>();
    private MainController mainController;
    private StringProperty propertyTextField = new SimpleStringProperty(this, "textPropert", "Nowa Gra!");





    @FXML
    public void initialize(){
        label.textProperty().bind(propertyTextField);




    }

    @FXML
    public void getClickedId(MouseEvent e) {

            if (pickedId != null ) {
                    pickedOld = pickedId;
                    checkForKill("b");
                    oldMouseClicked.setRowIndex(mouseClicked.getRowIndex());
                    oldMouseClicked.setColumnIndex(mouseClicked.getColumnIndex());
                    try {
                        pickedId = (ImageView) e.getTarget();
                    } catch (Exception e2) {
                        System.out.println("Wciskasz biale pole!");
                    }
                    Integer colIndex = GridPane.getColumnIndex(pickedId);
                    Integer rowIndex = GridPane.getRowIndex(pickedId);
                    mouseClicked.setColumnIndex(colIndex);
                    mouseClicked.setRowIndex(rowIndex);
                    System.out.println(oldMouseClicked.getColumnIndex() + " " + oldMouseClicked.getRowIndex());
                    System.out.println(mouseClicked.getColumnIndex() + " " + mouseClicked.getRowIndex());
                    System.out.println(pickedOld.getId());
                    System.out.println(pickedId.getId());
                    System.out.println(gridPane.getChildren().toString());
                    if (pickedOld.getId().contains("white") && pickedId.getId().contains("free")
                            && mouseClicked.getRowIndex() == oldMouseClicked.getRowIndex() - 1
                            && (oldMouseClicked.getColumnIndex() == mouseClicked.getColumnIndex() + 1
                            || oldMouseClicked.getColumnIndex() == mouseClicked.getColumnIndex() - 1)) {
                        GridPane.setColumnIndex(pickedOld, mouseClicked.getColumnIndex());
                        GridPane.setRowIndex(pickedOld, mouseClicked.getRowIndex());
                        GridPane.setColumnIndex(pickedId, oldMouseClicked.getColumnIndex());
                        GridPane.setRowIndex(pickedId, oldMouseClicked.getRowIndex());
                        previousRoundNumber = playerRoundCounter.getCountedObjects();
                        playerRoundCounter.setCountedObjects(previousRoundNumber+1);
                        changeToQueen(pickedOld);
                        pickedId = null;
                        pickedOld = null;


                    }
                    try {
                        if (pickedOld != null && pickedOld.getId().contains("white") && pickedId.getId().contains("b")) {
                            if (isKillFrontRight) {
                                killingFrontRight(pickedOld);
                                checkForKill("b");
                                killBlack(pickedOld);
                                playerRoundCounter.setCountedObjects(previousRoundNumber + 1);
                                changeToQueen(pickedOld);
                                pickedId = null;
                                pickedOld = null;


                            }
                            if (isKillFrontLeft) {
                                killingFrontLeft(pickedOld);
                                checkForKill("b");
                                killBlack(pickedOld);
                                playerRoundCounter.setCountedObjects(previousRoundNumber + 1);
                                changeToQueen(pickedOld);
                                pickedId = null;
                                pickedOld = null;


                            }
                            if (isKillBackRight) {
                                killingBackRight(pickedOld);
                                checkForKill("b");
                                killBlack(pickedOld);
                                playerRoundCounter.setCountedObjects(previousRoundNumber + 1);
                                changeToQueen(pickedOld);
                                pickedId = null;
                                pickedOld = null;


                            }
                            if (isKillBackLeft) {
                                killingBackLeft(pickedOld);
                                checkForKill("b");
                                killBlack(pickedOld);
                                playerRoundCounter.setCountedObjects(previousRoundNumber + 1);
                                changeToQueen(pickedOld);
                                pickedId = null;
                                pickedOld = null;

                            }


                        }
                    }catch (Exception e2){
                        System.out.println("wykonałeś ruch!");
                    }
                    try {
                        if (pickedOld != null && pickedOld.getId().contains("wqueen")) {
                            moveOrKillWhiteQueen(pickedOld, pickedId);
                            playerRoundCounter.setCountedObjects(previousRoundNumber + 1);
                            pickedId = null;
                            pickedOld = null;
                        }
                    } catch (Exception e4){
                        System.out.println("Nie wybrano damy!");
                    }






            } else {
                try {
                    pickedId = (ImageView) e.getTarget();
                    Integer colIndex = GridPane.getColumnIndex(pickedId);
                    Integer rowIndex = GridPane.getRowIndex(pickedId);
                    mouseClicked.setColumnIndex(colIndex);
                    mouseClicked.setRowIndex(rowIndex);
                } catch (Exception e1) {
                    System.out.println("Wciskasz białe pole");

                }

            }

        if (playerRoundCounter.getCountedObjects().equals(previousRoundNumber+1)) {
            anyBlacks.clear();
            List<Node> anyObjects = gridPane.getChildren();
            anyObjects.stream()
                    .filter(s -> s.getId().contains("b"))
                    .forEach(anyBlacks::add);
            if (anyBlacks.size()==0){
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/resources/fxml/game/gameEnd.fxml"));
                Pane pane = null;
                try {
                    pane = loader.load();
                } catch (IOException e8) {
                    e8.printStackTrace();
                }
                FinishGameController finishGameController = loader.getController();
                finishGameController.setMainController(mainController);
                mainController.setScreen(pane);
            }
            blacksPawns.clear();
            List<Node> allObjects = gridPane.getChildren();
            allObjects.stream()
                    .filter(s -> s.getId().contains("black"))
                    .forEach(blacksPawns::add);
            if (playerRoundCounter.getCountedObjects().equals(previousRoundNumber+1)
                    && playerRoundCounter.getCountedObjects() % 2 == 1
                    && blackQueens.getCountedObjects() > 0) {
                randomMoveOrKillQueen(gridPane);
                previousRoundNumber = playerRoundCounter.getCountedObjects();
                noBlackQueen = false;
            } else {
                noBlackQueen = true;
            }
            if (playerRoundCounter.getCountedObjects().equals(previousRoundNumber+1)
                    && blacksPawns.size()==0) {
                randomMoveOrKillQueen(gridPane);
                previousRoundNumber = playerRoundCounter.getCountedObjects();
                noBlackQueen = false;
            } else {
                noBlackQueen = true;
            }
            if (noBlackQueen&& blacksPawns.size()!=0) {
                try {
                    pickedBlack = chooseRandomBlackKilling(gridPane);
                    if (pickedBlack != null) {
                        isBlackHaveKill(pickedBlack);
                        if (isKillBackLeft) {
                            killingBackLeft(pickedBlack);
                            isBlackHaveKill(pickedBlack);
                            killWhite(pickedBlack);
                            previousRoundNumber = playerRoundCounter.getCountedObjects();
                            changeToQueen(pickedBlack);
                            pickedBlack = null;


                        }
                        if (isKillBackRight) {
                            killingBackRight(pickedBlack);
                            isBlackHaveKill(pickedBlack);
                            killWhite(pickedBlack);
                            previousRoundNumber = playerRoundCounter.getCountedObjects();
                            changeToQueen(pickedBlack);
                            pickedBlack = null;


                        }
                        if (isKillFrontLeft) {
                            killingFrontLeft(pickedBlack);
                            isBlackHaveKill(pickedBlack);
                            killWhite(pickedBlack);
                            previousRoundNumber = playerRoundCounter.getCountedObjects();
                            changeToQueen(pickedBlack);
                            pickedBlack = null;


                        }
                        if (isKillFrontRight) {
                            killingFrontRight(pickedBlack);
                            isBlackHaveKill(pickedBlack);
                            killWhite(pickedBlack);
                            previousRoundNumber = playerRoundCounter.getCountedObjects();
                            changeToQueen(pickedBlack);
                            pickedBlack = null;


                        }


                    }
                } catch (Exception e3) {
                    moveBlack(gridPane);
                    System.out.println("runda czarnego");
                }
            }
        }else {
            System.out.println("Nie mozesz sie ruszyc!");
        }
        System.out.println("round : " + playerRoundCounter.getCountedObjects() + "| round poprzednia : " + previousRoundNumber);
        winningOrLoseingStatus();


    }
    private Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

    private Node chooseRandomBlackToMove(GridPane gridPaneLoaded) {
        Node result;
        List<Node> allObjects = gridPaneLoaded.getChildren();
        List<Node> blacks = new ArrayList<>();
        allObjects.stream()
                .filter(s -> s.getId().contains("black"))
                .forEach(blacks::add);

        Random randomBlack = new Random();
        if (blacks.size()>1) {
            System.out.println("Liczba czarnych : " + blacks.size());
            int randomId = randomBlack.nextInt(blacks.size());
            result = blacks.get(randomId);
        }else {
            result = blacks.get(0);
        }

        return result;
    }

    private Node chooseRandomBlackKilling(GridPane gridPaneLoaded) {
        Node result;
        List<Node> allObjects = gridPaneLoaded.getChildren();
        List<Node> blacksHavingKill = new ArrayList<>();
        allObjects.stream()
                .filter(s -> s.getId().contains("black"))
                .filter(s-> isBlackHaveKill(s).equals("tak"))
                .forEach(blacksHavingKill::add);

        Random randomBlack = new Random();
        if (blacksHavingKill.size()>1) {
            int randomId = randomBlack.nextInt(blacksHavingKill.size());
            result = blacksHavingKill.get(randomId);
        }else {
            result = blacksHavingKill.get(0);
        }
        System.out.println("Jest bicie !!!!! Liczba czarnych mającyhc bicie : " + blacksHavingKill.size());

        return result;
    }

    private void moveBlack(GridPane gridPaneLoaded){
        Node chosenBlack;
        int column;
        int row;
        int oldRow;
        int oldColumn;
        boolean isFreeBackLeft;
        boolean isFreeBackRight;
        int loopCounter = 0;
        Node autoPickedNew;
        Node autoPickedOld;

        while (true) {
            chosenBlack = chooseRandomBlackToMove(gridPaneLoaded);
            column = GridPane.getColumnIndex(chosenBlack);
            row = GridPane.getRowIndex(chosenBlack);
            try {
                isFreeBackLeft = getNodeByRowColumnIndex(row+1, column -1, gridPaneLoaded).getId().contains("free");
            } catch (Exception e){
                System.out.println("tylko po jednej stronie jest pole");
                isFreeBackLeft = false;
            }
            try {
                isFreeBackRight= getNodeByRowColumnIndex(row+1, column +1, gridPaneLoaded).getId().contains("free");
            }catch (Exception e){
                System.out.println("tylko po jednej stronie jest pole");
                isFreeBackRight = false;
            }

            if (isFreeBackLeft || isFreeBackRight) {
                if (isFreeBackRight) {
                    autoPickedOld = chosenBlack;
                    autoPickedNew = getNodeByRowColumnIndex(row + 1, column + 1, gridPaneLoaded);
                } else {
                    autoPickedOld = chosenBlack;
                    autoPickedNew = getNodeByRowColumnIndex(row + 1, column - 1, gridPaneLoaded);
                }
                oldColumn = GridPane.getColumnIndex(autoPickedOld);
                oldRow = GridPane.getRowIndex(autoPickedOld);
                System.out.println("auto old :" + autoPickedOld + "| auto new : " +autoPickedNew);
                GridPane.setColumnIndex(autoPickedOld, GridPane.getColumnIndex(autoPickedNew));
                GridPane.setRowIndex(autoPickedOld, GridPane.getRowIndex(autoPickedNew));
                GridPane.setColumnIndex(autoPickedNew, oldColumn);
                GridPane.setRowIndex(autoPickedNew, oldRow);

                previousRoundNumber = playerRoundCounter.getCountedObjects();
                changeToQueen(autoPickedOld);
                break;


            }
            loopCounter++;
            if (loopCounter >1000){
                System.out.println("Zostało zrobionych 1000 pętli !!" + loopCounter);
                previousRoundNumber = playerRoundCounter.getCountedObjects();
                break;
            }

        }


    }

    private ImageView addCountersAndFreePlacesAfterBlackKill(){
        freePlaces.setCountedObjects(freePlaces.getCountedObjects()+1);
        killedBlack.setCountedObjects(killedBlack.getCountedObjects()+1);
        ImageView imageView = new ImageView();
        imageView.setFitWidth(50.0);
        imageView.setFitHeight(50.0);
        String newId = "free"+freePlaces.getCountedObjects();
        imageView.setPreserveRatio(true);
        imageView.setPickOnBounds(true);
        imageView.setId(newId);
        return imageView;
    }


    private void autoMultipleKill(String colorKilling, String colorToKill, Node picked) {
        boolean condition1 ;
        boolean condition2 ;
        boolean condition3 ;
        boolean condition4 ;

        if (colorKilling.equals("white") && colorToKill.equals("b")){
             condition1 = isKillFrontRight;
             condition2 = isKillFrontLeft;
             condition3 = isKillBackRight;
             condition4 = isKillBackLeft;

        } else {
            condition4 = isKillFrontRight;
            condition3 = isKillFrontLeft;
            condition2 = isKillBackRight;
            condition1 = isKillBackLeft;

        }


        System.out.println(isKillBackLeft + "" + isKillBackRight + "" + isKillFrontLeft + "" + isKillFrontRight);

        if (picked.getId().contains(colorKilling)) {
            while (isKillFrontLeft || isKillFrontRight || isKillBackLeft || isKillBackRight) {
                if (condition1) {

                    if (colorKilling.equals("white")) {
                        killingFrontRight(picked);

                        condition2 = false;
                        condition3 = false;
                        condition4 =false;


                    } else {
                        killingBackLeft(picked);

                        condition2 = false;
                        condition3 = false;
                        condition4 =false;

                    }
                    System.out.println("pętla 1 !!!");





                }


                if (condition2) {

                    if (colorKilling.equals("white")) {
                        killingFrontLeft(picked);

                        condition3 = false;
                        condition4 =false;


                    } else {
                        killingBackRight(picked);

                        condition3 = false;
                        condition4 =false;

                    }
                    System.out.println("pętla 2 !!!");




                }


                if (condition3) {


                    if (colorKilling.equals("white")) {
                        killingBackRight(picked);

                        condition4 =false;


                    } else {
                        killingFrontLeft(picked);

                        condition4 =false;

                    }
                    System.out.println("pętla 3 !!!");



                }

                if (condition4) {

                    if (colorKilling.equals("white")) {
                        killingBackLeft(picked);



                    } else {
                        killingFrontRight(picked);


                    }
                    System.out.println("pętla 4 !!!");
                }
                if (picked.getId().contains("white")) {
                    checkForKill(colorToKill);
                    condition1 = isKillFrontRight;
                    condition2 = isKillFrontLeft;
                    condition3 = isKillBackRight;
                    condition4 = isKillBackLeft;
                }else {
                    isBlackHaveKill(picked);
                    condition4 = isKillFrontRight;
                    condition3 = isKillFrontLeft;
                    condition2 = isKillBackRight;
                    condition1 = isKillBackLeft;
                }
                if (!isKillFrontRight&&!isKillFrontLeft&&!isKillBackLeft&&!isKillBackRight){
                    break;
                }

            }
        }
    }

    private void checkForKill(String color){
        try {
            isKillBackRight = (getNodeByRowColumnIndex(GridPane.getRowIndex(pickedOld) + 1, GridPane.getColumnIndex(pickedOld) + 1, gridPane).getId().contains(color)
                    && getNodeByRowColumnIndex(GridPane.getRowIndex(pickedOld) + 2, GridPane.getColumnIndex(pickedOld) + 2, gridPane).getId().contains("free"));
        } catch (Exception e){
            isKillBackRight = false;
        }

        try {
            isKillBackLeft = (getNodeByRowColumnIndex(GridPane.getRowIndex(pickedOld) + 1, GridPane.getColumnIndex(pickedOld) - 1, gridPane).getId().contains(color)
                    && getNodeByRowColumnIndex(GridPane.getRowIndex(pickedOld) + 2, GridPane.getColumnIndex(pickedOld) - 2, gridPane).getId().contains("free"));
        } catch (Exception e){
            isKillBackLeft = false;
        }

        try {
            isKillFrontRight = (getNodeByRowColumnIndex(GridPane.getRowIndex(pickedOld) - 1, GridPane.getColumnIndex(pickedOld) + 1, gridPane).getId().contains(color)
                    && getNodeByRowColumnIndex(GridPane.getRowIndex(pickedOld) - 2, GridPane.getColumnIndex(pickedOld) + 2, gridPane).getId().contains("free"));
        } catch (Exception e){
            isKillFrontRight = false;
        }

        try {
            isKillFrontLeft = (getNodeByRowColumnIndex(GridPane.getRowIndex(pickedOld) - 1, GridPane.getColumnIndex(pickedOld) - 1, gridPane).getId().contains(color)
                    && getNodeByRowColumnIndex(GridPane.getRowIndex(pickedOld) - 2, GridPane.getColumnIndex(pickedOld) - 2, gridPane).getId().contains("free"));
        } catch (Exception e){
            isKillFrontLeft = false;
        }

    }

    private String isBlackHaveKill(Node pickedBlack){
        String result;
        try {
            isKillBackRight = (getNodeByRowColumnIndex(GridPane.getRowIndex(pickedBlack) + 1, GridPane.getColumnIndex(pickedBlack) + 1, gridPane).getId().contains("w")
                    && getNodeByRowColumnIndex(GridPane.getRowIndex(pickedBlack) + 2, GridPane.getColumnIndex(pickedBlack) + 2, gridPane).getId().contains("free"));
        } catch (Exception e){
            isKillBackRight = false;
        }

        try {
            isKillBackLeft = (getNodeByRowColumnIndex(GridPane.getRowIndex(pickedBlack) + 1, GridPane.getColumnIndex(pickedBlack) - 1, gridPane).getId().contains("w")
                    && getNodeByRowColumnIndex(GridPane.getRowIndex(pickedBlack) + 2, GridPane.getColumnIndex(pickedBlack) - 2, gridPane).getId().contains("free"));
        } catch (Exception e){
            isKillBackLeft = false;
        }

        try {
            isKillFrontRight = (getNodeByRowColumnIndex(GridPane.getRowIndex(pickedBlack) - 1, GridPane.getColumnIndex(pickedBlack) + 1, gridPane).getId().contains("w")
                    && getNodeByRowColumnIndex(GridPane.getRowIndex(pickedBlack) - 2, GridPane.getColumnIndex(pickedBlack) + 2, gridPane).getId().contains("free"));
        } catch (Exception e){
            isKillFrontRight = false;
        }

        try {
            isKillFrontLeft = (getNodeByRowColumnIndex(GridPane.getRowIndex(pickedBlack) - 1, GridPane.getColumnIndex(pickedBlack) - 1, gridPane).getId().contains("w")
                    && getNodeByRowColumnIndex(GridPane.getRowIndex(pickedBlack) - 2, GridPane.getColumnIndex(pickedBlack) - 2, gridPane).getId().contains("free"));
        } catch (Exception e){
            isKillFrontLeft = false;
        }
        if (isKillBackLeft||isKillBackRight||isKillFrontLeft||isKillFrontRight){
            result = "tak";
            System.out.println("Znaleziono bicie : " + pickedBlack.getId());
            System.out.println(isKillBackLeft + "" + isKillBackRight + "" + isKillFrontLeft + "" + isKillFrontRight);
        }else {
            result = "nie";
        }
        return result;


    }

    private void killBlack(Node picked){
        checkForKill("b");
        autoMultipleKill("white", "b", picked);

    }
    private void killWhite(Node picked) {
        isBlackHaveKill(picked);
        autoMultipleKill("black", "w", picked);
    }


    private void killingFrontRight(Node picked){
        try {
            if (getNodeByRowColumnIndex(GridPane.getRowIndex(picked) - 2, GridPane.getColumnIndex(picked) + 2, gridPane)!=null) {
                ImageView imageView = addCountersAndFreePlacesAfterBlackKill();
                gridPane.getChildren().remove(getNodeByRowColumnIndex(GridPane.getRowIndex(picked) - 1, GridPane.getColumnIndex(picked) + 1, gridPane));
                GridPane.setConstraints(imageView, GridPane.getColumnIndex(picked) + 1, GridPane.getRowIndex(picked) - 1);
                gridPane.getChildren().add(imageView);
                GridPane.setConstraints(getNodeByRowColumnIndex(GridPane.getRowIndex(picked) - 2, GridPane.getColumnIndex(picked) + 2, gridPane), GridPane.getColumnIndex(picked), GridPane.getRowIndex(picked));
                GridPane.setConstraints(picked, GridPane.getColumnIndex(picked) + 2, GridPane.getRowIndex(picked) - 2);
            }
        } catch (Exception e){
            System.out.println("Nie da się bić w prawo przód");
        }
    }

    private void killingFrontLeft(Node picked) {
        try {
            if (getNodeByRowColumnIndex(GridPane.getRowIndex(picked) - 2, GridPane.getColumnIndex(picked) - 2, gridPane)!=null) {
                ImageView imageView = addCountersAndFreePlacesAfterBlackKill();
                gridPane.getChildren().remove(getNodeByRowColumnIndex(GridPane.getRowIndex(picked) - 1, GridPane.getColumnIndex(picked) - 1, gridPane));
                GridPane.setConstraints(imageView, GridPane.getColumnIndex(picked) - 1, GridPane.getRowIndex(picked) - 1);
                gridPane.getChildren().add(imageView);
                GridPane.setConstraints(getNodeByRowColumnIndex(GridPane.getRowIndex(picked) - 2, GridPane.getColumnIndex(picked) - 2, gridPane), GridPane.getColumnIndex(picked), GridPane.getRowIndex(picked));
                GridPane.setConstraints(picked, GridPane.getColumnIndex(picked) - 2, GridPane.getRowIndex(picked) - 2);
            }
        } catch (Exception e) {
            System.out.println("Nie da się bić w lewo przód");
        }
    }

    private void killingBackRight(Node picked){
        try {
            if (getNodeByRowColumnIndex(GridPane.getRowIndex(picked) + 2, GridPane.getColumnIndex(picked) + 2, gridPane)!=null) {
                ImageView imageView = addCountersAndFreePlacesAfterBlackKill();
                gridPane.getChildren().remove(getNodeByRowColumnIndex(GridPane.getRowIndex(picked) + 1, GridPane.getColumnIndex(picked) + 1, gridPane));
                GridPane.setConstraints(imageView, GridPane.getColumnIndex(picked) + 1, GridPane.getRowIndex(picked) + 1);
                gridPane.getChildren().add(imageView);
                GridPane.setConstraints(getNodeByRowColumnIndex(GridPane.getRowIndex(picked) + 2, GridPane.getColumnIndex(picked) + 2, gridPane), GridPane.getColumnIndex(picked), GridPane.getRowIndex(picked));
                GridPane.setConstraints(picked, GridPane.getColumnIndex(picked) + 2, GridPane.getRowIndex(picked) + 2);
            }
        } catch (Exception e){
            System.out.println("Nie da się bić w prawo tył");
        }
    }

    private void killingBackLeft(Node picked){
        try {
            if (getNodeByRowColumnIndex(GridPane.getRowIndex(picked) + 2, GridPane.getColumnIndex(picked) - 2, gridPane)!=null) {
                ImageView imageView = addCountersAndFreePlacesAfterBlackKill();
                gridPane.getChildren().remove(getNodeByRowColumnIndex(GridPane.getRowIndex(picked) + 1, GridPane.getColumnIndex(picked) - 1, gridPane));
                GridPane.setConstraints(imageView, GridPane.getColumnIndex(picked) - 1, GridPane.getRowIndex(picked) + 1);
                gridPane.getChildren().add(imageView);
                GridPane.setConstraints(getNodeByRowColumnIndex(GridPane.getRowIndex(picked) + 2, GridPane.getColumnIndex(picked) - 2, gridPane), GridPane.getColumnIndex(picked), GridPane.getRowIndex(picked));
                GridPane.setConstraints(picked, GridPane.getColumnIndex(picked) - 2, GridPane.getRowIndex(picked) + 2);
            }
        } catch (Exception e) {
            System.out.println("Nie da się bić w lewa tył");
        }
    }
    private void changeToQueen(Node picked){
        ImageView pickedToChange = (ImageView) picked;
        if (pickedToChange.getId().contains("white")){
            if (GridPane.getRowIndex(pickedToChange)==0){
                Image whiteQueen = new Image("resources/fxml/game/biala_dama.png");
                pickedToChange.setImage(whiteQueen);
                pickedToChange.setId("wqueen"+pickedToChange.getId().substring(5));
            }
        }
        if (pickedToChange.getId().contains("black")){
            if (GridPane.getRowIndex(pickedToChange)==7){
                Image blackQueen = new Image("resources/fxml/game/czarna_dama.png");
                pickedToChange.setImage(blackQueen);
                pickedToChange.setId("bqueen"+pickedToChange.getId().substring(5));
                blackQueens.setCountedObjects(blackQueens.getCountedObjects()+1);
            }
        }
    }

    private void moveOrKillWhiteQueen(Node pickedQueen, Node pickedField) {
        int loops;
        int deltaRows;
        int deltaColumns;
        loops = Math.abs(GridPane.getRowIndex(pickedField) - GridPane.getRowIndex(pickedQueen));

        System.out.println("Liczba pętli do wykonania : "+ loops);
        if (Math.abs(GridPane.getRowIndex(pickedField) - GridPane.getRowIndex(pickedQueen)) ==
                Math.abs(GridPane.getColumnIndex(pickedField) - GridPane.getColumnIndex(pickedQueen))) {
            System.out.println("Warunek pierwszy równosci ok !");
            for (int i = 0; i < loops; i++) {
                deltaColumns = GridPane.getColumnIndex(pickedField) - GridPane.getColumnIndex(pickedQueen);
                deltaRows = GridPane.getRowIndex(pickedField) - GridPane.getRowIndex(pickedQueen);
                System.out.println("Delta rows : "+ deltaRows + "| Delta columns : "+deltaColumns );
                if (deltaRows > 0
                        && deltaColumns > 0) {

                    if (getNodeByRowColumnIndex(GridPane.getRowIndex(pickedQueen) + 1, GridPane.getColumnIndex(pickedQueen) + 1, gridPane).getId().contains("free")) {
                        GridPane.setConstraints(getNodeByRowColumnIndex(GridPane.getRowIndex(pickedQueen) + 1, GridPane.getColumnIndex(pickedQueen) + 1, gridPane), GridPane.getColumnIndex(pickedQueen), GridPane.getRowIndex(pickedQueen));
                        GridPane.setConstraints(pickedQueen, GridPane.getColumnIndex(pickedQueen) + 1, GridPane.getRowIndex(pickedQueen) + 1);
                        System.out.println("Ruch prawo tyl");
                    }else if ((getNodeByRowColumnIndex(GridPane.getRowIndex(pickedQueen) + 1, GridPane.getColumnIndex(pickedQueen) + 1, gridPane).getId().contains("black")
                            || getNodeByRowColumnIndex(GridPane.getRowIndex(pickedQueen) + 1, GridPane.getColumnIndex(pickedQueen) + 1, gridPane).getId().contains("bqueen"))
                            && getNodeByRowColumnIndex(GridPane.getRowIndex(pickedQueen) + 2, GridPane.getColumnIndex(pickedQueen) + 2, gridPane).getId().contains("free")) {
                        killingBackRight(pickedQueen);
                        i++;
                        System.out.println("Bicie prawo tyl");
                    }

                }
                if (deltaRows > 0
                        && deltaColumns < 0) {

                    if (getNodeByRowColumnIndex(GridPane.getRowIndex(pickedQueen) + 1, GridPane.getColumnIndex(pickedQueen) - 1, gridPane).getId().contains("free")) {
                        GridPane.setConstraints(getNodeByRowColumnIndex(GridPane.getRowIndex(pickedQueen) + 1, GridPane.getColumnIndex(pickedQueen) - 1, gridPane), GridPane.getColumnIndex(pickedQueen), GridPane.getRowIndex(pickedQueen));
                        GridPane.setConstraints(pickedQueen, GridPane.getColumnIndex(pickedQueen) - 1, GridPane.getRowIndex(pickedQueen) + 1);
                        System.out.println("Ruch lewo tyl");
                    }else if ((getNodeByRowColumnIndex(GridPane.getRowIndex(pickedQueen) + 1, GridPane.getColumnIndex(pickedQueen) - 1, gridPane).getId().contains("black")
                            || getNodeByRowColumnIndex(GridPane.getRowIndex(pickedQueen) + 1, GridPane.getColumnIndex(pickedQueen) - 1, gridPane).getId().contains("bqueen"))
                            && getNodeByRowColumnIndex(GridPane.getRowIndex(pickedQueen) + 2, GridPane.getColumnIndex(pickedQueen) - 2, gridPane).getId().contains("free")) {
                        killingBackLeft(pickedQueen);
                        i++;
                        System.out.println("Bicie lewo tyl");
                    }



                }
                if (deltaRows < 0
                        && deltaColumns < 0) {

                    if (getNodeByRowColumnIndex(GridPane.getRowIndex(pickedQueen) - 1, GridPane.getColumnIndex(pickedQueen) - 1, gridPane).getId().contains("free")) {
                        GridPane.setConstraints(getNodeByRowColumnIndex(GridPane.getRowIndex(pickedQueen) - 1, GridPane.getColumnIndex(pickedQueen) - 1, gridPane), GridPane.getColumnIndex(pickedQueen), GridPane.getRowIndex(pickedQueen));
                        GridPane.setConstraints(pickedQueen, GridPane.getColumnIndex(pickedQueen) - 1, GridPane.getRowIndex(pickedQueen) - 1);
                        System.out.println("Ruch lewo przod");
                    }else if ((getNodeByRowColumnIndex(GridPane.getRowIndex(pickedQueen) - 1, GridPane.getColumnIndex(pickedQueen) - 1, gridPane).getId().contains("black")
                            || getNodeByRowColumnIndex(GridPane.getRowIndex(pickedQueen) - 1, GridPane.getColumnIndex(pickedQueen) - 1, gridPane).getId().contains("bqueen"))
                            && getNodeByRowColumnIndex(GridPane.getRowIndex(pickedQueen) - 2, GridPane.getColumnIndex(pickedQueen) - 2, gridPane).getId().contains("free")) {
                        killingFrontLeft(pickedQueen);
                        i++;
                        System.out.println("Bicie lewo przod");
                    }

                }

                if (deltaRows < 0
                        && deltaColumns > 0) {

                    if (getNodeByRowColumnIndex(GridPane.getRowIndex(pickedQueen) - 1, GridPane.getColumnIndex(pickedQueen) + 1, gridPane).getId().contains("free")) {
                        GridPane.setConstraints(getNodeByRowColumnIndex(GridPane.getRowIndex(pickedQueen) - 1, GridPane.getColumnIndex(pickedQueen) + 1, gridPane), GridPane.getColumnIndex(pickedQueen), GridPane.getRowIndex(pickedQueen));
                        GridPane.setConstraints(pickedQueen, GridPane.getColumnIndex(pickedQueen) + 1, GridPane.getRowIndex(pickedQueen) - 1);
                        System.out.println("Ruch prawo przod");
                    }else if ((getNodeByRowColumnIndex(GridPane.getRowIndex(pickedQueen) - 1, GridPane.getColumnIndex(pickedQueen) + 1, gridPane).getId().contains("black")
                            || getNodeByRowColumnIndex(GridPane.getRowIndex(pickedQueen) - 1, GridPane.getColumnIndex(pickedQueen) + 1, gridPane).getId().contains("bqueen"))
                            && getNodeByRowColumnIndex(GridPane.getRowIndex(pickedQueen) - 2, GridPane.getColumnIndex(pickedQueen) + 2, gridPane).getId().contains("free")) {
                        killingFrontRight(pickedQueen);
                        i++;
                        System.out.println("Bicie prawo przod");
                    }



                }
                System.out.println("Wykonano pentli : "+ i);
            }


        }
    }
    private QueenWithHighestKills chooseRandomBlackQueen(GridPane gridPaneLoaded) {

        Node blackQueenAnalyzed;
        Node blackQueenWithHighestKills = null;
        Node analyzedFieldLeftFront;
        Node analyzedFieldRightFront;
        Node analyzedFieldLeftBack;
        Node analyzedFieldRightBack;
        int blackQueenAnalyzedKills;
        int blackQueenKills=0;
        int blackQueenHighestKills=0;
        int blackQueenAnalyzedRow;
        int blackQueenAnalyzedColumn;
        int i=0;
        String directoinQueenWithHighestKills="nic";
        String directoinAnalyzedQueen="nic";
        QueenWithHighestKills choosenQueenWithHighestKills =new QueenWithHighestKills(blackQueenWithHighestKills, directoinQueenWithHighestKills);
        List<Node> allObjects = gridPaneLoaded.getChildren();
        List<Node> blacksQueens = new ArrayList<>();
        allObjects.stream()
                .filter(s -> s.getId().contains("bqueen"))
                .forEach(blacksQueens::add);

        if (blacksQueens.size()>0){
            Random randomArrayId = new Random();
            System.out.println("Liczba dam w kolekcji : " + blacksQueens.size());
            while (i<blacksQueens.size()){
                blackQueenAnalyzedKills=0;
                blackQueenAnalyzed = blacksQueens.get(i);
                blackQueenAnalyzedRow = GridPane.getRowIndex(blackQueenAnalyzed);
                blackQueenAnalyzedColumn = GridPane.getColumnIndex(blackQueenAnalyzed);
                analyzedFieldRightBack = getNodeByRowColumnIndex(blackQueenAnalyzedRow+1,blackQueenAnalyzedColumn+1,gridPaneLoaded);
                analyzedFieldLeftFront = getNodeByRowColumnIndex(blackQueenAnalyzedRow-1,blackQueenAnalyzedColumn-1,gridPaneLoaded);
                analyzedFieldRightFront = getNodeByRowColumnIndex(blackQueenAnalyzedRow-1,blackQueenAnalyzedColumn+1,gridPaneLoaded);
                analyzedFieldLeftBack = getNodeByRowColumnIndex(blackQueenAnalyzedRow+1,blackQueenAnalyzedColumn-1,gridPaneLoaded);

                try {
                    while (analyzedFieldLeftBack.getId() != null && !analyzedFieldLeftBack.getId().contains("black") && !analyzedFieldLeftBack.getId().contains("bqueen")) {
                        if (analyzedFieldLeftBack.getId().contains("free")) {
                            analyzedFieldLeftBack = getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldLeftBack) + 1, GridPane.getColumnIndex(analyzedFieldLeftBack) - 1, gridPaneLoaded);
                        } else if (analyzedFieldLeftBack.getId().contains("w")
                                && getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldLeftBack) + 1, GridPane.getColumnIndex(analyzedFieldLeftBack) - 1, gridPaneLoaded).getId().contains("free")) {
                            blackQueenAnalyzedKills = blackQueenAnalyzedKills + 1;
                            analyzedFieldLeftBack = getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldLeftBack) + 2, GridPane.getColumnIndex(analyzedFieldLeftBack) - 2, gridPaneLoaded);
                        } else {
                            break;
                        }
                    }
                } catch (Exception e1){
                    System.out.println("Null dama left back!");
                }
                if (blackQueenAnalyzedKills > blackQueenKills) {
                    blackQueenKills = blackQueenAnalyzedKills;
                    directoinAnalyzedQueen = "lb";
                }
                try {
                    while (analyzedFieldRightBack.getId() != null && !analyzedFieldRightBack.getId().contains("black") && !analyzedFieldRightBack.getId().contains("bqueen")) {
                        if (analyzedFieldRightBack.getId().contains("free")) {
                            analyzedFieldRightBack = getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldRightBack) + 1, GridPane.getColumnIndex(analyzedFieldRightBack) + 1, gridPaneLoaded);
                        } else if (analyzedFieldRightBack.getId().contains("w")
                                && getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldRightBack) + 1, GridPane.getColumnIndex(analyzedFieldRightBack) + 1, gridPaneLoaded).getId().contains("free")) {
                            blackQueenAnalyzedKills = blackQueenAnalyzedKills + 1;
                            analyzedFieldRightBack = getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldRightBack) + 2, GridPane.getColumnIndex(analyzedFieldRightBack) + 2, gridPaneLoaded);
                        } else {
                            break;
                        }
                    }

                }catch (Exception e2){
                    System.out.println("Null dama right back!");
                }
                if (blackQueenAnalyzedKills > blackQueenKills) {
                    blackQueenKills = blackQueenAnalyzedKills;
                    directoinAnalyzedQueen = "rb";
                }
                try {
                    while (analyzedFieldLeftFront.getId() != null && !analyzedFieldLeftFront.getId().contains("black") && !analyzedFieldLeftFront.getId().contains("bqueen")) {
                        if (analyzedFieldLeftFront.getId().contains("free")) {
                            System.out.println("Left front free dama!");
                            analyzedFieldLeftFront = getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldLeftFront) - 1, GridPane.getColumnIndex(analyzedFieldLeftFront) - 1, gridPaneLoaded);
                        } else if (analyzedFieldLeftFront.getId().contains("w")
                                && getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldLeftFront) - 1, GridPane.getColumnIndex(analyzedFieldLeftFront) - 1, gridPaneLoaded).getId().contains("free")) {
                            System.out.println("Left front jest bicie dama!");
                            blackQueenAnalyzedKills = blackQueenAnalyzedKills + 1;
                            analyzedFieldLeftFront = getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldLeftFront) - 2, GridPane.getColumnIndex(analyzedFieldLeftFront) - 2, gridPaneLoaded);
                        } else {
                            break;
                        }
                    }

                }catch (Exception e3){
                    System.out.println("Null dama left front!");
                }
                if (blackQueenAnalyzedKills > blackQueenKills) {
                    blackQueenKills = blackQueenAnalyzedKills;
                    System.out.println("Left front kierunek : lf !");
                    directoinAnalyzedQueen = "lf";
                }
                try {
                    while (analyzedFieldRightFront.getId() != null && !analyzedFieldRightFront.getId().contains("black") && !analyzedFieldRightFront.getId().contains("bqueen")) {
                        if (analyzedFieldRightFront.getId().contains("free")) {
                            System.out.println("Right front free dama!");
                            analyzedFieldRightFront = getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldRightFront) - 1, GridPane.getColumnIndex(analyzedFieldRightFront) + 1, gridPaneLoaded);
                        } else if (analyzedFieldRightFront.getId().contains("w")
                                && getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldRightFront) - 1, GridPane.getColumnIndex(analyzedFieldRightFront) + 1, gridPaneLoaded).getId().contains("free")) {
                            System.out.println("Right front jest bicie dama!");
                            blackQueenAnalyzedKills = blackQueenAnalyzedKills + 1;
                            analyzedFieldRightFront = getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldRightFront) - 2, GridPane.getColumnIndex(analyzedFieldRightFront) + 2, gridPaneLoaded);
                        } else {
                            break;
                        }

                    }

                }catch (Exception e4){
                    System.out.println("Null dama right front!");
                }
                if (blackQueenAnalyzedKills > blackQueenKills) {
                    blackQueenKills = blackQueenAnalyzedKills;
                    System.out.println("Right front kierunek : rf !");
                    directoinAnalyzedQueen = "rf";
                }
                if(blackQueenKills > blackQueenHighestKills){
                    blackQueenHighestKills = blackQueenKills;
                    blackQueenWithHighestKills=blackQueenAnalyzed;
                    choosenQueenWithHighestKills.setQueen(blackQueenWithHighestKills);
                    choosenQueenWithHighestKills.setDirectionToKill(directoinAnalyzedQueen);
                }

                System.out.println("Wykonano petle losowania Queen numar : "+i);
                i++;

            }
            if(blackQueenHighestKills == 0){
                if (blacksQueens.size()==1){
                    choosenQueenWithHighestKills.setQueen(blacksQueens.get(0));
                    choosenQueenWithHighestKills.setDirectionToKill("brak");
                }else {
                    choosenQueenWithHighestKills.setQueen(blacksQueens.get(randomArrayId.nextInt(blacksQueens.size())));
                    choosenQueenWithHighestKills.setDirectionToKill("brak");
                }
            }
        }
        System.out.println("Wybrano dame : " + choosenQueenWithHighestKills.getQueen() + "| Kierunek : " + choosenQueenWithHighestKills.getDirectionToKill());
        return choosenQueenWithHighestKills;
    }
    private void randomMoveOrKillQueen(GridPane gridPaneLoaded){
        QueenWithHighestKills queen = chooseRandomBlackQueen(gridPaneLoaded);
        if (queen.getQueen()!=null) {
            Node analyzedFieldLeftFront;
            Node analyzedFieldRightFront;
            Node analyzedFieldLeftBack;
            Node analyzedFieldRightBack;
            int blackQueenAnalyzedRow;
            int blackQueenAnalyzedColumn;
            int newRow;
            int newColumn;
            blackQueenAnalyzedRow = GridPane.getRowIndex(queen.getQueen());
            blackQueenAnalyzedColumn = GridPane.getColumnIndex(queen.getQueen());
            analyzedFieldRightBack = getNodeByRowColumnIndex(blackQueenAnalyzedRow + 1, blackQueenAnalyzedColumn + 1, gridPaneLoaded);
            analyzedFieldLeftFront = getNodeByRowColumnIndex(blackQueenAnalyzedRow - 1, blackQueenAnalyzedColumn - 1, gridPaneLoaded);
            analyzedFieldRightFront = getNodeByRowColumnIndex(blackQueenAnalyzedRow - 1, blackQueenAnalyzedColumn + 1, gridPaneLoaded);
            analyzedFieldLeftBack = getNodeByRowColumnIndex(blackQueenAnalyzedRow + 1, blackQueenAnalyzedColumn - 1, gridPaneLoaded);
            Random randomDirection = new Random();
            int direciton;
            List<Integer> possibleDirections = new ArrayList<>();

            if (queen.getDirectionToKill().equals("brak")) {
                try {
                    if (analyzedFieldLeftBack.getId().contains("free")) {
                        possibleDirections.add(0);
                    }
                } catch (Exception e1) {
                    System.out.println("Error : " + e1);
                }
                try {
                    if (analyzedFieldRightBack.getId().contains("free")) {
                        possibleDirections.add(1);
                    }
                } catch (Exception e2) {
                    System.out.println("Error : " + e2);
                }
                try {
                    if (analyzedFieldLeftFront.getId().contains("free")) {
                        possibleDirections.add(2);
                    }
                } catch (Exception e3) {
                    System.out.println("Error : " + e3);
                }
                try {
                    if (analyzedFieldRightFront.getId().contains("free")) {
                        possibleDirections.add(3);
                    }
                } catch (Exception e4) {
                    System.out.println("Error : " + e4);
                }
                if (possibleDirections.size() > 1) {
                    direciton = possibleDirections.get(randomDirection.nextInt(possibleDirections.size()));
                } else {
                    direciton = possibleDirections.get(0);
                }

                if (direciton == 0) {
                    try {
                        while (analyzedFieldLeftBack.getId().contains("free")) {
                            if (!analyzedFieldLeftBack.getId().contains("free")) {
                                break;
                            }

                            newColumn = GridPane.getColumnIndex(analyzedFieldLeftBack);
                            newRow = GridPane.getRowIndex(analyzedFieldLeftBack);
                            GridPane.setConstraints(analyzedFieldLeftBack, GridPane.getColumnIndex(queen.getQueen()), GridPane.getRowIndex(queen.getQueen()));
                            GridPane.setConstraints(queen.getQueen(), newColumn, newRow);
                            blackQueenAnalyzedRow = GridPane.getRowIndex(queen.getQueen());
                            blackQueenAnalyzedColumn = GridPane.getColumnIndex(queen.getQueen());
                            analyzedFieldLeftBack = getNodeByRowColumnIndex(blackQueenAnalyzedRow + 1, blackQueenAnalyzedColumn - 1, gridPaneLoaded);
                        }
                    }catch (Exception e0){
                        System.out.println("Nie ma pola do ruchu damy lewy tyl");
                    }
                }
                if (direciton == 1) {
                    try {
                        while (analyzedFieldRightBack.getId().contains("free")) {
                            if (!analyzedFieldRightBack.getId().contains("free")) {
                                break;
                            }
                            newColumn = GridPane.getColumnIndex(analyzedFieldRightBack);
                            newRow = GridPane.getRowIndex(analyzedFieldRightBack);
                            GridPane.setConstraints(analyzedFieldRightBack, GridPane.getColumnIndex(queen.getQueen()), GridPane.getRowIndex(queen.getQueen()));
                            GridPane.setConstraints(queen.getQueen(), newColumn, newRow);
                            blackQueenAnalyzedRow = GridPane.getRowIndex(queen.getQueen());
                            blackQueenAnalyzedColumn = GridPane.getColumnIndex(queen.getQueen());
                            analyzedFieldRightBack = getNodeByRowColumnIndex(blackQueenAnalyzedRow + 1, blackQueenAnalyzedColumn + 1, gridPaneLoaded);
                        }
                    }catch (Exception e1){
                        System.out.println("Nie ma pola do ruchu damy prawy tyl");
                    }
                }
                if (direciton == 2) {
                    try {
                        while (analyzedFieldLeftFront.getId().contains("free")) {
                            if (!analyzedFieldLeftFront.getId().contains("free")) {
                                break;
                            }
                            newColumn = GridPane.getColumnIndex(analyzedFieldLeftFront);
                            newRow = GridPane.getRowIndex(analyzedFieldLeftFront);
                            GridPane.setConstraints(analyzedFieldLeftFront, GridPane.getColumnIndex(queen.getQueen()), GridPane.getRowIndex(queen.getQueen()));
                            GridPane.setConstraints(queen.getQueen(), newColumn, newRow);
                            blackQueenAnalyzedRow = GridPane.getRowIndex(queen.getQueen());
                            blackQueenAnalyzedColumn = GridPane.getColumnIndex(queen.getQueen());
                            analyzedFieldLeftFront = getNodeByRowColumnIndex(blackQueenAnalyzedRow - 1, blackQueenAnalyzedColumn - 1, gridPaneLoaded);
                        }
                    }catch (Exception e2){
                        System.out.println("Nie ma pola do ruchu damy lewy przod");
                    }
                }
                if (direciton == 3) {
                    try {
                        while (analyzedFieldRightFront.getId().contains("free")) {
                            if (!analyzedFieldRightFront.getId().contains("free")) {
                                break;
                            }
                            newColumn = GridPane.getColumnIndex(analyzedFieldRightFront);
                            newRow = GridPane.getRowIndex(analyzedFieldRightFront);
                            GridPane.setConstraints(analyzedFieldRightFront, GridPane.getColumnIndex(queen.getQueen()), GridPane.getRowIndex(queen.getQueen()));
                            GridPane.setConstraints(queen.getQueen(), newColumn, newRow);
                            blackQueenAnalyzedRow = GridPane.getRowIndex(queen.getQueen());
                            blackQueenAnalyzedColumn = GridPane.getColumnIndex(queen.getQueen());
                            analyzedFieldRightFront = getNodeByRowColumnIndex(blackQueenAnalyzedRow - 1, blackQueenAnalyzedColumn + 1, gridPaneLoaded);
                        }
                    }catch (Exception e3){
                        System.out.println("Nie ma pola do ruchu damy prawy przod");
                    }
                }

            } else {
                if (queen.getDirectionToKill().equals("lb")) {
                    try {
                        while (analyzedFieldLeftBack.getId().contains("free") || analyzedFieldLeftBack.getId().contains("w")) {
                            if (analyzedFieldLeftBack.getId().contains("free")) {
                                newColumn = GridPane.getColumnIndex(analyzedFieldLeftBack);
                                newRow = GridPane.getRowIndex(analyzedFieldLeftBack);
                                GridPane.setConstraints(analyzedFieldLeftBack, GridPane.getColumnIndex(queen.getQueen()), GridPane.getRowIndex(queen.getQueen()));
                                GridPane.setConstraints(queen.getQueen(), newColumn, newRow);
                                blackQueenAnalyzedRow = GridPane.getRowIndex(queen.getQueen());
                                blackQueenAnalyzedColumn = GridPane.getColumnIndex(queen.getQueen());
                                analyzedFieldLeftBack = getNodeByRowColumnIndex(blackQueenAnalyzedRow + 1, blackQueenAnalyzedColumn - 1, gridPaneLoaded);
                            } else if (analyzedFieldLeftBack.getId().contains("w")
                                    && getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldLeftBack) + 1, GridPane.getColumnIndex(analyzedFieldLeftBack) - 1, gridPaneLoaded).getId().contains("free")) {
                                killingBackLeft(queen.getQueen());
                                blackQueenAnalyzedRow = GridPane.getRowIndex(queen.getQueen());
                                blackQueenAnalyzedColumn = GridPane.getColumnIndex(queen.getQueen());
                                analyzedFieldLeftBack = getNodeByRowColumnIndex(blackQueenAnalyzedRow + 1, blackQueenAnalyzedColumn - 1, gridPaneLoaded);
                            } else {
                                break;
                            }
                        }
                    }catch (Exception e4){
                        System.out.println("Nie ma bicia damy lewy tyl");
                    }
                }
                if (queen.getDirectionToKill().equals("rb")) {
                    try {
                        while (analyzedFieldRightBack.getId().contains("free") || analyzedFieldRightBack.getId().contains("w")) {
                            if (analyzedFieldRightBack.getId().contains("free")) {
                                newColumn = GridPane.getColumnIndex(analyzedFieldRightBack);
                                newRow = GridPane.getRowIndex(analyzedFieldRightBack);
                                GridPane.setConstraints(analyzedFieldRightBack, GridPane.getColumnIndex(queen.getQueen()), GridPane.getRowIndex(queen.getQueen()));
                                GridPane.setConstraints(queen.getQueen(), newColumn, newRow);
                                blackQueenAnalyzedRow = GridPane.getRowIndex(queen.getQueen());
                                blackQueenAnalyzedColumn = GridPane.getColumnIndex(queen.getQueen());
                                analyzedFieldRightBack = getNodeByRowColumnIndex(blackQueenAnalyzedRow + 1, blackQueenAnalyzedColumn + 1, gridPaneLoaded);
                            } else if (analyzedFieldRightBack.getId().contains("w")
                                    && getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldRightBack) + 1, GridPane.getColumnIndex(analyzedFieldRightBack) + 1, gridPaneLoaded).getId().contains("free")) {
                                killingBackRight(queen.getQueen());
                                blackQueenAnalyzedRow = GridPane.getRowIndex(queen.getQueen());
                                blackQueenAnalyzedColumn = GridPane.getColumnIndex(queen.getQueen());
                                analyzedFieldRightBack = getNodeByRowColumnIndex(blackQueenAnalyzedRow + 1, blackQueenAnalyzedColumn + 1, gridPaneLoaded);
                            } else {
                                break;
                            }
                        }
                    }catch (Exception e5){
                        System.out.println("Nie ma bicia damy prawy tyl");
                    }
                }
                if (queen.getDirectionToKill().equals("lf")) {
                    try {
                        while (analyzedFieldLeftFront.getId().contains("free") || analyzedFieldLeftFront.getId().contains("w")) {
                            if (analyzedFieldLeftFront.getId().contains("free")) {
                                newColumn = GridPane.getColumnIndex(analyzedFieldLeftFront);
                                newRow = GridPane.getRowIndex(analyzedFieldLeftFront);
                                GridPane.setConstraints(analyzedFieldLeftFront, GridPane.getColumnIndex(queen.getQueen()), GridPane.getRowIndex(queen.getQueen()));
                                GridPane.setConstraints(queen.getQueen(), newColumn, newRow);
                                blackQueenAnalyzedRow = GridPane.getRowIndex(queen.getQueen());
                                blackQueenAnalyzedColumn = GridPane.getColumnIndex(queen.getQueen());
                                analyzedFieldLeftFront = getNodeByRowColumnIndex(blackQueenAnalyzedRow - 1, blackQueenAnalyzedColumn - 1, gridPaneLoaded);

                            } else if (analyzedFieldLeftFront.getId().contains("w")
                                    && getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldLeftFront) - 1, GridPane.getColumnIndex(analyzedFieldLeftFront) - 1, gridPaneLoaded).getId().contains("free")) {
                                killingFrontLeft(queen.getQueen());
                                blackQueenAnalyzedRow = GridPane.getRowIndex(queen.getQueen());
                                blackQueenAnalyzedColumn = GridPane.getColumnIndex(queen.getQueen());
                                analyzedFieldLeftFront = getNodeByRowColumnIndex(blackQueenAnalyzedRow - 1, blackQueenAnalyzedColumn - 1, gridPaneLoaded);
                            } else {
                                break;
                            }
                        }
                    }catch (Exception e6){
                        System.out.println("Nie ma bicia damy lewy przod");
                    }
                }
                if (queen.getDirectionToKill().equals("rf")) {
                    try {
                        while (analyzedFieldRightFront.getId().contains("free") || analyzedFieldRightFront.getId().contains("w")) {
                            if (analyzedFieldRightFront.getId().contains("free")) {
                                newColumn = GridPane.getColumnIndex(analyzedFieldRightFront);
                                newRow = GridPane.getRowIndex(analyzedFieldRightFront);
                                GridPane.setConstraints(analyzedFieldRightFront, GridPane.getColumnIndex(queen.getQueen()), GridPane.getRowIndex(queen.getQueen()));
                                GridPane.setConstraints(queen.getQueen(), newColumn, newRow);
                                blackQueenAnalyzedRow = GridPane.getRowIndex(queen.getQueen());
                                blackQueenAnalyzedColumn = GridPane.getColumnIndex(queen.getQueen());
                                analyzedFieldRightFront = getNodeByRowColumnIndex(blackQueenAnalyzedRow - 1, blackQueenAnalyzedColumn + 1, gridPaneLoaded);

                            } else if (analyzedFieldRightFront.getId().contains("w")
                                    && getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldRightFront) - 1, GridPane.getColumnIndex(analyzedFieldRightFront) + 1, gridPaneLoaded).getId().contains("free")) {
                                killingFrontRight(queen.getQueen());
                                blackQueenAnalyzedRow = GridPane.getRowIndex(queen.getQueen());
                                blackQueenAnalyzedColumn = GridPane.getColumnIndex(queen.getQueen());
                                analyzedFieldRightFront = getNodeByRowColumnIndex(blackQueenAnalyzedRow - 1, blackQueenAnalyzedColumn + 1, gridPaneLoaded);

                            } else {
                                break;
                            }
                        }
                    }catch (Exception e7){
                        System.out.println("Nie ma bicia damy prawy przod");
                    }
                }
            }
        }

    }
    void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void save(){
        List<ObjectsToSave> savedObjects = new ArrayList<>();
        List<Node> allObjects = gridPane.getChildren();
        allObjects.stream()
                .map(s-> new ObjectsToSave(GridPane.getRowIndex(s),GridPane.getColumnIndex(s),s))
                .forEach(savedObjects::add);
        for (ObjectsToSave objects : savedObjects
                ) {
            System.out.println(objects.getChild() +" | Column : "+objects.getColumn()+" | Row :  "+ objects.getRow());

        }
        Path path = Paths.get("C:\\Users\\Soran\\Documents\\Development\\KursJavaFx-master\\GameKodilla\\src\\resources\\fxml\\game\\previousGame.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path))
        {
            for (ObjectsToSave textToSave: savedObjects
            ) {
                writer.write(textToSave.getRow() +"|"+ textToSave.getColumn() +"|"+textToSave.getChild().getId()+"\n");


            }
            System.out.println("Udało się zapisać!");

        } catch (IOException e) {
            System.out.println("wystąpił błąd: " + e);
        }
    }

    void setPreviousGame(String textLine){
        int row = Integer.parseInt(textLine.substring(0,1));
        int column = Integer.parseInt(textLine.substring(2,3));
        String id = textLine.substring(4);

        ImageView pickedToChange = (ImageView) getNodeByRowColumnIndex(row,column,gridPane);
        if (id.contains("wqueen")) {
            Image whiteQueen = new Image("resources/fxml/game/biala_dama.png");
            pickedToChange.setImage(whiteQueen);
            pickedToChange.setId(id);
        }
        if (id.contains("bqueen")) {
            Image blackQueen = new Image("resources/fxml/game/czarna_dama.png");
            pickedToChange.setImage(blackQueen);
            pickedToChange.setId(id);
        }
        if (id.contains("white")) {
            Image white = new Image("resources/fxml/game/bialy_pionek.png");
            pickedToChange.setImage(white);
            pickedToChange.setId(id);
        }
        if (id.contains("black")) {
            Image black = new Image("resources/fxml/game/czarny_pionek.png");
            pickedToChange.setImage(black);
            pickedToChange.setId(id);
        }
        if (id.contains("free")) {
            Image free = new Image("resources/fxml/game/free.png");
            pickedToChange.setImage(free);
            pickedToChange.setId(id);
        }
    }
    void assignFreePlacesCount(List freeList){
        freePlaces.setCountedObjects(freeList.size());
        System.out.println("Liczba wolnych pol : " + freePlaces.getCountedObjects());
    }
    private void winningOrLoseingStatus(){
        List<String>blacks = new ArrayList<>();
        List<String>whites = new ArrayList<>();
        gridPane.getChildren().stream()
                .map(Node::getId)
                .filter(s->s.contains("b"))
                .forEach(blacks::add);
        gridPane.getChildren().stream()
                .map(Node::getId)
                .filter(s->s.contains("w"))
                .forEach(whites::add);
        if(blacks.size()>whites.size()){
            propertyTextField.setValue("Przegrywasz !");

        }
        if(blacks.size()<whites.size()){
            propertyTextField.setValue("Wygrywasz !");

        }
        if(blacks.size()==whites.size()){
            propertyTextField.setValue("Remis !");

        }
    }

}
