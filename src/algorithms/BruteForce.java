package algorithms;

import common.Constants;
import model.Box;
import model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michto on 16.01.16.
 */
public class BruteForce extends PackingAlgorithm {

    private int currentBestSolution;
    public List<Box> currentBestBoxes = new ArrayList<>();

    public BruteForce(List<Item> items) {
        super(items);
        for(Item item : items){
            boxes.add(new Box()); //wypelnij nagorsza mozliwoscia -> 1 item na 1 box
            Box b = new Box();
            b.addItem(item);
            currentBestBoxes.add(b);
        }
        currentBestSolution = items.size();
    }

    @Override
    public List<Box> solvePackingProblem() {
        System.out.println("BruteForce");
        bruteforce(items, 0);
        return boxes;
    }

    private void bruteforce(List<Item> in, int currentPosition) {
        if (currentPosition >= in.size()) { // reached last item, done with this iteration
            int filledBins = getFilledBinsCount(boxes);
            if (filledBins < currentBestSolution) { // is this solution better than the current best?
                currentBestSolution = filledBins; // then save it
                currentBestBoxes = deepCopy(boxes);
            }
            return;
        }
        // iterate over bins
        Item currentItem = in.get(currentPosition);
        for (Box bin : boxes) {
            if (currentItem.getItemSize().compareTo(bin.getFreeCapacity()) == -1 || currentItem.getItemSize().compareTo(bin.getFreeCapacity()) == 0) {
                bin.addItem(currentItem);
                bruteforce(in, currentPosition + 1);
                bin.remove(currentItem);
            } // else: item did not fit in bin, ignore
        }
    }

    public List<Box> deepCopy(List<Box> bins) {
        ArrayList<Box> copy = new ArrayList<Box>();
        for (int i = 0; i < bins.size(); i++) {
            copy.add(bins.get(i).deepCopy());
        }
        return copy;
    }

    private int getFilledBinsCount(List<Box> boxList) {
        int filledBins = 0;
        for (Box box : boxList) {
            if (box.getItemsInBox().size() != 0) {
                filledBins++;
            }
        }
        return filledBins;
    }
}
