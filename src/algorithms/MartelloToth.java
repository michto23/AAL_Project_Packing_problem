package algorithms;

import common.Constants;
import model.Box;
import model.Item;

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
            double compareSize = ((MTRPHelperStruct) mtrp).getItem().getItemSize();
            if (this.item.getItemSize() > compareSize)
                return 1;
            else if ((this.item.getItemSize() < compareSize))
                return -1;
            else return 0;
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
        return boxes;
    }

    private boolean addToExistingBox(MTRPHelperStruct mtrpHelperStruct){
        for(Box box : boxes){
            if(mtrpHelperStruct.item.getItemSize() <= box.getFreeCapacity()){
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
            double sizeJ = toUse.get(0).item.getItemSize();
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
                if(k == 1 || (toUse.get(0).item.getItemSize() + minMtrp.item.getItemSize() == Constants.MAX_CAPACITY_OF_BOX)){
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
                    findTwoBestItemsToAdd(mtrA, mtrB, mtrPreviousB, mtr2XPreviousB, toUse, Constants.MAX_CAPACITY_OF_BOX - sizeJ);
                    if(minMtrp.item.getItemSize() >= mtrA.item.getItemSize() + mtrB.item.getItemSize()){
                        Box box = new Box();
                        box.addItem(toUse.get(0).getItem());
                        box.addItem(minMtrp.getItem());
                        boxes.add(box);
                        toUse.remove(0);
                        toUse.remove(minMtrp);
                        updateOrder(toUse);
                    }
                    else if(minMtrp.getItem().getItemSize() == mtrA.getItem().getItemSize() && ((mtrB.orderNumber - mtrA.orderNumber <= 2) || (sizeJ + mtrPreviousB.item.getItemSize() + mtr2XPreviousB.item.getItemSize() > Constants.MAX_CAPACITY_OF_BOX))){
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

    private int findLargestPossibleItem(int j, double sizeJ, boolean sumAllPrevious, List<MTRPHelperStruct> toUseInCall){
        int k = 0;
        double sum = 0;
        double smallSum = 0;
        while (true){
            if(sumAllPrevious){
                smallSum += toUseInCall.get(toUseInCall.size()- 1- k).item.getItemSize();
                sum = sizeJ + smallSum;
            }
            else
                sum = sizeJ + toUseInCall.get(toUseInCall.size()- 1- k).item.getItemSize();
            if(sum > Constants.MAX_CAPACITY_OF_BOX || k >= toUseInCall.size() - 1){
                return k;
            }
            ++k;
        }
    }

    private void findTwoBestItemsToAdd(MTRPHelperStruct mtrA, MTRPHelperStruct mtrB, MTRPHelperStruct mtrPreviousB, MTRPHelperStruct mtr2XPreviousB, List<MTRPHelperStruct> toUseInCall, double freeCapacity){
        double max = 0;
        MTRPHelperStruct temp;
        MTRPHelperStruct temp2;
        for(int i = toUseInCall.size() - 1; i > 1; --i){
            for(int j = i - 1; j > 0; --j){
                double sum = toUseInCall.get(i).item.getItemSize() + toUseInCall.get(j).item.getItemSize();
                if(sum <= freeCapacity && sum > max){
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
