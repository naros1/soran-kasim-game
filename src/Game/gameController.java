package Game;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.*;

public class gameController {

    @FXML
    private ImageView black1;

    @FXML
    private ImageView black2;

    @FXML
    private ImageView black3;

    @FXML
    private ImageView black4;

    @FXML
    private ImageView black5;

    @FXML
    private ImageView black6;

    @FXML
    private ImageView black7;

    @FXML
    private ImageView black8;

    @FXML
    private ImageView black9;

    @FXML
    private ImageView black10;

    @FXML
    private ImageView black11;

    @FXML
    private ImageView black12;

    @FXML
    private ImageView white1;

    @FXML
    private ImageView white2;

    @FXML
    private ImageView white3;

    @FXML
    private ImageView white4;

    @FXML
    private ImageView white5;

    @FXML
    private ImageView white6;

    @FXML
    private ImageView white7;

    @FXML
    private ImageView white8;

    @FXML
    private ImageView white9;

    @FXML
    private ImageView white10;

    @FXML
    private ImageView white11;

    @FXML
    private ImageView white12;

    @FXML
    private ImageView free1;

    @FXML
    private ImageView free2;

    @FXML
    private ImageView free3;

    @FXML
    private ImageView free4;

    @FXML
    private ImageView free5;

    @FXML
    private ImageView free6;

    @FXML
    private ImageView free7;

    @FXML
    private ImageView free8;

    @FXML
    private GridPane gridPane;


    public void setBlack1(ImageView black1) {
        this.black1 = black1;
    }

    public void setBlack2(ImageView black2) {
        this.black2 = black2;
    }

    public void setBlack3(ImageView black3) {
        this.black3 = black3;
    }

    public void setBlack4(ImageView black4) {
        this.black4 = black4;
    }

    public void setBlack5(ImageView black5) {
        this.black5 = black5;
    }

    public void setBlack6(ImageView black6) {
        this.black6 = black6;
    }

    public void setBlack7(ImageView black7) {
        this.black7 = black7;
    }

    public void setBlack8(ImageView black8) {
        this.black8 = black8;
    }

    public void setBlack9(ImageView black9) {
        this.black9 = black9;
    }

    public void setBlack10(ImageView black10) {
        this.black10 = black10;
    }

    public void setBlack11(ImageView black11) {
        this.black11 = black11;
    }

    public void setBlack12(ImageView black12) {
        this.black12 = black12;
    }

    public void setWhite1(ImageView white1) {
        this.white1 = white1;
    }

    public void setWhite2(ImageView white2) {
        this.white2 = white2;
    }

    public void setWhite3(ImageView white3) {
        this.white3 = white3;
    }

    public void setWhite4(ImageView white4) {
        this.white4 = white4;
    }

    public void setWhite5(ImageView white5) {
        this.white5 = white5;
    }

    public void setWhite6(ImageView white6) {
        this.white6 = white6;
    }

    public void setWhite7(ImageView white7) {
        this.white7 = white7;
    }

    public void setWhite8(ImageView white8) {
        this.white8 = white8;
    }

    public void setWhite9(ImageView white9) {
        this.white9 = white9;
    }

    public void setWhite10(ImageView white10) {
        this.white10 = white10;
    }

    public void setWhite11(ImageView white11) {
        this.white11 = white11;
    }

    public void setWhite12(ImageView white12) {
        this.white12 = white12;
    }

    public void setFree1(ImageView free1) {
        this.free1 = free1;
    }

    public void setFree2(ImageView free2) {
        this.free2 = free2;
    }

    public void setFree3(ImageView free3) {
        this.free3 = free3;
    }

    public void setFree4(ImageView free4) {
        this.free4 = free4;
    }

    public void setFree5(ImageView free5) {
        this.free5 = free5;
    }

    public void setFree6(ImageView free6) {
        this.free6 = free6;
    }

    public void setFree7(ImageView free7) {
        this.free7 = free7;
    }

    public void setFree8(ImageView free8) {
        this.free8 = free8;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }


    private ImageView pickedId;
    private ImageView pickedOld;
    private Node pickedBlack;
    private Mouse mouseClicked = new Mouse(0, 0);
    private Mouse oldMouseClicked = new Mouse(0, 0);
    private ObjectsCounter killedWhite = new ObjectsCounter(0);
    private ObjectsCounter killedBlack = new ObjectsCounter(0);
    private ObjectsCounter freePlaces = new ObjectsCounter(8);
    private ObjectsCounter playerRoundCounter = new ObjectsCounter(0);
    private int previousRoundNumber;
    private boolean isKillFrontRight;
    private boolean isKillFrontLeft;
    private boolean isKillBackRight;
    private boolean isKillBackLeft;





