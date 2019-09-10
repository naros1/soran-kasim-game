package Game;


import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class ObjectsToSave {
    private int row;
    private int column;
    private Node child;

    public ObjectsToSave(int row, int column, Node child) {
        this.row = row;
        this.column = column;
        this.child = child;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Node getChild() {
        return child;
    }

    public void setChild(Node child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return child+"\n"+row+"\n"+column+"\n";
    }
}
