package model;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by michto on 25.10.15.
 */
public class Item implements Comparable<Item>{
    private static AtomicInteger uniqueId = new AtomicInteger();
    private long id;
    private BigDecimal itemSize;
    private boolean isInBox;

    public Item(BigDecimal itemSize) {
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

    public BigDecimal getItemSize() {return itemSize;
    }

    public void setItemSize(BigDecimal itemSize) {
        this.itemSize = itemSize;
    }

    public boolean isInBox() {
        return isInBox;
    }

    public void setIsInBox(boolean isInBox) {this.isInBox = isInBox;}

    @Override
    public int compareTo(Item item) {
        BigDecimal compareSize = ((Item) item).getItemSize();
        return this.itemSize.compareTo(compareSize);
    }
}
