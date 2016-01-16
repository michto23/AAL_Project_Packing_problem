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

    public abstract List<Box> solvePackingProblem();
}
