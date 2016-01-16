package algorithms;

import model.Box;
import model.Item;

import java.util.Collections;
import java.util.List;

/**
 * Created by michto on 16.01.16.
 */
public class LastAndFirstFitAscending extends PackingAlgorithm{
    public LastAndFirstFitAscending(List<Item> items) {
        super(items);
        Collections.sort(this.items);
    }

    @Override
    public List<Box> solvePackingProblem() {
        System.out.println("LastAndFirstFitAscending");
        while(!items.isEmpty()){
            Box max = pickItemsForBox(this.items);
            System.out.println("box ma " + max.getUsedCapacity());
            boxes.add(max);
        }
        return boxes;
    }

    private Box pickItemsForBox(List<Item> items){
        boolean isTooMuch = false;
        int index = 0;
        Box box = new Box();
        box.addItem(items.get(items.size() - 1));
        items.remove(items.get(items.size() - 1));

        while(!isTooMuch && !items.isEmpty()){
            if(items.get(index).getItemSize() <= box.getFreeCapacity()){
                box.addItem(items.get(index));
                items.remove(index);
            }
            else{
                isTooMuch = true;
            }
        }
        return box;
    }
}
