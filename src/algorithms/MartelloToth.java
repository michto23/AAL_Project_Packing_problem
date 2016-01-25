package algorithms;

import common.Constants;
import model.Box;
import model.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by michto on 16.01.16.
 */
public class MartelloToth extends PackingAlgorithm {

    private class MTRPHelperStruct implements Comparable<MTRPHelperStruct>{
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

        @Override
        public int compareTo(MTRPHelperStruct mtrp) {
            BigDecimal compareSize = ((MTRPHelperStruct) mtrp).getItem().getItemSize();
            return this.item.getItemSize().compareTo(compareSize);
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
        long startTime = System.nanoTime();
        while(toUse.size() > 3){
            MTRP();
            if(!toUse.isEmpty()){ //dodaj ostatni (najmniejszy element)
                deleted.add(toUse.get(toUse.size() - 1));
                toUse.remove(toUse.get(toUse.size() - 1));
            }
        }
        for(MTRPHelperStruct mtrpHelperStruct : toUse){
            deleted.add(mtrpHelperStruct);
        }
        Collections.sort(deleted);
        Collections.reverse(deleted);
        for(MTRPHelperStruct mtrpHelperStruct : deleted){
            if(!addToExistingBox(mtrpHelperStruct)){
                Box box = new Box();
                box.addItem(mtrpHelperStruct.item);
                boxes.add(box);
            }
        }
        deleted.clear();
        long endTime = System.nanoTime();
        executionTime = endTime - startTime;
        return boxes;
    }

    private boolean addToExistingBox(MTRPHelperStruct mtrpHelperStruct){
        for(Box box : boxes){
            if(mtrpHelperStruct.item.getItemSize().compareTo(box.getFreeCapacity()) == -1 || mtrpHelperStruct.item.getItemSize().compareTo(box.getFreeCapacity()) == 0){
                box.addItem(mtrpHelperStruct.item);
                return true;
            }
        }
        return false;
    }


    private void MTRP() {
        int k = 0;
        List<MTRPHelperStruct> notPicked = new ArrayList<>();
        do{
            int j = toUse.get(0).orderNumber;
            BigDecimal sizeJ = toUse.get(0).item.getItemSize();
            k = findLargestPossibleItem(j, sizeJ, true, toUse);
            if(k == 0){ //dodaj tylko jeden item
                Box box = new Box();
                box.addItem(toUse.get(0).getItem());
                boxes.add(box);
                toUse.remove(0);
                updateOrder(toUse);
            }
            else{
                MTRPHelperStruct minMtrp = toUse.get(toUse.size() - findLargestPossibleItem(j, sizeJ, false, toUse));//pobierz jak najwiekszy spelniajacy wj + wminmtrp <= capacity
                if(k == 1 || (toUse.get(0).item.getItemSize().add(minMtrp.item.getItemSize()).equals(Constants.MAX_CAPACITY_OF_BOX))){
                    Box box = new Box();
                    box.addItem(toUse.get(0).getItem());
                    box.addItem(minMtrp.getItem());
                    boxes.add(box);
                    toUse.remove(0);
                    toUse.remove(minMtrp);
                    updateOrder(toUse);
                }
                else if(k == 2){
                    //znajdz 2 najwieksze takie ze wj + w2 + w3 <= capacity
                    MTRPHelperStruct mtrA = new MTRPHelperStruct(null);
                    MTRPHelperStruct mtrB = new MTRPHelperStruct(null);
                    MTRPHelperStruct mtrPreviousB = new MTRPHelperStruct(null);
                    MTRPHelperStruct mtr2XPreviousB = new MTRPHelperStruct(null);
                    findTwoBestItemsToAdd(mtrA, mtrB, mtrPreviousB, mtr2XPreviousB, toUse, Constants.MAX_CAPACITY_OF_BOX.subtract((sizeJ)));
                    if(minMtrp.item.getItemSize().compareTo(mtrA.item.getItemSize().add(mtrB.item.getItemSize())) == 1 || minMtrp.item.getItemSize().compareTo(mtrA.item.getItemSize().add(mtrB.item.getItemSize())) == 0){
                        Box box = new Box();
                        box.addItem(toUse.get(0).getItem());
                        box.addItem(minMtrp.getItem());
                        boxes.add(box);
                        toUse.remove(0);
                        toUse.remove(minMtrp);
                        updateOrder(toUse);
                    }
                    else if(minMtrp.getItem().getItemSize().equals(mtrA.getItem().getItemSize()) && ((mtrB.orderNumber - mtrA.orderNumber <= 2) || (sizeJ.add(mtrPreviousB.item.getItemSize() ).add(mtr2XPreviousB.item.getItemSize()).compareTo(Constants.MAX_CAPACITY_OF_BOX) == 1 ))){
                        Box box = new Box();
                        box.addItem(toUse.get(0).getItem());
                        box.addItem(mtrA.getItem());
                        box.addItem(mtrB.getItem());
                        boxes.add(box);
                        toUse.remove(mtrB.getOrderNumber());
                        toUse.remove(mtrA.getOrderNumber());
                        toUse.remove(0);

                        updateOrder(toUse);
                    }
                    else{
                        //usun tymczasowo, bo nie mozna dodac do zadnego boxa, nie spelnia wymagan
                        notPicked.add(toUse.get(0));
                        toUse.remove(0);
                        updateOrder(toUse);
                    }
                }
            }
        }while(toUse.size() > 3 && k <= 2);
        mergeToUseNotPicked(notPicked);
    }

