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
//        while(!items.isEmpty()){
//            ArrayList taken = new ArrayList<Item>();
//            double max = pickItemsForBox(Constants.MAX_CAPACITY_OF_BOX, items, items.size(), taken);
//            Box box = new Box(taken);
//            boxes.add(box);
//            for(Item item : box.getItemsInBox()){
//                System.out.print(" " + item.getItemSize());
//            }
//            System.out.println(" ");
//            items.removeAll(taken);
//            System.out.println("Mam teraz tyle itemow : " + items.size());
//        }

        bruteforce(items, 0);

        System.out.println("ile to " + ile);
        System.out.println("chuj to " + chuj);

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


    private void solve(List<Item> itemsToPick, List<Box> usedBoxes, List<Box> bestListOfBoxes){
        if(itemsToPick != null && !itemsToPick.isEmpty()){
            Item item = itemsToPick.get(0);
            List<Box> boxesForNextNode = new ArrayList<>(usedBoxes);
            for(Box box : boxesForNextNode){
                if(item.getItemSize() <= box.getFreeCapacity()){
                    box.addItem(item);
                    solve(itemsToPick, boxesForNextNode, bestListOfBoxes);
                }
            }
            Box box2 = new Box();
            box2.addItem(item);
            usedBoxes.add(box2);
        }
    }



    private boolean firstFit(List<Box> usedBoxes, Item item){
        for(Box box : usedBoxes){
            if(item.getItemSize() <= box.getFreeCapacity()){
                box.addItem(item);
                return true; //+odpal nastepne drzewo
            }
        }
        return false;
    }

    Long ile = 0L;
    Long chuj = 0L;


    private void bruteforce(List<Item> in, int currentPosition) {
        ++ile;
        if (currentPosition >= in.size()) { // reached last item, done with this iteration
            ++chuj;
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
            if (currentItem.getItemSize() <= bin.getFreeCapacity()) {
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






//    private boolean KnapsackSimplifiedProblemRecursive(List<Item> items, int sum, int currentSum, int index, List<int> itemsInTheKnapsack)
//    {
//        if (currentSum == sum)
//        {
//            return true;
//        }
//        if (currentSum > sum)
//        {
//            return false;
//        }
//        if (index >= weights.Length)
//        {
//            return false;
//        }
//        itemsInTheKnapsack.Add(weights[index]);
//        boolean flag = KnapsackSimplifiedProblemRecursive(weights, sum, currentSum: currentSum + weights[index],
//            index: index + 1, itemsInTheKnapsack: itemsInTheKnapsack);
//        if (!flag)
//        {
//            itemsInTheKnapsack.Remove(weights[index]);
//            flag = KnapsackSimplifiedProblemRecursive(weights, sum, currentSum, index + 1, itemsInTheKnapsack);
//        }
//        return flag;
//    }
//    public bool KnapsackRecursive(int[] weights, int sum, out List<int> knapsack)
//    {
//        if(sum <= 0)
//        {
//            throw new ArgumentException("sum should be +ve non zero integer");
//        }
//        knapsack = new List<int>();
//        bool fits = KnapsackSimplifiedProblemRecursive(weights, sum, currentSum: 0, index: 0,
//            itemsInTheKnapsack: knapsack);
//        return fits;
//    }
}
