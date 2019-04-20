package amazon.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import amazon.test.CalculateDistanceExam.Coordination;

public class CalculateJava8 {

    public List <List<Integer>> returnPlaces(int maxplaces, List <List <Integer>> potential) {
        AtomicInteger counter = new AtomicInteger(0);
        Comparator <Coordination> byDistance = (point1,point2)->{
           return (int) (CalculateDistanceExam.disBetween2Point(point1.getX(),point1.y)-CalculateDistanceExam.disBetween2Point(point2.getX(),point2.y));
        };


        //potential.stream().map

        List <Coordination> coordinationList = potential.stream().map((List <Integer> n) -> {

                    List <Coordination> listpoint = new ArrayList <>();
                    for (int y : n) {
                        listpoint.add(new Coordination(counter.get(), y));
                    }
                counter.getAndIncrement();
                    return listpoint;
                }

        ).flatMap(n -> n.stream()).sorted(byDistance).limit(maxplaces).collect(Collectors.toList());


//        List <Coordination> coordinationList = potential.stream().flatMap(n -> n.stream()).map(n ->
//                {
//                    counter.getAndIncrement();
//                    return new Coordination(counter.get(), n);
//                    //counter.get();
//                }
//        ).sorted(byDistance).limit(maxplaces).collect(Collectors.toList());

        coordinationList.size();
        coordinationList.forEach(System.out::println);
        List <List <Integer>> returnPointsLists = new ArrayList <>();
        for (int i = 0; i < potential.size(); i++) {
            returnPointsLists.add(null);
        }
       // returnPointsLists.set(1,new ArrayList <Integer>());
        for(Coordination points:coordinationList){
            List list = getList(returnPointsLists, points.getX());
            list.add(points.getY());
            returnPointsLists.set( points.getX(),list);

        }



        return returnPointsLists;
    }

    private List getList(List<List <Integer>> list, int index){

        if(list.get(index)==null)
            return new ArrayList();
        else
            return list.get(index);
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

        @Override
        public String toString() {
            return "["+x+","+y+"]";
        }
    }
}
