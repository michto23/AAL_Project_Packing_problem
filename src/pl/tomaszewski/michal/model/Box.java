package pl.tomaszewski.michal.model;

import pl.tomaszewski.michal.common.Constants;

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
    private Set<Item> itemsInBox;

    public Box() {
        this.isActive = true;
        this.id = uniqueId.getAndIncrement();
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

    public Set<Item> getItemsInBox() {
        return itemsInBox;
    }

    public void setItemsInBox(Set<Item> itemsInBox) {
        this.itemsInBox = itemsInBox;
    }

    //REGULAR METHODS
    public long addItem(Item item){
        //if przekroczy objetosc to eexception
        this.usedCapacity += item.getItemSize();
        this.freeCapacity -= item.getItemSize();
        this.itemsInBox.add(item);
        return this.itemsInBox.size();
    }


}
