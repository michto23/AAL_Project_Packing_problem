import algorithms.*;
import common.Constants;
import model.Box;
import model.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void printBoxes(List<Box> boxes){
        System.out.println("Liczba wykorzystanych boxow: " + boxes.size());
        for(Box box : boxes){
            if(!box.getItemsInBox().isEmpty()){
                System.out.println("Zajęte miejsce : " + box.getUsedCapacity());
            }
        }
        System.out.print("\n\n\n\n\n");
    }

    private static void addItem(double value, List<Item>items){
        Item item = new Item(value);
        items.add(item);
    }

    public static void main(String[] args) {
        ArrayList<Item> items = new ArrayList<Item>();
//        addItem(0.99, items);
//        addItem(0.94, items);
//        addItem(0.79, items);
//        addItem(0.64, items);
//        addItem(0.50, items);
//        addItem(0.46, items);
//        addItem(0.43, items);
//        addItem(0.37, items);
//        addItem(0.32, items);
//        addItem(0.19, items);
//        addItem(0.18, items);
//        addItem(0.07, items);
//        addItem(0.06, items);
//        addItem(0.03, items);
//        addItem(0.18, items);
//        addItem(0.07, items);
//        addItem(0.06, items);
//        addItem(0.03, items);
//        addItem(0.99, items);
//        addItem(0.94, items);
//        addItem(0.79, items);
//        addItem(0.64, items);
//        addItem(0.50, items);
//        addItem(0.46, items);
//        addItem(0.43, items);
//        addItem(0.37, items);
//        addItem(0.32, items);
//        addItem(0.19, items);
//        addItem(0.18, items);
//        addItem(0.07, items);
//        addItem(0.06, items);
//        addItem(0.03, items);
//        addItem(0.07, items);
//        addItem(0.06, items);
//        addItem(0.03, items);
//        addItem(0.50, items);
//        addItem(0.94, items);
//        addItem(0.79, items);
//        addItem(0.64, items);
//        addItem(0.50, items);
//        addItem(0.46, items);
//        addItem(0.43, items);
//        addItem(0.37, items);
//        addItem(0.32, items);
//        addItem(0.19, items);
//        addItem(0.18, items);
//        addItem(0.07, items);
//        addItem(0.06, items);
//        addItem(0.03, items);
//        addItem(0.07, items);
//        addItem(0.06, items);
//        addItem(0.03, items);
//        addItem(0.99, items);
//        addItem(0.94, items);
//        addItem(0.79, items);
//        addItem(0.64, items);
//        addItem(0.50, items);
//        addItem(0.46, items);
//        addItem(0.43, items);
//        addItem(0.37, items);
//        addItem(0.32, items);
//        addItem(0.19, items);
//        addItem(0.18, items);
//        addItem(0.07, items);
//        addItem(0.06, items);
//        addItem(0.03, items);
//        addItem(0.79, items);
//        addItem(0.64, items);
//        addItem(0.50, items);
//        addItem(0.46, items);
//        addItem(0.43, items);
//        addItem(0.37, items);
//        addItem(0.32, items);
//        addItem(0.19, items);
//        addItem(0.18, items);
//        addItem(0.07, items);
//        addItem(0.06, items);
//        addItem(0.03, items);
//        addItem(0.07, items);
//        addItem(0.06, items);
//        addItem(0.03, items);
//        addItem(0.99, items);
//        addItem(0.94, items);
//        addItem(0.79, items);
//        addItem(0.64, items);
//        addItem(0.50, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.01, items);
//        addItem(0.01, items);
//        addItem(0.01, items);
//        addItem(0.01, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.01, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.99, items);
//        addItem(0.94, items);
//        addItem(0.79, items);
//        addItem(0.64, items);
//        addItem(0.50, items);
//        addItem(0.46, items);
//        addItem(0.43, items);
//        addItem(0.37, items);
//        addItem(0.32, items);
//        addItem(0.19, items);
//        addItem(0.18, items);
//        addItem(0.07, items);
//        addItem(0.06, items);
//        addItem(0.03, items);
//        addItem(0.18, items);
//        addItem(0.07, items);
//        addItem(0.06, items);
//        addItem(0.03, items);
//        addItem(0.99, items);
//        addItem(0.94, items);
//        addItem(0.79, items);
//        addItem(0.64, items);
//        addItem(0.50, items);
//        addItem(0.46, items);
//        addItem(0.43, items);
//        addItem(0.37, items);
//        addItem(0.32, items);
//        addItem(0.19, items);
//        addItem(0.18, items);
//        addItem(0.07, items);
//        addItem(0.06, items);
//        addItem(0.03, items);
//        addItem(0.07, items);
//        addItem(0.06, items);
//        addItem(0.03, items);
//        addItem(0.50, items);
//        addItem(0.94, items);
//        addItem(0.79, items);
//        addItem(0.64, items);
//        addItem(0.50, items);
//        addItem(0.46, items);
//        addItem(0.43, items);
//        addItem(0.37, items);
//        addItem(0.32, items);
//        addItem(0.19, items);
//        addItem(0.18, items);
//        addItem(0.07, items);
//        addItem(0.06, items);
//        addItem(0.03, items);
//        addItem(0.07, items);
//        addItem(0.06, items);
//        addItem(0.03, items);
//        addItem(0.99, items);
//        addItem(0.94, items);
//        addItem(0.79, items);
//        addItem(0.64, items);
//        addItem(0.50, items);
//        addItem(0.46, items);
//        addItem(0.43, items);
//        addItem(0.37, items);
//        addItem(0.32, items);
//        addItem(0.19, items);
//        addItem(0.18, items);
//        addItem(0.07, items);
//        addItem(0.06, items);
//        addItem(0.03, items);
//        addItem(0.79, items);
//        addItem(0.64, items);
//        addItem(0.50, items);
//        addItem(0.46, items);
//        addItem(0.43, items);
//        addItem(0.37, items);
//        addItem(0.32, items);
//        addItem(0.19, items);
//        addItem(0.18, items);
//        addItem(0.07, items);
//        addItem(0.06, items);
//        addItem(0.03, items);
//        addItem(0.07, items);
//        addItem(0.06, items);
//        addItem(0.03, items);
//        addItem(0.99, items);
//        addItem(0.94, items);
//        addItem(0.79, items);
//        addItem(0.64, items);
//        addItem(0.50, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.01, items);
//        addItem(0.01, items);
//        addItem(0.01, items);
//        addItem(0.01, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.51, items);
//        addItem(0.01, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//        addItem(0.80, items);
//         addItem(0.99, items);
//         addItem(0.01, items);
//        addItem(0.99, items);
//        addItem(0.01, items);
//        addItem(0.99, items);
//        addItem(0.01, items);
//        addItem(0.55, items);
//        addItem(0.44, items);
//        addItem(0.01, items);
//        addItem(0.55, items);
//        addItem(0.44, items);
//        addItem(0.01, items);
//        addItem(0.55, items);
//        addItem(0.44, items);
//        addItem(0.01, items);






        BruteForce bruteForce = new BruteForce(items);
        FirstFitAscending firstFitAscending = new FirstFitAscending(items);
        LastAndFirstFitAscending lastAndFirstFitAscending = new LastAndFirstFitAscending(items);
        MartelloToth martelloToth = new MartelloToth(items);
        LowerBoundL2 lowerBoundL2 = new LowerBoundL2(items);
//        bruteForce.solvePackingProblem();
//        Main.printBoxes(bruteForce.currentBestBoxes);
        Main.printBoxes(firstFitAscending.solvePackingProblem());
        Main.printBoxes(lastAndFirstFitAscending.solvePackingProblem());
        Main.printBoxes(martelloToth.solvePackingProblem());


        System.out.println("l2 to " + lowerBoundL2.solveLowerBoundL2());

        double a = 0;
        double b = 0.1;
        double c = 0.01;
        BigDecimal biga = new BigDecimal(String.valueOf(0.91111));
        BigDecimal bigb = new BigDecimal("0.0111111");
        BigDecimal bigc = biga.subtract(bigb);


        a = b - c;
        if(1>0){
            System.out.println(bigc);
        }

    }
}