    @FXML
    public void initialize(){







    }

    @FXML
    public void getClickedId(MouseEvent e) {

            if (pickedId != null ) {
                    pickedOld = pickedId;
                    checkForKill("black");
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
                        if (pickedOld.getId().contains("white") && pickedId.getId().contains("b")) {
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
                        if (pickedOld.getId().contains("wqueen")) {
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
            }catch (Exception e3) {
                moveBlack(gridPane);
                System.out.println("runda czarnego");
            }
        }else {
            previousRoundNumber = playerRoundCounter.getCountedObjects();
        }
        System.out.println("round : " + playerRoundCounter.getCountedObjects() + "| round poprzednia : " + previousRoundNumber);


    }
    public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
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

    public Node chooseRandomBlackToMove(GridPane gridPaneLoaded) {
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

    public Node chooseRandomBlackKilling(GridPane gridPaneLoaded) {
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

    public void moveBlack(GridPane gridPaneLoaded){
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

    public ImageView addCountersAndFreePlacesAfterBlackKill(){
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


    public void autoMultipleKill(String colorKilling, String colorToKill, Node picked) {
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
                if (picked.equals("white")) {
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

    public void checkForKill(String color){
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

    public String isBlackHaveKill(Node pickedBlack){
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

    public void killBlack(Node picked){
        checkForKill("b");
        autoMultipleKill("white", "b", picked);

    }
    public void killWhite(Node picked) {
        isBlackHaveKill(picked);
        autoMultipleKill("black", "w", picked);
    }


    public void killingFrontRight(Node picked){
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

    public void killingFrontLeft(Node picked) {
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

    public void killingBackRight(Node picked){
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

    public void killingBackLeft(Node picked){
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
    public void changeToQueen(Node picked){
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
            }
        }
    }

    public void moveOrKillWhiteQueen(Node pickedQueen, Node pickedField) {
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
    public QueenWithHighestKills chooseRandomBlackQueen(GridPane gridPaneLoaded) {

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

                while(analyzedFieldLeftBack.getId()!=null && !analyzedFieldLeftBack.getId().contains("black") && !analyzedFieldLeftBack.getId().contains("bqueen")){
                    if (analyzedFieldLeftBack.getId().contains("free")){
                        analyzedFieldLeftBack= getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldLeftBack)+1,GridPane.getColumnIndex(analyzedFieldLeftBack)-1,gridPaneLoaded);
                    }else if (analyzedFieldLeftBack.getId().contains("w")
                            && getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldLeftBack)+1,GridPane.getColumnIndex(analyzedFieldLeftBack)-1,gridPaneLoaded).getId().contains("free")){
                        blackQueenAnalyzedKills=blackQueenAnalyzedKills+1;
                        analyzedFieldLeftBack= getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldLeftBack)+2,GridPane.getColumnIndex(analyzedFieldLeftBack)-2,gridPaneLoaded);
                    }else {
                        break;
                    }
                }
                if (blackQueenAnalyzedKills>blackQueenKills){
                    blackQueenKills=blackQueenAnalyzedKills;
                    directoinAnalyzedQueen="lb";
                }
                while(analyzedFieldRightBack.getId()!=null && !analyzedFieldRightBack.getId().contains("black") && !analyzedFieldRightBack.getId().contains("bqueen")){
                    if (analyzedFieldRightBack.getId().contains("free")){
                        analyzedFieldRightBack= getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldRightBack)+1,GridPane.getColumnIndex(analyzedFieldRightBack)+1,gridPaneLoaded);
                    }else if (analyzedFieldRightBack.getId().contains("w")
                            && getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldRightBack)+1,GridPane.getColumnIndex(analyzedFieldRightBack)+1,gridPaneLoaded).getId().contains("free")){
                        blackQueenAnalyzedKills=blackQueenAnalyzedKills+1;
                        analyzedFieldRightBack= getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldRightBack)+2,GridPane.getColumnIndex(analyzedFieldRightBack)+2,gridPaneLoaded);
                    }else {
                        break;
                    }
                }
                if (blackQueenAnalyzedKills>blackQueenKills){
                    blackQueenKills=blackQueenAnalyzedKills;
                    directoinAnalyzedQueen="rb";
                }
                while(analyzedFieldLeftFront.getId()!=null && !analyzedFieldLeftFront.getId().contains("black") && !analyzedFieldLeftFront.getId().contains("bqueen")){
                    if (analyzedFieldLeftFront.getId().contains("free")){
                        analyzedFieldLeftFront= getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldLeftFront)-1,GridPane.getColumnIndex(analyzedFieldLeftFront)-1,gridPaneLoaded);
                    }else if (analyzedFieldLeftFront.getId().contains("w")
                            && getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldLeftFront)-1,GridPane.getColumnIndex(analyzedFieldLeftFront)-1,gridPaneLoaded).getId().contains("free")){
                        blackQueenAnalyzedKills=blackQueenAnalyzedKills+1;
                        analyzedFieldLeftFront= getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldLeftFront)-2,GridPane.getColumnIndex(analyzedFieldLeftFront)-2,gridPaneLoaded);
                    }else {
                        break;
                    }
                }
                if (blackQueenAnalyzedKills>blackQueenKills){
                    blackQueenKills=blackQueenAnalyzedKills;
                    directoinAnalyzedQueen="lf";
                }
                while(analyzedFieldRightFront.getId()!=null && !analyzedFieldRightFront.getId().contains("black") && !analyzedFieldRightFront.getId().contains("bqueen")){
                    if (analyzedFieldRightFront.getId().contains("free")){
                        analyzedFieldRightFront= getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldRightFront)-1,GridPane.getColumnIndex(analyzedFieldRightFront)+1,gridPaneLoaded);
                    }else if (analyzedFieldRightFront.getId().contains("w")
                            && getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldRightFront)-1,GridPane.getColumnIndex(analyzedFieldRightFront)+1,gridPaneLoaded).getId().contains("free")){
                        blackQueenAnalyzedKills=blackQueenAnalyzedKills+1;
                        analyzedFieldRightFront= getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldRightFront)-2,GridPane.getColumnIndex(analyzedFieldRightFront)+2,gridPaneLoaded);
                    }else {
                        break;
                    }
                }
                if (blackQueenAnalyzedKills>blackQueenKills){
                    blackQueenKills=blackQueenAnalyzedKills;
                    directoinAnalyzedQueen="rf";
                }
                if(blackQueenKills >= blackQueenHighestKills){
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
        return choosenQueenWithHighestKills;
    }
    public void randomMoveorKillQueen(GridPane gridPaneLoaded){
        QueenWithHighestKills queen = chooseRandomBlackQueen(gridPaneLoaded);
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
        analyzedFieldRightBack = getNodeByRowColumnIndex(blackQueenAnalyzedRow+1,blackQueenAnalyzedColumn+1,gridPaneLoaded);
        analyzedFieldLeftFront = getNodeByRowColumnIndex(blackQueenAnalyzedRow-1,blackQueenAnalyzedColumn-1,gridPaneLoaded);
        analyzedFieldRightFront = getNodeByRowColumnIndex(blackQueenAnalyzedRow-1,blackQueenAnalyzedColumn+1,gridPaneLoaded);
        analyzedFieldLeftBack = getNodeByRowColumnIndex(blackQueenAnalyzedRow+1,blackQueenAnalyzedColumn-1,gridPaneLoaded);
        Random randomDirection = new Random();
        int direciton;

        if (queen.getDirectionToKill().equals("brak")){
            direciton=randomDirection.nextInt(4);
            if (direciton == 0){
                while (analyzedFieldLeftBack.getId().contains("free")||analyzedFieldLeftBack.getId().contains("b")){
                    if (analyzedFieldLeftBack.getId().contains("b")
                            || getNodeByRowColumnIndex(GridPane.getRowIndex(analyzedFieldLeftBack)+1,GridPane.getColumnIndex(analyzedFieldLeftBack)-1, gridPaneLoaded).getId().contains("w")){
                        break;
                    }else {
                        newColumn = GridPane.getColumnIndex(analyzedFieldLeftBack);
                        newRow = GridPane.getRowIndex(analyzedFieldLeftBack);
                        GridPane.setConstraints(analyzedFieldLeftBack, GridPane.getColumnIndex(queen.getQueen()), GridPane.getRowIndex(queen.getQueen()));
                        GridPane.setConstraints(queen.getQueen(), newColumn, newRow);
                    }
                }
            }

        }

    }







}
