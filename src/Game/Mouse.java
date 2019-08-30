package Game;

import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;

public class Mouse {

    private Integer columnIndex;
    private Integer rowIndex;

    public Mouse(Integer columnIndex, Integer rowIndex) {
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
    }

    public Integer getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(Integer columnIndex) {
        if (columnIndex != null) {
            this.columnIndex = columnIndex;
        } else {
            this.columnIndex = 0;
        }
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        if (rowIndex != null) {
            this.rowIndex = rowIndex;
        } else {
            this.rowIndex = 0;
        }
    }
}
