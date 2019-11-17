package amazon.test;

import java.awt.geom.Point2D;
import java.util.Comparator;
import java.util.List;

public class NewIdeaCalculate {

    public List <List <Integer>> returnPlacesNew(int maxplaces, List <List <Integer>> potential) {

        Comparator <List<Integer>> a= (List<Integer> x1,List<Integer> x2) ->{  return x1.size() - x2.size();};


        //potential.forEach(n);
        potential.sort(a);

        potential.forEach(System.out::println);

        return null;
    }


}
