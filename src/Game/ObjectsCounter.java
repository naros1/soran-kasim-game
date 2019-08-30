package Game;

public class ObjectsCounter {

    private Integer CountedObjects;

    public ObjectsCounter(Integer CountedObjects) {
        this.CountedObjects = CountedObjects;
    }

    public Integer getCountedObjects() {
        return CountedObjects;
    }

    public void setCountedObjects(Integer killedObjects) {
        this.CountedObjects = killedObjects;
    }
}
