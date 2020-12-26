package model.graphs.logic;
import controller.visualisation.painters.Visualable;
import model.elements.Vertex;
import model.graphs.representation.WeightGraph;
import java.util.stream.Collectors;


public class WeightGraphLogic extends SimpleGraphLogic {
    private final int[][] W ;

    public WeightGraphLogic(WeightGraph parent, int v, boolean[][] e, int[][] w) {
        super(parent, v, e);
        W = w;
    }

    public void remove(int x) {
        super.remove(x);
        for(int i=x;i<this.V;i++) {
            for(int j=0;j<this.V;j++) {
                this.W[i][j]=this.W[i+1][j];
                this.W[j][i]=this.W[j][i+1];
            }
        }
        this.V--;
    }

    public void addE(int a,int b,int w){
       super.addE(a,b);
       W[a][b] = W[b][a] = w;
    }

    public  void remove(int a,int b){
        super.remove(a,b);
        W[a][b]=W[b][a]=0;
    }

    public void vdijkstra(int vStart, int vEnd, Visualable painter) throws InterruptedException {
        painter.visualVertex(vStart,"0");
        int[] distances = new int[V];
        int[] available = new int[V];
        int[] parentv = new int[V];
        int sup = Integer.MAX_VALUE;
        for (int i = 0; i <V; i++) {
            distances[i] = sup;
            available[i] = 0;
        }
        distances[vStart] = 0;
        available[vStart] = 2;
        parentv[vStart] = -1;
        int vLast = vStart;
        int iteration = 0;
        while (iteration < V) {
            iteration += 1;
            for (int v : parent.getVertexes().stream().map(Vertex::getNumb).collect(Collectors.toList()) ) {
                if (available[v] == 0 && E[v][vLast]) {
                    available[v] = 1;
                }
            }
            int min = Integer.MAX_VALUE;
            for(int v : parent.getVertexes().stream().map(Vertex::getNumb).collect(Collectors.toList())) {
                if(available[v] == 1) {
                    if(distances[v] < min) {
                        min = distances[v];
                        vLast = v;
                    }
                }
            }
            available[vLast] = 2;
            // relaxation
            for(int v : parent.getVertexes().stream().map(Vertex::getNumb).collect(Collectors.toList())) {
                if(E[v][vLast]) {
                    int weight = W[v][vLast];
                    if(distances[v] > distances[vLast] + weight) {
                        distances[v] = distances[vLast] + weight;
                        painter.visualVertex(v,""+distances[v]);
                        if(parentv[v]>=0&&parentv[v]!=v){
                            painter.clearEdge(v,parentv[v]);
                        }
                        parentv[v] = vLast;
                        if(parentv[v]>=0&&parentv[v]!=v){
                            painter.visualEdge(v,parentv[v]);
                        }
                        if(v == vEnd) {
                            painter.visualMainVertex(v);
                            while (v!=vStart) {
                                int x = v;
                                v = parentv[v];
                                painter.visualMainEdge(x,v);
                                painter.visualMainVertex(v);

                            }
                            return;
                        }
                    }
                }
            }
        }
        for(Vertex v:parent.getVertexes()) {
            int i=v.getNumb();
        }

    }
}