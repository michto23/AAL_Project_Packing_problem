package model;

import common.Constants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by michto on 24.10.15.
 */
public class Box {
    private static final BigDecimal MAX_CAPACITY = Constants.MAX_CAPACITY_OF_BOX;
    private static AtomicInteger uniqueId = new AtomicInteger();
    private long id;
    private BigDecimal usedCapacity = BigDecimal.valueOf(0);
    private BigDecimal freeCapacity = Constants.MAX_CAPACITY_OF_BOX;
    private boolean isActive;
    private List<Item> itemsInBox = new ArrayList<>();

    public Box() {
        this.isActive = true;
        this.id = uniqueId.getAndIncrement();
    }

    public Box(List<Item> itemsInBox) {
        this.isActive = true;
        this.id = uniqueId.getAndIncrement();
        this.itemsInBox = itemsInBox;
        for(Item item : itemsInBox){
            this.usedCapacity = this.usedCapacity.add(item.getItemSize());
            this.freeCapacity = this.freeCapacity.subtract(item.getItemSize());
        }
    }

    public Box(Box b) {
        this.id = b.id;
        this.usedCapacity = b.usedCapacity;
        this.freeCapacity = b.freeCapacity;
        this.isActive = b.isActive;
        this.itemsInBox = b.itemsInBox;
    }

    //GETTERS SETTERS
    public static BigDecimal getMaxCapacity() {
        return MAX_CAPACITY;
    }

    public long getId() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getUsedCapacity() {
        return usedCapacity;
    }

    public void setUsedCapacity(BigDecimal usedCapacity) {
        this.usedCapacity = usedCapacity;
    }

    public BigDecimal getFreeCapacity() {
        return freeCapacity;
    }

    public void setFreeCapacity(BigDecimal freeCapacity) {
        this.freeCapacity = freeCapacity;
    }

    public List<Item> getItemsInBox() {
        return itemsInBox;
    }

    public void setItemsInBox(List<Item> itemsInBox) {
        this.itemsInBox = itemsInBox;
    }

    //REGULAR METHODS
    public long addItem(Item item){
        this.usedCapacity = this.usedCapacity.add(item.getItemSize());
        this.freeCapacity = this.freeCapacity.subtract(item.getItemSize());
        this.itemsInBox.add(item);
        return this.itemsInBox.size();
    }

    public Box deepCopy() {
        Box copy = new Box();
        copy.itemsInBox = new ArrayList<Item>(itemsInBox);
        copy.freeCapacity = freeCapacity;
        copy.usedCapacity = usedCapacity;
        copy.id = id;
        return copy;
    }

    public void remove(Item item) {
        itemsInBox.remove(item);
        this.usedCapacity = this.usedCapacity.subtract(item.getItemSize());
        this.freeCapacity = this.freeCapacity.add(item.getItemSize());
    }


}
