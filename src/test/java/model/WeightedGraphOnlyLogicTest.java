package model;

import controller.visualisation.painters.Visualable;
import model.graphs.logic.WeightGraphLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

class WeightedGraphOnlyLogicTest {
    private static WeightGraphLogic G;
    private static final List<Integer> vList=new LinkedList<>();
    private static final List<int[]> eList=new LinkedList<>();
    private static SequancePainter sPainter;


    @BeforeAll
    static void prepareGraphLogic(){
        G=new WeightGraphLogic(null,5,new boolean[][] {{true,true,true,false,false},{true,true,true,false,false},
                {true,true,true,true,false},{false,false,true,true,true},{false,false,false,true,true}},
                new int[][] {{0,2,2,0,0},{2,0,5,0,0},{2,5,0,1,0},{0,0,1,0,7},{0,0,0,7,0}});
        sPainter=new SequancePainter();
    }




    private static class SequancePainter implements Visualable {
        @Override
        public void visualVertex(int x,String... args){
            vList.add(x);
        }

        @Override
        public void visualEdge(int x,int y){
            eList.add(new int[] {x,y});
        }

        @Override
        public void visualMainVertex(int v, String... arg) throws InterruptedException {

        }

        @Override
        public void visualMainEdge(int a, int b) throws InterruptedException {

        }

        @Override
        public void clearEdge(int x,int y){
            eList.remove(new Integer[] {x,y});
        }

         void clear(){
            vList.clear();
            eList.clear();
        }
    }

}
