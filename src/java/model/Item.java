package java.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by michto on 25.10.15.
 */
public class Item implements Comparable<Item>{
    private static AtomicInteger uniqueId = new AtomicInteger();
    private long id;
    private double itemSize;
    private boolean isInBox;

    public Item(double itemSize) {
        this.id = uniqueId.getAndIncrement();
        this.itemSize = itemSize;
        this.isInBox = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getItemSize() {
        return itemSize;
    }

    public void setItemSize(double itemSize) {
        this.itemSize = itemSize;
    }

    public boolean isInBox() {
        return isInBox;
    }

    public void setIsInBox(boolean isInBox) {
        this.isInBox = isInBox;
    }

    @Override
    public int compareTo(Item item) {
        double compareSize = ((Item) item).getItemSize();
        if (this.itemSize > compareSize)
            return 1;
        else return 0;
    }
}
