package algorithms;

import common.Constants;
import model.Box;
import model.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by michto on 16.01.16.
 */
public class FirstFitAscending extends PackingAlgorithm {

    public FirstFitAscending(List<Item> items) {
        super(items);
        Collections.sort(this.items);
    }

    @Override
    public List<Box> solvePackingProblem() {
        System.out.println("FirstFitAscending");
        while(!items.isEmpty()){
            Box max = pickItemsForBox(this.items);
            boxes.add(max);
        }
        return boxes;
    }

    private Box pickItemsForBox(List<Item> items){
        boolean isTooMuch = false;
        int index = 0;
        Box box = new Box();
        while(!isTooMuch && !items.isEmpty()){
            if(items.get(index).getItemSize().compareTo(box.getFreeCapacity()) == -1 || items.get(index).getItemSize().compareTo(box.getFreeCapacity()) == 0){
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
