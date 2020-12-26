package model.graphs.logic;

import controller.visualisation.painters.Visualable;
import model.graphs.representation.WeightGraph;


import java.util.LinkedList;
import java.util.Queue;

public class SimpleGraphLogic extends GraphLogic {

    final boolean[][] E;

    public SimpleGraphLogic(WeightGraph parent, int v, boolean[][] e) {
        super(parent,v);
        E=e;
    }

    public void addE(int a,int b){
        E[a][b] = E[b][a] = true;
    }

    void remove(int x) {
        for(int i=x;i<this.V;i++) {
            for(int j=0;j<this.V;j++) {
                this.E[i][j]=this.E[i+1][j];
                this.E[j][i]=this.E[j][i+1];
            }
        }
        this.V--;
    }

    public void remove(int a, int b) {
        E[a][b] = E[b][a] = false;
    }


    //Visualisation algorithms

    public void vDFS(int v, int w, Visualable painter) throws InterruptedException{
        boolean[] Visited=new boolean[n];
        this.vDFSUtil(v,w,Visited,painter);
    }

    private boolean vDFSUtil(int v, int w, boolean[] Visited,Visualable painter ) throws InterruptedException {
        Visited[v]=true;
        if(last>=0) painter.visualEdge(last,v);
        painter.visualVertex(v);
        if(v==w) {
            painter.visualMainVertex(v);
            return true;
        }
        for(int i=0;i<V;i++){
            last=v;
            if(E[v][i] && !Visited[i]){
                if(vDFSUtil(i,w,Visited,painter)) {
                    painter.visualMainEdge(v, i);
                    painter.visualMainVertex(v);
                    return true;
                }
            }
        }
        return false;
    }

    public void vBFS(int v, int w,Visualable painter) throws InterruptedException{
        last=-1;
        painter.visualVertex(v);
        boolean[] Visited=new boolean[n];
        int[] Parents=new int[n];
        Visited[v]=true;
        Queue<Integer> Q=new LinkedList<>();
        vBFSUtil(v,w,Visited,Parents,Q,painter);
        while (w != v) {
            painter.visualMainVertex(w);
            painter.visualMainEdge(w, Parents[w]);
            w = Parents[w];
        }
        painter.visualMainVertex(v);
    }

    private boolean vBFSUtil(int v,int w, boolean[] Visited, int[] Parents, Queue<Integer> Q, Visualable painter) throws InterruptedException{
        last=v;
        for(int i=0;i<V;i++){
            if(E[v][i] && !Visited[i]){
                Visited[i]=true;
                if(last>=0) painter.visualEdge(last,i);
                painter.visualVertex(i);
                Parents[i] = last;
                if(i==w) {
                    return true;
                }
                Q.add(i);
            }
        }
        int x;
        while(!Q.isEmpty()){
            x=Q.remove();
            if(vBFSUtil(x,w,Visited,Parents,Q,painter)) {
                return true;
            };
        }
        return false;
    }


    //pure graph algorithms

    /**
     * DFS, returning number of vertex in sequence in DFS
     * @param v current vertex
     * @param numb current number in sequence
     * @param Sequence sequence of vertices
     * @param Visited table if visited
     * @return number in sequence
     */
    private int DFS(int v, int numb, int[] Sequence, boolean[] Visited){
        Visited[v]=true;
        Sequence[numb]=v;
        numb++;
        for(int i=0;i<this.V;i++){
            if(this.E[v][i] && !Visited[i]){
                numb=DFS(i,numb,Sequence,Visited);
            }
        }
        return numb;
    }


    /**
     * Checking if 2 vertices connected
     * @param v first vertex
     * @param u secound vertex
     * @return if connected
     */

    public boolean connected(int v, int u){
        int numb=0;
        int[] Sequence = new int[101];
        boolean[] Visited = new boolean[50];
        DFS(v,numb,Sequence,Visited);
        //DFS
        return Visited[u];
    }


    @Override
    public String toString(){
        String s=""+V+"\n";
        for(int i=0;i<V;i++){
            for(int j=0;j<=i;j++){
                if(E[i][j]) System.out.print(i+"-"+j+"  ");
            }
        }
        return s;
    }

}