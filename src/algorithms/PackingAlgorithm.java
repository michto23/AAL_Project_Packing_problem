package algorithms;

import model.Box;
import model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michto on 16.01.16.
 */
public abstract class PackingAlgorithm {
    protected List<Item> items;
    protected List<Box> boxes;
    protected long executionTime = 0;

    public PackingAlgorithm(List<Item> items) {
        this.items = new ArrayList<>(items);
        this.boxes = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public void setBoxes(List<Box> boxes) {
        this.boxes = boxes;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public long getExecutionTimeInMiliseconds() {
        return executionTime/1000000;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public abstract List<Box> solvePackingProblem();
}
