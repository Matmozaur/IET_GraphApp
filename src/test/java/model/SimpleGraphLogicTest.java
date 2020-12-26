package model;

import controller.visualisation.painters.Visualable;
import model.graphs.logic.SimpleGraphLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

class SimpleGraphLogicTest {
    private static SimpleGraphLogic G;
    private static final List<Integer> vList=new LinkedList<>();
    private static final List<Integer[]> eList=new LinkedList<>();
    private static SequancePainter sPainter;


    @BeforeAll
    static void prepareGraphLogic(){
        G=new SimpleGraphLogic(null,5,new boolean[][] {{true,true,true,false,false},{true,true,true,false,false},
                {true,true,true,true,false},{false,false,true,true,true},{false,false,false,true,true}});
        sPainter=new SequancePainter();
    }

    @Test
    void connectedTestedTrue(){
        Assertions.assertTrue(G.connected(0, 4));
    }

    @Test
    void connectedTestedFalse(){
        G.remove(3,4);
        Assertions.assertFalse(G.connected(0, 4));
        G.addE(3,4);
    }





    private static class SequancePainter implements Visualable{
        @Override
        public void visualVertex(int x,String... args){
            vList.add(x);
        }

        @Override
        public void visualEdge(int x,int y){
            eList.add(new Integer[] {x,y});
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
