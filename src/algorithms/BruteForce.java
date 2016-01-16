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

    public BruteForce(List<Item> items) {
        super(items);
    }

    @Override
    public List<Box> solvePackingProblem() {
        System.out.println("BruteForce");
        while(!items.isEmpty()){
            ArrayList taken = new ArrayList<Item>();
            double max = pickItemsForBox(Constants.MAX_CAPACITY_OF_BOX, items, items.size(), taken);
            System.out.println(max);
            boxes.add(new Box(taken));
            items.removeAll(taken);
        }

        return boxes;
    }

    private double pickItemsForBox(double capacity, List<Item> items, int itemsNumber, List<Item> taken) {
        if (itemsNumber == 0 || capacity == 0)
            return 0;
        if (items.get(itemsNumber-1).getItemSize() > capacity)
            return pickItemsForBox(capacity, items, itemsNumber - 1, taken);
        else {
            final int preTookSize = taken.size();
            final double took = items.get(itemsNumber-1).getItemSize() + pickItemsForBox(capacity - items.get(itemsNumber - 1).getItemSize(), items, itemsNumber - 1, taken);

            final int preLeftSize = taken.size();
            final double left = pickItemsForBox(capacity, items, itemsNumber - 1, taken);

            if (took > left) {
                if (taken.size() > preLeftSize) {
                    taken.subList(preLeftSize, taken.size()).clear();
                }
                taken.add(items.get(itemsNumber - 1));
                return took;
            }
            else {
                if (preLeftSize > preTookSize)
                    taken.subList(preTookSize, preLeftSize).clear();
                return left;
            }
        }
    }
}
