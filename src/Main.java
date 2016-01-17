import algorithms.BruteForce;
import algorithms.FirstFitAscending;
import algorithms.LastAndFirstFitAscending;
import algorithms.MartelloToth;
import model.Item;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Item item = new Item(0.0);
        item.setItemSize(0.99);
        Item item2 = new Item(0.0);
        item2.setItemSize(0.94);
        Item item3 = new Item(0.0);
        item3.setItemSize(0.79);
        Item item4 = new Item(0.0);
        item4.setItemSize(0.64);
        Item item5 = new Item(0.0);
        item5.setItemSize(0.50);
        Item item6 = new Item(0.0);
        item6.setItemSize(0.46);
        Item item7 = new Item(0.0);
        item7.setItemSize(0.43);
        Item item8 = new Item(0.0);
        item8.setItemSize(0.37);
        Item item9 = new Item(0.0);
        item9.setItemSize(0.32);
        Item item10 = new Item(0.0);
        item10.setItemSize(0.19);
        Item item11 = new Item(0.0);
        item11.setItemSize(0.18);
        Item item12 = new Item(0.0);
        item12.setItemSize(0.07);
        Item item13 = new Item(0.0);
        item13.setItemSize(0.06);
        Item item14 = new Item(0.0);
        item14.setItemSize(0.03);

        ArrayList<Item> items = new ArrayList<Item>();
        items.add(item);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        items.add(item6);
        items.add(item7);
        items.add(item8);
        items.add(item9);
        items.add(item10);
        items.add(item11);
        items.add(item12);
        items.add(item13);
        items.add(item14);

        BruteForce bruteForce = new BruteForce(items);
        FirstFitAscending firstFitAscending = new FirstFitAscending(items);
        LastAndFirstFitAscending lastAndFirstFitAscending = new LastAndFirstFitAscending(items);
        MartelloToth martelloToth = new MartelloToth(items);
//        bruteForce.solvePackingProblem();
//        firstFitAscending.solvePackingProblem();
//        lastAndFirstFitAscending.solvePackingProblem();
        martelloToth.solvePackingProblem();
    }
}
