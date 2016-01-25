package algorithms;

import common.Constants;
import model.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        BigDecimal sumAllItemsSize = BigDecimal.valueOf(0);
        for(Item item : items){
            sumAllItemsSize = sumAllItemsSize.add(item.getItemSize());
        }
        int l1 = sumAllItemsSize.divide(Constants.MAX_CAPACITY_OF_BOX).setScale(0, RoundingMode.CEILING).intValue();
        int maxCurrentLowerBound = l1;
        for(Item item : items){
            if(item.getItemSize().compareTo(Constants.MAX_CAPACITY_OF_BOX.divide(BigDecimal.valueOf(2))) == 0 || item.getItemSize().compareTo(Constants.MAX_CAPACITY_OF_BOX.divide(BigDecimal.valueOf(2))) == -1){
                isAnyBiggerThanHalfCapacity = true;
                int ret = solveLAlpha(item.getItemSize(), maxCurrentLowerBound);
                maxCurrentLowerBound = Math.max(ret, maxCurrentLowerBound);
            }
        }
        if(!isAnyBiggerThanHalfCapacity)
            return items.size();
        return maxCurrentLowerBound;
    }

    private int solveLAlpha(BigDecimal alpha, int maxCurrentLowerBound){
        List<Item> j1 = findJ1(alpha);
        List<Item> j2 = findJ2(alpha);
        List<Item> j3 = findJ3(alpha);
        System.out.println("alpha to " + alpha);
        int lAlphaRoundUp = j1.size() + j2.size() + Math.max(0, ((makeSum(j3).subtract((BigDecimal.valueOf(j2.size()).multiply(Constants.MAX_CAPACITY_OF_BOX).subtract(makeSum(j2)))) ).divide(Constants.MAX_CAPACITY_OF_BOX)).setScale(0, RoundingMode.CEILING).intValue());
        System.out.println("round to " + lAlphaRoundUp);
        return lAlphaRoundUp;
    }

    private List findJ1(BigDecimal aplha){
        List<Item> itemsJ1 = new ArrayList<>();
        for(Item item : items){
            if(item.getItemSize().compareTo(Constants.MAX_CAPACITY_OF_BOX.subtract(aplha)) == 1 ){
                itemsJ1.add(item);
            }
        }
        return itemsJ1;
    }

    private List findJ2(BigDecimal aplha){
        List<Item> itemsJ2 = new ArrayList<>();
        for(Item item : items){
            if((Constants.MAX_CAPACITY_OF_BOX.subtract(aplha).compareTo(item.getItemSize()) == 1 || Constants.MAX_CAPACITY_OF_BOX.subtract(aplha).compareTo(item.getItemSize()) == 0) && item.getItemSize().compareTo(Constants.MAX_CAPACITY_OF_BOX.divide(BigDecimal.valueOf(2))) == 1){
                itemsJ2.add(item);
            }
        }
        return itemsJ2;
    }

    private List findJ3(BigDecimal aplha){
        List<Item> itemsJ3 = new ArrayList<>();
        for(Item item : items){
            if((Constants.MAX_CAPACITY_OF_BOX.divide(BigDecimal.valueOf(2)).compareTo(item.getItemSize()) == 1 || Constants.MAX_CAPACITY_OF_BOX.divide(BigDecimal.valueOf(2)).compareTo(item.getItemSize()) == 0) && (item.getItemSize().compareTo(aplha) == 1 || item.getItemSize().compareTo(aplha) == 0)){
                itemsJ3.add(item);
            }
        }
        return itemsJ3;
    }

    private BigDecimal makeSum(List<Item> itemsForSum){
        BigDecimal sum = BigDecimal.valueOf(0);
        for (Item item : itemsForSum){
            sum = sum.add(item.getItemSize());
        }
        return sum;
    }
}
