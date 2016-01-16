package model;

import common.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by michto on 24.10.15.
 */
public class Box {
    private static final int MAX_CAPACITY = Constants.MAX_CAPACITY_OF_BOX;
    private static AtomicInteger uniqueId = new AtomicInteger();
    private long id;
    private double usedCapacity;
    private double freeCapacity;
    private boolean isActive;
    private List<Item> itemsInBox = new ArrayList<>();

    public Box() {
        this.isActive = true;
        this.id = uniqueId.getAndIncrement();
        this.usedCapacity = 0;
        this.freeCapacity = Constants.MAX_CAPACITY_OF_BOX;
    }

    public Box(List<Item> itemsInBox) {
        this.isActive = true;
        this.id = uniqueId.getAndIncrement();
        this.itemsInBox = itemsInBox;
        this.usedCapacity = 0;
        this.freeCapacity = Constants.MAX_CAPACITY_OF_BOX;
        for(Item item : itemsInBox){
            this.usedCapacity += item.getItemSize();
            this.freeCapacity -= item.getItemSize();
        }
    }

    //GETTERS SETTERS
    public static int getMaxCapacity() {
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

    public double getUsedCapacity() {
        return usedCapacity;
    }

    public void setUsedCapacity(double usedCapacity) {
        this.usedCapacity = usedCapacity;
    }

    public double getFreeCapacity() {
        return freeCapacity;
    }

    public void setFreeCapacity(double freeCapacity) {
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
        //if przekroczy objetosc to exception
        this.usedCapacity += item.getItemSize();
        this.freeCapacity -= item.getItemSize();
        this.itemsInBox.add(item);
        return this.itemsInBox.size();
    }


}
