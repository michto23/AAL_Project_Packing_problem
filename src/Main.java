import algorithms.BruteForce;
import algorithms.FirstFitAscending;
import algorithms.LastAndFirstFitAscending;
import model.Item;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Item item = new Item(0.0);
        item.setItemSize(0.20);
        Item item2 = new Item(0.0);
        item2.setItemSize(0.60);
        Item item3 = new Item(0.0);
        item3.setItemSize(0.90);
        Item item4 = new Item(0.0);
        item4.setItemSize(0.05);
        Item item5 = new Item(0.0);
        item5.setItemSize(0.37);
        Item item6 = new Item(0.0);
        item6.setItemSize(0.6);

        ArrayList<Item> items = new ArrayList<Item>();
        items.add(item);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        items.add(item6);

        BruteForce bruteForce = new BruteForce(items);
        FirstFitAscending firstFitAscending = new FirstFitAscending(items);
        LastAndFirstFitAscending lastAndFirstFitAscending = new LastAndFirstFitAscending(items);
        bruteForce.solvePackingProblem();
        firstFitAscending.solvePackingProblem();
        lastAndFirstFitAscending.solvePackingProblem();
    }
}
