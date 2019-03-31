package amazon.test;

import java.util.*;

public class CalculateDistanceExam {


    public static void main(String[] args) {

        System.out.println("This is alex from amazon");
        List <List <Integer>> all = new ArrayList <>();
        all.add(new ArrayList <>());

        //Add 2d coordinates
        List <Integer> listy = new ArrayList <>();
        listy.add(2);
        listy.add(3);
        listy.add(4);
        all.add(listy);
        all.add(listy);
        all.add(listy);


        List <List <Integer>> lists = new CalculateDistanceExam().returnPlaces(3, all);
       // lists.size();
     // List <List <Integer>> lists = new CalculateJava8().returnPlaces(3, all);

    }

    class Coordination {
        int x;
        int y;

        Coordination(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public List <List <Integer>> returnPlaces(int maxplaces, List <List <Integer>> potential) {


        //java 7
        //Find which pair has the smaller distance and save it in tree
        Map <Double, Coordination> saveAllTree = new TreeMap <>();
        for (int i = 0; i < potential.size(); i++) {
            List <Integer> intList = potential.get(i);
            for (int j = 0; j < intList.size(); j++) {
                double disresult = disBetween2Point(i, intList.get(j));
                saveAllTree.putIfAbsent(disresult, new Coordination(i, intList.get(j)));
            }
        }


        //java 8

        Map <Integer, List <Integer>> ordinatesMap = new HashMap <>();
        //Start Iteration
        saveAllTree.keySet().stream().limit(maxplaces).forEach(n -> {


            Coordination coordination = saveAllTree.get(n);
            System.out.println("x=" + coordination.getX() + ", y=" + coordination.getY());

            if (ordinatesMap.get(coordination.getX()) == null) {
                List pointy = new ArrayList <Integer>();
                pointy.add(coordination.getY());
                ordinatesMap.put(coordination.getX(), pointy);
            } else {
                List <Integer> integers = ordinatesMap.get(coordination.getX());
                integers.add(coordination.getY());
                ordinatesMap.replace(coordination.getX(), integers);

            }
        });

        //cordinatesMap.size();
        Iterator <Map.Entry <Integer, List <Integer>>> it = ordinatesMap.entrySet().iterator();

        //-- initialize return list
        List <List <Integer>> returnPointsLists = new ArrayList <>();
        for (int i = 0; i < potential.size(); i++) {
            returnPointsLists.add(null);
        }
        while (it.hasNext()) {
            Map.Entry pair = it.next();
            System.out.println(pair.getKey() + " --> " + pair.getValue());

            returnPointsLists.set((int) pair.getKey(), (List <Integer>) pair.getValue());
            // it.remove();
        }

        return returnPointsLists;
    }

    public static double disBetween2Point(int x, int y) {

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}
