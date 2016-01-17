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
public class MartelloToth extends PackingAlgorithm {

    private class MTRPHelperStruct {
        private Item item;
        private int boxIndex = -1; //no box assigned
        private int orderNumber;

        public MTRPHelperStruct(Item item) {
            this.item = item;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public int getBoxIndex() {
            return boxIndex;
        }

        public void setBoxIndex(int boxIndex) {
            this.boxIndex = boxIndex;
        }

        public int getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(int orderNumber) {
            this.orderNumber = orderNumber;
        }
    }

    private List<MTRPHelperStruct> resultMTRPList = new ArrayList<>();
    private List<MTRPHelperStruct> toUse = new ArrayList<>();
    private List<MTRPHelperStruct> deleted = new ArrayList<>();

    public MartelloToth(List<Item> items) {
        super(items);
        Collections.sort(this.items);
        Collections.reverse(this.items);
        int index = 0;
        for(Item item : this.items){
            MTRPHelperStruct mtrp = new MTRPHelperStruct(item);
            mtrp.setOrderNumber(index);
            toUse.add(mtrp);
            ++index;
        }
    }

    @Override
    public List<Box> solvePackingProblem() {
        System.out.println("MartelloToth");

        List<MTRPHelperStruct> toUseInOneCall = new ArrayList<>(toUse);

        do{
            int j = toUse.get(0).orderNumber;
            double sizeJ = toUse.get(0).item.getItemSize();
            int k = findLargestPossibleItem(j, sizeJ, true);
            if(k == 0){ //dodaj tylko jeden item
                Box box = new Box();
                box.addItem(toUse.get(0).getItem());
                boxes.add(box);
                toUse.remove(0);
                toUseInOneCall.remove(0);
            }
            else{
                MTRPHelperStruct minMtrp = toUseInOneCall.get(toUseInOneCall.size() - findLargestPossibleItem(j, sizeJ, true));//pobierz jak najwiekszy spelniajacy wj + wminmtrp <= capacity
                if(k == 1 || (toUse.get(0).item.getItemSize() + minMtrp.item.getItemSize() == Constants.MAX_CAPACITY_OF_BOX)){
                    Box box = new Box();
                    box.addItem(toUse.get(0).getItem());
                    box.addItem(minMtrp.getItem());
                    boxes.add(box);
                    toUse.remove(0);
                    toUse.remove(minMtrp);
                    toUseInOneCall.remove(0);
                    toUseInOneCall.remove(minMtrp);
                }
                else if(k == 2){
                    //znajdz 2 najwieksze takie ze wj + w2 + w3 <= capacity
                    MTRPHelperStruct mtrA = new MTRPHelperStruct(null);
                    MTRPHelperStruct mtrB = new MTRPHelperStruct(null);
                    findTwoBestItemsToAdd(mtrA, mtrB, toUseInOneCall, Constants.MAX_CAPACITY_OF_BOX - sizeJ);
                    //TODO
                }
            }


        }while(!toUseInOneCall.isEmpty()); //TODO do sprawdzenia
        return null;
    }

    private int findLargestPossibleItem(int j, double sizeJ, boolean sumAllPrevious){
        int k = 0;
        double sum = 0;
        while (true){
            if(sumAllPrevious)
                sum += sizeJ + toUse.get(toUse.size()- 1- k).item.getItemSize();
            else
                sum = sizeJ + toUse.get(toUse.size()- 1- k).item.getItemSize();
            if(sum > Constants.MAX_CAPACITY_OF_BOX || k >= toUse.size() - 1){
                return k;
            }
            ++k;
        }
    }

    private void findTwoBestItemsToAdd(MTRPHelperStruct mtrA, MTRPHelperStruct mtrB, List<MTRPHelperStruct> toUseInCall, double freeCapacity){
        double max = 0;
        MTRPHelperStruct temp;
        MTRPHelperStruct temp2;
        for(int i = toUseInCall.size() - 1; i > 1; --i){
            for(int j = toUseInCall.size() - 2; i > 0; --i){
                double sum = toUseInCall.get(i).item.getItemSize() + toUseInCall.get(j).item.getItemSize();
                if(sum <= freeCapacity && sum > max){
                    max = sum;
                    mtrA = toUseInCall.get(i);
                    mtrB = toUseInCall.get(j);
                }
            }
        }
    }

    private MTRPHelperStruct findByOrderNumber(int orderNumber, List<MTRPHelperStruct> mtrpList){
        for(MTRPHelperStruct mtrp : mtrpList){
            if(mtrp.orderNumber == orderNumber)
                return mtrp;
        }
        return null;
    }
}
