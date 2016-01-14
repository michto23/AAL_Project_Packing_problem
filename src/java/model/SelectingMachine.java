package java.model;

import java.common.Constants;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by michto on 25.10.15.
 */
public class SelectingMachine {
    double minItemSize = 0.001;
    double maxItemSize = 1.0;
    private Random random = new Random();
    private List<Box> allBoxes;
    private List<Item> allItems;


    public List<Box> getAllBoxes() {
        return allBoxes;
    }

    public void setAllBoxes(List<Box> allBoxes) {
        this.allBoxes = allBoxes;
    }

    public List<Item> getAllItems() {
        return allItems;
    }

    public void setAllItems(List<Item> allItems) {
        this.allItems = allItems;
    }

    public void init(){
        for(int i=0; i<100; ++i){
            allBoxes.add(new Box());
            allItems.add(new Item(this.minItemSize + (this.maxItemSize - this.minItemSize) * random.nextDouble()));
        }
    }

    public long selectItemsToBoxes(){
        double sizeResult = 0.0;
        boolean isResultSmallerThanMaxCapacity = true;
        int boxId = 0;
        Collections.sort(allItems);
        ///////////////////////////
        /*
        1.ciagne z tylu jakis item
        2.biore pierwszy z przodu i robie operacje wynik = ostatni + pierwszy
        3.jesli jest < 1 to ok i lece dalej
            3bjest >1 to dodaje to co mam do tej pory do jakiegos wolnego pojemnika
        4.biore drugi z przodu i operacja wynik = wynik + drugi
        5.jesli jest <1 to wciaz nawalam do przodu, ciagnac nastepne liczby
            5b jest >1 to odstepuje i biore do pojeminka to co mam do tej pory, sciagajac je z listy


         */
        //////////////////////////////////
        while (!(allItems.isEmpty())) {
            boxId += 1;
            Item lastItem = allItems.get(allItems.size() - 1);
            sizeResult = lastItem.getItemSize();
            allBoxes.get(boxId).addItem(lastItem);
            allItems.remove(allItems.size() - 1);

            while(isResultSmallerThanMaxCapacity && !(allItems.isEmpty())){
                sizeResult += allItems.get(0).getItemSize();
                if(sizeResult <= Constants.MAX_CAPACITY_OF_BOX){
                    allBoxes.get(boxId).addItem(allItems.get(0));
                    allItems.remove(0);
                }
                else{
                    isResultSmallerThanMaxCapacity = false;
                    allBoxes.get(boxId).setIsActive(false);
                }
            }
        }
        return 1L;
    }
}