    private void mergeToUseNotPicked(List<MTRPHelperStruct> notPicked){
        for(MTRPHelperStruct mtrpHelperStruct : notPicked){
            toUse.add(mtrpHelperStruct);
        }
        Collections.sort(toUse);
        Collections.reverse(toUse);
        updateOrder(toUse);
        notPicked.clear();
    }

    private int findLargestPossibleItem(int j, BigDecimal sizeJ, boolean sumAllPrevious, List<MTRPHelperStruct> toUseInCall){
        int k = 0;
        BigDecimal sum = BigDecimal.valueOf(0);
        BigDecimal smallSum = BigDecimal.valueOf(0);
        while (true){
            if(sumAllPrevious){
                smallSum = smallSum.add(toUseInCall.get(toUseInCall.size() - 1 - k).item.getItemSize());
                sum = sizeJ.add(smallSum);
            }
            else
                sum = sizeJ.add(toUseInCall.get(toUseInCall.size()- 1- k).item.getItemSize());
            if((sum.compareTo(Constants.MAX_CAPACITY_OF_BOX ) == 1) || k >= toUseInCall.size() - 1){
                return k;
            }
            ++k;
        }
    }

    private void findTwoBestItemsToAdd(MTRPHelperStruct mtrA, MTRPHelperStruct mtrB, MTRPHelperStruct mtrPreviousB, MTRPHelperStruct mtr2XPreviousB, List<MTRPHelperStruct> toUseInCall, BigDecimal freeCapacity){
        BigDecimal max = BigDecimal.valueOf(0);
        for(int i = toUseInCall.size() - 1; i > 1; --i){
            for(int j = i - 1; j > 0; --j){
                BigDecimal sum = toUseInCall.get(i).item.getItemSize().add(toUseInCall.get(j).item.getItemSize());
                if((sum.compareTo(freeCapacity) == -1 || sum.compareTo(freeCapacity) == 0) && (sum.compareTo(max) == 1)){
                    max = sum;
                    mtrA.setItem(toUseInCall.get(j).item);
                    mtrA.setOrderNumber(toUseInCall.get(j).orderNumber);
                    mtrA.setBoxIndex(toUseInCall.get(j).boxIndex);
                    mtrB.setItem(toUseInCall.get(i).item);
                    mtrB.setOrderNumber(toUseInCall.get(i).orderNumber);
                    mtrB.setBoxIndex(toUseInCall.get(i).boxIndex);
                    if(mtrB.orderNumber - mtrA.orderNumber > 2){
                        mtrPreviousB.setItem(toUseInCall.get(i - 1).item);
                        mtrPreviousB.setOrderNumber(toUseInCall.get(i - 1).orderNumber);
                        mtrPreviousB.setBoxIndex(toUseInCall.get(i - 1).boxIndex);
                        mtr2XPreviousB.setItem(toUseInCall.get(i - 2).item);
                        mtr2XPreviousB.setOrderNumber(toUseInCall.get(i - 2).orderNumber);
                        mtr2XPreviousB.setBoxIndex(toUseInCall.get(i - 2).boxIndex);
                    }
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

    private void updateOrder(List<MTRPHelperStruct> list ){
        int i = 0;
        for(MTRPHelperStruct mtrpHelperStruct1 : list){
            mtrpHelperStruct1.setOrderNumber(i);
            ++i;
        }
    }
}
