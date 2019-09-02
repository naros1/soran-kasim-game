package Game;


import javafx.scene.Node;

public class QueenWithHighestKills {
    private Node queen;
    private String directionToKill;

    public QueenWithHighestKills(Node queen, String directionToKill) {
        this.queen = queen;
        this.directionToKill = directionToKill;
    }

    public Node getQueen() {
        return queen;
    }

    public void setQueen(Node queen) {
        this.queen = queen;
    }

    public String getDirectionToKill() {
        return directionToKill;
    }

    public void setDirectionToKill(String directionToKill) {
        this.directionToKill = directionToKill;
    }
}
