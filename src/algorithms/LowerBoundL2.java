package algorithms;

import common.Constants;
import model.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by michto on 24.01.16.
 */
public class LowerBoundL2 {
    private List<Item> items = new ArrayList<>();
    boolean isAnyBiggerThanHalfCapacity = false;

    public LowerBoundL2(List<Item> items) {
        this.items = items;
        Collections.sort(this.items);
        Collections.reverse(this.items);
    }

    public int solveLowerBoundL2(){
        double sumAllItemsSize = 0;
        for(Item item : items){
            sumAllItemsSize += item.getItemSize();
        }
        System.out.println(sumAllItemsSize);
        int l1 = (int)Math.ceil(sumAllItemsSize/ Constants.MAX_CAPACITY_OF_BOX); //prosty ogranicznik
        System.out.println(l1);
        int maxCurrentLowerBound = l1;
        for(Item item : items){
            if(item.getItemSize() <= Constants.MAX_CAPACITY_OF_BOX/2){
                isAnyBiggerThanHalfCapacity = true;
                int ret = solveLAlpha(item.getItemSize(), maxCurrentLowerBound);
                maxCurrentLowerBound = Math.max(ret, maxCurrentLowerBound);
            }
        }
        if(!isAnyBiggerThanHalfCapacity)
            return items.size();
        return maxCurrentLowerBound;
    }

    private int solveLAlpha(double alpha, int maxCurrentLowerBound){
        List<Item> j1 = findJ1(alpha);
        List<Item> j2 = findJ2(alpha);
        List<Item> j3 = findJ3(alpha);

        double lAlpha = j1.size() + j2.size() + Math.max(0, (int)Math.ceil((makeSum(j3) - (j2.size()*Constants.MAX_CAPACITY_OF_BOX - makeSum(j2))) / Constants.MAX_CAPACITY_OF_BOX));
        int lAlphaRoundUp = (int)Math.ceil(lAlpha);

        return lAlphaRoundUp;
    }

    private List findJ1(double aplha){
        List<Item> itemsJ1 = new ArrayList<>();
        for(Item item : items){
            if(item.getItemSize() > Constants.MAX_CAPACITY_OF_BOX - aplha){
                itemsJ1.add(item);
            }
        }
        return itemsJ1;
    }

    private List findJ2(double aplha){
        List<Item> itemsJ2 = new ArrayList<>();
        for(Item item : items){
            if(Constants.MAX_CAPACITY_OF_BOX - aplha >= item.getItemSize() && item.getItemSize() > Constants.MAX_CAPACITY_OF_BOX/2){
                itemsJ2.add(item);
            }
        }
        return itemsJ2;
    }

    private List findJ3(double aplha){
        List<Item> itemsJ3 = new ArrayList<>();
        for(Item item : items){
            if(Constants.MAX_CAPACITY_OF_BOX/2 >= item.getItemSize() && item.getItemSize() >= aplha){
                itemsJ3.add(item);
            }
        }
        return itemsJ3;
    }

    private double makeSum(List<Item> itemsForSum){
        double sum = 0;
        for (Item item : itemsForSum){
            sum += item.getItemSize();
        }
        return sum;
    }
}
