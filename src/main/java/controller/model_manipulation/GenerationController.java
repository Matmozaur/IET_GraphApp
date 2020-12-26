package controller.model_manipulation;

import model.additional.Algos;
import model.elements.Edge;
import model.elements.Vertex;
import model.graphs.representation.WeightGraph;
import model.settings.Settings;

public interface  GenerationController {
    int HEIGHT=800;
    int WIDTH=900;

    static WeightGraph generateFull(int v){
        Settings settings=new Settings();
        WeightGraph G=new WeightGraph();
        int step=(2*(HEIGHT-100)+2*(WIDTH-100))/(v);
        int[] C;
        for(int i=0;i<v;i++){
            C= Algos.coors(WIDTH,HEIGHT,i*step);
            G.add(new Vertex(C[0],C[1],i,null,settings));
            if(i>0){
                for(int j=0;j<i;j++){
                    G.add(new Edge(G.getVertexes().get(j),G.getVertexes().get(i),settings));
                }
            }
        }
        return G;
    }

    static WeightGraph generateCycle(int v){
        Settings settings=new Settings();
        WeightGraph G=new WeightGraph();
        int step=(2*(HEIGHT-100)+2*(WIDTH-100))/(v);
        int[] C;
        for(int i=0;i<v;i++){
            C= Algos.coors(WIDTH,HEIGHT,i*step);
            G.add(new Vertex(C[0],C[1],i,null,settings));
            if(i>0){
                G.add(new Edge(G.getVertexes().get(i-1),G.getVertexes().get(i),settings));
            }
        }
        if(v>1){
            G.add(new Edge(G.getVertexes().get(0),G.getVertexes().get(v-1),settings));
        }
        return G;
    }

    static WeightGraph generatePath(int v){
        Settings settings=new Settings();
        WeightGraph G=new WeightGraph();
        int step=(2*(HEIGHT-100)+2*(WIDTH-100))/(v);
        int[] C;
        for(int i=0;i<v;i++){
            C= Algos.coors(WIDTH,HEIGHT,i*step);
            G.add(new Vertex(C[0],C[1],i,null,settings));
            if(i>0){
                G.add(new Edge(G.getVertexes().get(i-1),G.getVertexes().get(i),settings));
            }
        }
        return G;
    }

    static WeightGraph generateBT(int v){
        Settings settings=new Settings();
        WeightGraph G=new WeightGraph();
        int stepV=(int)(HEIGHT/(Math.log1p(v)/Math.log(2)+1));
        int counter=0;
        for(int i=0;i<(int)(Math.log1p(v)/Math.log(2)+1);i++){
            int stepH=(int)(WIDTH/(Math.pow(2,i)+1));
            for(int j=0;j<(int)Math.pow(2,i);j++){
                if(counter<v){
                    Vertex v1=new Vertex(stepH*(j+1),50+stepV*i,counter,null,settings);
                    G.add(v1);
                    counter++;
                    if(counter>1){
                        G.add(new Edge(v1,G.getVertexes().get((counter)/2-1),settings));
                    }
                }
            }
        }
        return G;
    }

    static WeightGraph generateGrid(int v, int w) {
        Settings settings=new Settings();
        WeightGraph G=new WeightGraph();
        int stepH=(int)(WIDTH/v);
        int stepW=(int)(HEIGHT/w);
        int counter =0;
        for(int i=0;i<v;i++){
            for(int j=0;j<w;j++){
                    Vertex v1=new Vertex(50+stepH*i,50+stepW*j,counter,null,settings);
                    G.add(v1);
                    if(i>0){
                        G.add(new Edge(v1,G.getVertexes().get(counter-v),settings));
                    }
                    if(j>0){
                        G.add(new Edge(v1,G.getVertexes().get(counter-1),settings));
                    }
                counter++;
                }
            }
        return G;
    }
}
